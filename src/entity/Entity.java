package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Entity {

    GamePanel gp;
    public double x, y;
    public double speed;
    public double angle;
    //store image files
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2,
            up, down, left, right, blood;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    
    public Entity(GamePanel gp){
        this.gp = gp;
    }

    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            image = uTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public abstract void draw(Graphics2D g2);

    public abstract void update();

}
