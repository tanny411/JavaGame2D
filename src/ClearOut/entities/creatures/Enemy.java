package ClearOut.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import ClearOut.Handler;
import ClearOut.gfx.Animation;
import ClearOut.gfx.Assets;
import ClearOut.tiles.Tile;

public class Enemy extends Creature {

    int timer;
    int lastTime, currentTime;
    //Animations
    private Animation animDown, animUp, animLeft, animRight;

    public Enemy(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        speed = 1;
        //Animatons
        animDown = new Animation(500, Assets.zombie_down, true);
        animUp = new Animation(500, Assets.zombie_up, true);
        animLeft = new Animation(500, Assets.zombie_left, true);
        animRight = new Animation(500, Assets.zombie_right, true);

        lastTime = (int) System.currentTimeMillis();
        timer = 0;

        xMove = 0;
        yMove = 0;
    }

    @Override
    public void move() {
        if (xMove != 0 && !checkEntityCollisions(xMove, 0f)) {
            moveX();
        } else if (yMove != 0 && !checkEntityCollisions(0f, yMove)) {
            moveY();
        } else {
            getInput();
        }
    }

    @Override
    public void tick() {
        //Animations
        animDown.tick();
        animUp.tick();
        animRight.tick();
        animLeft.tick();
        //Movement
        move();

    }

    @Override
    public void moveX() {
        if (xMove > 0) {//Moving right
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;

            if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
                    && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
                x += xMove;
            } else {
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
                getInput();
            }

        } else if (xMove < 0) {//Moving left
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;

            if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
                    && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
                x += xMove;
            } else {
                x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
                getInput();
            }

        }
    }

    @Override
    public void moveY() {
        if (yMove < 0) {//Up
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;

            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)
                    && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
                y += yMove;
            } else {
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
                getInput();
            }

        } else if (yMove > 0) {//Down
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;

            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)
                    && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
                y += yMove;
            } else {
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
                getInput();
            }

        }
    }

    @Override
    public void die() {

    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        currentTime = (int) System.currentTimeMillis();
        timer += (currentTime - lastTime);
        lastTime = currentTime;

        if (timer < 500) {
            return;
        }

        timer = 0;

        Random rand = new Random();
        int r = Math.abs(rand.nextInt()) % 4;
        //System.out.println(r);
        if (r == 0) {
            yMove = -speed;
        }
        if (r == 1) {
            yMove = speed;
        }
        if (r == 2) {
            xMove = -speed;
        }
        if (r == 3) {
            xMove = speed;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        //g.setColor(Color.red);
        //g.fillRect((int) (x+bounds.x - handler.getGameCamera().getxOffset()), (int) (y+bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
    }

    private BufferedImage getCurrentAnimationFrame() {
        if (xMove < 0) {
            return animLeft.getCurrentFrame();
        } else if (xMove > 0) {
            return animRight.getCurrentFrame();
        } else if (yMove < 0) {
            return animUp.getCurrentFrame();
        } else {
            return animDown.getCurrentFrame();
        }
    }

    @Override
    public boolean isEnemy() {
        return true;
    }
}
