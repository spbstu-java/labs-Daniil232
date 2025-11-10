import java.util.Scanner;

import src.TextConverter;
import src.TextProcessorBuilder;
import exception.FileReadException;
import exception.InvalidFileFormatException;

public class Main {
    private static final String DICTIONARY_PATH = "dictionary.txt";
    private static TextConverter textProcessor;

    private static void initializeProcessor() throws FileReadException, InvalidFileFormatException {
        textProcessor = TextProcessorBuilder.buildBilingualProcessor(DICTIONARY_PATH);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Программа-переводчик");

        try {
            // Инициализация процессора
            initializeProcessor();

            System.out.println("Словарь успешно загружен!");
            System.out.println("--------------------------");

            runTranslationLoop(scanner);

        } catch (FileReadException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
            System.out.println("Проверьте наличие файла " + DICTIONARY_PATH);
        } catch (InvalidFileFormatException e) {
            System.err.println("Ошибка формата словаря: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Неожиданная ошибка: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void runTranslationLoop(Scanner scanner) {
        while (true) {
            System.out.println("Введите текст для перевода (eng->rus):");
            System.out.println("(или введите 'exit' для выхода, 'help' для справки)");
            System.out.print(">>>> ");

            String inputText = scanner.nextLine().trim();

            if ("exit".equalsIgnoreCase(inputText)) {
                System.out.println("До свидания!");
                break;
            }

            if ("help".equalsIgnoreCase(inputText)) {
                showHelp();
                continue;
            }

            if (inputText.isEmpty()) {
                System.out.println("Введите текст для перевода\n");
                continue;
            }

            try {
                // Используем новый метод convertText вместо translate
                String translatedText = textProcessor.convertText(inputText);

                System.out.println("Результат перевода:");
                System.out.println(translatedText);
                System.out.println("--------------------------");
            } catch (Exception e) {
                System.err.println("Ошибка при переводе: " + e.getMessage());
            }
        }
    }

    private static void showHelp() {
        System.out.println("\nСправка:");
        System.out.println("Введите текст на английском для перевода");
        System.out.println("Программа использует словарь из файла: " + DICTIONARY_PATH);
        System.out.println("Переводчик выбирает самый длинный подходящий вариант");
        System.out.println("Для выхода введите 'exit'");
        System.out.println("--------------------------\n");
    }
}