@Setter를 클래스에 붙이는 방식의 문제점
java
@Getter
@Setter
public class MemberEntity {
...
}
이렇게 하면
모든 필드에 대해 public setter가 생성되며, 외부에서 마음대로 값을 변경할 수 있게 됩니다. 이는 다음과 같은 문제를 일으킬 수 있습니다:
불변성 위반
엔티티는 가능한 한 불변(immutable)한 상태로 유지하는 것이 좋습니다. 변경이 자유롭다면 예측 불가능한 상태 변화가 생깁니다.
보안 및 데이터 무결성 문제
예: id나 joindate 같은 값은 외부에서 수정되어선 안 되는데, @Setter를 전부 붙이면 이런 필드도 setter가 생깁니다.
JPA 내부 동작과 충돌 가능성
JPA는 Entity를 관리할 때 프록시 객체를 사용하는데, setter가 남발되면 프록시가 의도치 않게 상태를 변경할 가능성이 생깁니다.
:흰색_확인_표시: 직접 메서드로 Setter 역할을 수행하는 방식의 장점
java
public void update(String userId, String userPw, String userName, String userRole) {
this.userId = userId;
this.userPw = userPw;
this.userName = userName;
this.userRole = userRole;
}
이 방식은 다음과 같은 장점을 가집니다:
변경 가능한 필드를 명확히 통제 가능
어떤 필드가 언제, 어떻게 바뀔 수 있는지 코드를 통해 명시적으로 나타냄.
유지보수가 쉬움
나중에 특정 필드 변경에 대해 로깅, 유효성 검사 등의 추가 작업을 넣기 쉬움.
불변성 부분 유지
id, joindate 등은 생성자에서만 설정되고, 이후 변경되지 않도록 설계할 수 있음.
:반짝임: 결론
Setter를 필요한 곳에만 명시적으로 작성하면:
코드의 명확성과 안전성이 높아지고,
예측 가능한 방식으로 Entity를 조작할 수 있으며,
JPA나 Lombok의 의도와도 잘 맞습니다.