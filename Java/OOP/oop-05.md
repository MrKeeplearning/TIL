# 05. 인스턴스 생성과 힙 메모리

```java
package ch04;

public class StudentTest {

	public static void main(String[] args) {

		Student studentLee = new Student();
		studentLee.studentID = 12345;
		studentLee.setStudentName("Lee");
		studentLee.address = "서울 강남구";
		
		Student studentKim = new Student();
		studentKim.studentID = 54321;
		studentKim.setStudentName("Kim");
		studentKim.address = "서울 서초구";
		
		studentLee.showStudentInfo();
		studentKim.showStudentInfo();
		
	}

}
```

* 위의 코드에서 studentLee와 studentKim이라는 두 개의 인스턴스가 존재한다.
* 그런데 각각의 인스턴스들에는 서로 다른 ID, 이름, 주소 등을 부여했다. 이렇게 따로 부여하는 것이 가능한 이유는 studentLee와 studentKim은 서로 다른 메모리에 있기 때문이다.
* 클래스 코드는 하나이지만 해당 클래스로부터 생성될 수 있는 인스턴스는 여러 개일 수도 있다. 그리고 그 인스턴스들은 서로 다른 메모리 공간을 차지하고 있다.
* 인스턴스는 힙(heap)에 할당된다.

## 힙 메모리
* C에서는 malloc을, C++에서는 new를 사용해서 동적 메모리(heap)를 할당받는다.
* 동적메모리는 필요할 때 할당을 받고(동적), 다 사용을 했다면 해제시켜야 한다. C에서는 free(), C++에서는 delete을 사용해서 메모리 해제를 한다.

<br/>

위의 코드에서 다음 코드를 추가해보면 이런 결과가 나온다.
```java
System.out.println(studentKim);
System.out.println(studentLee);
```
```java
ch04.Student@28a418fc
ch04.Student@5305068a
```
* 앞의 `ch04.Student`는 패키지명과 클래스명을 말한다.
* 그리고 그 뒤에 @뒤로 따르는 값들이 바로 주소값이다.
* 이 주소값이 가리키는 것은 힙에 studentKim과 studentLee가 자리잡은 주소를 가리킨다.
* 그런데, 이 주소값은 실제 물리적인 주소가 아니라 JVM이 부여한 가상 주소이다.

<br/>

![image](https://user-images.githubusercontent.com/27791880/150644037-c25de25d-a946-4cc6-9f91-fe2c0603e6b6.png)

* studentLee는 단순 지역변수이고, 여기에 할당된 값이 Heap에 할당된 인스턴스의 주소값이 된다.
* studentLee나 studentKim과 같은 것들을 참조변수(reference variable)라고 하고, `ch04.Student@28a418fc`나 `ch04.Student@5305068a`를 참조값(reference value)이라고 한다.