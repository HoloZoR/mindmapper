import javax.swing.*;
import java.util.ArrayList;

public class ToolBar {
    private ArrayList<JButton> buttons;

    public ToolBar() {
        this.buttons = new ArrayList<>();
    }

    public ArrayList<JButton> getButtons() {
        return buttons;
    }

    public void setButtons(ArrayList<JButton> buttons) {
        this.buttons = buttons;
    }
}
