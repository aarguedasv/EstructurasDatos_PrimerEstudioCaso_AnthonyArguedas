import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void menu() {
        int opcion;

        do {
            System.out.println("\n===== ANALIZADOR DE EXPRESIONES =====");
            System.out.println("1. Verificar paréntesis");
            System.out.println("2. Convertir infija a postfija");
            System.out.println("3. Evaluar expresión");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Ingrese un número válido.");
                scanner.next();
            }

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la expresión: ");
                    String exp1 = scanner.nextLine();
                    boolean balanceado = AnalizadorExpresion.verificarParentesis(exp1);

                    if (balanceado) {
                        System.out.println("Paréntesis balanceados.");
                    } else {
                        System.out.println("Paréntesis desbalanceados.");
                    }
                    break;

                case 2:
                    System.out.print("Ingrese la expresión infija: ");
                    String exp2 = scanner.nextLine();
                    try {
                        String postfija = AnalizadorExpresion.infijaAPostfija(exp2);
                        System.out.println("Expresión postfija:");
                        System.out.println(postfija);
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Ingrese la expresión infija: ");
                    String exp3 = scanner.nextLine();
                    try {
                        String post = AnalizadorExpresion.infijaAPostfija(exp3);
                        AnalizadorExpresion.validarPostfija(post);
                        System.out.println("La expresión está correcta.");
                    } catch (RuntimeException e) {
                        System.out.println("La expresión no es correcta.");
                        System.out.println("Motivo: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 4);
    }

    public static void main(String[] args) {
        menu();
    }
}