### ※ 아래의 학습 내용은 `MySQL 8.0 Command Line Client`를 활용하여 실습을 진행했음.

<br/>

* 관계형 데이터베이스라는 이론적 토대 위에서 만들어진 기술들

    * MySQL, Oracle, SQL Server, PostgreSQL, DB2, Access

* 대부분의 사용법이 동일하다는 특징이 있다.

# MySQL
* 무료, 오픈소스, 관계형데이터베이스의 주요한 기능을 대부분 갖추고 있는 준수한 관계형 데이터베이스 시스템

    * 상업적으로 사용할 때, 즉 MySQL SW를 대폭 수정 후 고객에게 배포할 때는 상업용 라이센스 구입이 필요

# 1. MySQL의 구조

* 데이터를 기록하는 최종적인 공간 -> 표(table)

* 관계형 데이터베이스는 스프레드 시트와 유사한 구조를 가지고 있다.

* 웹사이트 운영에 사용되는 데이터를 데이터베이스에 저장한다고 하면 post table, comment table, userInfo table 등이 생길 수 있다.

  -> 수많은 테이블이 생성될 텐데 정리가 필요함

  -> 연관된 표들을 그룹핑하고 이들을 연관되어 있지 않은 표들에서 분리하는데 사용하는 파일의 폴더 = 데이터베이스(또는 '스키마(schema)'라고 부름)

* 스키마들은 어딘가에 저장되어야 함 -> 데이터베이스 서버(database server)

