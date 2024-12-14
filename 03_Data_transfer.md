# Back-end에서 데이터 수신
## @RequestParam
Query string과 method 매개변수를 매칭시키기 위한 어노테이션

```java
    public String method(@RequestParam(“name”) type variableName) {
        ...
```

Html에서의 전송 문장이 다음과 같다면
```html
<a th:href=“@{uri(n1=value)}>전송</a>
```

Controller는 다음과 같이 처리한다.
```java
    public String methodName(@RequestParam(“n1”) String n){
        ...
```

### 예제
t_control.html에 전송 예제용 페이지로 이동하는 링크를 작성
```html
...
<a th:href="@{sendData}">데이터전송 ></a>
</body>
```

HomeController.java에 해당 페이지로 이동하는 매핑 메소드를 작성
```java
    @GetMapping("sendData")
    public String sendData(){
        return "sendData";
    }
```

sendData.html
```html
<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Send Form</title>
</head>
<body>
<h1>Client -> Server</h1>
<h2>a 태그 활용</h2>
<p>전송 한 데이터 : 10, 20</p>
<a th:href="@{a_send(num1=10,num2=20)}">전송</a>
</body>
</html>
```

HomeController.java
```java
    @GetMapping("a_send")
    public ModelAndView aTagDataSend(@RequestParam("num1") String num1,
                                     @RequestParam("num2") int num2){
        System.out.println("num1 : " + num1 + ", num2 : " + num2);
        ModelAndView mv = new ModelAndView();
        int n1 = Integer.parseInt(num1);
        mv.addObject("result", n1 + num2);
        mv.setViewName("s_result");
        return mv;
    }
```

## Dto(전송 데이터 저장용) 객체 활용
Query string 또는 input 태그의 name 속성의 값과 동일한 멤버 변수명을 사용할 경우 같은 query string 또는 form으로 전송되는 모든 데이터를 한번에 처리할 수 있음

이 때는 @RequestParam을 작성하지 않는다.
```java
    public String methodName(DtoClass dto) {
        ...
```

### 예제
sendData.html
```html
...
<hr>
<h2>form 데이터 전송</h2>
<form th:action="@{noneDtoSend}">
    <input type="text" name="name"><br>
    <input type="number" name="age"><br>
    <input type="text" name="address"><br>
    <input type="submit" value="전송">
</form>
<hr>
<h2>form 데이터 전송 - Dto 사용</h2>
<form method="post" th:action="@{dtoSend}">
    <input type="text" name="name"><br>
    <input type="number" name="age"><br>
    <input type="text" name="address"><br>
    <input type="submit" value="전송">
</form>
</body>
```

HomeController.java
```java
    @GetMapping("noneDtoSend")
    public String noneDtoSend(@RequestParam("name") String name,
                              @RequestParam("age") int age,
                              @RequestParam("address") String address,
                              Model model){
        System.out.println("name : " + name);
        System.out.println("age : " + age);
        System.out.println("address : " + address);

        model.addAttribute("result", "none dto send OK");
        return "s_result";
    }

    @PostMapping("dtoSend")
    public String dtoSend(DataDto data, Model model){
        System.out.println("name : " + data.getName());
        System.out.println("age : " + data.getAge());
        System.out.println("address : " + data.getAddress());

        model.addAttribute("result", "dto send OK");
        return "s_result";
    }
```

s_result.html
```html
<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Send Data Result</title>
</head>
<body>
<h1>Send Data Result 페이지</h1>
<th:block th:if="${result != null}">
    <p th:text="'결과 : ' + ${result}"></p>
</th:block>
<hr>
<a th:href="@{sendData}">< 뒤로</a>
</body>
</html>

```
