package robots.logic;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Cordinate extends JPanel {
    private String count = "start";
    JTextArea textArea = new JTextArea(count);


    public Cordinate() {
        setBackground(Color.white);
        add(textArea);
        Timer timer = initTime();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                printSome();

            }
        }, 0, 100);
    }

    private static java.util.Timer initTime() {
        java.util.Timer timer = new Timer("events", true);
        return timer;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        JTextArea textArea = this.textArea;
    }

    private void printSome() {
        this.count = String.valueOf((Math.random() * (10 - 1)) + 1);
        System.out.println(count);
        textArea = new JTextArea(count);

    }
}