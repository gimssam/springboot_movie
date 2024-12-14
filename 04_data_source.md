# DB 설정
## H2 Database
자바로 구현된 오픈소스 데이터베이스

별도의 설치 없이 바로 사용 가능(개발 테스트용으로 많이 사용)

## H2 Database 활용을 위한 설정
초기 Dependency 추가
- H2 Database

## Intellij Database 설정
Intellij IDEA는 Data Source로 DB를 활용할 수 있는 도구를 제공한다.

![image](https://github.com/user-attachments/assets/8fa9c066-8165-4f6e-858b-bfcebc0c1a63)

우측에 ``DB`` 아이콘을 클릭하면 다음과 같은 화면이 보이게 된다.

![image](https://github.com/user-attachments/assets/db61f55c-44fe-4012-8ab2-7cdaab3eb314)

``+`` 클릭 후 ``Data Source`` -> ``H2`` 선택

Data Source 설정
- Name : moviedb
- Path : ~/db/moviedb.mv.db
- User : sa
- Password : 12341234
- URL : jdbc:h2:~/db/moviedb
- URL을 변경하면 Name과 Path가 함께 변경됨
- Test Connection을 수행하면 ``C:/users/사용자명/``에 H2용 폴더와 DB 파일이 생성됨
- URL은 application.properties에서 사용

![image](https://github.com/user-attachments/assets/9c15f832-66c0-4357-b7de-7a6557c2e492)

``Test Connection`` 실행 후 ``OK``를 클릭하면 설정이 완료된다.

DB 연동을 위한 ``Driver``가 필요하며, 없을 경우 위의 설정 창에서 다운로드하여 곧바로 사용할 수 있다.
