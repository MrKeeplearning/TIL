# 08. 다운 캐스팅과 instanceof

## 1) 다운 캐스팅

* 인스턴스가 원래 `Human`클래스(하위 클래스)였는데, `Animal`클래스(상위 클래스)로 바뀌었다가 다시 `Human`클래스로 돌아오는 것.

* 이처럼 업캐스팅된 클래스르 다시 원래의 타입으로 형 변환하는 것을 다운캐스팅이라고 한다.

* Object클래스로 반환되는 경우들이 있다. Object 클래스는 모든 클래스의 최상위 클래스이고, 그렇다보면 모든 클래스가 Object 클래스로 형변환될 수 있다. 이 때 Object 클래스로 업캐스팅된 것들이 원래 클래스로 돌아갈 때 다운 캐스팅을 활용한다.

* 업캐스팅과 달리 다운캐스팅은 명시적으로 해야 한다.

    ```java
    Customer vc = new VIPCustomer();              //묵시적
    VIPCustomer vCustomer = (VIPCustomer)vc;      //명시적
    ```

## 2) instanceof를 이용하여 인스턴스의 형 체크

* 상위 클래스인 Customer가 있고 이것을 확장한 하위 클래스인 GoldCustomer와 VIPCustomer 클래스가 있다고 생각해보자.

* customerE는 GoldCustomer 타입인데, 이를 VIPCustomer 타입으로 캐스팅하려고 한다. 이 경우 컴파일 에러는 발생하지 않는다. 왜냐하면 이클립스가 인스턴스의 타입까지 확인할 수는 없기 때문이다. 그러나 실행을 하면 customerE는 원래 GoldCustomer타입이기 때문에 에러가 발생한다.

```java
import java.util.ArrayList;

public class CustomerTest {
	public static void main(String[] args) {		
		ArrayList<Customer> customerList = new ArrayList<>();
		
		Customer customerT = new Customer(10010, "Tomas");
		Customer customerJ = new Customer(10020, "James");
		Customer customerE = new GoldCustomer(10030, "Edward");
		Customer customerP = new GoldCustomer(10040, "Percy");
		Customer customerK = new VIPCustomer(10050, "Kim", 456);
		
		customerList.add(customerT);
		customerList.add(customerJ);
		customerList.add(customerE);
		customerList.add(customerP);
		customerList.add(customerK);
		
		VIPCustomer vc = (VIPCustomer)customerE;
	}
}
```

👉 에러코드

```java
Exception in thread "main" java.lang.ClassCastException: class ch08.GoldCustomer cannot be cast to class ch08.VIPCustomer (ch08.GoldCustomer and ch08.VIPCustomer are in unnamed module of loader 'app')
	at ch08.CustomerTest.main(CustomerTest.java:23)
```

* 이러한 에러 발생을 방지하기 위해 `instanceof`를 활용한다. customerE가 VIPCustomer 타입의 인스턴스인지 묻고 해당 조건이 true인 경우에만 변환을 하도록 둔다.

```java
if (customerE instanceof VIPCustomer) {
    VIPCustomer vc = (VIPCustomer)customerE;
    System.out.println(customerE.showCustomerInfo());
}
```
