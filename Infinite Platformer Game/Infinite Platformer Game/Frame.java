import javax.swing.*;

public class Frame extends JFrame {
    public static void main(String[] args) { 
        JFrame frame = new JFrame("2D Game");
        frame.add(new Game());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,585);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
