# MySQL에서 signed와 unsigned를 사용해야할까?

MySQL에서는 signed와 unsigned 옵션을 제공한다. 물론, 이것이 SQL 표준은 아니다.

기본은 signed이며 unsigned를 선언할 시 사라진 signed 영역만큼 unsigned로 표시할 수 있는 범위가 넓어진다.

| Type      | Storage (Bytes) | Minimum Value Signed | Minimum Value Unsigned | Maximum Value Signed | Maximum Value Unsigned |
|-----------|-----------------|----------------------|------------------------|----------------------|------------------------|
| TINYINT   | 1               | -128                 | 0                      | 127                  | 255                    |
| SMALLINT  | 2               | -32768               | 0                      | 32767                | 65535                  |
| MEDIUMINT | 3               | -8388608             | 0                      | 8388607              | 16777215               |
| INT       | 4               | -2147483648          | 0                      | 2147483647           | 4294967295             |
| BIGINT    | 8               | -2<sup>63</sup>      | 0                      | 2<sup>63</sup>-1     | 2<sup>64 </sup>-1      |

만약 내가 사용하려는 컬럼에 들어갈 데이터가 양의 정수만 담긴다면 unsigned를 사용하는 것이 더 적합하다. 게다가 unsigned를 사용한다면 훨씬 더 넓은 범위를 커버할 수 있다.

## unsigned를 선언하지 않아도 unsigned만 표현할 수 있다?

굳이 unsigned를 선언하지 않더라고 unsigned만 표현하는 방법이 있다. 바로 `AUTO_INCREMENT`를 선언하는 것이다.

그런데, 이렇게 할 경우 최대 범위는 unsigned의 절반 밖에 되지 않는다.

e.g., `TINYINT`에 대해서 `UNSIGNED`없이 `AUTO_INCREMENT`만 선언하게 되면 범위는 0부터 127까지이다.

## unsigned를 선언해서 최대 가용 범위까지 사용하는 것이 좋은 방법일까?

경우에 따라서 다를 것 같다.

그 이유는 바로 `UNSIGNED`가 표준 SQL 기능이 아니라 MySQL에서만 지원하는 기능이기 때문이다(참고한 게시글이 2012년 글이기 때문에 다른 DBMS에서도 지원하는지는 정확하지 않다).

계속해서 MySQL만을 사용할 것이라면 unsigned를 사용하는 것이 큰 문제가 되지 않겠지만, 향후 다른 RDBMS로 마이그레이션하게 된다면 같은 데이터타입의 칼럼이라고 하더라도 처음 작성 시 의도했던 범위가 서로 다르기 때문에 문제가 발생할 것이다.

결과적으로 향후 마이그레이션까지 고려한다면 UNSIGNED를 사용하지 않고도 더 큰 범위를 커버할 수 있는 다른 데이터 타입을 사용하는 것이 적합하다고 생각한다.

<br/>
<br/>

### Ref.
* [Integer Types (Exact Value) - INTEGER, INT, SMALLINT, TINYINT, MEDIUMINT, BIGINT](https://dev.mysql.com/doc/refman/8.0/en/integer-types.html)
* [When should I use UNSIGNED and SIGNED INT in MySQL?](https://stackoverflow.com/questions/11515594/when-should-i-use-unsigned-and-signed-int-in-mysql)
* [What does `unsigned` in MySQL mean and when to use it?](https://stackoverflow.com/questions/3895692/what-does-unsigned-in-mysql-mean-and-when-to-use-it)