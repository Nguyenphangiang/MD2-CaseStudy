package controller;

import model.*;
import storage.IMissileData;
import storage.MissileData;

import java.util.ArrayList;
import java.util.Random;

public class MissileManager  {
    public boolean rightTarget;
    public static final int MISSILE_SPEED = 20;
    private LandManager landManager = new LandManager();
    private MissileLaunchers missileLaunchers = new MissileLaunchers();
    public static IMissileData missileData = new MissileData();
    public static ArrayList<Rocket> rocketList = MissileData.saveFile;

    MissileFactory missileFactory = new MissileFactory();

    public  void addNewMissile(String type){
        switch (type){
            case "tactical" :
                TacticalMissile newTMissile =  missileFactory.creatTacticalMissile();
                if (checkNameMissile(newTMissile)){
                    int buyQuantity = newTMissile.getQuantity();
                    int totalQuantity = buyQuantity + rocketList.get(getIndexByName(newTMissile)).getQuantity();
                    rocketList.get(getIndexByName(newTMissile)).setQuantity(totalQuantity);
                } else {
                    rocketList.add(newTMissile);
                }
                try {
                    missileData.writeFile(rocketList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "ballistic":
                BallisticMissile newBMissile = missileFactory.creatBallisticMissile();
                if (checkNameMissile(newBMissile)){
                    int buyQuantity = newBMissile.getQuantity();
                    int totalQuantity = buyQuantity + rocketList.get(getIndexByName(newBMissile)).getQuantity();
                    rocketList.get(getIndexByName(newBMissile)).setQuantity(totalQuantity);
                } else {
                    rocketList.add(newBMissile);
                }
                try {
                    missileData.writeFile(rocketList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
    public void showAllMissile(ArrayList<Rocket> rockets){
        rockets = missileData.readFile();
        for (Rocket r : rockets){
            System.out.println(r);
        }
    }
    public void checkTreasuryMoney(){
        System.out.println("-----NGÂN KHỐ" + missileFactory.factoryTreasury);
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
                System.out.println("Không có tên lửa đó.");
        }else {
            System.out.println("Nạp đạn đã !!!");
        }
    }
    public void reloadLauncher(){
        try {
            System.out.println("Dừng khoảng chừng là 3 giây...");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("...OKE !!!");
        missileLaunchers.clean();
    }
    public void missileFly(){
        int runDistance = 0;
        while (runDistance < Land.DISTANCE){
            int speed = MISSILE_SPEED;
            runDistance += speed;
            String land ="|";
            int percentTravel = (runDistance * 100)/Land.DISTANCE;
            for (int i = 0; i < Land.DISTANCE;i += Land.STEP){
                if (percentTravel >= i + Land.STEP){
                    land += "~";
                } else if (percentTravel >= i && percentTravel < i + Land.STEP){
                    land += ">O>";
                }else {
                    land += "_";
                }
            }
            land += "|";
            System.out.println(land);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("Missile Broken !!!");
            }
        }System.out.println("-------KABOOM !!! -------");
    }
    public void target(){
        int target = (new Random()).nextInt(2);
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
               damageMissile = 100;
           } else {
               damageMissile = 150;
           }
            return damageMissile;
        } else {
            if (rocket instanceof BallisticMissile){
                int power = ((BallisticMissile) rocket).getPower();
                if (power == missileFactory.RUSSIA_BALLISTIC_MISSILE_POWER){
                    damageMissile = 200;
                } else {
                    damageMissile = 250;
                }
                return damageMissile;
            }
        } return damageMissile;
    }
    public void penalty(Rocket rocket){
        if (!rightTarget){
            missileFactory.setFactoryTreasury(missileFactory.getFactoryTreasury()-checkPower(rocket));
            landManager.humanLand.setHealth(landManager.humanLand.getHealth()-checkPower(rocket));
        }
    }public void bonus(Rocket rocket){
        if (rightTarget){
            missileFactory.setFactoryTreasury(missileFactory.getFactoryTreasury()+checkPower(rocket));
            landManager.monsterLand.setHealth(landManager.monsterLand.getHealth()-checkPower(rocket));
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
    private boolean checkNameMissile(Rocket rocket){
        boolean check = false;
        for (int i = 0; i < rocketList.size(); i++) {
            if (rocket.getName().equals(rocketList.get(i).getName())){
                check = true;
                break;
            }
        }return check;
    }


}
