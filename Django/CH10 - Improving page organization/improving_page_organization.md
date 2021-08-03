# 페이지 구성 개선하기

## 1. 포스트 목록 페이지의 문제 파악하기

대표 이미지가 없는 포스트가 하나라도 있으면 포스트 목록 페이지에서 오류가 발생하는 문제가 존재했다.

첫 번째 포스트에서 기존에 존재했던 사진을 삭제했다. 삭제 후 적용해보면 이전과 동일한 'The 'head_image' attribute has no file associated with it.' 이라는 에러 메시지를 확인할 수 있다.

![image](https://user-images.githubusercontent.com/27791880/128020847-68e1d93e-b9b2-4f8d-8cb3-3d96ba7c40ae.png)

어디에서 에러가 발생한 것인지 친절하게 알려준다.

![image](https://user-images.githubusercontent.com/27791880/128020928-d72a4834-8885-415d-9710-fa6338f6aee0.png)

## 2. 템플릿 파일에서 if문 사용하기

p.231

포스트 상세 페이지에는 아직 수정을 하지 않았기 때문에 상세 페이지로 들어가면 오류가 발생한다.

![image](https://user-images.githubusercontent.com/27791880/128037986-7037b8c7-f3d0-487c-9089-502665cd2955.png)

p.232

첨부 파일 다운로드 버튼 만들기를 할 때 bootstrap 사이트에서 button 형식을 가져와서 사용하는데, 버튼은 기본적으로 `<button>` 속성을 사용하는데, 때로는 `<a>`나 `<input>`과 함께 사용하기도 한다. 그런데 `<a>` 요소를 사용하는 경우는 특정 이유가 있다. bootstrap 공식문서에는 어떻게 표현하는지 살펴보자.

> ### Button tags
> The .btn classes are designed to be used with the `<button>` element. However, you can also use these classes on `<a>` or `<input>` elements (though some browsers may apply a slightly different rendering).
>
>When using button classes on `<a>` elements that are used to trigger in-page functionality (like collapsing content), rather than linking to new pages or sections within the current page, these links should be given a role="button" to appropriately convey their purpose to assistive technologies such as screen readers.

보충 설명 필요!

Ref.

[Bootstrap Docs - Buttons](https://getbootstrap.com/docs/5.0/components/buttons/)
