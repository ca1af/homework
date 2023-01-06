# Java Spring Web Server: Memo Application
Java Spring framework를 이용하여 게시글 CRUD, 댓글 작성, 게시글과 댓글 좋아요 등의 기능을 가진 back-end Web Server를 구축하였습니다.

## 개발기간
2022/12/30 09:00 - 2023/1/6 12:00

## 멤버 소개
김미란 https://github.com/h7359841

박경원 https://github.com/ruddnjs5816

김동균 https://github.com/ca1af

김주성 https://github.com/karlema

곽두영 https://github.com/youngfromnowhere

## Dependencies
1. Spring Web
2. Spring data JPA
3. Thymeleaf
4. Lombok
5. H2 database

## 주요기능
#### REST API
URL주소와 GET, POST, PUT, DELETE등의 메서드로 요청을 받아서, client에게 JSON형식의 데이터를 반환합니다.
#### CRUD
메모(게시글), 댓글 엔티티에 대해 게시, 조회, 수정, 삭제 기능을 구현하였습니다.
#### 좋아요 기능
게시글, 댓글에 대해 한 User당 하나의 좋아요를 표시할 수 있습니다.
좋아요를 취소할 수 있습니다.
#### Sign in / Log in
JWT 방식으로 회원가입, 로그인 기능을 구현하였습니다.
#### Member role
일반 User와 Admin 계정을 구분하여 권한을 부여하였습니다.

## API 명세서

POST맨을 이용했습니다. 아래 링크에서 확인 할 수 있습니다.

https://documenter.getpostman.com/view/24788145/2s8YzXtedY#d106912e-1f82-49ba-aacf-9e8d8c86cea0
https://documenter.getpostman.com/view/24788145/2s8YzXtedY#3bcb9d75-daa2-423c-b39e-fa405ae2e041

## 팀 노션 페이지

https://www.notion.so/221230-230106-Spring-b0b44c834a674ec08024e45f5394748b

