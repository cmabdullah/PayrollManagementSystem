http://cmabdullah.me/PayrollManagementSystem/WebContent/resources/customize/customize%20design.html


https://docs.spring.io/spring-security/site/docs/3.0.x/reference/taglibs.html

https://docs.spring.io/spring-security/site/docs/3.0.x/reference/el-access.html

https://tiles.apache.org/framework/tiles-core/dtddoc/index.html

https://tiles.apache.org/framework/tutorial/basic/pages.


SELECT * FROM PayrollManagementSystem.attendence where logintime like  '2018-10-25%';

SELECT * FROM PayrollManagementSystem.attendence where logintime like  '2018-10-25%'  AND userinfo_id=2020;

SELECT * FROM PayrollManagementSystem.attendence where logintime like  '2018-10-25%'  AND userinfo_id=2020 AND logouttime IS NULL;

SELECT * FROM PayrollManagementSystem.attendence where logintime like  '2018-10-25%'  AND userinfo_id=2020 AND logouttime IS NULL;


SELECT * FROM PayrollManagementSystem.attendence where logintime <  "2018-10-26%" AND userinfo_id=2020 AND logouttime IS NULL;



insert into PayrollManagementSystem.leave (reasone,userinfo_id) values ('ff',2026)