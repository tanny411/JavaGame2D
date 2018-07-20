package ClearOut.items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import ClearOut.Handler;
import ClearOut.gfx.Assets;

public class Item {

    // Handler
    public static Item[] items = new Item[256];
    public static Item woodItem = new Item(Assets.wood, "Wood", 0);
    public static Item rockItem = new Item(Assets.rock, "Rock", 1);
    public static Item rockItem2 = new Item(Assets.smallRock, "Rock", 16);
    public static Item[] fruits;
    public static Item gun = new Item(Assets.gun, "Revolver", 9);
    public static Item run = new Item(Assets.run, "Speed", 10);
    public static Item[] knife;
    public static Item bombPow = new Item(Assets.bombPow, "Bomb Amplifier", 14);
    public static Item bombExtra= new Item(Assets.bombExtra, "Extra Bomb", 15);
    // Class

    public static void makeFruits() {
        fruits = new Item[7];
        fruits[0] = new Item(Assets.fruits[0], "Banana", 2);
        fruits[1] = new Item(Assets.fruits[1], "Cheese", 3);
        fruits[2] = new Item(Assets.fruits[2], "Cherry", 4);
        fruits[3] = new Item(Assets.fruits[3], "Grape", 5);
        fruits[4] = new Item(Assets.fruits[4], "Lemon", 6);
        fruits[5] = new Item(Assets.fruits[5], "Nut", 7);
        fruits[6] = new Item(Assets.fruits[6], "Orange", 8);
        knife = new Item[3];
        knife[0] = new Item(Assets.knife[0], "Knife", 11);
        knife[1] = new Item(Assets.knife[2], "Special Knife", 12);
        knife[2] = new Item(Assets.knife[1], "Sword", 13);
    }

    public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;

    protected Handler handler;
    protected BufferedImage texture;
    protected String name;
    protected final int id;

    protected Rectangle bounds;

    protected int x, y, count;
    protected boolean pickedUp = false;

    public Item(BufferedImage texture, String name, int id) {
        this.texture = texture;
        this.name = name;
        this.id = id;
        count = 1;

        bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);

        items[id] = this;
    }

    public void tick() {
        if (handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)) {
            pickedUp = true;
            handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
        }
    }

    public void render(Graphics g) {
        if (handler == null) {
            return;
        }
        render(g, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()));
    }

    public void render(Graphics g, int x, int y) {
        if(id==16)
             g.drawImage(texture, x, y, ITEMWIDTH*2, ITEMHEIGHT*2, null);
        else
            g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
    }

    public Item createNew(int count) {
        Item i = new Item(texture, name, id);
        i.setPickedUp(true);
        i.setCount(count);
        return i;
    }

    public Item createNew(int x, int y) {
        Item i = new Item(texture, name, id);
        i.setPosition(x, y);
        return i;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }

    // Getters and Setters
    public Handler getHandler() {
        return handler;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

}
