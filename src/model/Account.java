package model;

import java.io.Serializable;

public class Account implements Serializable {
    private String accName;
    private String password;

    public Account() {
    }

    public Account(String accName, String password) {
        this.accName = accName;
        this.password = password;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accName='" + accName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
