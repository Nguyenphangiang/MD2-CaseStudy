package storage;

import controller.MissileManager;
import model.Rocket;

import java.io.*;
import java.util.ArrayList;

public class MissileData implements IMissileData{

    public static final String SAVE_FILE = "rocket.dat";
    public static MissileData tool =new MissileData();
    public static ArrayList<Rocket> saveFile = tool.readFile();

    @Override
    public ArrayList<Rocket> readFile() {
        ArrayList<Rocket> tempList =new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(SAVE_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object result = ois.readObject();
            tempList =(ArrayList<Rocket>) result;
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            System.err.println("Loading Game Data.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tempList;
    }

    @Override
    public void writeFile(ArrayList<Rocket> arrayList) throws Exception {
        FileOutputStream fos = new FileOutputStream(SAVE_FILE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(arrayList);
        oos.close();
        fos.close();
    }
}
