package ClearOut.entities.statics;

import static ClearOut.Game.levels;
import java.awt.Graphics;

import ClearOut.Handler;
import static ClearOut.Handler.lv;
import ClearOut.gfx.Assets;
import ClearOut.input.AudioManager;
import ClearOut.states.GameState;
import ClearOut.states.LevelState;
import ClearOut.states.State;
import ClearOut.tiles.Tile;

public class Exit extends StaticEntity {

    public Exit(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
        bounds.x = 13;
        bounds.y = 2;
        bounds.width = 37;
        bounds.height = 57;
    }

    @Override
    public void tick() {
        boolean col = (handler.getWorld().player.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(0f, 0f)));
        if (handler.getWorld().boxNum <= 0 && col) {
            //System.out.println("NEXT LEVEL");
            new AudioManager().play("res/snd/exit.wav",0);
            handler.getMouseManager().setUIManager(null);
            handler.getGame().levelState = new LevelState(handler);
            State.setState(handler.getGame().levelState);
            handler.getGame().levelState.setui();
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.exit, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        //g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), 
        //		(int) (y+bounds.y - handler.getGameCamera().getyOffset()),bounds.width,bounds.height);
    }

    @Override
    public void die() {

    }

}
