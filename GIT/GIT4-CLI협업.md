# GIT4 - CLI 협업

git은 여러 개의 저장소를 서로 연결시켜 동기화시킬 수 있다. 이러한 특성을 이용하면 서로 다른 사람들이 협업의 도구로 git을 사용할 수 있다. 이 때 내부적으로는 브랜치를 사용한다.

## 1. 혼자서 작업

a라는 디렉토리를 하나 생성하고 해당 디렉토리 안에 work.txt라는 파일 하나를 생성한다. 이후 staging과 commit까지 진행한다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git5
$ git init a
Initialized empty Git repository in C:/Users/JaeSeok/git_study/git5/a/.git/

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git5/a (main)
$ vim work.txt

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git5/a (main)
$ git add work.txt
warning: LF will be replaced by CRLF in work.txt.
The file will have its original line endings in your working directory

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git5/a (main)
$ git commit -m "work 1"
[main (root-commit) fdf8066] work 1
 1 file changed, 1 insertion(+)
 create mode 100644 work.txt
```

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git5/a (main)
$ git remote add origin git@github.com:MrKeeplearning/GithubPractice-collaboration.git

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git5/a (main)
$ git push -u origin main
```

단순히 `git push`만을 하면 안된다. 지역 저장소의 main과 원격저장소의 main 브랜치를 tracking시켜주어야 한다. 즉, 둘을 연결시켜주어야 하는 것인데 그 때 사용하는 옵션이 위에서 사용한 `-u` 옵션이다. 그리고 그 뒤에 `origin main`을 붙여주면 지역저장소의 main 브랜치와 origin의 main 브랜치가 연결되면서 push까지 한 번에 진행이 된다. `-u` 옵션은 최초 1회만 하면 된다. 그 뒤부터는 `git push`만 입력해도 자동으로 origin의 main 브랜치로 push가 된다.

위의 과정이 모두 error없이 잘 진행되었다면 Github에 work.txt가 올바르게 생성된 것을 확인할 수 있다.

