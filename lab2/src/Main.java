package lab2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Программа вывода метода (1-4):");
        System.out.println("1. Public methods");
        System.out.println("2. Private methods");
        System.out.println("3. Protected methods");
        System.out.println("4. All methods");

        int choice = scanner.nextInt();

        TestService service = new TestService();
        MethodExecutor executor = new MethodExecutor();

        System.out.println("Вызов метода...");

        switch (choice) {
            case 1:
                executor.invokePublicMethods(service);
                break;
            case 2:
                executor.invokePrivateMethods(service);
                break;
            case 3:
                executor.invokeProtectedMethods(service);
                break;
            case 4:
                executor.invokeAllMethods(service);
                break;
            default:
                System.out.println("Неверный выбор! Вызов всех методов....");
                executor.invokeAllMethods(service);
        }

        scanner.close();
    }
}