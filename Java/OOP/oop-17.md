# 17. static 메서드의 구현과 활용, 변수의 유효 범위

* static 변수 = 클래스 변수 = 정적 변수

* static 메서드 = 클래스 메서드 = 정적 메서드

## static 메서드

* static 메서드에서는 인스턴스 변수를 사용할 수가 없다.

* static 메서드는 인스턴스 생성과 무관하게 클래스 이름으로 호출 될 수 있다. 인스턴스가 생성되지 않더라도 클래스 이름으로 static 메서드 호출이 가능하다.

* 따라서, 인스턴스 변수를 사용하게 되면 생성이 되지 않은 인스턴스를 참조하는 경우가 된다.

```java
package ch16;

public class Employee {
	
	private static int serialNum = 1000;
	
	private int employeeId;
	private String employeeName;
	private String department;
	
	public Employee() {
		
		serialNum++;	// 사원이 증가할 때마다 serialNum이 증가
		employeeId = serialNum;
	}
	
	public static int getSerialNum() {
		int i = 0;	// 함수가 끝나면 없어지는 함수 내부의 지역변수
		
		employeeName = "Lee"; // 메서드가 불려지는 시점에 employeeName이 메모리에 없을 수도 있다.
		
		return serialNum;
	}

	public int getEmployeeId() {
        serialNum = 1000;
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

* `getSerialNum()` 메서드를 살펴보자.
* `getSerialNum()`이 불려지는 시점에 `employeeName`이 메모리에 없을 수도 있다.
* 따라서 static 메서드 내부에서는 인스턴스 변수(일반 멤버 변수)를 사용할 수가 없다.
* 그러나 `getEmployeeId()`처럼 static이 아닌 일반 메서드 안에서는 static 변수를 사용하는 것이 아무런 문제가 되지 않는다.
* 왜냐하면 static 변수는 프로그램 실행과 동시에 메모리의 데이터 영역에 로드가 되기 때문이다.

## 변수의 유효범위와 메모리

* 지역변수, 멤버 변수, 클래스 변수는 유효범위와 life cycle, 사용하는 메모리도 다르다.

![image](https://user-images.githubusercontent.com/27791880/151112499-93778665-35da-4178-8571-22927cf85026.png)

* static 변수는 프로그램이 메모리에 있는 동안 계속 그 영역을 차지하므로 너무 큰 메모리를 할당하는 것은 좋지 않다. 따라서 큰 사이즈를 가지는 배열을 static 변수로 선언하는 것은 좋지 못하다.

* 따라서, 클래스 내부의 여러 메서드에서 사용하는 변수는 멤버 변수로 선언하는 것이 좋다.

* 한편, 멤버 변수가 너무 많으면 인스턴스 생성 시 쓸데없는 메모리가 할당된다.

* 그렇다고 멤버변수를 사용하지 않으면, 필요할 때마다 함수 간에 매개변수를 계속 전달해주어야 한다. 그리고 외부에서 클래스를 사용할 때 귀찮아진다. 따라서 멤버변수는 클래스의 속성이 될 수 있는 것들로 적당히 사용해야 한다.

* 결과적으로 상황에 적절하게 변수를 활용하자.