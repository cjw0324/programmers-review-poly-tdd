package calc;

public class Main {
    // 테스트용 메인 메서드
    public static void main(String[] args) {
        String expr = "((3 + 5) * 5 + -10) * 10 / 5";
        int rs = Calc.run(expr);
        System.out.println("Result: " + rs); // 결과 출력
    }
}