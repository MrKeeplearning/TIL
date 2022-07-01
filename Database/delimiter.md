# Delimiter

> delimiter
>
> 1. 구획 문자 ((자기(磁氣) 테이프에서 데이터의 시작[끝]을 나타냄))

MySQL에서는 세미콜론(`;`)을 명령문의 구분자로 인식하고 있기 때문에 delimiter가 필요하다.

Procedure나 Trigger와 같은 [Stored Program](http://www.ktword.co.kr/test/view/view.php?m_temp1=5428)의 경우 세미콜론으로 구분된 다수의 SQL문으로 구성되어 있을 수 있고, 이러한 Stored Program을 서버에 그대로 전달하게 되면 문제가 발생한다.

```mysql
CREATE PROCEDURE dorepeat(p1 INT)
BEGIN
  SET @x = 0;
  REPEAT SET @x = @x + 1; UNTIL @x > p1 END REPEAT;
END;
```

위와 같은 stored program을 서버에 전달한다고 하면 다 입력도 하기 전에 아래와 같이 문법 오류가 발생한다.

```mysql
mysql> CREATE PROCEDURE dorepeat(p1 INT)
    -> BEGIN
    ->  SET @x = 0;
ERROR 1064 (42000): You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near '' at line 3
```

이러한 문제를 해결하기 위해서 delimiter를 사용해서 default 구분자인 `;`를 일시적으로 다른 문자로 재정의하는 작업이 필요하다.

```mysql
mysql> delimiter //
mysql> CREATE PROCEDURE dorepeat(p1 INT)
    -> BEGIN
    ->  SET @x = 0;
    ->  REPEAT SET @x = @x + 1; UNTIL @x > p1 END REPEAT;
    -> END
    -> //
Query OK, 0 rows affected (0.07 sec)

mysql> delimiter ;
mysql> CALL dorepeat(1000);
Query OK, 0 rows affected (0.02 sec)

mysql> SELECT @x;
+------+
| @x   |
+------+
| 1001 |
+------+
1 row in set (0.00 sec)
```

위의 예시는 구분자를 `//`로 바꾼 뒤 전체 stored program을 단일 명령문으로 서버에 전달한다.

그 뒤, 프로시저를 호출하기 전에 구분자를 다시 `;`로 정의한다(만약 구분자를 계속 `//`로 사용하고 싶다면 재정의하지 않아도 상관없다^^).

DELIMITER는 어떠한 문자로도 정의할 수 있고 단일 문자 또는 여러 문자로 정의할 수도 있다.

다만, `\`(백슬래시)는 MySQL에서 이스케이프 문자로 사용되기 때문에 해당 문자는 사용을 피해야 한다.

>💡 모든 Stored Program이 서버에 전달될 때 delimiter를 사용할 필요는 없다.\
> 만약, 내부에 `;`구분자가 없고 Stored Program 끝에 `;`가 하나만 있다면 사용할 필요가 없다.

<br/>
<br/>

### Ref.

[MySQL Documentation - Defining Stored Program](https://dev.mysql.com/doc/refman/8.0/en/stored-programs-defining.html)