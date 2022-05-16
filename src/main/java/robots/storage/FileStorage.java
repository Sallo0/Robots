package robots.storage;

import robots.gui.storemanager.WindowState;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileStorage {
    private Map<String, WindowState> windowStateStore = new HashMap<>();

    public void setState(String window, WindowState state) {
        windowStateStore.put(window, state);
    }

    public WindowState getState(String key){
        if (windowStateStore.get(key) == null) {
            windowStateStore.put(key, new WindowState(800, 800, 10, 10, false));
        }
        return windowStateStore.get(key);
    }

    public void saveToFile(String path) {
        try
        {
            try
            {
                FileOutputStream fileOut = new FileOutputStream(path);
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                objectOut.writeObject(windowStateStore);
                objectOut.close();
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public static Object readFromFile(String path) {
        try
        {
            try
            {
                FileInputStream inputFile = new FileInputStream(path);
                ObjectInputStream objectInput = new ObjectInputStream(inputFile);
                var result = objectInput.readObject();
                objectInput.close();
                return result;
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public FileStorage(String path){
        var dataObject = readFromFile(path);
        if (dataObject == null){
            windowStateStore = new HashMap<>();
        } else {
            windowStateStore = (Map<String, WindowState>) dataObject;
        }
    }
}
