package model;

import controller.MissileManager;

import java.util.Scanner;

public class MissileFactory  {

    public static final String TACTICAL_MISSILE_RUSSIA = "TM-1";
    public static final String RUSSIA = "Russia";
    public static final String TACTICAL_MISSILE_USA = "TM-2";
    public static final String USA = "USA";
    public static final String BALLISTIC_MISSILE_RUSSIA = "BM-1";
    public static final String BALLISTIC_MISSILE_USA = "BM-2";
    public int factoryTreasury = 1000;

    public int getFactoryTreasury() {
        return factoryTreasury;
    }

    public void setFactoryTreasury(int factoryTreasury) {
        this.factoryTreasury = factoryTreasury;
    }

    public  final int USA_TACTICAL_MISSILE_POWER = 150;
    public  final int USA_BALLISTIC_MISSILE_POWER = 250;

    public  final int USA_TACTICAL_MISSILE_PRICE = 200;
    public  final int USA_BALLISTIC_MISSILE_PRICE = 150;

    public  final int RUSSIA_TACTICAL_MISSILE_POWER = 100;
    public  final int RUSSIA_BALLISTIC_MISSILE_POWER = 200;

    public  final int RUSSIA_TACTICAL_MISSILE_PRICE = 150;
    public  final int RUSSIA_BALLISTIC_MISSILE_PRICE = 100;

    public MissileFactory(){
    }
    public TacticalMissile creatTacticalMissile1(){
        return getMissileTactical(TACTICAL_MISSILE_RUSSIA, RUSSIA, RUSSIA_TACTICAL_MISSILE_POWER, RUSSIA_TACTICAL_MISSILE_PRICE);
    }public TacticalMissile creatTacticalMissile2(){
        return getMissileTactical(TACTICAL_MISSILE_USA, USA, USA_TACTICAL_MISSILE_POWER, USA_TACTICAL_MISSILE_PRICE);
    }

    private TacticalMissile getMissileTactical(String tacticalMissileUsa, String usa, int usa_tactical_missile_power, int usa_tactical_missile_price) {
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Nhập số lượng : ");
        int missileQuantity = scanner1.nextInt();
        TacticalMissile tacticalMissile = new TacticalMissile(missileQuantity);
        tacticalMissile.setName(tacticalMissileUsa);
        tacticalMissile.setCountryFactory(usa);
        tacticalMissile.setPower(usa_tactical_missile_power);
        tacticalMissile.setPrice(usa_tactical_missile_price);
        factoryTreasury =  factoryTreasury - tacticalMissile.getPrice()* tacticalMissile.getQuantity();
        return tacticalMissile;
    }

    public BallisticMissile creatBallisticMissile1(){
        return getMissileBallistic(BALLISTIC_MISSILE_RUSSIA, RUSSIA, RUSSIA_BALLISTIC_MISSILE_POWER, RUSSIA_BALLISTIC_MISSILE_PRICE);
    }
        public BallisticMissile creatBallisticMissile2(){
            return getMissileBallistic(BALLISTIC_MISSILE_USA, USA, USA_BALLISTIC_MISSILE_POWER, USA_BALLISTIC_MISSILE_PRICE);
        }

    private BallisticMissile getMissileBallistic(String ballisticMissileUsa, String usa, int usa_ballistic_missile_power, int usa_ballistic_missile_price) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập số lượng : ");
        int missileQuantity = sc.nextInt();
        BallisticMissile ballisticMissile = new BallisticMissile(missileQuantity);
        ballisticMissile.setName(ballisticMissileUsa);
        ballisticMissile.setCountryFactory(usa);
        ballisticMissile.setPower(usa_ballistic_missile_power);
        ballisticMissile.setPrice(usa_ballistic_missile_price);
        factoryTreasury = factoryTreasury - ballisticMissile.getPrice()* ballisticMissile.getQuantity();
        return ballisticMissile;
    }

}
