// Стратегия: На лошади
class HorseRideStrategy implements MoveStrategy {
    @Override
    public void move(String from, String to) {
        System.out.println("Герой скачет на лошади из " + from + " в " + to);
    }

    @Override
    public String getDescription() {
        return "На лошади";
    }
}