package entity;

import main.GamePanel;
import main.KeyHandler;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);
        this.keyH = keyH;
        direction = "down";
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 100;
        y = 400;
        speed = 4;
    }

    public  void getPlayerImage(){
        up1 = setup("player/boy_up_1.png",gp.TILE_SIZE , gp.TILE_SIZE );
        up2 = setup("player/boy_up_2.png",gp.TILE_SIZE , gp.TILE_SIZE );
        down1 = setup("player/boy_down_1.png",gp.TILE_SIZE , gp.TILE_SIZE );
        down2 = setup("player/boy_down_2.png",gp.TILE_SIZE , gp.TILE_SIZE );
        left1 = setup("player/boy_left_1.png",gp.TILE_SIZE , gp.TILE_SIZE );
        left2 = setup("player/boy_left_2.png",gp.TILE_SIZE , gp.TILE_SIZE );
        right1 = setup("player/boy_right_1.png",gp.TILE_SIZE , gp.TILE_SIZE );
        right2= setup("player/boy_right_2.png",gp.TILE_SIZE , gp.TILE_SIZE );
    }

    //60 times per second
    public void update(){
        if(keyH.upPressed){
            direction = "up";
            y -= speed;
        }
        else if(keyH.downPressed){
            direction = "down";
            y += speed;
        }
        else if(keyH.leftPressed){
            direction = "left";
            x -= speed;
        }
        else if(keyH.rightPressed) {
            direction = "right";
            x += speed;
        }
        //images changes in every 10 frames
        spriteCounter++;
        if(spriteCounter > 15){
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2){
        //use player images to draw
        BufferedImage image = null;
        switch(direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
            if(spriteNum == 1){
                image = left1;
            }
            if(spriteNum == 2){
                image = left2;
            }
                break;
            case "right":
            if(spriteNum == 1){
                image = right1;
            }
            if(spriteNum == 2){
                image = right2;
            }
                break;
        }
        g2.drawImage(image, (int)x, (int)y, gp.TILE_SIZE , gp.TILE_SIZE , null);
    }
}
