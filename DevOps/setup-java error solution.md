# `setup-java` 액션에서 에러가 발생했을 때 해결 방법

### build error가 발생한 지점

![image](https://user-images.githubusercontent.com/27791880/188920681-7c3ebc84-85d9-4843-a40f-0848e29671d8.png)

### 에러가 발생한 workflow YAML 파일

```yaml
# logging-deploy.yml

name: logging-system

on:
  workflow_dispatch:
  
jobs:
  build:
    runs-on: ubuntu-latest
    
    steps:
      - name: Checkout
        uses: actions/checkout@v3
        
      - name: Set up JDK 11
        uses: actions/setup-java@v3
        with:
          java-version: '11'
      
      - name: Grant execute permission for gradlew
        run: chmod +x gradlew
        shell: bash
      
      - name: Build with Gradle
        run: ./gradlew build
        shell: bash
```

## 에러 발생 배경

workflow YAML 파일을 작성 후 Run workflow를 했을 때 `setup-java` action 부분에서 error가 발생했다.

처음 겪었던 빌드 에러는 자바의 버전 문제에서 기인한 에러였는데, 아무 생각없이 `checkout`과 `setup-java` 모두 v3 버전으로 전환했다. GitHub Actions 자체에 대한 이해도가 부족한 상태에서 무작정 적용했다가 겪게된 문제이다.

역시나 해결책은 [공식문서](https://github.com/actions/setup-java#usage)에 있었다. 구글 검색도 좋지만 검색하기 전에 공식문서부터 읽는 습관을 들이자...

## 해결 방법

[setup-java 레포지토리의 README.md 파일](https://github.com/actions/setup-java#readme)을 읽어보면 사용방법에 다음과 같이 명시되어 있다.

| Inputs `java-version` and `distribution` are mandatory. See Supported distributions section for a list of available options.

앞서 작성한 yml 파일을 살펴보면 자바 버전만 작성하고 distribution 파트는 작성하지 않은 것을 확인할 수 있다. 참고로 `distribution`은 v2 버전부터 꼭 작성해야 하는 항목으로 추가되었다.

### 💡 `distribution`에는 어떤 옵션을 선택해야 할까?

선택지는 다양하게 존재한다. 아마존 EC2에 배포할 것을 고려했을 때 AWS 환경에 가장 최적화된 corretto를 선택하는 것이 나을 것 같아 corretto를 선택하기로 했다. corretto를 선택했을 때 YAML 파일은 아래와 같아진다.

```yaml
- name: Set up java
  uses: actions/setup-java@v3
  with:
    distribution: 'corretto'
    java-version: '11'
```

참고로 `setup-java@v1`에서는 distribution 기본값으로 Zulu OpenJDK가 선택되었다.

<br/>
<br/>

### Ref.

* [setup-java Repository](https://github.com/actions/setup-java)
* [Get Ready for the New Decade of Java Software Development: Java Versions, Distributions, and Platforms](https://www.pentalog.com/blog/it-development-technology/java-versions-distributions-platforms/)
* [Java 언어의 ownership과 distribution](https://velog.io/@yoonkangho/Java-%EC%8B%9C%EC%9E%91%ED%95%98%EA%B8%B0)