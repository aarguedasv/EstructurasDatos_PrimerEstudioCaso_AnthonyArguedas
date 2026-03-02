public class AnalizadorExpresion {

    private static int precedencia(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    public static boolean verificarParentesis(String expresion) {

        PilaOperador pilaOperador = new PilaOperador();

        for (char c : expresion.toCharArray()) {

            if (c == '(') {
                pilaOperador.push(c);
            } else if (c == ')') {
                if (pilaOperador.estaVacia()) {
                    return false;
                }
                pilaOperador.pop();
            }
        }

        return pilaOperador.estaVacia();
    }

    public static String infijaAPostfija(String expresion) {

        if (expresion == null || expresion.trim().isEmpty()) {
            throw new RuntimeException("La expresión está vacía.");
        }

        if (!verificarParentesis(expresion)) {
            throw new RuntimeException("Paréntesis desbalanceados.");
        }

        PilaOperador pilaOperador = new PilaOperador();
        StringBuilder salida = new StringBuilder();
        int i = 0;

        while (i < expresion.length()) {

            char c = expresion.charAt(i);

            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }

            if (Character.isDigit(c)) {
                while (i < expresion.length() && Character.isDigit(expresion.charAt(i))) {
                    salida.append(expresion.charAt(i));
                    i++;
                }
                salida.append(" ");
                continue;
            }

            if (c == '(') {
                pilaOperador.push(c);
            } else if (c == ')') {

                while (!pilaOperador.estaVacia() && pilaOperador.peek() != '(') {
                    salida.append(pilaOperador.pop()).append(" ");
                }

                if (!pilaOperador.estaVacia()) {
                    pilaOperador.pop();
                } else {
                    throw new RuntimeException("Paréntesis desbalanceados.");
                }

            } else if (c == '+' || c == '-' || c == '*' || c == '/') {

                while (!pilaOperador.estaVacia()
                        && pilaOperador.peek() != '('
                        && precedencia(c) <= precedencia(pilaOperador.peek())) {

                    salida.append(pilaOperador.pop()).append(" ");
                }

                pilaOperador.push(c);

            } else {
                throw new RuntimeException("Carácter inválido: " + c);
            }

            i++;
        }

        while (!pilaOperador.estaVacia()) {

            char operador = pilaOperador.pop();

            if (operador == '(') {
                throw new RuntimeException("Paréntesis desbalanceados.");
            }

            salida.append(operador).append(" ");
        }

        return salida.toString().trim();
    }

    public static void validarPostfija(String postfija) {

        String[] tokens = postfija.split(" ");
        int contadorOperandos = 0;

        for (String token : tokens) {

            if (token.isEmpty())
                continue;

            if (token.matches("\\d+")) {
                contadorOperandos++;
            } else if (token.matches("[+\\-*/]")) {

                if (contadorOperandos < 2) {
                    throw new RuntimeException("Faltan operandos en la expresión.");
                }

                contadorOperandos--;

            } else {
                throw new RuntimeException("Token inválido en expresión postfija.");
            }
        }

        if (contadorOperandos != 1) {
            throw new RuntimeException("Expresión inválida.");
        }
    }
}