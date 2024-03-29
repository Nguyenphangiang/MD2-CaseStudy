package storage;

import model.Account;
import model.Rocket;

import java.io.*;
import java.util.ArrayList;

public class AccountData implements IAccountData{
    public static AccountData tool =new AccountData();
    public static ArrayList<Account> saveFile = tool.readFile();
    public static final String ACCOUNT_DAT = "account.dat";

    @Override
    public ArrayList<Account> readFile() {
        ArrayList<Account> tempList =new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(ACCOUNT_DAT);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object result = ois.readObject();
            tempList =(ArrayList<Account>) result;
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            System.err.println("Loading Account Data...");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tempList;
    }

    @Override
    public void writeFile(ArrayList<Account> arrayList) throws Exception {
        FileOutputStream fos = new FileOutputStream(ACCOUNT_DAT);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(arrayList);
        oos.close();
        fos.close();
    }
}
