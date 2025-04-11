# Pr01Counter 프로젝트 설명

> Spring Boot 기반의 간단한 카운터 및 계산기 웹 애플리케이션입니다.  
> 사용자는 버튼을 클릭하여 숫자를 증가하거나 감소시킬 수 있으며, 숫자 두 개를 입력해 연산 결과를 확인할 수도 있습니다.  
> 프론트엔드는 Thymeleaf + Tailwind CSS로 구성되어 있습니다.

---

### 🔧 주요 구성 요소 설명

 📄 `Counter.java`
- `@Component`가 붙은 Bean 클래스이며, 애플리케이션 내에서 하나의 공유 객체로 주입됨
- `count` 필드: 현재 카운터 값을 저장
- `@Data` (Lombok): getter/setter 자동 생성

 📄 `HtmlController.java`
- `@Controller` 애노테이션을 사용하여 HTTP 요청 처리
- 루트 경로(`/`)에서 현재 카운트값 출력
- `/plus`: 카운트 +1
- `/minus`: 카운트 -1
- `Model` 객체를 통해 View에 데이터 전달

 📄 `CalcController.java`
- `/calc` 경로에서 숫자 두 개를 받아 사칙연산 처리 후 View로 결과 전달
- `@RequestParam`을 통해 폼 데이터 수신
- 결과는 정수형으로 반환

 📄 `Pr01CounterApplication.java`
- `@SpringBootApplication` 애노테이션이 붙은 진입점 클래스
- `main()` 메서드에서 SpringApplication 실행

---

### ✨ index.html 구성 요약

- Thymeleaf 템플릿 엔진을 사용해 서버 데이터를 바인딩
- TailwindCSS를 사용하여 스타일을 간단히 구성
- `form` 태그: GET 방식으로 숫자 전송
- `JavaScript`: 입력 필드 초기화 ("지우기" 버튼)
- 타임리프 구문 예:
    - `th:value="${num1}"`
    - `th:text="${result}"`

### ✅ 학습 내용

- 의존성 주입(DI) :
    - @Component: 해당 클래스를 Spring Bean으로 등록
    - @Autowired: Bean 객체를 자동으로 주입받음
- 웹 요청 흐름 이해 :
    - @Controller: 웹 요청을 처리하는 클래스임을 명시
    - @GetMapping: GET 방식의 URL 요청을 처리
    - @RequestParam: HTML 폼의 데이터를 매개변수로 전달받을 때 사용
- MVC 패턴 학습: Controller → Model → View 흐름 이해
- Thymeleaf: 서버 데이터를 HTML에 출력하는 템플릿 문법 학습
- Tailwind CSS: 유틸리티 기반 CSS 프레임워크 사용 실습
- JavaScript: HTML에서 DOM을 조작하는 기본 예제 구현 (입력창 초기화 등)
- 정적 리소스 처리: HTML 내에서 JS, CSS 불러오기


