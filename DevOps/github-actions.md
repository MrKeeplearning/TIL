# Workflows

- Github Actions에서 가장 상위 개념
- 작업흐름
- 자동화 해놓은 작업 과정
- .github/workflows 폴더 아래에 YAML 파일로 설정
- workflows YAML파일에는 크게 2가지를 정의해야 한다.
    1. `on` 속성을 통해서 언제 해당 workflow를 실행할지 트리거 정의
    2. `jobs` 속성을 통해서 해당 workflow가 구체적으로 어떤 일을 해야 하는지 정의

## 1) `on` 속성

- 코드 저장소의 main 브랜치에 push 이벤트가 발생할 때마다 workflow를 실행

    ```yaml
    on:
        push:
            branches:
                - main
    jobs:
    ```
    
- 매일 자정 workflow를 실행
    
    ```yaml
    on:
        schedule:
           - cron: "0 0 * * *"
    jobs:
    ```
        
- 수동으로 해당 workflow를 실행
    
    ```yaml
    on:
        workflow_dispatch:
    
    jobs:
    ```
    

## 2) `jobs` 속성

- Github Actions에서 작업(job)이란 독립된 가상 머신(machine) 또는 컨테이너(container)에서 돌아가는 하나의 처리 단위를 의미한다.
- 하나의 workflow는 여러 개의 job들로 구성되며 적어도 하나의 job은 있어야 한다.
    - 실행할 작업이 없는 상태라면, workflow의 의미가 없어진다.
- 모든 job은 기본적으로 동시에 실행되고 필요 시 작업 간 의존 관계를 설정하여 작업이 실행되는 순서를 제어할 수 있다.
- 작업 식별자(ID)와 작업 세부 내용 간 맵핑(mapping)형태로 명시
- `job1`, `job2`, `job3` 라는 작업 ID를 가진 3개의 작업을 추가하려면 아래와 같이 설정한다.
    
    ```yaml
    jobs:
        job1:
            # job1에 대한 세부 내용
        job2:
            # job2에 대한 세부 내용
        job3:
            # job3에 대한 세부 내용
    ```
    
- `runs-on` 속성은 필수적으로 들어가야 한다.
    - 어떤 환경에서 실행할 것인가? (e.g., 리눅스, 윈도우, …)
        
        ```yaml
        # 우분투의 최신 환경에서 실행하고 싶다면?
        
        jobs:
            job1:
                runs-on: ubuntu-latest
                steps: 
        ```
        

# Steps

- 각 작업(job)은 하나 이상의 단계(step)로 모델링 된다.
- 작업 단계는 단순 command나 script가 될 수도 있다. 또는 action이라는 명령이 될 수도 있다.
- command나 script를 실행할 때는 `run` 속성을 사용하고, action을 사용할 때는 `uses` 속성을 사용한다.
- 예시
    - javascript 프로젝트에서 테스트를 돌리기 위해서는 코드 저장소의 코드를 작업 실행 환경으로 내려 받고 , 패키지를 설치한 후, 테스트 스크립트를 실행해야 한다.
    - **checkout**
        - checkout은 깃헙에서 제공하는 워크스페이스(현재 workflow를 실행하는 공간)에서 내 저장소가 위치한 곳으로 이동한다고 생각하면 된다.
    
    ```yaml
    jobs:
        test:
            runs-on: ubuntu-latest
            steps:
                - uses: actions/checkout@v3
                - run: npm install
                - run: npm test
    ```
    
    🚨 **주의**: YAML 문법에서 시퀀스(sequence) 타입을 사용하기 때문에 각 단계 앞에는 반드시 `-` 를 붙여야 한다.
    

# Actions

- Github Actions에서 빈번하게 필요한 반복 단계를 재사용하기 용이하도록 제공하는 일종의 작업 공유 매커니즘
- 하나의 코드 저장소 범위 내에서 여러 workflow 간의 공유가 가능
- 공개 코드 저장소를 통해 액션을 공유하면 Github 상의 모든 코드 저장소에서 사용 가능

## 1) `actions/checkout`

- Github에서 제공하는 대표적인 공개 액션
- 대부분의 CI/CD 작업은 코드 저장소에서 코드를 작업 실행 환경으로 내려받는 것에서 시작하기 때문에 `checkout` 액션은 상당히 범용적으로 사용된다.
- [actions/checkout 레포지토리]([https://github.com/actions/checkout](https://github.com/actions/checkout))
    
    ### Git 버전 관리 시스템과의 연관성
    
    - Git 버전 관리 시스템에서 checkout이라는 개념은 일반적으로 코드 저장소(repository)에서 특정 branch로 전환하는 작업을 뜻한다. 이 작업을 위해서는 우선 원격 저장소에 올려둔 코드를 로컬 컴퓨터로 내려받는 작업이 선행되어야 한다.
    - GitHub Actions 입장에서 보면, GitHub 레포지토리에 올려둔 코드를 CI 서버로 내려 받은 뒤 특정 branch로 전환하는 행위라고 볼 수 있다.
    - 매번 필요한 체크아웃 단계를 모든 workflow에서 항상 shell 스크립트로 작성하고, CI 서버와 코드 저장소 간 인증과 같은 절차를 신경써줘야 한다면 번거롭기 때문에 공개 액션을 제공.
    
    ### checkout 액션
    
    ```yaml
    jobs:
        test:
            runs-on: ubuntu-latest
            steps:
                - uses: actions/checkout@v3
                - run: npm install
                - run: npm test
    ```
    
    - workflow YAML 파일에서는 `steps` 키 하위의 `uses` 키에 사용하고자 하는 액션의 위치를 `{소유자}/{저장소명}@{참조자}` 형태로 명시한다.
    - 따라서, GitHub에서 제공하는 checkout 액션의 소유자는 `actions` 이고, 저장소 이름은 `checkout` 이며, 사용하는 버전은 `v3` 라는 것을 확인할 수 있다.

## 2) GitHub Marketplace

- [GitHub Marketplace]([https://github.com/marketplace?type=actions](https://github.com/marketplace?type=actions))
- 수많은 vendor에서 공개한 다양한 액션을 쉽게 접할 수 있음.
