# 관계형 데이터 모델링

## 💡 용어 정리

### ◼️ Model

* 어떤 목적을 가지고 진짜를 모방한 것

    * 좋은 모델은 목적에 부합하는 모방

* 무한히 복잡한 현실을 정보로 만들어 표에 담는 것은 어려운 작업이고 이러한 현실의 복잡함을 컴퓨터에 담을 수 있는 방법론을 **데이터 모델링**이라고 한다.

### ◼️ 데이터 모델

* 데이터의 관계, 접근과 그 흐름에 필요한 처리 과정에 대한 추상화된 모형

* SW 개발과 유지, 보수의 기준이 됨

* 데이터의 구조를 결정하며, 그래픽 형태로 설명되는 데이터 모델링 개념에 정의되어 있음

* 관계형 데이터베이스와 같은 데이터 관리 시스템 상의 저장소에 담을 구조적인 데이터를 기술하는 것

### ◼️ 데이터 모델링

* 주어진 개념으로부터 논리적인 데이터 모델을 구성하는 작업

* 일반적으로 논리적인 데이터 모델을 물리적인 데이터베이스 모델로 환원하여 고객의 요구에 따라 특정 정보 시스템의 데이터베이스에 반영하는 작업을 포함한다.

### ◼️ 데이터베이스 모델

