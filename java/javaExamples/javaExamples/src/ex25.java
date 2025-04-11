//싱글톤(Singleton)
//  : 프로그램안에서의 유일한 클래스 객체
//  : new를 통해서 여러개의 객체를 반복적으로 찍어낼 수 있다.
//  : 예) 붕어빵1, 붕어빵2, ....
//      절대붕어빵(유일한 붕어빵)
//유일한 객체가 필요한 이유
//  : 유일한 정보를 저장하기 위해서
class FishBun {
    int bunNo = 10;
}
class UniqueFishBun {
    int bunNo = 30;
    private static UniqueFishBun singletone;
    static UniqueFishBun getInstance() {
        if (singletone == null) {
            singletone = new UniqueFishBun();
        }
        return singletone;
    }
}

public class ex25 {
    public static void main(String[] args) {


        FishBun bun1 = new FishBun();
        FishBun bun2 = new FishBun();
        FishBun bun3 = new FishBun();
        System.out.println(bun1);
        System.out.println(bun2);
        bun1.bunNo = 20;
        System.out.println(bun1.bunNo);
        System.out.println(bun2.bunNo);
    }
}