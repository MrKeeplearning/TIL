# GIT3 Branch and Conflict

## 1. 실습준비

하나의 매뉴얼을 작성 중이라고 가정해보자.

먼저 하나의 저장소를 만들고 work.txt라는 파일을 만든다. work.txt에는 'content 1' 이라는 내용만 우선 담고 staging 후 commit을 진행한다.

![image](https://user-images.githubusercontent.com/27791880/125640851-412c3676-82c6-4980-88c4-6e00780a5ebe.png)

이후 동일하게 content2와 content3를 파일에 입력하며 총 3번의 commit을 진행해본다.

![image](https://user-images.githubusercontent.com/27791880/125641642-3fbdb352-80a8-4ce8-90c0-4f972c096a0f.png)

현재 우리는 'manual'을 작성 중인데, 우리에게 고객사가 생겨나서 고객사마다 서로 다른 내용의 사용 설명서를 제공해주어야 하는 상황이다.

GIT의 branch의 도움을 받지 않는다면 매뉴얼이라는 디렉토리를 복사해서 고객사마다 복사본을 만들어야 하는 상황이 발생한다. 앞으로 이러한 문제를 branch가 어떻게 해결하는지 알아보자.

<br/>

## 2. 브랜치의 기본사용법 - 1

고객사마다 매뉴얼에 다른 내용을 추가해야 하는 상황이라면 브랜치를 통해서 문제를 해결할 수 있다.

고객사에는 naver, kakao, 우아한형제들이 있다고 가정해보자.

`git log`를 통해서 상태를 확인하는데 이번에는 새로운 옵션을 사용해본다.

* `git log --all` : 생성한 모든 브랜치를 확인할 수 있다.
* `git log --graph` : 시각적으로 표현해준다.
* `git log --oneline` : 버전이 한 줄로 보여진다.

`git branch`라고 명령을 입력하면 다음과 같은 결과가 나온다.

![image](https://user-images.githubusercontent.com/27791880/125644026-0f99ce33-3842-4143-992f-1bf24d67cda9.png)

`git branch` 라는 명령어는 브랜치의 목록을 보여주는 명령어인데, 결과로 master만 나타난다는 것은 저장소를 만드는 순간 master branch 위에서 작업을 한다는 것을 의미한다.

만약 고객사인 naver의 브랜치를 만들고 싶다면 `git branch naver` 와 같이 branch 뒤에 만들고 싶은 브랜치의 이름을 넣어준다.

![image](https://user-images.githubusercontent.com/27791880/125645045-2db5d81b-d192-4d40-8b0c-9d46b301bfb1.png)

새롭게 naver라는 브랜치가 생성된 것을 확인할 수 있고 별표(`*`)가 master 위에 있다는 것은 아직 master branch 위에 있다는 것을 의미한다.

![image](https://user-images.githubusercontent.com/27791880/125717360-4d78a20d-e049-427e-895c-1be6127b0dad.png)

마찬가지로, `git log`로 보여지는 결과에서도 HEAD는 master를 가리키고 있다. 즉, 현재 master에 속해있는 것을 의미한다.

이번에는 kakao와 우아한형제들 branch를 생성해본다.

![image](https://user-images.githubusercontent.com/27791880/125717781-96c01afb-9311-4fcf-bfa3-6abec8c03fa6.png)

현재 생성한 3개의 branch들은 master에 머무른 상태에서 만들었기 때문에 work3라는 공통점을 가지고 있다.

이번에는 work.txt에 'master content 4'라는 내용을 입력한 뒤, master work 4로 commit message를 작성하여 commit을 한다.

![image](https://user-images.githubusercontent.com/27791880/125718292-1d9d096a-cc48-4aea-8daa-401be814f94d.png)

현재 작업한 것은 master work 4이고 woohan, naver, kakao 브랜치는 여전히 work3에 남겨진 상태이다.

이 때, `git checkout kakao`를 하게 되면 어떻게 될까? 이 명령어는 kakao 브랜치로 가기 위함이고, HEAD는 kakao를 가리킬 것을 예상해 볼 수 있다. 또한 work3 버전으로 돌아갈 것이다. work3 버전에서는 work.txt 파일에 'master content 4' 라는 텍스트가 없었다.

![image](https://user-images.githubusercontent.com/27791880/125719059-b2e28e6c-bb7b-4b87-98aa-e9c2a90ad916.png)

work.txt에서 'master content 4'가 사라진 것을 확인할 수 있다.

![image](https://user-images.githubusercontent.com/27791880/125719165-d048433c-0455-4c09-88e4-898b9cd84cf8.png)

현재 브랜치는 kakao를 가리키고 있다. 

다시 `git checkout master`를 하게 되면 원래 상태로 돌아갈 것이다.

<br/>

## 3. 브랜치의 기본사용법 - 2

현재 상태는 kakao branch에 있는 상태이다.

![image](https://user-images.githubusercontent.com/27791880/125730580-f180ab42-19b6-4865-b3c5-2c41a54677af.png)

이 상태에서 content 3 밑에 'kakao work 4'라는 내용을 추가해보자.

그리고 추가로 kakao.txt라는 파일을 생성한다.

![image](https://user-images.githubusercontent.com/27791880/125730824-31b8af72-c600-49d8-a60b-c9d0aef1dd93.png)

`git add .` 를 통해서 working tree에 있는 모든 파일을 add하고 "kakao work 4"라는 메시지로 commit을 진행한다. 이제 `git log --all --graph --oneline`을 실행하면 그래프로 서로 간의 관계를 확인할 수 있다.

![image](https://user-images.githubusercontent.com/27791880/125731113-40631f1c-4a12-457d-b467-742e543b1617.png)

HEAD가 가리키고 있는 파트를 보면 현재 kakao branch에 있는 것을 확인할 수 있다. 그리고 'apple work 4' 버전의 부모는 'work 3'이다. 그리고 'master work 4'의 부모 또한 'work 3'이다. 이러한 서로 간의 관계를 그래프로 표현해주는 역할을 하는 것이 `git log`의 `--graph` 옵션이다.

이번에는 naver와 woowahan의 branch에도 작업을 추가해보자.

먼저 naver로 branch를 전환한 상태에서 work.txt를 수정하고 새로운 파일을 하나 생성한다.

![image](https://user-images.githubusercontent.com/27791880/125731998-840bc763-39ce-4fe4-9609-35692798fbfe.png)

work.txt와 새롭게 생성한 naver.txt에는 다음과 같은 내용이 담겨 있다.

![image](https://user-images.githubusercontent.com/27791880/125732088-d0fba8d2-5bda-4c8a-bf64-3912707acd90.png)

각 파일들을 commit한 뒤에 살펴본 git의 상태는 다음과 같이 변했다.

![image](https://user-images.githubusercontent.com/27791880/125732225-26494da2-55bf-4ba2-aba1-a1356da13774.png)

현재 naver가 checkout된 상태이고, naver의 부모는 work 3인 것을 확인할 수 있다. work 3에는 여전히 woowahan이 남아 있다.

kakao work 4와 master work 4도 여전히 부모는 work 3이다.

마지막으로 남은 woowahan으로 HEAD의 위치를 변경해본다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual (naver)
$ git checkout woowahan
Switched to branch 'woowahan'
```

woowahan으로 브랜치를 변경한 상태에서 위에서 해왔던 것과 동일하게 work.txt를 수정하고, woowahan.txt 파일을 새롭게 생성해본다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual (woowahan)
$ cat work.txt
content 1
content 2
content 3
woowahan content 4

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual (woowahan)
$ cat woowahan.txt
woowahan content 4
```

최종적으로 2개의 파일을 모두 commit을 하면 다음과 같은 그래프를 확인할 수 있다.
```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual (woowahan)
$ git log --all --graph --oneline
* 4afdf5c (HEAD -> woowahan) woowahan work 4
| * 9dece72 (naver) naver work 4
|/
| * 7b2b7ff (kakao) kakao work 4
|/
| * 59c9dfd (master) master work 4
|/
* 5f47adb work 3
* 3f9c812 work 2
* 338694d work 1
```

이처럼 브랜치라는 기능을 통해서 branch에서 작업했던 특정 내용으로 이동할 수 있다. 더 이상 복제본마다 저장소를 copy할 필요 없이 하나의 저장소 안에서 공통의 작업을 공유하면서도 각자 자신의 작업을 가지고 있는 별도의 평행우주를 만들 수 있다!

<br/>

## 4. 병합1 : 서로 다른 파일 병합

새로운 디렉토리인 `manual-merge/`를 생성하고 `work.txt`라는 파일을 생성한다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3
$ ls -al
total 4
drwxr-xr-x 1 JaeSeok 197121 0 Jul 15 15:14 ./
drwxr-xr-x 1 JaeSeok 197121 0 Jul 15 13:35 ../
drwxr-xr-x 1 JaeSeok 197121 0 Jul 15 14:18 manual/
drwxr-xr-x 1 JaeSeok 197121 0 Jul 15 15:14 manual-merge/

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3
$ cd manual-merge

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge (master)
$ vim work.txt

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge (master)
$ cat work.txt
1
```

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge (master)
$ git log
commit 136a210359c5a84f00757471c2f6c513626e981c (HEAD -> master)
Author: Jaeseok <jaeseok1115@gmail.com>
Date:   Thu Jul 15 15:20:38 2021 +0900

    work 1
```

`work.txt` 파일을 commit한 뒤에 `o2`라는 브랜치를 만들고 해당 브랜치에서 `master.txt` 파일을 생성한다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge (master)
$ git branch o2

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge (master)
$ vim master.txt

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge (master)
$ git add master.txt
warning: LF will be replaced by CRLF in master.txt.
The file will have its original line endings in your working directory

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge (master)
$ git commit -am "work 2"
[master 033b0f2] work 2
 1 file changed, 1 insertion(+)
 create mode 100644 master.txt
```

참고로, 만약 이미 commit한 것에 대해서 commit message를 변경하고 싶다면 `git commit --amend`라는 명령을 사용하면 된다.

이 경우 기본 편집기(vim)가 뜨면서 commit message를 수정할 수 있게 해준다.

![image](https://user-images.githubusercontent.com/27791880/125740522-cc9ef647-ccf5-4979-99aa-00ebd6866965.png)

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge (master)
$ git log --all --oneline
df0928b (HEAD -> master) master work 2
136a210 (o2) work 1
```

commit message가 work 2에서 master work 2로 정상적으로 변경되었다.

### master 브랜치에 o2 브랜치를 병합하기

---

o2 브랜치에는 o2.txt 파일을 생성했고 해당 파일 안에는 다음과 같은 내요을 담았다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge (o2)
$ cat o2.txt
o2 2
```

master에 o2 branch를 병합을 하려면 현재 위치가 master 브랜치인 상태여야 한다. 따라서 만약 현재 상태가 o2라면 `git checkout master`를 통해서 branch를 바꿔주어야 한다.

먼저 현재 `git log`를 살펴보면 다음과 같은 관계를 가지고 있다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge (master)
$ git log --all --graph --oneline
* 1d290a5 (o2) o2 work 2
| * df0928b (HEAD -> master) master work 2
|/
* 136a210 work 1
```

나의 현재 브랜치(`master`)의 `df0928b` 버전과 `o2`브랜치의 `1d290a5` 버전을 병합한 새로운 버전을 만들고 싶은데, 이 때는 다음과 같은 명령을 사용한다.

```bash
$ git merge o2
```

위의 명령을 실행하면 아래와 같이 vim 편집기가 뜰 텐데 해당 편집기에서 왜 현재 새로운 버전을 생성하려는지에 대한 이유를 작성해준다.

![image](https://user-images.githubusercontent.com/27791880/125742366-eb0d51c2-1226-4ed8-b596-76b1e90ea7e0.png)

merge를 마치고 log를 살펴보면 다음과 같은 구조로 변경된 것을 확인할 수 있다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge (master)
$ git merge o2
Merge made by the 'recursive' strategy.
 o2.txt | 1 +
 1 file changed, 1 insertion(+)
 create mode 100644 o2.txt

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge (master)
$ git log --all --graph --oneline
*   7555f6e (HEAD -> master) Merge branch 'o2'
|\
| * 1d290a5 (o2) o2 work 2
* | df0928b master work 2
|/
* 136a210 work 1
```

master는 새로운 브랜치를 가리키고 있고, 새로운 commit은 o2의 최신 커밋과 마스터의 이전 커밋을 공통의 조상으로 하는 그래프를 보여주고 있다.

<br/>

## 5. 병합2 : 같은 파일, 다른 부분 병합

서로 파일명이 다른 것들끼리 병합할 때는 자동으로 병합되었다. 그렇다면 파일명이 같고 해당 파일 안에서 다른 부분을 수정했을 때 병합을 하려면 어떻게 될까?

파일 안에서 서로 다른 부분을 수정할 것이기 때문에 새로운 텍스트 파일(`work.txt`)에서는 명확한 구분을 위해 다음과 같이 작성하였다.

![image](https://user-images.githubusercontent.com/27791880/125754885-4f5975f2-ce33-46f5-85de-69ecc75ca2a0.png)

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge2 (master)
$ vim work.txt

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge2 (master)
$ git add work.txt
warning: LF will be replaced by CRLF in work.txt.
The file will have its original line endings in your working directory

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge2 (master)
$ git commit -m "1"
[master (root-commit) 8cc1e2f] 1
 1 file changed, 6 insertions(+)
 create mode 100644 work.txt

 JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge2 (master)
$ git log --all --oneline
8cc1e2f (HEAD -> master) 1
```

o2라는 브랜치를 하나 생성하고 master 브랜치에서는 work.txt의 윗부분을 수정한다.

![image](https://user-images.githubusercontent.com/27791880/125755867-668ffb09-c02c-4d8a-a03a-dbb20e3f054e.png)

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge2 (master)
$ git branch o2

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge2 (master)
$ vim work.txt

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge2 (master)
$ git commit -am "master work 2"
warning: LF will be replaced by CRLF in work.txt.
The file will have its original line endings in your working directory
[master faf4852] master work 2
 1 file changed, 1 insertion(+), 1 deletion(-)

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge2 (master)
$ git log --all --oneline
faf4852 (HEAD -> master) master work 2
8cc1e2f (o2) 1
```

이번에는 o2 브랜치로 옮긴 뒤 `work.txt`의 하단에 변화를 주었다.

![image](https://user-images.githubusercontent.com/27791880/125756555-d6d9cdd0-d742-4f1a-acc5-fcf895cf7afb.png)

master 브랜치에서도 `work.txt`파일을 수정했고, o2 브랜치에서도 `work.txt`파일을 수정했다. 따라서 `log`를 확인해보았을 때 다음과 같은 그래프가 그려진다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge2 (o2)
$ git log --all --graph --oneline
* 0d74ffd (HEAD -> o2) o2 work 2
| * faf4852 (master) master work 2
|/
* 8cc1e2f 1
```

이제 o2의 내용을 master로 병합해보자. 그렇다면 제일 먼저, 현재 위치가 master인지 확인해야 한다. 현재 위치는 o2이기 때문에 master 브랜치로 변경해주어야 한다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge2 (o2)
$ git checkout master
Switched to branch 'master'

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge2 (master)
$ git merge o2
Auto-merging work.txt
Merge made by the 'recursive' strategy.
 work.txt | 2 +-
 1 file changed, 1 insertion(+), 1 deletion(-)

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge2 (master)
$ git log --all --graph --oneline
*   6cae66d (HEAD -> master) Merge branch 'o2'
|\
| * 0d74ffd (o2) o2 work 2
* | faf4852 master work 2
|/
* 8cc1e2f 1

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge2 (master)
$ cat work.txt
# title
master content


# title
o2 content
```

이처럼 git은 초기 두 개의 파일이 서로 같은 이름과 같은 내용을 가지고 있더라도 둘의 변경지점이 다르다면 merge를 할 때 모든 변경사항을 적용하여 하나의 파일로 만들어주는 기능을 가지고 있다.

<br/>

## 6. 병합3 : 같은 파일, 같은 부분 병합
두 개의 브랜치에서 같은 이름의 파일을 수정했는데, 같은 부분을 수정했다면 git은 그것을 자동으로 병합하지 못한다. 이것을 충돌(conflict)라고 한다.

이러한 경우 git은 우리에게 모든 것을 알아서 병합하려는데, 이 경우는 양쪽 모두 동시에 수정해서 git 스스로 수정이 불가능하니, 사용자가 직접 해당 부분만 병합해달라는 말을 전달한 것이다.

이번에도 하나의 디렉토리 밑에 work.txt 라는 파일을 생성한다.
```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge3 (master)
$ vim work.txt

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge3 (master)
$ cat work.txt
#Title
content

#Title
content
```

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge3 (master)
$ git add work.txt
warning: LF will be replaced by CRLF in work.txt.
The file will have its original line endings in your working directory

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge3 (master)
$ git commit -m "work 1"
[master (root-commit) 50bdfa2] work 1
 1 file changed, 5 insertions(+)
 create mode 100644 work.txt

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge3 (master)
$ git branch o2

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge3 (master)
$ git log --all --oneline
50bdfa2 (HEAD -> master, o2) work 1
```

현재는 o2와 master 모두 work1을 가리키고 있는 상태이다.

<br/>

mastser branch에서 `work.txt`의 파일 중간에 master를 입력해본다.

![image](https://user-images.githubusercontent.com/27791880/125788285-27495a45-4b88-44df-9c32-4ca02f3d9ccd.png)

o2 branch로 이동해서 `work.txt`파일에 내용을 삽입하는데, 동일한 위치에 삽입하지만 내용은 master branch에서 했던 것과는 다른 내용을 넣는다.

![image](https://user-images.githubusercontent.com/27791880/125788844-8c7dde3f-ed8c-42cd-ab06-16ab3b995e8c.png)

각 브랜치에서 생성한 파일들은 모두 staging과 commit을 마쳤다. 현재 branch의 구조를 살펴보면 다음과 같이 구성되어 있다.

```branch
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge3 (o2)
$ git log --all --graph --oneline
* 1a1fd3d (HEAD -> o2) o2 work 2
| * 29c918c (master) master work 2
|/
* 50bdfa2 work 1
```

이제 해볼 것은 master branch의 내용에 o2 branch의 내용을 병합하여 새로운 버전을 생성하는 것이다.

어떻게 해야 할까?

1. master branch로 이동
2. master branch에서 merge 명령어로 o2를 당겨온다.

위의 과정을 따라서 진행하면 다음과 같이 **conflict**가 발생한다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge3 (o2)
$ git checkout master
Switched to branch 'master'

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge3 (master)
$ git merge o2
Auto-merging work.txt
CONFLICT (content): Merge conflict in work.txt
Automatic merge failed; fix conflicts and then commit the result.
```

내용을 확인해보면 `work.txt`에서 충돌이 발생했고, merge에 실패했으니 충돌을 해결 후 commit하라고 되어 있다.

![image](https://user-images.githubusercontent.com/27791880/125790623-a8d69daf-b83f-4ed6-9d88-cde7c2588d4e.png)

`git status`를 통해서 상태를 확인해보면 빨간색으로 `both modified: work.txt` 라고 나오는데, 이 역시 양쪽 다 수정되었고 충돌이 발생했다는 것을 의미한다.

`work.txt`파일을 살펴보면 내용이 이상하게 변경된 것을 확인할 수 있다.

![image](https://user-images.githubusercontent.com/27791880/125791312-2be73aac-a623-4742-9c9f-299cf6657f21.png)

master와 o2 사이에 있는 구분자 `=======` 위쪽을 확인해보자.

```bash
<<<<<<< HEAD
master
```

이것은 현재 branch(`HEAD`)의 내용은 master라는 것을 뜻한다.

```bash
o2
>>>>>>> o2
```

구분자를 중심으로 그 하단 두 줄은 o2 브랜치의 내용은 o2인데 이것은 git이 자동으로 합치는 것을 할 수 없으니 사용자가 이것만큼은 직접 합쳐달라고 말하는 것이다.

따라서 사용자는 master를 살리고 싶으면 master를 제외한 나머지를 삭제하면 되고, o2를 살리고 싶다면 o2를 제외한 나머지를 삭제하면 된다. 즉, 사용자의 임의대로 merge하는데 실패한 `work.txt` 파일의 내용을 수정하면 된다.

![image](https://user-images.githubusercontent.com/27791880/125792606-d8cde760-308f-4fe0-b8ad-cba76f3e9529.png)

위와 같이 수정을 완료했다면 그 다음으로 `git add work.txt`라는 명령을 전달한다. 이것은 git에게 `work.txt`에서 발생한 오류를 해결했다는 것을 알려주는 것이다.

`status`를 확인해봐도 문제가 해결되었다는 것을 알 수 있다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge3 (master|MERGING)
$ git status
On branch master
All conflicts fixed but you are still merging.
  (use "git commit" to conclude merge)

Changes to be committed:
        modified:   work.txt


JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge3 (master|MERGING)
$ git commit
[master 321ce2e] Merge branch 'o2'
```

단순히, `git commit`만 입력해보면 다음과 같이 vim 에디터가 open된다.

![image](https://user-images.githubusercontent.com/27791880/125793160-5f6e4220-8778-4737-ad75-e3e49e21bb34.png)

위의 내용을 살펴보면 충돌이 발생했다는 것을 알려주고 수정한 것이 맞는지 확인해달라는 내용을 보여준다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/manual-merge3 (master)
$ git log --all --graph --oneline
*   321ce2e (HEAD -> master) Merge branch 'o2'
|\
| * 1a1fd3d (o2) o2 work 2
* | 29c918c master work 2
|/
* 50bdfa2 work 1
```

위의 `git log` 결과를 보면 알 수 있듯이 새로운 버전이 생성되었다.

새롭게 생성된 `321ce2e`버전은 o2의 최신 브랜치와 master의 이전 브랜치인 `29c918c`버전을 공통의 부모로 하고 있다.

<br/>

## 7. 깃은 어떻게 충돌을 파악하는가?

### 2 way merge

---

![image](https://user-images.githubusercontent.com/27791880/125911497-ba4c65a5-b8aa-48cf-a5b9-390dc596dffd.png)

2-way merge 방식은 base에서 나온 2개의 branch만을 비교해서 merge하는 방식이다. 이 경우 첫 번째 데이터인 A는 here branch와 there branch에서 동일하기 때문에 문제되지 않지만, 2번째부터 4번째 데이터는 모두 서로 다르기 때문에 merge하는데 문제가 발생한다. git의 입장에서는 기준이 없기 때문에 둘 중 어느 쪽을 선택해서 merge를 해야할지 알 수가 없다. 따라서 git은 conflict를 발생시킨다.

### 3 way merge

---

![image](https://user-images.githubusercontent.com/27791880/125911576-bcbb9888-010c-4536-a720-f7fdced20ba6.png)

3-way merge 방식은 base까지 같이 비교를 하여 merge를 한다.

첫 번째 데이터는 here와 there모두 동일하기 때문에 merge하는데 문제가 없다.

두 번째 데이터는 here branch에서만 변경되었다. 따라서 변경된 here branch의 내용을 merge한 결과에 적용시킨다. 마찬가지로 세 번째 데이터는 there branch에서만 변경되었기 때문에 해당 변경사항을 merge branch에 적용시킨다.

마지막으로 4번째 데이터는 here와 there 두 branch 모두에서 변경이 발생했다. GIT은 어느 쪽을 기준으로 merge를 진행해야 할지 결정할 수 없으므로 사용자에게 해당 작업 처리를 요청하게 된다.

<br/>

## 8. 외부도구를 이용한 병합

현재 구축되어 있는 branch들은 다음과 같은 형태를 가지고 있다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/ch10 (master)
$ git log --all --graph --oneline
* 5623fee (there) ABTT
| * cf8e9e9 (here) AHCH
|/
* e4a1ac4 (HEAD -> master) ABCD

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/ch10 (master)
$ cat work.txt
A

B

C

D
```

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/ch10 (here)
$ cat work.txt
A

H

C

H
```

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/ch10 (there)
$ cat work.txt
A

B

T

T
```

이 상태에서 here branch로 이동한 다음에 there branch를 merge하게 되면 conflict가 발생하게 된다. 정확하게 어디에서 conflict가 발생했는지 확인하기 위해서 here branch의 work.txt 내용을 살펴보자.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/ch10 (here)
$ git merge there
Auto-merging work.txt
CONFLICT (content): Merge conflict in work.txt
Automatic merge failed; fix conflicts and then commit the result.

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/ch10 (here|MERGING)
$ cat work.txt
A

H

T

<<<<<<< HEAD
H
=======
T
>>>>>>> there
```

3 way merge를 사용하기 때문에 세 번째 데이터까지는 처리하는 것에 문제가 없지만 마지막 데이터는 here와 there 모두에서 변경되어 git 스스로 처리하는 것이 불가능하다.

이러한 conflict를 사용자가 직접 해결하는 방법도 있지만 병합을 전문적으로 해주는 외부 tool을 사용할 수도 있다. P4Merge라는 툴을 사용했다.

Download Link : [P4Merge](https://www.perforce.com/downloads/visual-merge-tool)

P4Merge라는 tool을 merge하기 위해서 사용하려면 git에 전역설정을 해주어야 한다.

```bash
git config --global merge.tool p4merge
```
위와 같이 명령을 전달할 경우 git의 전역설정으로 merge tool로 p4merge tool을 사용하겠다는 것을 뜻한다. 이렇게 전역설정을 마친 뒤에 home directory의 `.gitconfig`파일을 보면 merge에 대한 세팅 정보를 확인할 수 있다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/ch10 (here|MERGING)
$ git config --global merge.tool p4merge

JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/ch10 (here|MERGING)
$ cat ~/.gitconfig
[user]
        name = Jaeseok
        email = jaeseok1115@gmail.com
[core]
        autocrlf = true
[merge]
        tool = p4merge
```

merge tool로 무엇을 사용할지 지정했다고 바로 사용할 수 있는 것은 아니다. p4merge를 사용한다고 했다면, 명령을 입력했을 때 해당 툴을 어디서 불러와서 사용할지에 대한 정보가 있어야 올바르게 명령을 실행할 수 있다.

따라서, 역시 `.gitconfig` 파일에 경로를 지정해주어야 하는데 이 때 사용하는 명령은 다음과 같다.

```bash
$ git config --global mergetool.p4merge.path "C:\Program Files\Perforce\p4merge.exe"
```

path 뒤에는 p4merge.exe 파일이 저장된 주소를 입력하면 되는데, 만약 자신의 컴퓨터에 설치된 위치가 다르다면 해당 위치에 맞게 수정해야 한다.

`.gitconfig` 파일을 살펴보면 merge tool의 경로가 지정된 것을 확인할 수 있다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/ch10 (here|MERGING)
$ cat ~/.gitconfig
[user]
        name = Jaeseok
        email = jaeseok1115@gmail.com
[core]
        autocrlf = true
[merge]
        tool = p4merge
[mergetool "p4merge"]
        path = C:\\Program Files\\Perforce\\p4merge.exe
```

이제 설정을 마쳤으니 merge tool을 활용하여 merge를 진행해보자.

merge tool을 활용한 merge를 진행하기 위해서는 명령으로 `git mergetool`을 전달하면 된다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/ch10 (here|MERGING)
$ git mergetool
Merging:
work.txt

Normal merge conflict for 'work.txt':
  {local}: modified file
  {remote}: modified file

```

![image](https://user-images.githubusercontent.com/27791880/126029924-919d338f-7c5b-4d14-a6e1-084c2a58cf58.png)

`git mergetool`을 실행한 뒤 나오는 창이 앞에서 설치한 p4merge이다.


**📌 참조** 

[Setup p4merge as difftool and mergetool on Windows](https://gist.github.com/dgoguerra/8258007)

가운데 나오는 노란색으로 표시된 `./work_BASE_1116.txt`는 두 브랜치의 공통의 조상이다.

merge 작업을 시작할 때 here branch에서 진행했다. 즉, 현재 속해 있는 LOCAL은 here인 것이고, 오른쪽에 표시된 초록색 영역이 here branch의 결과물이다. 따라서 남아 있는 파란색 영역인 REMOTE가 there branch에 해당한다.

하단에 `work.txt` 섹션에 있는 것이 p4merge가 생성해준 merge 파일이다. 하지만, 이 역시 마지막 데이터는 두 영역에서 모두 수정되었기에 사용자의 조작이 필요하다.

해당 영역은 사용자 임의대로 목적에 맞게 수정하면 된다.\

수정을 한 뒤 저장 후 프로그램을 종료하면 자동으로 `add`까지는 작업해준다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/ch10 (here|MERGING)
$ git status
On branch here
All conflicts fixed but you are still merging.
  (use "git commit" to conclude merge)

Changes to be committed:
        modified:   work.txt

Untracked files:
  (use "git add <file>..." to include in what will be committed)
        work.txt.orig
```

그럼 Untracked files에 있는 `work.txt.orig` 파일은 무엇일까? `orig`는 original의 약자로 merge tool을 사용하기 이전 상태를 백업해 둔 것이다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/ch10 (here|MERGING)
$ cat work.txt.orig
A

H

T

<<<<<<< HEAD
H
=======
T
>>>>>>> there
```

그리고 work.txt는 사용자 임의대로 바꾼 형태가 제대로 적용되어 있다.

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/ch10 (here|MERGING)
$ cat work.txt
A

H

T

D, T, H
```

merge 수정 사항이 확실하다면 이제 orig 파일은 필요 없기 때문에 삭제해준다.

`git commit`을 해주면 vim 편집기에 다음과 같이 충돌이 있었고 어떠한 행동을 하면 되는지에 대한 설명이 나온다.

![image](https://user-images.githubusercontent.com/27791880/126030372-927f0eb4-a322-43c6-8021-f4ecc506f2f8.png)

```bash
JaeSeok@DESKTOP-2UKJA1I MINGW64 ~/git_study/git3/ch10 (here)
$ git log --all --graph --oneline
*   c3b2d7c (HEAD -> here) Merge branch 'there' into here
|\
| * 5623fee (there) ABTT
* | cf8e9e9 AHCH
|/
* e4a1ac4 (master) ABCD
```

모두 정상적으로 merge가 완료되었다.

## 정리

* `git branch [branch name]` : 브랜치 생성
* `git branch` : 브랜치 목록 확인
* `git log --all` : 생성한 모든 브랜치를 확인할 수 있다.
* `git log --graph` : 시각적으로 branch간의 상관관계를 표현해준다.
* `git log --oneline` : 버전이 한 줄로 보여진다.
* `git checkout [branch name]` : 'branch name'으로 브랜치 전환
* `git commit --amend` : 방금 commit한 파일의 commit message 수정
* `git merge [branch name]` : 현재 브랜치에서 merge할 branch name을 입력하면 새로운 버전이 생성된다.
