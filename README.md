#나만의 일정 관리 앱 서버 만들기



|기능|Method|URL|REQUEST|RESPONSE|상태코드|
|------|---|---|---|---|---|
|일정등록|POST|API/TODO|요청BODY|등록정보|200:정상등록|
|일정조회|GET|/api/TODO/{TODOId}|요청PARAM|단건응답정보|200:정상조회|
|일정수정|PUT|/api/TODO/{TODOId}|요청BODY|수정정보|200:정상수정|
|일정삭제|DELETE|/api/TODO/{TODOId}|요청PARAM|-|200:정상삭제|


![list](https://github.com/user-attachments/assets/d3622ea7-77ff-48ae-83c9-5ea8189e0d23)
