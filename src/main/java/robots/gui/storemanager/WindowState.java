package robots.gui.storemanager;

import java.io.Serializable;

public class WindowState implements Serializable {
    private int height;
    private int width;
    private int locationX;
    private int locationY;
    private boolean isIcon;

    public WindowState() {
    }

    public WindowState(int width, int height, int locationX, int locationY, boolean isIcon) {
        this.width = width;
        this.height = height;
        this.locationX = locationX;
        this.locationY = locationY;
        this.isIcon = isIcon;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }

    public boolean isIcon() {
        return isIcon;
    }

    public void setIcon(boolean icon) {
        isIcon = icon;
    }
}
