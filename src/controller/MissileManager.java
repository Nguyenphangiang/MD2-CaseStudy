package controller;

import model.*;
import storage.IMissileData;
import storage.MissileData;

import java.util.ArrayList;

public class MissileManager  {

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
