# CH07 : 장고 프로젝트에서 앱 개발하기

## 1. Project vs App

Django의 공식문서에 따르면 프로젝트와 앱의 차이를 다음과 같이 명시하고 있다.

> Project vs App
>
> What’s the difference between a project and an app? An app is a Web application that does something – e.g., a Weblog system, a database of public records or a small poll app. A project is a collection of configuration and apps for a particular website. A project can contain multiple apps. An app can be in multiple projects.

위의 설명에 따라서 정말 간단하게 생각해보면 Project에 App이 종속되는 개념이라고 생각할 수 있다.

프로젝트는 전체 app의 집합과 그것에 속한 모든 part들(e.g., __init__.py, models.py, etc)을 의미한다. 그리고 app은 프로젝트의 submodule이라고 보면 된다. 즉, 특정 기능 수행을 위한 단위 모듈이다. 앱은 프로젝트 내에서 다른 앱들과 서로 엮이지 않기 때문에 이론 상 다른 프로젝트에 수정 없이 넣는 것이 가능하다. 따라서 앱을 독립 실행형 파이썬 모듈이라고 생각할 수도 있다. 만약 매우 단순한 프로젝트라면 하나의 앱만 가질 수 있다.

앱은 필요에 따라 다른 앱과 상호작용이 필요할 수도 있는데 이 때 중요하게 생각해야할 점은 앱 간의 상호의존성이다. 만약, 하나의 앱이 두 가지 문제를 해결하려고 한다면 두 개의 앱으로 나눌 필요가 있다. 또한 두 개의 앱이 서로 너무 의존적이기 때문에 하나의 앱만으로는 재사용이 불가능한 상황이라면 단일 앱으로 합치는 것이 낫다.

Ref.

