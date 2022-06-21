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
        //62 is height of the player greater than platform, x-coordinate of character is less than right of platform, x-coordinate is greater than left of platform  
        if(p.y+62 < y && p.left < x+270 && p.left+125 > x) { 
            this.onPlatform = true;
        } else if(p.left > x+270 || p.left+125 < x) { //left x of character greater than right of platform or right character less than platform
            if (this.onPlatform && !p.jumpStart) { //
                p.falling = true;
            }
            this.onPlatform = false;
        }

        if (this.onPlatform) {
            if (p.y - p.jumpStrength > y) {
                p.jumpStrength = 30;
                p.y = y-62;
                p.jumpStart = false;
            }
        } else if (p.falling) {
            p.jumpStart = true;
            if (p.jumpStrength > 0) {
                p.jumpStrength = 0;
            }
        }
    }
}