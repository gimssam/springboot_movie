# Thymeleaf Templates
> Thymeleaf는 웹 및 독립형 환경 모두를 위한 최신 Server-Side Java 템플릿 엔진.<br>
Thymeleaf의 주요 목표는 개발 워크플로우에 우아하고 자연스러운 템플릿을 제공하는 것.<br>

Spring Boot에 포함된 기본 Template 엔진 중 하나로 프로젝트 생성 시 바로 추가하여 사용할 수 있음.

## 프로젝트 생성
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

### Dependency 설정
- Developer Tools
  - Spring Boot DevTools
  - Lombok
- Web
  - Spring Web
- Template Engines
  - Thymeleaf

## Thymeleaf 활용
### Namespace
Thymeleaf namespace를 \<html> 태그에 명시해야 html 페이지 내에서 thymeleaf 코드가 동작한다.
```html
<html lang=”ko” xmlns:th=”http://www.thymeleaf.org”>
```

## 기본 문법
html 태그 안에 th 문법을 추가
``<태그 th:[속성]=”데이터 및 표현식”>``

### 표현식 기호
- ${식별자} : 일반적인 글자 데이터 출력(SpringEL)
- @{link} : a 태그의 href 속성이나 form 태그의 action 속성에서 uri 처리
- *{field} : server에서 dto 객체 형태로 전송할 때, dto 객체의 필드를 출력
  - 먼저 상위 요소에서 th:object로 객체를 지정
- 자바스크립트에서의 출력(html의 content에 직접 출력할 때도 사용)
  - ``[[${식별자}]]``

### 주요 속성
#### 링크 처리
- th:href - 링크 연결 속성. a 태그에서 사용
- th:action - form 태그의 action 속성 대신 사용

intro.html
```html
<hr />
<a th:href="@{t_output}">타임리프 출력</a>
```
HomeController.java
```java
    @GetMapping("t_output")
    public ModelAndView thymeleafOutput(){
        log.info("thymeleafOutput()");
        mv.setViewName("t_output");
        return mv;
    }
```

Thymeleaf를 사용하는 경우 `setViewName`문에 작성하는 파일명은 <b>확장자</b>를 제외하고 작성한다.

#### 데이터 출력
- innerText로 출력
  - th:text : html 시작태그 내에 사용
  - [[${식별자}]] : 시작태그와 종료태그 사이에서 사용
- innerHTML로 출력
  - th:utext - html 시작태그 내에 사용
  - [(${식별자})] : 시작태그와 종료태그 사이에서 사용

> java code에서 template으로 데이터를 전송할 때 Model 객체를 활용

``model.addAttribute("식별자", value);``

t_output.html
```html
<h2>th:text와 th:utext의 차이</h2>
<div th:text="${testStr}"></div>
<div>[[${testStr}]]</div>
<div th:utext="${testStr}"></div>
<div>[(${testStr})]</div>
<hr>
```

HomeController.java
```java
    @GetMapping("t_output")
    public String thymeleafOutput(Model model){
        log.info("thymeleafOutput()");

        //일반 데이터
        model.addAttribute("testStr", "<h3>h3로 만든 문자열</h3>");

        return "t_output";
    }
```

#### 객체 처리
여러 데이터를 하나로 묶는 Map이나 Dto와 같은 객체의 처리
- Map 데이터
    - java 코드에서 Map을 사용하여 데이터를 전송
    - ``map.put("data1", data);``
    - ``model.addAttribute("식별자", map);``
    - ``<p th:text="${식별자.data1}"></p>``

t_output.html
```html
<h2>Map 데이터 출력</h2>
<p th:text="${mapdata.data1}"></p>
<p th:text="${mapdata.data2}"></p>
<p th:text="${mapdata.data3}"></p>
```

HomeController.java
```java
    @GetMapping("t_output")
    public String thymeleafOutput(Model model){
        ...
        //map 데이터 (묶음 데이터)
        Map<String, String> rmap = new HashMap<>();
        rmap.put("data1", "홍길동");
        rmap.put("data2", "20");
        rmap.put("data3", "인천시 미추홀구");

        model.addAttribute("mapdata", rmap);
        ...
    }
```

