package view;

import controller.MissileManager;
import model.*;
import storage.MissileData;

import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    private static MissileLaunchers missileLaunchers = new MissileLaunchers();
    private static MissileManager missileManager = new MissileManager();
    private static ArrayList<Rocket> missileClient = MissileManager.rocketList;



    public static void main(String[] args) {
        TacticalMissile missile1 = new TacticalMissile("TM-1","Russia",150,2,100);
        TacticalMissile missile2 = new TacticalMissile("TM-2","Usa",200,1,150);

        BallisticMissile missile3 = new BallisticMissile("BM-1","Russia",100,2,200);
        BallisticMissile missile4 = new BallisticMissile("BM-2","Usa",150,1,250);

        if(missileClient.size() == 0){
            missileClient.add(missile1); missileClient.add(missile2);missileClient.add(missile3);missileClient.add(missile4);
        }
        int choice;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("""
                    1.Chế tạo tên lửa .
                    2.Hiển thị số tên lửa.
                    3.Phóng tên lửa.
                    4.Xem ngân khố.
                    5.Nạp tên lửa vào bệ phóng.
                    0.Thoát.
                    Chọn tính năng:\s""");
            choice = scanner.nextInt();
            switch (choice){
                case 1:
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Chọn kiểu tên lửa muốn chế tạo.\n1-1.Tactical.\n1-2.Ballistic.");
                    int typeMissile = sc.nextInt();
                    switch (typeMissile){
                        case 1:
                            missileManager.addNewMissile("tactical");
                            break;
                        case 2:
                            missileManager.addNewMissile("ballistic");
                            break;
                    }
                    break;
                case 2:
                    missileManager.showAllMissile(missileClient);
                    break;
                case 3:
                    Scanner sc1 = new Scanner(System.in);
                    System.out.println("Chọn tên lửa muốn phóng.\n3-1.TM-1.\n3-2.TM-2.\n3-3.BM-1.\n3-4.BM-2.");
                    int missileLaunch = sc1.nextInt();
                    switch (missileLaunch){
                        case 1:
                            missileManager.launchMissile("TM-1");
                            break;
                        case 2:
                            missileManager.launchMissile("TM-2");
                            break;
                        case 3:
                            missileManager.launchMissile("BM-1");
                            break;
                        case 4:
                            missileManager.launchMissile("BM-2");
                            break;
                        }
                    break;
                case 4:
                    missileManager.checkTreasuryMoney();
                    break;
                case 5:
                    missileManager.reloadLauncher();
                    break;
            }
        } while (choice != 0);
    }
}
