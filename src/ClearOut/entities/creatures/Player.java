package ClearOut.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import ClearOut.Handler;
import ClearOut.entities.Entity;
import ClearOut.entities.statics.Bomb;
import ClearOut.gfx.Animation;
import ClearOut.gfx.Assets;
import ClearOut.input.AudioManager;
import ClearOut.inventory.Inventory;
import ClearOut.states.State;
import ClearOut.tiles.Tile;
import java.awt.Color;

public class Player extends Creature {

    //Animations
    private Animation animDown, animUp, animLeft, animRight;
    // Attack timer
    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
    // Inventory
    public Inventory inventory;

    public Bomb bomb = null;

    Rectangle ar;

    private BufferedImage previousFrame;
    //bullet relase lag
    int in;
    //bombPower
    public int bombPow;
    
    private int dir = Bullet.DOWN;
    
    public Player(Handler handler) {
        super(handler, 0f, 0f, Creature.DEFAULT_CREATURE_WIDTH, 2 * Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 12;
        bounds.y = 80;
        bounds.width = 40;
        bounds.height = 45;

        //Animatons
        animDown = new Animation(500, Assets.player_down, true);
        animUp = new Animation(500, Assets.player_up, true);
        animLeft = new Animation(500, Assets.player_left, true);
        animRight = new Animation(500, Assets.player_right, true);

        inventory = new Inventory(handler);

        ar = new Rectangle();

        in = 0;

        previousFrame = animDown.getCurrentFrame();
        
        bombPow=1;
    }

    @Override
    public void tick() {
        //Animations
        animDown.tick();
        animUp.tick();
        animRight.tick();
        animLeft.tick();
        //Movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
        // Attack
        if(handler.getWorld().hasKnife) checkAttacks();
        //shooting
        if (in == 0 && handler.getWorld().hasGun) {
           shoot();
        }
        in = (1 + in) % 8;
        //shooting end
        
    }

    private void checkAttacks() {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown) {
            return;
        }

        if (inventory.isActive() || handler.pause) {
            return;
        }

        Rectangle cb = getCollisionBounds(0, 0);

        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;

        if (handler.getKeyManager().aUp) {
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;
        } else if (handler.getKeyManager().aDown) {
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y + cb.height;
        } else if (handler.getKeyManager().aLeft) {
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        } else if (handler.getKeyManager().aRight) {
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        } else {
            return;
        }

        attackTimer = 0;

        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this)) {
                continue;
            }
            if (e.getCollisionBounds(0, 0).intersects(ar)) {
                new AudioManager().play("res/snd/Combat.wav",0);
                e.hurt(1);
                return;
            }
        }

    }

    @Override
    public void die() {
        //System.out.println("You lose");
       
        handler.getMouseManager().setUIManager(null);
        handler.getGame().overState.setui();
        State.setState(handler.getGame().overState);
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if (inventory.isActive() || handler.pause) {
            return;
        }

        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_B) && Bomb.dropped == false) {
            bomb = new Bomb(handler, (int) ((x + bounds.x + bounds.width / 2) / Tile.TILEWIDTH) * Tile.TILEWIDTH, (int) ((y + bounds.y +bounds.height/2) / Tile.TILEHEIGHT) * Tile.TILEHEIGHT);
            //handler.getWorld().getEntityManager().addEntity(bomb);
        }

        if (handler.getKeyManager().up) {
            yMove = -speed;
        }
        if (handler.getKeyManager().down) {
            yMove = speed;
        }
        if (handler.getKeyManager().left) {
            xMove = -speed;
        }
        if (handler.getKeyManager().right) {
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
            return previousFrame = animLeft.getCurrentFrame();
        }
        if (xMove > 0) {
            return previousFrame = animRight.getCurrentFrame();
        }
        if (yMove < 0) {
            return previousFrame = animUp.getCurrentFrame();
        } else if (yMove > 0) {
            return previousFrame = animDown.getCurrentFrame();
        } else {
            return previousFrame;
        }

    }

    private void shoot() {
        if (handler.getKeyManager().keys[KeyEvent.VK_SPACE]) {
            if (inventory.isActive() || handler.pause) {
                return;
            }
            //System.out.println("Bullet Out");
            
            if (prx > 0) {
                dir = Bullet.RIGHT;
            } else if (prx < 0) {
                dir = Bullet.LEFT;
            } else if (pry > 0) {
                dir = Bullet.DOWN;
            } else if (pry < 0) {
                dir = Bullet.UP;
            }
            
            new AudioManager().play("res/snd/bullet2.wav", 0);
            Bullet bullet = new Bullet(handler, x + width / 2, y + height / 2 + 20, dir);
            handler.getWorld().getEntityManager().addEntity(bullet);
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

}
