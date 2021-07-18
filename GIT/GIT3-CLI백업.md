# GIT3 - CLI 백업

<br/>

## 1. 백업의 목표와 용어 정리

백업을 하기 위해서는 최소한 컴퓨터가 2대가 필요하다.

### Local Repository와 Remote Repository

실제로 작업을 해서 버전을 생성하는 컴퓨터를 **지역저장소**, 즉 **Local Repository**라고 부른다. 이 저장소에서 작업을 마치면 버전들이 존재할 것이다. 그것을 업로드해서 지역저장소와 같은 상태를 유지하는 컴퓨터에 설치된 저장소를 **원격 저장소**, 즉 **Remote Repository**라고 부른다.

![image](https://user-images.githubusercontent.com/27791880/126037641-c40a11c1-c317-455b-a20c-adceb54b94ba.png)

### PUSH

이 두 개의 저장소를 연결시킨 다음에 작업이 끝나면 그 때마다 `PUSH`를 하게 된다. 그러면 지역저장소에 담긴 소스코드와 문서와 버전이 원격저장소로 업로드된다. 작업이 끝날 때마다 `PUSH` 를 통해서 두 가지의 저장소가 같은 상태를 유지할 수 있는데 이를 통해서 백업을 달성할 수 있는 것이다.

### CLONE

백업이라는 것은 복원도 가능해야 한다. 복원이 가능하면 굉장히 편리한 효과를 얻을 수 있다. 예를 들어, Local Repository가 집에 있는 컴퓨터라고 생각해보자. 그리고 회사에도 컴퓨터가 있으며 집과 회사에서 모두 일을 한다고 가정해본다.

회사에 있는 컴퓨터와 원격 저장소를 연결시키려고 한다. 그 때 원격저장소에 있는 내용을 회사에 있는 지역 저장소에다가 복제를 하면 된다. 복제를 하는 과정을 `CLONE` 이라고 한다. `CLONE` 을 하게 되면 원격 저장소와 회사에 있는 지역 저장소가 완전히 같은 상태로 된다.

### PULL

회사에서 작업을 마쳤다면 버전을 만들어서 `PUSH`를 한다. 이로써 회사의 컴퓨터와 원격저장소는 완전히 같은 상태를 유지할 수 있게 된다. 하지만, 아직 집에 있는 컴퓨터와는 같은 상태가 아니다. 집에 있는 컴퓨터도 같은 상태를 유지하고 싶다면 원격저장소에서 버전을 다운로드 받으면 된다. 이 때, 다운로드를 받는 것을 GIT에서는 `PULL`이라고 표현한다.

이처럼 GIT의 `PUSH`와 `PULL` 기능을 가지고 백업을 함으로써 작업이동성을 극대화할 수 있다.

### Git hosting

GIT의 백업 기능을 사용하는데 있어서 원격 저장소는 중요한 역할을 수행하는데, 이것을 직접 마련하는 것은 쉬운 일이 아니기 때문에 이러한 서비스를 제공해주는 것을 **Git hosting** 서비스라고 한다.

<br/>

## 2. HTTP 방식을 활용한 원격저장소 연결

지역저장소로 `loc-git`을 하나 생성했고 해당 저장소에는 f1.txt라는 파일 하나가 존재한다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git4/loc-git (main)
$ ls -al
total 5
drwxr-xr-x 1 JaeSeok 197121 0 Jul 17 22:33 ./
drwxr-xr-x 1 JaeSeok 197121 0 Jul 17 22:32 ../
drwxr-xr-x 1 JaeSeok 197121 0 Jul 17 22:33 .git/
-rw-r--r-- 1 JaeSeok 197121 2 Jul 17 22:33 f1.txt

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git4/loc-git (main)
$ git log
commit 7da785747a1d902f0281d6969e781983d8ae638f (HEAD -> main)
Author: Jaeseok <jaeseok1115@gmail.com>
Date:   Sat Jul 17 22:33:32 2021 +0900

    add a
```

이 지역저장소의 내용을 원격 저장소로 이동시켜보자.

github에 [repository](https://github.com/MrKeeplearning/GithubPractice)를 하나 생성했다면 해당 레포지토리를 연결해보자.

git의 명령어 중에서 원격저장소와 관련된 명령은 `remote`이다. 그리고 원격저장소를 추가를 해야 하는데 그 때 `add`라는 명령을 사용한다. `add` 뒤에는 원격저장소의 주소를 입력해준다.

현재는 HTTP 방식을 사용하기 때문에 HTTPS에 해당하는 주소를 사용해야 한다.

![image](https://user-images.githubusercontent.com/27791880/126040297-2ddeb278-e277-4474-bb81-d26e44a72b0a.png)

```bash
$ git remote add https://github.com/MrKeeplearning/GithubPractice2.git
```

위의 명령은 지역 저장소를 원격저장소에 연결시키는 명령이다.

그런데, 이렇게 설정한 원격 저장소는 자주 접근하게 되는데 그 때마다 위와 같은 긴 주소를 매번 입력하는 것은 귀찮은 일이다. 또한 하나의 지역저장소에는 여러 개의 원격저장소가 연결되어 있을 수 있다.

그래서 각각의 원격저장소마다 별명을 붙여줘서 쉽게 접근할 수 있도록 원격저장소의 별명을 `add` 뒤에 입력하면 된다.

별명은 사용자 임의대로 설정해주어도 상관은 없으나 기본적인 원격저장소는 `origin` 이라는 이름을 사용하도록 약속되어 있다.

```bash
$ git remote add origin https://github.com/MrKeeplearning/GithubPractice2.git
```

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git4/loc-git (main)
$ git remote -v
origin  https://github.com/MrKeeplearning/GithubPractice2.git (fetch)
origin  https://github.com/MrKeeplearning/GithubPractice2.git (push)
```

원격저장소가 정상적으로 연결된 것을 확인할 수 있다.

<br/>

## 3. HTTP 방식 - 원격저장소에 파일 업로드

지역저장소가 원격저장소와 연결되었다면 지역저장소에 있는 파일을 업로드해보자.

현재 지역저장소는 원격저장소와 연결된 상태이기 때문에 `git push`라는 명령을 실행하면 업로드 절차가 시작된다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git4/loc-git (main)
$ git push
fatal: The current branch master has no upstream branch.
To push the current branch and set the remote as upstream, use

    git push --set-upstream origin main
```

`git push`를 할 때 최초 한 번은 위의 설명대로 `git push --set-upstream origin main`을 실행해야 한다.

위의 명령은 무슨 뜻일까? 우리의 지역저장소는 여러 개의 원격 저장소와 연결될 수 있다. 그 중에서 어떤 원격저장소와 기본적으로 연결시켜줄지 setting하는 것이다. 따라서 이후에 `git push`만 입력하면 `origin`의 `main` branch로 업로드가 된다.

이후에 깃허브 로그인창이 뜰 텐데, 원격 저장소 입장에서는 push가 들어오고 있는 것을 신뢰할 수 없기 때문에 인증을 요구하는 것이다.

최초 push를 문제 없이 완료했다면 이후 push는 훨씬 간단하다.

기존 f1.txt 파일에 b를 추가한 뒤 staging과 commit을 모두 진행해보자.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git4/loc-git (main)
$ vim f1.txt

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git4/loc-git (main)
$ git commit -am "add b"
[main 48bc6f1] add b
 1 file changed, 1 insertion(+)
```

이미 앞에서 local repository와 remote repository의 main branch를 연결했기 때문에 push는 단순히 `git push`만 입력하면 된다.

<br/>

### 사이트에서 직접 커밋, 그리고 PULL

---

사이트에서 직접 commit을 진행한 경우 local repository와 remote repository에 차이가 발생한다.

현재 사이트에서 직접 f2.txt 파일에 1,2,3 만 입력 후 커밋을 한 상태이다.

![image](https://user-images.githubusercontent.com/27791880/126048061-51edf4c8-c4e4-4304-9187-c68104e6155e.png)

![image](https://user-images.githubusercontent.com/27791880/126048104-b8ea7a29-c6d8-4dce-9e56-5c0b8164bea1.png)


remote repository에는 f2.txt 파일이 있지만 local에는 없다. 따라서 이것을 동기화해주기 위해 `pull`을 사용한다.

pull을 진행하기 위해 아래와 같이 명령을 하는 경우도 있지만,

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git4/loc-git (main)
$ git pull origin main
```

push를 할 때와 마찬가지로 기본 원격저장소가 origin이고, default branch가 main이기 때문에 `git pull`만 해도 상관없다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git4/loc-git (main)
$ git log
commit ee19650a1c2aefa398d114f56653d1c96b58eea8 (HEAD -> main, origin/main)
Author: MrKeeplearning <fossilhunter@hanmail.net>
Date:   Sun Jul 18 04:53:39 2021 +0900

    Create f2.txt

commit 48bc6f15ace92691bc94c1adfe34653ca07eab1d
Author: Jaeseok <jaeseok1115@gmail.com>
Date:   Sun Jul 18 04:37:09 2021 +0900

    add b

commit 688c249d9eec6ff98ea2bf8c8321d7169f901da6
Author: Jaeseok <jaeseok1115@gmail.com>
Date:   Sun Jul 18 03:18:48 2021 +0900

    add a

commit 919fe29c376be9e311c7ea3348e6b7c427a2e8c7
Author: MrKeeplearning <fossilhunter@hanmail.net>
Date:   Sun Jul 18 03:01:52 2021 +0900

    Initial commit
```

> 💡 여러 대의 컴퓨터에서 작업을 할 때는 항상 **원격 저장소에서 pull -> 내 컴퓨터에서 작업 -> staging & commit -> push** 과정을 반드시 지키자!

<br/>

## 4. HTTP 방식 - CLONE

현재까지는 지역저장소에 있는 것을 원격저장소로 `PUSH`함으로써 백업이라는 목적을 달성했다. 백업을 할 수 있다면 복원을 할 수 있어야 의미가 있다.

새로운 컴퓨터를 마련하고 해당 컴퓨터로 지금까지 작업했던 것을 복제(`CLONE`)해서 복원하는 방법을 살펴보자. 복원을 할 수 있게 되면 여러 대의 컴퓨터에서 같은 소스코드의 상태를 유지할 수 있고, 이러한 특징은 이동하면서 작업하는데 상당한 편리함을 제공해준다.

현재까지 작업해왔던 `/git_study/git4/loc-git` 저장소가 아닌 바로 상위 저장소인 `/git_study/git4`에 `CLONE`을 시도해보자.

`git`을 입력해보면 manual이 나오는데 저장소름 만드는 방법 2가지를 알려준다. 지금까지는 최초 저장소 생성을 위한 init을 사용했다면 이번에는 이미 있는 repository를 복제하기 위한 `clone`을 사용해본다.

![image](https://user-images.githubusercontent.com/27791880/126056396-9f01bc35-4aca-4318-a5e4-b3343f14edf1.png)

clone을 진행하기 위해서는 clone하려는 repository의 주소가 필요한데 이것은 push를 할 때와 동일한 방법으로 https 주소를 복사한다.

![image](https://user-images.githubusercontent.com/27791880/126056483-19b63917-4439-4277-9c52-2848e6afb213.png)

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git4
$ git clone https://github.com/MrKeeplearning/GithubPractice2.git
```
현재 위치에서 위와 같이 명령을 입력하면 현재 디렉토리에 GithubPractice2라는 디렉토리가 생성되면서 해당 디렉토리에 clone이 된다. 만약 다른 디렉토리에 clone을 하고 싶다면 위의 명령 뒤에 이어서 원하는 디렉토리명을 지정하면 해당 디렉토리로 저장소가 생성된다.

e.g., `$ git clone https://github.com/MrKeeplearning/GithubPractice2.git exDir`

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git4
$ git clone https://github.com/MrKeeplearning/GithubPractice2.git
Cloning into 'GithubPractice2'...
remote: Enumerating objects: 12, done.
remote: Counting objects: 100% (12/12), done.
remote: Compressing objects: 100% (8/8), done.
remote: Total 12 (delta 1), reused 5 (delta 0), pack-reused 0
Receiving objects: 100% (12/12), done.
Resolving deltas: 100% (1/1), done.

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git4
$ ls
GithubPractice2/  loc-git/
```

GithubPractice2라는 repository가 생성된 것을 확인할 수 있다.

repository에 담긴 내용을 살펴보면 이전에 `loc-git/` 디렉토리에 만들었던 내용과 동일하다. 즉, 원격저장소의 내용을 복제해서 지역저장소를 생성하는 작업이 정상적으로 진행되었다.

### GithubPractice2/

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git4/GithubPractice2 (main)
$ ls -al
total 7
drwxr-xr-x 1 JaeSeok 197121  0 Jul 18 14:15 ./
drwxr-xr-x 1 JaeSeok 197121  0 Jul 18 14:15 ../
drwxr-xr-x 1 JaeSeok 197121  0 Jul 18 14:15 .git/
-rw-r--r-- 1 JaeSeok 197121 70 Jul 18 14:15 README.md
-rw-r--r-- 1 JaeSeok 197121  6 Jul 18 14:15 f1.txt
-rw-r--r-- 1 JaeSeok 197121  7 Jul 18 14:15 f2.txt
```

### loc-git/

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git4/loc-git (main)
$ ls -al
total 11
drwxr-xr-x 1 JaeSeok 197121  0 Jul 18 05:01 ./
drwxr-xr-x 1 JaeSeok 197121  0 Jul 18 14:15 ../
drwxr-xr-x 1 JaeSeok 197121  0 Jul 18 05:01 .git/
-rw-r--r-- 1 JaeSeok 197121 70 Jul 18 03:24 README.md
-rw-r--r-- 1 JaeSeok 197121  6 Jul 18 04:36 f1.txt
-rw-r--r-- 1 JaeSeok 197121  7 Jul 18 05:01 f2.txt
```

<br/>

