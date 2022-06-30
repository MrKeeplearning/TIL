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

<br/>

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

# 2. 개념적 데이터 모델링

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

> Cardinality의 사전적 정의
>
> : the number of elements in a set or other grouping, as a property of that grouping
> 즉, 1,2,3,4,5,...와 같은 숫자들을 말한다.

ER 다이어그램에서 각 엔티티 사이의 관계는 다음과 같은 기호로 표시한다.

![ERD-Notation](https://user-images.githubusercontent.com/27791880/175819725-d0402279-2950-4b2b-a206-bcf0e0faa5c4.png)

### ◼️ 1:1 관계

---

![카디널리티2](https://user-images.githubusercontent.com/27791880/175870459-a54769d1-0344-4d30-894a-1b6087ab9962.png)

각 선생님은 하나의 학급만을 담당한다. Kim 선생님이 1반을 맡았다면 다른 반은 맡을 수 없다.

따라서, 담임에게 반은 1개이다.

각 반의 담임은 한 명이다. 1반의 담임이 Kim 선생님이라면 Lee나 Park 선생님은 담임이 될 수 없다.

### ◼️ 1:N 관계 (일대다 관계)

---

![1대N관계_1](https://user-images.githubusercontent.com/27791880/175819443-bf28c026-4446-417d-af3f-fd377556b136.png)

저자는 여러 개의 댓글을 작성하는 것이 가능하다. 따라서, 저자에게 댓글은 N개이다.

각 댓글은 하나의 저자만 존재한다. 이 경우 댓글에게 저자는 1개이다.

이러한 경우를 **일대다 관계**라고 일컫는다.

### ◼️ N:M 관계 (다대다 관계)

---

만들려는 서비스에서 하나의 글을 여러 명이 편집할 수 있다고 가정해보자.

![다대다관계](https://user-images.githubusercontent.com/27791880/175873428-2db0304a-00bd-456b-80ed-df56ce389e43.png)

저자는 여러 개의 글을 작성할 수 있다. 따라서 저자에게 글은 여러 개라는 뜻에서 M개 라고 표현할 수 있다.

글에게 저자는 여러 명이 존재할 수 있다. 따라서 글에게 저자는 여러 개이기에 N개 라고 표현할 수 있다.

이러한 관계를 **N:M 관계** 또는 **다대다 관계**라고 표현한다.

> 💡 현실의 데이터베이스에서는 다대다관계를 표현할 수 없다.
>
> 데이터베이스 상에서 다대다관계를 표현할 때는 다대다 상태로 두지 않고, 중간에 **연결테이블**이라고 하는 특별한 테이블을 만들어서 최종적으로는 일대다 관계로 변환한다.

## 2.6. Optionality

![Optionality1](https://user-images.githubusercontent.com/27791880/175876835-92cad9af-935b-4be8-8ae7-0149b0b33626.png)

### ◼️ Optional

---

어떤 시스템에 저자로 등록을 하면, 반드시 댓글을 작성해야할 의무는 없다.

따라서 **"저자에게 댓글은 옵션이다(Optional)"** 라고 해석할 수 있다.

ER 다이어그램 상에서는 다음과 같이 기호로 표시한다.

![ERD-Optional1](https://user-images.githubusercontent.com/27791880/175875994-7efa508f-c773-4355-8f95-ee01c8d2fb56.png)

### ◼️ Mandatory

---

모든 댓글에는 저자가 무조건 존재한다. 따라서 댓글에게 저자는 **필수(Mandatory)** 이다.

Mandatory는 ER 다이어그램 상에서 다음과 같이 표현할 수 있다.

![mandatory1](https://user-images.githubusercontent.com/27791880/175886047-db1c8537-decf-4ce8-9082-ac8fa4748319.png)

저자와 댓글 사이에는 Optional과 Mandatory 관계 뿐만 아니라 동시에 일대일, 일대다, 다대다 등의 카디널리티도 존재한다.

Optionality와 Cardinality 특성을 모두 반영하면 다음과 같다.

* 저자에게 댓글은 여러 개이다. 그리고 댓글에게 저자는 1명뿐이다. ➡️ **1:N 관계**

* 저자는 댓글을 작성할 수도 작성하지 않을 수도 있다. ➡️ **Optional**

* 하나의 댓글에는 무조건 1명의 저자가 존재한다. ➡️ **Mandatory**

![Optionality2](https://user-images.githubusercontent.com/27791880/175887360-dfd102c1-da24-40ec-ae47-7dda3d5840b1.png)

<br/>

# 3. 논리적 데이터 모델링

* 개념적 데이터 모델링이 잘 되어 있다면 논리적 데이터 모델링은 상당히 기계적인 작업이다.

* 개념적 데이터 모델링이 업무에서 개념을 추출하는 작업이라면, 논리적 데이터 모델링은 그렇게 추출한 개념을 관계형 데이터베이스 패러다임에 어울리게 데이터의 형식을 잘 정리정돈하는 것이다.

* 이 과정에서는 구체적인 데이터베이스 제품의 특성이나 성능과 같은 것은 고려사항이 아니다. 대신, **관계형 데이터베이스 패러다임에 어울리는 이상적인 모습으로 개념을 잘 정리정돈하는 것** 이 포인트이다.

### Mapping Rule

* ER 다이어그램을 통해서 표현한 내용을 관계형 데이터베이스에 맞는 형식으로 전환할 때 사용하는 방법론이다.

* 맵핑 룰은 다음과 같은 흐름을 따르면 된다.

    * Entity는 Table로 전환한다.

    * Attribute는 Column으로 전환한다.

    * Relation은 PK와 FK로 전환한다.

다음과 같은 구조를 가지는 개념적 데이터 모델을 논리적 데이터 모델로 변경하는 과정을 다룬다.

![관계형 데이터 모델링 drawio](https://user-images.githubusercontent.com/27791880/175991456-24612a80-28a9-4221-b2c3-61ff4d99ae9c.png)

## 3.1. 테이블과 컬럼 생성

※ 실습은 [ERDCloud](https://www.erdcloud.com/)라는 서비스에서 진행했다.

### 💡TIPS 

* 논리적 데이터 모델링을 진행할 때 **FK가 없는 테이블**부터 만드는 것이 편하다.

* 어떠한 속성에 대한 제약조건(e.g., "정수여야 한다", "문자 길이는 얼마여야 한다", "UNIQUE해야 한다" 등)을 설정한다는 것을 해당 컬럼에 대한 **"도메인"** 을 설정한다고 말한다.

위에서 다룬 개념적 데이터 모델의 각 엔티티 별 테이블을 생성하면 다음과 같다.

![Open tutorials ERD (1)](https://user-images.githubusercontent.com/27791880/175991278-8cb80f86-f102-4820-9ea2-176b3823f6c1.png)

## 3.2. 1:1 관계의 처리

PK와 FK를 연결하는 것을 통해 관계형 데이터베이스 모델에 맞는 논리적 데이터 모델로 변경하는 작업에서 가장 중요한 것은 Cardinality를 따져보는 것이다.

1:1 관계, 1:N 관계, N:M 관계 순으로 작업의 난이도가 상승한다.

![Open tutorials ERD author and inactivity](https://user-images.githubusercontent.com/27791880/175992422-43892f45-eaa8-4925-9451-67beeff210d0.png)

저자 테이블과 휴면계정 테이블은 서로 일대일 관계를 가지고 있다. 이 둘 사이는 id값을 기준으로 연결되어 있는데, 어디에 FK를 부여해야 하는지 헷갈릴 수 있다.

**이 때는 누가 누구에게 의존하는지를 따져보면 된다.**

저자는 휴면계정의 id 값이 무엇인지 상관없이 id값을 추가할 수 있다.

반면, 휴면계정 테이블에서 id값을 추가하는 작업은 반드시 해당 아이디가 저자 테이블에도 있어야 한다.

즉, 휴면계정은 저자가 누구인지 반드시 알아야 한다. 휴면계정은 저자에 의존하고 있다고 할 수 있다.

💡 혼자서 지낼 수 없는 의존하고 있는 테이블 = **자식 테이블** (e.g., 휴면계정 테이블)

💡 혼자서 지낼 수 있는 테이블 = **부모 테이블** (e.g., 저자 테이블)

결과적으로 **부모 테이블에 PK를, 자식 테이블에 FK를 설치하면 된다.**

![Open tutorials ERD author and inactivity(3)](https://user-images.githubusercontent.com/27791880/176096699-07c2a0e2-8831-49a0-8d54-2a6f06b25aeb.png)

## 3.3. 1:N 관계의 처리

* 1명의 저자는 댓글을 작성하지 않을 수도 있고 1개 이상의 댓글을 작성할 수도 있다. 한편 댓글에는 무조건 1명의 저자가 필요하다.

    ➡️ 일대다 관계

* 하나의 글에는 댓글이 없을수도 있고 1개 이상의 댓글이 있을 수도 있다. 한편, 댓글에는 무조건 하나의 글 밑에 달릴 수 있다(서비스 전체의 댓글이 아닌 특정 글의 댓글일 때).

    ➡️ 일대다 관계

![Open tutorials ERD (2)](https://user-images.githubusercontent.com/27791880/176097104-e41ab449-428f-4b8f-8c6a-2303b4a43e35.png)

## 3.4. N:M 관계의 처리

`author` 테이블과 `topic` 테이블에 다음과 같이 데이터가 담겨 있다고 가정해보자.

![다대다 관계의 처리1](https://user-images.githubusercontent.com/27791880/176114541-38f33076-e65c-4575-a7ac-706015ce3960.png)

그리고 `Kim`은 MySQL, SQL Server, ORACLE 주제의 글을 작성했고 `Lee`는 MySQL, SQL Server 주제의 글을 작성했다고 가정한다.

이러한 상황을 참고했을 때 저자는 여러 개의 글을 작성했고, MySQL과 SQL Server 글은 Kim과 Lee 모두 같은 글을 작성 및 편집했기 때문에 `author`와 `topic`테이블이 다대다 관계를 가진다고 볼 수 있다.

### ◼️ topic테이블에 author테이블 정보를 적는 방법

---

두 테이블의 관계를 테이블로 표현할 때 author 테이블의 정보를 topic 테이블에 넣어서 표현하려면 다음과 같이 표현된다.

![다대다 관계의 처리2](https://user-images.githubusercontent.com/27791880/176114337-780dae4c-94b2-4e1a-b3ae-e4d873db0a65.png)

`author_id`에 콤마가 들어간 경우 `JOIN`이 불가능해진다. 정렬을 해도 이상하게 되고, `author_id = 1`인 사용자의 글을 가져올 때도 이상해진다.

### ◼️ author테이블에 topic테이블 정보를 적는 방법

---

![다대다 관계의 처리3](https://user-images.githubusercontent.com/27791880/176116516-56634adc-4ea9-4c47-9840-0525d393273b.png)

위에서 topic테이블에 author테이블 정보를 적는 방법에서와 마찬가지로 JOIN이 불가능해지는 등의 문제가 발생한다.

### ◼️ 다대다 관계에서는 `Mapping Table`을 사용한다!

---

다대다 관계에서는 맵핑 테이블(연결 테이블)을 사용해서 각 테이블을 연관짓는다.

![다대다 관계의 처리4](https://user-images.githubusercontent.com/27791880/176120576-300b1091-3e31-490a-b9f3-ec335b8583e3.png)

맵핑 테이블을 작성하게 되면 2개의 테이블이 결합되었을 때 의미있는 정보(e.g., 각각의 저자가 해당 글을 언제 수정하기 시작했는가)들을 추가할 수 있다.

### ◼️ 논리적 데이터 모델링 완성

---

* 자는 작성을 하지 않을 수 있기 때문에 author에게 write는 Option이 될 수 있다.

* 만약 작성을 했다면 write에게 author는 필수이다.

* 현재 글은 여러 사람이서 하나의 글을 같이 작성하는 것이 가능한 상태이다. 따라서 하나의 글(topic)이 존재하면 write는 1개 이상은 무조건 가지고 있다. 반대로 write가 있어야 topic이 존재할 수 있다.

따라서 author와 write, 그리고 write와 topic은 다음과 같은 관계를 가진다.

> author (1) ---- (0..N) write
>
> write (1..N) ---- (1) topic 

![Open tutorials ERD (3)](https://user-images.githubusercontent.com/27791880/176125601-4de49336-a322-4328-a906-084b53ace4ef.png)

<br/>

# 4. 논리적 데이터 모델링 - 정규화(Normalization)

### ◼️ 정규화

* 관계형 데이터베이스 설계에서 중복을 최소화할 수 있도록 데이터를 구조화하는 프로세스

* 일반적으로 크고, 제대로 조직되지 않은 테이블들과의 관계들을 작고 잘 조직된 테이블과 관계들로 나누는 것을 포함

* 비공식적으로 관계형 데이터베이스 테이블이 제 3정규화(3NF)가 되었으면 정규화 되었다고 한다. 3NF 테이블의 대부분이 삽입, 삭제, 변경에 이상이 없고 BCNF, 4NF, 5NF에 해당한다.

* 제 4 정규화(4NF) 이상의 정규형은 현실에서 적용할 만한 문제가 거의 발생하지 않기 때문에 주로 학문적 관심의 측면에서 가치가 있다.

![정규화 단계](https://user-images.githubusercontent.com/27791880/176193569-7cb687ae-54af-46bc-8323-893334d49224.png)

※ 사진 출처: [Wikipedia Database normalization](https://en.wikipedia.org/wiki/Database_normalization)

* 에드거 F.커드는 1970년 제 1 정규화(1NF)로 알려진 정규화의 개념을 도입했고 이어 제 2 정규화와 제 3 정규화를 정의하며 정규화의 개념을 발전시켰다.

* 정규화 프로세스는 점진적으로 발전되는 형태를 띄고 있기 때문에 이전 단계의 조건을 만족하지 못하면 상위 수준의 정규화를 실현할 수 없다.



### ◼️ 정규화의 목표

* 이상이 있는 관계를 재구성해서 작고 잘 조직된 관계를 생성하는 것

### ◼️ 정규화의 목적

* 하나의 테이블에서 발생한 데이터의 삽입, 삭제, 변경이 정의된 관계들에 따라 데이터베이스의 나머지 부분들로 전파되는 것

## 4.1. 제 1 정규화

### 제 1 정규화의 원칙

➡️ Atomic columns : **테이블의 각 컬럼에 단일 값이 있어야 한다.** 값의 집합 또는 중첩된 레코드가 포함된 열은 허용되지 않는다.

![1NF - Unnormalized form1](https://user-images.githubusercontent.com/27791880/176198339-85e1e679-b2b5-4edd-9417-fef8dd9f4946.png)

위의 테이블은 정규화되지 않은 상태의 테이블이다.

여기서 atomic columns에 해당하지 않는 것은 `tag` 칼럼이다.

만약, 원하는 데이터 자체가 여러 개의 값을 가지고 있는 것 자체가 하나의 값이면서 더 이상 쪼갤 필요가 없는 값이라면 문제가 없다. 

하지만, 다음과 같은 연산을 할 경우 문제가 될 수 있다.

```mysql
SELECT * FROM topic WHERE tag='free' 
```

`free`라는 값을 가진 행을 원한다면 위와 같은 SELECT문은 정규화되지 않은 테이블에서 사용이 불가능하다.

또한 다음 연산에서도 문제가 발생할 수 있다.

```mysql
SELECT * FROM topic ORDER BY tag
```

만약 tag에 담긴 문자열의 첫 글자를 기준으로 정렬을 한다면 큰 문제는 없다. 하지만, `free`나 `commercial`도 엄연히 하나의 값이고 이를 기준으로 정렬을 하고 싶을 때는 문제가 복잡해진다.

하나의 컬럼 안에 여러 개의 값이 들어가 있다면 `JOIN`을 하는 것이 어렵거나 불가능해진다. 즉, 각각의 컬럼의 값이 atomic 하지 않다면 여러 가지 문제점을 유발할 수 있기 때문에 해결되어야 하는 상태이다.

이처럼 `Atomic Columns`를 만족하도록 정규화시켰을 때 제 1 정규화의 원칙에 부합한다고 할 수 있다.

### ◼️ 제 1 정규화의 적용

---

위에서 정규화되지 않은 topic 테이블의 첫 번째 row만 우선적으로 제1정규형(1NF)에 맞게 수정을 해본다. 방법은 여러 가지가 있다.

![1NF-1](https://user-images.githubusercontent.com/27791880/176378705-ebf7aec2-44f9-4512-bd8a-ec5fe0263440.png)

위처럼 atomic columns를 만족하도록 수정을 하면 1NF에 해당하지만 여전히 tag를 제외한 컬럼들은 중복된 컬럼이기 때문에 문제가 있다.

![1NF-2](https://user-images.githubusercontent.com/27791880/176379435-586dfc6a-d372-467f-bd34-c7085b061f74.png)

위의 경우도 제 1 정규형을 만족하지만 여전히 문제가 있다.

예를 들어, 태그를 추가하게 되면 테이블의 컬럼 전체를 변경하게 된다. 즉, 컬럼의 구조를 변경하게 된다.

한편 `tag2`의 값까지 채우지 않고 `tag1`만 필요하게 되면 `tag2`에는 NULL이 들어가게 되고 공간을 낭비하게 된다.

<br/>

### 💡 이러한 문제를 해결하기 위해서는 **테이블을 분리**하면 된다. ➡️ `Topic`과 `Tag`로 분리

<br/>

하나의 `Topic`은 여러 개의 `Tag`를 가질 수 있다.

하나의 `Tag`는 여러 개의 `Topic`을 가질 수 있다.

따라서 `Topic`테이블과 `Tag`테이블은 카디널리티가 N:M이라고 할 수 있다. 이러한 카디널리티에서는 **Mapping Table** 이 필요하다.

`tag` 컬럼은 `title`에 의존하고 있다. 따라서 topic 테이블은 중복키를 primary key로 사용하고 있지만 그 중에서 title만을 key로 가져온다.

![1NF-3](https://user-images.githubusercontent.com/27791880/176438632-8b16a679-3d9e-4e0a-b937-a81af4867c72.png)

## 4.2. 제 2 정규화

### 제 2 정규화의 원칙 ➡️ No partial dependencies

제 2 정규형(2NF)을 만족하기 위해서는 '부분 종속성이 없어야 한다'는 조건을 지켜야 한다.

![2NF-1](https://user-images.githubusercontent.com/27791880/176445072-6dc606cb-82c2-45eb-9d8b-ca8e5f582c94.png)

위에서 제 1 정규화를 마친 2개의 테이블과 이 둘의 맵핑 테이블(`topic_tag_relation`)이다.

### ◼️ 부분 종속성

---

topic 테이블을 살펴보면 노란색으로 표시된 부분이 중복된 부분이다. 이 부분에서 중복이 발생하는 이유는 **부분 종속성**때문이다.

색으로 표시된 중복된 행은 모두 `title=MySQL`에 해당한다. 즉, MySQL이라는 제목을 가지고 있다면 PK를 통해서 색으로 표시된 행을 얻을 수 있다.

### 💡이 말을 다시 부분 종속성과 연관지어 말하면, `description`, `created`, `author_id`, `author_name`, `author_profile`은 `title`이라는 컬럼에만 부분적으로 종속되어 있다.

한편, `price`컬럼은 MySQL의 컨텐츠 타입(`type`)이 인쇄물인지 온라인인지에 따라 달라진다.

결과적으로, topic 테이블은 `title`, `type`, `price`를 위한 테이블인 것이다.

### ◼️ 부분 종속성 적용

---

>💡 부분적으로 종속되는 컬럼들만 모으고, 전체적으로 종속되고 있는 컬럼을 따로 분리한다.

topic이라는 테이블은 부분적으로 종속되는 정보들만 가지고 있다(중복행 없음).

![2NF-2](https://user-images.githubusercontent.com/27791880/176462655-6c237e44-46e1-4bd2-ae97-05028419a296.png)

* `title = MySQL`에 해당하는 중복되는 행은 더 이상 type 컬럼을 가지고 있지 않기 때문에 하나만 표시한다.

![2NF-3](https://user-images.githubusercontent.com/27791880/176462796-9e35579e-34d7-43d2-80ab-2b0aef439929.png)

* price는 type에 의존하기에 위와 같이 표현할 수 있다.

### ◼️ 제 2 정규화를 마친 전체 테이블

---

![2NF-4](https://user-images.githubusercontent.com/27791880/176463601-8fb33109-21b5-44f2-bf4c-79ea8774c815.png)

## 4.3. 제 3 정규화

<br/>

### 제 3 정규화의 원칙 ➡️ No transitive dependencies(이행적 종속성)

<br/>

![2NF-topic](https://user-images.githubusercontent.com/27791880/176478786-47cbcf52-c840-4b3f-b16a-8d7698111cf2.png)

* 위의 제 2 정규화를 마친 상태의 topic 테이블에서 `title=MySQL`인 행은 title 이라는 기본 키에 종속되어 있다.

* `author_id`는 `title`에 의존하고, `author_name`과 `author_profile`은 `author_id`에 의존하는 관계를 **이행적 종속성**이라고 한다.

* 이행적 종속성을 가지고 있다면 테이블에 문제가 있다는 증거이다.

![2NF-topic colored](https://user-images.githubusercontent.com/27791880/176480411-ee2a603f-3ec9-48d3-aa29-9170ae994008.png)

중복이 되는 행은 분리한다.

![3NF-1](https://user-images.githubusercontent.com/27791880/176481814-2c084bf5-f027-4f9b-b80b-88b9cdfc2a2d.png)

<br/>

# 5. 물리적 데이터 모델링

* 물리적 데이터 모델링 : **이상적인 표를 구체적인 제품에 맞는 현실적인 표로 만드는 작업**

    (*cf.* 논리적 데이터 모델링 : 관계형 데이터베이스에 맞는 이상적인 표를 만드는 작업)

* 물리적 데이터 모델링의 초점 : **성능**

* 데이터가 쌓이고 처리량이 많아지면 여러 쿼리가 동작할 때 특히 느려는 쿼리(Slow Query)가 발생

* Slow Query가 발생한 병목지점을 찾았다면 성능 개선을 위한 작업을 진행

    * Index

    * Cache

    * Denormalization(역정규화 or 반정규화) ➡️ 다른 개선 방법을 사용해봐도 해결이 안될 때 가장 마지막으로 사용하는 것을 권장

## 📌 Index

* 비약적으로 행에 대한 **읽기 성능을 향상**시키지만 **쓰기 성능을 희생**시킴

    ### 쓰기 성능의 희생

    * 쓰기가 이루어질 때마다 행에 인덱스가 걸려있다면 입력된 정보를 정리하기 위한 복잡한 연산과정이 필요

    * 이 과정에서 많은 시간을 소요할 뿐만 아니라 저장공간을 더 많이 차지하기도 함

    ### 읽기 성능의 향상

    * 잘 정리된 상태는 엄청나게 빠른 상태로 읽을 수 있기 때문에 쓰기 성능에 대한 희생이 무의미하진 않다.

## 📌 Cache

* 인덱스만으로 성능을 향상이 힘들다면 데이터베이스를 이용하고 있는 어플리케이션 영역에서 캐시(Cache)를 활용하는 방법도 있다.

* 입력에 따른 실행결과를 저장해두었다가 나중에 동일한 입력이 들어왔을 때 저장해둔 결과를 사용하는 것을 통해서 데이터베이스에 부하를 주지 않는 것이다.

* 데이터베이스가 겪게 되는 부하를 획기적으로 줄일 수 있다.

## 📌 Denormalization(역정규화)

* Index나 Cache와 같이 여러 가지 방법을 적용해도 성능향상에 어려움이 있다면 표의 구조를 바꾸는 역정규화를 시도한다.

* 정규화된 데이터베이스에서 성능이나 개발의 편의성을 개선하기 위해 사용하는 전략

* 일부 쓰기 성능의 손실을 감수하고 데이터를 묶거나 데이터의 복제 사본을 추가함으로써 데이터베이스의 읽기 성능을 개선하려고 시도하는 과정

    * 정규화는 표를 분할하는 작업이고, 이 표들을 JOIN해서 활용해야 하는데, JOIN은 굉장히 비싼 작업에 속한다. 결과적으로 읽기의 성능을 희생하게 된다.

* 많은 수의 읽기 작업을 처리할 필요가 있는 관계형 데이터베이스 소프트웨어의 성능이나 스케일링에서 고려됨

* 효율적인 역정규화를 위해서는 정규화가 선행되어야 함

* 정규화되지 않은 것이 좋은 것이라고 할 수 없고, 정규화가 반드시 성능을 하락시키는 것도 아니다.

## 5.1. 역정규화 - Column을 조작해서 JOIN을 줄이기

사용할 전체 테이블

![역정규화-1](https://user-images.githubusercontent.com/27791880/176687151-57eece3b-5765-4e1a-b6e1-c8d8b4a10c35.png)

💡 진행할 작업

* `topic_tag_relation` 테이블에서 `topic_title = MySQL`인 행의 태그를 확인하고 싶다.

* `topic_tag_relation` 테이블에는 태그의 id값만 있고 실제 이름은 id값과 연결된 `tag`라는 테이블에 있다.

* 따라서 JOIN을 활용해야 원하는 값을 확인가능

```mysql
mysql> SELECT tag.name
    -> FROM topic_tag_relation AS ttr
    -> LEFT JOIN tag
    -> ON ttr.tag_id = tag.id
    -> WHERE topic_title = 'MySQL';
+------+
| name |
+------+
| rdb  |
| free |
+------+
2 rows in set (0.00 sec)
```

서비스를 운영하다 보면 위와 같은 조회가 굉장히 많이 발생한다. 이것으로 인해서 시스템의 부하가 심해지고, 사용자의 경험이 안좋아질 수 있다.

💡 해결책

* 중복을 허용한다.

* topic_tag_relation 테이블에서 tag_id에 해당하는 name컬럼을 추가한다.

`topic_tag_relation`테이블에 역정규화를 적용한다.

```mysql
ALTER TABLE `topic_tag_relation` ADD COLUMN `tag_name` VARCHAR(45) NULL AFTER `tag_id`;

UPDATE `topic_tag_relation` SET `tag_name` = 'rdb' WHERE (`topic_title` = 'MySQL') and (`tag_id` = '1');
UPDATE `topic_tag_relation` SET `tag_name` = 'free' WHERE (`topic_title` = 'MySQL') and (`tag_id` = '2');
UPDATE `topic_tag_relation` SET `tag_name` = 'rdb' WHERE (`topic_title` = 'ORACLE') and (`tag_id` = '1');
UPDATE `topic_tag_relation` SET `tag_name` = 'commercial' WHERE (`topic_title` = 'ORACLE') and (`tag_id` = '3');
```
```mysql
mysql> SELECT * FROM topic_tag_relation;
+-------------+--------+------------+
| topic_title | tag_id | tag_name   |
+-------------+--------+------------+
| MySQL       |      1 | rdb        |
| MySQL       |      2 | free       |
| ORACLE      |      1 | rdb        |
| ORACLE      |      3 | commercial |
+-------------+--------+------------+
4 rows in set (0.00 sec)
```
```mysql

```


### 역정규화를 했을 때의 특징

---

* 역정규화를 하게 되면 시스템의 복잡도가 매우 높아진다.

    * *e.g.,*
    
        * `rdb` 값이 중복되는 행이 2개가 있어 수정하려면 각각의 행을 수정해야 하는 문제

        * `tag_name` 컬럼을 `topic_tag_relation`에 역정규화를 했음에도 여전히 tag 테이블은 그대로 존재하여 중복이 발생

* 시스템의 복잡도가 높아지면, 프로그램에 에러가 발생하기 훨씬 쉽다.

* 그럼에도 역정규화를 하는 것은 **성능** 때문!




<br/>
<br/>

### Ref.

* [생활코딩 : 관계형 데이터 모델링](https://opentutorials.org/module/4134)

* Wikipedia

* [TCP SCHOOL : MySQL](http://tcpschool.com/mysql/intro)

* [Lucidchart : Entity-Relationship Diagram Symbols and Notation](https://www.lucidchart.com/pages/ER-diagram-symbols-and-meaning)