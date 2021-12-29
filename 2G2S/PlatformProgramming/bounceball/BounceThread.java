import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;

public class BounceThread{
    public static void main(String[] args) {
        JFrame frame = new BounceFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class BallRunnable implements Runnable {
    public BallRunnable(Ball aBall, JPanel ballPanel){
        ball = aBall;
        this.ballPanel = ballPanel;
    }

    public void run(){
        try{
            for(int i = 1; i <= STEPS; i++){
                ball.move(ballPanel.getBounds());
                ballPanel.paint(ballPanel.getGraphics());
                Thread.sleep(DELAY);
            }
        }
        catch(InterruptedException e){}
    }

    private Ball ball;
    private JPanel ballPanel;
    public static final int STEPS = 1000;
    public static final int DELAY = 3;
}
    
class Ball{
    public void move(Rectangle2D bounds){
        x += dx;
        y += dy;

        if(x < bounds.getMinX()){
            x = bounds.getMinX();
            dx = -dx;
        }

        if(x + XSIZE >= bounds.getMaxX()){
            x = bounds.getMaxX() - XSIZE;
            dx = -dx;
        }
        
        if(y < bounds.getMinY()){
            y = bounds.getMinY();
            dy = -dy;
        }

        if(y + YSIZE >= bounds.getMaxY()){
            y = bounds.getMaxY() - YSIZE;
            dy = -dy;
        }
    }
    
    public Ellipse2D getShape()
    {
        return new Ellipse2D.Double(x,y,XSIZE,YSIZE);
    }

    private static final int XSIZE = 15;
    private static final int YSIZE = 15;
    private double x = 0;
    private double y = 0;
    private double dx = 1;
    private double dy = 1;
}

class BallPanel extends JPanel{

    public void add(Ball b){
        balls.add(b);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        for(Ball b : balls){
            g2.fill(b.getShape());
        }
    }
    
    private List<Ball> balls = new ArrayList<>();
}

class BounceFrame extends JFrame{
    public BounceFrame(){
        setTitle("BounceThread");
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);

        ballPanel = new BallPanel();
        add(ballPanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel,"Start",new ActionListener(){
            public void actionPerformed(ActionEvent event){addBall();}
        });

        addButton(buttonPanel,"Close",new ActionListener(){
            public void actionPerformed(ActionEvent event){System.exit(0);}
        });

        add(buttonPanel,BorderLayout.SOUTH);

        JMenu speedMenu = new JMenu("Speed");

        ButtonGroup group = new ButtonGroup();

        JCheckBoxMenuItem fasterItem = new JCheckBoxMenuItem("Faster");
        fasterItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                boolean fastSelected = fasterItem.isSelected();

                if(fastSelected){
                    fasterItem.setSelected(false);
                    System.out.println("faster denied!");
                }
                else{
                    System.out.println("faster selected!");
                }
            }
        });
        JCheckBoxMenuItem slowerItem = new JCheckBoxMenuItem("Slower");
        slowerItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                System.out.println("slower selected!");
            }
        });

        group.add(fasterItem);
        group.add(slowerItem);

        speedMenu.add(fasterItem);
        speedMenu.add(slowerItem);

        // speedMenu.add(new TestAction("Faster"));
        // JMenuItem slowerItem = speedMenu.add(new TestAction("Slower"));
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(speedMenu);
    }

    private void addButton(Container container, String title, ActionListener listener){
        JButton button = new JButton(title);
        container.add(button);
        button.addActionListener(listener);
    }

    public void addBall(){
        Ball b = new Ball();
        ballPanel.add(b);
        Runnable r = new BallRunnable(b,ballPanel);
        Thread t = new Thread(r);
        t.start();
    }

    private BallPanel ballPanel;
    public static final int DEFAULT_WIDTH = 450;
    public static final int DEFAULT_HEIGHT = 350;
}