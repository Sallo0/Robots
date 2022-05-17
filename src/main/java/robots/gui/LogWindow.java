package robots.gui;

import robots.gui.abstractmenu.DialogGenerator;
import robots.gui.storemanager.WindowState;
import robots.log.LogChangeListener;
import robots.log.LogEntry;
import robots.log.LogWindowSource;
import robots.log.Logger;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.*;
import java.util.ResourceBundle;

public class LogWindow extends JInternalFrame implements LogChangeListener {
    private final LogWindowSource m_logSource;
    private final TextArea m_logContent;
    private final DialogGenerator exitDialog = new DialogGenerator();

    public LogWindow(LogWindowSource logSource) {
        super(ResourceBundle.getBundle("locale").getString("text.protocolOfWork"), true, true, true, true);
        m_logSource = logSource;
        m_logSource.registerListener(this);
        m_logContent = new TextArea("");
        //m_logContent.setSize(200, 500);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(m_logContent, BorderLayout.CENTER);
        getContentPane().add(panel);
        updateLogContent();

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                exitDialog.windowExitDialog(e);
            }
        });
    }

    private void updateLogContent() {
        StringBuilder content = new StringBuilder();
        for (LogEntry entry : m_logSource.all()) {
            content.append(entry.getMessage()).append("\n");
        }
        m_logContent.setText(content.toString());
        m_logContent.invalidate();
    }

    public void setParams(WindowState windowState) {
        this.setSize(windowState.getWidth(), windowState.getHeight());
        this.setLocation(windowState.getLocationX(), windowState.getLocationY());
        try {
            this.setIcon(windowState.isIcon());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Logger.debug(ResourceBundle.getBundle("locale").getString("text.protocolWorks"));
    }

    public void changeLocale() {
        this.setTitle(ResourceBundle.getBundle("locale").getString("text.protocolOfWork"));
    }

    @Override
    public void onLogChanged() {
        EventQueue.invokeLater(this::updateLogContent);
    }

    @Override
    public void unregister() {
        m_logSource.unregisterListener(this);
    }

    @Override
    public void dispose() {
        unregister();
        super.dispose();
    }
}

