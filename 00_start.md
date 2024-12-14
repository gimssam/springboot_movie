# Spring Boot 프로젝트

## 방식 1
https://start.spring.io/ 사이트를 통해 프로젝트를 생성하고, 다운로드하여 개발환경에서 불러오는 방법
- 모든 프로젝트는 이 사이트를 통해 생성되어 IDE로 다운로드된다.
- 즉, 인터넷이 연결되어 있지 않는 오프라인 환경에서는 프로젝트 생성도 불가

![image](https://github.com/tiblo/spring_edu/assets/34559256/306882a4-fb45-401b-b4fc-f1e67a6ee37b)

## 방식 2
IDEA에서 프로젝트 생성

![image](https://github.com/tiblo/spring_edu/assets/34559256/e3fb90cc-7690-40aa-b82d-f9a877e576a3)

### 작성 항목
- Name : ``start01``
- Location : ``작업 폴더``
- Language : Java
- Type : Gradle - Groovy
- Group : com.``icia``
- Artifact : start01
- Package name : com.icia.start01
- JDK : 17
- Java : 17
- Packaging : Jar

`` ``의 내용만 작성 및 지정 후 Next.

![image](https://github.com/tiblo/spring_edu/assets/34559256/2a7b2a84-32d1-40b8-b5b9-52d400f353ac)

### Dependency(초기 라이브러리 설정)
- Developer Tools
  - Spring Boot DevTools
  - Lombok
- Web
  - Spring Web
- Template Engines
  - Thymeleaf

선택 후 Create.

### 생성 완료 후 프로젝트 폴더 구조

![image](https://github.com/tiblo/spring_edu/assets/34559256/5982f410-fd66-43d3-ab87-39977dcc7056)


## 초기 화면 처리 및 실행
> 작업 폴더 – src/main
> java 폴더 : java 소스 코드 작성.
> resources 폴더 : 이미지, 스타일시트, 자바스크립트, HTML 페이지 작성.

작성 내용
- ‘start01’에 Package 생성
  - controller
- controller 패키지에 Java Class 생성
  - HomeController
- static 폴더에 HTML file을 생성
  - index.html
- Start01Application 실행

### Resources 폴더의 구성
#### static 폴더
배경이미지, 스타일시트, 자바스크립트 등 웹의 정적 자원(Static Resources)을 저장하는 폴더

static 폴더 밑에 각 자원을 위한 폴더를 생성하여 각각 저장

#### templates 폴더
HTML 템플릿을 저장하는 폴더

접속자의 화면에 보여질 HTML 문서를 작성

Thymeleaf 등 템플릿 엔진을 사용하는 프로젝트에서 HTML 페이지를 작성

#### application.properties
정적 자원의 위치 지정, DB 설정, 파일 업로드, 에러 페이지 등 웹 프로그램 실행 시 필요한 설정 내용을 작성.

### 작성 코드
HomeController.java
```java
@Controller
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
```

home.html
```html
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>HOME</title>
</head>
<body>
<h1>첫 페이지</h1>
<p>처음으로 보이는 페이지</p>
</body>
</html>
```

## 실행 환경 설정
좌측하단의 ``Services`` 아이콘을 클릭.
- ‘+’ (Add service) > Run Configuration Type > Spring Boot 선택.
- ‘▶’를 눌러 실행.

> Lombok requires enabled annotation processing 창이 뜨면 Enable annotation processing 클릭

‘Start01Application :8080/’에서 마지막 ‘8080/’ 부분을 클릭. 
- 브라우저로 실행

![image](https://github.com/tiblo/spring_edu/assets/34559256/e13c3bc2-b620-4963-8063-dd93d7d2f2c8)


## 페이지 이동

페이지의 이동은 반드시 controller를 거쳐야한다.

home.html에 다음 코드를 추가한다.

```html
<p>처음으로 보이는 페이지</p>
<hr />
<a href="intro">소개</a>
```

intro.html

```html
<h1>강의 소개</h1>
<h2>프로젝트 기반의 Java Web 애플리케이션 개발</h2>
<p>
  본 강의는 Spring Boot와 Thymeleaf, MyBatis를 활용한 Web 애플리케이션 개발 관련
  내용을 수업합니다.
</p>
<h2>강사 소개</h2>
<p>박종일(인천일보아카데미)</p>
<hr />
<a th:href="@{t_output}">thymelead 출력 ></a>
```

HomeController.java

```java
    @GetMapping("intro")
    public String intro(Model model){
        Date now = new Date();
        model.addAttribute("data", now);
        return "intro";
    }
```
