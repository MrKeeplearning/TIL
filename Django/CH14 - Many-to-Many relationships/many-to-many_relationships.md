# 다대다 관계 구현하기

## 1. Tag 모델 만들기

p.348 모델 수정 이후 makemigrations를 하게 되면?

```bash
D:\JaeSeok\Github\do_it_django (main -> origin)                          
(venv) λ python manage.py makemigrations                                 
System check identified some issues:                                     
                                                                         
WARNINGS:                                                                
blog.Post.tags: (fields.W340) null has no effect on ManyToManyField.
Migrations for 'blog':
  blog\migrations\0010_auto_20210808_1951.py
    - Create model Tag
    - Change Meta options on category
    - Alter field file_upload on post
    - Alter field head_image on post
    - Add field tags to post
```

ManyToManyField는 기본적으로 null=True로 설정되어 있기 때문에 따로 입력한 null=True가 효과를 주지 못한다.

## 2. 포스트 목록과 상세 페이지에 태그 기능 추가하기

p.355 테스트 코드 실행 후 결과

```bash
(venv) λ python manage.py test
Creating test database for alias 'default'...
System check identified no issues (0 silenced).
..F
======================================================================
FAIL: test_post_list (blog.tests.TestView)
----------------------------------------------------------------------
Traceback (most recent call last):
  File "D:\JaeSeok\Github\do_it_django\blog\tests.py", line 101, in test_post_list
    self.assertIn(self.tag_hello.name, post_001_card.text)
AssertionError: 'hello' not found in '\n\n\nprogramming\nJanuary 1, 2021\n첫 번째 포스트입니다.\nHello World. We are the world\nRead more →\n\n\n                    Posted on Aug. 8, 2021, 9:17 p.m. by\n                    TRUMP\n\n'

----------------------------------------------------------------------
Ran 3 tests in 0.716s

FAILED (failures=1)
Destroying test database for alias 'default'...
```

## 3. 태그 페이지 만들기

p.363 `AssertionError: 404 != 200` 오류에 대해서 정리해 놓을 것.

```bash
(venv) λ python manage.py test
Creating test database for alias 'default'...
System check identified no issues (0 silenced).
...F
======================================================================
FAIL: test_tag_page (blog.tests.TestView)
----------------------------------------------------------------------
Traceback (most recent call last):
  File "D:\JaeSeok\Github\do_it_django\blog\tests.py", line 171, in test_tag_page
    self.assertEqual(response.status_code, 200)
AssertionError: 404 != 200

----------------------------------------------------------------------
Ran 4 tests in 0.955s

FAILED (failures=1)
Destroying test database for alias 'default'...
```

p.365 뷰 수정하기 테스트

```bash
(venv) λ python manage.py test
Creating test database for alias 'default'...
System check identified no issues (0 silenced).
...F
======================================================================
FAIL: test_tag_page (blog.tests.TestView)
----------------------------------------------------------------------
Traceback (most recent call last):
  File "D:\JaeSeok\Github\do_it_django\blog\tests.py", line 177, in test_tag_page
    self.assertIn(self.tag_hello.name, soup.h1.text)
AssertionError: 'hello' not found in 'Blog '

----------------------------------------------------------------------
Ran 4 tests in 0.935s

FAILED (failures=1)
Destroying test database for alias 'default'...
```
