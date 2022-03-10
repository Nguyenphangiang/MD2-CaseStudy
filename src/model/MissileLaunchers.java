package model;

public class MissileLaunchers {
    private boolean checkMissile = false;

    public MissileLaunchers() {
    }

    public MissileLaunchers(boolean checkMissile) {
        this.checkMissile = checkMissile;
    }

    public boolean isCheckMissile() {
        return checkMissile;
    }

    public void launch(){
        this.checkMissile = false;
    }
    public void clean(){
        this.checkMissile = true;
    }

    @Override
    public String toString() {
        return "MissileLaunchers{" +
                "checkMissile=" + checkMissile +
                '}';
    }
}
