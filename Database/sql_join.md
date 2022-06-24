# SQL JOIN

※ 실습 실행 환경 : MySQL 8.0 Command Line Client

<br/>

## 사용할 테이블

### 📌 topic 테이블

```mysql
CREATE TABLE `topic` (
  `tid` int NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `description` text,
  `author_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `topic`
VALUES  (1,'Saul Goodman','Better Call Saul','1'),
        (2,'White','Say my name','2'),
        (3,'Jimmy','Call Saul','1'),
        (4,'Kim','Wexler is ...',NULL);
```

```mysql
mysql> SELECT * FROM topic;
+-----+--------------+------------------+-----------+
| tid | title        | description      | author_id |
+-----+--------------+------------------+-----------+
|   1 | Saul Goodman | Better Call Saul | 1         |
|   2 | White        | Say my name      | 2         |
|   3 | Jimmy        | Call Saul        | 1         |
|   4 | Kim          | Wexler is ...    | NULL      |
+-----+--------------+------------------+-----------+
4 rows in set (0.00 sec)
```

### 📌 author 테이블

```mysql
CREATE TABLE `author` (
  `aid` int NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `profile_id` int DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `author`
VALUES  (1,'Saul','Cicero',1),
        (2,'Walter','Albuquerque',2),
        (3,'Gustavo','Chile',3);
```

```mysql
mysql> SELECT * FROM author;
+-----+---------+-------------+------------+
| aid | name    | city        | profile_id |
+-----+---------+-------------+------------+
|   1 | Saul    | Cicero      |          1 |
|   2 | Walter  | Albuquerque |          2 |
|   3 | Gustavo | Chile       |          3 |
+-----+---------+-------------+------------+
3 rows in set (0.00 sec)
```

### 📌 profile 테이블

```mysql
CREATE TABLE `profile` (
  `pid` int NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `profile`
VALUES  (1,'attorney','attorney is ...'),
        (2,'drug lord','drug lord is ..'),
        (3,'drug kingpin','drug kingpin is ...');
```

```mysql
mysql> SELECT * FROM profile;
+-----+--------------+---------------------+
| pid | title        | description         |
+-----+--------------+---------------------+
|   1 | attorney     | attorney is ...     |
|   2 | drug lord    | drug lord is ..     |
|   3 | drug kingpin | drug kingpin is ... |
+-----+--------------+---------------------+
3 rows in set (0.00 sec)
```

## SQL JOIN 종류 별 그림 정리

![sql_join](https://user-images.githubusercontent.com/27791880/175295060-a1ed759d-0980-4241-a79b-feea30f96696.png)

출처 : [dataschool.com](https://dataschool.com/how-to-teach-people-sql/sql-join-types-explained-visually/)


## 1. INNER JOIN

table A와 table B 모두 존재하는 정보만으로 새로운 테이블을 생성

![교집합_xsmall](https://user-images.githubusercontent.com/27791880/175244781-3814c83b-d573-48cc-a018-86b7acfd3467.png)

```mysql
SELECT * FROM tableA A
INNER JOIN tableB B ON A.key = B.key
```

* 양쪽 모두에 존재하는 레코드만을 가지고 표를 생성한다.

* NULL행이 존재하지 않는다.

* 표준 SQL과 달리 MySQL에서는 JOIN, INNER JOIN, CROSS JOIN 모두 같은 의미로 사용된다.

### 🔹topic 테이블과 author 테이블을 INNER JOIN

```mysql
mysql> SELECT * FROM topic
    -> INNER JOIN author ON topic.author_id = author.aid;
+-----+--------------+------------------+-----------+-----+--------+-------------+------------+
| tid | title        | description      | author_id | aid | name   | city        | profile_id |
+-----+--------------+------------------+-----------+-----+--------+-------------+------------+
|   1 | Saul Goodman | Better Call Saul | 1         |   1 | Saul   | Cicero      |          1 |
|   2 | White        | Say my name      | 2         |   2 | Walter | Albuquerque |          2 |
|   3 | Jimmy        | Call Saul        | 1         |   1 | Saul   | Cicero      |          1 |
+-----+--------------+------------------+-----------+-----+--------+-------------+------------+
3 rows in set (0.01 sec)
```

`INNER JOIN`은 표준 SQL방식과는 별도로 MySQL에서만 사용 가능한 방식이 따로 존재한다. 문법은 조금 다를 수 있어도 결과는 동일하다.

```mysql
mysql> SELECT * FROM topic, author
    -> WHERE topic.author_id = author.aid;
+-----+--------------+------------------+-----------+-----+--------+-------------+------------+
| tid | title        | description      | author_id | aid | name   | city        | profile_id |
+-----+--------------+------------------+-----------+-----+--------+-------------+------------+
|   1 | Saul Goodman | Better Call Saul | 1         |   1 | Saul   | Cicero      |          1 |
|   2 | White        | Say my name      | 2         |   2 | Walter | Albuquerque |          2 |
|   3 | Jimmy        | Call Saul        | 1         |   1 | Saul   | Cicero      |          1 |
+-----+--------------+------------------+-----------+-----+--------+-------------+------------+
3 rows in set (0.00 sec)
```

## 2. LEFT (OUTER) JOIN

table A에 존재하는 정보와 table A와 B 모두에 존재하는 정보까지 포함한다.

![LEFTJOIN_xsmall](https://user-images.githubusercontent.com/27791880/175247205-dd9d2efb-b84a-4276-83da-7bef3992467e.png)

```mysql
SELECT * FROM tableA A
LEFT JOIN tableB B ON A.key = B.key
```

### 🔹topic 테이블과 author 테이블을 LEFT JOIN

---

* `LEFT JOIN`은 첫 번째 테이블을 기준으로, 두 번째 테이블을 조합하는 JOIN이다.

* `ON`절의 조건을 만족하지 않을 경우에, 첫 테이블의 필드 값은 그대로 가져오지만, 해당 레코드의 두 번째 테이블의 필드 값은 모두 NULL로 표시된다.

* `ON`절에서는 `WHERE`절에서 사용 가능한 모든 조건을 사용 가능.

```mysql
mysql> SELECT * FROM topic
    -> LEFT JOIN author ON topic.author_id = author.aid;
+-----+--------------+------------------+-----------+------+--------+-------------+------------+
| tid | title        | description      | author_id | aid  | name   | city        | profile_id |
+-----+--------------+------------------+-----------+------+--------+-------------+------------+
|   1 | Saul Goodman | Better Call Saul | 1         |    1 | Saul   | Cicero      |          1 |
|   2 | White        | Say my name      | 2         |    2 | Walter | Albuquerque |          2 |
|   3 | Jimmy        | Call Saul        | 1         |    1 | Saul   | Cicero      |          1 |
|   4 | Kim          | Wexler is ...    | NULL      | NULL | NULL   | NULL        |       NULL |
+-----+--------------+------------------+-----------+------+--------+-------------+------------+
4 rows in set (0.00 sec)
```

`topic` 테이블에서 title이 Kim인 row의 author_id는 NULL이었고, `author`테이블의 aid에는 NULL이 없기 때문에 ON절의 조건이 만족하지 않는다.

따라서, `title = Kim`에 해당하는 topic 테이블 레코드의 두 번째 테이블의 필드 값은 모두 NULL로 표시된다.

### 🔹topic 테이블과 author 테이블을 LEFT JOIN한 결과에 profile 테이블을 LEFT JOIN

---

기존에 LEFT JOIN한 결과에 이어서 `LEFT JOIN`을 추가적으로 실행하는 것도 가능하다.

```mysql
mysql> SELECT * FROM topic
    -> LEFT JOIN author ON topic.author_id = author.aid
    -> LEFT JOIN profile ON author.profile_id = profile.pid;
+-----+--------------+------------------+-----------+------+--------+-------------+------------+------+-----------+-----------------+
| tid | title        | description      | author_id | aid  | name   | city        | profile_id | pid  | title     | description     |
+-----+--------------+------------------+-----------+------+--------+-------------+------------+------+-----------+-----------------+
|   1 | Saul Goodman | Better Call Saul | 1         |    1 | Saul   | Cicero      |          1 |    1 | attorney  | attorney is ... |
|   2 | White        | Say my name      | 2         |    2 | Walter | Albuquerque |          2 |    2 | drug lord | drug lord is .. |
|   3 | Jimmy        | Call Saul        | 1         |    1 | Saul   | Cicero      |          1 |    1 | attorney  | attorney is ... |
|   4 | Kim          | Wexler is ...    | NULL      | NULL | NULL   | NULL        |       NULL | NULL | NULL      | NULL            |
+-----+--------------+------------------+-----------+------+--------+-------------+------------+------+-----------+-----------------+
4 rows in set (0.00 sec)
```

`LEFT JOIN`으로 3개의 table을 연결한 뒤 중복되는 칼럼을 삭제하면 가독성을 높일 수 있다.

```mysql
mysql> SELECT tid, topic.title, topic.description,
    ->        aid, name, city,
    ->        pid, profile.title, profile.description
    -> FROM topic
    -> LEFT JOIN author ON topic.author_id = author.aid
    -> LEFT JOIN profile ON author.profile_id = profile.pid;
+-----+--------------+------------------+------+--------+-------------+------+-----------+-----------------+
| tid | title        | description      | aid  | name   | city        | pid  | title     | description     |
+-----+--------------+------------------+------+--------+-------------+------+-----------+-----------------+
|   1 | Saul Goodman | Better Call Saul |    1 | Saul   | Cicero      |    1 | attorney  | attorney is ... |
|   2 | White        | Say my name      |    2 | Walter | Albuquerque |    2 | drug lord | drug lord is .. |
|   3 | Jimmy        | Call Saul        |    1 | Saul   | Cicero      |    1 | attorney  | attorney is ... |
|   4 | Kim          | Wexler is ...    | NULL | NULL   | NULL        | NULL | NULL      | NULL            |
+-----+--------------+------------------+------+--------+-------------+------+-----------+-----------------+
4 rows in set (0.00 sec)
```

`WHERE`절을 사용해서 특정 레코드만 추출하는 것도 가능하다. `tid = 1`인 레코드를 추출한다.

```mysql
mysql> SELECT tid, topic.title, topic.description,
    ->          aid, name, city,
    ->          pid, profile.title, profile.description
    -> FROM topic
    -> LEFT JOIN author ON topic.author_id = author.aid
    -> LEFT JOIN profile ON author.profile_id = profile.pid
    -> WHERE tid = 1;
+-----+--------------+------------------+------+------+--------+------+----------+-----------------+
| tid | title        | description      | aid  | name | city   | pid  | title    | description     |
+-----+--------------+------------------+------+------+--------+------+----------+-----------------+
|   1 | Saul Goodman | Better Call Saul |    1 | Saul | Cicero |    1 | attorney | attorney is ... |
+-----+--------------+------------------+------+------+--------+------+----------+-----------------+
1 row in set (0.00 sec)
```

## 3. RIGHT (OUTER) JOIN

table B에 존재하는 정보와 table A와 B 모두에 존재하는 정보까지 포함한다.

![RIGHTJOIN_xsmall](https://user-images.githubusercontent.com/27791880/175248158-cec47e23-df0f-494e-9cc5-6068ddf4af47.png)

```mysql
SELECT * FROM tableA A
RIGHT JOIN tableB B ON A.key = B.key
```

* `RIGHT JOIN`은 `LEFT JOIN`과 기능적으로 거의 동일하지만, `LEFT JOIN`과는 달리 두 번째 테이블을 기준으로 첫 번째 테이블을 JOIN한다.

* `ON`절을 만족하지 않을 경우 두 번째 테이블의 필드 값은 모두 그대로 가져오지만, 해당 레코드의 첫 번째 테이블의 필드 값은 모두 NULL로 표시된다.

## 4. FULL OUTER JOIN

table A와 table B의 모든 내용을 포함하고 있다.

![FULLOUTERJOIN_xsmall](https://user-images.githubusercontent.com/27791880/175249095-a835c860-0c17-4dc9-829f-9ee26743364b.png)

```mysql
SELECT * FROM tableA A
FULL OUTER JOIN tableB B ON A.key = B.key
```

* 합집합과 같은 역할을 수행(`LEFT JOIN`과 `RIGHT JOIN`을 합한 결과)한다.

* 중복되는 행은 제거된다.

* 많은 데이터베이스 시스템에서 `FULL OUTER JOIN`은 지원되지 않는다.

  ```mysql
  mysql> SELECT * FROM topic
      -> FULL OUTER JOIN author ON topic.author_id = author.aid;
  ERROR 1064 (42000): You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'OUTER JOIN author ON topic.author_id = author.aid' at line 2
  ```
  MySQL에서는 문법 오류가 발생하는 것을 확인할 수 있다.

* `FULL OUTER JOIN`의 기능은 `UNION`을 통해서 동일한 결과를 얻을 수 있다.

### 💡UNION

---

* 여러 개의 SELECT 문의 결과를 하나의 테이블이나 결과 집합으로 표현할 때 사용

* 각각의 SELECT 문으로 선택된 필드의 개수와 타입은 모두 같아야 하며, 필드의 순서 또한 같아야 한다.

* 두 개의 SELECT 문의 결과에서 중복된 레코드는 한 번만 표시된다. 즉, `UNION DISTINCT`와 같이 `DISTINCT`를 굳이 명시할 필요가 없다.

* 만약 중복된 레코드까지 모두 출력하고 싶다면 `UNION ALL`을 사용하면 된다.

`UNION`만 사용한 경우
```mysql
mysql> (SELECT * FROM topic
    -> LEFT JOIN author ON topic.author_id = author.aid)
    -> UNION
    -> (SELECT * FROM topic
    -> RIGHT JOIN author ON topic.author_id = author.aid);
+------+--------------+------------------+-----------+------+---------+-------------+------------+
| tid  | title        | description      | author_id | aid  | name    | city        | profile_id |
+------+--------------+------------------+-----------+------+---------+-------------+------------+
|    1 | Saul Goodman | Better Call Saul | 1         |    1 | Saul    | Cicero      |          1 |
|    2 | White        | Say my name      | 2         |    2 | Walter  | Albuquerque |          2 |
|    3 | Jimmy        | Call Saul        | 1         |    1 | Saul    | Cicero      |          1 |
|    4 | Kim          | Wexler is ...    | NULL      | NULL | NULL    | NULL        |       NULL |
| NULL | NULL         | NULL             | NULL      |    3 | Gustavo | Chile       |          3 |
+------+--------------+------------------+-----------+------+---------+-------------+------------+
5 rows in set (0.00 sec)
```

`UNION ALL`을 사용한 경우
```mysql
mysql> (SELECT * FROM topic
    -> LEFT JOIN author ON topic.author_id = author.aid)
    -> UNION ALL
    -> (SELECT * FROM topic
    -> RIGHT JOIN author ON topic.author_id = author.aid);
+------+--------------+------------------+-----------+------+---------+-------------+------------+
| tid  | title        | description      | author_id | aid  | name    | city        | profile_id |
+------+--------------+------------------+-----------+------+---------+-------------+------------+
|    1 | Saul Goodman | Better Call Saul | 1         |    1 | Saul    | Cicero      |          1 |
|    2 | White        | Say my name      | 2         |    2 | Walter  | Albuquerque |          2 |
|    3 | Jimmy        | Call Saul        | 1         |    1 | Saul    | Cicero      |          1 |
|    4 | Kim          | Wexler is ...    | NULL      | NULL | NULL    | NULL        |       NULL |
|    3 | Jimmy        | Call Saul        | 1         |    1 | Saul    | Cicero      |          1 |
|    1 | Saul Goodman | Better Call Saul | 1         |    1 | Saul    | Cicero      |          1 |
|    2 | White        | Say my name      | 2         |    2 | Walter  | Albuquerque |          2 |
| NULL | NULL         | NULL             | NULL      |    3 | Gustavo | Chile       |          3 |
+------+--------------+------------------+-----------+------+---------+-------------+------------+
8 rows in set (0.00 sec)
```

## 5. EXCLUSIVE LEFT JOIN

![exclusiveJoin_xsmall](https://user-images.githubusercontent.com/27791880/175503441-c1084f04-ff9c-43c8-89e5-aaaf0d837b7d.png)

topic 테이블과 author 테이블을 `EXCLUSIVE LEFT JOIN` (`WHERE`절이 핵심)

```mysql
mysql> SELECT * FROM topic
    -> LEFT JOIN author
    -> ON topic.author_id = author.aid
    -> WHERE author.aid is NULL;
+-----+-------+---------------+-----------+------+------+------+------------+
| tid | title | description   | author_id | aid  | name | city | profile_id |
+-----+-------+---------------+-----------+------+------+------+------------+
|   4 | Kim   | Wexler is ... | NULL      | NULL | NULL | NULL |       NULL |
+-----+-------+---------------+-----------+------+------+------+------------+
1 row in set (0.00 sec)
```

<br/>

### Ref.

* [생활코딩 SQL JOIN](https://opentutorials.org/module/4118)

* [TCP SCHOOL MySQL](http://www.tcpschool.com/mysql/intro)