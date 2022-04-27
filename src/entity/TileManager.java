package entity;

import main.GamePanel;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

public class TileManager {

    GamePanel gp;
    Tile[] tile;

    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[10];
        getTileImage();
    }

    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("images/background.jpg"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("images/angle1.png"));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("images/speed.png"));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("images/score.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        g2.drawImage(tile[0].image, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(tile[1].image, 10, 30, (int)(gp.TILE_SIZE*1.5), gp.TILE_SIZE, null);
        g2.drawImage(tile[2].image, 10, 80, (int)(gp.TILE_SIZE*1.5), gp.TILE_SIZE ,  null);
        g2.drawImage(tile[3].image, 530, 10, gp.TILE_SIZE *3, gp.TILE_SIZE *3, null);
    }
}

