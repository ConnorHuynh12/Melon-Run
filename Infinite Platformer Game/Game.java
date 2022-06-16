import java.awt.*;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

public class Game extends JPanel implements ActionListener{

    Player p;
    public Image img;
    Timer time;

    public Game(){

        p = new Player();
        addKeyListener(new AL());
        setFocusable(true);
        ImageIcon backround = new ImageIcon("gameBackround.png");
        img = backround.getImage();
        time = new Timer(5, this);
        time.start();
    }


    public void actionPerformed(ActionEvent e){
        p.move();
        repaint();
    }

    //Creates the backround and moves the backround and loops it
    public void paint(Graphics g){
        super.paint(g);

            Graphics2D g2d = (Graphics2D) g;
            if ((p.getX() - 405) %  2400 == 0){//Checks that the backround is complete, 405-405%2400 is 0 
                p.nx = 0;
            }
            if ((p.getX() - 1605) % 2400 == 0){
                p.nx2 = 0;
            }
            //x-coordinate 405 marks end of backround
            //1605 is 405 + length of frame(1200). used to start second backround image.
            //2400 is frame x 2

            //testing x value
            //System.out.println(p.getX());
            //negative so backround scrolls left, 1150 is end of backroud so that frame moves slightly ahead
            //This is drawing backround for the first scroll of backround
            g2d.drawImage(img, 1150-p.nx2, 0, null);
            //This is drawing backround for the loop
            if (p.getX() >= 405){
                g2d.drawImage(img, 1150-p.nx, 0, null);
            }
            g2d.drawImage(p.getImage(), p.left, p.y, null);
    }

    //Calls the methods for movement
    private class AL extends KeyAdapter{
        public void keyReleased(KeyEvent e){
            p.keyReleased(e);
        }
        public void keyPressed(KeyEvent e){ 
            p.keyPressed(e);
        }
    }
    
}