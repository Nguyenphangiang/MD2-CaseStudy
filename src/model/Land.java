package model;

import java.util.ArrayList;
import java.util.List;

public  class Land implements Subject{
    List<ObserverLand> observerLandList = new ArrayList<>();
    public static int DISTANCE = 100;
    public static int STEP = 2;

    public List<ObserverLand> getObserverLandList(){
        return  observerLandList;
    }

    public void setObserverLandList(List<ObserverLand> observerLandList) {
        this.observerLandList = observerLandList;
    }

    @Override
    public void addNew(ObserverLand observerLand) {
        observerLandList.add(observerLand);
    }

    @Override
    public void remove(ObserverLand observerLand) {
        observerLandList.remove(observerLand);
    }

    @Override
    public void notification(String mess) {
        for (ObserverLand o : observerLandList){
            o.update(mess);
        }
    }
}
