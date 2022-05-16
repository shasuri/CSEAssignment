
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.net.*;
import java.util.*;

enum ActionList {
    CONNECT, DISCONNECT, SEND, CLEAR, QUIT, INVALID
};

public class EchoClient {
    public static void main(String[] args) {
        CenterFrame frame = new CenterFrame();
        // try {
        // Socket socket = new Socket("localhost", 8189);

        // PrintWriter sender = new PrintWriter(socket.getOutputStream(), true);

        // try (Scanner in = new Scanner(socket.getInputStream())) {
        // while (in.hasNextLine()) {
        // String line = in.nextLine();
        // System.out.println(line);
        // }
        // } finally {
        // socket.close();
        // }
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
    }
}

@SuppressWarnings("serial")
class CenterFrame extends JFrame {
    JTextArea clientLog = new JTextArea(10, 50);

    ButtonPanel panel = new ButtonPanel(this);

    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();

    final int screenHeight = screenSize.height;
    final int screenWidth = screenSize.width;

    final int frameHeight = 500;
    final int frameWidth = 700;

    public CenterFrame() {
        setSize(frameWidth, frameHeight);
        setLocation(screenWidth / 2 - frameWidth / 2, screenHeight / 2 - frameHeight / 2);

        setTitle("lab13_201724539_client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        clientLog.setEditable(false);
        // add(new JScrollPane(clientLog));

        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        c.add(new JScrollPane(clientLog));

        add(panel);

        setVisible(true);
    }

    public void addMessage(final String msg) {
        clientLog.append(msg);
        clientLog.append("\n");
        clientLog.setCaretPosition(clientLog.getDocument().getLength());

    }
}

class ButtonPanel extends JPanel {
    public JTextField msgField = new JTextField(20);
    CenterFrame outer;

    public ButtonPanel(CenterFrame outer) {
        this.outer = outer;
        addButton("Connect", ActionList.CONNECT, "Connect to the server.");
        addButton("Disconnect", ActionList.DISCONNECT, "Disconnect from the server.");

        add(msgField);

        addButton("Send", ActionList.SEND, "Send message to the server.");
        addButton("Clear", ActionList.CLEAR, "Clear the log.");
        addButton("Quit", ActionList.QUIT, "Quit program.");
    }

    public void addButton(final String name, final ActionList action, final String toolTip) {
        final Action buttonAction = new ButtonAction(this, name, action, toolTip);
        add(new JButton(buttonAction));
    }

    class ButtonAction extends AbstractAction {
        ActionList action;
        String name;
        ButtonPanel outer;

        public ButtonAction(ButtonPanel outer, final String name, final ActionList action, final String toolTip) {
            putValue(Action.NAME, name);
            putValue(Action.SHORT_DESCRIPTION, toolTip);
            putValue("Action", action);

            this.action = action;
            this.name = name;
            this.outer = outer;
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

            Socket socket = new Socket();
            InetSocketAddress connectServer = new InetSocketAddress("localhost", 8189);

            switch (actionOnButton) {
                case CONNECT: {
                    outer.outer.addMessage("Try to connect " + connectServer.getAddress());
                    try {
                        socket.connect(connectServer);
                        outer.outer.addMessage("Connect Established");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }

                case DISCONNECT: {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }

                case SEND: {
                    outer.outer.addMessage(outer.msgField.getText());

                    try {
                        PrintWriter sender = new PrintWriter(socket.getOutputStream(), true);
                        sender.println(outer.msgField.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
                }

                case CLEAR: {
                    outer.outer.clientLog.setText(null);
                    break;
                }

                case QUIT: {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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