package model;

public class TacticalMissile extends Rocket{
    private int  power;
    private final boolean  avoidRadar = true;

    public TacticalMissile() {
    }

    public TacticalMissile(String name, String countryFactory, int price, int quantity, int power) {
        super(name, countryFactory, price, quantity);
        this.power = power;
    }

    public TacticalMissile(String name, String countryFactory, int quantity) {
        super(name, countryFactory, quantity);
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
    public boolean isAvoidRadar() {
        return avoidRadar;
    }
    @Override
    public String toString() {
        return "TacticalMissile{" + super.toString() +
                "avoidRadar=" + avoidRadar +
                '}';
    }
}
