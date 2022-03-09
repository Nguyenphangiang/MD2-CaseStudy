package view;

import controller.MissileManager;
import model.BallisticMissile;
import model.MissileFactory;
import model.Rocket;
import model.TacticalMissile;
import storage.MissileData;

import java.util.ArrayList;

public class Client {
    private static MissileManager missileManager = new MissileManager();
    private static ArrayList<Rocket> missileClient = MissileManager.rocketList;



    public static void main(String[] args) {
        TacticalMissile missile1 = new TacticalMissile("TM-1","Russia",150,2,100);
        TacticalMissile missile2 = new TacticalMissile("TM-2","Usa",200,1,150);

        BallisticMissile missile3 = new BallisticMissile("BM-1","Russia",100,2,200);
        BallisticMissile missile4 = new BallisticMissile("BM-3","Usa",150,1,250);


        missileClient.add(missile1); missileClient.add(missile2);missileClient.add(missile3);missileClient.add(missile4);

    }
}
