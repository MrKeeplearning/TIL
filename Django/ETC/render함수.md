# render()

템플릿에 context를 채워서 표현한 결과를 HttpResponse 객체와 함께 돌려주는 구문은 자주 사용되는데, 이것을 장고에서는 `render()`를 통해서 쉽게 표현이 가능하도록 shortcut을 제공한다.

`render()` 함수는 request 객체를 첫 번째 인수로 받고, 템플릿 이름을 두 번째 인수로 받으며, 선택적 인수로 context의 dictionary형태 객체, `content_type`, response의 status code, 그리고 template을 로딩하는데 사용하는 template engine의 이름 등을 받을 수 있다.

<br/>

## 예시

```python
# blog/views.py

from django.shortcuts import render

(...생략...)

def category_page(request, slug):
    
    (...생략...)

    return render(
        request,
        'blog/post_list.html',
        {
            'post_list': post_list,
            'categories': Category.objects.all(),
            'no_category_post_count': Post.objects.filter(category=None).count(),
            'category': category,
        }
    )
```

첫 번째 인수인 request는 response를 생성하는데 사용되는 객체이다. 위의 예시에서와 같이 `request`만 기입해주면 된다.

두 번째 인수인 template_name에는 사용할 템플릿의 full name을 기입한다. 장고에서 view가 model로부터 받은 데이터를 view에 선언한 class나 함수를 통해 가공하여 template에게 전달한다는 점을 생각해볼 때 브라우저에 html파일을 띄우는 역할을 한다고 생각해볼 수 있다.

위의 예시에서 세 번째 인수로 context를 사용했다. 이것은 템플릿으로 post_list.html을 사용하기 때문에 view의 PostList 클래스에서 context로 정의한 부분을 dictionary 형태로 직접 정의한 것이다.

세 번째 인수인 context는 선택적인 것으로 dictionary 형태를 가지는데, key값이 template에서 사용할 변수이름이 되고 value값은 view에서 key에 대응되는 값이다. 만약 dictionary의 값이 호출 가능한 경우 view는 template을 rendering하기 직전에 이것을 호출한다.

<br/>

## Optional arguments

### ◻ context

위에서 다루었기에 생략.

### ◻ content_type

[MIME type(미디어타입)](https://ko.wikipedia.org/wiki/%EB%AF%B8%EB%94%94%EC%96%B4_%ED%83%80%EC%9E%85)으로 default는 `text/html`이다.

### ◻ status

response에 대한 status code로 default는 200이다.

### ◻ using

template을 로딩하기 위해 사용할 template engine의 이름에 해당한다.

<br/>
<br/>

#### Reference

* https://docs.djangoproject.com/en/3.2/topics/http/shortcuts/#django.shortcuts.render