
package ClearOut.entities.creatures;

import ClearOut.Handler;
import ClearOut.gfx.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bullet extends Creature{

    
    public final static int UP = 0, DOWN = 1, RIGHT = 2, LEFT = 3;
    BufferedImage frame;
    int dir;
    
    public Bullet(Handler handler, float x, float y, int dir) {
        
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH/2, Creature.DEFAULT_CREATURE_HEIGHT/2);
        
        speed=2;
        
        this.dir = dir;
        
        if (dir == UP) {
            frame = Assets.bulletUP;
        } else if (dir == DOWN) {
            frame = Assets.bulletDOWN;
        } else if (dir == LEFT) {
            frame = Assets.bulletLEFT;
        } else {
            frame = Assets.bulletRIGHT;
        }


        //bounds set
        bounds.x = 11;
        bounds.y = 9;
        bounds.width = 12;
        bounds.height = 20;

        //direction set
        if (dir == UP) {
            yMove = -speed;
        } else if (dir == DOWN) {
            yMove = speed;
        } else if (dir == RIGHT) {
            xMove = speed;
        } else {
            xMove = -speed;
        }

    }

    @Override
    public void move() {
        
        if (!checkEntityCollisions(xMove, yMove)) {
            moveX();
            moveY();
        }else {
            die();
        }
    }

    @Override
    public void render(Graphics g) {
        
        g.drawImage(frame,(int)(x - handler.getGameCamera().getxOffset()) , (int)(y - handler.getGameCamera().getyOffset()),width,height,null);

    }

    @Override
    public void die() {
        
        if (active == true) {
            active = false;
        }
    }
    @Override
    public boolean isBullet() {
        return true;
    }
    @Override
    public void tick() {
        
        move();
        if (x < handler.getGameCamera().getxOffset() || y < handler.getGameCamera().getyOffset() || x > handler.getGameCamera().getxOffset() + handler.getWidth() || y > handler.getGameCamera().getyOffset() + handler.getHeight()) {
            die();
         }
        
    }

}

    
    

