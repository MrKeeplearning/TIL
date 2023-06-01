# 03. 함수와 메서드

```java
int add(int num1, int num2) {
		
	int result;
	result = num1 + num2;
	return result;
}
```
* 함수는 이름, 매개변수, 반환값을 작성하는 부분이 중요
* 위의 예시에서 add 함수명 앞에 있는 int는 반환값의 자료형을 나타낸다.
* 반환을 하지 않는 경우에는 void를 선언해준다.
    ```java
    int void add(){}
    ```
<br/>

* 다른 프로그래밍 언어에서는 함수가 별도로 존재하지만 자바는 클래스르 떠나서 존재하는 것은 있을 수 없기 때문에 자바의 함수는 따로 존재하지 않고, 클래스 내부에 존재한다. 그리고 이렇게 클래스 내부에 존재하는 함수를 메소드(Method)라고 부른다.

## 매개변수와 인수
* 매개변수: 메소드에 입력으로 전달되는 값을 받는 변수.
* 인수: 메소드를 호출할 때, 흔히 괄호 안에 넣어 메소드에 전달해주는 입력값을 말한다.

<br/>
<br/>

### Ref.
[위키독스 점프 투 자바](https://wikidocs.net/225)