package view;

import controller.AccountManager;
import controller.MissileManager;
import model.*;
import storage.MissileData;

import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    private static AccountManager accountManager = new AccountManager();
    private static MissileManager missileManager = new MissileManager();
    private static ArrayList<Rocket> missileClient = MissileManager.rocketList;
    private static ArrayList<Account> accountClient = AccountManager.accountList;

    public static void main(String[] args) {
        Account admin = new Account("admin123", "admin123");
        if (accountClient.size() == 0) {
            accountClient.add(admin);
        }
        TacticalMissile missile1 = new TacticalMissile("TM-1", "Russia", 150, 2, 100);
        TacticalMissile missile2 = new TacticalMissile("TM-2", "Usa", 200, 1, 150);

        BallisticMissile missile3 = new BallisticMissile("BM-1", "Russia", 100, 2, 200);
        BallisticMissile missile4 = new BallisticMissile("BM-2", "Usa", 150, 1, 250);

        if (missileClient.size() == 0) {
            missileClient.add(missile1);
            missileClient.add(missile2);
            missileClient.add(missile3);
            missileClient.add(missile4);
        }
        int inputLogin;
        do {
            Scanner accInput = new Scanner(System.in);
            System.out.println("""
                    -------------------------------------------------
                    |         => .WELCOME. TO LOGIN MENU.<=         |
                    |1. Tạo tài khoản mới.                          |
                    |2. Đã có tài khoản.                            |
                    |3. Thoát chương trình.                         |
                    =================================================
                    """);
            inputLogin = accInput.nextInt();
            switch (inputLogin) {
                case 1 -> {
                    accountManager.creatAccount();
                    accountManager.showLoginMenu();
                    int choice;
                    do {
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("""
                                1.Chế tạo tên lửa .
                                2.Hiển thị số tên lửa.
                                3.Phóng tên lửa.
                                4.Nạp tên lửa vào bệ phóng.
                                5.Xem ngân khố.
                                6.Xem tiến trình.
                                0.Thoát.
                                Chọn tính năng:\s""");
                        choice = scanner.nextInt();
                        missileManager.getHumanLandHealth(choice);
                        missileManager.getMonsterLandHealth(choice);
                        gameplay(choice);
                    } while (choice != 0);
                }
                case 2 -> {
                    accountManager.showLoginMenu();
                    int choice1;
                    do {
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("""
                                1.Chế tạo tên lửa .
                                2.Hiển thị số tên lửa.
                                3.Phóng tên lửa.
                                4.Nạp tên lửa vào bệ phóng.
                                5.Xem ngân khố.
                                6.Xem tiến trình.
                                0.Thoát.
                                Chọn tính năng:\s""");
                        choice1 = scanner.nextInt();
                        gameplay(choice1);
                    } while (choice1 != 0);
                }
                case 3 -> inputLogin = 0;
            }
        } while (inputLogin != 0);
    }


    private static void gameplay(int choice) {
        switch (choice) {
            case 1:
                Scanner sc = new Scanner(System.in);
                System.out.println("""
                        Chọn kiểu tên lửa muốn chế tạo.
                        1-1.Tactical TM-1.
                        1-2.Tactical TM-2.
                        1-3.Ballistic BM-1.
                        1-4.Ballistic BM-2.
                        .""");
                int typeMissile = sc.nextInt();
                switch (typeMissile) {
                    case 1:
                        missileManager.addNewMissile("tactical1");
                        break;
                    case 2:
                        missileManager.addNewMissile("tactical2");
                        break;
                    case 3:
                        missileManager.addNewMissile("ballistic1");
                        break;
                    case 4:
                        missileManager.addNewMissile("ballistic2");
                        break;
                }
                break;
            case 2:
                missileManager.showAllMissile(missileClient);
                break;
            case 3:
                Scanner sc1 = new Scanner(System.in);
                System.out.println("""
                        Chọn tên lửa muốn phóng.
                        3-1.TM-1.
                        3-2.TM-2.
                        3-3.BM-1.
                        3-4.BM-2
                        .""");
                int missileLaunch = sc1.nextInt();
                switch (missileLaunch) {
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
                missileManager.reloadLauncher();
                break;
            case 5:
                missileManager.checkTreasuryMoney();
                break;
            case 6:
                missileManager.showProcess();
                break;
        }
    }

}
