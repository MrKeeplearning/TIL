# GIT2 - CLI 버전관리

<br />

## 1. 버전관리의 시작

`git init` : initialize repository

`.git` :

- `.git` 디렉토리의 부모 디렉토리에서 생성되는 변화를 버전으로 만들어서 해당 정보를 이곳에 역할에 따라 저장한다.
- 이 정보를 지우는 순간 프로젝트의 역사가 사라지기 때문에 절대 지워서는 안되는 디렉토리이다.

<br />
<br />

## 2. 버전 만들기

<br/>

### Repository

---

- Git에서 버전이 저장되는 곳
- `.git` 디렉토리

### Working tree

---

- 아직 버전으로 만들어지기 전 단계
- 파일을 수정하는 곳

### Staging Area

---

- 파일이 10개이고 딱 2개만 버전으로 만들고 싶은 상태
- 그렇다면 Working tree에서 2개의 파일만 Staging Area에 올리는 것이다!
- 버전을 만들려고 하는 파일이 있는 공간이다.

<br />

### 2.1. 실제로 파일을 생성해보기

---

<img src="https://user-images.githubusercontent.com/27791880/125474611-27c19d14-991f-40e9-a210-daf377addaaf.png">

위의 캡쳐화면처럼 master branch에 hello1.txt 라는 파일을 생성했다.

```bash
No commits yet
```

즉, 아직 버전이 만들어지지 않은 상태라는 뜻이다. commit은 버전이라고 같은 뜻이라고 생각하면 된다.

Untracked files에는 hello1.txt가 있다고 알려준다.

git은 명시적으로 어떤 파일을 버전 관리할 것이라고 알려주어야 한다. 그렇지 않다면 git은 그것이 없는 파일이라고 생각한다. 현재 git에게 hello1.txt라는 파일을 버전관리하라는 명령을 전달하지 않았기 때문에 Untracked 상태인 것이다.

hello1.txt를 버전으로 만들기 위해서는 staging area에 올려주어야 한다. 이 때 사용하는 것이 add라는 명령이다.

```bash
git add hello1.txt
```

이것은 hello1.txt라는 파일의 working tree에서의 수정사항을 버전으로 만들 것이니 staging area에 올리라는 뜻이다.

<br/>

### LF will be replaced by CRLF?

---

`$ git add hello1.txt`를 실행하고 난 뒤에 다음과 같은 에러를 확인할 수 있었다.

```bash
$ git add hello1.txt
warning: LF will be replaced by CRLF in hello1.txt.
The file will have its original line endings in your working directory
```

먼저 `LF`와 `CRLF`의 의미부터 알고 가면 좋을 것 같다.

이 둘은 모두 새줄 문자(newline)이다. 텍스트의 한 줄이 끝났음을 표현해주는 것인데 개행문자, 줄바꿈 문자(line break), EOL(end of line) 모두 같은 뜻을 가진 표현이다.

