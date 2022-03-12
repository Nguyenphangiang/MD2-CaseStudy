package model;

public class Account {
    private String accName;
    private String password;
    private Account account;

    public Account() {
        account = new Account();
    }

    public Account(String accName, String password) {
        this.accName = accName;
        this.password = password;
        account = new Account();
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
