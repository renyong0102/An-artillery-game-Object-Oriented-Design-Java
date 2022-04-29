package entity;

import main.GamePanel;
import main.KeyHandler;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Fire extends Entity {
    public static int SPEED_LOWER_BOUND = 1;
    public static int SPEED_UPPER_BOUND = 10;
    public static int ANGLE_LOWER_BOUND = 0;
    public static int ANGLE_UPPER_BOUND = 90;
    KeyHandler keyH;
    double speedX;
    double speedY;
    double tempAngle = 1.0;

    public Fire(GamePanel gp, KeyHandler keyH) {
        super(gp);
        x = 1000.0;
        y = 1000.0;
        speed = 6.0;
        angle = 45.0;
        getImage();
        this.keyH = keyH;
        direction = "up";

    }

public void setAngle(){
    if (this.keyH.angleUpPressed && spriteCounter == 7 && angle < ANGLE_UPPER_BOUND) {
        angle += tempAngle;
        System.out.println("angle is " + angle);
    } else if (this.keyH.angleDownPressed && spriteCounter == 7 && angle > ANGLE_LOWER_BOUND) {
        angle -= tempAngle;
        System.out.println("angle is " + angle);
    }
}

    public void setSpeed(){
        if (this.keyH.powerUpPressed && spriteCounter == 7 && speed < SPEED_UPPER_BOUND) {
            speed ++;
            System.out.println("speed is " + speed);
        } else if (this.keyH.powerDownPressed && spriteCounter == 7 && speed > SPEED_LOWER_BOUND) {
            speed --;
            System.out.println("speed is " + speed);
        }
    }
    public void update(){
        setAngle();
        setSpeed();
        if (this.keyH.spacePressed) {
            // reset
            x = gp.player.x + 20;
            y = gp.player.y + 15;
            speedX = Math.cos(angle / 180.0 * Math.PI) * speed;
            speedY = Math.sin(angle / 180.0 * Math.PI) * speed;
            gp.playSE(2);
        }
        x += speedX;
        y -= speedY;
        speedY -= 0.1;

        spriteCounter++;
        //axe rotate speed
        if(spriteCounter > 8){
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else if(spriteNum == 2){
                spriteNum = 3;
            }
            else if(spriteNum == 3) {
                spriteNum = 4;
            }
            else if(spriteNum == 4){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

    }
    public void getImage(){
        up = setup("weapon/axe_u.png",gp.TILE_SIZE , gp.TILE_SIZE );
        down = setup("weapon/axe_d.png",gp.TILE_SIZE , gp.TILE_SIZE );
        left = setup("weapon/axe_l.png",gp.TILE_SIZE , gp.TILE_SIZE );
        right = setup("weapon/axe_r.png",gp.TILE_SIZE , gp.TILE_SIZE );
    }

    public void draw(Graphics2D g2){
        BufferedImage image = switch (spriteNum) {
            case 1 -> up;
            case 2 -> right;
            case 3 -> down;
            case 4 -> left;
            default -> null;
        };
        g2.drawImage(image, (int)x, (int)y, gp.TILE_SIZE , gp.TILE_SIZE , null);
    }
}
