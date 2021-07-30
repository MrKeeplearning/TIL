# CH07 : 장고 프로젝트에서 앱 개발하기

## 07-3

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
