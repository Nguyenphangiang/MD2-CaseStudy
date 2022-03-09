package model;

public class Treasury {
    public static final int TREASURY_MONEY = 1000;
    public int money;
    private final Treasury treasury;

    public Treasury() {
        this.money = TREASURY_MONEY;
        treasury = new Treasury();
    }

    public int getMoney() {
        return money;
    }
    public void setMoney(int money){
        this.money = money;
    }

    @Override
    public String toString() {
        return "Treasury{" +
                "money=" + money +
                '}';
    }

    public Treasury getTreasury() {
        return treasury;
    }
}
