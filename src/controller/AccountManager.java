package controller;

import storage.AccountData;
import storage.IAccountData;
import model.Account;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountManager {
    private static final String ACCOUNT_REGEX = "\\w{8,}";
    private static final String PASSWORD_REGEX = "\\w";
    private static ArrayList<Account> accountList = new ArrayList<>();
    private IAccountData iAccountData = new AccountData();


    public void creatAccount(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập tên tài khoản muốn tạo: ");
        String accountName = sc.nextLine();
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Nhập mật khẩu muốn tạo: ");
        String password = sc1.nextLine();
        if (validateAccount(accountName) && validatePassword(password)){
            Account account = new Account(accountName,password);
            accountList.add(account);
            try {
                iAccountData.writeFile(accountList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("----- Tên tài khoản hoặc mật khẩu không hợp lệ !!!");
            System.out.println("----- Tên tài khoản bao gồm 8 chữ cái hoặc số.");
            System.out.println("----- Tên tài khoản  và mật khẩu không có ký tự đặc biệt. ");
            System.out.println(".....Nhập lại tên tài khoản và mật khẩu. !!! ");
            creatAccount();
        }
    }



    private boolean checkAccountName(String name){
        boolean check = false;
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getAccName().equals(name)){
                check = true;
            }
            return check;
        } return check;
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
    public boolean validateAccount(String rex){
        Pattern pattern = Pattern.compile(ACCOUNT_REGEX);
        Matcher matcher = pattern.matcher(rex);
        return matcher.matches();
    }
    public boolean validatePassword(String rex){
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(rex);
        return matcher.matches();
    }
}
