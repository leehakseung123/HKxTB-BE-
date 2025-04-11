public class ex02 {
    public static void main(String[] args) {
        // 한줄 주석문
        /*
        여러줄
        주석문
        */
        //출력문
        // + 연산자는 왼쪽이나 오른쪽에 문자열이 있으면
        // 문자열 연결 연산자로 동작함.
        // 둘다 숫자이면 산술연산자로 동작함.
        System.out.println("화면출력");
        System.out.println("화면" + "출력");
        System.out.println("화면" + 10 + 20);
        System.out.println(10 + 20 + "화면");
        //연산자 우선순위가 헷갈리면 소괄호로 묶어준다.
        System.out.println(10 + (20 + "화면"));

        //println print printf
        //println : 문자열 한줄 출력 + 줄바꿈
        //print : 줄바꿈 없음
        //printf : 형식화된 출력문
        System.out.println("한줄출력후 줄바꿈");
        System.out.print("한줄출력후 줄바꿈 없음");
        // \n : 줄바꿈 특수문자
        System.out.printf("%d\n", 30); //10진수
        System.out.printf("%o\n", 30); //8진수
        System.out.printf("%x\n", 30); //16진수
        System.out.printf("%e\n", 300.0); //지수형 출력
    }
}