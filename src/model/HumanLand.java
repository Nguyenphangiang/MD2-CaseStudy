package model;

public class HumanLand extends Land {
    private int health = super.getHealth();
    public HumanLand() {
    }

    public HumanLand(int health) {
        super(health);
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public String toString() {
        return "HumanLand{" +
                "health=" + health +
                '}';
    }
}
