package controller;

import model.Rocket;
import storage.AccountData;
import storage.IAccountData;
import model.Account;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountManager {
    private static final String ACCOUNT_REGEX = "^[a-zA-Z0-9]{8,12}$";
    private static final String PASSWORD_REGEX = "^[a-z0-9]{8,12}$";
    public static final String ADMIN_ACCNAME = "admin123";
    public static ArrayList<Account> accountList = AccountData.saveFile;
    private IAccountData iAccountData = new AccountData();

    public void creatAccount(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập tên tài khoản muốn tạo: ");
        String accountName = sc.nextLine();
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Nhập mật khẩu muốn tạo: ");
        String password = sc1.nextLine();
        System.out.println(validateAccount(accountName));
        System.out.println(validatePassword(password));
        if (validateAccount(accountName) && validatePassword(password)){
            Account account = new Account(accountName,password);
            if (checkAccountName(account.getAccName())){
                System.out.println("----------- Tên bị trùng rồi....");
                creatAccount();
            } else {
                accountList.add(account);
                try {
                    iAccountData.writeFile(accountList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("----- Tên tài khoản hoặc mật khẩu không hợp lệ !!!");
            System.out.println("----- Tên tài khoản  và mật khẩu bao gồm 8 chữ cái hoặc số.");
            System.out.println("----- Tên tài khoản  và mật khẩu không có ký tự đặc biệt. ");
            System.out.println("----- Mật khẩu không được viết hoa.");
            System.out.println(".....Nhập lại tên tài khoản và mật khẩu. !!! ");
            creatAccount();
        }
    }
    public void showAllAcount(ArrayList<Account> accountList){
        accountList = iAccountData.readFile();
        for (Account a : accountList){
            System.out.println(a);
        }
    }
    public boolean checkUserAccount(String account){
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getAccName().equals(account)){
                return true;
            }
        }
        return false;
    }public boolean checkUserPassword(String password){
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    private void deleteAccount(String accName){
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getAccName().equals(accName)){
                accountList.remove(i);
                break;
            }
        }
    }
    private boolean checkAccountName(String name){
        boolean check = false;
        for (Account account : accountList) {
            if (account.getAccName().equals(name)) {
                check = true;
            }
            return check;
        }
        return check;
    }
    private int getIndexAccountByName(String name){
        int index = -1;
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getAccName().equals(name)){
                index = i;
                break;
            }else return index;
        } return index;
    }
    private void changePassword(){
        Scanner sc1 = new Scanner(System.in);
        System.out.println("------ Nhập tên tài khoản muốn đổi mật khẩu: ");
        String accName = sc1.nextLine();
        if (isUserAccount(accName)){
            int accountAdress = getIndexAccountByName(accName);
            Scanner sc = new Scanner(System.in);
            System.out.println("----- Nhập mật khẩu mới: ");
            String newPassword = sc.nextLine();
            accountList.get(accountAdress).setPassword(newPassword);
            System.out.println("----- OKE!!!");
            }else {
            System.out.println("----- Kiểm tra lại đi...");
        }
    }
    private boolean isUserAccount(String accName){
        for (Account account : accountList){
            if (account.getAccName().equals(accName)){
                return true;
            }
        } return false;
    }
    private void showUserPassword(){
        Scanner sc = new Scanner(System.in);
        System.out.println("----- Nhập tên tài khoản muốn show mật khẩu: ");
        String accName = sc.nextLine();
        if (isUserAccount(accName)){
            System.out.println("----- Mật khẩu là :  " + accountList.get(getIndexAccountByName(accName)).getPassword());
        } else {
            System.out.println("----- Kiểm tra lại đi...");
        }
    }
    public boolean validateAccount(String regex){
        Pattern pattern = Pattern.compile(ACCOUNT_REGEX);
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }
    public boolean validatePassword(String regex){
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }
    private void welcomeEntering(String playerName){
        System.out.println("====================================");
        System.out.println("|    > WELCOME TO MISSILE GAME <   |");
        System.out.println(">>>>>>>> " + playerName + " <<<<<<<<");
        System.out.println("|----------------------------------|");
    }
    public void showLoginMenu(){
        System.out.println("""
                -------------------------------------------------
                |         => .WELCOME. TO LOGIN MENU.<=         |
                |        Nhập tên tài khoản và mật khẩu.....    | 
                |                                               |
                =================================================
                """);
        Scanner sc2 = new Scanner(System.in);
        System.out.println("---- Nhập tài khoản: ");
        String inputAccName= sc2.nextLine();
        Scanner sc3 = new Scanner(System.in);
        System.out.println("---- Nhập password: ");
        String inputPassword = sc3.nextLine();
        if (checkUserAccount(ADMIN_ACCNAME) &&(checkUserPassword(ADMIN_ACCNAME))){
            showAdminMenu();
        } else if (checkUserAccount(inputAccName) && checkUserPassword(inputPassword)){
            welcomeEntering(inputAccName);
        } else {
            System.out.println("------- Sai mật khẩu hoặc tài khoản rồi .-----");
            showLoginMenu();
        }
    }

    private void showAdminMenu() {
        int choice;
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
                    creatAccount();
                    break;
            case 2:
                    Scanner sc1 = new Scanner(System.in);
                    System.out.println("----- Xóa tài khoản : ");
                    String deleteAccountName = sc1.nextLine();
                    deleteAccount(deleteAccountName);
                    break;
            case 3:
                    changePassword();
                    break;
            case 4:
                    showUserPassword();
                    break;
            case 5:
                    showAllAcount(accountList);
                    break;
            case 0:
                    showLoginMenu();
                    break;
            }
    }
}