![640px-Database_models](https://user-images.githubusercontent.com/27791880/175544217-8ffa0f8f-6212-45a0-b365-844c73711ab5.jpg)

* 데이터베이스 관리 시스템이 지원하는 공식 언어로 기술된 데이터베이스의 구조나 형식

* 데이터베이스 관리 시스템과 결합하는 데 쓰이는 데이터 모델의 응용

* 데이터베이스가 어떻게 구조화되고 쓰이는지를 알려주는 이론이나 상세한 설명을 말한다.

    e.g., 계층형 모델, 네트워크형 모델, 관계형 모델, 객체-관계형 모델, 객체형 모델

### ◼️ 관계형 모델

* 술어 논리와 집합론에 기반을 둔 일종의 데이터베이스 모델

* 데이터를 column과 row를 이루는 하나 이상의 테이블(또는 관계)로 정리하며, 고유 키가 각 row를 식별한다.

* row는 record나 tuple로도 불린다.

# 1. 데이터 모델링의 순서

> 데이터 모델링의 순서는 다음과 같다.
>
> 업무파악 ➡️ 개념적 데이터 모델링 ➡️ 논리적 데이터 모델링 ➡️ 물리적 데이터 모델링

## 1.1. 업무파악

* 의뢰한 사람이 어떤 것을 만들고 싶은지 찾아내는 과정

* 기획서나 화면구성을 담은 프로토타입을 받을 수 있음

## 1.2. 개념적 데이터 모델링

* 만들게 될 결과물에는 어떤 개념들이 있고 각각의 개념들은 서로 어떻게 상호작용하고 있는지를 심사숙고 해보는 시간

* ER 다이어그램을 작성 (다음 과정으로 넘어가기 위한 초석)

    ![539px-ER_Diagram_MMORPG](https://user-images.githubusercontent.com/27791880/175760008-22431976-1ab3-4dfe-9258-076ad19a1e4b.png)

## 1.3. 논리적 데이터 모델링

* ER 다이어그램에서 다룬 각 개념들을 관계형 데이터베이스 패러다임에 맞는 표로 전환한다.

## 1.4. 물리적 데이터 모델링

* 어떤 데이터베이스 제품을 사용할 것인가를 선택하고 그 데이터베이스 제품에 최적화된 코드를 작성해서 실제 표를 만드는 것이 물리적 데이터 모델링 단계에서 하는 일

* 이 때의 산출물은 표를 생성할 수 있는 SQL 코드이다.

<br/>

# 2. 개념적 데이터 모델링 1

* 파악한 업무에서 개념을 추출하는 작업

* ERD는 복잡한 현실을 3개의 관점으로 바라볼 수 있는 파인더를 제공해준다.

    ![image](https://user-images.githubusercontent.com/27791880/175764078-9f389038-7146-4e26-990e-3ec3f63c4d89.png)

    * 정보 : 정보를 발견하고 그것을 다른 사람들에게 표현할 수 있게 도와준다. (e.g., 본문, 작성일, 작성자, ...)

    * 그룹 : 서로 연관된 정보를 그룹핑해서 인식하고 이것을 다른 사람들에게 표현할 수 있게 해준다. (e.g., 댓글, 저자, 글)

    * 관계 : 정보와 그룹 사이의 관계를 인식하고 그것을 다른 사람에게 표현할 수 있게 해준다. (e.g., 속하다, 작성한다)

* 현실로부터 개념을 인식하는 도구이면서 그것을 다른 사람도 알아볼 수 있게 표현하는 도구를 Entity-Relational Diagram(ERD)이라고 한다.

* ERD에는 위와 같이 작성한 관계를 매우 쉽게 표로 전환하는 기능도 제공한다.

    ![image](https://user-images.githubusercontent.com/27791880/175764154-5e221ccb-4c07-4ce1-ae5f-08f2506880fb.png)


## 2.1. 관계형 데이터베이스 다운 개념의 구조

* RDB는 내포관계를 허용하지 않음

    ![RDB는 내포관계 지원no_github](https://user-images.githubusercontent.com/27791880/175771799-a5f49ecf-6857-4fab-aeb1-47de268e6906.png)

* 내포관계가 표현된 테이블을 하나의 테이블로 표현을 하게 되면 중복이 발생한다.

    ![거대단일테이블중복발생](https://user-images.githubusercontent.com/27791880/175773563-2479caa5-5ffb-4439-94da-f6b7020f1a83.png)

* 위의 2가지 문제를 해결하기 위해서는 주제에 따라서 테이블을 분리한다.

    ![RDB테이블분리](https://user-images.githubusercontent.com/27791880/175773988-640910e9-941c-4ff4-8f1c-ad5a8b1523c7.png)

* 테이블을 분리했을 때의 장점

    1. 주제에 따라서 데이터를 그룹핑 할 수 있다.

    2. 만약 특정 주제(e.g., 글, 저자, ...)에 대한 정보만이 필요하다면 해당 주제만을 담고 있는 테이블을 조회해서 컴퓨터의 자원을 아낄 수 있다.

    3. JOIN

        * 어떤 댓글의 저자는 누구이고, 그 저자의 정보가 함께 나왔으면 좋겠다.

            ![댓글JOIN저자](https://user-images.githubusercontent.com/27791880/175774204-d629a34a-f101-4cd4-bd7e-751181495242.png)

            ```mysql
            SELECT 댓글.내용, 댓글.작성일, 저자.이름, 저자.소개
            FROM 댓글
            LEFT JOIN 저자
            ON 댓글.저자 아이디 = 저자.아이디
            ```

## 2.2. ERD의 구성요소

### ◼️ Entity (➡️ Table로 표현됨)

* 웹사이트를 제작한다고 했을 때 댓글, 글, 저자 등은 Entity라고 불린다.

* Entity는 후에 Table로 전환될 수 있다.

* '글'이라는 entity는 실제 데이터가 아니다. 구체적인 데이터는 제목, 생성일, 본문 등에 담겨 있다. 이러한 것들을 그룹핑한 것이 글이라는 엔티티에 그룹핑 된 것이다.

### ◼️ Attribute (➡️ Column으로 표현됨)

* 글 엔티티의 attribute(속성)는 제목, 본문, 생성일이 있을 수 있다.

* 이 attribute들은 나중에 테이블의 column이 된다.

### ◼️ Relation (➡️ PK, FK로 표현됨)

![ERD의 구성요소 - 관계](https://user-images.githubusercontent.com/27791880/175775219-f4ccf341-bac0-4159-b838-5d967f856ff0.png)

* 저자와 글, 글과 댓글, 저자와 댓글 사이에는 특정한 관계를 가지고 있다.

* 연관성을 표현해준 것을 관계라고 표현하고 표에서는 기본키, 외래키라는 형태로 이 관계가 표현된다.

* 이렇게 저장된 데이터를 기반으로 JOIN을 통해서 동적으로 테이블들을 연결하게 된다.

## 2.3. 식별자

* 원하는 대상을 정확하게 타겟팅하는 것이 목적이다.

* 식별자가 되기 위해서는 그 대상을 제외한 누구도 같은 값을 가지고 있으면 안된다. (e.g., 주민등록번호)

* ER 다이어그램에서도 어떤 속성을 식별자로 지정할 것인지 정해야 한다. 이 때 지정한 식별자는 이후에 Primary Key(기본키)가 된다.

| user_id | email      | national_id | name  | city        |
|---------|------------|-------------|-------|-------------|
| 1       | a@mail.com | 100001      | kim   | nebraska    |
| 2       | b@mail.com | 100002      | saul  | albuquerque |
| 3       | c@mail.com | 100003      | white | albuquerque |

* 특정 테이블에 담긴 칼럼들 중에서 마땅히 식별자로 활용할 것이 없다면 행이 추가될 때마다 자동으로 1씩 증가하게 만들어(`AUTO INCREMENT`) 중복되지 않는 값을 식별자('인조키'라고 부른다.)로 제공할 수도 있다.

    * 위의 표에서는 `user_id` 컬럼을 `AUTO INCREMENT`를 적용한 식별자로 활용할 수 있다.

### ◼️ 후보키 (candidate key)

---

* 식별자가 될 수 있는 키를 후보키라고 한다.

* 위의 표에서 후보키는 `user_id`, `email`, `national_id`가 있다.

### ◼️ 기본키 (primary key)

---

* 후보키 중에서 최종적으로 선택한 식별자를 **기본키** 라고 한다. (e.g., `user_id`)

* 기본키가 아닌 키들은 **대체키(alternate key)** 라고 한다. (e.g., `email`, `national_id`)

* 대체키는 추후에 성능 향상을 위해서 **secondary index** 로 지정하기에 좋다.

### ◼️ 중복키 (composite key, 복합키)

---

부서 별 직원 명단 테이블

| emp_no(직원번호) | dept_no(부서번호) | from_date(부서배정일) |
|------------------|-------------------|-----------------------|
| 1                | 1                 | 2010                  |
| 2                | 1                 | 2011                  |
| 1                | 2                 | 2013                  |

* 위의 테이블에서는 어떤 한 컬럼만을 가지고 식별하는 것이 불가능하다.

* `emp_no`와 `dept_no` 두 개의 컬럼을 합쳐야 식별하는 것이 가능하다.

* 이처럼 두 개 이상의 컬럼을 키로 지정한 것을 **Composite Key** 라고 부른다.

## 2.4. Relationship

![엔티티간의 연결](https://user-images.githubusercontent.com/27791880/175781213-339e3271-de82-4b9b-a3de-fa302a430a89.png)

* 각각의 표들은 데이터로써 연결되어 있다.

* 각각의 표의 행을 식별하는 유일무이한 식별자를 Primary Key라고 한다.

### ◼️ 외래키 (Foreign Key)

* 위의 그림에서 `저자` 테이블의 `아이디` 값을 가리키는 `글` 테이블의 `저자 아이디`의 값을 외래에 있는 테이블과 연결할 때 사용하는 키인 **외래키(Foreign Key, FK)**라고 부른다.

* `FOREIGN KEY` 제약 조건을 설정한 필드를 외래 키라고 부르며, 한 테이블을 다른 테이블과 연결해주는 역할을 한다.

* 외래키가 설정된 테이블에 레코드를 입력하면, 기준이 되는 테이블의 내용을 참조해서 레코드가 입력된다.

    * FOREIGN KEY 제약 조건은 하나의 테이블을 다른 테이블에 의존하게 만든다.

* FK 제약 조건을 설정할 때 참조되는 테이블의 필드는 반드시 UNIQUE나 PK 제약 조건이 설정되어 있어야 한다.

## 2.5. Cardinality



### Ref.

* [생활코딩 - 관계형 데이터 모델링](https://opentutorials.org/module/4134)

* Wikipedia

* [TCP SCHOOL - MySQL](http://tcpschool.com/mysql/intro)