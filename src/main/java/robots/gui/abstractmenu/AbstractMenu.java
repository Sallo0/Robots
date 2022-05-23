package robots.gui.abstractmenu;

import robots.locale.ILocalable;
import robots.locale.LocalableComponents;

public abstract class AbstractMenu implements ILocalable {

    public AbstractMenu() {
        LocalableComponents.components.add(this);
    }

    @Override
    public abstract void changeLocale();
}
