import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

enum ActionList {
    ALERT, QUIT, INVALID
};

public class Swing{
    public static void main(String[] args) {
        CenterFrame frame = new CenterFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        ButtonPanel panel = new ButtonPanel();
        frame.add(panel);
        
        panel.addButton("Add","ALERT","Add single element.");
        panel.addButton("Remove First","ALERT","Remove first element.");
        panel.addButton("Remove Last","ALERT","Remove last element.");
        panel.addButton("Remove All","ALERT","Remove all elements.");

        panel.addButton("Quit","QUIT","Quit program.");
    }
}

class CenterFrame extends JFrame{
    public CenterFrame(){
        setTitle("lab10_201724539");

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        final int screenHeight = screenSize.height; // 864
        final int screenWidth = screenSize.width; // 1536

        final int frameHeight = 100;
        final int frameWidth = 600;

        setSize(frameWidth,frameHeight);
        setLocation(screenWidth/2 - frameWidth/2,screenHeight/2 - frameHeight/2);
    }
}

// class AlertButtonMessage implements ActionListener {
//     public void actionPerformed(ActionEvent event){
//         System.out.println(event);

//         String cmd = event.getActionCommand();

//         JOptionPane.showMessageDialog(null,cmd);
//     }
// }

// class QuitProgram implements ActionListener{
//     public void actionPerformed(ActionEvent event){
//         System.out.println(event);
//         System.exit(0);
        
//     }
// }

class ButtonPanel extends JPanel{
    // public void addAlertButton(final String value){
    //     JButton button = new JButton(value);
    //     button.addActionListener(new AlertButtonMessage());
    //     add(button);
    // }

    // public void addQuitButton(final String value){
    //     JButton button = new JButton(value);
    //     button.addActionListener(new QuitProgram());
    //     add(button);
    // }
    
    public void addButton(final String name, final String action, final String toolTip){
        final Action buttonAction = new ButtonAction(name,action,toolTip);
        add(new JButton(buttonAction));

        // ActionMap amap = getActionMap();
        // amap.put()
    }
}

class ButtonAction extends AbstractAction{
    String action;
    public ButtonAction(final String name,final String action,final String toolTip){
        putValue(Action.NAME,name);
        putValue(Action.SHORT_DESCRIPTION,toolTip);
        putValue("Action",action);
        this.action = action;
    }

    @Override
    public void actionPerformed(ActionEvent event){
        System.out.println(event);

        ActionList actionOnButton;
        try {
            actionOnButton = ActionList.valueOf(action);
        } catch (final Exception e) {
            actionOnButton = ActionList.INVALID;
        }

        switch (actionOnButton) {
            case ALERT: {
                final String cmd = event.getActionCommand();//
                JOptionPane.showMessageDialog(null,cmd);
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