# 21. 객체 배열 사용하기

## 객체 배열의 선언과 구현

* 기본 자료형 배열은 선언과 동시에 배열의 크기만큼의 메모리가 할당되지만, 객체 배열의 경우엔 요소가 되는 객체의 주소가 들어갈(4바이트, 8바이트) 메모리만 할당되고(null) 각 요소 객체는 생성하여 저장해야 한다.

![image](https://user-images.githubusercontent.com/27791880/151492010-fb043cc3-f395-45d5-a001-36feb23700c4.png)

* 반드시 객체를 생성해서 각 배열의 요소로 저장을 해주어야 한다.

```java
public class BookTest {

	public static void main(String[] args) {
		
		Book[] library = new Book[5];
		
		library[0] = new Book("태백산맥1", "조정래");
		library[1] = new Book("태백산맥2", "조정래");
		library[2] = new Book("태백산맥3", "조정래");
		library[3] = new Book("태백산맥4", "조정래");
		library[4] = new Book("태백산맥5", "조정래");
		
		for(Book book : library) {
			System.out.println(book);
			book.showInfo();
		}

	}

}
```

## 객체 배열 복사하기

### 얕은 복사(shallow copy)

* `System.arrayCopy(src, srcPos, dest, destPos, length)` 자바에서 제공되는 배열 복사 메서드를 활용한다.

* `src`배열에서 어떤 위치(`srcPos`)에서부터 어느 정도 길이(`length`)까지 복사를 해서 목적지(`dest`) 배열의 어디서부터(`destPos`) 저장을 할지를 나타낸다.

* `System.array`는 얕은 복사이기 때문에 **객체 주소**만 복사되어 한쪽 배열의 요소를 수정하면 같이 수정 된다. 즉, 원본 배열과 복사한 배열 둘 다 같은 객체를 가리켜서 주소와 수정한 값 모두 양쪽 다 같다.

### 깊은 복사(deep copy)

* 얕은 복사의 경우 한쪽 배열의 요소를 수정하면 복사한 배열까지 모두 수정되는 문제가 있다고 했다.

* 이러한 문제점을 해결하기 위해서는 깊은 복사를 사용한다. 원본 배열과 복사 배열 각각의 객체를 생성하여 원본과 복사본 배열이 서로 다른 객체를 가리키도록 한다. 이렇게 할 경우 한쪽에서 수정을 해도 다른 쪽에 전형 영향이 가지 않는다.