![image](https://user-images.githubusercontent.com/27791880/174438794-c0913453-b856-43a4-9bff-06e552092906.png)

* MySQL을 설치한 행위 = 데이터베이스 서버라는 프로그램을 설치한 것. 이 프로그램이 가진 기능들을 활용해서 여러 작업을 진행.

# 2. 데이터베이스 사용의 장점

* 보안 : 자체적인 보안쳬계를 가지고 있어 안전한 보관 가능

* 권한 : MySQL에 여러 사람을 등록해서 어떤 데이터베이스에 접근이 가능한지, 그리고 데이터베이스 상에서 읽기, 쓰기, 수정, 삭제 중 어떠한 작업을 할 수 있는지 설정할 수 있다.

# 3. 스키마의 사용

테이블 생성하기

```mysql
mysql> CREATE DATABASE sql_study;
Query OK, 1 row affected (0.01 sec)
```

테이블 삭제하기

```mysql
mysql> DROP DATABASE sql_study;
Query OK, 0 rows affected (0.03 sec)
```

MySQL 서버 호스트에 담긴 모든 데이터베이스를 확인

```mysql
mysql> SHOW DATABASES;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| sakila             |
| sql_study          |
| sys                |
| world              |
+--------------------+
7 rows in set (0.00 sec)
```

아래와 같이 선언하면 현재부터 내리는 명령을 sql_study라는 스키마에 있는 표를 대상으로 명령을 실행하게 된다.

```mysql
mysql> use sql_study;
Database changed
```

# 4. SQL과 테이블의 구조

SQL이라는 컴퓨터 언어는 관계형 데이터베이스라는 카테고리에 속하는 제품들이 공통적으로 데이터베이스 서버를 제어할 때 사용하는 언어이다.

## 4.1. SQL: Structured

* 관계형 데이터베이스는 기본적으로 표의 형식으로 정보를 정리할 수 있다.

* 이 표를 작성하는 작업을 구조화되었다고 말한다. -> Structured

## 4.2. SQL: Query

* 데이터베이스에 데이터를 넣어주거나, 읽어주거나, 수정·삭제하거나, 스키마를 만들거나 하는 작업들을 요청한다. -> Query

## 4.3. SQL: Language

* 데이터베이스도 이해할 수 있고, 사람도 이해할 수 있는 공통의 약속에 따라 데이터베이스 서버에게 요청을 해야 서로가 알아들을 수 있다. 이 때 사용하는 언어가 SQL이라는 '언어'이다.

## 4.4. 용어 정리

row, record, 행

* 관계형 데이터베이스에서 로우는 데이터 하나하나를 생각하면 된다.

* 데이터 자체

column, 열

* 관계형 데이터베이스에서 컬럼은 데이터의 타입이라고 생각하면 된다.

* 데이터의 구조

# 5. 테이블의 생성

## 5.1. 만들려는 테이블

![image](https://user-images.githubusercontent.com/27791880/174543193-4dc66a76-7dd5-442f-96c7-6aa030f31ff5.png)

* 데이터베이스의 특징 중 하나는 컬럼의 data type을 강제할 수 있다.

* id값이 없다면 수정, 삭제가 불가능하기 때문에 필수적으로 포함되어야 한다.

* 반면, winner는 나중에 작성하는 것이 가능하다.

* NOT NULL : 값이 없는 것을 허용하지 않는다.

## 5.2. 3번째 행을 가장 효과적으로 지우는 방법은?

![image](https://user-images.githubusercontent.com/27791880/174543302-18d3a8b2-f9a6-45c1-9c95-0c0add566a1f.png)

* 만약 winner 칼럼이 'Charles_Leclerc'인 행을 삭제하라고 하면 같은 데이터를 가진 행이 있기 때문에 다른 행이 삭제될 수 있다.

* id값이 중복되지 않는 이상 틀림없이 `id=3`인 행이 삭제된다. 따라서, 데이터를 추가할 때 마지막 id값보다 1이 큰 id값을 채워주면 중복이 없게 된다.

* MySQL에서 id값이 자동으로 1씩 증가한다는 의미에서 `AUTO_INCREMENT`를 지정해주면 중복되지 않는 식별자를 가지게 된다.

## 5.3. PRIMARY KEY()

성능과 중복방지를 위해 사용해야 한다.

### 🔹성능
---

* MySQL의 InnoDB는 기본적으로 데이터를 저장하고 Indexing하기 위해 PK를 요구한다.

* 만약 명시적인 PK가 없다면 사용자에게 값이 노출되지 않더라도 InnoDB는 그 값을 생성한다. 이 때 생성된 값은 Auto Increment INT 또는 BIGINT보다 큰 값이다.

* PK를 설정하지 않을 경우 데이터의 실제 저장 정렬 순서를 보장할 수 없고, 경우에 따라 데이터 조회 시 DB가 보든 데이터를 조회하는 일이 발생할 수 있다.

### 🔹중복방지
---

* 위의 `f1_2022_results` 테이블을 예로 들었을 때, id값은 각 행을 식별하는 식별자의 역할을 한다. 따라서 중복된 값을 가질 수 없으며 Null값을 가질 수 없다.

* 관계형 데이터베이스에서 각 행이 고유하게 식별되는 규칙을 위반한다면, `관계형` 이라는 특징을 더 이상 가질 수 없으며 모호성이 발생한다. 중복 행을 생성하는 `JOIN Query`에서는 큰 문제가 발생하게 된다.

## 5.4. 참고: MySQL의 인덱스 - clustered index와 nonclustered index

### 🔹Clustered Index (클러스터형 인덱스)
---

* 키 값에 대한 테이블의 데이터 행을 정렬하는 인덱스의 한 유형

* 테이블의 데이터를 정렬해서 저장되는 순서를 정의한다.

* 테이블당 하나만 생성 가능

* 행 데이터를 인덱스로 지정한 열에 맞춰 자동 정렬

  * 영어 사전에서 알파벳 순으로 사전의 단어들이 정렬되어 있는 것과 유사

* PK에 의해 자동 생성

  * RDBMS에서는 일반적으로 PK를 사용하여 특정 열을 기반으로 클러스터링된 인덱스를 만들 수 있다.

* UNIQUE NOT NULL에 의해 자동으로 생성

* PK와 UNIQUE NOT NULL이 한 테이블에 함께 있을 경우 PK를 클러스터형 인덱스로 사용

### 🔹Nonclustered Index (비클러스터형 인덱스 / 보조 인덱스)
---

* 데이터와 인덱스를 서로 다른 위치에 저장

  * 인덱스는 해당 데이터의 위치에 대한 포인터를 포함하고 있다.

* 테이블당 여러 개 생성 가능

* 책의 맨 뒷장 색인에서 단어를 찾고 해당 페이지로 가서 관련 내용을 검색하는 것과 유사

* UNIQUE, UNIQUE NULL에 의해 자동 생성

* 별도의 인덱스가 생성되지만, 원본데이터 변경은 없음

# 6. CRUD (Create, Read, Update, Delete)

## 6.1. Create: INSERT

```mysql
mysql> SHOW TABLES;
+---------------------+
| Tables_in_sql_study |
+---------------------+
| f1_2022_results     |
+---------------------+
1 row in set (0.00 sec)
```

```mysql
mysql> DESC f1_2022_results;
+------------+--------------+------+-----+---------+----------------+
| Field      | Type         | Null | Key | Default | Extra          |
+------------+--------------+------+-----+---------+----------------+
| id         | int          | NO   | PRI | NULL    | auto_increment |
| grand_prix | varchar(100) | NO   |     | NULL    |                |
| date       | datetime     | NO   |     | NULL    |                |
| winner     | varchar(100) | NO   |     | NULL    |                |
| car        | text         | YES  |     | NULL    |                |
| laps       | tinyint      | YES  |     | NULL    |                |
+------------+--------------+------+-----+---------+----------------+
6 rows in set (0.00 sec)
```

`INSERT` 작업을 할 때 `id` 값은 `auto_increment` 설정이 되어 있기 때문에 굳이 언급할 필요가 없다.

```mysql
mysql> INSERT INTO f1_2022_results (grand_prix, date, winner, car, laps)
    -> VALUES('Bahrain', '2022-03-20', 'Charles_Leclerc', 'FERRARI', 57);
Query OK, 1 row affected (0.02 sec)
```

입력된 데이터 확인

```mysql
mysql> SELECT * FROM f1_2022_results;
+----+------------+---------------------+-----------------+---------+------+
| id | grand_prix | date                | winner          | car     | laps |
+----+------------+---------------------+-----------------+---------+------+
|  1 | Bahrain    | 2022-03-20 00:00:00 | Charles_Leclerc | FERRARI |   57 |
+----+------------+---------------------+-----------------+---------+------+
1 row in set (0.00 sec)
```

나머지 데이터도 같은 방법으로 입력하여 전체 테이블 완성

```mysql
mysql> SELECT * FROM f1_2022_results;
+----+----------------+---------------------+-----------------+----------------------+------+
| id | grand_prix     | date                | winner          | car                  | laps |
+----+----------------+---------------------+-----------------+----------------------+------+
|  1 | Bahrain        | 2022-03-20 00:00:00 | Charles_Leclerc | FERRARI              |   57 |
|  2 | Saudi_Arabia   | 2022-03-27 00:00:00 | Max_Verstappen  | RED_BULL_RACING_RBPT |   50 |
|  3 | Australia      | 2022-04-10 00:00:00 | Charles_Leclerc | FERRARI              |   58 |
|  4 | Emilia_Romagna | 2022-04-24 00:00:00 | Max_Verstappen  | RED_BULL_RACING_RBPT |   63 |
|  5 | Miami          | 2022-05-08 00:00:00 | Max_Verstappen  | RED_BULL_RACING_RBPT |   57 |
+----+----------------+---------------------+-----------------+----------------------+------+
5 rows in set (0.00 sec)
```

## 6.2. Read: SELECT

특정 column만을 선택하여 읽어온다.

```mysql
mysql> SELECT id, grand_prix, winner, car FROM f1_2022_results;
+----+----------------+-----------------+----------------------+
| id | grand_prix     | winner          | car                  |
+----+----------------+-----------------+----------------------+
|  1 | Bahrain        | Charles_Leclerc | FERRARI              |
|  2 | Saudi_Arabia   | Max_Verstappen  | RED_BULL_RACING_RBPT |
|  3 | Australia      | Charles_Leclerc | FERRARI              |
|  4 | Emilia_Romagna | Max_Verstappen  | RED_BULL_RACING_RBPT |
|  5 | Miami          | Max_Verstappen  | RED_BULL_RACING_RBPT |
+----+----------------+-----------------+----------------------+
5 rows in set (0.00 sec)
```

특정 column에서 특정 value에 해당하는 데이터만 추출

winner의 value가 `Charles_Leclerc`인 row만 추출해 보여준다.

```mysql
mysql> SELECT id, grand_prix, winner, car FROM f1_2022_results WHERE winner='Charles_Leclerc';
+----+------------+-----------------+---------+
| id | grand_prix | winner          | car     |
+----+------------+-----------------+---------+
|  1 | Bahrain    | Charles_Leclerc | FERRARI |
|  3 | Australia  | Charles_Leclerc | FERRARI |
+----+------------+-----------------+---------+
2 rows in set (0.00 sec)
```

이번에는 winner의 value가 `Max_Verstappen`인 상태에서 id 값을 내림차순으로 보여준다.

```mysql
mysql> SELECT id, grand_prix, winner, car
    -> FROM f1_2022_results
    -> WHERE winner='Max_Verstappen'
    -> ORDER BY id DESC;
+----+----------------+----------------+----------------------+
| id | grand_prix     | winner         | car                  |
+----+----------------+----------------+----------------------+
|  5 | Miami          | Max_Verstappen | RED_BULL_RACING_RBPT |
|  4 | Emilia_Romagna | Max_Verstappen | RED_BULL_RACING_RBPT |
|  2 | Saudi_Arabia   | Max_Verstappen | RED_BULL_RACING_RBPT |
+----+----------------+----------------+----------------------+
3 rows in set (0.01 sec)
```

엄청나게 많은 10억 건 이상의 데이터를 한 번의 제한 없이 요청하게 되면 장애가 발생할 것이다. 따라서 **제한조건**을 두는 것은 대규모 서비스에서 필수적이다.

아래와 같이 `LIMIT 2` 라는 조건을 부여하면 위에서부터 2건의 row만 불러온다.

```mysql
mysql> SELECT id, grand_prix, winner, car
    -> FROM f1_2022_results
    -> WHERE winner='Max_Verstappen'
    -> ORDER BY id DESC
    -> LIMIT 2;
+----+----------------+----------------+----------------------+
| id | grand_prix     | winner         | car                  |
+----+----------------+----------------+----------------------+
|  5 | Miami          | Max_Verstappen | RED_BULL_RACING_RBPT |
|  4 | Emilia_Romagna | Max_Verstappen | RED_BULL_RACING_RBPT |
+----+----------------+----------------+----------------------+
2 rows in set (0.01 sec)
```

## 6.3. UPDATE

```mysql
mysql> SELECT * FROM f1_2022_results;
+----+----------------+---------------------+-----------------+----------------------+------+
| id | grand_prix     | date                | winner          | car                  | laps |
+----+----------------+---------------------+-----------------+----------------------+------+
|  1 | Bahrain        | 2022-03-20 00:00:00 | Charles_Leclerc | FERRARI              |   57 |
|  2 | Saudi_Arabia   | 2022-03-27 00:00:00 | Max_Verstappen  | RED_BULL_RACING_RBPT |   50 |
|  3 | Australia      | 2022-04-10 00:00:00 | Charles_Leclerc | FERRARI              |   58 |
|  4 | Emilia_Romagna | 2022-04-24 00:00:00 | Max_Verstappen  | RED_BULL_RACING_RBPT |   63 |
|  5 | Miami          | 2022-05-08 00:00:00 | Max_Verstappen  | RED_BULL_RACING_RBPT |   57 |
+----+----------------+---------------------+-----------------+----------------------+------+
5 rows in set (0.01 sec)
```

`id=2`인 행에 대해서 `grand_prix`, `date`, `winner`, `laps` 칼럼을 수정해보자.

`WHERE`절을 넣어주지 않는다면 해당되는 모든 칼럼이 변경되는 일이 발생할 수 있다.

```mysql
mysql> UPDATE f1_2022_results
    -> SET
    ->  grand_prix='Monaco',
    ->  date='2022-05-29',
    ->  winner='Sergio_Perez',
    ->  laps=64
    -> WHERE id=2;
Query OK, 1 row affected (0.03 sec)
Rows matched: 1  Changed: 1  Warnings: 0
```

```mysql
mysql> SELECT * FROM f1_2022_results;
+----+----------------+---------------------+-----------------+----------------------+------+
| id | grand_prix     | date                | winner          | car                  | laps |
+----+----------------+---------------------+-----------------+----------------------+------+
|  1 | Bahrain        | 2022-03-20 00:00:00 | Charles_Leclerc | FERRARI              |   57 |
|  2 | Monaco         | 2022-05-29 00:00:00 | Sergio_Perez    | RED_BULL_RACING_RBPT |   64 |
|  3 | Australia      | 2022-04-10 00:00:00 | Charles_Leclerc | FERRARI              |   58 |
|  4 | Emilia_Romagna | 2022-04-24 00:00:00 | Max_Verstappen  | RED_BULL_RACING_RBPT |   63 |
|  5 | Miami          | 2022-05-08 00:00:00 | Max_Verstappen  | RED_BULL_RACING_RBPT |   57 |
+----+----------------+---------------------+-----------------+----------------------+------+
5 rows in set (0.00 sec)
```

## 6.4. DELETE

MySQL document를 살펴보면 DELETE Syntax에 대해 다음과 같이 설명하고 있다.

```mysql
DELETE [LOW_PRIORITY] [QUICK] [IGNORE] FROM tbl_name [[AS] tbl_alias]
    [PARTITION (partition_name [, partition_name] ...)]
    [WHERE where_condition]
    [ORDER BY ...]
    [LIMIT row_count]
```

이 때, `WHERE`절을 누락하게 되면 모든 행이 삭제되는 문제가 발생할 수 있으니 유의한다.

```mysql
mysql> DELETE FROM f1_2022_results WHERE id = 5;
Query OK, 1 row affected (0.02 sec)

mysql> SELECT * FROM f1_2022_results;
+----+----------------+---------------------+-----------------+----------------------+------+
| id | grand_prix     | date                | winner          | car                  | laps |
+----+----------------+---------------------+-----------------+----------------------+------+
|  1 | Bahrain        | 2022-03-20 00:00:00 | Charles_Leclerc | FERRARI              |   57 |
|  2 | Monaco         | 2022-05-29 00:00:00 | Sergio_Perez    | RED_BULL_RACING_RBPT |   64 |
|  3 | Australia      | 2022-04-10 00:00:00 | Charles_Leclerc | FERRARI              |   58 |
|  4 | Emilia_Romagna | 2022-04-24 00:00:00 | Max_Verstappen  | RED_BULL_RACING_RBPT |   63 |
+----+----------------+---------------------+-----------------+----------------------+------+
4 rows in set (0.00 sec)
```

# 7. 관계형데이터베이스의 필요성

![image](https://user-images.githubusercontent.com/27791880/174942319-8256f384-6dd6-4b8d-8d2a-b4c85a5e96a3.png)

위의 표를 보면 데이터가 중복해서 등장하는 경우가 있다.

winner와 car 칼럼이 (Charles_Leclerc, FERRARI)인 것과 (Max_Verstappen, RED_BULL_RACING_RBPT)인 것이 자주 중복된다.

데이터가 중복되고 있다면 개선할 것이 있다는 강력한 증거가 된다.

행이 1억건이 있고, 중복되는 데이터들이 천 만 개 정도 중복된다고 하면 다양한 문제를 발생시킬 수 있다.

예를 들어, 앞서 살펴본 winner와 car에 담기는 각각의 value들이 Charles_Leclerc와 FERRARI 같은 작은 문자열에 그치는 것이 아니라 매우 큰 사이즈를 가지는 데이터이고, 이것이 천 만 번 중복된다면 기술적·경제적 손해가 크다.

* 중복되는 천 만 개의 행을 각각 수정해야 한다면 시간적으로 비효율적

* 각 데이터들의 용량이 크다면 서로 비교해서 같은 것인지 판별하는 것이 힘들 수 있음

이러한 문제를 해결하기 위해 다음과 같이 표를 수정할 수 있다.

![image](https://user-images.githubusercontent.com/27791880/175043136-1c4d1d66-29aa-4540-8e03-bede4c18c5bb.png)


표가 조금은 더 복잡해졌지만 `f1_2022_results`에 존재하던 중복된 데이터는 사라지고 각각의 데이터들에 대한 `winner`테이블의 식별자인 `id`값으로 대체되었다.

따라서, `winner`테이블을 참조하고 있는 모든 `f1_2022`의 행은 `winner`테이블의 내용을 수정할 때마다 함께 수정되어 **유지보수**가 훨씬 더 편해진다.

하지만, 하나의 테이블에 담겨 있을 때 모든 데이터를 직관적으로 살펴볼 수 있던 것과는 달리, row가 참조하고 있는 별도의 테이블을 열어서 해당 테이블을 비교해보며 사용해야 하는 불편함이 있다.

**테이블을 분리해서 중복을 방지하면서도 데이터를 확인할 때는 하나의 테이블로 합쳐진 결과를 보는 것**은 **MySQL**을 사용한다면 쉽게 해결할 수 있다.

# 8. 테이블 분리하기

테이블을 분리하기에 앞서 기존에 작업했던 테이블을 다른 이름으로 저장하고 새로운 테이블을 만들어 놓으려 한다.

## *cf. 테이블 이름 바꾸기*

```mysql
mysql> RENAME TABLE f1_2022_results TO f1_backup;
```
```mysql
mysql> SHOW TABLES;
+---------------------+
| Tables_in_sql_study |
+---------------------+
| f1_backup           |
+---------------------+
1 row in set (0.00 sec)
```

## *cf. warnings* 

앞서 만든 테이블과 내용이 조금 다른 `f1_2022_results`라는 새로운 테이블을 생성했다.

```mysql
mysql> CREATE TABLE f1_2022_results (
    -> id INT(11) NOT NULL AUTO_INCREMENT,
    -> grand_prix VARCHAR(100) NOT NULL,
    -> date DATETIME NOT NULL,
    -> winner VARCHAR(100) NOT NULL,
    -> car TEXT NULL,
    -> laps TINYINT NULL,
    -> time TIME(3),
    -> PRIMARY KEY(id)
    -> );
Query OK, 0 rows affected, 1 warning (0.49 sec)
```

정상적으로 생성은 되었지만 1개의 `warning`이 발생한 것을 확인할 수 있다.

### 💡warning 확인하기

```mysql
mysql> SHOW WARNINGS;
+---------+------+------------------------------------------------------------------------------+
| Level   | Code | Message                                                                      |
+---------+------+------------------------------------------------------------------------------+
| Warning | 1681 | Integer display width is deprecated and will be removed in a future release. |
+---------+------+------------------------------------------------------------------------------+
1 row in set (0.00 sec)

mysql> SHOW WARNINGS\G
*************************** 1. row ***************************
  Level: Warning
   Code: 1681
Message: Integer display width is deprecated and will be removed in a future release.
1 row in set (0.00 sec)
```

### 💡`INT(display width)`은 deprecated 되었다.

위의 warning 메시지를 보면 `INT()`에 display width, 즉 몇 자리 숫자까지 표현할 것인지를 지정하는 옵션에 대해서 지원이 중단되었다고 말한다.

물론, 위의 warning을 무시해도 table을 생성하고 데이터를 조작하는 것에는 문제가 없다. 하지만, deprecated 되었다고 하니 `INT()`로 수정하자.

[Document](https://dev.mysql.com/doc/refman/8.0/en/numeric-type-attributes.html)를 살펴봐도 `INT()`의 display width를 사용하는 것을 권장하지 않는다고 기술되어 있다.

> As of MySQL 8.0.17, the ZEROFILL attribute is deprecated for numeric data types, as is the display width attribute for integer data types. You should expect support for ZEROFILL and display widths for integer data types to be removed in a future version of MySQL. Consider using an alternative means of producing the effect of these attributes.

참고로 [Document](https://dev.mysql.com/doc/refman/8.0/en/numeric-type-attributes.html)에 따르면 `INT(3)`과 같이 세 자리 숫자까지만 넣는 것을 강제한다고 하더라도 실제로 그 범위가 강제되지는 않는다고 한다.

세 자리 숫자를 벗어나는 경우, 즉 1000 이상의 숫자를 넣어도 INT의 Signed Value의 범위인 -2147483648에서 2147483647사이에 해당되는 값이라면 모두 허용된다. 또한 표현되는 값또한 100의 자리에서 잘리지 않고 모두 표현된다.

`DESC`를 사용해서 각 column의 속성을 조회해도 `CREATE TABLE`을 할 때는 `INT(3)`으로 제한했더라도 실제 TYPE은 `int`로만 표현된 것을 확인할 수 있다.

```mysql
mysql> DESC f1_2022_results;
+------------+--------------+------+-----+---------+----------------+
| Field      | Type         | Null | Key | Default | Extra          |
+------------+--------------+------+-----+---------+----------------+
| id         | int          | NO   | PRI | NULL    | auto_increment |
| grand_prix | varchar(100) | NO   |     | NULL    |                |
| date       | datetime     | NO   |     | NULL    |                |
| winner     | varchar(100) | NO   |     | NULL    |                |
| car        | text         | YES  |     | NULL    |                |
| laps       | tinyint      | YES  |     | NULL    |                |
| time       | time(3)      | YES  |     | NULL    |                |
+------------+--------------+------+-----+---------+----------------+
7 rows in set (0.00 sec)
```

`INT(11)`을 `INT`로 수정하기

```mysql
mysql> ALTER TABLE f1_2022_results
    -> MODIFY COLUMN id INT NOT NULL AUTO_INCREMENT;
Query OK, 0 rows affected (0.03 sec)
Records: 0  Duplicates: 0  Warnings: 0
```

## *cf. 초의 소수점 단위까지 어떻게 표현할까?*

f1과 같이 0.001초 차이로 1, 2위의 승부가 결정나는 스포츠의 경우 시간을 아주 세밀하게 기록하는 것이 중요하다.

생성하려는 `f1_2022_results`테이블의 경우 전체 레이스 타임 데이터를 담은 `time` 칼럼이 존재하고 이 칼럼에 들어가는 데이터는 `TIME`타입의 데이터이다.

이 `time`칼럼에는 초가 소수점 셋째 자리까지 표현된다. 소수부까지 표현하기 위해서 MySQL에서는 `type_name(fsp)`와 같은 형식을 지원해준다.

`type_name`은 `TIME`, `DATETIME`, `TIMESTAMP`등에 해당하고 `fsp`는 초의 소수부를 어디까지 표현할 것인지를 뜻하며 0에서 6사이의 숫자 중에 선택해야 한다.

레이스 총 시간은 초의 소수부가 셋째 자리까지 표현되기 때문에 `fsp`에 3을 넣어주고, 테이블 생성 시 아래와 같이 작성했다.

```mysql
mysql> CREATE TABLE f1_2022_results (
    -> ...
    -> time TIME(3),
    -> ...
    -> );
```

더 상세한 내용은 MySQL document의 [Fractional Seconds in Time Values](https://dev.mysql.com/doc/refman/8.0/en/fractional-seconds.html)에서 확인할 수 있다.

<br/>

### 8.1. `f1_2022_results`테이블 생성하기

```mysql
mysql> SELECT * FROM f1_2022_results;
+----+----------------+---------------------+-----------------+----------------------+------+--------------+
| id | grand_prix     | date                | winner          | car                  | laps | time         |
+----+----------------+---------------------+-----------------+----------------------+------+--------------+
|  1 | Bahrain        | 2022-03-20 00:00:00 | Charles_Leclerc | FERRARI              |   57 | 01:37:33.584 |
|  2 | Saudi_Arabia   | 2022-03-27 00:00:00 | Max_Verstappen  | RED_BULL_RACING_RBPT |   50 | 01:24:19.293 |
|  3 | Australia      | 2022-04-10 00:00:00 | Charles_Leclerc | FERRARI              |   58 | 01:27:46.548 |
|  4 | Emilia_Romagna | 2022-04-24 00:00:00 | Max_Verstappen  | RED_BULL_RACING_RBPT |   63 | 01:32:07.986 |
|  5 | Miami          | 2022-05-08 00:00:00 | Max_Verstappen  | RED_BULL_RACING_RBPT |   57 | 01:34:24.258 |
|  6 | Spain          | 2022-05-22 00:00:00 | Max_Verstappen  | RED_BULL_RACING_RBPT |   66 | 01:37:20.475 |
|  7 | Monaco         | 2022-05-29 00:00:00 | Sergio_Perez    | RED_BULL_RACING_RBPT |   64 | 01:56:30.265 |
|  8 | Azerbaijan     | 2022-06-12 00:00:00 | Max_Verstappen  | RED_BULL_RACING_RBPT |   51 | 01:34:05.941 |
|  9 | Canada         | 2022-06-19 00:00:00 | Max_Verstappen  | RED_BULL_RACING_RBPT |   70 | 01:36:21.757 |
+----+----------------+---------------------+-----------------+----------------------+------+--------------+
9 rows in set (0.00 sec)
```

### Ref.

[Table 작성 시 PK를 무조건 사용해야 하는 이유](https://hodongman.github.io/2019/01/14/Database-PK%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%B4%EC%95%BC-%ED%95%98%EB%8A%94-%EC%9D%B4%EC%9C%A0.html)

[인덱스(index),클러스터/보조인덱스](https://jie0025.tistory.com/107)

[MySQL/MariaDB Clustered vs Non-Clustered Index](https://estenpark.tistory.com/384)
