public class ex55 {
    public static void main(String[] args) {
        // throws문
        // :  자신을 호출한 메소드에 예외처리를 떠넘기는것.
        try {
            myFunc();
        } catch (Exception e) {
            e.getMessage();
        }
    }
    static void myFunc() throws Exception {
        System.out.println( 10/0 ); // 산술오류!
        System.out.println(" --- ");
    }
}

// 사용자 정의 예외
class MyException extends Exception {
    public MyException(String message) {
        super(message);
    }
}