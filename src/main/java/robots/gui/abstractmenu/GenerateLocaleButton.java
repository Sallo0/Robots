package robots.gui.abstractmenu;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class GenerateLocaleButton {
    private final Locale[] locales = {
            new Locale("ru", "RU"),
            new Locale("en", "US"),
    };

    public JMenu generateLocaleButton(ItemListener onChange) {
        JMenu exitMenu = new JMenu(ResourceBundle.getBundle("locale").getString("title.changeLanguage"));
        JRadioButtonMenuItem russianLocale = new JRadioButtonMenuItem("Русский");
        JRadioButtonMenuItem englishLocale = new JRadioButtonMenuItem("English");
        ButtonGroup bg = new ButtonGroup();
        bg.add(russianLocale);
        bg.add(englishLocale);
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
        exitMenu.add(russianLocale);
        exitMenu.add(englishLocale);
        return exitMenu;
    }
}
