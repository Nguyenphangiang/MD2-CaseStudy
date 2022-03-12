package controller;

import model.*;
import storage.IMissileData;
import storage.MissileData;

import java.util.ArrayList;
import java.util.Random;


public class MissileManager  {
    public static final int MAX_BUY_TM1_MISSILE = 7;
    public static final int MAX_BUY_TM2_MISSILE = 6;
    public static final int MAX_BUY_BM1_MISSILE = 11;
    public static final int MAX_BUY_BM2_MISSILE = 7;
    public boolean rightTarget;
    public static final int MISSILE_SPEED = 20;
    private final LandManager landManager = new LandManager();
    private final MissileLaunchers missileLaunchers = new MissileLaunchers();
    public static IMissileData missileData = new MissileData();
    public static ArrayList<Rocket> rocketList = MissileData.saveFile;

    MissileFactory missileFactory = new MissileFactory();

    public  void addNewMissile(String type){
        if (missileFactory.factoryTreasury <= 0){
            System.out.println("----- Hết tiền rồi...");
        }else {
            switch (type){
                case "tactical1" :
                    TacticalMissile newTMissile =  missileFactory.creatTacticalMissile1();
                    if (newTMissile.getQuantity() >= MAX_BUY_TM1_MISSILE){
                        System.out.println("----- KO ĐỦ TIỀN -----");
                    } else {
                        if (checkNameMissile(newTMissile)){
                            int buyQuantity = newTMissile.getQuantity();
                            int totalQuantity = buyQuantity + rocketList.get(getIndexByName(newTMissile)).getQuantity();
                            rocketList.get(getIndexByName(newTMissile)).setQuantity(totalQuantity);
                            System.out.println("-----Xong-----");
                        } else {
                            rocketList.add(newTMissile);
                            System.out.println("-----Xong-----");
                        }
                        try {
                            missileData.writeFile(rocketList);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                case "tactical2" :
                    TacticalMissile newTMissile1 =  missileFactory.creatTacticalMissile2();
                    if (newTMissile1.getQuantity() >= MAX_BUY_TM2_MISSILE){
                        System.out.println("----- KO ĐỦ TIỀN -----");
                    } else {
                        if (checkNameMissile(newTMissile1)){
                            int buyQuantity = newTMissile1.getQuantity();
                            int totalQuantity = buyQuantity + rocketList.get(getIndexByName(newTMissile1)).getQuantity();
                            rocketList.get(getIndexByName(newTMissile1)).setQuantity(totalQuantity);
                            System.out.println("-----Xong-----");
                        } else {
                            rocketList.add(newTMissile1);
                            System.out.println("-----Xong-----");
                        }
                        try {
                            missileData.writeFile(rocketList);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                case "ballistic1":
                    BallisticMissile newBMissile = missileFactory.creatBallisticMissile1();
                    if(newBMissile.getQuantity() >= MAX_BUY_BM1_MISSILE){
                        System.out.println("----- KO ĐỦ TIỀN -----");
                    } else {
                        if (checkNameMissile(newBMissile)){
                            int buyQuantity = newBMissile.getQuantity();
                            int totalQuantity = buyQuantity + rocketList.get(getIndexByName(newBMissile)).getQuantity();
                            rocketList.get(getIndexByName(newBMissile)).setQuantity(totalQuantity);
                            System.out.println("-----Xong-----");
                        } else {
                            rocketList.add(newBMissile);
                            System.out.println("-----Xong-----");
                        }
                        try {
                            missileData.writeFile(rocketList);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    case "ballistic2":
                    BallisticMissile newBMissile2 = missileFactory.creatBallisticMissile2();
                    if (newBMissile2.getQuantity() >= MAX_BUY_BM2_MISSILE){
                        System.out.println("----- KO ĐỦ TIỀN -----");
                    } else {
                        if (checkNameMissile(newBMissile2)){
                            int buyQuantity = newBMissile2.getQuantity();
                            int totalQuantity = buyQuantity + rocketList.get(getIndexByName(newBMissile2)).getQuantity();
                            rocketList.get(getIndexByName(newBMissile2)).setQuantity(totalQuantity);
                            System.out.println("-----Xong-----");
                        } else {
                            rocketList.add(newBMissile2);
                            System.out.println("-----Xong-----");
                        }
                        try {
                            missileData.writeFile(rocketList);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
            }
        }

    }
    public void showAllMissile(ArrayList<Rocket> rockets){
        rockets = missileData.readFile();
        for (Rocket r : rockets){
            System.out.println(r);
        }
    }
    public void checkTreasuryMoney(){
        System.out.println("-----NGÂN KHỐ : " + missileFactory.factoryTreasury);
    }
    public void launchMissile(String name){
        if (missileLaunchers.isCheckMissile()){
            if (isNameMissile(name,rocketList)){
                int missilePosition = getMissilePosition(rocketList,name);
                if (rocketList.get(missilePosition).getQuantity() ==1){
                    missileFly();
                    target();
                    penalty(rocketList.get(missilePosition));
                    bonus(rocketList.get(missilePosition));
                    rocketList.remove(missilePosition);
                    missileLaunchers.launch();
                    try {
                        missileData.writeFile(rocketList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    int afterLaunch = rocketList.get(missilePosition).getQuantity() -1;
                    missileFly();
                    target();
                    penalty(rocketList.get(missilePosition));
                    bonus(rocketList.get(missilePosition));
                    rocketList.get(missilePosition).setQuantity(afterLaunch);
                    missileLaunchers.launch();
                    try {
                        missileData.writeFile(rocketList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else
                System.out.println("-----Không có tên lửa đó.");
        }else {
            System.out.println("-----Nạp đạn đã !!!");
        }
    }
    public void reloadLauncher(){
        try {
            System.out.println("-----Dừng khoảng chừng là 3 giây...");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----...OKE !!!");
        missileLaunchers.clean();
    }
    public void missileFly(){
        int runDistance = 0;
        while (runDistance < Land.DISTANCE){
            int speed = MISSILE_SPEED;
            runDistance += speed;
            String sky = "0";
            String land ="|";
            String under = "0";
            int percentTravel = (runDistance * 100)/Land.DISTANCE;
            for (int i = 0; i < Land.DISTANCE;i += Land.STEP){
                if (percentTravel >= i + Land.STEP){
                    sky += "_";
                    land += "~";
                    under +="_";
                } else if (percentTravel >= i && percentTravel < i + Land.STEP){
                    sky += "_";
                    land += ">O>";
                    under +="_";
                }else {
                    sky += "_";
                    land += "-";
                    under +="_";
                }
            }
            sky += "<|";
            land += " oOo|";
            under += "<|";
            System.out.println(sky);
            System.out.println(land);
            System.out.println(under);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("Missile Broken !!!");
            }
        }System.out.println("-------KABOOM !!! -------");
    }
    public void target(){
        int target = getCritical();
        if (target == 0){
           landManager.land.addNew(landManager.humanLand);
           landManager.land.notification("<HUMAN LAND>");
           landManager.land.remove(landManager.humanLand);
           rightTarget = false;
        } else {
            landManager.land.addNew(landManager.monsterLand);
            landManager.land.notification("<MONSTER LAND>");
            landManager.land.remove(landManager.monsterLand);
            rightTarget = true;
        }
    }
    public  int checkPower(Rocket rocket){
        int damageMissile = 0;
        if (rocket instanceof TacticalMissile){
           int power =  ((TacticalMissile) rocket).getPower();
           if (power == missileFactory.RUSSIA_TACTICAL_MISSILE_POWER){
               damageMissile = missileFactory.RUSSIA_TACTICAL_MISSILE_POWER;
           } else {
               damageMissile = missileFactory.USA_TACTICAL_MISSILE_POWER;
           }
            return damageMissile;
        } else {
            if (rocket instanceof BallisticMissile){
                int power = ((BallisticMissile) rocket).getPower();
                if (power == missileFactory.RUSSIA_BALLISTIC_MISSILE_POWER){
                    damageMissile = missileFactory.RUSSIA_BALLISTIC_MISSILE_POWER;
                } else {
                    damageMissile = missileFactory.USA_BALLISTIC_MISSILE_POWER;
                }
                return damageMissile;
            }
        } return damageMissile;
    }

    private void isCritical(Rocket rocket) {
        int critical = getCritical();
        if (critical == 0){
            missileFactory.setFactoryTreasury(missileFactory.getFactoryTreasury()-(checkPower(rocket)*2));
            landManager.humanLand.setHealth(landManager.humanLand.getHealth()-(checkPower(rocket)*2));
            System.out.println("-----CRITICAL x2 DAMAGE-----");
        } else {
            missileFactory.setFactoryTreasury(missileFactory.getFactoryTreasury()-checkPower(rocket));
            landManager.humanLand.setHealth(landManager.humanLand.getHealth()-checkPower(rocket));
        }
    }
    private int getCritical() {
        return (new Random()).nextInt(2);
    }

    public void penalty(Rocket rocket){
        if (!rightTarget){
            if (rocket.isAvoidRadar()){
                isCritical(rocket);
            } else {
                missileFactory.setFactoryTreasury(missileFactory.getFactoryTreasury()-checkPower(rocket));
                landManager.humanLand.setHealth(landManager.humanLand.getHealth()-checkPower(rocket));
            }
        }
    }
    public void bonus(Rocket rocket){
        if (rightTarget){
            if (rocket.isAvoidRadar()){
                isCritical(rocket);
            } else {
                missileFactory.setFactoryTreasury(missileFactory.getFactoryTreasury()+checkPower(rocket));
                landManager.monsterLand.setHealth(landManager.monsterLand.getHealth()-checkPower(rocket));
            }
        }
    }
    public void showProcess(){
        System.out.println("---> HUMAN IN LAND = " + landManager.humanLand.getHealth());
        System.out.println("---> MONSTER IN LAND = " + landManager.monsterLand.getHealth());
    }

    private boolean isNameMissile(String name,ArrayList<Rocket> rockets){
        for (int i = 0; i < rockets.size(); i++) {
            if (rockets.get(i).getName().equals(name)){
                return true;
            }
        }return false;
    }
    private boolean checkNameMissile(Rocket rocket){
        boolean check = false;
        for (int i = 0; i < rocketList.size(); i++) {
            if (rocket.getName().equals(rocketList.get(i).getName())){
                check = true;
                break;
            }
        }return check;
    }

    private int getMissilePosition(ArrayList<Rocket> rockets,String name){
        int index = -1;
        for (int i = 0; i < rockets.size(); i++) {
            if (rockets.get(i).getName().equals(name)){
                index = i;
                return index;
            }
        } return index;
    }
    private int getIndexByName(Rocket rocket){
        int index = -1;
        for (int i = 0; i < rocketList.size(); i++) {
            if (rocketList.get(i).getName().equals(rocket.getName())){
                index = i;
                return index;
            }
        } return index;
    }

    public void getHumanLandHealth(int choice){
        if (landManager.humanLand.getHealth() <=0){
            System.out.println("-----GAME_OVER-----");
            choice = 0;
        }
    }
    public void getMonsterLandHealth(int choice){
        if (landManager.monsterLand.getHealth()<=0){
            System.out.println("-----WIN-----");
            choice = 0;
        }
    }
}
