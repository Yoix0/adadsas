import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculadora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double num1 = 0;
        double num2 = 0;
        String operacion;
        boolean salir = false;


        while (!salir) {
            try {
                System.out.print("Ingrese el primer número (o 'q' para salir): ");
                if (scanner.hasNext("q") || scanner.hasNext("Q")) {
                    salir = true;
                    break;
                }
                num1 = scanner.nextDouble();

                System.out.print("Ingrese la operación (+, -, *, /, r2): ");
                operacion = scanner.next();

                if (operacion.equalsIgnoreCase("r2")) {
                    if (num1 < 0) {
                        System.out.println("Error: No se puede calcular la raíz cuadrada de un número negativo.");
                        continue;
                    }
                    System.out.println("Resultado: " + Math.sqrt(num1));
                } else {
                    System.out.print("Ingrese el segundo número: ");
                    num2 = scanner.nextDouble();

                    double resultado = 0;
                    switch (operacion) {
                        case "+":
                            resultado = num1 + num2;
                            break;
                        case "-":
                            resultado = num1 - num2;
                            break;
                        case "*":
                            resultado = num1 * num2;
                            break;
                        case "/":
                            if (num2 == 0) {
                                System.out.println("Error: No se puede dividir por cero");
                                continue;
                            }
                            resultado = num1 / num2;
                            break;
                        default:
                            System.out.println("Operacion no válida. Use +, -, *, / o r2.");
                            continue;
                    }
                    System.out.println("Resultado: " + resultado);
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no vlida. Por favor, ingrese un número o una operación correcta.");
                scanner.next();
            } catch (Exception e) {
                System.out.println("Ocurrio un error inesperado: " + e.getMessage());
            }
        }
        scanner.close();
    }
}