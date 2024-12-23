package calc;
public class Calc {
    private String expression;
    private int pos;

    // 주어진 식을 평가하는 메서드
    public static int run(String expr) {
        Calc calculator = new Calc(expr);
        return calculator.parseExpr();
    }

    // 생성자
    public Calc(String expr) {
        this.expression = expr;
        this.pos = 0;
    }

    // 현재 문자를 가져오는 메서드
    private char currentChar() {
        if (pos < expression.length()) {
            return expression.charAt(pos);
        }
        return '\0'; // 끝을 나타내는 널 문자
    }

    // 공백을 건너뛰는 메서드
    private void skipWhitespace() {
        while (Character.isWhitespace(currentChar())) {
            pos++;
        }
    }

    // 숫자를 파싱하는 메서드
    private int parseNumber() {
        skipWhitespace();
        int start = pos;
        if (currentChar() == '-') { // 음수 처리
            pos++;
        }
        while (Character.isDigit(currentChar())) {
            pos++;
        }
        String numberStr = expression.substring(start, pos);
        return Integer.parseInt(numberStr);
    }

    // factor = number | ( expr )
    private int parseFactor() {
        skipWhitespace();
        char ch = currentChar();
        if (ch == '(') {
            pos++; // '(' 건너뛰기
            int value = parseExpr();
            skipWhitespace();
            if (currentChar() == ')') {
                pos++; // ')' 건너뛰기
            } else {
                throw new RuntimeException("')' expected at position " + pos);
            }
            return value;
        } else {
            return parseNumber();
        }
    }

    // term = factor | term * factor | term / factor
    private int parseTerm() {
        int value = parseFactor();
        while (true) {
            skipWhitespace();
            char ch = currentChar();
            if (ch == '*' || ch == '/') {
                pos++;
                int nextFactor = parseFactor();
                if (ch == '*') {
                    value *= nextFactor;
                } else {
                    value /= nextFactor;
                }
            } else {
                break;
            }
        }
        return value;
    }

    // expr = term | expr + term | expr - term
    private int parseExpr() {
        int value = parseTerm();
        while (true) {
            skipWhitespace();
            char ch = currentChar();
            if (ch == '+' || ch == '-') {
                pos++;
                int nextTerm = parseTerm();
                if (ch == '+') {
                    value += nextTerm;
                } else {
                    value -= nextTerm;
                }
            } else {
                break;
            }
        }
        return value;
    }
}

