import javax.swing.*;

public class Frame extends JFrame {
    public static void main(String[] args) { 
        JFrame frame = new JFrame("Melon-Run");
        frame.add(new Game()); //Creating game object and adding it to the frame

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,585);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
