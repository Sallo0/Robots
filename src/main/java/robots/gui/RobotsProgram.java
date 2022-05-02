package robots.gui;

import robots.storage.FileStorage;

import java.awt.Frame;
import java.io.File;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class RobotsProgram
{
  private static final String file = new String(System.getProperty("user.home") + File.separator + "robotState");
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
//        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    } catch (Exception e) {
      e.printStackTrace();
    }
    SwingUtilities.invokeLater(() -> {
      MainApplicationFrame frame = new MainApplicationFrame(new FileStorage(file));
      frame.pack();
      frame.setVisible(true);
      frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    });
  }
}
