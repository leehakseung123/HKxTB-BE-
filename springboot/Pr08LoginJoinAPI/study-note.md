# Pr08LoginJoinAPI 로그인 & 회원가입 (REST API + Fetch)


## 구현된 요구사항 요약

| 번호 | 요구사항 | 구현 상태 | 비고 |
|------|----------|------------|------|
| 1 | 클래스 객체 ArrayList로 데이터 저장 | 완료 | MemberService 내부 List<Member> 사용 |
| 2 | HTML form 태그 GET/POST 방식 사용 | 완료 | form onsubmit=... 방식으로 POST 처리 |
| 3 | Thymeleaf HTML + CSS + JS 구성 | 완료 | 타임리프 템플릿 구조 유지 (th:*) |
| 4 | 부트스트랩(Bootstrap) 스타일 적용 | 완료 | CDN 방식으로 적용됨 |
| 5 | 회원가입, 로그인 로직 구현 | 완료 | /api/join, /api/login REST API 구성 |
| 6 | 약관 미동의 시 JS Alert 처리 | 완료 | if (!agree) alert("약관동의 해주세요") |
| 7 | 아이디 미입력 시 JS Alert 처리 | 완료 | if (!username) alert("아이디를 입력하세요") |
| 8 | Fetch + REST API 방식 구현 | 완료 | JavaScript fetch() 사용하여 서버와 통신 |

---

## 클래스 및 스프링 기능 구성 상세 설명

### 1. Member (도메인 클래스)
- Java Bean 형식으로 설계됨
- 필드: `username`, `password`, `email`, `joindate`
- Getter/Setter 메서드를 통해 캡슐화
- Spring에서 JSON 객체로 받기 위해 기본 생성자 필요

### 2. MemberService (서비스 계층)
- `@Service` 어노테이션을 사용하여 스프링 빈으로 등록
- 회원 정보 저장용 `List<Member>`를 필드로 사용
- 기능:
    - `register(Member member)`: 가입 처리 및 joindate 설정
    - `login(String username, String password)`: 아이디/비밀번호 확인
    - `isDuplicated(String username)`: 중복 확인

### 3. MemberApiController (REST API 컨트롤러)
- `@RestController` + `@RequestMapping("/api")`
- `@PostMapping("/join")`: JSON으로 회원가입 처리
- `@PostMapping("/login")`: JSON으로 로그인 처리
- `@RequestBody`로 JSON 파싱
- 응답은 `Map<String, Object>`로 상태 전달 (`ok`, `fail`, `duplicated` 등)

### 4. MemberController (뷰 컨트롤러)
- `@Controller` 사용
- HTML 페이지 이동만 담당 (`/join`, `/login`)
- 타임리프와 함께 동작하며 서버측 렌더링 가능

---

## 주요 문법 및 기술 개요

| 문법 / 기술 | 설명 |
|-------------|------|
| `@Controller` | 뷰 템플릿 반환용 컨트롤러 지정 |
| `@RestController` | REST API 전용 응답 (JSON) 컨트롤러 지정 |
| `@PostMapping`, `@GetMapping` | HTTP 요청 방식에 따른 엔드포인트 지정 |
| `@RequestParam`, `@RequestBody` | 쿼리 파라미터 or JSON 데이터 바인딩 |
| `Model` | 타임리프에서 서버 데이터를 전달할 때 사용 |
| `List<Member>` | 메모리 저장소 역할 (데이터베이스 대체) |
| `fetch()` | 클라이언트에서 서버로 비동기 JSON 요청 처리 |
| `alert()` | 유효성 검사 실패 시 사용자 알림 |
| Bootstrap | UI 구성에 사용된 CSS 프레임워크 |
| Thymeleaf (`th:if`, `th:value`) | 서버 데이터 바인딩, 조건 렌더링 |

---

## 요청 흐름 

### 회원가입 흐름
1. join.html에서 JS 유효성 검사 수행
2. fetch('/api/join')로 JSON 데이터 POST 요청
3. 서버에서 중복 검사 후 가입 처리
4. 응답에 따라 alert 및 페이지 이동

### 로그인 흐름
1. login.html에서 입력 후 fetch('/api/login') 요청
2. 서버에서 아이디/비밀번호 확인
3. ok 또는 fail 응답에 따라 alert 처리

---

## 클라이언트 

### 회원가입 예시 (join.html)
```js
if (!username) alert("아이디를 입력하세요");
if (!agree) alert("약관동의 해주세요");

fetch("/api/join", {
  method: "POST",
  headers: { "Content-Type": "application/json" },
  body: JSON.stringify({ username, password, email })
});
```

### 로그인 예시 (login.html)
```js
fetch("/api/login", {
  method: "POST",
  headers: { "Content-Type": "application/json" },
  body: JSON.stringify({ username, password })
})
.then(data => {
  if (data.status === "ok") alert("로그인 성공!");
  else alert("로그인 실패");
});
```

---

## 트러블슈팅 이슈 정리

### 1. success 변수 오류
- 문제: success 변수가 선언되지 않아 오류 발생
- 해결: boolean status = ... 로 변수 이름 통일하여 해결

### 2. form 중복 정의 및 <head> 내 form 배치 오류
- 문제: <head> 내부에 <form> 사용하거나 중복 선언
- 해결: <body>에 form 하나만 두고 JavaScript로 처리



### 마치며

모든 요구사항을 충족하며 다음과 같은 구조로 완성되었습니다:

- DB 없이 ArrayList 기반 저장소 구현
- fetch와 JSON 기반의 RESTful 통신 구조 확립
- 타임리프 + 부트스트랩 기반 사용자 친화적 UI 구성

향후 확장 가능 기능:
- 세션 기반 로그인 유지 (HttpSession)
- 로그아웃 처리
- JPA 연동 및 DB 저장 방식으로 확장
- 비밀번호 암호화 및 Spring Security 적용