package main;

import javax.swing.JFrame;

public class Main{
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("5004 final project");
        GamePanel gamePanel = new GamePanel();
        gamePanel.setGame();
        window.add(gamePanel);
        window.pack(); // we can see the game window
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.startGameThread();

    }
}