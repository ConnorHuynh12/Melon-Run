import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

//x:x-coordinate, dx:difference in x-coordinate, y:y-coordinate, nx2:new x-coordinate 2, nx:new x-coordinate, left:x-coordinate for moving left, dy:Difference in y-value
public class Player {
    int x, dx, y, nx2, nx, left, dy, jumpStrength, score;
    Image Character;
    boolean jumpStart = false;
    boolean falling = false;
    boolean onPlatform;
    boolean gameOver = false;

    //Adding the character models
    ImageIcon rightCharacter = new ImageIcon("Melon_Character.png");
    ImageIcon leftCharacter = new ImageIcon("Melon_Left.png");
    ImageIcon upCharacter = new ImageIcon("Melon_Jump.png");

    public Player(){
        Character = rightCharacter.getImage();
        left = 150;
        score = 0;
        x = 575;
        nx2 = 1150; //x-value a little smaller than frame length 
        nx = 0;//variable used to create infinite looping backround
        // y= 450 is the ground
        y = 190; //change this to match the y value of starting platform
        jumpStrength = 30;
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
                score++;
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

        if (key == KeyEvent.VK_LEFT && !gameOver){ //if you are pressing left key and game is not over
            dx = -10;
            Character = leftCharacter.getImage();
        }

        if (key == KeyEvent.VK_RIGHT && !gameOver){
            dx = 10;
            Character = rightCharacter.getImage();
        }

        if (key == KeyEvent.VK_UP && !gameOver){
            dy = -5; //Going up the screen is negative
            Character = upCharacter.getImage();
            jumpStart = true;
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
}