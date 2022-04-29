package entity;

import main.GamePanel;
import java.awt.Graphics2D;
import java.util.Random;
import java.awt.image.BufferedImage;

public class Monster extends Entity {

    public Monster(GamePanel gp){
        super(gp);
        direction = "down";
        speed = 2;
        getImage();
        Random random = new Random();
        x = random.nextInt(400,600);
        y = random.nextInt(200,400);
    }

    public  void getImage(){
        down1 = setup("player/greenslime_down_1.png",gp.TILE_SIZE , gp.TILE_SIZE );
        down2 = setup("player/greenslime_down_2.png",gp.TILE_SIZE , gp.TILE_SIZE );
    }

    public void update(){
        spriteCounter++;
        if(spriteCounter > 20){
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        if(spriteNum == 1){
            image = down1;
        }
        if(spriteNum == 2){
            image = down2;
        }
        g2.drawImage(image, (int) x, (int) y, gp.TILE_SIZE , gp.TILE_SIZE , null);
    }
}


