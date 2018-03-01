import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        JFrame frame = new JFrame();
        Game game = new Game();
        frame.setBounds(10,10, 710, 600);
        frame.setTitle("Tetris");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
    }
}
