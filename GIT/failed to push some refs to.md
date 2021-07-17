# error: failed to push some refs to '[git 주소]`는 어떻게 해결할까?

GIT의 활용 방법에 대해서 공부하다가 다음과 같은 에러를 만났다.

![image](https://user-images.githubusercontent.com/27791880/126046548-31cc2027-ecc3-4b9e-8df3-a1d8fc71117a.png)

이 에러가 나오기 전에는 새롭게 생성한 GithubPractice2 레포지토리를 로컬 저장소와 연결하기 위한 작업을 진행 중이었다.

![image](https://user-images.githubusercontent.com/27791880/126046622-13b2730b-6d31-413b-9061-81020f77dda0.png)

원격저장소에 origin을 정상적으로 추가한 뒤에 로컬저장소의 커밋을 원격저장소로 업로드하기 위해 `git push`를 진행했다.

GithubPractice2 repository에는 처음으로 push를 하는 것이기 때문에 위의 `git push --set-upstream origin main` 를 진행했지만 error가 나왔다.

```bash
To https://github.com/MrKeeplearning/GithubPractice2.git
 ! [rejected]        main -> main (fetch first)
error: failed to push some refs to 'https://github.com/MrKeeplearning/GithubPractice2.git'
hint: Updates were rejected because the remote contains work that you do
hint: not have locally. This is usually caused by another repository pushing
hint: to the same ref. You may want to first integrate the remote changes
hint: (e.g., 'git pull ...') before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.
```

### 위 에러는 왜 발생한 것일까?

---

hint란에 적힌 내용을 다시 살펴보자.

> Updates were rejected because the remote contains work that you do not have locally. This is usually caused by another repository pushing to the same ref. You may want to first integrate the remote changes(e.g., 'git pull ...') before pushing again. See the 'Note about fast-forwards' in 'git push --help' for details.

로컬저장소에는 원격저장소에서 작업한 내용이 없다고 한다. 그리고 친절하게도 push를 하기 전에 pull을 통해 원격저장소의 내용을 내려받아 로컬저장소의 내용과 merge를 먼저하라고 설명을 해주고 있다.

맨 처음 GithubPractice2라는 레포지토리는 내 Github에서 만들면서 README.md 파일을 생성해둔 상태이다. 따라서 원격저장소의 main branch에는 README.md 파일이 존재하지만 로컬저장소에는 f1.txt만 존재하고 README.md 파일은 없다. 결과적으로 두 저장소는 차이가 존재하고 git은 이러한 이유 때문에 push를 거부한 것이다.

### Solution

---

로컬저장소와 원격저장소의 차이를 없애기 위해 동기화를 시켜주면 된다. `pull`은 앞에서도 말했다시피 원격저장소의 브랜치를 가져와서 로컬저장소의 브랜치와 병합을 진행한다. 만약, 충돌이 발생하게 되면 그 부분은 사용자가 직접 수정해준 뒤에 commit을 해야 한다.

이러한 pull의 특성을 가지고 원격저장소의 main branch를 pull한 뒤 다시 push를 해주면 정상적으로 push가 된다.

<br/>

**Ref**

* https://velog.io/@1703979/git-%EC%97%90%EB%9F%AC-rejected-main-main-fetch-first
* https://blog.dalso.org/it/git/14204
* https://aonee.tistory.com/14
* https://devlog-wjdrbs96.tistory.com/236
* https://backlog.com/git-tutorial/kr/stepup/stepup3_1.html
