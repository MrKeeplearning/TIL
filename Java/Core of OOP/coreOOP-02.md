# 2. 상속을 활용한 멤버십 클래스 구현

## protected 접근 제어자

* private 접근 제어자는 외부 클래스에서 아무도 접근할 수가 없다. 같은 클래스 내부에서만 사용한다.

* 상속관계에서는 상위 클래스의 속성을 하위 클래스가 받아서 자신의 클래스 내부에서 활용할 일이 많기 때문에 상위 클래스에서 private으로 멤버변수를 선언해놓으면 하위 클래스에서 해당 속성을 사용할 수가 없다.

* 이러한 문제에 대한 대안 `protected` 접근 제어자를 사용한다.

* `protected`가 붙은 변수, 메소드는 같은 패키지의 클래스 또는 해당 클래스를 상속받은 다른 패키지의 클래스에서만 접근 가능하다.