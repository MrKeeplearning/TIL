# 23. 객체 배열을 구현한 클래스: ArrayList

* 기존의 배열 선언과 사용 방식은 배열의 길이를 정하고 요소의 개수가 배열의 길이보다 커지면 배열을 재할당하고 복사해야 했다. 따라서 배열의 요소를 추가하거나 삭제하게 되면 다른 요소들의 이동에 대한 구현을 해야 한다.

* ArrayList는 객체 배열을 좀 더 효율적으로 관리하기 위해서 자바에서 제공하는 클래스이다.

## ArrayList의 주요 메서드

![image](https://user-images.githubusercontent.com/27791880/151552369-932cee71-57f5-4664-88ab-ab8baa808d4a.png)

## ArrayList의 사용법

* ArrayList를 선언한 다음에 제너릭(Generic)이라고 해서 JDK 1.5에서부터 제공되는 것으로 어떤 객체를 넣을 것인지 지정한다.

### 예시
```java
ArrayList<Book> library = new ArrayList<Book>();

또는

ArrayList<Book> library = new ArrayList<>();
```

* 최근에는 처음 제너릭에 데이터 타입을 명시했다면 new 에서 다시 명시할 필요가 없어졌다.

### Generic이란?

* 제너릭은 데이터의 타입을 일반화(generalize)하는 것을 의미한다.

* 클래스나 메소드에서 사용할 내부 데이터 타입을 컴파일 시에 미리 지정하는 방법으로 컴파일 시에 미리 타입 검사를 수행하면 다음과 같은 장점을 가지게 된다.

    * 클래스나 메소드 내부에서 사용되는 객체의 타입 안정성을 높일 수 있다.

    * 반환값에 대한 타입 변환 및 타입 검사에 들어가는 노력을 줄일 수 있다.



### Reference
* [TCPschool.com - 제너릭의 개념](http://www.tcpschool.com/java/java_generic_concept)