import javax.swing.*;
import java.awt.*;

public class FenetrePrincipal extends JFrame {
    public FenetrePrincipal() {
        super();
        build();
    }

    public void build() {
        setTitle("MindMapper");
        setSize(500,500);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
