package model;

public class MonsterLand extends Land implements ObserverLand{
    private int health = 1000;

    public MonsterLand(int health) {
        this.health = health;
    }

    public MonsterLand() {
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return "MonsterLand{" +
                "health=" + health +
                '}';
    }


    @Override
    public void update(String mess) {
        System.out.println( mess + " trúng quái vật, cộng tiền.");
    }
}
