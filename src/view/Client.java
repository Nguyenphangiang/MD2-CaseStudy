package view;

import controller.AccountManager;
import controller.MissileManager;
import model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    public static final String BACK_MENU = "b";
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
            inputLogin = gameLoginMenu();
        } while (inputLogin != 0);
    }

    private static int gameLoginMenu() {
        int inputLogin;
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
                showLoginMenu();
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
                showLoginMenu();
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
        return inputLogin;
    }
    public static void showLoginMenu() {
        System.out.println("""
                -------------------------------------------------
                |         => .WELCOME. TO LOGIN MENU.<=         |
                |        Nhập tên tài khoản và mật khẩu.....    |               
                |                 Nhập 'b' để quay lại          |
                =================================================
                """);
        Scanner sc2 = new Scanner(System.in);
        System.out.println("---- Nhập tài khoản: ");
        String inputAccName = sc2.nextLine();
        if (!inputAccName.equals(BACK_MENU)) {
            Scanner sc3 = new Scanner(System.in);
            System.out.println("---- Nhập password: ");
            String inputPassword = sc3.nextLine();
            if (inputAccName.equals(AccountManager.ADMIN_ACCNAME) && (inputPassword.equals(AccountManager.ADMIN_ACCNAME))) {
                showAdminMenu();
            } else if (accountManager.checkUserAccount(inputAccName) && accountManager.checkUserPassword(inputPassword)) {
                accountManager.welcomeEntering(inputAccName);
            } else {
                System.out.println("------- Sai mật khẩu hoặc tài khoản rồi .-----");
                showLoginMenu();
            }
        } else {
            gameLoginMenu();
        }
    }
    public static void showAdminMenu() {
        int choice;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("""
                    ______> WELCOME ADMIN <______
                    1. Thêm tài khoản.
                    2.Xóa tài khoản.
                    3.Đổi password.
                    4.Tìm lại mật khẩu.
                    5.Show all account.
                    0.Exit to login menu.
                    Chọn tính năng :""");
            choice = sc.nextInt();
            switch (choice){
                case 1:
                    accountManager.creatAccount();
                    System.out.println("-----OKe-----");
                    break;
                case 2:
                    Scanner sc1 = new Scanner(System.in);
                    System.out.println("----- Xóa tài khoản : ");
                    String deleteAccountName = sc1.nextLine();
                    accountManager.deleteAccount(deleteAccountName);
                    System.out.println("-----OKe-----");
                    break;
                case 3:
                    accountManager.changePassword();
                    break;
                case 4:
                    accountManager.showUserPassword();
                    break;
                case 5:
                    accountManager.showAllAcount(accountClient);
                    break;
                case 0:
                    showLoginMenu();
                    break;
            }
        }while (choice!=0);
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
