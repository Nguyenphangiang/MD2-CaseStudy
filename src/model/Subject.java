package model;

public interface Subject {
    void addNew(ObserverLand observerLand);
    void remove(ObserverLand observerLand);
    void notification(String mess);
}
