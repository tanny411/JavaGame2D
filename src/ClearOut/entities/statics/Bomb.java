package ClearOut.entities.statics;

import java.awt.Color;
import javafx.util.Pair;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import ClearOut.Handler;
import ClearOut.entities.Entity;
import ClearOut.gfx.Animation;
import ClearOut.gfx.Assets;
import ClearOut.input.AudioManager;
import ClearOut.tiles.Tile;

public class Bomb extends StaticEntity {

    public static boolean dropped = false;
    private int speed = 2000;
    private long lastTime, timer;
    private boolean blast = false;
    private int index = 0;
    Animation animBlast = null;
    Set< Pair<Integer, Integer>> effectedTiles;
    private boolean flag=false;
    
    public Bomb(Handler handler, int x, int y) {

        super(handler, x, y, 60, 60);
        timer = 0;
        lastTime = System.currentTimeMillis();
        effectedTiles = new HashSet< Pair<Integer, Integer>>();
        dropped = true;
    }

    @Override
    public void tick() {
        if (!active) {
            return;
        }
        if (blast == false) {
            timer += System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();

            if (timer > speed) {
                animBlast = new Animation(70, false, 7);
                blast = true;
            }
        } else {
            if(flag==false) new AudioManager().play("res/snd/BOM_11_S.wav",0);
            flag=true;
            animBlast.tick();
        }
    }

    @Override
    public void render(Graphics g) {

        if (!active) {
            return;
        }
        if (blast == false) {
            g.drawImage(Assets.bomb, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else {
            Blast(g);
        }

    }

    private void Blast(Graphics g) {
        if (!active) {
            return;
        }
        if (animBlast.over) {
            active = false;
            ///die was here
            return;
        }
        int in = animBlast.getIndex();

        int tempx = (int) (x - handler.getGameCamera().getxOffset());
        int tempy = (int) (y - handler.getGameCamera().getyOffset());

        int xtile = (int) (x / Tile.TILEWIDTH);
        int ytile = (int) (y / Tile.TILEHEIGHT);
        //System.out.println(xtile+"  "+ytile);
        g.drawImage(Assets.bombMid[in], tempx, tempy, 64, 64, null);

        effectedTiles.add(new Pair<Integer, Integer>(xtile, ytile));
        int x = handler.getWorld().player.bombPow;
        int i = 1;
        for (; i <= x; i++) {
            if (ok(xtile + i, ytile)) {
                g.drawImage(Assets.bombHor[in], tempx + i*64, tempy, 64, 64, null);
                effectedTiles.add(new Pair<Integer, Integer>(xtile + i, ytile));
            } else {
                break;
            }
        }
        if (ok(xtile + i, ytile)) {
            g.drawImage(Assets.bombEndr[in], tempx + i*64, tempy, 64, 64, null);
            effectedTiles.add(new Pair<Integer, Integer>(xtile + i, ytile));
        }
//        if (ok(xtile + 1, ytile)) {
//            g.drawImage(Assets.bombHor[in], tempx + 64, tempy, 64, 64, null);
//            effectedTiles.add(new Pair<Integer, Integer>(xtile + 1, ytile));
//            if (ok(xtile + 2, ytile)) {
//                g.drawImage(Assets.bombEndr[in], tempx + 64 + 64, tempy, 64, 64, null);
//                effectedTiles.add(new Pair<Integer, Integer>(xtile + 2, ytile));
//            }
//        }
        i = 1;
        for (; i <= x; i++) {
            if (ok(xtile - i, ytile)) {
                g.drawImage(Assets.bombHor[in], tempx - i*64, tempy, 64, 64, null);
                effectedTiles.add(new Pair<Integer, Integer>(xtile - i, ytile));
            } else {
                break;
            }
        }
        if (ok(xtile - i, ytile)) {
            g.drawImage(Assets.bombEndl[in], tempx - i*64, tempy, 64, 64, null);
            effectedTiles.add(new Pair<Integer, Integer>(xtile - i, ytile));
        }
//        if (ok(xtile - 1, ytile)) {
//            g.drawImage(Assets.bombHor[in], tempx - 64, tempy, 64, 64, null);
//            effectedTiles.add(new Pair<Integer, Integer>(xtile - 1, ytile));
//            if (ok(xtile - 2, ytile)) {
//                g.drawImage(Assets.bombEndl[in], tempx - 64 - 64, tempy, 64, 64, null);
//                effectedTiles.add(new Pair<Integer, Integer>(xtile - 2, ytile));
//            }
//        }
        i = 1;
        for (; i <= x; i++) {
            if (ok(xtile, ytile+i)) {
                g.drawImage(Assets.bombVer[in], tempx , tempy+ i*64-i, 64, 70, null);
                effectedTiles.add(new Pair<Integer, Integer>(xtile , ytile+i));
            } else {
                break;
            }
        }
        if (ok(xtile, ytile+i)) {
            g.drawImage(Assets.bombEndd[in], tempx, tempy+i*64-i, 64, 70, null);
            effectedTiles.add(new Pair<Integer, Integer>(xtile, ytile+i));
        }
//        if (ok(xtile, ytile + 1)) {
//            g.drawImage(Assets.bombVer[in], tempx, tempy + 64 - 1, 64, 64, null);
//            effectedTiles.add(new Pair<Integer, Integer>(xtile, ytile + 1));
//            if (ok(xtile, ytile + 2)) {
//                g.drawImage(Assets.bombEndd[in], tempx, tempy + 64 + 64 - 2, 64, 64, null);
//                effectedTiles.add(new Pair<Integer, Integer>(xtile, ytile + 2));
//            }
//        }
        i = 1;
        for (; i <= x; i++) {
            if (ok(xtile, ytile-i)) {
                g.drawImage(Assets.bombVer[in], tempx , tempy- i*64-i, 64, 70, null);
                effectedTiles.add(new Pair<Integer, Integer>(xtile , ytile-i));
            } else {
                break;
            }
        }
        if (ok(xtile, ytile-i)) {
            g.drawImage(Assets.bombEndu[in], tempx, tempy-i*64-i, 64, 70, null);
            effectedTiles.add(new Pair<Integer, Integer>(xtile, ytile-i));
        }
//        if (ok(xtile, ytile - 1)) {
//            g.drawImage(Assets.bombVer[in], tempx, tempy - 64, 64, 64, null);
//            effectedTiles.add(new Pair<Integer, Integer>(xtile, ytile - 1));
//            if (ok(xtile, ytile - 2)) {
//                g.drawImage(Assets.bombEndu[in], tempx, tempy - 64 - 64 + 1, 64, 64, null);
//                effectedTiles.add(new Pair<Integer, Integer>(xtile + 1, ytile - 2));
//            }
//        }
        if(in==5)die();
    }

    private boolean ok(int x, int y) {
        return !handler.getWorld().getTile(x, y).isSolid();
    }

    @Override
    public void die() {
        //System.out.println("Dead"+effectedTiles.size()+"  ");
        for (Pair<Integer, Integer> p : effectedTiles) {
            //System.out.println(p.getKey()+"  "+p.getValue());
            for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
                if (e.getCollisionBounds(0f, 0f).intersects(new Rectangle(p.getKey() * 64, p.getValue() * 64, 64, 64))) {
                    e.hurt(100);
                }
            }
        }
        dropped = false;
    }

    @Override
    public boolean isBomb() {
        return true;
    }

}
