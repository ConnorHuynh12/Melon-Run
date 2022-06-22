import java.awt.*;
import javax.swing.ImageIcon;

class Platform {
    int y;
    static ImageIcon img = new ImageIcon("Earth_Platform.png");
    static Image platform = img.getImage();
    int counter = 0;

    Boolean onPlatform = false;

    public Platform(int y) {
        this.y = y;
    }

    public void setY (int y){
        this.y = y;
    }

    public void behavior(Player p, int x) {
        //62 is around half the height of the character greater than platform, x-coordinate of character is less than right of platform, x-coordinate is greater than left of platform  
        if(p.y+62 < y && p.left < x+270 && p.left+125 > x) { 
            this.onPlatform = true;
        } else if(p.left > x+270 || p.left+125 < x) { //left x of character greater than right of platform or right character left of the left edge of platform
            if (this.onPlatform && !p.jumpStart) { //If your one platform and not jumping
                p.falling = true;
            }
            this.onPlatform = false;
        }

        if (this.onPlatform) {
            if (p.y - p.jumpStrength > y) { //Land on the platform and allows for jump
                p.jumpStrength = 30; //Allows you to jump
                p.y = y-62;
                p.jumpStart = false; //Standing on platform
            }
        } else if (p.falling) {
            p.jumpStart = true; //Starts gravity to fall
            if (p.jumpStrength > 0) {
                p.jumpStrength = 0; //Makes you only fall
            }
        }
    }
}