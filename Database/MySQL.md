* 관계형 데이터베이스라는 이론적 토대 위에서 만들어진 기술들

    * MySQL, Oracle, SQL Server, PostgreSQL, DB2, Access

* 대부분의 사용법이 동일하다는 특징이 있다.

# MySQL
* 무료, 오픈소스, 관계형데이터베이스의 주요한 기능을 대부분 갖추고 있는 준수한 관계형 데이터베이스 시스템

    * 상업적으로 사용할 때, 즉 MySQL SW를 대폭 수정 후 고객에게 배포할 때는 상업용 라이센스 구입이 필요

## 1. MySQL의 구조

* 데이터를 기록하는 최종적인 공간 -> 표(table)

* 관계형 데이터베이스는 스프레드 시트와 유사한 구조를 가지고 있다.

* 웹사이트 운영에 사용되는 데이터를 데이터베이스에 저장한다고 하면 post table, comment table, userInfo table 등이 생길 수 있다.

  -> 수많은 테이블이 생성될 텐데 정리가 필요함

  -> 연관된 표들을 그룹핑하고 이들을 연관되어 있지 않은 표들에서 분리하는데 사용하는 파일의 폴더 = 데이터베이스(또는 '스키마(schema)'라고 부름)

* 스키마들은 어딘가에 저장되어야 함 -> 데이터베이스 서버(database server)

