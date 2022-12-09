package com.sparta.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // SQL 관련해서 동작의 변화가 일어난 것에 대해 반영시켜달라!
@SpringBootApplication
public class HomeworkApplication {
    public static void main(String[] args) {
        SpringApplication.run(HomeworkApplication.class, args);
    }
}
//IDBMS // 3계층(컨트롤러, 서비스- DTO, 레퍼지토리- JPA(어떤 역할을 하느냐, 무엇이냐)) 클라 서버 디비 구성 알고,
// 모델 만들 때 entity가 어떤 것이랑 대응하느냐?
// SQL -> create table 해서~ Memo테이블 생성.
// bigInt 타입으로 아이디만들것이고, barChar(스트링과 비슷)로 컨텐츠 만들고. <-이놈들이 entity의 멤버변수와 대응한다.