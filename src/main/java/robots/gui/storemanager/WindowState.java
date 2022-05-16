package robots.gui.storemanager;

import java.io.Serializable;

public class WindowState implements Serializable {
    private final int height;
    private final int width;
    private final int locationX;
    private final int locationY;
    private final boolean isIcon;

    public WindowState(int width, int height, int locationX, int locationY, boolean isIcon) {
        this.width = width;
        this.height = height;
        this.locationX = locationX;
        this.locationY = locationY;
        this.isIcon = isIcon;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public boolean isIcon() {
        return isIcon;
    }

}
