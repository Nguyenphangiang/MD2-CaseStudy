package model;

public class HumanLand extends Land implements ObserverLand{
    private int health = 1000;

    public HumanLand() {
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return "HumanLand{" +
                "health=" + health +
                '}';
    }

    @Override
    public void update(String mess) {
        System.out.println( mess + " trúng người rồi trừ tiền.");
    }
}
