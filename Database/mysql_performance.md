# MySQL에서 쿼리문 성능 측정하기

## Performance Schema 사용하기

MySQL에서 쿼리문 성능 측정과 관련하여 검색하면 `SHOW PROFILE` statement가 자주 등장.

그러나 SHOW PROFILE Statement는 deprecated된 상태이다. 사용이 불가능한 것은 아니지만 향후 삭제될 예정이니 다른 방법을 사용하라고 [MySQL Documentation](https://dev.mysql.com/doc/refman/5.6/en/show-profile.html)에서도 안내하고 있다.

> Note
>
> These statements are deprecated as of MySQL 5.6.7; expect them to be removed in a future MySQL release. Use the Performance Schema instead; see Section 22.18.1, “Query Profiling Using Performance Schema”.

