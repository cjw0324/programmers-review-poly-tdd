package calc;

public class MainCalc {
    public static void main(String[] args) {
        int real = ((3 + 5) * 5 + -10) * 10 / 10;
        String expr = "((3 + 5) * 5 + -10) * 10 / 10";
        int rs = Calc.run(expr);
        System.out.println("Calc Result: " + rs); // 결과 출력
        System.out.println("Real Result: " + real);

        System.out.println(real == rs);
    }
}
