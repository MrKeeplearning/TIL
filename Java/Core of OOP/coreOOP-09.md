# 09. 추상 클래스 구현하기

## 1) 추상 클래스란?

* 구현 코드는 없고 메서드의 선언만 있는 추상 메서드가 포함된 클래스

* `abstract` 예약어를 사용한다.

* 추상 클래스는 new를 할 수가 없다. 즉, 인스턴스화 할 수 없다. 따라서 인스턴스를 생성해야 한다면 추상 클래스가 아닌 하위 클래스를 통해서 생성해야 한다.

## 2) 추상 클래스 구현

* 메서드에 구현 코드가 없다면 `abstract`를 선언한다. 그리고 `abstract`가 선언된 메서드를 가진 클래스 또한 `abstract`가 선언되어야 한다.

* 추상 클래스는 공통으로 쓸 메서드만 구현해둔다. 추상 클래스에서 구현하지 못한 추상 메서드들은 하위 클래스에게 책임을 위임한다.

* 하위 클래스에서는 추상 클래스에서 구현하지 못한 추상 메서드들을 반드시 구현해야 한다. 만약 **모든 추상 메서드들을** 구현하지 않는다면 하위 클래스 또한 `abstract`를 선언해야 한다.

* 하지만, 하위 클래스에서 추상 클래스의 모든 메서드들을 구현했음에도 `abstract`로 클래스를 선언하는 경우도 있다. 이 때는 상속만을 위해서 사용하는 클래스이다. 또한 모두 구현되어 있더라도 abstract 키워드를 사용한다면 new를 통해서 인스턴스를 생성할 수 없다.

* 추상 클래스 내부에 구현된 메서드는 필요에 따라서 하위 클래스에서 재정의할 수도 있다.

## 정리

* 추상 클래스는 상속을 하기 위해서 만든 클래스이다.

* 추상 클래스 내부에 구현하는 메서드들은 일부는 구현을 할 수도 있고, 일부는 구현하지 않을 수도 있다. 어떤 경우는 구현을 모두 하지 않을 수도 있다.

* 공통으로 하위 클래스에서도 사용하는 메서드는 상위 클래스에서 코드를 구현해둔다.