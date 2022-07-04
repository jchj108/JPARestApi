# MileageRestAPI


마일리지 서비스 구현

## ERD

![image](https://user-images.githubusercontent.com/75921378/177166391-87b7df06-bd35-421f-80a4-ca626efad72b.png)

## Prerequisite 

- (java 8, Gradle)

## Runbook
- git clone 후 프로젝트를 임포트해주세요.
- 의존성에 구애받지 않도록 H2 데이터 베이스를 인 메모리 모드로 사용하도록 설정했습니다. Mysql 문법을 사용하도록 Dialect 설정이 되어 있습니다.
- `localhost:8080/h2-console` 에서 데이터 확인이 가능합니다. jdbc url은 `jdbc:h2:mem:testdb;` 입니다.
- `localhost:8080/swagger-ui/index.html` 에서 Api 명세 확인 및 테스트가 가능합니다.

## Description

![image](https://user-images.githubusercontent.com/75921378/177163421-553d3463-fb44-4559-b9bd-7ad74ba81da1.png)

- User와 Place가 존재하지 않는다면 Review를 등록할 수 없습니다.
- ddl-auto 옵션을 통해 어플리케이션 실행 시 스키마가 자동으로 생성됩니다.
- 테이블 스키마는 resources.sql 에서 확인이 가능합니다.
