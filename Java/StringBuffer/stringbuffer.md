# StringBuffer

- 문자열을 추가하거나 변경할 때 주로 사용하는 자료형

## 1. append

- StringBuffer 객체 생성 후 `append()` 메서드를 사용해서 문자열을 추가
- StringBuffer 객체에 `toString()`을 사용해서 String 자료형으로 변경

```java
StringBuffer sb = new StringBuffer();
sb.append("meta");
sb.append(", amazon ");
sb.append("절 뽑아주세요");
System.out.println(sb.toString());  // "meta, amazon 절 뽑아주세요" 출력
```

- String 자료형에 `+` 연산자를 사용해서 동일한 결과를 얻을 수도 있다.

```java
String result = "";
result += "meta";
result += ", amazon ";
result += "절 뽑아주세요";
System.out.println(result);  // "meta, amazon 절 뽑아주세요" 출력
```

### StringBuffer와 String 자료형 방식의 차이점 1

- StringBuffer 객체를 사용할 때는 객체가 한 번 생성되지만, String 자료형에 더하기 연산자를 사용하는 방식은 `+` 연산이 있을 때마다 새로운 String 객체가 생성된다.
- 자바는 문자열 간 `+` 연산이 있을 때마다 자동으로 새로운 String 객체를 만들기 때문에 두번째 케이스는 총 4개의 String 객체가 생성된다.

### StringBuffer와 String 자료형 방식의 차이점 2

- String 자료형은 immutable하다. `trim`이나 `toUpperCase`등의 메서드를 사용할 때 문자열이 변경되는 것이 아니라 다른 String 객체를 생성하여 리턴하는 것이다.
- `StringBuffer`는 mutable하기 때문에 한 번 생성된 값을 언제든 수정 가능!

### StringBuffer와 String 자료형은 언제 써야 하나

- `new StringBuffer()`로 StringBuffer 객체를 생성하는 것은 String 자료형을 사용하는 것보다 메모리 사용도 많고 속도도 느림
- 문자열 추가나 변경이 많은 것이 아닌 이상 String을 쓰는 것이 유리. 물론 그 반대의 경우에는 StringBuffer 사용 권장.

## 2. insert

- 특정 인덱스에 원하는 문자열을 삽입 가능

```java
StringBuffer sb = new StringBuffer();
sb.append("world");
sb.insert(0, "hello ");
System.out.println(sb.toString());      // "hello world" 출력
```

## 3. substring

- `substring(시작인덱스, 끝인덱스)`와 같이 사용하면 StringBuffer 객체에서 시작 인덱스부터 끝 인덱스 직전까지의 문자를 출력
- String 자료형에서 substring을 사용하는 것과 동일한 방식이다.

```java
StringBuffer sb = new StringBuffer();
sb.append("Hello World");
System.out.println(sb.substring(0, 4));     // "Hell" 출력
```

## 4. StringBuilder

- StringBuffer가 멀티스레드 환경에서 안전하다는 장점이 있다면 StringBuilder는 StringBuffer보다 성능이 좋음
- 동기화를 고려하지 않아도 된다면 StringBuilder를 사용하는 것이 유리
- 사용방법은 StringBuffer와 동일

# Reference

[점프 투 자바: StringBuffer](https://wikidocs.net/276)