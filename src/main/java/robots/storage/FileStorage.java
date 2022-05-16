package robots.storage;

import robots.gui.storemanager.WindowState;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FileStorage {
    private Map<String, WindowState> windowStateStore;
    private static final File file = new File(System.getProperty("user.home") + File.separator + "robotState");

    public FileStorage(){
        windowStateStore = new HashMap<>();
    }

    public FileStorage(String path) {
        var dataObject = readFromFile(path);
        if (dataObject == null) {
            windowStateStore = new HashMap<>();
        } else {
            windowStateStore = (Map<String, WindowState>) dataObject;
        }
    }

    public void setState(String window, WindowState state) {
        windowStateStore.put(window, state);
    }

    public WindowState getState(String key) {
        if (windowStateStore.get(key) == null) {
            windowStateStore.put(key, new WindowState(800, 800, 10, 10, false));
        }
        return windowStateStore.get(key);
    }

    public void saveToFile(String path) {
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            var sb = new StringBuilder();
            for (var entry : windowStateStore.entrySet()) {
                var key = entry.getKey();
                var state = entry.getValue();
                sb.append(String.format("%s{\nheight=%d\nwidth=%d\nlocationX=%d\nlocationY=%d\nisIcon=%b\n}\n",
                        key,
                        state.getHeight(),
                        state.getWidth(),
                        state.getLocationX(),
                        state.getLocationY(),
                        state.isIcon()));
            }
            fileOut.write(sb.toString().getBytes(StandardCharsets.UTF_8));
            fileOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static Object readFromFile(String path) {
        if (file.exists()) {
            try (FileInputStream fileOut = new FileInputStream(path)) {
                String config = Files.readString(Path.of(path));
                Map<String, WindowState> res = new HashMap<String, WindowState>();
                for (var block : config.split("}\n")) {
                    if (Objects.equals(block, "")) {
                        continue;
                    }
                    var blockBebroid = block.split("\\{");
                    var key = blockBebroid[0];
                    var blockBebroidBody = blockBebroid[1];
                    var state = new WindowState();
                    for (var record : blockBebroidBody.split("\n")) {
                        if (Objects.equals(record, "")) {
                            continue;
                        }
                        var recordBebroid = record.split("=");
                        var field = recordBebroid[0];
                        var value = recordBebroid[1];
                        switch (field) {
                            case ("height"):
                                state.setHeight(Integer.parseInt(value));
                                break;
                            case ("width"):
                                state.setWidth(Integer.parseInt(value));
                                break;
                            case ("locationX"):
                                state.setLocationX(Integer.parseInt(value));
                                break;
                            case ("locationY"):
                                state.setLocationY(Integer.parseInt(value));
                                break;
                            case ("isIcon"):
                                state.setIcon(Boolean.parseBoolean(value));
                                break;
                        }
                    }
                    res.put(key, state);
                }
                return res;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}