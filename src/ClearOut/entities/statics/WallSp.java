package ClearOut.entities.statics;

import java.awt.Graphics;
import java.util.Random;

import ClearOut.Handler;
import ClearOut.gfx.Animation;
import ClearOut.gfx.Assets;
import ClearOut.items.Item;
import ClearOut.tiles.Tile;

public class WallSp extends StaticEntity {

    Animation animWall;
    private boolean dead = false;

    public WallSp(Handler handler, float x, float y) {
        super(handler, x + 4, y + 4, Tile.TILEWIDTH - 8, Tile.TILEHEIGHT - 8);
        //super(handler, x, y , Tile.TILEWIDTH, Tile.TILEHEIGHT);
        animWall = new Animation(100, Assets.wallSp, false);
    }

    @Override
    public void tick() {
        if (active && dead) {
            animWall.tick();
        }
    }

    @Override
    public void render(Graphics g) {
        if (!active) {
            return;
        }
        if (dead == true) {
            if (animWall.over) {
                active = false;
                return;
            }
            g.drawImage(animWall.getCurrentFrame(), (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else {
            g.drawImage(Assets.wallSp[0], (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        }
    }

    @Override
    public void hurt(int amt) {
        health -= amt;
        if (health <= 0) {
            die();
        }
    }

    @Override
    public void die() {

        if(dead==true) return;
        Random rand = new Random();
        int r = Math.abs(rand.nextInt()) % (Assets.fruits.length + 7);
        int posx = (int) (x / Tile.TILEWIDTH) * 64 + 16;
        int posy = (int) (y / Tile.TILEHEIGHT) * 64 + 16;
        
        if (r == 7) {
            handler.getWorld().getItemManager().addItem(Item.gun.createNew((int) posx, (int) posy));
        } else if (r == 8) {
            handler.getWorld().getItemManager().addItem(Item.run.createNew((int) posx, (int) posy));
        } else if (r == 9) {
            if(handler.lv==0) handler.getWorld().getItemManager().addItem(Item.fruits[r%Assets.fruits.length ].createNew((int) posx, (int) posy));
            else handler.getWorld().getItemManager().addItem(Item.knife[0].createNew((int) posx, (int) posy));
        } else if (r == 10) {
             if(handler.lv==0) handler.getWorld().getItemManager().addItem(Item.fruits[r%Assets.fruits.length ].createNew((int) posx, (int) posy));
             else handler.getWorld().getItemManager().addItem(Item.knife[1].createNew((int) posx, (int) posy));
        } else if (r == 11) {
             if(handler.lv==0) handler.getWorld().getItemManager().addItem(Item.fruits[r%Assets.fruits.length ].createNew((int) posx, (int) posy));
             else handler.getWorld().getItemManager().addItem(Item.knife[2].createNew((int) posx, (int) posy));
        } else if (r == 12) {
            handler.getWorld().getItemManager().addItem(Item.bombPow.createNew((int) posx, (int) posy));
        } else if (r == 13) {
            handler.getWorld().getItemManager().addItem(Item.bombExtra.createNew((int) posx, (int) posy));
        } else {
            handler.getWorld().getItemManager().addItem(Item.fruits[r].createNew((int) posx, (int) posy));
        }
        dead = true;
    }

    @Override
    public boolean isBox() {
        return true;
    }
}
