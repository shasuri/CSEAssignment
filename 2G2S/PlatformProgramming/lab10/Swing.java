import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

enum ActionList {
    ALERT, QUIT, INVALID
};

public class Swing {
    public static void main(String[] args) {
        CenterFrame frame = new CenterFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        ButtonPanel panel = new ButtonPanel();
        frame.add(panel);

        panel.addButton("Add", ActionList.ALERT, "Add single element.");
        panel.addButton("Remove First", ActionList.ALERT, "Remove first element.");
        panel.addButton("Remove Last", ActionList.ALERT, "Remove last element.");
        panel.addButton("Remove All", ActionList.ALERT, "Remove all elements.");

        panel.addButton("Quit", ActionList.QUIT, "Quit program.");
    }
}

class CenterFrame extends JFrame {
    public CenterFrame() {
        setTitle("lab10_201724539");

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        final int screenHeight = screenSize.height; // 864
        final int screenWidth = screenSize.width; // 1536

        final int frameHeight = 100;
        final int frameWidth = 600;

        setSize(frameWidth, frameHeight);
        setLocation(screenWidth / 2 - frameWidth / 2, screenHeight / 2 - frameHeight / 2);
    }
}

class ButtonPanel extends JPanel {

    public void addButton(final String name, final ActionList action, final String toolTip) {
        final Action buttonAction = new ButtonAction(name, action, toolTip);
        add(new JButton(buttonAction));
    }

    class ButtonAction extends AbstractAction {
        ActionList action;
        String name;

        public ButtonAction(final String name, final ActionList action, final String toolTip) {
            putValue(Action.NAME, name);
            putValue(Action.SHORT_DESCRIPTION, toolTip);
            putValue("Action", action);

            this.action = action;
            this.name = name;
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println(event);

            ActionList actionOnButton;
            try {
                actionOnButton = action;
            } catch (final Exception e) {
                actionOnButton = ActionList.INVALID;
            }

            switch (actionOnButton) {
                case ALERT: {
                    final String cmd = name;// event.getActionCommand();
                    JOptionPane.showMessageDialog(null, cmd);
                    break;
                }

                case QUIT: {
                    System.exit(0);
                    break;
                }

                case INVALID:
                default:
                    System.out.println("Unknown Action!");
                    System.exit(0);
                    break;
            }
        }
    }
}
