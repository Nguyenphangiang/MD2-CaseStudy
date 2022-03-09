package model;

import java.util.Scanner;

public class MissileFactory {

    public static final int USA_TACTICAL_MISSILE_POWER = 150;
    public static final int USA_BALLISTIC_MISSILE_POWER = 250;

    public static final int USA_TACTICAL_MISSILE_PRICE = 200;
    public static final int USA_BALLISTIC_MISSILE_PRICE = 150;

    public static final int RUSSIA_TACTICAL_MISSILE_POWER = 100;
    public static final int RUSSIA_BALLISTIC_MISSILE_POWER = 200;

    public static final int RUSSIA_TACTICAL_MISSILE_PRICE = 150;
    public static final int RUSSIA_BALLISTIC_MISSILE_PRICE = 100;

    private TacticalMissile tacticalMissile;
    private BallisticMissile ballisticMissile;

    Treasury treasury = new Treasury();

    public MissileFactory(){
    }
    public TacticalMissile creatTacticalMissile(){
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Nhập tên missile: ");
        String missileName = scanner1.nextLine();
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Nhập tên đất nước sản xuất (Russia or USA): ");
        String missileProducer = scanner2.nextLine();
        Scanner scanner3 = new Scanner(System.in);
        System.out.println("Nhập số lượng : ");
        int missileQantity = scanner3.nextInt();
        tacticalMissile = new TacticalMissile(missileName,missileProducer,missileQantity);
        if (tacticalMissile.getCountryFactory().equals("Usa")){
            tacticalMissile.setPower(USA_TACTICAL_MISSILE_POWER);
            tacticalMissile.setPrice(USA_TACTICAL_MISSILE_PRICE);
            productionCost(tacticalMissile);
            return tacticalMissile;
        } else {
            tacticalMissile.setPower(RUSSIA_TACTICAL_MISSILE_POWER);
            tacticalMissile.setPrice(RUSSIA_TACTICAL_MISSILE_PRICE);
            productionCost(tacticalMissile);
            return tacticalMissile;
        }
    }
    public BallisticMissile creatBallisticMissile(){
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Nhập tên missile: ");
        String missileName = sc1.nextLine();
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Nhập tên nước sản xuất: ");
        String missileProducer = sc2.nextLine();
        Scanner sc3 = new Scanner(System.in);
        System.out.println("Nhập số lượng : ");
        int missileQuantity = sc3.nextInt();
        ballisticMissile = new BallisticMissile(missileName,missileProducer,missileQuantity);
        if (ballisticMissile.getCountryFactory().equals("Usa")){
            ballisticMissile.setPower(USA_BALLISTIC_MISSILE_POWER);
            ballisticMissile.setPrice(USA_BALLISTIC_MISSILE_PRICE);
            productionCost(ballisticMissile);
            return ballisticMissile;
        } else {
            ballisticMissile.setPower(RUSSIA_BALLISTIC_MISSILE_POWER);
            ballisticMissile.setPrice(RUSSIA_BALLISTIC_MISSILE_PRICE);
            productionCost(ballisticMissile);
            return ballisticMissile;
        }
    }
    public  void productionCost(Rocket rocket){
        treasury.setMoney(treasury.getMoney()-(rocket.getPrice()* rocket.getQuantity()));
    }

    //thêm , sửa ...
}
