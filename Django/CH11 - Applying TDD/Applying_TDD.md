# 테스트 주도 개발 적용하기

p.253

```bash
D:\JaeSeok\Github\do_it_django (main -> origin)
(venv) λ python manage.py test
Creating test database for alias 'default'...
System check identified no issues (0 silenced).
E
======================================================================
ERROR: test_post_list (blog.tests.TestView)
----------------------------------------------------------------------
Traceback (most recent call last):
  File "D:\JaeSeok\Github\do_it_django\blog\tests.py", line 28, in test_post_list
    self.assertIn('아직 게시물이 없습니다.', main_area.text)
AttributeError: 'NoneType' object has no attribute 'text'

----------------------------------------------------------------------
Ran 1 test in 0.018s

FAILED (errors=1)
Destroying test database for alias 'default'...
```

p.254

```bash
D:\JaeSeok\Github\do_it_django (main -> origin)
(venv) λ python manage.py test
Creating test database for alias 'default'...
System check identified no issues (0 silenced).
F
======================================================================
FAIL: test_post_list (blog.tests.TestView)
----------------------------------------------------------------------
Traceback (most recent call last):
  File "D:\JaeSeok\Github\do_it_django\blog\tests.py", line 28, in test_post_list
    self.assertIn('아직 게시물이 없습니다.', main_area.text)
AssertionError: '아직 게시물이 없습니다.' not found in '\nBlog\n\n\n\n\nNewer\n1\n2\n3\n...\n15\nOlder\n\n\n'

----------------------------------------------------------------------
Ran 1 test in 0.017s

FAILED (failures=1)
Destroying test database for alias 'default'...
```

## 11-3 포스트 상세 페이지 테스트하기
