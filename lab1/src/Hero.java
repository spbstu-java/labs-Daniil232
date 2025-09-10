// Класс героя
class Hero {
    private String name;
    private MoveStrategy moveStrategy;
    private String currentLocation;

    public Hero(String name, String startingLocation) {
        this.name = name;
        this.moveStrategy = new WalkStrategy();
        this.currentLocation = startingLocation;
    }

    public void move(String to) {
        String from = currentLocation;
        System.out.print(name + " (" + moveStrategy.getDescription() + "): ");
        moveStrategy.move(from, to);
        currentLocation = to;
    }

    public void setMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
        System.out.println(name + " теперь перемещается способом: " + moveStrategy.getDescription());
    }

    public String getCurrentMoveStrategy() {
        return moveStrategy.getDescription();
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public String getName() {
        return name;
    }
}