![image](https://user-images.githubusercontent.com/27791880/174438794-c0913453-b856-43a4-9bff-06e552092906.png)

* MySQL을 설치한 행위 = 데이터베이스 서버라는 프로그램을 설치한 것. 이 프로그램이 가진 기능들을 활용해서 여러 작업을 진행.

## 2. 데이터베이스 사용의 장점

* 보안 : 자체적인 보안쳬계를 가지고 있어 안전한 보관 가능

* 권한 : MySQL에 여러 사람을 등록해서 어떤 데이터베이스에 접근이 가능한지, 그리고 데이터베이스 상에서 읽기, 쓰기, 수정, 삭제 중 어떠한 작업을 할 수 있는지 설정할 수 있다.

## 3. 스키마의 사용

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

## 4. SQL과 테이블의 구조

SQL이라는 컴퓨터 언어는 관계형 데이터베이스라는 카테고리에 속하는 제품들이 공통적으로 데이터베이스 서버를 제어할 때 사용하는 언어이다.

### 4.1. SQL: Structured

* 관계형 데이터베이스는 기본적으로 표의 형식으로 정보를 정리할 수 있다.

* 이 표를 작성하는 작업을 구조화되었다고 말한다. -> Structured

### 4.2. SQL: Query

* 데이터베이스에 데이터를 넣어주거나, 읽어주거나, 수정·삭제하거나, 스키마를 만들거나 하는 작업들을 요청한다. -> Query

### 4.3. SQL: Language

* 데이터베이스도 이해할 수 있고, 사람도 이해할 수 있는 공통의 약속에 따라 데이터베이스 서버에게 요청을 해야 서로가 알아들을 수 있다. 이 때 사용하는 언어가 SQL이라는 '언어'이다.

### 4.4. 용어 정리

row, record, 행

* 관계형 데이터베이스에서 로우는 데이터 하나하나를 생각하면 된다.

* 데이터 자체

column, 열

* 관계형 데이터베이스에서 컬럼은 데이터의 타입이라고 생각하면 된다.

* 데이터의 구조

## 5. 테이블의 생성

### 5.1. 만들려는 테이블

![image](https://user-images.githubusercontent.com/27791880/174543193-4dc66a76-7dd5-442f-96c7-6aa030f31ff5.png)

* 데이터베이스의 특징 중 하나는 컬럼의 data type을 강제할 수 있다.

* id값이 없다면 수정, 삭제가 불가능하기 때문에 필수적으로 포함되어야 한다.

* 반면, winner는 나중에 작성하는 것이 가능하다.

* NOT NULL : 값이 없는 것을 허용하지 않는다.

### 5.2. 3번째 행을 가장 효과적으로 지우는 방법은?

![image](https://user-images.githubusercontent.com/27791880/174543302-18d3a8b2-f9a6-45c1-9c95-0c0add566a1f.png)

* 만약 winner 칼럼이 'Charles_Leclerc'인 행을 삭제하라고 하면 같은 데이터를 가진 행이 있기 때문에 다른 행이 삭제될 수 있다.

* id값이 중복되지 않는 이상 틀림없이 `id=3`인 행이 삭제된다. 따라서, 데이터를 추가할 때 마지막 id값보다 1이 큰 id값을 채워주면 중복이 없게 된다.

* MySQL에서 id값이 자동으로 1씩 증가한다는 의미에서 `AUTO_INCREMENT`를 지정해주면 중복되지 않는 식별자를 가지게 된다.

### 5.3. PRIMARY KEY()

성능과 중복방지를 위해 사용해야 한다.

🔹성능

* MySQL의 InnoDB는 기본적으로 데이터를 저장하고 Indexing하기 위해 PK를 요구한다.

* 만약 명시적인 PK가 없다면 사용자에게 값이 노출되지 않더라도 InnoDB는 그 값을 생성한다. 이 때 생성된 값은 Auto Increment INT 또는 BIGINT보다 큰 값이다.

* PK를 설정하지 않을 경우 데이터의 실제 저장 정렬 순서를 보장할 수 없고, 경우에 따라 데이터 조회 시 DB가 보든 데이터를 조회하는 일이 발생할 수 있다.

🔹중복방지

* 위의 `f1_2022_results` 테이블을 예로 들었을 때, id값은 각 행을 식별하는 식별자의 역할을 한다. 따라서 중복된 값을 가질 수 없으며 Null값을 가질 수 없다.

* 관계형 데이터베이스에서 각 행이 고유하게 식별되는 규칙을 위반한다면, `관계형` 이라는 특징을 더 이상 가질 수 없으며 모호성이 발생한다. 중복 행을 생성하는 `JOIN Query`에서는 큰 문제가 발생하게 된다.

### 5.4. 참고: MySQL의 인덱스 - clustered index와 nonclustered index

**🔹Clustered Index (클러스터형 인덱스)**

* 키 값에 대한 테이블의 데이터 행을 정렬하는 인덱스의 한 유형

* 테이블의 데이터를 정렬해서 저장되는 순서를 정의한다.

* 테이블당 하나만 생성 가능

* 행 데이터를 인덱스로 지정한 열에 맞춰 자동 정렬

  * 영어 사전에서 알파벳 순으로 사전의 단어들이 정렬되어 있는 것과 유사

* PK에 의해 자동 생성

  * RDBMS에서는 일반적으로 PK를 사용하여 특정 열을 기반으로 클러스터링된 인덱스를 만들 수 있다.

* UNIQUE NOT NULL에 의해 자동으로 생성

* PK와 UNIQUE NOT NULL이 한 테이블에 함께 있을 경우 PK를 클러스터형 인덱스로 사용

**🔹Nonclustered Index (비클러스터형 인덱스 / 보조 인덱스)**

* 데이터와 인덱스를 서로 다른 위치에 저장

  * 인덱스는 해당 데이터의 위치에 대한 포인터를 포함하고 있다.

* 테이블당 여러 개 생성 가능

* 책의 맨 뒷장 색인에서 단어를 찾고 해당 페이지로 가서 관련 내용을 검색하는 것과 유사

* UNIQUE, UNIQUE NULL에 의해 자동 생성

* 별도의 인덱스가 생성되지만, 원본데이터 변경은 없음

## 6. CRUD (Create, Read, Update, Delete)

### 6.1. Create: INSERT

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

### 6.2. Read: SELECT

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

<br/>

### Ref.

[Table 작성 시 PK를 무조건 사용해야 하는 이유](https://hodongman.github.io/2019/01/14/Database-PK%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%B4%EC%95%BC-%ED%95%98%EB%8A%94-%EC%9D%B4%EC%9C%A0.html)

[인덱스(index),클러스터/보조인덱스](https://jie0025.tistory.com/107)

[MySQL/MariaDB Clustered vs Non-Clustered Index](https://estenpark.tistory.com/384)
