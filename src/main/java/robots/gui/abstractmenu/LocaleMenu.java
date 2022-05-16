package robots.gui.abstractmenu;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleMenu {
    private JMenu localeMenu;
    private JRadioButtonMenuItem russianLocale;
    private JRadioButtonMenuItem englishLocale;
    public JMenu generateLocaleMenu(ItemListener onChange) {
        localeMenu = new JMenu(ResourceBundle.getBundle("locale").getString("title.changeLanguage"));
        russianLocale = new JRadioButtonMenuItem("Русский");
        englishLocale = new JRadioButtonMenuItem("English");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(russianLocale);
        buttonGroup.add(englishLocale);
        russianLocale.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Locale.setDefault(new Locale("ru", "RU"));
                }
            }
        });
        englishLocale.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Locale.setDefault(new Locale("en", "US"));
                }
            }
        });
        englishLocale.addItemListener(onChange);
        russianLocale.addItemListener(onChange);
        localeMenu.add(russianLocale);
        localeMenu.add(englishLocale);
        return localeMenu;
    }

    public void changeLocale(){
        localeMenu.setText(ResourceBundle.getBundle("locale").getString("title.changeLanguage"));
    }
}
