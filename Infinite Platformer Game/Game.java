import java.awt.*;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

public class Game extends JPanel implements ActionListener{

    Player p;
    Platform surface, surface2, surface3, surface4, surfaceStart;
    public Image img;
    Timer time;
    int counter = 0;

    public Game(){

        p = new Player();
        surfaceStart = new Platform(210);
        surface = new Platform(350);
        surface2 = new Platform(150);
        surface3 = new Platform(400);
        surface4 = new Platform(300);

        addKeyListener(new AL());
        setFocusable(true);
        ImageIcon backround = new ImageIcon("FarmGameBackround.png");
        img = backround.getImage();
        time = new Timer(10, this);
        time.start();
    }

    public void actionPerformed(ActionEvent e){

        if (!p.gameOver){
          p.move();
        }

        surfaceStart.behavior(p, 1150 - p.nx2 +100);
        surface.behavior(p,1150 - p.nx);
        surface2.behavior(p,1150 - p.nx + 500);
        surface3.behavior(p,1150 - p.nx + 900);
        surface4.behavior(p,1150 - p.nx2 + 550);

        if ((p.getX() - 405) % 2400 == 0){ //Checks that the backround is complete, 405-405%2400 is 0 
            p.nx = 0;
        }
        if ((p.getX() - 1605) % 2400 == 0){
            p.nx2 = 0;
        }

        //Jumping component
        if (p.jumpStart){
            System.out.println("This is y:" + p.y);
            p.y -= p.jumpStrength/2.0;
            p.jumpStrength -= 1.0; //1.0 is the gravity that y decreases
            //
            if (p.y - p.jumpStrength >= 450 || p.y - p.jumpStrength >= surface.y && surface.onPlatform || p.y - p.jumpStrength >= surface2.y && surface2.onPlatform || p.y - p.jumpStrength >= surface3.y && surface3.onPlatform || p.y - p.jumpStrength >= surface4.y && surface4.onPlatform /*|| p.y - p.jumpStrength >= surfaceStart.y*/){
                p.jumpStrength = 30;
                p.y = 450;
                p.jumpStart = false;
                p.falling = false;
            }
            System.out.println("This is jump strength "+ p.jumpStrength);
        }    

        if (p.y < 450){
            p.gameOver = false;
        }
        else if (p.y >= 450){
            p.gameOver = true;
        }


        repaint();
    }

    //Creates the backround and moves the backround and loops it
    public void paint(Graphics g){
        super.paint(g);
            Graphics2D g2d = (Graphics2D) g;

            //x-coordinate 405 marks end of backround
            //1605 is 405 + length of frame(1200). used to start second backround image.
            //2400 is frame x 2
            //negative so backround scrolls left, 1150 is end of backroud so that frame moves slightly ahead
            //This is drawing backround for the first scroll of backround
            g2d.drawImage(img, 1150-p.nx2, 0, null);
            //This is drawing backround for the loop
            if (p.getX() >= 405){
                g2d.drawImage(img, 1150-p.nx, 0, null); //Draws backround constanly at changing x values
                
                //Platform drawing
                g2d.drawImage(Platform.platform, 1150-p.nx2+100, surfaceStart.y,null);
                g2d.drawImage(Platform.platform, 1150-p.nx, surface.y, null); //Draws the first platform
                g2d.drawImage(Platform.platform, 1150-p.nx+500, surface2.y, null); //Draws the second platform
                g2d.drawImage(Platform.platform, 1150-p.nx+900, surface3.y, null);
                g2d.drawImage(Platform.platform, 1150-p.nx2+550, surface4.y, null);
            }
            g2d.drawImage(p.getImage(), p.left, p.y, null); //This draws the character
            
            //Displays the score
            g2d.setColor(new Color(0,0,0));
            g2d.setFont(new Font("Roboto", Font.PLAIN, 20));
            g2d.drawString("SCORE: "+ p.score, 1000, 50);

            //Displays GAME OVER
            if (p.gameOver){
                g2d.setColor(new Color(255,0,0));
                g2d.setFont(new Font("Roboto", Font.BOLD, 100));
                g2d.drawString("GAME OVER", 300, 250);
                g2d.drawString("Your score: " + p.score, 260, 400);
            }
        }
    

    private class AL extends KeyAdapter{
        public void keyReleased(KeyEvent e){
            p.keyReleased(e);
        }
        public void keyPressed(KeyEvent e){ 
            p.keyPressed(e);
        }
    }
    
}