# Github에 SSH 원격 접속하기

Github 저장소에 접속하는 방식에는 2가지 방식이 존재한다. 첫 번째는 HTTPS 프로토콜을 이용하는 방법이 존재하고 또 한 가지는 SSH, 즉 Secure Shell을 통해서 접속하는 방법이 존재한다.

HTTPS를 활용하는 방법은 [여기](https://github.com/MrKeeplearning/TIL/blob/main/GIT/GIT3-CLI%EB%B0%B1%EC%97%85.md#2-http-%EB%B0%A9%EC%8B%9D%EC%9D%84-%ED%99%9C%EC%9A%A9%ED%95%9C-%EC%9B%90%EA%B2%A9%EC%A0%80%EC%9E%A5%EC%86%8C-%EC%97%B0%EA%B2%B0)에 정리를 해두었고, 이번에는 SSH를 활용하는 방법에 대해서 알아보자.

<br/>

## 1. SSH 키 생성

사용자의 컴퓨터에서 SSH 키 생성기를 사용하면 프라이빗 키와 퍼블릭 키가 생성된다. 이 키들이 어디에 저장되고 어떤 용도로 사용될까?

```bash
$ ssh-keygen
```
home directory에서 위의 명령을 실행하면 SSH 키가 저장되는 경로를 확인할 수 있다. key를 저장할 파일명을 입력하라고 하는데 일단은 건너뛰고 계속해서 enter를 입력하다보면 `id_rsa`와 `id_rsa.pub` 가 나오는데 각각 **private key**와 **public key**를 의미한다.

<br/>

## 2. 깃허브에 퍼블릭 키 전송

SSH 방식으로 Github 저장소에 접속하려면 사용자 pc에 있는 public key를 Github 서버로 전송한 다음 저장한다.

public key와 private key는 한 쌍이기 때문에 사용자의 pc가 Github 저장소에 접속하면 pc의 private key와 서버의 public key를 비교하고 만약 짝이 맞으면 연결이 성사된다.

public key를 Github에 업로드하기 위해 home directory의 `.ssh` 디렉토리로 이동하여 내용을 복사하자.

그 다음 깃허브 메인 페이지의 우측 상단 setting으로 들어간다.

![image](https://user-images.githubusercontent.com/27791880/126069709-1d6a68ee-1d52-483b-88e5-fc7144019d8a.png)

Setting에 들어와서 하단에 보면 SSH and GPG keys가 있다.

![image](https://user-images.githubusercontent.com/27791880/126069758-070674f6-588e-4e8b-8e59-680eb6617b84.png)

Public key는 여러 개를 등록해 둘 수 있기 때문에 현재 등록하려는 SSH public key를 나중에도 쉽게 알아볼 수 있도록 Title에 적절한 이름을 달아준다.

![image](https://user-images.githubusercontent.com/27791880/126069854-e62b7646-257e-4315-b6cf-3bee32fbd030.png)

Title을 입력하고 Key에 public key를 복사해서 붙여넣은 뒤 Add SSH key를 눌렀다면 정상적으로 public key가 Github 서버에 올라간 것이다.

따라서 public key를 올린 디바이스는 깃허브 저장소의 SSH 주소만 알고 있다면 로그인 정보를 입력하지 않고도 즉시 저장소에 접속할 수 있다.

현재 나의 SSH connection이 잘 이루어지는지 확인하기 위해서는 아래와 같은 명령으로 확인하면 된다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/.ssh
$ ssh -T git@github.com
```

위의 명령에 대한 응답으로 `Hi MrKeeplearning! You've successfully authenticated, but GitHub does not provide shell access.` 과 같은 메시지를 전달받았다면 정상적으로 연결이 되는 것이다.

<br/>

### Ref
* https://docs.github.com/en/github/authenticating-to-github/connecting-to-github-with-ssh

🤔 사실 위의 공식 문서를 확인해보면 현재 공부하는 책보다 훨씬 자세하고 유용한 정보가 많다. 이렇게 기록을 올리는 것이 무색해질 정도로 상당히 친절하다.

<br/>

## 3. SSH 주소로 원격 저장소 연결

SSH 주소로 연결할 원격 저장소에서 Code 다운로드 버튼을 눌러 SSH 주소를 복사한다.

![image](https://user-images.githubusercontent.com/27791880/126070135-3a32b1ad-6f34-4376-8b37-c5760349816a.png)

HTTPS 방식을 사용할 때와 마찬가지로 local repository를 생성하고 해당 위치에서 `git remote add origin [SSH 주소]` 명령을 입력하여 연결하면 된다. SSH 주소를 사용한다는 것을 제외하고는 모든 방식이 HTTPS 방식과 동일하다.

SSH 방식으로 remote repository 연결하는 방법 연습에 사용한 Repository는 [GithubPractice3](https://github.com/MrKeeplearning/GithubPractice3)이다.