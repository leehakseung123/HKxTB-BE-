# Pr01Counter

Spring Boot 기반의 간단한 카운터 웹 애플리케이션입니다.  
사용자는 버튼을 클릭하여 숫자를 증가하거나 감소시킬 수 있으며,  
화면은 Thymeleaf 기반의 HTML 템플릿을 통해 동적으로 렌더링됩니다.

---

## 주요 클래스

#### 1. `Counter.java`

    ```java
    @Component
    @Data
    public class Counter {
        private int count;
    }
    ```

   - `@Component` : Spring이 관리하는 Bean으로 등록하여, 애플리케이션 구동 시 자동으로 객체가 생성됩니다.
    - `@Data` : Lombok 라이브러리를 통해 `getter`, `setter`, `toString`, `equals` 등을 자동 생성합니다.

    > `@Component`를 붙이면 해당 클래스는 **Spring 컨테이너가 관리하는 객체(Bean)** 가 되어, 다른 클래스에서 `@Autowired`로 주입받아 사용할 수 있습니다.

---

#### 2. `HtmlController.java`

```java
@Controller
public class HtmlController {
    @Autowired
    private Counter counter;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("count", counter.getCount());
        return "index";
    }

    @GetMapping("/plus")
    public String plus(Model model) {
        counter.setCount(counter.getCount() + 1);
        model.addAttribute("count", counter.getCount());
        return "index";
    }

    @GetMapping("/minus")
    public String minus(Model model) {
        counter.setCount(counter.getCount() - 1);
        model.addAttribute("count", counter.getCount());
        return "index";
    }
}
```

- `@Controller` : Spring MVC의 컨트롤러임을 나타내며, 웹 요청을 처리합니다.

- `@Autowired` : DI(의존성 주입, Dependency Injection)를 통해 `Counter` 객체를 자동으로 주입받습니다.
    - **의존성 주입이란?** : 객체 간의 의존 관계를 개발자가 직접 생성하지 않고, Spring 프레임워크가 대신 생성해서 넣어주는 방식입니다.
    - 여기서는 `HtmlController`가 `Counter`를 직접 new 하지 않고, `@Autowired`를 통해 주입받습니다.
    
- `@GetMapping("/경로")` : 해당 경로로 들어오는 HTTP GET 요청을 처리합니다.
    - `/` : 초기 화면 (카운터 값 보여주기)
    - `/plus` : 카운터 증가
    - `/minus` : 카운터 감소

---

### 화면 구성: `index.html`

- **Thymeleaf** 템플릿 엔진을 사용해 HTML을 구성합니다.
- `${count}`를 통해 서버에서 전달된 값을 화면에 출력합니다.

📌 **Thymeleaf란?**

> 서버 사이드에서 동적으로 HTML을 생성해주는 템플릿 엔진입니다. Spring Boot와 자연스럽게 통합되어, 컨트롤러에서 전달한 모델 데이터를 HTML에 쉽게 바인딩할 수 있습니다.
---

### 🟩 Bean이란?

- Spring이 관리하는 객체를 뜻합니다.
- `@Component`, `@Service`, `@Repository`, `@Controller` 등으로 선언된 클래스는 자동으로 Bean으로 등록됩니다.
- Bean은 Spring 컨테이너가 생성하고, 관리하며, 필요한 곳에 의존성 주입(DI)을 통해 전달됩니다.

### 🟩 Lombok이란?

- 반복적인 Java 코드를 줄이기 위한 라이브러리입니다.
- `@Data`, `@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor` 등을 제공하여, 클래스 내부에 반복되는 메서드들을 자동 생성해줍니다.
- 컴파일 시점에 코드가 삽입되므로, 가독성이 높아지고 코드 작성이 편리해집니다.

### 🟩 Spring 컨테이너란?

- Spring 프레임워크의 핵심 개념으로, **Bean을 생성하고 관리하는 공간**입니다.
- Bean을 등록, 조회, 주입(DI), 생명주기 관리 등을 수행합니다.
- 애플리케이션 구동 시 컨테이너가 실행되고, `@Component` 등으로 등록된 클래스를 찾아 객체를 만들고 관리합니다.

### 🟩 Thymeleaf란?

- HTML, XML, JavaScript 등의 뷰 템플릿을 서버에서 동적으로 렌더링할 수 있도록 해주는 템플릿 엔진입니다.
- Spring Boot와의 연동이 간편하고, `th:text`, `th:if`, `th:each` 등의 문법으로 서버 데이터를 HTML에 매핑할 수 있습니다.
- `.html` 파일에서 JSP 없이도 뷰 구성을 할 수 있어, REST API 기반의 백엔드 서버에서도 많이 활용됩니다.

---

## 실행 방법

1. 터미널에서 프로젝트 루트 경로로 이동
2. 실행 명령:

```bash
./gradlew bootRun
```

---

## 마치며

- `@Component`, `@Controller`, `@Service`, `@Repository`는 Spring의 Bean 등록 어노테이션입니다.
- `@Autowired`를 통해 생성자 주입, 필드 주입, 세터 주입 등 다양한 방식으로 DI가 가능합니다.
- 이 프로젝트는 학습용으로, 기본적인 DI 구조 및 HTML 연동을 이해하는 데 목적이 있습니다.



