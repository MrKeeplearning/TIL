# CH07 : 장고 프로젝트에서 앱 개발하기

## Project vs App

Django의 공식문서에 따르면 프로젝트와 앱의 차이를 다음과 같이 명시하고 있다.

> Project vs App
>
> What’s the difference between a project and an app? An app is a Web application that does something – e.g., a Weblog system, a database of public records or a small poll app. A project is a collection of configuration and apps for a particular website. A project can contain multiple apps. An app can be in multiple projects.

위의 설명에 따라서 정말 간단하게 생각해보면 Project에 App이 종속되는 개념이라고 생각할 수 있다.

프로젝트는 전체 app의 집합과 그것에 속한 모든 part들(e.g., __init__.py, models.py, etc)을 의미한다. 그리고 app은 프로젝트의 submodule이라고 보면 된다. 즉, 특정 기능 수행을 위한 단위 모듈이다. 앱은 프로젝트 내에서 다른 앱들과 서로 엮이지 않기 때문에 이론 상 다른 프로젝트에 수정 없이 넣는 것이 가능하다. 따라서 앱을 독립 실행형 파이썬 모듈이라고 생각할 수도 있다. 만약 매우 단순한 프로젝트라면 하나의 앱만 가질 수 있다.

앱은 필요에 따라 다른 앱과 상호작용이 필요할 수도 있는데 이 때 중요하게 생각해야할 점은 앱 간의 상호의존성이다. 만약, 하나의 앱이 두 가지 문제를 해결하려고 한다면 두 개의 앱으로 나눌 필요가 있다. 또한 두 개의 앱이 서로 너무 의존적이기 때문에 하나의 앱만으로는 재사용이 불가능한 상황이라면 단일 앱으로 합치는 것이 낫다.

Ref.

* [Djnago documentation](https://docs.djangoproject.com/ko/3.2/intro/tutorial01/)
* [Stack Overflow](https://stackoverflow.com/questions/19350785/what-s-the-difference-between-a-project-and-an-app-in-django-world)

<br/>

## 앱 만들기

**Step1** : 가상환경이 실행 중인지 확인

**Step2** : `python manage.py startapp [app name]` 을 사용하여 앱을 생성

<br/>

## 모델 만들기

```bash
D:\JaeSeok\Github\do_it_django (main -> origin)
(venv) λ python manage.py makemigrations
No changes detected
```

```python
# Application definition

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

settings.py 파일에 `blog`와 `single_pages`를 추가하여 변경사항을 감지할 수 있도록 한다.

```bash
D:\JaeSeok\Github\do_it_django (main -> origin)
(venv) λ python manage.py makemigrations
Migrations for 'blog':
  blog\migrations\0001_initial.py
    - Create model Post
```

정상적으로 인식.

실제 DB에 모델 적용

```bash
D:\JaeSeok\Github\do_it_django (main -> origin)
(venv) λ python manage.py migrate
Operations to perform:
  Apply all migrations: admin, auth, blog, contenttypes, sessions
Running migrations:
  Applying blog.0001_initial... OK
```

## 포스트 개선하기

![image](https://user-images.githubusercontent.com/27791880/126892380-31769ecd-6c8e-4d1d-a582-9ea888e90eae.png)

Note: You are 9 hours ahead of server time.

어떻게 해결하지? settings.py 파일을 열어 기존 그리니치 표준시에 맞추어진 것을 서울을 기준으로 바꿔주자.

### 기존 설정

```bash
# Internationalization
# https://docs.djangoproject.com/en/3.2/topics/i18n/

LANGUAGE_CODE = 'en-us'

TIME_ZONE = 'UTC'

USE_I18N = True

USE_L10N = True

USE_TZ = True
```

### 수정 이후

```bash
# Internationalization
# https://docs.djangoproject.com/en/3.2/topics/i18n/

LANGUAGE_CODE = 'en-us'

TIME_ZONE = 'Asia/Seoul'

USE_I18N = True

USE_L10N = True

USE_TZ = False
```

TIME_ZONE과 USE_TZ 항목을 변경함. 그런데, USE_TZ 항목은 무엇을 담당하지?
