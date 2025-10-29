package lab4;

import lab4.src.*;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final MathOperations mathService = ServiceFactory.createMathOperations();
    private static final StringOperations stringService = ServiceFactory.createStringOperations();
    private static final CollectionOperations collectionService = ServiceFactory.createCollectionOperations();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = getIntInput("Выберите операцию: ");

            switch (choice) {
                case 1 -> demonstrateAverage();
                case 2 -> demonstrateUpperCaseWithPrefix();
                case 3 -> demonstrateUniqueSquares();
                case 4 -> demonstrateLastElement();
                case 5 -> demonstrateSumEven();
                case 6 -> demonstrateMapByFirstCharacter();
                case 0 -> {
                    running = false;
                    System.out.println("До свидания!");
                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\nВЫБЕРИТЕ ОПЕРАЦИЮ0");
        System.out.println("1. Среднее значение списка целых чисел");
        System.out.println("2. Строки в верхний регистр с префиксом '_new_'");
        System.out.println("3. Квадраты уникальных элементов списка");
        System.out.println("4. Последний элемент коллекции");
        System.out.println("5. Сумма чётных чисел массива");
        System.out.println("6. Преобразование строк в Map (первый символ - ключ)");
        System.out.println("0. Выход");
    }

    // 1. Метод, возвращающий среднее значение списка целых чисел
    private static void demonstrateAverage() {
        System.out.println("\n--- Среднее значение списка чисел ---");
        List<Integer> numbers = getIntegerList("Введите числа через пробел: ");
        if (numbers.isEmpty()) {
            System.out.println("Список пуст. Невозможно вычислить среднее значение.");
        } else {
            double average = mathService.getAverage(numbers);
            System.out.println("Среднее значение: " + average);
        }
    }

    // 2. Метод, приводящий все строки в списке в верхний регистр и добавляющий префикс «_new_»
    private static void demonstrateUpperCaseWithPrefix() {
        System.out.println("\n--- Строки в верхний регистр с префиксом '_new_' ---");
        List<String> strings = getStringList("Введите строки через запятую: ");
        List<String> result = stringService.convertToUpperCaseWithPrefix(strings);
        System.out.println("Результат: " + result);
    }

    // 3. Метод, возвращающий список квадратов всех встречающихся только один раз элементов списка
    private static void demonstrateUniqueSquares() {
        System.out.println("\n--- Квадраты уникальных элементов ---");
        List<Integer> numbers = getIntegerList("Введите числа через пробел: ");
        List<Integer> uniqueSquares = mathService.getUniqueElementsSquared(numbers);
        System.out.println("Квадраты уникальных элементов: " + uniqueSquares);
    }

    // 4. Метод, принимающий на вход коллекцию и возвращающий ее последний элемент или кидающий исключение
    private static void demonstrateLastElement() {
        System.out.println("\n--- Последний элемент коллекции ---");
        List<String> collection = getStringList("Введите элементы коллекции через запятую: ");

        try {
            String lastElement = collectionService.getLastElement(collection);
            System.out.println("Последний элемент: " + lastElement);
        } catch (EmptyCollectionException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    // 5. Метод, принимающий на вход массив целых чисел, возвращающий сумму чётных чисел или 0
    private static void demonstrateSumEven() {
        System.out.println("\n--- Сумма чётных чисел массива ---");
        int[] numbers = getIntArray("Введите числа через пробел: ");
        int sum = mathService.sumOfEvenNumbers(numbers);
        System.out.println("Сумма четных чисел: " + sum);
    }

    // 6. Метод, преобразовывающий все строки в списке в Map, где первый символ – ключ, оставшиеся – значение
    private static void demonstrateMapByFirstCharacter() {
        System.out.println("\n--- Преобразование строк в Map ---");
        List<String> strings = getStringList("Введите строки через запятую: ");
        Map<Character, String> result = stringService.getMapByFirstCharacter(strings);
        System.out.println("Результат преобразования: " + result);
    }

    // Вспомогательные методы для ввода данных
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число!");
            }
        }
    }

    private static List<Integer> getIntegerList(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    return Collections.emptyList();
                }

                return Arrays.stream(input.split("\\s+"))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите числа через пробел! Например: 1 2 3 4");
            }
        }
    }

    private static int[] getIntArray(String prompt) {
        List<Integer> list = getIntegerList(prompt);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    private static List<String> getStringList(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return Collections.emptyList();
        }

        return Arrays.stream(input.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
}