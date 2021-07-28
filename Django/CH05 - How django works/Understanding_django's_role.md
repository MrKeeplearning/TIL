# 장고의 역할 이해하기

<br/>

## 1. 웹 프레임워크의 역할

<br/>

### 웹 사이트의 공통점

* Create: 생성
* Read: 조회
* Update: 수정
* Delete: 삭제

웹 프레임워크는 웹 사이트들의 공통적인 특징인 CRUD의 4가지 기능과 관리자 페이지를 좀 더 쉽게 개발할 수 있도록 도와준다.

<br/>

## 2. 장고의 작동 구조 이해하기

<br/>

![image](https://user-images.githubusercontent.com/27791880/126987749-ef8bab7a-a9d4-4cf5-b27d-3e99109617ce.png)

<br/>

1. client에서 keeplearning.com이라는 이름의 서버를 찾아간다.

2. urls.py를 요청해서 내용을 확인한다. urls.py에는 예를 들어 '`keeplearning.com/about` 을 방문했을 때는 `about_me` 함수를 실행시킨다'와 같이 어떤 함수를 실행시킬지에 대한 내용이 기술되어 있다.

   e.g.,

   ```python
   # urls.py
   from django.urls import path
   from . import views

   urlpatterns = [
        path('<int:pk>/', views.PostDetail.as_view()),
        path('', views.PostList.as_view()),
   ]
   ```

3. urls.py에서 언급한 `about_me`와 같은 함수는 views.py에 정의되어 있다. views.py에는 함수 뿐만이 아닌 클래스까지도 정의하고 있다. 위에서 예시로 보여준 urls.py에는 PostList와 PostDetail함수가 사용되었는데, 이 두 함수에 대한 기능을 기술한 곳이 views.py이다.

   e.g.,

   ```python
   # views.py
   from django.views.generic import ListView, DetailView
   from .models import Post
   
   class PostList(ListView):
        model = Post
        ordering = '-pk'

   class PostDetail(DetailView):
        model = Post
   ```

4. 게시글(post)에는 제목, 글, 작성자, 작성일 등이 포함되어야 한다. 장고에서는 models.py에 각 페이지별로 담아야 할 자료의 형태를 클래스로 정의하는데 이것을 모델이라고 한다. 아래는 블로그 페이지에 보여야 하는 자료의 형태를 Post 클래스, 즉 Post 모델에 정의한 것이다.

   e.g.,

   ```python
   # models.py
   from django.db import models

   class Post(models.Model):
        title = models.CharField(max_length=30)
        content = models.TextField()
        created_at = models.DateTimeField(auto_now_add=True)
        updated_at = models.DateTimeField(auto_now=True)
   
        def __str__(self):
        return f'[{self.pk}]{self.title}'

        def get_absolute_url(self):
        return f'/blog/{self.pk}/'
   ```

5. 데이터베이스에서 자료를 가져올 때는 models.py에서 정의한 모델에 맞게 가져온다. 예를 들어 views.py의 PostList클래스에서 게시물들을 가져와서 최근의 작성한 것이 가장 위에 오도록 배치를 하고 싶다면 DB에서 최근 게시물들을 불러오는 것이다.

6. 템플릿에는 DB에서 가져은 내용들을 형식에 맞게 채워넣어 사용자의 화면에 보여주는 역할을 한다.

<br/>
<br/>

## 2. MVC 패턴

<br/>

### MVC 패턴이란?

---

하나의 파일 안에서 변수들을 다루고, DB에 접속하고, 사용자에게 보여질 HTML 파트까지 모든 작업이 이루어진다고 생각해보자. 그 결과물이 작은 게시판 수준이라면 상관 없을지도 모르겠지만 네이버와 같은 대형 프로젝트가 되는 순간 유지보수가 힘들어진다. 그래서 각각의 기능 별로 모듈화한 것이 MVC 패턴이다.

<br/>

### MVC에서 M, V, C는 각각 무엇을 말하는 것일까?

---

#### ✅ Model - 데이터와 관련된 부분

게시판을 예로 들었을 때, 게시판에 쓰이는 글, 그리고 그 글의 작성자, 글의 작성 시각과 수정 시각 등은 데이터베이스에 데이터로 저장된다. 이 데이터의 형식을 지정하고 불러오는 작업을 하는 것에 대한 코드가 Model 파트에서 이루어진다.

#### ✅ View - 사용자한테 보여지는 부분

그리고 데이터베이스에 저장된 데이터들을 사용자들이 시각적으로 볼 수 있게 하는 html, css 등의 요소가 View 파트에 작성된다.

#### ✅ Controller - Model과 View를 이어주는 부분

Model과 View를 연결시키는 작업을 하는 것이 Controller이다. Model의 데이터를 View에 연결해서 사용자가 GUI 화면을 통해 해당 데이터에 대하여 CRUD 작업을 실행할 수 있도록 제어를 하는 것이 Controller의 역할이다.

<br/>

유지보수를 위해 사용하는 것이 MVC 패턴이지만 이것을 매번 만들어서 사용하는 것은 효율적인 방식이 아니다. 그래서 Model, View, Controller를 지닌 웹사이트의 기본 골격을 코드로 만들어 제공하는 것이 **MVC 웹 프레임워크** 이다.

프레임워크는 내가 아닌 다른 사람이 작성한 코드를 제공한다는 점에서 **라이브러리** 와도 유사하다. 하지만 라이브러리가 집을 구성하는 문, 수도꼭지, 변기와 같은 집의 기본적인 구성 요소, 즉 웹사이트에서 사용할 수 있는 개별적인 기능이라면, 프레임워크는 집의 기본 구성 요소가 갖추어진 꾸미기 전의 집, 즉 기초적인 골격을 갖춘 것을 말한다.

python의 django도 MVC 웹 프레임워크 중 하나이고, 여기서는 MVC가 아닌 **MTV 패턴** 을 사용한다. 하지만, 그 기반은 MVC이기 때문에 서로 유사한 형태를 가진다.

<br/>

### \[💡참고\] MVC를 지키는 코딩 방법

---

1. Model은 Controller와 View에 의존하지 않아야 한다. 즉, Controller와 View에 관련된 코드가 Model에 들어가 있으면 안된다. 데이터에 대한 코드만을 깔끔하게 정리하기 위함이다.

2. View는 Model에만 의존하고 Controller에는 의존하면 안 된다. 즉, View에는 Model에 관련된 코드는 있을 수 있지만, Controller의 코드가 담기면 안된다.

3. View가 Model로부터 데이터를 받을 때는 사용자마다 다르게 보여주어야 하는 데이터에 대해서만 받아야 한다. 예를 들어, 앱스토어의 계정 페이지를 생각해보자.
   
   <img src="https://user-images.githubusercontent.com/27791880/127140870-1c361ceb-a784-492d-a534-78d0b5d32037.jpg" width="300">
   
   <br/>

   위의 캡쳐 화면에서 보이듯이 계정, 완료, 구입항목, 구독 등과 같은 텍스트와 그 텍스트들의 구성 등은 어떠한 사용자이든 동일하게 보여야 한다. 하지만 가장 밑에 있는 항목인 최근 업데이트된 항목은 사용자마다 다른 내용이 보여져야 한다. 그리고 View가 Model로부터 받아야 할 데이터가 이러한 부분인 것이다.

4. Controller는 Model과 View에 의존해도 된다. 그 이유는 controller가 model과 view의 중개자 역할을 하면서 전체 로직을 구성하기 때문이다.

5. View는 Model로부터 데이터를 받을 때 반드시 Controller에서 받아야 한다.

<br/>

Ref.

* [우아한Tech - 10분 테코톡](https://youtu.be/ogaXW6KPc8I)
* [얄팍한 코딩사전 - MVC 웹 프레임워크가 뭔가요?](https://youtu.be/AERY1ZGoYc8)

<br/>
<br/>

## 3. MTV 패턴

<br/>

앞서 다루었던 것처럼 장고는 MVC 웹 프레임워크이지만 디자인 패턴으로 MTV 패턴을 사용한다. 하지만 기본적인 틀은 MVC와 동일하다고 볼 수 있다.

<br/>

### ✅ Model

---

MTV 패턴에서 Model은 MVC의 Model과 동일하다. Model은 DB에 저장되는 데이터의 형식을 의미한다. 모델에는 클래스가 정의되어 있는데, 하나의 클래스는 하나의 DB table을 의미한다고 볼 수 있다. 이렇게 DB의 데이터들을 가지고 작업을 하기 때문에 SQL을 다룰 줄 알아야할 것 같지만, 장고에서는 SQL을 ORM(Object Relational Mapping)으로 대체하는 것이 가능하다.

<br/>

#### 💡 ORM은 무엇인가?

---

ORM에 대해서 간단하게 알아보자.

SQL은 데이터베이스와 대화를 할 때 사용하는 언어를 뜻한다. 그래서 데이터베이스로부터 정보를 얻고 싶거나 데이터를 관리하는 작업을 할 때 SQL을 사용한다. 그리고 콘솔에 작성하는 것을 SQL statement라고 한다. SQL 데이터베이스는 관계형 데이터베이스이다. 이것과 반대에 있는 것이 흔히 NoSQL이라고 많이 말하는 비관계형 데이터베이스인데 이 둘은 완전히 다른 시스템이다. 아무튼 여기서 중요한 것은 SQL이 데이터베이스와 소통하는 언어라는 것이다.

예를 들어, 엑셀 페이지와 같은 데이터 테이블이 있다고 생각해보자. 우리는 여기서 쿠바에서 온 유저를 찾고 싶고 이들이 가입한 날짜를 기준으로 정렬하고 싶다. 이것을 SQL statement로 표현하면 다음과 같이 표현할 수 있다.

```SQL
SELECT * FROM users WHERE country="cuba" ORDER BY created_date
```

그런데 위와 같은 SQL statement를 장고 ORM으로 표현한다면 다음과 같이 표현하는 것이 가능하다.

```python
User.objects.filter(country="cuba").order_by('created_date')
```

즉, 장고 ORM은 파이썬과 SQL 사이의 통역사와 같은 것이다. 장고는 위의 파이썬 코드를 맨 처음 작성했던 SQL로 번역할 것이다. 따라서 개발자가 직접 SQL문을 작성할 필요가 없다. 결과적으로 DB는 장고가 해석한 SQL문을 받게 된다.

<br/>

### ✅ Template

---

MVC 패턴에서 View와 대응된다. 즉, 유저에게 보여지는 부분을 담당하는데 이 때 MTV 패턴의 View에서 어떤 자료를 어떤 동작으로 보여줄지 정의를 한 것을 바탕으로 html 파일에 적용을 한다. 장고 자체적으로 Django Template 문법을 지원하기 때문에 이 문법을 html 태그 사이에 적재적소 배치하여 받은 데이터를 사용자에게 보여줄 수 있다.

<br/>

### ✅ View

---

MVC 패턴의 View와 이름이 동일하여 혼동될 수 있으나 MTV 패턴의 View는 MVC의 Controller에 해당한다. View는 웹으로부터 요청을 받기도 하고, Model로부터 받은 데이터를 View에 선언한 class(CBV 방식)나 함수(FBV 방식)를 통해서 가공하여 template에게 전달해준다.
