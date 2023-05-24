# 06. 생성자

## 생성자
* 생성자는 내가 필요하다고 해서 호출해서 사용하는 것이 아니다. 객체를 생성할 때만 생성자를 사용한다.
* 객체가 생성이 될 때 필요한 초기화 단계, 예를 들어 변수 초기화, 상수 초기화, 객체 변수를 가져왔는데 해당 객체 변수를 생성한다거나 여러 가지 초기화 과정들이 필요하고 그러한 초기화 과정을 위해 사용한다.

## 기본 생성자(default constructor)
* 컴파일러가 넣어주는 생성자
* pre-compile 단계에서 명령어 구조로 바뀔 때 컴파일에 필요한 코드들이 중간에 들어가게 된다. 이 때, default constructor도 포함된다.
* 기본 생성자는 매개변수도 없고, 구현코드도 없다.
    ```java
    public Student(){}
    ```

### 멤버변수는 자동으로 초기화된다.
```java
# Student.java

public class Student {
	
	public int studentNumber;
	public String studentName;
	public int grade;
	
	public String showStudentInfo() {
		
		return studentName + "학생의 학번은 "+ studentNumber + "이고, " + grade + "학년입니다."; 
	}

}
```

```java
# StudentTest.java

public class StudentTest {

	public static void main(String[] args) {
		
		Student studentLee = new Student();
		
		System.out.println(studentLee.showStudentInfo());

	}

}
```
결과
> null학생의 학번은 0이고, 0학년입니다.

같은 패키지 내에 존재하는 위의 두 코드에서 `StudentTest.java`를 실행하면 위와 같은 결과가 출력된다.

이를 통해서 멤버변수는 자동으로 초기화된다는 것을 알 수가 있다.

만약, Student.java에서 `showStudentInfo()` 메서도 내부에 `int i;`를 선언하고 return에 i를 포함시키면 오류가 발생한다.

`i`는 지역변수이고 자동으로 초기화되지 않는다. 이처럼 멤버변수와 지역변수는 완전 다른 타입의 변수이다.

### ⭐ 중요!
* `StudentTest.java`에서 `Student studentLee = new Student();`가 가능한 이유는 기본 생성자 때문이다.
* Student 클래스 내부에는 생성자를 구현하지 않은 상태이지만, 컴파일러가 기본 생성자 코드를 넣어주기 때문에 `new` 키워드를 통해서 생성자를 호출할 수 있는 것이다.

```java
package ch06;

public class Student {
	
	public int studentNumber;
	public String studentName;
	public int grade;
	
	public Student(int studentNumber, String studentName, int grade) {
		
		this.studentNumber = studentNumber;
		
	}
	
	public String showStudentInfo() {
		
		return studentName + "학생의 학번은 "+ studentNumber + "이고, " + grade + "학년입니다."; 
	}

}
```

* 나의 멤버변수에 넘어온 값을 대입하고 싶다. 그것이 생성자의 역할이기도 하다.
* 객체가 생성이 될 때 자신의 파라미터 값을 자신의 멤버변수에 넣어주는 것이 생성자의 역할. 이 때 사용하는 것이 `this`이다.
* 위의 코드의 `this.studentNumber = studentNumber;`에서 `this.studentNumber`의 `studentNumber`는 Student 클래스의 멤버변수인 `studentNumber`를 가리킨다.
* 그리고 equal 뒤의 `studentNumber`는 Student 생성자의 매개변수인 `studentNumber`를 가리킨다.
* 따라서, 다시 정리하면 객체가 받은 파라미터값을 Student 클래스의 멤버변수에 넣어 초기화하는 것이다.
* 만약 매개변수 studentNumber를 멤버변수와 같은 이름이 아닌 다른 변수명을 사용한다면 this를 사용할 필요가 없지만, 멤버변수와 같은 이름을 사용하는 것이 가독성도 좋고 찾아보기도 좋다. 일종의 convention같은 느낌이라고 보면 될 것 같다.