* [Django documentation](https://docs.djangoproject.com/ko/3.2/intro/tutorial01/)
* [Stack Overflow](https://stackoverflow.com/questions/19350785/what-s-the-difference-between-a-project-and-an-app-in-django-world)

<br/>

## 2. 앱 만들기

**Step1** : 가상환경이 실행 중인지 확인

**Step2** : `python manage.py startapp [app name]` 을 사용하여 앱을 생성

<br/>

## 3. 모델 만들기

### 3.1. Post 모델 생성

장고는 디자인 패턴으로 MTV패턴을 사용한다고 했다. 그리고 MTV패턴에서 Model은 데이터베이스에 저장되는 데이터의 형식을 정의한다.

블로그를 제작할 것인데 블로그에 핵심은 블로그에 올라간 포스트이다. 이 포스트의 형태를 정의하는 Post 모델을 만들어보자. Post에는 제목(title), 내용(content), 작성일(created_at), 작성자(author) 정보가 필요하다.

```python
# blog/models.py

from django.db import models


class Post(models.Model):
    title = models.CharField(max_length=30)
    content = models.TextField()

    created_at = models.DateTimeField(auto_now_add=True)
    # The author field will be made soon.
```

Post 모델은 django.db 패키지의 models모듈에 있는 Model클래스를 상속해서 만든 클래스이다. 객체로 선언한 변수명(e.g., title, content)을 데이터베이스 필드와 매칭하여 사용자가 입력한 자료를 데이터베이스에 기록한다던지, 데이터베이스에 기록된 정보를 다시 파이썬 객체로 불러오는 등의 기능을 위하여 Model 클래스를 상속하는 것이다. 그리고 이러한 기능들은 Model 클래스에 함수로 정의되어 있다.

참고로, models.py는 models를 import하고 있다. 이 경우에는 해당 폴더의 `__init__.py` 파일을 먼저 읽게 된다. 그리고 `__init__.py` 파일을 살펴보면 `__all__` 변수에서 Model을 지정하여 자동으로 import되도록 하고 있다. 그리고 Model 클래스는 `base.py` 파일에 담겨 있다.

Ref.

[doitdjango.com의 Q&A게시판](https://doitdjango.com/board/qna/114/) - 작가님의 친절한 답변으로 이해할 수 있었다^^

장고에서는 다양한 모델의 field 옵션을 제공하고 있다. 각 field option에 대한 상세한 내용은 django의 [DOCUMENTATION](https://docs.djangoproject.com/en/3.2/ref/models/fields/)을 통해 확인할 수 있다.

일단 위에서는 제목에는 CharField를, 글에는 TextField 옵션을 사용했는데, CharField는 반드시 argument로 max_length를 지정해주어 최대길이를 제한해주어야 한다.

### 3.2. DB에 Post 모델 반영

앞에서 Post 모델에서 데이터베이스에 어떤 데이터를 저장할지 그 형식을 지정했다. 아직 데이터베이스에 적용이 되지 않은 상태이니 migration을 통해서 적용을 시키자.

```bash
D:\JaeSeok\Github\do_it_django (main -> origin)
(venv) λ python manage.py makemigrations
No changes detected
```

### 3.3. settings.py에 blog 앱 등록하기

migration을 적용했음에도 변화를 감지하지 못했다. 그 때는 맨 처음 생성했던 프로젝트 폴더(do_it_django_prj) 아래의 settings.py 파일에 현재 작업 중인 blog 앱이 등록되어 있는지 확인해야 한다.

```python
# do_it_django_prj/settings.py

INSTALLED_APPS = [
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles',
    'blog',
    'single_pages'
]
```

위와 같이 settings.py 파일에서 INSTALLED_APPS 리스트에 `blog`와 `single_pages`앱을 추가하여 변경사항을 감지할 수 있도록 한다.

### 3.4. 데이터베이스에 Post 모델 반영

```bash
D:\JaeSeok\Github\do_it_django (main -> origin)
(venv) λ python manage.py makemigrations
Migrations for 'blog':
  blog\migrations\0001_initial.py
    - Create model Post
```

앞에서 입력했던 `python manage.py makemigrations`를 입력하면 정상적으로 인식하는 것을 확인할 수 있다. 그러나 아직 실제 데이터베이스에는 적용되기 전이기 때문에 마지막으로 `python manage.py migrate`를 통해서 실제 DB에 적용시킨다.

```bash
D:\JaeSeok\Github\do_it_django (main -> origin)
(venv) λ python manage.py migrate
Operations to perform:
  Apply all migrations: admin, auth, blog, contenttypes, sessions
Running migrations:
  Applying blog.0001_initial... OK
```

<br/>

## 4. 포스트 개선하기

### 4.1. 특정 지역을 기준으로 작성 시각 맞추기

![image](https://user-images.githubusercontent.com/27791880/126892380-31769ecd-6c8e-4d1d-a582-9ea888e90eae.png)

admin 페이지에서 Post를 작성하고 난 뒤에 Created at에서 작성시각을 입력하려고 보면 하단에 'Note: You are 9 hours ahead of server time.'라는 문장이 나온 것을 확인할 수 있다. 이것은 그리니치 표준 시에 맞추어져 있기 때문이다. 따라서 서울을 기준으로 시간을 바꾸어주자.

#### Solution

do_it_django_prj\settings.py 파일을 열어 기존 그리니치 표준시에 맞추어진 것을 서울을 기준으로 바꿔주자.

#### 기존 설정

```python
# Internationalization
# https://docs.djangoproject.com/en/3.2/topics/i18n/

LANGUAGE_CODE = 'en-us'

TIME_ZONE = 'UTC'

USE_I18N = True

USE_L10N = True

USE_TZ = True
```

기존 설정에서 TIME_ZONE과 USE_TZ 항목을 변경하면 된다.

#### 수정 이후

```python
# Internationalization
# https://docs.djangoproject.com/en/3.2/topics/i18n/

LANGUAGE_CODE = 'en-us'

TIME_ZONE = 'Asia/Seoul'

USE_I18N = True

USE_L10N = True

USE_TZ = False
```

그런데, TIME_ZONE은 무엇을 담당하는지 알곘는데, USE_TZ 항목은 무엇을 담당할까?

