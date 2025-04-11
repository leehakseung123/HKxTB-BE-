import java.util.Random;

// 이타를 안정적으로 저장할 때 주로 사용.
//          3. 자주 사용하는 유틸성 클래스에 지정한다.
//             new를 안해도 클래스 함수 사용가능
class BallFactory {
    int ballCount = 100;
    void make() {
        this.ballCount++;
        System.out.println("축구공 생산!");
    }
}
class FoodFactory {
    static int foodCount = 200;
    static void make(){
        foodCount++;
        System.out.println("도시락 생산!");
    }
}
public class ex21 {
    public static void main(String[] args) {
        //객체를 생성(new)해야 사용가능
        BallFactory bf = new BallFactory();
        System.out.println( bf.ballCount );
        //객체를 생성하지 않고도 사용가능(static)
        System.out.println( FoodFactory.foodCount );
        FoodFactory.make();

        //Random클래스 사용시
        Random random = new Random();
        Math.pow( 2, 3);
    }
}