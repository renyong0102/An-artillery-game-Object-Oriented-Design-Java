package main;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    public Font maruMonica;
    public int commandNum = 0;
    public UI(GamePanel gp){
        this.gp = gp;
        try{
            InputStream is = getClass().getResourceAsStream("font/MaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        }catch (FontFormatException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(maruMonica);
        g2.setColor(Color.WHITE);
        //Title state
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        //Play state
        if(gp.gameState == gp.playState){
            //play state
        }
        if(gp.gameState == gp.pauseState){
            //pause state
            drawPauseScreen();
        }
    }

    public void drawPauseScreen(){
        String text = "PAUSED";
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 96F));
        g2.setColor(Color.WHITE);
        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2;
        g2.drawString(text, x, y);
    }

    public void drawTitleScreen(){
        //title screen color
        g2.setColor(new Color(70, 0, 20));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        //text style
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        //Title name
        String text = "Axe Killer Game";
        int x = getXForCenteredText(text);
        int y = gp.TILE_SIZE  * 3;
        //text shadow
        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 10, y + 10);
        //text main color
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);
        //image in screen
        x = gp.screenWidth/2 - (gp.TILE_SIZE );
        y += gp.TILE_SIZE  * 2;
        g2.drawImage(gp.fire.up, x, y, gp.TILE_SIZE  * 2, gp.TILE_SIZE  * 2, null );
        //menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        text = "START GAME (press enter key)";
        x = getXForCenteredText(text);
        y += gp.TILE_SIZE  * 4;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x - gp.TILE_SIZE  , y);
        }
        text = "Quit";
        x = getXForCenteredText(text);
        y += gp.TILE_SIZE ;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x - gp.TILE_SIZE  , y);
        }
    }

    public int getXForCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
