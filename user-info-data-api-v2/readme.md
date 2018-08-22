# Swagger Details
- http://localhost:7070/v2/api-docs
- http://localhost:7070/swagger-ui.html#/

# Actuator URL
 - http://localhost:7070/actuator or http://localhost:7070/application
 - Hal Browser - http://localhost:7070/browser/index.html#/
 
# Hystrix
 - hystrix stream url - http://localhost:7070/actuator/hystrix.stream 
 - hystrix dashboard url - http://localhost:7070/hystrix
 - use 'http://localhost:7070/actuator/hystrix.stream ' url on hystrix dashboard and give the service name and hit enter
 
# Database Details:
Go to H2 Console- 
http://localhost:7070/h2-console

create table user (id integer not null, birth_date date, name varchar(255), primary key (id))
INSERT INTO USER VALUES(100, sysdate(), 'Jack');
INSERT INTO USER VALUES(101, sysdate(), 'Bill');
INSERT INTO USER VALUES(102, sysdate(), 'Alex');
SELECT * FROM USER;

create table post (id integer not null, comment varchar(255), user_id integer, primary key (id))

INSERT INTO USER VALUES(100, sysdate(), 'Jack');
INSERT INTO USER VALUES(101, sysdate(), 'Bill');
INSERT INTO USER VALUES(102, sysdate(), 'Alex');
INSERT INTO POST VALUES(200, 'my first comment', 100);
INSERT INTO POST VALUES(201, 'my first comment', 101);
INSERT INTO POST VALUES(202, 'my second comment', 101);
SELECT * FROM POST;
SELECT * FROM USER;