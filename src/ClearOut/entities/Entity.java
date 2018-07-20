package ClearOut.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import ClearOut.Handler;

public abstract class Entity {

    public static final int DEFAULT_HEALTH = 3;
    protected Handler handler;
    public float x, y;
    protected int width, height;
    protected int health;
    protected boolean active = true;
    protected Rectangle bounds;

    public Entity(Handler handler, float x, float y, int width, int height) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        health = DEFAULT_HEALTH;

        bounds = new Rectangle(0, 0, width, height);
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract void die();

    public void hurt(int amt) {
        health -= amt;
        if (health <= 0) {
            active = false;
            die();
        }
    }

    public boolean checkEntityCollisions(float xOffset, float yOffset) {
        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this))
            {
                continue;
            }
            
            if( (this.isBullet()&&e.isPlayer()) || (e.isBullet()&&this.isPlayer()) ) continue;
            if ((e.isPlayer() && this.isBomb()) || (this.isPlayer() && e.isBomb())) continue;
            
            if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
                if ((e.isPlayer() && this.isEnemy()) || (this.isPlayer() && e.isEnemy())) {
                    if (e.isPlayer()) {
                        e.hurt(100);
                    }
                    if (this.isPlayer()) {
                        this.hurt(100);
                    }
                }
                if(this.isBullet()) e.hurt(1);
                return true;
            }
        }
        return false;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isBomb() {
        return false;
    }

    public boolean isBox() {
        return false;
    }

    public boolean isPlayer() {
        return false;
    }

    public boolean isEnemy() {
        return false;
    }

    public boolean isBullet() {
        return false;
    }
}
