# springboot_movie

spring_edu
수업용 폴더

Corretto https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html

개발툴 https://www.jetbrains.com/ko-kr/idea/download/?section=windows

Utility Objects
https://www.thymeleaf.org/doc/tutorials/3.1/usingthymeleaf.html#appendix-b-expression-utility-objects

Thymeleaf prefix, suffix 설정(필요 시)
spring.thymeleaf.prefix=file:src/main/resources/templates/
spring.thymeleaf.suffix=.html
IntelliJ 교육용 라이선스 관련 사항
라이선스를 신청하려면 일단 Jetbrains에 회원 가입을 먼저 하셔야 합니다.

라이선스 신청 페이지 : https://www.jetbrains.com/shop/eform/training

해당 기관에 소속되어 있음을 증명해야 합니다. 기관의 강사 소개 페이지에 있다면 가능합니다. 위 페이지에 신청을 하면 약 1주일 내에 등록한 email로 답변 및 쿠폰 목록이 옵니다.

쿠폰 등록 페이지 : https://www.jetbrains.com/store/redeem/

학생 개인별로 위 페이지에 받을 쿠폰 번호와 개인 email을 등록해야 합니다. 등록한 email로 activation code가 전송됩니다. IntelliJ IDEA에 위 코드를 등록하면 신청한 기간 동안 모든 기능을 제한없이 사용 가능합니다.

제공된 쿠폰을 80% 이상 사용하게 되면 주어진 기간이 지난 후 Jetbrains 사에서 다음 기간에 사용할 수 있는 쿠폰을 자동으로 보내줍니다.
또 재신청을 하실 수도 있습니다.
프로젝트 기반의 Java Web 애플리케이션 개발
개요
Spring Boot와 Thymeleaf, MyBatis를 활용한 Web 애플리케이션 개발

기본적인 웹 운영 형태
image

사용자는 웹 브라우저를 통해 서버에 요청(Request)을 전송하면 서버는 요청한 내용(자원, 데이터 등)을 처리하여 응답(Response)을 전송하는 구조.

웹 애플리케이션의 구성
웹 애플리케이션 = 프론트엔드 + 백엔드

프론트엔드(Front-end)
웹 사용자가 브라우저 프로그램(MS 엣지, 구글 크롬, 사파리 등)을 통해 보는 화면을 개발하는 분야.

HTML
HTML은 하이퍼텍스트와 마크업 언어로 구성. 하이퍼텍스트는 페이지들 사이의 링크, 마크업 언어는 웹페이지의 구조를 정의. 웹 페이지의 뼈대.
CSS
CSS는 종속된 스타일시트(Cascading Style Sheets). 웹페이지에 다양한 스타일을 적용할 수 있게 해줌으로써 애플리케이션 페이지를 표시하는 프로세스를 단순하게 만들어주는 디자인 언어. 웹 페이지의 모습.
Javascript
사용자들과 상호작용하는 기능을 제공. (최근 JS는 백엔드 개발에도 활용되고 있음)
백엔드(Back-end)
서버 측 개발 분야로, 사용자가 요청한 기능(서비스 로직)을 내부적으로 수행하며, 데이터를 저장하고 관리하는 프로그램을 개발하는 분야.

Java
가장 인기 있는 프로그래밍 언어 중 하나이자 객체지향 프로그래밍 언어(본 과정 주요 언어)
Javascript
백엔드와 프론트엔드 모두에서 사용.(Node.js)
Python
다양한 개발에 활용되며, 특히 딥러닝, 데이터 사이언스, 인공지능 분야에서 많이 사용.
Spring Framework
로드 존슨(Rod Johnson)이 2002년에 출판한 저서 Expert One-on-One J2EE Design and Development에서 선보인 예제 소스 코드에서 시작하여 현재까지 발전된 자바 기반의 웹 프레임워크.

한국 전자정부 표준 프레임워크의 기반 기술

한국 지능정보사회 진흥원에서 2023년에 발표한 표준프레임워크 신규버전(4.2버전)에 따르면 스프링 부트 2.7.12 지원 및 Spring WebFlu, Spring Cloud Stream 활용에 대한 내용을 담고 있다.
Spring Framework 주요 특징
POJO(Plain Old Java Object) 방식

‘오래된 방식의 간단한 자바 오브젝트’라는 뜻. Java EE(EJB) 등의 중량 프레임워크들을 사용하게 되면서 해당 프레임워크에 종속된 "무거운" 객체를 만들게 된 것에 반발해서 사용되게 된 용어. 특정 자바 모델이나 기능, 프레임워크 등을 따르지 않은 자바 오브젝트를 지칭.
AOP(Aspect Oriented Programming)

‘관점 지향 프로그래밍’. 로깅, 트랜잭션, 보안 등 여러 부분에서 공통적으로 사용되는 코드(기능)를 분리하여 관리하는 프로그래밍 방식.
DI(Dependency Injection)

‘의존성 주입’. 객체 간의 의존 관계를 소스 코드 내부에서 처리하지 않고, 외부 설정으로 정의되는 방식. 소소의 재사용성과 객체 간의 결합도를 낮출 수 있음. 스프링 프레임워크가 DI를 처리.
IoC(Inversion of Control)

‘제어의 역전’. 개발자가 작성한 코드가 외부 라이브러리를 사용하는 방식(전통적인 방식)이 아니라 외부 라이브러리(프레임워크)가 개발자의 코드를 필요에 따라 사용하는 방식.
Lifecycle 관리

자바 객체의 생성, 소멸을 프레임워크가 관리.
Spring Boot
Spring Framework를 사용하여 더 빠르고 쉽게 웹 애플리케이션과 마이크로서비스를 개발하도록 돕는 도구.

핵심 기능
사전 설정된 종속성 항목으로 애플리케이션을 초기화. 내장형 자동 구성 기능과 설정에 따라 Spring Framework와 3rd-party 패키지를 자동으로 구성.

개발자의 초기 선택에 따라 설치할 패키지(라이브러리)와 사용할 기본값을 지정. 개발자는 단순한 웹 폼인 Spring Boot Initializer로 프로젝트를 시작.

Tomcat과 같은 웹 서버를 앱에 포함하여 외부 웹 서버에 의존하지 않고 자체적으로 실행되는 독립형 애플리케이션을 개발할 수 있음.

개발환경 설정
개발 도구
JDK 17 이상
Amazon Corretto 17
통합 개발 환경(IDE)
JetBrain IntelliJ IDEA Ultimate 버전(30일 무료 평가판)
Database(DBMS)
MySQL 8.x.x
