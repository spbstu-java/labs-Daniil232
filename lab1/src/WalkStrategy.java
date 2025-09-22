class WalkStrategy implements MoveStrategy {
    @Override
    public void move(String from, String to) {
        System.out.println("Герой идет пешком из " + from + " в " + to);
    }

    @Override
    public String getDescription() {
        return "Пешком";
    }
}
