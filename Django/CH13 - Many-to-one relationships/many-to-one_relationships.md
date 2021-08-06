# 다대일 관계 구현하기

`models.py`에 author 필드를 추가하고 나서 migration을 할 시, 아래와 같은 경고 메시지가 출력된다.

```bash
(venv) λ python manage.py makemigrations
You are trying to add a non-nullable field 'author' to post without a default; we can't do that (the database needs something to populate existing rows).
Please select a fix:
 1) Provide a one-off default now (will be set on all existing rows with a null value for this column)
 2) Quit, and let me add a default in models.py
Select an option:
```

```bash
(venv) λ python manage.py makemigrations
You are trying to add a non-nullable field 'author' to post without a default; we can't do that (the database needs something to populate existing rows).
Please select a fix:
 1) Provide a one-off default now (will be set on all existing rows with a null value for this column)
 2) Quit, and let me add a default in models.py
Select an option: 1
Please enter the default value now, as valid Python
The datetime and django.utils.timezone modules are available, so you can do e.g. timezone.now
Type 'exit' to exit this prompt
>>> 1
Migrations for 'blog':
  blog\migrations\0006_post_author.py
    - Add field author to post
```

우선은 1번을 옵션을 선택하고 pk=1인 유저를 author로 지정한다.

![image](https://user-images.githubusercontent.com/27791880/128346260-4e67ce20-4d0b-429d-bcc4-8d5bd9d63580.png)

Author 입력란이 생기고, 마이그레이션을 할 때 설정한 것처럼 pk=1인 user인 MrKeeplearning으로 작성자가 되어 있다.

## p.296 연결된 사용자가 삭제되면 빈 칸으로 두기

마이그레이션 시도 시 오류 메시지 발생

```bash
(venv) λ python manage.py makemigrations
SystemCheckError: System check identified some issues:

ERRORS:
blog.Post.author: (fields.E320) Field specifies on_delete=SET_NULL, but cannot be null.
        HINT: Set null=True argument on the field, or change the on_delete rule.
```

## p.302 오류 메시지

```bash
(venv) λ python manage.py test
Creating test database for alias 'default'...
System check identified no issues (0 silenced).
.F
======================================================================
FAIL: test_post_list (blog.tests.TestView)
----------------------------------------------------------------------
Traceback (most recent call last):
  File "D:\JaeSeok\Github\do_it_django\blog\tests.py", line 72, in test_post_list
    self.assertIn(self.user_trump.username.upper(), main_area.text)
AssertionError: 'TRUMP' not found in '\nBlog\n\n\n\n\nJanuary 1, 2021\n두 번째 포스트입니다.\nperfer et ob
dura dolor hic tibi proderit olim\nRead more →\n\n\n                    Posted on Aug. 5, 2021, 10:04 p.m . by\n                    작성자명 쓸 위치(개발예정)\n\n\n\n\n\n\nJanuary 1, 2021\n첫 번째 포스트입니다.\n
Hello World. We are the world\nRead more →\n\n\n                    Posted on Aug. 5, 2021, 10:04 p.m. by \n                    작성자명 쓸 위치(개발예정)\n\n\n\n\n\n\nNewer\n1\n2\n3\n...\n15\nOlder\n\n\n'

----------------------------------------------------------------------
Ran 2 tests in 0.495s

FAILED (failures=1)
Destroying test database for alias 'default'...
```

## p.304 오류 메시지

```bash
(venv) λ python manage.py test
Creating test database for alias 'default'...
System check identified no issues (0 silenced).
F.
======================================================================
FAIL: test_post_detail (blog.tests.TestView)
----------------------------------------------------------------------
Traceback (most recent call last):
  File "D:\JaeSeok\Github\do_it_django\blog\tests.py", line 105, in test_post_detail
    self.assertIn(self.user_trump.username.upper(), post_area.text)
AssertionError: 'TRUMP' not found in '\n\n\n\n첫 번째 포스트입니다.\n\n\nPosted on Aug. 5, 2021, 10:11 p.m
. by 작성자명 쓸 위치(개발예정) \n\nWeb Design\nFreebies\n\n\n\n\n\nHello World. We are the world.\n\n\n\n
\n'
----------------------------------------------------------------------
Ran 2 tests in 0.502s

FAILED (failures=1)
Destroying test database for alias 'default'...
```
