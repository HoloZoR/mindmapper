package Vue;

import Utils.Observe;

import javax.swing.*;
import java.awt.*;

public class FenetrePrincipal extends Observe {

    private ToolBar toolBar;

    public FenetrePrincipal() {
        super();
        build();
        toolBar = new ToolBar();
        this.add(toolBar.getToolBar(), BorderLayout.NORTH);
    }

    public void build() {
        setTitle("MindMapper");
        setSize(500,500);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public ToolBar getToolBar() {
        return toolBar;
    }

    public void setToolBar(ToolBar toolBar) {
        this.toolBar = toolBar;
    }
}
