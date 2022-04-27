package main;

import entity.*;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;

    //screen settings
    public final int ORIGINAL_TILE_SIZE = 16;
    public final int SCALE = 3;
    // 48*48 size
    public final int TILE_SIZE  = ORIGINAL_TILE_SIZE * SCALE;
    public int maxScreenCol = 16;
    public int maxScreenRow = 12;
    public int screenWidth = TILE_SIZE  * maxScreenCol; // 768 pixels
    public int screenHeight = TILE_SIZE  * maxScreenRow; // 576 pixels
    public int count;
    //FPS
    int FPS = 60;
    public KeyHandler keyH = new KeyHandler(this);
    public UI ui = new UI(this);

    public Player player = new Player(this, keyH);
    public Fire fire = new Fire(this,keyH);
    //repeat some processes again, s.t.join one screen every 60sec
//    public  AssetSetter aSetter = new AssetSetter(this);

    public Entity monster = new Monster(this);
    public Blood blood = new Blood(this);
    TileManager background = new TileManager(this);
    Sound sound = new Sound();
    Thread gameThread;

    //constructor
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        //if set to true, all the drawing from this component will be done in an offscreen painting uffer
        this.setDoubleBuffered(true);
        //add keyHandler
        this.addKeyListener(keyH);
        //the GamePanel can be focused to receive key input
        this.setFocusable(true);
    }

    public void setGame(){
        gameState = titleState;
        //stopMusic();
    }

    public void startGameThread(){
        //passing GamePanel to this thread
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        //we need to know the diff time between update() and repaint()
        double drawInterval = 1000000000/FPS; //nanoSecond = 1 sec, 0.016666sec
        double nextDrawTime = System.nanoTime() + drawInterval;
        //game loop: core of the game
        while(gameThread != null){
            //1.update information s.t.player position
            update();
            //2.draw the screen with the updateed info
            repaint();//paintComponent(Graphics g) method
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if(remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){
        if(gameState == playState){
            player.update();
            monster.update();
            fire.update();
            blood.update();
        }
        if(gameState == pauseState) {
           //nothing
        }

    }

    public void drawLetter(Graphics2D g, String str, Color color, int size, int x, int y, String fontName) {
        g.setColor(color);
        g.setFont(new Font(fontName, Font.BOLD, size));
        g.drawString(str, x, y);
    }

    //draw things in JPanel
    public void paintComponent(Graphics g){
        //Graphic is pen brush
        // have to have this if we use paintComponent in JPanel
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //title screen
        if(gameState == titleState){
            ui.draw(g2);
        }
        //other screen
        else {
            if (gameState == pauseState) {
                ui.draw(g2);
            }
            if (gameState == playState) {
                background.draw(g2);
                fire.draw(g2);
                player.draw(g2);
                monster.draw(g2);

                drawLetter(g2, String.valueOf(fire.angle), Color.BLACK, 30, 100,60, "Algerian");
                drawLetter(g2, String.valueOf(fire.speed), Color.BLACK, 30, 100,120, "Algerian");
                drawLetter(g2, String.valueOf(count), Color.BLACK, 50, 700,100, "Algerian");
                drawLetter(g2, "Instructions:", Color.RED, 12, 200,20, "TimesRoman");
                drawLetter(g2, "[W] UP [S] DOWN [A] LEFT [D] RIGHT [SPACE] ATTACK",
                        Color.RED, 12, 200,33, "TimesRoman");
                drawLetter(g2, "[G] POWER UP [H] POWER DOWN", Color.RED, 12, 200,46, "TimesRoman");
                drawLetter(g2, "[UP] ANGLE UP [DOWN] ANGLE DOWN", Color.RED, 12, 200,59, "TimesRoman");

//                drawLetter(g2, "" + fire.angle, Color.BLACK, 30, 70, 37);
//                drawLetter(g2, "" + fire.speed, Color.BLACK, 30, 70, 80);
//                drawLetter(g2, "" + count, Color.BLACK, 50, 700, 100);

                if (Math.abs(fire.x - monster.x) < 30 && Math.abs(fire.y - monster.y) < 30) {
                    monster = new Blood(this);
                    count++;
                    this.playSE(1);
                    monster.draw(g2);
                    fire = new Fire(this, keyH);
                }
                if (monster.spriteNum == 10) {
                    monster = new Monster(this);
                    monster.draw(g2);
                    fire.draw(g2);
                }
            }
        }
        g2.dispose();
    }

    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic(){
        sound.stop();
    }

    //sound effect
    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }
}
