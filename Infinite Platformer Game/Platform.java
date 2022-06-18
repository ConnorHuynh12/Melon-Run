import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

class Platform {
    int x;
    int y;
    Image platform;
    ImageIcon img = new ImageIcon("Platform.png");
    int counter = 0;

    public Platform(int y) {
        this.y = y;
        platform = img.getImage();
    }

    public void behavior(Player p, int x) {
        if(p.y+62 < y && p.left < x+270 && p.left+125 > x) {
            p.onPlatform = true;
        } else if(p.left > x+270 || p.left+125 < x) {
            if (p.onPlatform) {
                p.falling = true;
            }
            p.onPlatform = false;
        }

        if (p.onPlatform) {
            if (p.y - p.jumpStrength >= y) {
                p.jumpStrength = 30;
                p.y = y-62;
                p.jumpStart = false;
            }
        } else if (p.falling) {
            p.jumpStart = false;
            p.y += counter / 2.0;
            counter += 1;
            if (p.y + counter / 2.0 >= 450) {
                counter = 0; 
                p.falling = false;
            }
        }
    }
}