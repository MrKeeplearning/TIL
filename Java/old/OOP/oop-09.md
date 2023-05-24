# 09. 참조 자료형 변수

## 참조 자료형
* 변수의 자료형
* 클래스형으로 변수를 선언
* 기본 자료형과 클래스에 따라서 사용하는 메모리의 크기가 다르다.
* 참조 자료형을 사용할 때는 해당 변수에 대해서 생성해야 한다. 단, String 클래스는 예외적으로 생성 없이 사용이 가능하다.

<br/>

```java
public class Student {

	int studentId;
	String studentName;
	
	Subject korea;
	Subject math;
	
	Student(int studentId, String studentName){
		this.studentId = studentId;
		this.studentName = studentName;
		
		korea = new Subject();
		math = new Subject();
	}
	
}
```
* 대부분 참조 자료형으로 멤버변수를 사용하는 경우 생성자 내부에서 변수에 대해서 생성을 한다.
* 그런데, 반드시 생성자 내부에서 생성하지 않을 수도 있다. 가장 중요한 것은 참조 자료형 변수를 사용하기 전에 생성한다는 것이다. 
