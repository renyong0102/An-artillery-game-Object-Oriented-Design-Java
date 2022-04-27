package entity;

import main.GamePanel;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Blood extends Entity {

    public Blood(GamePanel gp){
        super(gp);
        x = gp.monster.x;
        y = gp.monster.y;
        getImage();
    }

    public void getImage(){
        blood = setup("player/blood.png",gp.TILE_SIZE , gp.TILE_SIZE );
    }
    public void update() {
        spriteCounter++;
        if (spriteCounter > 120) {
            spriteNum = 10;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        image = blood;
        g2.drawImage(image, (int) x, (int) y, gp.TILE_SIZE , gp.TILE_SIZE , null);
    }
}


