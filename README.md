# user-service

## Dependencies
- Java 17
- Spring Boot 3.5.5
    - Spring Boot Starter Data JPA
    - Hibernate 6.6.26
- MySQL 8.0.14
- QueryDSL 5.1.0
- Spring Cloud
    - Eureka Client 4.3.0
- Lombok 1.18.38

## API Specification

https://docs.google.com/spreadsheets/d/1erfJV-eh3TtUMcaTJLLNou-vhwnxBvB4MV5cJ72-hw8/edit?gid=416827453#gid=416827453

## API Descriptions

#### 소셜 로그인
- 플로우
  1. 소셜 서비스 계정 이메일로 유저 조회
     - 유저 없을 경우 소셜 회원 가입을 위한 응답 반환
  2. 해당 유저에 연동된 소셜 계정이 없을 경우 새로운 연동 데이터 생성
- 시퀀스 다이어그램
    ![소셜_로그인](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdna%2Fb1BNeE%2FbtsQZlTHrre%2FAAAAAAAAAAAAAAAAAAAAAD6V6L8_7j0YkUDwuoWwJa2rU8Fk_fzbxFQUAN6EzLAu%2Fimg.png%3Fcredential%3DyqXZFxpELC7KVnFOS48ylbz2pIh7yKj8%26expires%3D1761922799%26allow_ip%3D%26allow_referer%3D%26signature%3DNXiPSQnJbqvCSOFTdMNSWoCAGb8%253D)