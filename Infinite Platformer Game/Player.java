import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

//x:x-coordinate, dx:difference in x-coordinate, y:y-coordinate, nx2:new x-coordinate 2, nx:new x-coordinate, left:x-coordinate for moving left, dy:Difference in y-value
public class Player {
    int x, dx, y, nx2, nx, left, dy, elevation, jumpStrength;
    Image Character;
    boolean jumpStart = false;
    boolean falling = true;

    //Adding the character models
    ImageIcon rightCharacter = new ImageIcon("Character.png");
    ImageIcon leftCharacter = new ImageIcon("CharacterLeft.png");
    ImageIcon upCharacter = new ImageIcon("CharacterJump.png");

    public Player(){
        Character = rightCharacter.getImage();
        left = 150;

        x = 575;
        nx2 = 1150; //x-value a little smaller than frame length 
        nx = 0;//variable used to create infinite looping backround
        y = 450;
        elevation = 450;
        jumpStrength = 20;
    }
    //method for calculating the movement of character
    public void move(){
        if (dx != -10){//If you are moving forward/right
            if (left + dx <= 150){
                left = left + dx;//Left controls movement of the character
            }
            else{//If character is at x:150 then backround moves right. These variables control backround
                x = x + dx;
                nx2 = nx2 + dx;
                nx = nx + dx;
            }
        }
        else{//If you are moving left/backwards
            if (left + dx > 0){//Keeps character on screen
                left = left + dx;//Going left
            }
        }
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Image getImage(){
        return Character;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode(); 

        if (key == KeyEvent.VK_LEFT){
            dx = -10;
            Character = leftCharacter.getImage();
        }

        if (key == KeyEvent.VK_RIGHT){
            dx = 10;
            Character = rightCharacter.getImage();
        }

        if (key == KeyEvent.VK_UP){
            dy = -5; //Going up the screen is negative
            Character = upCharacter.getImage();
            jumpStart = true;
            falling = false;
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode(); 

        if (key == KeyEvent.VK_LEFT){
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT){
            dx = 0;
        }

        if (key == KeyEvent.VK_UP){
            dy = 0;
            Character = rightCharacter.getImage();
        }
    }

    //Past jumping method attempts
    /* 
    public void jump(int dy){
        boolean jumpPeak = false;
        boolean jumpDone = false;
        System.out.println("This is elevation:" + elevation);

        if (jumpPeak == false){//Subtract is going up screen
                //elevation--;
                elevation = elevation - 2;
        }
        if (elevation == 100){//100 random max value
                jumpPeak = true;
        }
        if (jumpPeak == true && elevation <= 450){ //if you are at peak of jump and above floor (450)
            //elevation++;//coming back down to ground
            elevation = elevation + 100;
            if (elevation == 450){
                jumpDone = true;
                    
                //Testing when jump done
                System.out.println("JUMP DONE");
            }
        }
    }
*/


}