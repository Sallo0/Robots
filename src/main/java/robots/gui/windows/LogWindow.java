package robots.gui.windows;

import robots.gui.abstractmenu.DialogGenerator;
import robots.log.LogChangeListener;
import robots.log.LogEntry;
import robots.log.LogWindowSource;
import robots.log.Logger;
import robots.storage.WindowState;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.*;
import java.util.ResourceBundle;

public class LogWindow extends AbstractWindow implements LogChangeListener {
    private final LogWindowSource m_logSource;
    private final TextArea m_logContent;

    public LogWindow(LogWindowSource logSource) {
        super(ResourceBundle.getBundle("locale").getString("text.protocolOfWork"), true, true, true, true);
        m_logSource = logSource;
        m_logSource.registerListener(this);
        m_logContent = new TextArea("");
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(m_logContent, BorderLayout.CENTER);
        getContentPane().add(panel);
        updateLogContent();

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                DialogGenerator.windowExitDialog(e);
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

    @Override
    public void setParams(WindowState windowState) {
        super.setParams(windowState);
        Logger.debug(ResourceBundle.getBundle("locale").getString("text.protocolWorks"));
    }

    @Override
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

