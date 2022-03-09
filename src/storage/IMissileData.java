package storage;

import model.Rocket;

import java.util.ArrayList;

public interface IMissileData {
    ArrayList<Rocket> readFile();
    void writeFile(ArrayList<Rocket> arrayList) throws Exception;
}