![image](https://user-images.githubusercontent.com/27791880/126097382-f82daadc-a96b-480c-a1ce-70a09cde5411.png)

<br/>

## 2. 협업 - 시작

다수의 계정을 가지고 협업을 실습하는 것이 아닌 한 대의 컴퓨터에서 두 개의 저장소를 만들고 각각의 저장소가 서로 다른 사용자라고 간주하고 실습을 진행해본다.

기존에 존재하던 디렉토리 a가 하나의 사용자라고 간주하고 새로운 사용자인 b(디렉토리 b)를 생성한다. b라는 인물은 a와 협업을 할 것이기 때문에 a의 환경을 그대로 복제해서 자신의 local repository로 가져와야 한다. 이 때 `clone`을 사용한다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git5
$ git clone git@github.com:MrKeeplearning/GithubPractice-collaboration.git b
Cloning into 'b'...
remote: Enumerating objects: 3, done.
remote: Counting objects: 100% (3/3), done.
remote: Total 3 (delta 0), reused 3 (delta 0), pack-reused 0
Receiving objects: 100% (3/3), done.
```

위의 명령처럼 SSH 주소 뒤에 복제를 해 놓을 디렉토리 명을 입력하면 해당 디렉토리로 복제가 이루어진다.

<br/>

## 3. 협업 - push & pull

팀원 a가 자신의 local directory에서 work.txt에 텍스트를 추가하고 `push`를 진행한 상황이다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git5/a (main)
$ cat work.txt
1
2a

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git5/a (main)
$ git commit -am "work 2a"
warning: LF will be replaced by CRLF in work.txt.
The file will have its original line endings in your working directory
[main e1ede6f] work 2a
 1 file changed, 1 insertion(+)

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git5/a (main)
$ git push
Enumerating objects: 5, done.
Counting objects: 100% (5/5), done.
Writing objects: 100% (3/3), 241 bytes | 241.00 KiB/s, done.
Total 3 (delta 0), reused 0 (delta 0), pack-reused 0
To github.com:MrKeeplearning/GithubPractice-collaboration.git
   fdf8066..e1ede6f  main -> main
```

이 상황에서 팀원 b가 작업을 시작하는데 정석대로 하면 먼저 `git pull`을 통해서 팀원 a의 작업을 다운로드 받고 자신의 버전으로 추가한 다음에 작업을 시작하는 것이다. 그런데, `git pull`을 하는 것을 잊고 바로 작업을 했다고 가정해보자.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git5/b (main)
$ cat work.txt
1
2b

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git5/b (main)
$ git commit -am "work 2b"
[main 3bb7cb0] work 2b
 1 file changed, 1 insertion(+)
```

현재 상태에서 `git log --oneline`을 통해서 a의 상태와 b의 상태를 한 번 비교해보자.

### b의 상태

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git5/b (main)
$ git log --oneline
3bb7cb0 (HEAD -> main) work 2b
fdf8066 (origin/main, origin/HEAD) work 1
```

### a의 상태

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git5/a (main)
$ git log --oneline
e1ede6f (HEAD -> main, origin/main) work 2a
fdf8066 work 1
```

a는 이미 push를 통해서 최신 버전을 올려놓았기 때문에 서로 상태가 다른 것을 확인할 수 있다. b는 아직 origin의 main 브랜치와 origin의 HEAD가 work 1을 향해 있는데, a는 origin의 main 브랜치가 work 2a를 향하고 있다. 서로 가지고 있는 버전이 다르기 떄문에 b에서 `push`를 하게 되면 error가 날 수 밖에 없는 상황이다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git5/b (main)
$ git push
To github.com:MrKeeplearning/GithubPractice-collaboration.git
 ! [rejected]        main -> main (fetch first)
error: failed to push some refs to 'github.com:MrKeeplearning/GithubPractice-collaboration.git'
hint: Updates were rejected because the remote contains work that you do
hint: not have locally. This is usually caused by another repository pushing
hint: to the same ref. You may want to first integrate the remote changes
hint: (e.g., 'git pull ...') before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.
```

예상했던 바와 같이 push가 거부되었다는 메시지가 나왔다. 밑에 hint를 보면 거부당한 사유가 나오는데 아주 친절하게 많은 것을 알려주고 있다.

> Updates were rejected because the remote contains work that you do not have locally. This is usually caused by another repository pushing to the same ref. You may want to first integrate the remote changes (e.g., 'git pull ...') before pushing again.
> 
> See the 'Note about fast-forwards' in 'git push --help' for details.

원격저장소가 현재 local 저장소가 가지고 있지 않은 작업을 가지고 있기 때문에, 즉 서로 상태가 다르기 때문에 거부된 것이다.

git pull을 통해서 다른 사람이 작업한 것을 자신의 것으로 가져오고 그것을 하나의 타임라인으로 정돈하고 다시 push를 시도하라고 나와있다.

그런데, a와 b는 모두 work.txt에서 2번째 줄에 수정을 했다. 따라서 b에서 pull을 하게 되면 conflict가 발생할 것이다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git5/b (main)
$ git pull
remote: Enumerating objects: 5, done.
remote: Counting objects: 100% (5/5), done.
remote: Total 3 (delta 0), reused 3 (delta 0), pack-reused 0
Unpacking objects: 100% (3/3), 221 bytes | 8.00 KiB/s, done.
From github.com:MrKeeplearning/GithubPractice-collaboration
   fdf8066..e1ede6f  main       -> origin/main
Auto-merging work.txt
CONFLICT (content): Merge conflict in work.txt
Automatic merge failed; fix conflicts and then commit the result.

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git5/b (main|MERGING)
$ cat work.txt
1
<<<<<<< HEAD
2b
=======
2a
>>>>>>> e1ede6f7f97093425a2c198dd469683b8146af15
```

commit id가 적힌 부분은 내가 아닌 다른 사람이 변경한 사항을 보여주는 것이다.

수정은 직접 vim 편집기를 사용해서 편집해도 되고, `git mergetool`을 통해서 기존에 설치해둔 p4merge를 사용해도 된다.

merge 작업을 마쳤다면 `git add work.txt`를 통해서 충돌을 해결했다는 것을 git에게 알려준다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git5/b (main|MERGING)
$ git add work.txt

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git5/b (main|MERGING)
$ git status
On branch main
Your branch and 'origin/main' have diverged,
and have 1 and 1 different commits each, respectively.
  (use "git pull" to merge the remote branch into yours)

All conflicts fixed but you are still merging.
  (use "git commit" to conclude merge)

Changes to be committed:
        modified:   work.txt
```

이 상태에서 메시지 옵션 없이 `git commit`만 하면 git도 현재 merge가 발생한 것을 알고 있기 때문에 자동으로 commit message를 작성해준다.

```bash
$ git log --all --graph --oneline
*   4261701 (HEAD -> main) Merge branch 'main' of github.com:MrKeeplearning/GithubPractice-collaboration
|\
| * e1ede6f (origin/main, origin/HEAD) work 2a
* | 3bb7cb0 work 2b
|/
* fdf8066 work 1
```

팀원 a가 작업한 `e1ede6f` 'work 2a'와 팀원 b가 작업한 `3bb7cb0` 'work 2b'를 동시에 조상으로 가지는 `4261701`라는 새로운 버전이 생성되고 이 새로운 버전을 `git push` 하게 되면 원격 저장소로 병합된 commit이 올라간다.

이제 팀원 a가 자신의 local repository에서 `git pull`을 한 뒤 `git log --all --graph --oneline`을 해보면 팀원 a와 팀원 b의 결과가 같은 것을 확인할 수 있다.

### 팀원 a

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git5/a (main)
$ git log --all --graph --oneline
*   4261701 (HEAD -> main, origin/main) Merge branch 'main' of github.com:MrKeeplearning/GithubPractice-collaboration
|\
| * e1ede6f work 2a
* | 3bb7cb0 work 2b
|/
* fdf8066 work 1
```

### 팀원 b

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git5/b (main)
$ git log --all --graph --oneline
*   4261701 (HEAD -> main, origin/main, origin/HEAD) Merge branch 'main' of github.com:MrKeeplearning/GithubPractice-collaboration
|\
| * e1ede6f work 2a
* | 3bb7cb0 work 2b
|/
* fdf8066 work 1
```

<br/>

## 4. 협업 - 원격 브랜치와 fetch

### 4.1. fetch에 대한 간단한 소개

---

일반적으로 원격 저장소를 중심으로 협업을 할 때는 작업 전에 다음과 같은 과정을 거친다.

> `git pull` → `git commit` → `git push`

그런데, pull 대신에 다른 방법을 사용할 수도 있다.

> `git fetch` → `git merge FETCH_HEAD` → `git commit` → `git push`

이러한 방법을 사용해도 `git pull`로 시작하는 과정과 동일한 결과를 얻을 수 있다.

<br/>

### 4.2. 원격 브랜치

---

![image](https://user-images.githubusercontent.com/27791880/126121226-e412b61b-4db4-42a3-9b95-eb627790c576.png)


위의 코드에서 `HEAD -> main` 부분의 main은 지역 저장소의 main branch이다. 그리고 붉은 색의 `origin/main`은 원격저장소 중 origin이라는 이름을 가진 저장소의 main 브랜치를 가리킨다. 그리고 이것은 마지막으로 마스터 브랜치의 어떤 버전을 가져왔는지를 의미한다.

디렉토리 a에서 한 번 work.txt를 수정해보자.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git5/a (main)
$ cat work.txt
1
2ab
3a

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git5/a (main)
$ git commit -am "work 3a"
[main 8258af5] work 3a
 1 file changed, 1 insertion(+)
```

현 상태에서 git log를 살펴보자.

![image](https://user-images.githubusercontent.com/27791880/126121900-71e11015-9ebb-4259-9249-0e71d9c39f02.png)

현재 브랜치는 main이다. 그리고 main branch는 work 3a를 가리킨다. 그러나 origin/main은 여전히 이전에 있었던 `4261701`를 가리키고 있다.

즉, local repository의 main 브랜치가 origin/main 브랜치보다 하나의 버전이 앞서 있는 것이다.

역시나 `git status` 명령을 전달해도 'origin/main'보다 커밋 하나가 앞서 있기 때문에 push를 해야 한다고 알려준다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git5/a (main)
$ git status
On branch main
Your branch is ahead of 'origin/main' by 1 commit.
  (use "git push" to publish your local commits)

nothing to commit, working tree clean
```

![image](https://user-images.githubusercontent.com/27791880/126122627-3e88d7e4-50cd-4006-b56f-35e921be8330.png)

push를 하고 나면 위와 같이 origin/main이 main과 같은 브랜치를 가리키고 있는 것을 확인할 수 있다.

<br/>

### 4.3. fetch

---

팀원 b(디렉토리 b)가 작업을 할 때는 `git pull`을 통해서 팀원 a가 만든 변경사항을 동기화해야 한다. 그런데, `git fetch`를 통해서도 변경사항을 적용할 수 있다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git5/b (main)
$ git fetch
remote: Enumerating objects: 5, done.
remote: Counting objects: 100% (5/5), done.
remote: Total 3 (delta 0), reused 3 (delta 0), pack-reused 0
Unpacking objects: 100% (3/3), 224 bytes | 8.00 KiB/s, done.
From github.com:MrKeeplearning/GithubPractice-collaboration
   4261701..8258af5  main       -> origin/main
```

a가 작업한 내용은 work.txt를 수정한 것이고 마지막에 추가한 내용이 3a였다. `fetch`가 `pull`과 같은 역할을 한다고 해서 work.txt를 확인해보면 왠지 3a가 적용되어 있을 것 같지만 실제로는 그렇지 않다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git5/b (main)
$ cat work.txt
1
2ab
```

![image](https://user-images.githubusercontent.com/27791880/126123940-18eb3983-f8ce-472f-8d89-ca634c324463.png)

현재 지역 저장소는 아직 `4261701` 버전의 브랜치를 가리키고 있다. 하지만 그 위에를 보면 `origin/main`이 main 브랜치보다 한 칸 더 앞서 있다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git5/b (main)
$ git status
On branch main
Your branch is behind 'origin/main' by 1 commit, and can be fast-forwarded.
  (use "git pull" to update your local branch)

nothing to commit, working tree clean
```

`git status`를 확인해보면 현재 main 브랜치가 origin/main 보다 한 커밋 뒤쳐져 있으니 `git pull`을 하라고 알려준다.

상태를 업데이트 해주기 위해 git의 설명대로 `git pull`을 해도 되고, origin/main을 지역저장소의 main으로 merge해도 된다.

![image](https://user-images.githubusercontent.com/27791880/126124808-8acc953b-f84c-47dd-804c-204aaa350093.png)

merge한 결과를 보면 `git pull`을 했을 때와 동일한 결과가 나오는 것을 확인할 수 있다.

`git pull`을 한다는 것은 `git fetch`를 통해서 **원격 저장소만 업데이트**하고 `git merge origin/main`을 한 것과 같은 결과를 가져온다. 그런데, 항상 어떤 브랜치를 병합할 것인지 신경쓰는 것은 귀찮은 일이다. 그래서 git에서는 fetch를 할 때마다 .git 디렉토리 안에 `.git/FETCH_HEAD`라는 파일을 하나 만들어준다.

![image](https://user-images.githubusercontent.com/27791880/126125530-7111ad86-1e8c-4cb9-b1d1-737f0933a412.png)

해당 파일에는 위와 같이 원격저장소에 가장 최근에 merge한 내용이 담겨 있다.

따라서 `git fetch; git merge FETCH_HEAD`라는 명령을 입력하면 git은 `.git/FETCH_HEAD` 파일을 참고해서 가장 최근에 fetch했던 내용을 merge시켜준다. `git merge origin/main`과 기능 상으로는 같은데 자동으로 merge할 데이터를 찾아 merge해준다는 장점이 있다.