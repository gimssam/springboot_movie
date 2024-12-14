# Spring MVC
Spring MVC 동작 구조는 다음과 같다.

![image](https://github.com/tiblo/spring_edu/assets/34559256/b2ee479d-0cf7-4080-9d1a-ce4bc2517207)

사용자의 요청(Request)은 Web Server를 거쳐 Servlet Container의 Spring Module로 전달된다.

![image](https://github.com/tiblo/spring_edu/assets/34559256/6383d4bf-29df-467e-8c5c-4c77c5f377a4)

내부 동작 과정은 다음과 같다.
- DispatcherServlet은 HandlerMapping을 통해 해당 요청을 처리하는 Controller를 찾아 Request를 넘긴다.
- Controller는 요청 기능에 대한 서비스 로직을 수행하고 그 결과를 Model에 담아 DispatcherServlet에 반환하며, Model이 담길 View의 이름을 함께 반환한다.
- DispatcherServlet은 ViewResolver를 통해 해당 View를 찾아 Model을 담은 후 사용자에게 응답(Response)한다.

## URI Mapping
사용자의 요청은 URI로 구분하여 Controller로 전달되며, Mapping된 메소드에서 처리된다.

Spring Framework는 Annotation 방식으로 URI와 메소드를 mapping한다.
- @RequestMapping
- @GetMapping
- @PostMapping

더 있지만 여기서는 주로 사용하는 3가지만 살펴본다.

### @RequestMapping
Controller의 메소드를 Handler에 등록하기 위해 제공되는 annotation.

2가지 옵션으로 메소드의 mapping을 수행한다.
- value : mapping하는 uri를 작성. 필수 옵션
- method : http 전송 방식을 지정. 생략 시 GET 방식으로 설정.

```java
@RequestMapping(value = "someUri", method = RequestMethod.GET)
public String ...
```

method 옵션으로 전송 방식(GET/POST)에 따라 메소드를 구분할 수 있으며, Get 방식의 전송일 경우 생략할 수 있다.
이 때는 vaule 키워드도 생략하여 uri만 작성한다.
```java
@RequestMapping("someUri")
public String ...
```

@RequestMapping은 Controller Class에서도 활용가능하며, 이 때는 공통 uri를 지정할 수 있다.

예를 들어 customer site와 seller site로 controller를 구분하여 작성할 경우
```java
@Controller
@RequestMapping("customer")
public class CustomerController {
...
```

```java
@Controller
@RequestMapping("seller")
public class SellerController {
...
```

요청 uri는 ``customer/xxx``, ``seller/xxx``와 같으며, ``xxx``부분은 각 메소드에서 따로 mapping하여 처리한다.

### @GetMapping
Get 방식의 전송에 대응하는 메소드 mapping anntation.

value만 설정하면 된다.

```java
@Controller
public class SomeController {
    @GetMapping("uri")
    public String uriMappingMethod(...
```

메소드 앞에만 붙일 수 있으며, class에 @RequestMapping이 붙은 경우 하위 uri와 mapping된다.
```java
@Controller
@RequestMapping("customer")
public class CustomerController {
    @GetMapping("buy")
    public String uriMappingMethod(...
```

이 경우, 요청 uri는 ``customer/buy``가 된다.

### @PostMapping
Post 방식의 전송에 대응하는 메소드 mapping anntation.

@GetMapping과 마찬가지로 value만 설정하면 된다.

```java
@Controller
public class SomeController {
    @PostMapping("uri")
    public String uriMappingMethod(...
```

메소드 앞에만 붙일 수 있으며, class에 @RequestMapping이 붙은 경우 하위 uri와 mapping된다.
```java
@Controller
@RequestMapping("seller")
public class SellerController {
    @PostMapping("product")
    public String uriMappingMethod(...
```

이 경우, 요청 uri는 ``seller/product``가 된다.

## Model과 view
Spring Framework는 Back-end에서 Front-end(Thymeleaf Templates)로 데이터를 보낼 때 활용할 수 있는 두가지 객체를 제공한다.

### Model 객체
Model 객체는 DispatcherServlet에서 보내주기 때문에 따로 생성하지 않고 해당 메소드의 매개변수로 받는다.
```java
    @GetMapping("uri")
    public String someMethod(Model model){
        ...
        model.addAttribute("식별자", data);
        return "view_name";
    }
```

Model 객체는 return하지 않아도 DispatcherServlet에 전달된다.

따라서 메소드는 Model이 전달될 View의 이름만 return한다.

### ModelAndView 객체
ModelAndView는 Model과 View를 합친 형태의 객체이다.

DispatcherServlet에서 보내주는 객체가 아니기 때문에 매개변수로 받지 않으며, 메소드 내에서 객체를 생성하여 사용한다.

```java
    @GetMapping("uri")
    public ModelAndView someMethod(){
        ...
        ModelAndView mv = new ModelAndView();
        mv.addObject("식별자", data);
        mv.setViewName("view_name");

        return mv;
    }
```

Data를 담기위해 ``addObject()``를 사용하며, View 이름의 지정을 위해 ``setViewName()``을 사용한다.


