package model;

public class MonsterLand extends Land{
    private int health = super.getHealth();

    public MonsterLand() {
    }

    public MonsterLand(int health) {
        super(health);
    }
    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public String toString() {
        return "MonsterLand{" +
                "health=" + health +
                '}';
    }
}