![image](https://user-images.githubusercontent.com/27791880/125553657-7ab07f61-56b3-4c21-afe1-3daf36dc0d7e.png)

#### LF(Line-Feed)

---

- 유닉스 계열에서 사용하는 줄바꿈 문자열
- Typewriter로 따지면 커서의 위치는 변하지 않은 채 종이만 그대로 위로 올리는 동작
- 프린터에서 종이가 한 줄씩 인쇄되며 나오는 것
- ASCII 코드 = 10(십진법)

#### CR(Carriage Return)

---

- 맥 OS 버전 9까지 새줄문자로 사용
- Typewriter로 따지면 Typewrite의 carriage가 끝에서 시작위치로 복귀하는 것을 뜻함
- 커서의 위치를 맨앞으로 옮긴다.
- ASCII 코드 = 13(십진법)

#### CRLF(Carriage-Return + Line-Feed)

---

- Window에서 사용
- CR과 LF를 합친 동작
- 커서를 다음 줄 맨 앞으로 옮겨주는 동작

<br/>

이처럼 사용하는 운영체제에 따라서 newline을 나타내는 코드가 다르기 때문에 텍스트를 다른 시스템으로 전송할 때 새줄 문자의 치환 작업도 필요하다.

마찬가지로, git도 작업을 처리하는 도중 어떤 방식을 택해야 할지 모르는 상태이기 때문에 사용자에게 경고 메시지를 전달해준 것이다.

<br/>

### 그렇다면 해결 방법은?

---

git에는 CRLF와 LF를 자동으로 변환해주는 기능인 `core.autocrlf` 라는 기능을 제공한다. 이 기능을 켜준다면 에러를 해결할 수 있다.

```bash
git config --global core.autocrlf true
```

위와 같은 명령을 전달하면 commit할 때와 같이 git에 코드를 추가할 때는 CRLF를 LF로 변환해주고, git의 코드를 개발자가 조회할 때, 예를 들어 clone할 때는 LF를 CRLF로 변환해준다.

현재 윈도우 환경을 사용하고 있고 이러한 작업이 항상 진행되는 것이 유익하기 때문에 위의 명령을 입력해준다.

반면, 리눅스나 맥 환경에서 사용 중이라면, 조회를 할 때 LF가 CRLF로 바뀌는 것은 바람직하지 않기 때문에 `input` 이라는 명령어를 추가해줌으로써 단방향으로만 변환이 발생할 수 있게 한다.

```bash
git config --global core.autocrlf true input
```

만약, 이러한 변환을 원하지 않고 에러 메시지를 무시한 채로 작업하고 싶은 경우 다음과 같이 명령을 전달한다.

```bash
git config --global core.safecrlf false
```

참고로 시스템 전체가 아닌 특정 프로젝트에서만 적용하고 싶다면 `--global`을 뺀 상태에서 명령을 전달하면 된다.

<br/>

⋇ 참조

[https://blog.jaeyoon.io/2018/01/git-crlf.html](https://blog.jaeyoon.io/2018/01/git-crlf.html)

[https://www.lesstif.com/gitbook/git-crlf-20776404.html](https://www.lesstif.com/gitbook/git-crlf-20776404.html)

<br/>

### 2.2. 에러를 해결하고 난 뒤

---

앞서 add 명령을 사용해서 hello1.txt 파일을 staging area에 올렸다. 이제 상태를 다시 확인해보자.

![image](https://user-images.githubusercontent.com/27791880/125554860-da1b9ab7-2c82-46ad-8f9b-f8b9e833fdcb.png)

이전에는 Untracked files라고 표시되었는데 add를 실행한 뒤에 확인해보니 Changes to be committed로 변경되었다. 즉, 버전이 될 파일들을 보여준다. 그리고 그 파일이 hello1.txt이다.

<br/>

### 2.3. 버전 생성

---

`git commit` : create version

앞서 본 상태에서 `git commit -m "Message 1"` 이라고 입력하면 현재 staging area에 있는 hello1.txt가 repository로 이동한다.

![image](https://user-images.githubusercontent.com/27791880/125554964-5e7e3270-42d3-43a4-9d1c-17285bcbb0cc.png)

Working tree가 비어 있다는 것은 버전으로 만들어지기 전 단계의 파일이 없다는 것이다. 그리고 nothing to commit은 버전으로 만들 것이 없다는 것이다.

버전으로 제대로 만들어졌는지 확인하려면 `git log` 를 활용한다.

`git log`를 활용하면 해당 파일의 고유한 id, 누가 수정했는지, 언제 수정했는지, 메시지 내용은 무엇인지를 확인할 수 있다.

<br/>

### 2.4. 한 번 더 수정해보자

---

이번에는 hello1.txt에 개행을 한 뒤 2를 추가해본다.

![image](https://user-images.githubusercontent.com/27791880/125555063-15cab878-bae4-489f-b0cf-d341fd468b5a.png)

status를 확인해보니 stage 위에 없는 수정사항이 working tree에 존재한다고 알려준다.

이제 위에서 진행했던 과정을 다시 진행한다.

![image](https://user-images.githubusercontent.com/27791880/125555117-cf208b85-65ec-44c8-8590-1ca52f000c15.png)

참고로, 처음에는 commit 메시지를 작성할 때 -m 옵션을 사용했는데 위와 같이 이 옵션을 사용하지 않으면 다음과 같이 vim 창이 뜨고 해당 창에 메시지를 작성해주면 된다.

![image](https://user-images.githubusercontent.com/27791880/125555190-e09d8f5e-7947-44dd-a513-ab132ea4573e.png)

정상적으로 commit이 된 것을 확인할 수 있다.

![image](https://user-images.githubusercontent.com/27791880/125555214-55e45ab1-1721-4e95-ab86-13ac41c4f25c.png)

<br/>
<br/>

## 3. 여러 개의 파일을 버전으로 만들기

<br/>

![image](https://user-images.githubusercontent.com/27791880/125555558-ee69e421-765e-46fe-9d5e-47f566f98962.png)

위의 캡쳐에서 보이듯이 hello1.txt에는 3을 추가로 입력했고, hello2.txt라는 파일을 새로 만들어서 내용으로 3을 입력했다. 그런데, git status를 통해 상태를 확인해보니 hello1.txt와 hello2.txt가 서로 다른 status인 것을 확인할 수 있다.

이미 앞에서 hello1.txt는 git으로 1회 이상 관리를 한 상태이기 때문에 git은 그것을 기억하고 있고, hello2.txt는 그렇지 않다. 이 상태로 협업을 하려하면 hello2.txt는 불가능한 것이다. git은 모든 파일을 자동으로 tracked, 즉 관리하지 않는다. 직접 어떤 파일을 관리할 것이라고 알려주지 않는 이상 tracking하지 않는다.

그렇다면, 이 둘을 모두 staging area에 올리고 싶다면 다음과 같이 진행한다.

![image](https://user-images.githubusercontent.com/27791880/125559826-5631bc07-faab-44f8-927e-87cd2a9d1bb1.png)

버전관리가 되고 있지 않는 파일이나, 관리되고 있는 파일이나 staging area에 올릴 때는 무조건 `git add`를 사용한다.

![image](https://user-images.githubusercontent.com/27791880/125559876-d7d9c0c1-627b-43bb-b27a-9f5b4c0fb4fc.png)

hello1.txt는 modified된 상태이고, hello2.txt는 새로운 파일이라고 상태를 알려주는 것을 확인할 수 있다. 어찌되었든 두 파일모두 staging area에 올라간 상태이고 commit이 되기를 대기 중이다.

<br/>

### 3.1. 2개의 파일을 commit

---

![image](https://user-images.githubusercontent.com/27791880/125559983-ac63c4c1-40d0-49d1-ba56-3c784598cdb0.png)

참고로 `git commit -am` 과 같이 a 옵션을 사용하면 수정되거나 삭제된 파일들을 `add` 를 통해 staging 하는 과정을 생략하고 바로 add와 commit을 하는 것도 가능하다.

매뉴얼을 참고하면 아래와 같이 설명되어 있다.

> **-a** <br/>
**--all** <br/>
Tell the command to automatically stage files that have been modified and deleted, but new files you have not told Git about are not affected.

<br/>

![image](https://user-images.githubusercontent.com/27791880/125560157-76cb6a85-aa93-4a0b-8758-4b99c8b272da.png)

2개의 파일에 대해서 commit을 마친 뒤에 log를 살펴보면 정상적으로 Message3라는 설명을 가진 채로 commit이 된 것을 확인할 수 있는데 어떤 파일이 Message3에 속하는지에 대한 정보를 확인할 수는 없다. 이 때 사용하는 것이 `git log --stat` 이다.

![image](https://user-images.githubusercontent.com/27791880/125560227-65561632-e0c8-4db8-b10d-6f233a364d66.png)

이렇게 `git log --stat` 을 사용하면 Message 3에는 hello1.txt와 hello2.txt가 연루되어있다는 것을 확인할 수 있다. 또한 이 2개의 파일 모두 1줄 씩 추가되었다는 것을 알 수 있다.

> `hello1.txt | 1 +` 에서 1+ 가 1줄 추가되었다는 것을 의미한다.

이처럼 git은 하나의 버전에 관련된 여러 개의 파일을 그룹핑할 수 있다는 장점이 있다.

<br/>
<br/>

## 4. 버전간의 차이점 비교

<br/>

![image](https://user-images.githubusercontent.com/27791880/125560331-e690ac11-1595-450b-9b78-9409d583abc1.png)

이번에는 기존 hello1.txt 파일에서 3을 지우고 그 자리에 four를 입력했다. status를 확인해보면 역시나 변경사항이 staging area에 올라가지 않았다는 메시지를 보여준다.

<br/>

### 4.1. 변경사항 확인하기

---

`git diff` 를 사용하면 변경사항을 확인할 수 있다.

![image](https://user-images.githubusercontent.com/27791880/125560415-9a735fdb-2930-4bd1-b870-a998d16a51a7.png)

Line by line으로 해석해보면 다음과 같다.

- `--- a/hello1.txt`에서 a는 이전 버전, b는 현재 버전을 의미한다.
- `@@ -1,3 +1,3 @@`
  - \- 는 이전 버전을, + 는 현재 버전을 의미한다.
  
  - \-와 +뒤의 숫자는 line number를 의미한다.

  - 위의 코드를 해석해보면 이전 버전의 1번째 라인부터 3번째 라인까지와 현재 버전의 1번째 라인부터 3번째 라인까지를 비교했다는 것을 뜻한다.

- 빨간색으로 표시된 것은 원본, 초록색으로 표시된 것은 원본이 어떻게 변경되었는지를 나타낸다.

<br/>

### 4.2. 변경 이전으로 돌아가기

---

변경한 사항이 마음에 들지 않는다면 이전으로 돌아갈 수도 있다. 이 때는 `git reset --hard` 를 사용한다.

![image](https://user-images.githubusercontent.com/27791880/125560832-7ae87f77-8e34-4249-aa91-687b8fda9ad7.png)

<br/>

### 4.3. 패치정보 확인하기

---

`git log`에서 `-p` 옵션을 사용하면 패치정보를 확인할 수 있다.

![image](https://user-images.githubusercontent.com/27791880/125560905-4aa1538b-a6dc-45fd-bc0b-07b3f9fc0aa9.png)

Message 3에서 좀 더 세부적으로 살펴보자.

![image](https://user-images.githubusercontent.com/27791880/125560983-3154e934-1e43-4d82-ab82-dba88ca4807a.png)

hello1.txt는 이전버전에서 3이라는 텍스트가 추가되었다는 것을 의미한다.

![image](https://user-images.githubusercontent.com/27791880/125561011-224b6308-5ec5-47fe-8277-8e533535aeee.png)

Message 3에서 hello2.txt에 해당하는 부분인데, hello1.txt와 다르게 이전버전에 대한 내용을 표현하는 부분이 `--- /dev/null` 이라고 표현되어 있다. 즉, 이전버전이 존재하지 않는 새로운 파일이라는 것을 뜻한다.

<br/>
<br/>

## 5. checkout과 시간여행

<br/>

![image](https://user-images.githubusercontent.com/27791880/125561090-f8fa34af-3f95-403b-b4f7-0d91f1a54aa5.png)

헤더(`HEAD`)가 `master`를 가리키고 있다는 것은 가장 최신 버전을 가리키고 있다는 것을 뜻한다. 그렇다면 Message2의 상태로 돌아가고 싶다면 어떻게 해야 할까?

먼저, 헤더가 a148a~ 로 된 버전을 가리키도록 하는 방법이 있다. 그렇다면 버전관리를 하고 있는 디렉토리 전체가 해당 시점으로 돌아가게 될 것이다.

![image](https://user-images.githubusercontent.com/27791880/125561145-773b2edf-9a22-4120-a64d-fc26973f2a9a.png)

현재 디렉토리에는 hello1.txt와 hello2.txt가 존재하고, 각 파일의 내용은 위와 같다. Message2의 상태로 복귀하고 싶다면 `git checkout` 뒤에 Message 2의 상태에 해당하는 **commit id**를 붙여준다.

![image](https://user-images.githubusercontent.com/27791880/125561193-f9989438-8d5e-4b7c-9817-ba5fa24214fc.png)

`ls -al`을 한 결과 checkout을 통해서 Message 2의 시점으로 정상적으로 복귀한 것을 알 수 있다. `cat`으로 내용을 확인해보고, `git log`를 확인해봐도 정상적으로 복귀한 것을 알 수 있다.

![image](https://user-images.githubusercontent.com/27791880/125561268-76a365d5-a9be-4577-b7e2-3d82d658cf0b.png)

master가 가리키고 있던 최신버전은 없어졌지만, 그렇다고 완전히 사라진 것은 아니다. 현재 상태에서 맨 처음 Message 3가 있던 상태로 복귀하고 싶다면 `git checkout master`라는 명령을 사용한다.

![image](https://user-images.githubusercontent.com/27791880/125561310-53132814-a23b-4da7-a3fe-170de609251e.png)

<br/>
<br/>

## 알아두면 좋은 참고사항

<br/>

### 📌 `git add`

- `git add .` 과 같이 add 뒤에 dot(.)을 찍으면 현재 디렉토리 밑에 있는 모든 파일을 add한다는 뜻이다.
- `git add [directory name]` 과 같이 디렉토리명을 뒤에 입력해주면 디렉토리 밑의 모든 파일을 입력시켜준다.

**즉, add를 꼭 파일명 하나하나 지정할 필요는 없다!**

<br/>

### 📌 기본 에디터 변경하기

- 현재 기본 에디터는 vim으로 되어 있다. 따라서 commit message를 작성하기 위해 git commit을 하면 vim editor가 나온다.
- 만약, 다른 에디터를 기본 에디터로 지정하고 싶다면 `git config --global core.editor "에디터 이름"` 과 같이 설정한다.

  - e.g., `git config --global core.editor "nano"` : nano 에디터를 default로 설정.

<br/>
<br/>

## 6. 삭제 - git reset

<br/>

특정 버전으로 현재 상태를 변경하고 싶다면 `git reset` 을 사용한다. 이것은 앞에서 배운 `checkout` 이 아닌 완전히 이전 버전으로 돌아가는 것이다.

![image](https://user-images.githubusercontent.com/27791880/125561671-f5d99872-6572-47fc-abd9-81df9e9c3ed8.png)

현재 커밋 메시지가 Message 3인 버전까지 있는데 커밋 메시지가 Message 2인 버전으로 reset해보자.

이 때 사용하는 명령은 `git reset --hard [commit id]` 이다.

![image](https://user-images.githubusercontent.com/27791880/125561699-ddf5a204-6f8a-46f9-bbcf-3529fdb221b6.png)

현재 버전이 Message 2가 된 것을 확인할 수 있다.

<br/>

### 6.1. --hard는 무슨 뜻이지?

---

매뉴얼을 확인해보자. 매뉴얼을 보면 처음 synopsis에 다음과 같이 나온 옵션이 있다.

```bash
git reset [--soft | --mixed [-N] | --hard | --merge | --keep] [-q] [<commit>]
```

hard 옵션을 사용하면 reset하려고 했던 버전을 지울 뿐만 아니라 수정하고 있던 것까지 지운다. 버전만 지우고 수정하고 있던 것은 살리고 싶다면 hard를 soft 또는 mixed로 바꾸면 된다. 참고로 mixed는 default이다.

**만약, 협업을 할 때는 이미 다른 사람과 공유된 버전에 대해서는 절대 reset하면 안된다. 공유되기 전 단계의 버전만 reset해야 한다. 그렇지 않다면 엉키는 문제가 발생한다.**

<br/>
<br/>

## 7. 되돌리기 - git revert

<br/>

revert를 사용하면 훨씬 더 세련된 방식으로 삭제 목적과 보존의 목적을 동시에 달성할 수 있다.

먼저, 현재 상태는 다음과 같다.

![image](https://user-images.githubusercontent.com/27791880/125561901-b131c336-d307-4b7e-82a4-e8b4952b371c.png)

현재 R4를 삭제하고 R3가 되고 싶은 상황이다.

Reset의 경우 R3의 버전으로 돌아가고 싶다면 다음과 같이 reset 뒤에 돌아갈 commit id를 입력했다.

```bash
git reset --hard [commit id]
```

그런데, revert는 R3로 가고 싶다면 R4를 revert해야 한다. 즉, R4의 commit id가 필요하다.

```bash
git revert [revert할 버전의 commit id]
```

revert를 하게 되면 아래와 같이 기본 에디터가 뜨고, 이 commit은 R4를 revert하는 것이라는 내용이 입력되어 있다. 여기에 추가적으로 왜 revert를 하는지 그 이유를 적어주면 좋다.

![image](https://user-images.githubusercontent.com/27791880/125561990-f72fe863-91b6-4ca4-b742-0c9af3d97382.png)

이렇게 revert를 진행하고 log를 확인하면 다음과 같다.

![image](https://user-images.githubusercontent.com/27791880/125562048-37a66102-5417-4332-aaa9-139570477af4.png)

기존의 R4는 사라지지 않은 채 revert된 R4가 새롭게 commit되어 있다. 즉, 기존의 commit은 내버려두고 R4에서의 변화를 취소한 것이다.

기존 R4의 commit id를 가지고 diff를 사용하여 변경사항을 확인해보자.

![image](https://user-images.githubusercontent.com/27791880/125562125-5c235225-4253-4020-8966-e9d695740cd3.png)

`git log -p`를 통해서도 변경사항을 확인할 수 있다.

![image](https://user-images.githubusercontent.com/27791880/125562163-0d298355-92b0-4b54-bf03-5bb16a74021d.png)

R4 commit에서 추가되었던 텍스트 R4가 revert를 함으로써 해당 텍스트가 취소되고 이전 상태(R3 commit)와 같은 상태가 된다.

<br/>

### 7.1. 주의사항 - 충돌

---

R4를 revert하지 않은 상태라고 가정해보자. 즉, R4가 최신인 상태이다. 이 때 Message 1으로 revert 방식을 사용하여 돌아가고 싶다면 Message 2를 revert하면 될까?

정답은 NO!

만약 Message 2를 revert하면 **충돌이 발생**하게 된다.

Message 4 revert → Message 3 revert → Message 2 revert 순서대로 진행해야 충돌이 발생하지 않는다. **반드시 역순으로 revert를 해주어야 한다!**

이렇게 되는 이유를 간단히 설명하자면 git의 revert는 단 하나의 commit id에 해당하는 버전만 revert를 한다. 즉, Message 2를 revert하면 Message 2만 revert하고 그 이후에 발생한 변경사항인 R3와 R4까지 revert를 해주지 않는다. 따라서 충돌을 발생시킨다.

<br/>
<br/>

## 정리

- 중요한 세 가지 영역

  - Working tree

    - 버전이 되기 전 상태의 파일이 위치한 공간

    - 파일 수정은 이 영역에서 진행된다.

  - Staging Area

    - Working tree에서 버전으로 만들고 싶은 파일을 이 공간으로 옮겨놓는다.

  - Repository

    - 버전이 저장되는 공간

    - `.git` 디렉토리

- `git init` : initialize repository

- `git status` : working tree status

- `git add` : add to staging area

- `git commit` : create version

- `git log` : show version

- `git commit -am`

  - 수정되거나 삭제된 파일을 자동으로 stage에 올리고 commit을 진행한다.

  - 즉, add를 통해서 staging 하는 과정을 생략한다. 단, 새롭게 생성되고 git에게 알려주지 않은 파일은 건드리지 않는다.

  - 완전히 새로운 파일은 무조건 add를 통해서 최초 1번은 직접 stage에 올려주는 작업을 해야 `-a` 를 사용했을 때 정상적으로 add한 뒤 commit이 가능하다.

- `git log --stat` : 각 commit message에 연루된 파일이 무엇이 있는지 확인

- `git diff` : show changes

- `git reset --hard` : 변경 이전으로 복귀하기

- `git log -p` : 패치 정보 확인하기

- `git checkout [commit id]` : commit id의 시점으로 돌아간다. 가장 최신 버전이 삭제되는 것은 아니다.

- `git checkout master` : 특정 commit id 시점으로 돌아간 상태에서 가장 최신 버전으로 다시 복귀

- `git add .` : 현재 디렉토리 밑에 있는 모든 파일을 staging한다.

- `git add [directory name]` : 해당 디렉토리 하위의 모든 파일을 staging한다.

- `git config --global core.editor "editor name"` : 원하는 특정 에디터를 default editor로 설정.

- `git reset --hard [commit id]` : commit id에 해당하는 버전을 reset한다. 완전히 해당 버전은 지우고 이전 버전으로 돌아간다.

- `git revert [revert할 버전의 commit id]`

  - revert할 버전의 이전 버전으로 돌아간다.

  - revert하는 버전은 삭제되는 것이 아니다. 단순히 해당 버전의 변화를 취소하는 것이다.

  - revert를 할 때는 반드시 역순으로 revert를 해주어야 충돌이 발생하지 않는다. 특정 버전만 revert를 하면 해당 버전만 revert되고 그 이후에 만들어진 버전은 같이 revert되는 것이 아니기 때문에 충돌이 발생한다.
