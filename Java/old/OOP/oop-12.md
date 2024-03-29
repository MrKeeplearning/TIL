# 12. 객체 자신을 가리키는 this

## this의 역할

* 인스턴스 자신의 메모리를 가리킨다.
* 생성자는 객체가 생성될 때 호출된다. 즉, new를 사용할 때 호출되는 것이라고 생각하면 된다.
* 그런데, 생성자 안에서 또 다른 생성자를 호출하는 일들이 있다. 같은 클래스에서 생성자가 여러 개 있을 때, 하나의 생성자에서 다른 생성자를 호출할 때 사용하는 키워드가 `this`이다.

## 생성자에서 다른 생성자를 호출하는 this
* 생성자가 여러 개인 경우에 생성자에서 다른 생성자를 호출하는 경우도 있다. 대부분 매개변수가 적은 생성자에서 매개변수가 더 많은 생성자를 호출하는 경우들이다.
* 생성자의 역할은 인스턴스를 초기화하는 역할이다.

```java
public class Person {

	String name;
	int age;
	
	public Person() {
		this("이름없음", 1);
	}
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
}
```
* 첫 번째 Person() 생성자에서는 다른 생성자인 두 번쨰 Person()을 this로 호출하는데, 호출이 완전히 종료되어야 인스턴스 생성이 끝나는 것이다.
* 첫 번째 Person() 생성자에서 this로 호출한 순간에는 아직 인스턴스가 생성된 상태가 아니기 때문에 this 앞에 다른 코드를 삽입하게 되면 오류가 발생한다.