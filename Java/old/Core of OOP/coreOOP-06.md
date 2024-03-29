# 06. 다형성

## 1) 다형성(polymorphism)이란?

* 하나의 코드가 여러 자료형으로 구현되어 실행되는 것을 의미한다.

* 같은 코드에서 여러 개의 다른 실행 결과가 출력되고 이것은 정보은닉, 상속과 더불어 객체지향 프로그래밍의 가장 큰 특징 중 하나이다.

* 잘만 활용한다면 유연하고 확장성 있는, 유지보수가 편리한 프로그램을 작성할 수 있게 된다.

## 2) 다형성 사용의 이유

* 상속과 메서드 재정의를 사용해서 확장성 있는 프로그램을 만들 수 있다.

* 다형성을 잘 활용하지 않는다면 각각의 조건에 맞게 if-else문이 길어지게 된다. 이것은 유지보수 측면에서 좋지 못하다.

* 상위 클래스에는 공통되는 부분을 제공하고 하위 클래스에는 각 클래스에 맞는 기능을 구현한다.

* 여러 하위 클래스들을 하나의 상위 클래스 타입으로 핸들링하는 것이 가능해진다.