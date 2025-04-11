# Pr07LogonJoin 회원가입 및 로그인

Spring Boot + Java 기반의 로그인/회원가입 기능 구현 예제입니다. 

---

##  프로젝트 개요

- **프레임워크**: Spring Boot 3
- **템플릿 엔진**: Thymeleaf
- **프론트엔드**: Bootstrap 5 + JavaScript Fetch API
- **데이터 저장 방식**: 메모리 내 ArrayList<Member>
- **핵심 기능**: 회원가입, 로그인, 아이디 중복확인 (REST 방식)


##  클래스 설계 구조

### ▶ Member (도메인 클래스)
- 사용자 정보를 저장하는 역할 (username, password, email, joindate)
- getter/setter를 통해 캡슐화

### ▶ MemberService (비즈니스 로직 계층)
- 회원가입, 로그인, 아이디 중복확인 로직을 담당
- 내부 데이터는 ArrayList로 저장되어 DB 없이 동작

### ▶ MemberController (웹 컨트롤러)
- `/join`, `/login` 요청을 처리
- 사용자 요청 → 서비스 계층 위임 → 모델 전달 → 뷰 렌더링 흐름 구성
- 중복확인 기능은 `@ResponseBody`로 JSON 반환 (REST 방식)

### ▶ Pr07LoginJoinApplication (프로젝트 진입점)
- `@SpringBootApplication`으로 컴포넌트 스캔 및 자동 설정
- 프로젝트의 main 클래스

---

## 요청 흐름

### 회원가입 요청 흐름
```
[join.html] → [POST /join] → MemberController → MemberService → List<Member> 추가 → redirect:/login
```

### 로그인 요청 흐름
```
[login.html] → [POST /login] → MemberController → MemberService → 인증 결과(Model 전달) → login.html
```

### 중복확인 흐름
```
[join.html JS fetch()] → [GET /check-duplicate] → MemberController → MemberService → JSON 응답 반환
```

---

##  클라이언트 & 서버 유효성 검사

| 위치 | 내용 | 예시 |
|------|------|------|
| 클라이언트 측 | JS로 유효성 검사 (비어있음, 약관 미체크 등) | alert("아이디를 입력하세요") |
| 서버 측 | `@RequestParam`과 조건 로직으로 검증 | `if (username == null)` 등 |

---

## 예외 처리 설계 고려사항

현재 예외 처리는 명시적으로 구현되어 있지 않으나 확장 시 다음과 같은 구조 고려 가능:

- `@ControllerAdvice` + `@ExceptionHandler`로 예외 처리 일괄 대응
- 중복된 아이디 요청 시 HTTP 상태코드와 메시지 조합 응답 (`status=409`, `{ status: 'duplicated' }` 등)
- 잘못된 로그인 시 사용자 친화적 메시지 처리

---

##  서비스 계층 분리 개선 포인트

- 현재 모든 로직이 `MemberService`에 집중됨
- 이후 아래와 같은 역할 분리를 통해 구조 향상 가능:
    - `MemberValidator` 클래스: 유효성 검사 로직 담당
    - `LoginService` / `JoinService`: 책임에 따라 로직 세분화

---

## 문법 및 기술 요약

###  회원가입
| 문법/기술           | 설명                        |
| --------------- | ------------------------- |
| `@PostMapping`  | POST 방식 요청 처리         |
| `@RequestParam` | HTML 폼 파라미터 바인딩       |
| `Model`         | 뷰 템플릿에 데이터 전달        |
| `fetch()`       | JS로 중복확인 요청           |

###  로그인 처리
| 문법/기술                 | 설명                      |
| --------------------- | ----------------------- |
| `@PostMapping`        | 로그인 처리 컨트롤러 매핑       |
| `Model`               | 로그인 결과 전달             |
| `Thymeleaf 조건문`       | 로그인 성공 여부 출력 조건 제어   |

###  아이디 중복확인
| 문법/기술           | 설명                        |
|--------------------|-----------------------------|
| `@GetMapping`      | GET 요청 처리                  |
| `@ResponseBody`    | JSON 형태로 직접 응답              |
| `Map<String,Object>` | JSON 응답 데이터 구성         |

###  템플릿 엔진 (Thymeleaf)
| 문법/기술              | 설명                        |
|----------------------|-----------------------------|
| `th:value`, `th:if` 등 | 조건 분기, 값 유지 등 HTML 템플릿 동작 |

###  데이터 저장
| 문법/기술         | 설명                        |
|------------------|-----------------------------|
| `ArrayList`      | 메모리 기반 사용자 저장 구조         |
| `List<Member>`   | 사용자 목록 관리용 컬렉션 구조       |


## 템플릿 구조 

### join.html
- `fetch()` 중복확인 호출
- 약관 동의 체크 및 패스워드 일치 유효성 검사
- POST `/join` 요청

### login.html
- POST `/login` 요청 처리
- `loginResult`에 따른 성공/실패 메시지 렌더링

```html
<div th:if="${loginResult != null}">
  <div th:if="${loginResult}" class="alert alert-success">로그인 성공</div>
  <div th:unless="${loginResult}" class="alert alert-danger">로그인 실패</div>
</div>
```

---

## 구현 기능 요약

| 기능     | 구현 여부 | 설명 |
|----------|-----------|------|
| 회원가입   | ✅ 완료    | 폼 데이터 처리, 중복확인 포함 |
| 로그인    | ✅ 완료    | 로그인 결과 조건 처리 |
| 중복확인   | ✅ 완료    | JS fetch + JSON 응답 |
| 로그인 유지 | ❌ 미구현   | 세션 처리 필요 (`HttpSession`) |
| 로그아웃   | ❌ 미구현   | `/logout` 구현 필요 |

