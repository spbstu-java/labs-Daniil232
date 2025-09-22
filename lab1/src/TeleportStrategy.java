class TeleportStrategy implements MoveStrategy {
    @Override
    public void move(String from, String to) {
        System.out.println("Герой телепортируется из " + from + " в " + to);
    }

    @Override
    public String getDescription() {
        return "Телепортация";
    }
}
