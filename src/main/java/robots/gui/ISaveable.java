package robots.gui;

import robots.gui.storemanager.WindowState;

import javax.swing.*;

public interface ISaveable{
    void setParams(WindowState params);
    WindowState windowParams();
}
