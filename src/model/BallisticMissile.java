package model;

public class BallisticMissile extends Rocket{
    private int power;


    public boolean isAvoidRadar() {
       super.setAvoidRadar(false);
       return super.isAvoidRadar();
    }

    public BallisticMissile() {
    }

    public BallisticMissile(int quantity) {
        super(quantity);
    }

    public BallisticMissile(String name, String countryFactory, int quantity) {
        super(name, countryFactory, quantity);
    }

    public BallisticMissile(String name, String countryFactory, int price, int quantity, int power) {
        super(name, countryFactory, price, quantity);
        this.power = power;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "BallisticMissile{" + super.toString() +
                "power=" + power + " avoid radar = false" +
                '}';
    }
}
