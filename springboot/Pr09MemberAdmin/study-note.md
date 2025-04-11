# Pr09MemberAdmin

Spring Boot 기반의 관리자 회원 목록 시스템 구현 프로젝트입니다. 

---

##  프로젝트 개요

| 항목 | 설명 |
|------|------|
| 프레임워크 | Spring Boot |
| 템플릿 엔진 | Thymeleaf |
| UI 스타일 | Bootstrap 5 |
| 데이터 저장 | Java 클래스 내 `ArrayList<Member>` |
| 통신 방식 | HTML Form (GET/POST), JS alert 사용 |
| DB 사용 여부 | ❌ 사용하지 아름 |
| API 방식 | ❌ JSON/REST 사용하지 아름 (Form 방식) |

---

##  구현된 기능

| 기능 | 설명 |
|------|------|
| 회원 목록 조회 | `/admin` 페이지에서 회원 전체 목록 표시 |
| 회원 수정 | `/update-form?index=x` → 폼 입력 후 `/update-action` 처리 |
| 회원 삭제 | `/delete?index=x` 링크 클릭 시 삭제 후 리다이렉트 |
| 수정/삭제 시 알림 | JS alert("수정되었습니다."), alert("삭제되었습니다.") 표시 |

---

## 클래스 구성 및 역할

### 1. Member (Model)
```java
private String username;
private String password;
private String email;
private LocalDate joindate;
```
- 회원 정보를 담당하는 데이터 모델 클래스
- Form에서 입력된 값과 매턴되어 사용됨

### 2. MemberService (Service)
```java
private final List<Member> members = new ArrayList<>();
```
- 회원 데이터 관리 단답
- `add()`, `update()`, `delete()`, `getAllMembers()` 등 로직 포함

### 3. MemberController (Controller)
- GET `/admin` → 회원 리스트 화면 반환
- GET `/update-form?index=x` → 수정 폼 반환
- POST `/update-action` → 수정 처리 후 리다이렉트
- GET `/delete?index=x` → 삭제 처리 후 리다이렉트

---

## 주요 문법 및 기술 요소

| 항목 | 사용 기술 및 설명 |
|------|------------------|
| 폼 처리 | `<form method="post" action="...">`, `@RequestParam`, `onsubmit` 사용 |
| 데이터 전달 | `Model model.addAttribute()`로 View에 데이터 전달 |
| 페이지 이동 | `return "admin"` or `redirect:/admin?status=deleted` |
| 날짜 처리 | `LocalDate.parse(String)`를 통해 날짜 문자열 처리 |
| 자바스크립트 경고차 | `alert("삭제되었습니다")` 등을 통해 사용자 알림 |

---

## 시간선 화면 단계 정보 (1)

```
[User] → /admin 가입 → [MemberController] → MemberService.getAllMembers()
      → model.addAttribute() → [Thymeleaf View: admin.html] 조회
```

---

## MVC 구조 단계 텍스트 (4)

```
[HTML Form View]
     ↑       ↓
Controller → Service → Model (ArrayList)
     ↓
   addAttribute() → View Rendering
```
- Controller에서 데이터를 수집한 후, Model에 전달 하고 View에서 Thymeleaf과 연계되어 볼 수 있는 구조

---

##  Thymeleaf 문법 요소 정리 (5)

| 문법 | 설명 |
|------|------|
| `th:value` | input 태그의 value 값 설정 시 사용 |
| `th:if / th:unless` | 조건문 처리 (`if-else` 구조와 유사) |
| `th:each` | 리스트 반복 렌더링 (회원 목록 출력 시 사용) |

---

## ✅ 트러블쉬팅 사례

| 이슈 | 원인 및 해결 방법 |
|------|------------------|
| Whitelabel Error (404) | HTML 파일이 `resources/templates`에 없거나 컨트롤러에서 건너 문자열 무적정 → 파일명/경로 확인 필요 |
| 수정/삭제 URL 오류 | `index` 값을 정확히 넘기지 않거나, ArrayList 범위 보너지는 경우 발생 → 인덱스 검증 추가 필요 |
| 페이지에서 alert 안 들면 | `redirect` 후 URL 파라미터 컨테츠 확인 필요 (`location.href.includes('status=updated')`) |

---

### 마치며
이 프로젝트는 Spring Boot 기반 관리자 페이지 학습을 위한 템플릿이자 실제 개발을 위한 기초 구현입니다.   
