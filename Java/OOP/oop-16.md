# 16. static 변수

![image](https://user-images.githubusercontent.com/27791880/150740773-b4104fb6-7b7f-4351-9293-dbd66d1c2d14.png)

* static 변수는 동일한 클래스에서 생성된 여러 개의 인스턴스가 공유할 수 있는 변수이다.

* 여러 인스턴스가 공유하는 기준 값이 필요한 경우 사용한다.

* static은 '고정된'이란 의미를 가지고 있고 클래스에 고정된 멤버로서 객체를 생성하지 않고 사용할 수 있는 필드와 메소드를 말한다(각각 정적필드, 정적메소드라고 부른다).

* 정적 멤버는 객체(인스턴스)에 소속된 멤버가 아니고 클래스에 속한 멤버이기 때문에 클래스 멤버라고 한다.

## static 변수 선언과 사용

e.g.,
```java
static int serialNum;
```

*  하드디스크에 프로그램이 있고, 프로그램을 구동시키면 프로그램은 메모리에 올라가게 된다. 메모리에 올라갔을 때의 상태를 프로세스라고 한다.

* 실제로 구동이 될 때는 쓰레드(thread)의 상태로 돌아가게 된다.

* 프로그램이 메모리에 로드가 될 때 프로그램은 크게 두 가지 영역을 가지게 된다. 하나는 code 영역이고, 또 하나는 data 영역이다.

* data 영역은 static 영역, 혹은 상수 영역이라고 표현하기도 한다.

* 프로그램이 메모리에 로드가 될 때 처음부터 메모리를 잡는 데이터들이 존재한다. 예를 들어서, 상수 같은 literal등이 constant 영역에 잡힌다.

* 코드 영역에는 명령어 집합(instruction set)이 들어가서 한 줄 한 줄 실행이 된다.

* 인스턴스가 생성될 때마다 사용하는 메모리는 힙 메모리라고 한다. 인스턴스가 생성되었다가 인스턴스가 사라지는 순간 Garbage Collector에 의해서 메모리가 수거된다.

* static 변수는 처음에 프로그램이 프로세스가 되어 메모리에 로딩되는 순간 데이터 영역에 잡히고, 프로그램이 모두 종료되어 메모리에서 unload되는 순간 소멸된다.

## static 변수 활용

```java
// Employee.java
package ch16;

public class Employee {
	
	public static int serialNum = 1000;
	
	private int employeeId;
	private String employeeName;
	private String department;
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
}
```

```java
// EmployeeTest.java
package ch16;

public class EmployeeTest {

	public static void main(String[] args) {
		Employee employeeLee = new Employee();
		employeeLee.setEmployeeName("이순신");
		System.out.println(employeeLee.serialNum);
		
		Employee employeeKim = new Employee();
		employeeKim.setEmployeeName("김유신");
		employeeKim.serialNum++;
		
		System.out.println(employeeLee.serialNum);
		System.out.println(employeeKim.serialNum);
		
	}
}
```
```java
// 결과
1000
1001
1001
```

* `serialNum`은 employeeKim에서만 증가시켰음에도 employeeLee에서도 증가된 값을 가진다. 이를 통해서 두 개의 인스턴스가 하나의 메모리를 공유한다는 것을 확인할 수가 있다.

![image](https://user-images.githubusercontent.com/27791880/150988398-f45b50a9-5c23-4550-ba04-bc84bf950614.png)

(`studentLee`는 `employeeLee`로, `studentSon`은 `employeeKim`으로 생각해보자.)

* `employeeLee`와 `employeeKim`은 main함수 내부에 Employee 타입으로 선언된 지역변수이다. 

* 이러한 지역변수는 스택 메모리에 선언된다.

* `employeeLee`와 `employeeKim`으로 인해 참조되는 인스턴스는 힙 메모리에 선언된다.

* 인스턴스들이 참조할 수 있는 static 변수는 공유메모리에 담겨 있다.

* 자바 뿐만이 아니라 대부분의 프로그램 언어들도 3가지 영역의 메모리들을 사용하게 된다.

* 동적 메모리(힙)는 필요할 때 할당받아 사용하고, 모두 사용을 했다면 해당 메모리를 해지(free, release)시킨다.

```java
// EmployeeTest.java

package ch16;

public class EmployeeTest {

	public static void main(String[] args) {
		
		Employee employeeLee = new Employee();
		employeeLee.setEmployeeName("이순신");
		
		System.out.println(employeeLee.serialNum);
		
		Employee employeeKim = new Employee();
		employeeKim.setEmployeeName("김유신");
		
		System.out.println(employeeLee.getEmployeeName() + "님의 사번은 " + employeeLee.getEmployeeId());
		System.out.println(employeeKim.getEmployeeName() + "님의 사번은 " + employeeKim.getEmployeeId());

	}

}
```
* 위와 같이 코드를 작성하게 되면 `employeeLee.serialNum`에 노란색 밑줄이 그어진다.

* `serialNum`은 클래스 변수이다. 따라서, 인스턴스의 생성과는 상관없이 사용할 수가 있다.

* static 변수는 프로그램 실행과 동시에 메모리에 이미 load된다. 따라서 클래스 이름으로 참조해서 많이 사용한다.

* `Employee.serialNum`으로 고치게 되면 노란색 밑줄은 사라진다.