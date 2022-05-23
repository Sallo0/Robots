package robots.gui.windows;

import robots.locale.ILocalable;
import robots.locale.LocalableComponents;
import robots.storage.ISaveable;
import robots.storage.WindowState;

import javax.swing.*;

public abstract class AbstractWindow extends JInternalFrame implements ILocalable, ISaveable {

    public AbstractWindow(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
        super(title, resizable, closable, maximizable, iconifiable);
        LocalableComponents.components.add(this);
    }

    @Override
    public abstract void changeLocale();

    @Override
    public WindowState windowParams() {
        return new WindowState(
                getWidth(),
                getHeight(),
                getX(),
                getY(),
                isIcon()
        );
    }

    @Override
    public void setParams(WindowState windowState) {
        this.setSize(windowState.getWidth(), windowState.getHeight());
        this.setLocation(windowState.getLocationX(), windowState.getLocationY());
        try {
            this.setIcon(windowState.isIcon());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
