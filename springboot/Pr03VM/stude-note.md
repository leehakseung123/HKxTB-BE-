# Pr03VM Study Note

> Spring Boot 기반의 자판기 상품 관리 웹 애플리케이션입니다.  
> 사용자는 상품을 추가, 수정, 삭제할 수 있으며, 상품 목록은 리스트 형태로 출력됩니다.   
> 뷰는 부트스트랩과 기본 HTML + JavaScript로 구성되며, 데이터는 DB가 아닌 `ArrayList<Product>` 객체로 관리됩니다.

## 🔧 주요 클래스 및 기능

### 📄 `Product.java`
- 상품 정보를 담는 모델 클래스
```java
public class Product {
    private String name;
    private int price;
    private LocalDate limitDate;

    // 생성자, getter/setter 포함
}
```

### 📄 `VmController.java`
- 상품 리스트 및 폼 화면을 매핑하는 컨트롤러
- URL 목록:
    - `/` : 상품 목록 페이지
    - `/addProductForm` : 상품 추가 폼 페이지
    - `/addProduct` : 상품 추가 처리
    - `/editProductForm?index={}` : 수정 폼
    - `/editProduct` : 수정 처리
    - `/deleteProduct?index={}` : 상품 삭제

- 상품은 `ArrayList<Product>`로 메모리 내 관리됨
- 날짜 파싱 (`LocalDate.parse()`) 적용

---

## 🚧 트러블슈팅

1. CSS 파일 로드 오류 (`404 Not Found`)
   - 최초 HTML에서 로컬 CSS 파일 (`index4.css`)을 로드하려 시도했으나, 실제로 존재하지 않아 오류 발생.
   - **해결책:** 해당 CSS 링크 제거하고 부트스트랩 CDN으로 대체하여 해결.

2.  상품 삭제 기능 오류
    - 상품 삭제 시 리스트가 갱신되지 않는 오류 발생.
     - **원인:** 삭제 후 리다이렉트가 제대로 이루어지지 않아 캐시 문제로 리스트가 갱신되지 않았음.
     - **해결책:** 컨트롤러의 삭제 메서드 내에서 정상적으로 `redirect:/`를 호출하고 인덱스 파라미터 처리를 추가하여 해결.

```java
@GetMapping("/deleteProduct")
public String deleteProduct(@RequestParam("index") int index) {
    if (index >= 0 && index < productList.size()) {
        productList.remove(index);
    }
    return "redirect:/";
}
```

3. 상품 추가 시 날짜 파싱 오류
   - `LocalDate.parse()`에서 포맷 문제로 예외 발생.
   - **해결책:** 날짜 형식을 `yyyy-MM-dd`로 맞추고 부트스트랩 DatePicker를 통해 강제로 형식을 통일.

```javascript
$(".datepicker").datepicker({
    format: "yyyy-mm-dd",
    language: "kr",
    autoclose: true
});
```

4. 언어 토글 미작동
   - 상품 목록 페이지에서 JavaScript로 작성된 언어 토글이 동작하지 않음.
   - **해결책:** 토글 스위치 이벤트 핸들러를 정확히 추가하여 한글 ↔ 영어 텍스트 변경이 가능하도록 구현.

```javascript
const langToggle = document.getElementById("changeToEng");
langToggle.addEventListener("change", function() {
  if(this.checked) {
    document.getElementById("VMTitle").textContent = "Vending Machine List";
    // 생략...
  } else {
    document.getElementById("VMTitle").textContent = "자판기 상품 목록";
    // 생략...
  }
});
```


## ✅ 학습 내용

- Spring MVC 컨트롤러 구성 방법
- Model 객체를 통한 View 데이터 전달
- HTML `<form>` 데이터 처리 (`@RequestParam`)
- 부트스트랩을 이용한 간단한 스타일링 적용
- Java의 `LocalDate`를 이용한 날짜 관리
- JavaScript로 Alert / Confirm 처리 구현
- Java 컬렉션 (`ArrayList`)으로 데이터 CRUD 구현

---

## 📌 주요 애노테이션

| 애노테이션 | 설명 |
|------------|------|
| `@Controller` | MVC 컨트롤러로 등록 |
| `@GetMapping` / `@PostMapping` | HTTP 요청 매핑 |
| `@RequestParam` | 폼에서 전달되는 값 처리 |