- DTO 객체
  - Map와 같은 형식으로 처리
  - th:object 활용
    - 하위 요소에서 *{field}로 각 데이터를 가져올 수 있음

t_output.html
```html
<h2>Dto 데이터 출력 1</h2>
<p th:text="${dtoData.getName()}"></p>
<p th:text="${dtoData.age}"></p>
<p th:text="${dtoData.address}"></p>
<hr>
<h2>Dto 데이터 출력 2</h2>
<th:block th:object="${dtoData}">
    <p th:text="*{name}"></p>
    <p th:text="*{age}"></p>
    <p th:text="*{address}"></p>
</th:block>
```

HomeController.java
```java
    @GetMapping("t_output")
    public String thymeleafOutput(Model model){
        ...
        //dto(또는 vo) 데이터
        DataDto dData = new DataDto();
        dData.setName("아무개");
        dData.setAge(30);
        dData.setAddress("인천시 동구");

        model.addAttribute("dtoData", dData);
        ...
    }
```

Dto/DataDto.java
```java
@Setter
@Getter
public class DataDto {
    private String name;
    private int age;
    private String address;
}
```

#### 스크립트 영역에서 처리
- th:inline - 스크립트 언어 지정
  - ``<script th:inline=”javascript”>``
  - 스크립트 영역 내에서 Thymeleaf 문법을 활용할 수 있도록 함.
 
t_output.html
```html
</body>
<script th:inline="javascript">
    let msg = [[${message}]];
    if(msg != null){
        alert(msg);
    }
</script>
</html>
```

HomeController.java
```java
    @GetMapping("t_output")
    public String thymeleafOutput(Model model){
        ...
        //javascript 출력용 데이터
        model.addAttribute("message", "서버로부터의 메시지");
        ...
    }
```

### Control

다음 페이지 이동을 위한 태그 및 코드, 페이지 추가
t_output.html
```html
...
<hr>
<a th:href="@{t_control}">타임리프 제어문</a>
</body>
...
```

t_control.html
```html
<body>
<h1>Thymeleaf Control</h1>
</body>
```

HomeController.java
```java
    @GetMapping("t_control")
    public String thymeleafControl(Model model){
        log.info("thymeleafControl()");

        return "t_control";
    }
```

#### 제어용 태그
th:block
- 제어문(분기, 반복) 및 객체 설정에 사용
```html
<th:block th:object="${dtoData}">
```

#### 제어 속성
1. 조건 제어 속성
- th:if - if문에 해당하는 속성(else는 없음)
- th:unless - if문의 반대에 해당하는 속성. 조건식을 th:if와 똑같이 작성하면 else문의 역할을 수행
- th:switch, th:case - switch, case에 해당하는 속성

t_control.html
```html
...
<h2>th:if</h2>
<th:block th:if="${data != null}">
    <p>메시지 : [[${data}]]</p>
</th:block>
<th:block th:unless="${data != null}">
    <p>메시지가 없습니다.</p>
</th:block>
<hr>
<h2>th:switch</h2>
<th:block th:switch="${age/10}">
    <p th:case="2">20대입니다.</p>
    <p th:case="3">30대입니다.</p>
    <p th:case="4">40대입니다.</p>
    <p th:case="5">50대입니다.</p>
</th:block>
...
```

HomeController.java
```java
    @GetMapping("t_control")
    public String thymeleafControl(Model model){
        ...
        model.addAttribute("data", "이 문자열이 보입니다.");
        model.addAttribute("age", 25);
        ...
    }
```

2. 반복 제어 속성
- th:each – for(foreach)문에 해당하는 속성
  - 반복 상태값(status) 활용 : ``th:each="item,status:${list}"``

