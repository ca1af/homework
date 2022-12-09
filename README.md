# homework

## API 명세서

| Method | URL             | Request                                                                 | Response                                                                                                                                                    |
|--------|-----------------|-------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------|
| get    | /api/memos/     | -                                                                       | [{"createdAt":"2022-12-09T11:14:00.978086","modifiedAt":"2022-12-09T11:14:00.978086","id":1,"password":"1","title":"제목","username":"이름","contents":"내용"}]   |
| post   | /api/memos/     | {"username":"이름", "title":"제목", "password":"비밀번호", "contents":"내용"}     | {"createdAt":"2022-12-09T12:10:19.556811","modifiedAt":"2022-12-09T12:10:19.556811","id":1,"password":"1","title":"제목","username":"이름","contents":"내용"}     |
| get    | /api/memos/{id} | -                                                                       | {"createdAt":"2022-12-09T12:10:19.556811","modifiedAt":"2022-12-09T12:10:19.556811","id":1,"password":"1","title":"제목","username":"이름","contents":"내용"}     |
| put    | /apimemos/{id}  | {"username":"이름", "title":"제목", "password":"비밀번호", "contents":"바꾸는 내용"} | {"createdAt":"2022-12-09T12:10:19.556811","modifiedAt":"2022-12-09T12:14:18.534253","id":1,"password":"1","title":"제목","username":"이름","contents":"바꾸는 내용"} |
| delete | /api/memos/{id} | {"password":"비밀번호"}                                                     | {"message":"삭제가 완료되었습니다"                                                                                                                                    |

### 1. 수정, 삭제 API의 request를 어떤 방식으로 사용하셨나요? (param, query, body)
- Body를 request 하는 방식. param, query의 경우는 Dto의 변화에 따라 변경될 수 있어서.
### 2. 어떤 상황에 어떤 방식의 request를 써야하나요?
- API 명세와 같음.
### 3. RESTful한 API를 설계했나요? 어떤 부분이 그런가요? 어떤 부분이 그렇지 않나요?
- 레이어들의 역할을 분리하는 데에 신경썼지만, REST API가 정확히 어떤 방식으로 동작해야하는지 이해하지 못함.
### 4. 적절한 관심사 분리를 적용하였나요? (Controller, Repository, Service)
- 네!
### 5. API 명세서 작성 가이드라인을 검색하여 직접 작성한 API 명세서와 비교해보세요!

### 어려웠던 부분
- 레이어들을 한 방식으로 맞춰가는 것. (타입부분 특히)
- 객체로 리턴할 때에 신경써야 했던 부분들이 많아서 조금 까다로웠다.
- 특히 deleteMemo 부분이 어떻게 "메시지"를 리턴해야 할까를 고민했다. Dto? String타입? 실험해 본 결과 String은 불가하고, Dto로 리턴하는 방식이 맞는 듯.(Dto는 컨트롤러와 서비스단에서 공용으로 쓰는 데이터를 가지고 있으니)