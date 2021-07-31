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

프로젝트를 생성하고 난 뒤 `startproject`를 사용할 때 같이 입력한 프로젝트명에 해당하는 폴더로 이동해보면 하위에 다음과 같은 파일들이 생성된 것을 확인할 수 있다.

![image](https://user-images.githubusercontent.com/27791880/127747959-07aab554-3c64-4edb-a650-e47577510fdc.png)

각각의 파일이 어떤 역할을 하는지 간단히 알아보자.

### __init__.py

이 파일은 비어있는 파일이다. `__init__.py` 파일이 있기 때문에 파이썬에서는 현재 디렉토리를 하나의 파이썬 패키지로 인식할 수가 있다.

참고로 파이썬에서 패키지는 dot(.)을 사용하여 파이썬 모듈을 계층적(directory 구조)으로 관리할 수 있게 해준다. 만약 어떤 모듈의 이름이 `A.B`라면 패키지 A의 모듈 B라고 이해하면 된다. 이 때, 모듈은 하나의 .py 파일에 해당한다. 따라서, `do_it_django_prj` 패키지 밑에 있는 asgi.py, settings.py, urls.py, wsgi.py들은 모두 module인 것이다.

> 💡 python3.3 부터는 `__init__.py` 파일이 없어도 패키지로 인식이 된다. 하지만, backward compatibility를 위해서 `__init__.py` 파일을 생성해두는 것이 안전하다.

### settings.py

이 파일에는 현재 만드려는 웹사이트의 모든 설정을 담고 있다. 이 파일에는 우리가 앞으로 만들 모든 앱, 정적파일의 위치, 데이터베이스 세부 설정 등을 등록한다.

### urls.py

이 파일은 사용자가 어떤 URL형식으로 접근했을 때 어떻게 웹 사이트를 작동시킬지를 정리한 파일이다. 즉, 사이트의 URL과 view의 연결을 지정해준다. urls.py에는 모든 URL 맵핑 코드가 포함될 수도 있지만 일반적으로는 하위의 나머지 앱에 맵핑의 일부를 할당해준다. 예를 들어보자. 아래는 `do_it_django_prj`의 `urls.py` 파일이다.

```python
from django.contrib import admin
from django.urls import path, include

urlpatterns = [
    path('blog/', include('blog.urls')),
    path('admin/', admin.site.urls),
]
```

현재 프로젝트에 연결된 앱은 blog와 admin이 있는데, 각각의 앱에는 하나 이상의 기능들이 구현되어 있다. 즉, URL 맵핑 코드가 더 있어야 맞다는 것이다. 그런데, 일반적으로는 앞에서도 말했다시피 맵핑의 일부를 앱에 할당해준다. 이번에는 `blog`의 `url.py`를 살펴보자.

```python
from django.urls import path
from . import views

urlpatterns = [
    path('<int:pk>/', views.PostDetail.as_view()),
    path('', views.PostList.as_view()),
]
```

이처럼 '포스트 자세히 보기' 기능과 '포스트 목록' 기능에 대한 나머지 URL 맵핑 코드가 존재하는 것을 확인할 수 있다.

### wsgi.py

이 파일은 장고 앱이 웹서버와 연결 및 소통하는 것을 돕는다.

*추후 내용 보충 예정*

### asgi.py

*추후 내용 추가 예정*

<br/>

다시 `runserver`를 한 결과를 살펴보자.

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

<br/>

### .gitignore파일에 db.sqlite3 추가하기

<br/>

db.sqlite3를 .gitignore 파일에 추가해서 버전관리를 하지 않도록 만든다. 완성된 웹사이트를 실제로 운영하기 전까지 다양한 테스트를 진행할 것이고 그 과정에서 생성된 게시글과 댓글 등은 모두 데이터베이스인 db.sqlite3에 저장된다. 실제 서버를 운영하는 상황에서 기존 local에서 생성한 db.sqlite3를 올리게 되면 해당 db.sqlite3와 실제 구동되는 서버에 올려진 db.sqlite3와 충돌을 일으켜 오류가 발생할 수 있다.
