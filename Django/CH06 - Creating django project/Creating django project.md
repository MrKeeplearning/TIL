# Chapter06: 장고 프로젝트 만들기

<br/>

## 1. 개발 시작 전 환경 구축하기

앞으로 진행되는 프로젝트는 가상환경에서 진행된다. 그리고 가상환경은 파이참에서 제공하는 기능을 활용하여 생성하였다.

장고 프로젝트를 진행할 때 가상환경을 사용하는 이유는 다른 프로젝트에 영향을 주지 않기 위함이다. 만약 현재 장고로 웹 사이트를 개발하는 프로젝트를 진행하는 도중에 같이 파이썬을 사용하는 다른 프로젝트를 개발 중이라고 가정해보자. 그렇다면 현 프로젝트를 위해 설치한 외부 모듈이 파이썬 개발환경 전체에 영향을 줄 수 있고 이것은 다른 프로젝트에 의도하지 않은 문제를 발생시킬 수 있다.

github 저장소를 생성할 때 `.gitignore` 파일을 생성하였는데, 이곳에는 가상환경 폴더인 `venv/`도 포함되어 있다. 여기에 한 가지 더 추가해야할 것이 있는데, 파이참에서 사용자 편의를 위해 제공하는 `.idea` 폴더이다. 파이참에서 자체 제공하는 폴더이기 때문에 버전관리는 필요가 없고 또한 다른 PC에서 작업할 때 오류를 발생시킬 수 있기 때문에 `.gitignore`폴더에 담아준다.

<br/>

### 💡 가상환경 실행하기

---

cmder(터미널)에서 가상환경을 실행한다. 프로젝트가 있는 폴더에 들어가서 아래와 같은 명령을 입력해준다.

```bash
D:\JaeSeok\Github\do_it_django (main -> origin)
λ venv\Scripts\activate.bat
```

위와 같이 명령을 입력해주고 나면 터미널이 정리되면서 아래와 같은 화면이 나올 것이다.

```bash
D:\JaeSeok\Github\do_it_django (main -> origin)
(venv) λ
```

가상환경이 실행되면 기존 환경과 시각적으로 다른 점이 shell prompt(`λ`) 앞에 가상환경을 뜻하는 `(venv)`가 추가된 것이다. 만약 가상환경을 종료하고 싶다면 프롬프트에 `deactivate`를 입력한다.

장고를 사용할 것이기 때문에 장고를 설치해준다.

프롬프트에 `pip install django`를 입력하여 설치하고 현재 설치된 버전이 궁금하다면 `pip list`를 통해서 설치된 모든 패키지를 확인함과 동시에 버전 정보도 확인이 가능하다.

<br/>

## 2. 장고로 기초 웹 사이트 생성

프로젝트 생성 시 아래와 같은 명령을 사용하는데, 반드시 끝에 띄어쓰기와 함께 dot을 붙여주자. 이것은 현재 폴더에 프로젝트를 생성한다는 것을 의미한다.

```bash
D:\JaeSeok\Github\do_it_django (main -> origin)
(venv) λ django-admin startproject do_it_django_prj .
```

프로젝트 생성 뒤 프로젝트 생성이 성공했는지 확인을 위해 `python manage.py runserver`를 입력하여 서버를 실행해보자. 처음에는 오류 메시지가 나오겠지만 하단에 있는 주소로 들어갔을 때 **'The install worked successfully'** 라는 메시지가 나온다면 성공한 것이다.

```bash
D:\JaeSeok\Github\do_it_django (main -> origin)
(venv) λ python manage.py runserver
Watching for file changes with StatReloader
Performing system checks...

System check identified no issues (0 silenced).

You have 18 unapplied migration(s). Your project may not work properly until you apply the migrations for app(s): admin, auth, contenttypes, sessions.
Run 'python manage.py migrate' to apply them.
July 24, 2021 - 19:02:34
Django version 3.2.5, using settings 'do_it_django_prj.settings'
Starting development server at http://127.0.0.1:8000/
Quit the server with CTRL-BREAK.
```

서버 실행 후 만난 오류 메시지에는 18개의 migration들을 적용하기 전까지는 프로젝트가 정상적으로 작동하지 않는다고 적혀있다.

<br/>

### Migration?

<br/>

Migration의 사전적 의미는 다음과 같다.

> 1. (사람∙철새∙동물의 대규모) 이주[이동]
> 2. (컴퓨터 시스템∙프로그램의) 이송[이행]

장고에서는 데이터베이스에 적용해야 하는 변화에 대한 기록을 의미한다.

댓글 기능이 없는 블로그에 댓글 작성 기능을 추가했다고 가정해보자. 정상적으로 작동되기 위해서는 DB에 댓글 저장을 위한 공간(테이블)이 필요하고, 이것을 DB에 반영해야한다. 이후 서버를 실행했을 때 댓글 기능이 추가된 블로그를 사용할 수 있다.

아직 아무 기능이 없음에도 마이그레이션이 필요한 이유는 장고가 새 프로젝트 생성 시 DB에 기본적으로 필요한 테이블을 미리 마련하기 때문이다.

<br/>

### 데이터베이스 생성

<br/>

`python manage.py migrations`로 migration을 먼저 만드는 것이 맞다. 하지만, 현재는 장고에서 만들어준 migration이 있기 때문에 이 과정을 생략하고 바로 `python manage.py migrate`을 진행한다.

```bash
D:\JaeSeok\Github\do_it_django (main -> origin)
(venv) λ ls
db.sqlite3  do_it_django_prj/  manage.py*  README.md  venv/
```

위의 명령을 실행하고 나면 db.sqlite3 파일이 생성된 것을 확인할 수 있는데, 이 파일 안에 migration을 반영한 데이터베이스가 생성된다. 참고로 SQLite3는 장고의 기본 데이터베이스이다.

<br/>

### 관리자 계정 생성

<br/>

블로그의 글을 관리하고 블로그에 추가 기능들을 적용하기 위해서는 해당 블로그를 관리할 수 있는 관리자의 계정이 필요하다.

`python manage.py createsuperuser`를 통해서 관리자 계정을 생성해주자.

```bash
D:\JaeSeok\Github\do_it_django (main -> origin)                  
(venv) λ python manage.py runserver                              
Watching for file changes with StatReloader                      
Performing system checks...                                      
                                                                 
System check identified no issues (0 silenced).                  
July 24, 2021 - 23:34:46                                         
Django version 3.2.5, using settings 'do_it_django_prj.settings' 
Starting development server at http://127.0.0.1:8000/            
Quit the server with CTRL-BREAK.
```

계정 생성 후 `python manage.py runserver`를 입력 한 뒤 나오는 주소에 `/admin`을 붙여서 관리자 페이지로 이동할 수 있다. 이 때 나오는 관리자 페이지는 장고 프레임워크에서 자체 제공하는 기능이다.
