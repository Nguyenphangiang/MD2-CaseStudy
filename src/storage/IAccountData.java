package storage;

import model.Account;

import java.util.ArrayList;

public interface IAccountData {
    ArrayList<Account> readFile();
    void writeFile(ArrayList<Account> arrayList) throws Exception;
}
