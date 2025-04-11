# Spring Boot 계산기 프로젝트 구성 요약

## 전체 사용 클래스 및 구성 요소

| 구성 요소 | 파일명 / 클래스명 | 설명 |
|-----------|-------------------|------|
| 메인 클래스 | `Pr05CalcApiApplication` | Spring Boot 애플리케이션 시작점. 자동 설정 및 컴포넌트 스캔 포함 |
| REST API 컨트롤러 | `CalcRestController` | `/api/calc`에 POST 요청으로 계산 수행 (현재 뺄셈만 처리됨) | 
| MVC 컨트롤러 | `CalcController` | GET 방식 계산기 (`/calc`) 처리. `Model`에 결과 전달 | 
| 상태관리 컨트롤러 | `HtmlController` | 카운터 상태 증감 처리 (`/`, `/plus`, `/minus`) | 
| 카운터 빈 | `Counter` | 싱글톤 컴포넌트로 count 상태 저장 | 
| 프론트 HTML | `index.html` | 입력폼 + fetch API JS로 서버와 통신 | 

---

## 구성 요소 기능 요약

### Pr05CalcApiApplication
- `@SpringBootApplication`
- Spring Boot 실행, 설정 로딩, 컴포넌트 스캔 포함

### CalcRestController
```java
@RestController
public class CalcRestController {
    @PostMapping("/api/calc")
    public Map<String, Object> calc(@RequestBody Map<String, Object> req) {
        int result = ((Number) req.get("num1")).intValue() - ((Number) req.get("num2")).intValue();
        return Map.of("status", "ok", "result", result);
    }
}
```
- fetch API 요청 처리
- JSON 형식 결과 반환 `{ status: "ok", result: 계산결과 }`

### CalcController
```java
@Controller
public class CalcController {
    @GetMapping("/calc")
    public String calc(@RequestParam String num1, @RequestParam String num2, @RequestParam String op, Model model) {
        return "index";
    }
}
```
- 전통적인 HTML 기반 계산 처리
- 결과를 Model에 담아 `index.html` 렌더링

### HtmlController와 Counter
- `/`, `/plus`, `/minus` 요청 처리
- 싱글톤 Counter 빈을 통해 count 상태 유지

### Counter 클래스
```java
@Component
@Data
public class Counter {
    private int count;
}
```
- `@Component`와 Lombok `@Data`를 사용
- 애플리케이션 전역에서 상태 공유

---

## 프론트엔드 요약

### index.html
- TailwindCSS로 스타일링된 계산기 UI
- 두 개의 숫자 입력 필드와 버튼 UI 구성
- 결과는 읽기 전용 input 필드에 표시됨

### JavaScript 로직
```javascript
fetch('/api/calc', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({ num1, num2 })
})
.then(res => res.json())
.then(json => document.getElementById('result').value = json.result);
```
- 버튼 클릭 시 서버로 JSON 데이터 전송 후 결과 출력

---

## 주요 문법 및 스프링 부트 기능 정리

### `@SpringBootApplication`
- `@ComponentScan`, `@Configuration`, `@EnableAutoConfiguration`을 포함한 복합 애노테이션
- 프로젝트의 메인 진입점 클래스에 선언

### `@RestController` vs `@Controller`
- `@RestController`: JSON 응답 전용 (자동 `@ResponseBody` 포함)
- `@Controller`: HTML 템플릿 응답 전용 (Thymeleaf 등과 함께 사용)

### `@RequestBody`
- HTTP 요청의 body에 포함된 JSON 데이터를 Java 객체로 자동 변환

### `@RequestParam`
- URL 쿼리 파라미터를 메서드 인자로 바인딩

### `@Component`
- 스프링이 관리하는 Bean으로 등록됨
- `@Autowired`를 통해 의존성 주입 가능

### `Model`
- 컨트롤러에서 뷰 템플릿으로 값을 전달하기 위한 객체
- `model.addAttribute("key", value)` 형태로 사용

### Lombok `@Data`
- `@Getter`, `@Setter`, `@ToString`, `@EqualsAndHashCode`, `@RequiredArgsConstructor` 등을 자동 생성

---

## 테스트 시 유의사항
- `fetch()` 방식은 JS에서 실행되므로 브라우저 콘솔에서 오류 확인 필요
- REST API와 전통 HTML 방식이 모두 존재하여 기능 중복 가능성 있음
- `CalcRestController`의 `result` 키 오타 (`reult`) 수정 필요

---

## 결론
- 전통 MVC 방식과 REST API 방식이 혼합된 하이브리드 구조
- 각각 독립적으로 기능하며, 모두 실제로 사용되고 있음
- 필요 시 기능 분리 및 명확한 API 구조로 개선 가능

