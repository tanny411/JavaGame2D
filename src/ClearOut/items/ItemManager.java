package ClearOut.items;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import ClearOut.Handler;
import ClearOut.input.AudioManager;

public class ItemManager {

    private Handler handler;
    private ArrayList<Item> items;

    public ItemManager(Handler handler) {
        this.handler = handler;
        items = new ArrayList<Item>();
    }

    public void tick() {
        Iterator<Item> it = items.iterator();
        while (it.hasNext()) {
            Item i = it.next();
            i.tick();
            if (i.isPickedUp()) {
                new AudioManager().play("res/snd/pickup.wav", 0);
                if (i.id == 10) {
                    handler.getWorld().player.speed += 2;
                }
                if (i.id >= 11 && i.id <= 13) {
                    handler.getWorld().hasKnife = true;
                }
                if (i.id == 9) {
                    handler.getWorld().hasGun = true;
                }
                if (i.id == 14) {
                    handler.getWorld().player.bombPow++;
                }
                it.remove();
            }
        }
    }

    public void render(Graphics g) {
        for (Item i : items) {
            i.render(g);
        }
    }

    public void addItem(Item i) {
        i.setHandler(handler);
        items.add(i);
    }

    // Getters and Setters
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

}
