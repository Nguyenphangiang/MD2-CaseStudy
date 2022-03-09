package model;

public class Land {
    private int health;

    public Land() {
    }

    public Land(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return
                "health=" + health +
                '}';
    }
}
