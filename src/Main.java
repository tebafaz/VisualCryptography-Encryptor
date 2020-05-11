import java.awt.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                        if ("Windows".equals(info.getName())) {
                            javax.swing.UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                JFrame frame = new JFrame("VisCry");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1000, 800);
                frame.setMinimumSize(new Dimension(1000, 700));
                frame.setLayout(new GridBagLayout());
                frame.getContentPane().setBackground(new Color(255, 255, 255));

                new MainFrame(frame);
            }
        });
    }
}
