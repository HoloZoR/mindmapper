import javax.swing.*;
import java.awt.*;

public class FenetrePrincipal extends JFrame {

    private ToolBar toolBar;

    public FenetrePrincipal() {
        super();
        build();
        toolBar = new ToolBar();
    }

    public void build() {
        setTitle("MindMapper");
        setSize(500,500);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
