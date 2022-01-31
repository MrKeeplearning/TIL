# 상속에서 클래스 생성과정과 형 변환

## 1. 하위 클래스가 생성되는 과정

* 하위 클래스를 생성하면 상위 클래스가 먼저 생성이 된다.

![image](https://user-images.githubusercontent.com/27791880/151649703-e5daef7e-cd16-4307-abbe-74ffbb73c9c0.png)

* 위와 같은 구조를 가질 때, `new VIPCustomer()`를 호출하면 `Customer()`가 먼저 호출된다.

* 클래스가 상속 받은 경우, 하위 클래스의 생성자에서는 반드시 상위 클래스의 생성자를 호출한다.

## 2. super 키워드

* 하위 클래스에서 가지는 상위 클래스에 대한 참조 값

* `super()`는 상위 클래스의 기본 생성자를 호출한다.

* 하위 클래스에서 명시적으로 상위 클래스의 생성자를 호출하지 않으면 `super()`가 호출된다. 이 때, 반드시 상위 클래스의 기본 생성자가 존재해야 한다.

* 상위 클래스에 기본 생성자가 없을 경우, 혹은 기본 생성자가 아닌 다른 생성자가 있을 경우 하위 클래스에서는 super를 사용해서 명시적으로 클래스의 생성자를 호출한다.

    ### 예시
    ```java
    // 디폴트 생성자 없애고 매개 변수가 있는 생성자 추가
    public Customer(int customerID, String customerName) {
        this.customerID = customerID;
        this.customerName = customerName;
            
        customerGrade = "SILVER";
        bonusRatio = 0.01;
        System.out.println("Customer(int, String) 생성자 호출");
    }
    ```
    ```java
    // super를 이용하여 상위 클래스의 생성자 명시적으로 호출
    public VIPCustomer(int customerID, String customerName) {
        super(customerID, customerName);
            
        customerGrade = "VIP";
        bonusRatio = 0.05;
        salesRatio = 0.1;
            
        System.out.println("VIPCustomer(int, String) 생성자 호출");
    }
    ```

* `super`는 생성된 상위 클래스 인스턴스의 참조 값을 가지므로 `super`를 이용하여 상위 클래스의 메서드나 멤버 변수에 접근할 수 있다.

## 3. 상속에서 인스턴스 메모리의 상태

![image](https://user-images.githubusercontent.com/27791880/151664980-d78fc0da-7fde-4d7d-a24a-599d6e6f1451.png)

* 항상 상위 클래스의 인스턴스가 먼저 생성되고, 그 뒤 하위 클래스의 인스턴스가 생성된다.

* 상위 클래스의 private 멤버 변수도 같이 만들어지지만, 접근이 불가능한 것일 뿐이다. 접근 문제를 해결하기 위해 protected라는 접근 제어자를 사용한다.

## 4. 형 변환(업 캐스팅)

* 상위 클래스로 변수를 선언하고 하위 클래스의 생성자로 인스턴스를 생성한다.

    ### 예시

    ```java
    Customer customerLee = new VIPCustomer();
    ```

    * 이러한 것이 가능한 이유는 `VIPCustomer()`를 생성하면 `Customer()`에 대한 것을 모두 내포하고 있기 때문이다.

    * VIPCustomer()는 Customer() 타입을 내포하고 있다고 한다.

* 상위 클래스 타입의 변수에 하위 클래스 변수가 대입.

    ### 예시

    ```java
    VIPCustomer vCustomer = new VIPCustomer();
    addCustomer(vCustomer);
    
    int addCustomer(Customer customer){

    }
    ```

    * `addCustomer`의 매개변수 타입을 보면 Customer 타입으로 받고 있다. 그럼에도 인자를 넘기는 것이 가능하다.

    * VIPCustomer이지만 Customer의 타입으로 형변환이 가능하다.

* 하위 클래스는 상위 클래스의 타입을 내포하고 있으므로 상위 클래스로의 묵시적 형 변환이 가능하다.

* 상속관계에 있는 모든 하위 클래스는 상위 클래스로 형변환(업캐스팅)이 가능하다. 그러나, 그 역은 성립하지 않는다.

## 5. 형 변환과 메모리

```java
Customer vc = new VIPCustomer();
```

* 여기서 VIPCustomer() 생성자에 의해서 VIPCustomer 클래스의 모든 멤버 변수에 대한 메모리는 생성되었지만, `vc`의 타입은 `Customer`이기 때문에 실제 접근 가능한 변수나 메서드는 `Customer` 타입의 것만 가능하다.


## ⭐ 핵심

* 하위 클래스가 생성될 때는 항상 상위 클래스가 먼저 생성된다.

* 상위 클래스에도 생성자가 없고 하위 클래스에도 생성자가 없다면 양쪽 모두 기본 생성자가 추가되면서 하위에서 super를 호출한다.

* 만약, 매개변수가 있는 생성자, 즉 컴파일러가 자동으로 호출해 줄 수 있는 생성자가 없다면 직접 명시적으로 호출해야 한다.