status
|name|description|
|---|---|
|index|목록의 순번. 0부터 시작.|
|count|반복 횟수. 1부터 시작.|
|size|목록의 크기.|
|current|반복 중인 항목. th:if 속성과 결합하여 활용.|
|first|반복 중인 항목이 첫 번째인가의 여부. true/false|
|last|반복 중인 항목이 마지막인가의 여부. true/false|
|odd|반복 중인 항목이 홀수 번째인가의 여부. true/false|
|even|반복 중인 항목이 짝수 번째인가의 여부. true/false|

- 전송된 list를 출력하는 경우 th:if와 중첩하여 빈(empty) 목록인지 확인하는 형태로 활용

t_control.html
```html
...
<hr>
<h2>th:each</h2>
<table border="1">
    <thead>
    <tr>
        <th width="100">이름</th>
        <th width="50">나이</th>
        <th width="150">주소</th>
    </tr>
    </thead>
    <tbody>
    <th:block th:if="${#lists.isEmpty(dList)}">
        <tr>
            <th colspan="3">출력할 데이터가 없습니다.</th>
        </tr>
    </th:block>
    <th:block th:unless="${#lists.isEmpty(dList)}">
        <th:block th:each="item:${dList}">
            <tr>
                <td th:text="${item.name}"></td>
                <td th:text="${item.age}"></td>
                <td th:text="${item.address}"></td>
            </tr>
        </th:block>
    </th:block>
    </tbody>
</table>
...
```

HomeController.java
```java
    @GetMapping("t_control")
    public String thymeleafControl(Model model){
        ...
        List<DataDto> d_list = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            DataDto data = new DataDto();
            data.setName("이름" + i);
            data.setAge(25 + i);
            data.setAddress("주소" + i);
            d_list.add(data);
        }
        model.addAttribute("dList", d_list);
        ...
    }
```

- ``#lists.isEmpty(list)`` : list가 비어있는지의 여부 확인.

### Thymeleaf Expression Object - Basic, Utility Object
참고 : [Thymeleaf Expression](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#expression-basic-objects)

## Template 확장
### th:fragment
- header나 nav, footer 같은 페이지 공통 부분의 처리를 위한 속성
- 하나의 html 파일에 여러 분할 영역을 작성
- 공통 부분이 필요한 페이지에서 활용

다음과 같은 형식으로 작성
```html
th:fragment="fragment_name"
```

th:fragment 속성을 갖는 태그는 th:block, div, p 등 다양하게 활용 가능하다.

fragments.html
```html
<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<th:block th:fragment="header1">
    <h2>Header1입니다.</h2>
</th:block>
</body>
</html>
```

### Fragment 활용 속성
- th:insert - 태그 안으로 fragment 요소를 삽입

```html
<header th:insert=..."></header>
```

![image](https://github.com/tiblo/spring_jpa/assets/34559256/674945de-e664-40fd-bf09-73ef79f8f341)


- th:replace – 태그를 fragment 요소로 교체

```html
<header th:replace=..."></header>
```

![image](https://github.com/tiblo/spring_jpa/assets/34559256/b96632f2-941b-47a4-b824-c1750ac79e64)

#### Fragment 표현식
특정 페이지에서 fragment를 불러오는 표현식은 다음과 같다.
```html
<tag th:insert"~{fragment_file::fragment_name}"></tag>
```

index.html
```html
<body>
...
<header th:insert="~{fragments::header1}"></header>
...
</body>
```

### Fragment로 데이터 전송
th:fragment 속성의 fragment_name 뒤에 데이터를 받을 식별자를 작성한다.

th:text나 th:utext 등으로 데이터를 출력한다.

fragment_file.html
```html
<th:block th:fragment="fragment_name(식별자)">
    <p th:text="${식별자}"></p>    
</th:block>
```

fragment를 활용하는 페이지에서는 다음과 같이 데이터를 주입한다.
some.html
```html
<header th:insert="~{fragment_file::fragment_name(data)}"></header>
```

fragments.html
```html
<body>
...
<th:block th:fragment="header2(data)">
    <h2 th:text="${data}"></h2>
</th:block>
</body>
```

index.html
```html
<body>
...
<header th:replace="~{fragments::header2('2번째 헤더')}"></header>
...
</body>
```
