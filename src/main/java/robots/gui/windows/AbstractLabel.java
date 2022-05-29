package robots.gui.windows;

import robots.locale.ILocalable;
import robots.locale.LocalableComponents;
import robots.storage.ISaveable;
import robots.storage.WindowState;

import javax.swing.*;

public class AbstractLabel extends JLabel implements ILocalable {

    public AbstractLabel(){
        LocalableComponents.components.add(this);
    }

    @Override
    public void changeLocale() {

    }
}
