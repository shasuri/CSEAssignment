
import javax.swing.*;
import java.awt.*;
// import java.awt.event.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class EchoServer {
    public static void main(String[] args) {
        CenterFrame frame = new CenterFrame();
        try {
            ServerSocket s = new ServerSocket(8189);

            Socket incoming = s.accept();

            InetAddress clientAddress = incoming.getInetAddress();
            frame.addMessage(clientAddress.getHostAddress());
            while (true) {
                try {
                    InputStream inStream = incoming.getInputStream();
                    OutputStream outStream = incoming.getOutputStream();

                    Scanner in = new Scanner(inStream);
                    PrintWriter out = new PrintWriter(outStream, true);

                    while (in.hasNextLine()) {
                        String line = in.nextLine();
                        frame.addMessage("Read: " + line);

                        String echoLine = "Hi! " + line;

                        out.println(echoLine); // echo back
                        frame.addMessage("Write: " + echoLine); // show message
                    }

                    in.close();
                    frame.addMessage("Disconnected");

                } finally {

                    incoming.close();
                    s.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

@SuppressWarnings("serial")
class CenterFrame extends JFrame {
    private JTextArea serverLog = new JTextArea();

    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();

    final int screenHeight = screenSize.height;
    final int screenWidth = screenSize.width;

    final int frameHeight = 300;
    final int frameWidth = 600;

    public CenterFrame() {
        setSize(frameWidth, frameHeight);
        setLocation(screenWidth / 2 - frameWidth / 2, screenHeight / 2 - frameHeight / 2);

        setTitle("lab13_201724539_server");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        serverLog.setEditable(false);
        add(new JScrollPane(serverLog));
        setVisible(true);
    }

    public void addMessage(final String msg) {
        serverLog.append(msg);
        serverLog.append("\n");
    }
}