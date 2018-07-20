package ClearOut.entities.creatures;

import ClearOut.Handler;
import ClearOut.entities.Entity;
import ClearOut.tiles.Tile;

public abstract class Creature extends Entity {

    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64,
                            DEFAULT_CREATURE_HEIGHT = 64;

    public float speed;
    protected float xMove, yMove;
    protected int px, py, prx, pry;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
        px=0;
        py=0;
    }

    public void move() {
        if (xMove != 0 || yMove != 0) {
            ///previous x and y
            ///previous relative x and y
            prx = (int)x - px;
            pry = (int)y - py;
            px = (int)x;
            py = (int)y;
        }
        if (!checkEntityCollisions(xMove, 0f)) {
            moveX();
        }
        if (!checkEntityCollisions(0f, yMove)) {
            moveY();
        }
    }

    public void moveX() {
        if (xMove > 0) {//Moving right
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;

            if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
                    && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
                x += xMove;
            } else {
                if(this.isBullet()) die();
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
            }

        } else if (xMove < 0) {//Moving left
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;

            if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
                    && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
                x += xMove;
            } else {
                if(this.isBullet()) die();
                x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
            }

        }
    }

    public void moveY() {
        if (yMove < 0) {//Up
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;

            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)
                    && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
                y += yMove;
            } else {
                if(this.isBullet()) die();
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
            }

        } else if (yMove > 0) {//Down
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;

            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)
                    && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
                y += yMove;
            } else {
                if(this.isBullet()) die();
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
            }

        }
    }

    protected boolean collisionWithTile(int x, int y) {
        return handler.getWorld().getTile(x, y).isSolid();
    }

    //GETTERS SETTERS
    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

}
