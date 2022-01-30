# 04. 메서드 재정의하기(overriding)

## 1) Method Overriding

* 상위 클래스에 정의된 메서드의 구현 내용이 하위 클래스에서 구현할 내용과 맞지 않는 경우 하위 클래스에서 동일한 이름의 메서드를 재정의할 수 있다.

* **주의할 점**: Overriding을 할 때는 반환값, 메소드 이름, 매개변수의 타입과 개수가 같아야 한다.

## 2) Annotation

* 원래 '주석'이라는 의미이다.

* 어노테이션은 컴파일러에게 특별한 정보를 제공해주는 역할을 한다.

![image](https://user-images.githubusercontent.com/27791880/151687141-c948af0b-1f46-48f9-9430-e64b9e4fbd6d.png)

## 3) 형 변환과 오버라이딩 메서드 호출

```java
Customer vc = new VIPCustomer();
```

* `vc`의 변수 타입은 Customer이지만 인스턴스 타입은 VIPCustomer이다.

* 자바에서는 가상메서드의 원리에 따라 항상 인스턴스의 메서드가 호출된다. 자바의 모든 메서드는 가상 메서드이다.