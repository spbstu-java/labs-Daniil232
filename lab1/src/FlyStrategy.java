class FlyStrategy implements MoveStrategy {
    @Override
    public void move(String from, String to) {
        System.out.println("Герой летит по воздуху из " + from + " в " + to);
    }

    @Override
    public String getDescription() {
        return "Полёт";
    }
}
