import java.util.Scanner;
// Главный класс
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== ИГРА: ПУТЕШЕСТВИЕ ГЕРОЯ ===");
        System.out.println("===============================");

        // Создаем героя
        System.out.print("Введите имя вашего героя: ");
        String heroName = scanner.nextLine();

        Hero hero = new Hero(heroName, "Замок");

        System.out.println("\nГерой " + hero.getName() + " создан!");
        System.out.println("Начальное местоположение: " + hero.getCurrentLocation());
        System.out.println("Способ перемещения: " + hero.getCurrentMoveStrategy());

        // Список доступных локаций
        String[] locations = {"Замок", "Лес", "Гора", "Деревня", "Река", "Пещера"};

        while (true) {
            System.out.println("\n" + "=".repeat(40));
            System.out.println("ТЕКУЩЕЕ СОСТОЯНИЕ:");
            System.out.println("Герой: " + hero.getName());
            System.out.println("Местоположение: " + hero.getCurrentLocation());
            System.out.println("Способ перемещения: " + hero.getCurrentMoveStrategy());
            System.out.println("=".repeat(40));

            System.out.println("\nВЫБЕРИТЕ ДЕЙСТВИЕ:");
            System.out.println("1 - Переместиться в другую локацию");
            System.out.println("2 - Сменить способ перемещения");
            System.out.println("3 - Показать доступные локации");
            System.out.println("4 - Выйти из игры");
            System.out.print("Ваш выбор: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите число от 1 до 4!");
                continue;
            }

            switch (choice) {
                case 1:
                    // Перемещение
                    System.out.println("\nДОСТУПНЫЕ ЛОКАЦИИ:");
                    for (int i = 0; i < locations.length; i++) {
                        if (!locations[i].equals(hero.getCurrentLocation())) {
                            System.out.println((i + 1) + " - " + locations[i]);
                        }
                    }

                    System.out.print("Выберите локацию для перемещения: ");
                    try {
                        int locationChoice = Integer.parseInt(scanner.nextLine());
                        if (locationChoice < 1 || locationChoice > locations.length) {
                            System.out.println("Неверный выбор локации!");
                            break;
                        }

                        String targetLocation = locations[locationChoice - 1];
                        if (targetLocation.equals(hero.getCurrentLocation())) {
                            System.out.println("Вы уже находитесь в этой локации!");
                        } else {
                            hero.move(targetLocation);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Пожалуйста, введите число!");
                    }
                    break;

                case 2:
                    // Смена способа перемещения
                    System.out.println("\nВЫБЕРИТЕ СПОСОБ ПЕРЕМЕЩЕНИЯ:");
                    System.out.println("1 - Пешком (базовый способ)");
                    System.out.println("2 - На лошади (быстрее)");
                    System.out.println("3 - Полёт (самый быстрый)");
                    System.out.println("4 - Телепортация (мгновенно)");
                    System.out.print("Ваш выбор: ");

                    try {
                        int strategyChoice = Integer.parseInt(scanner.nextLine());
                        switch (strategyChoice) {
                            case 1:
                                hero.setMoveStrategy(new WalkStrategy());
                                break;
                            case 2:
                                hero.setMoveStrategy(new HorseRideStrategy());
                                break;
                            case 3:
                                hero.setMoveStrategy(new FlyStrategy());
                                break;
                            case 4:
                                hero.setMoveStrategy(new TeleportStrategy());
                                break;
                            default:
                                System.out.println("Неверный выбор способа перемещения!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Пожалуйста, введите число!");
                    }
                    break;

                case 3:
                    // Показать доступные локации
                    System.out.println("\nДОСТУПНЫЕ ЛОКАЦИИ:");
                    for (String location : locations) {
                        if (location.equals(hero.getCurrentLocation())) {
                            System.out.println("✓ " + location + " (текущая)");
                        } else {
                            System.out.println("  " + location);
                        }
                    }
                    break;

                case 4:
                    // Выход
                    System.out.println("\nСпасибо за игру! До свидания!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Неверный выбор! Пожалуйста, выберите от 1 до 4.");
            }

            // Небольшая пауза для удобства чтения
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}