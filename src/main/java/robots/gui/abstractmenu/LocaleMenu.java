package robots.gui.abstractmenu;

import robots.gui.ILocalable;
import robots.gui.LocalableComponents;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleMenu implements ILocalable {
    private final JMenu localeMenu = new JMenu(ResourceBundle.getBundle("locale").getString("title.changeLanguage"));

    public LocaleMenu(){
        LocalableComponents.components.add(this);
    }

    public JMenu generateLocaleMenu(ItemListener onChange) {
        JRadioButtonMenuItem russianLocale = new JRadioButtonMenuItem("Русский");
        JRadioButtonMenuItem englishLocale = new JRadioButtonMenuItem("English");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(russianLocale);
        buttonGroup.add(englishLocale);

        russianLocale.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Locale.setDefault(new Locale("ru", "RU"));
            }
        });
        englishLocale.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Locale.setDefault(new Locale("en", "US"));
            }
        });

        englishLocale.addItemListener(onChange);
        russianLocale.addItemListener(onChange);
        localeMenu.add(russianLocale);
        localeMenu.add(englishLocale);
        return localeMenu;
    }

    public void changeLocale() {
        localeMenu.setText(ResourceBundle.getBundle("locale").getString("title.changeLanguage"));
    }
}
