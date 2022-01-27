# 18. static 응용 - 싱글톤 패턴(singleton pattern)

* 디자인 패턴은 어떻게 하면 효율적으로 객체지향을 구현해서 유지보수가 쉽고 좀 더 객체지향에 적합하게 코딩을 할 것인가를 23가지 정도의 패턴으로 정리해놓은 것이다.
* 기존의 사례를 바탕으로 패턴을 만들었고, 반드시 지켜야 한다는 rule은 아니지만, 유지보수와 확장성 면에서는 지키는 것이 권장된다.
* 여러 가지의 디자인 패턴 중에 하나가 싱글톤 패턴이다.

## 싱글톤 패턴

* 대부분의 경우는 하나의 클래스에 여러 개의 인스턴스가 존재하는 경우가 많다. 그런데 여러 개의 인스턴스를 생성했을 때 문제가 되는 상황이 있다.

* 예를 들어 날짜가 있다. Timezone에 따라서 제공되는 날짜는 여러 개를 가질 수가 없다. 대한민국의 표준시인 UTC+9에 의한 시간은 day, month, year, 시분초가 어디에 있던지 동일해야 한다. 따라서 시간 인스턴스는 여러 개일 수가 없다. 

* 회사 내부에 사원은 여러 명일 수 있지만 회사라는 객체 하나는 유일하다.

* 이러한 경우에 싱글톤 패턴을 적용한다.

```java
package ch18;

public class Company {
	private static Company instance = new Company();
	
	private Company() {}
	
	public static Company getInstance() {
		if (instance == null) {
			instance = new Company();
		}
		return instance;
	}
}
```

* 싱글톤 패턴에서는 외부에서 마음대로 Company 인스턴스를 생성할 수 없도록 생성자를 하나 제공한다.

* 위와 같이 생성자를 하나 만들게 되면 컴파일러는 생성자를 제공해주지 않는다(기본 생성자 제공 x).

* 싱글톤 패턴은 프로그램에서 인스턴스가 단 하나만 생성되어야 하는 경우 사용하는 디자인 패턴이라고 했다. 따라서, 유일한 private 인스턴스를 클래스 내부에 생성한다.

* 유일한 인스턴스에 외부에서 접근해서 활용할 수 있도록 getter 메소드를 하나 만들어준다.

### getInstance()
* 만약 `getInstance()`를 일반 메서드로 선언을 했다면 외부에서 `getInstance()`를 호출하기 위해 인스턴스를 하나 생성해야 한다. 그런데 인스턴스를 만들지 않고 바로 가져와서 사용할 것이기 때문에(싱글톤 패턴) static 메서드로 제공되어야 한다.
* 외부에서 static 메서드를 사용할 때는 아래와 같이 클래스 이름을 활용해서 사용한다.

```java
package ch18;

public class CompanyTest {

	public static void main(String[] args) {
		Company company1 = Company.getInstance();
		Company company2 = Company.getInstance();
	}
}
```

