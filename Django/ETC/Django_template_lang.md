# Django Template Language

> Django Documentation의 ['The Django template language'](https://docs.djangoproject.com/en/3.2/ref/templates/language/)를 번역한 문서입니다. 모든 내용을 번역해서 담은 것은 아니며, 개인적으로 학습하며 필요했던 부분 위주로 내용을 담았습니다.

<br/>

## Templates

* 하나의 텍스트 파일이고, 따라서 어떤 텍스트 기반 형식이든 생성이 가능하다.
* 템플릿에는 템플릿이 평가될 때 값으로 대체되는 변수와 템플릿의 logic을 제어할 태그가 포함된다.

<br/>

## 변수

* 변수는 `{{ variable }}`과 같은 형태를 가진다.
* 템플릿 엔진이 변수를 만나면 해당 변수에 대응되는 값이 view에 있는지 확인하고 대응되는 값이 있을 때는 결과값으로 바꾼다.
* 밑줄로 시작할 수 없고, 숫자를 변수로 사용하는 것도 안된다.
* 공백이나 구두점을 변수로 사용하는 것도 불가능하다.
* 변수에 사용되는 점(`.`)은 변수의 속성에 접근하기 위해 사용된다.
  
```html
{% extends "base_generic.html" %}

{% block title %}{{ section.title }}{% endblock %}

{% block content %}
<h1>{{ section.title }}</h1>

{% for story in story_list %}
<h2>
  <a href="{{ story.get_absolute_url }}">
    {{ story.headline|upper }}
  </a>
</h2>
<p>{{ story.tease|truncatewords:"100" }}</p>
{% endfor %}
{% endblock %}
```

위의 예시에서 `{{section.title}}`은 section 객체의 title 속성으로 대체된다. 만약, 변수에 존재하지 않는 변수를 사용했을 경우 template system은 `' '`(빈 문자열)로 설정된 `string_if_invalid`를 default로 삽입한다.

<br/>

## 필터

필터를 적용할 때는 파이프(`|`)를 사용한다. `name`이라는 변수에 담긴 값을 소문자로 전환하고 싶다면 pipe를 사용하여 `{{name|lower}}` 같이 표현하는 것이 가능하다.

필터는 연달아 적용하는 것이 가능하여 하나의 필터를 거친 결과값이 다음 필터의 입력값이 되어 또 다른 결과값을 얻을 수 있다. 예를 들어 `{{text|escape|linebreaks}}`는 text에 담긴 내용을 escape한 뒤 줄바꿈을 `<p>` 태그로 전환하는 자주사용되는 표현이다.

일부 필터는 인수를 사용할 수도 있다. `{{p.content | truncatewords:45}}`라는 표현이 있다면 `p.content`에 담긴 내용의 앞에서부터 45개 단어만 출력한다. 단어 수가 아닌 글자 수로 나누고 싶다면 `truncatechars`를 사용한다.

공백이 포함된 인수를 사용할 때는 따옴표로 묶어주어야 한다. `{{list | join:", "}}`과 같은 표현을 사용하면 list에 담긴 내용을 `,`와 공백을 사용하여 결합할 수 있다.

Django에는 이보다 더 많은 내장 템플릿 필터를 제공한다.

참고: https://docs.djangoproject.com/en/3.2/ref/templates/builtins/#ref-templates-builtins-filters

<br/>

## 태그

태그는 변수보다 좀 더 복잡하다. Output으로 텍스트를 생성하는 경우도 있고, loop 또는 logic에 따라서 flow를 제어하는 경우도 있으며, 이후 변수에서 사용할 외부 정보를 미리 템플릿에 적재하는 경우도 있다.

일부 태그는 시작과 끝을 명시해주어야 하는 경우도 있다(i.e. `{% tag %} ... tag contents ... {% endtag %}`).

장고는 약 24개의 내장 템플릿 태그를 제공한다. 자세한 내용은 [이곳](https://docs.djangoproject.com/en/3.2/ref/templates/builtins/#ref-templates-builtins-tags)을 참고하기.

일반적으로 사용되는 몇 가지 태그에 대해서 알아보자.

### ◻ for

배열의 각 항목을 반복한다.

```html
<ul>
{% for athlete in athlete_list %}
    <li>{{ athlete.name }}</li>
{% endfor %}
</ul>
```

### ◻ if, elif, and else

변수가 참이라면 해당 블록의 내용이 표시된다.

```html
{% if athlete_list %}
    Number of athletes: {{ athlete_list|length }}
{% elif athlete_in_locker_room_list %}
    Athletes should be out of the locker room soon!
{% else %}
    No athletes.
{% endif %}
```

위에서 만약 `athlete_list`가 비어 있지 않다면 `{{ athlete_list|length }}`에 값이 채워져 보여진다. elif의 `athlete_in_locker_room_list`가 비어 있지 않다면 elif문 바로 하단의 문장이 보여지게 된다. 그리고 두 개의 list가 모두 비어 있다면 'No athletes.'를 출력하게 된다.

if태그에는 filter와 다양한 연산자를 적용하는 것도 가능하다.

```html
{% if athlete_list|length > 1 %}
   Team: {% for athlete in athlete_list %} ... {% endfor %}
{% else %}
   Athlete: {{ athlete_list.0.name }}
{% endif %}
```

[length](https://docs.djangoproject.com/en/3.2/ref/templates/builtins/#std:templatefilter-length)는 예외이지만 대부분의 템플릿 필터는 문자열을 반환하기 때문에 필터를 사용한 수학적 비교는 예상했던 것과 다르게 작동할 수 있다.

<br/>

## Comments

한 줄 주석 처리가 필요할 때는 `{# ... #}`과 같은 방식으로 처리한다. 이 때 줄바꿈은 허용되지 않는다.

만약 여러 줄을 주석 처리해야 한다면 comment 태그를 활용한다.

### 👉 comment tag 사용 예시

```html
<p>Rendered text with {{ pub_date|date:"c" }}</p>
{% comment "Optional note" %}
    <p>Commented out text with {{ create_date|date:"c" }}</p>
{% endcomment %}
```

위와 같이 comment tag를 사용하면 comment tag 사이에 있는 모든 내용을 무시하게 된다. 필요하다면 첫 번째 태그에 메모(`Optional note`라고 표시한 부분)를 남길 수도 있다. Optional note는 해당 코드가 왜 비활성화되었는지 문서화할 때 유용하게 활용할 수 있다.

참고로, comment tag는 중첩해서 사용할 수 없다.

<br/>

## Template inheritance

장고 템플릿 엔진의 가장 강력하면서도 복잡한 부분이 템플릿 상속이다. 템플릿 상속을 사용하면 사이트의 모든 공통 요소를 포함하고, child 템플릿이 override(재정의)할 수 있는 block을 정의하는 base template을 작성할 수 있다.

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="style.css">
    <title>{% block title %}My amazing site{% endblock %}</title>
</head>

<body>
    <div id="sidebar">
        {% block sidebar %}
        <ul>
            <li><a href="/">Home</a></li>
            <li><a href="/blog/">Blog</a></li>
        </ul>
        {% endblock %}
    </div>

    <div id="content">
        {% block content %}{% endblock %}
    </div>
</body>
</html>
```

위의 파일을 이제 **base.html**이라고 부르기로 한다. 비어 있는 블록에 컨텐츠를 채우는 것이 child template이 해야 할 일이다.

위의 예시에서 block 태그는 child template이 채울 수 있는 세 개의 블록을 정의하고 있다. block 태그가 하는 모든 일은 템플릿 엔진에 child template이 템플릿의 해당 부분을 override할 수 있다는 것을 알리는 것이다.

child template은 다음과 같이 생겼다고 해보자.

```html
{% extends "base.html" %}

{% block title %}My amazing blog{% endblock %}

{% block content %}
{% for entry in blog_entries %}
    <h2>{{ entry.title }}</h2>
    <p>{{ entry.body }}</p>
{% endfor %}
{% endblock %}
```

위에서 핵심은 extends 태그이다. 이 태그는 현재 템플릿이 다른 템플릿을 extends한다고 template engine에 알리는 역할을 한다. 따라서 template engine은 위의 템플릿에 접근했을 때 가장 먼저 부모 템플릿인 base.html부터 찾게 된다.

이 때, template engine은 base.html에 있는 3개의 block 태그를 확인하게 되고, 해당 블록을 자식 템플릿의 내용으로 바꾼다. `blog_entries`의 값에 따라서 최종 결과는 다음과 같이 나오게 된다.

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="style.css">
    <title>My amazing blog</title>
</head>

<body>
    <div id="sidebar">
        <ul>
            <li><a href="/">Home</a></li>
            <li><a href="/blog/">Blog</a></li>
        </ul>
    </div>

    <div id="content">
        <h2>Entry one</h2>
        <p>This is my first entry.</p>

        <h2>Entry two</h2>
        <p>This is my second entry.</p>
    </div>
</body>
</html>
```

child template이 sidebar 블록을 정의하지 않았기 때문에 부모 템플릿의 값이 대신 사용된다. 이처럼 부모 템플릿에서 `{% block %}`태그 내의 컨텐츠는 항상 대체 수단으로 사용된다.

상속은 필요한 단계만큼 사용할 수 있다. 일반적으로는 3단계 접근 방식을 활용한다.

1. 만드려는 사이트의 전반적인 외양과 느낌을 유지해주는 base.html을 생성한다.
2. 사이트의 각 섹션에 해당하는 base_SECTIONNAME.html을 생성한다. 예를 들어, base_news.html 또는 base_sports.html 등이 있을 수 있다. 이러한 템플릿들은 모두 base.html을 확장한 것이고 각 섹션만의 스타일과 디자인을 가지고 있다.
3. 뉴스 기사나 블로그의 랜딩페이지와 같이 페이지 유형별로 각각의 템플릿을 생성한다. 이 템플릿들은 적절한 섹션 템플릿을 확장한다(extends).

이러한 방식을 사용하면 코드 재사용성을 극대화하고 공유 컨텐츠 영역에 아이템을 추가할 때 도움이 된다.

### 💡 상속을 사용할 때 몇 가지 팁

* 템플릿에서 `{% extends %}`를 사용한다면 해당 태그는 템플릿의 첫 번째 태그여야 한다. 그렇지 않다면 템플릿 상속을 사용할 수 없다.
* base 템플릿에 `{% block %}`태그가 많을수록 좋다. 자식 템플릿은 모든 부모 템플릿을 정의할 필요가 없다. 따라서 생성한 여러 개의 블록에 적당한 default 값을 채워 넣은 뒤 나중에 필요한 블록만 정의하면 된다. Hook이 적은 것보다 많은 것이 더 낫다.
* 여러 템플릿에서 같은 컨텐츠를 반복해서 사용하고 있다면 해당 컨텐츠를 부모 템플릿의 `{% block %}`으로 이동시키는 것이 더 나을 수도 있다.
* 부모 템플릿으로부터 모든 내용을 override하지 않고 몇 가지 컨텐츠만 추가할 때는 `{{ block.super }}`를 사용한다. `{{ block.super }}`를 사용해서 삽입한 데이터는 자동으로 이스케이프되지 않는다. 왜냐하면, 부모 템플릿에서 필요하다면 이미 이스케이프되어 있을 것이기 때문이다.
* **Extending an overridden template**
  * template loader를 구성하면 override를 하는 것과 동시에 `{% extends %}`를 활용하여 template을 확장할 수가 있다. 이러한 방식은 전체 템플릿을 다시 구현할 필요 없이 필요한 부분만 customization하는 것이 가능하다.
  * 위의 방식을 활용해서 예를 들어 `admin/base_site.html`템플릿에 custom logo를 적용하는 것이 가능하다.

    ```html
    <!-- templates/admin/base_site.html -->

    {% extends "admin/base_site.html" %}

    {% block branding %}
        <img src="link/to/logo.png" alt="logo">
        {{ block.super }}
    {% endblock %}
    ```
  * 새로운 템플릿은 현재 override되고 있는 것과 동일한 `admin/base_site.html`을 확장한다.
  * 템플릿은 branding block만 교체를 하고, custom logo를 추가했으며, 이전 컨텐츠 유지를 위해서 `block.super`를 사용했다.
  * 템플릿의 나머지 부분은 `admin/base_site.html`에서 변경되지 않고 상속된다.
  * 이러한 테크닉은 템플릿 로더가 `extends` 태그를 확인할 때, 이미 로드된 override template(`templates/admin/base_site.html`)을 고려하지 않기 때문에 가능한 것이다.
  * Ref. [Extending an overridden template](https://docs.djangoproject.com/en/3.2/howto/overriding-templates/#extending-an-overridden-template)

* 템플릿 태그 `as` 구문을 사용하여 `{% block %}` 외부에서 생성된 변수는 block 안에서 사용하는 것이 불가능하다. 예를 들어 아래의 템플릿은 어떠한 것도 렌더링하지 않는다.

    ```html
    {% translate "Title" as title %}
    {% block content %}{{ title }}{% endblock %}
    ```

* 가독성을 높이기 위해서 `{% endblock %}`에도 선택적으로 이름을 지정할 수 있다.

    ```html
    {% block content %}
    ...
    {% endblock content %}
    ```

    템플릿의 내용이 많아졌을 때는 이렇게 `{% endblock %}`에도 이름을 지정함으로써 어떤 블록을 닫는 것인지 쉽게 파악할 수 있다.

마지막으로 동일한 템플릿에 같은 이름으로 여러 개의 block 태그를 정의할 수 없다. 이러한 제한이 있는 이유는 블록 태그가 양방향으로 작동하기 때문이다. 블록 태그는 단순히 무언가를 채워넣을 구멍만 제공하는 것이 아니라, 부모 템플릿의 구멍에 채워 넣을 컨텐츠를 정의하기도 한다. 만약 하나의 템플릿의 두 개의 동일한 이름의 block 태그가 있다면 부모 템플릿은 어떤 블록의 내용을 선택해야 할지 알 수 없다.

<br/>

## Automatic HTML escaping

템플릿에서 HTML을 생성할 때 HTML의 결과에 영향을 줄 수 있는 문자가 변수에 포함될 수 있는 위험이 항상 존재한다. 예를 들어보자.

```HTML
Hello, {{ name }}
```

위의 HTML 코드는 사용자의 이름을 표시하는 방법으로 문제가 없어보일 수도 있지만, 만약 사용자가 아래와 같이 입력값을 주면 문제가 생길 수도 있다.

```HTML
<script>alert('hello')</script>
```

위와 같은 코드를 입력값으로 주게 되면 템플릿은 다음과 같이 렌더링하게 된다.

```HTML
Hello, <script>alert('hello')</script>
```

이러한 경우 브라우저는 자바스크립트 팝업 경고창을 띄우게 된다.

사용자가 제출한 데이터를 맹목적으로 신뢰하고 웹페이지에 직접 삽입해서는 안된다. 왜냐하면 악의적인 사용자가 위에서 확인했던 바와 같은 취약점을 가지고 나쁜 일을 할 가능성이 있기 때문이다. 이러한 유형의 취약점 공격을 XSS(Cross Site Scripting) 공격이라고 한다.

이러한 문제를 방지하기 위해서는 두 가지 옵션이 존재한다.

1. 잠재적으로 위험할 수 있는 HTML 문자를 무해한 것으로 변환해주는 [escape filter](https://docs.djangoproject.com/en/3.2/ref/templates/builtins/#std:templatefilter-escape)를 통해서 신뢰할 수 없는 변수를 실행할 수 있다.
2. Django의 automatic HTML escaping을 활용할 수 있다.

기본적으로 장고에서 모든 템플릿은 모든 변수 태그의 출력을 자동으로 이스케이핑한다. 특히, 아래 5개의 문자는 다음과 같이 이스케이핑이 된다.

* `<`는 `&lt;`로 변환됨.
* `>`는 `&gt;`로 변환됨.
* `'`(작은 따옴표)는 `&#x27;`로 변환됨.
* `"`(큰 따옴표)는 `&quot;`로 변환됨.
* `&`는 `&amp;`로 변환됨.

다시 말하지만, 이러한 automatic HTML escaping은 기본적으로 켜져 있는 상태이다. 따라서 장고의 템플릿 시스템을 이용 중이라면 관련된 위협으로부터 보호되고 있는 것이다.

<br/>

### 끄는 방법

---

#### 💡 개별 변수의 경우

개별 변수에 대해서 자동 이스케이프를 비활성화하려면 safe 필터를 사용한다.

```html
This will be escaped: {{ data }}
This will not be escaped: {{ data|safe }}
```

만약 data 변수에 `<b>`가 담겨 있다면 다음과 같이 출력된다.

```html
This will be escaped: &lt;b&gt;
This will not be escaped: <b>
```

#### 💡 템플릿 블록의 경우

템플릿의 자동 이스케이프를 제어하기 위해서는 다음과 같이 템플릿(또는 템플릿의 특정 섹션)을 autoescape 태그로 묶어준다.

```html
{% autoescape off %}
    Hello {{ name }}
{% endautoescape %}
```

autoescape태그는 인수로 on 또는 off를 취한다. 자동 이스케이핑이 비활성화되어 있을 때 강제로 활성화시킬 수도 있다. 아래는 관련된 예제이다.

```html
Auto-escaping is on by default. Hello {{ name }}

{% autoescape off %}
    This will not be auto-escaped: {{ data }}.

    Nor this: {{ other_data }}
    {% autoescape on %}
        Auto-escaping applies again: {{ name }}
    {% endautoescape %}
{% endautoescape %}
```

autoescape 태그는 모든 block 태그와 마찬가지로 include 태그를 통해서 포함된 템플릿 뿐만 아니라 현재 템플릿을 확장한 템플릿에도 적용된다. 예시를 확인해보자.

```html
<!-- base.html -->

{% autoescape off %}
<h1>{% block title %}{% endblock %}</h1>
{% block content %}
{% endblock %}
{% endautoescape %}
```

```html
<!-- child.html -->

{% extends "base.html" %}
{% block title %}This &amp; that{% endblock %}
{% block content %}{{ greeting }}{% endblock %}
```

자동 이스케이프가 base template에서 꺼져 있기 때문에 자식 템플릿에서도 꺼진 상태이다. 따라서, `greeting` 변수에 문자열로 `<b>Hello!</b>`이 포함되어 있는 경우 다음과 같이 렌더링된 결과물이 생성된다.

```
<h1>This &amp; that</h1>
<b>Hello!</b>
```

<br/>

### 참고

---

템플릿 작성자는 auto-escaping에 대해서 크게 걱정할 필요는 없다. 다만, 파이썬 측의 개발자(views를 작성하거나 custom filters를 작성하는 사람)는 데이터가 escape되면 안 되는 경우에 대해서 생각하고 데이터를 적절하게 표시해야 템플릿이 제대로 작동할 수 있다.

만약 여러분이 auto-escaping이 활성화되어 있는지 확실하지 않은 상황에서 템플릿을 작성하는 경우 escaping이 필요한 변수에 대해서 escape filter를 적용해라. Auto-escaping이 활성화되어 있는 경우, escape filter를 적용해서 이스케이핑이 두 번 일어나는 부분에 대해 문제가 될 것은 없다. escape 필터는 auto-escaping이 된 변수에 대해서 영향을 미치지 않는다.

<br/>

## 메소드 호출 접근하기

object에 연결된 대부분의 메소드 호출은 템플릿 내에서도 사용이 가능하다. 이것은 템플릿이 view에서 전달된 클래스 속성(예: field names) 및 변수 이상으로 엑세스할 수 있다는 것을 의미한다. 예를 들어 장고 ORM은 외래 키와 관련된 객체 컬렉션(collection of objects)을 찾기 위한 `entry_set` 구문을 제공한다. 따라서 "task"라는 모델에 대한 외래 키 관계가 있는 "comment"라는 모델이 주어지면 아래와 같이 모든 comment를 돌아볼 수 있다.

```html
{% for comment in task.comment_set.all %}
    {{ comment }}
{% endfor %}
```

마찬가지로, 쿼리셋은 포함된 object의 수를 계산하는 count() 메소드를 제공한다. 따라서 `{{ task.comment_set.all.count }}`를 사용해서 현재 task에 관련된 모든 comment 수를 확인할 수 있다.

자신의 모델에 명시적으로 정의한 메소드에 엑세스하는 것도 가능하다.

```python
# models.py

class Task(models.Model):
    def foo(self):
        return "bar"
```

```html
<!-- template.html -->

{{ task.foo }}
```

장고는 템플릿 언어에서 사용할 수 있는 logic processing의 양을 의도적으로 제한하기 때문에 템플릿 내에서 엑세스되는 메서드 호출에 인수를 전달할 수 없다. 데이터는 view에서 계산된 뒤에 실제로 보여지기 위해서 template으로 전달되어야 한다.
