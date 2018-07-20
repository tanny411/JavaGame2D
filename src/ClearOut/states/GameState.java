package ClearOut.states;

import java.awt.Graphics;

import ClearOut.Handler;
import ClearOut.gfx.Assets;
import ClearOut.input.AudioManager;
import ClearOut.ui.ClickListener;
import ClearOut.ui.UIImageButton;
import ClearOut.ui.UIManager;
import ClearOut.worlds.World;

public class GameState extends State {

    private World world;

    private UIImageButton pause;
    
    public GameState(Handler handler, String path) {
        super(handler);
        
        world = new World(handler, path);
        handler.setWorld(world);
        uiManager = new UIManager(handler);

        pause = new UIImageButton(10, 5, Assets.pause.getWidth(), Assets.pause.getHeight(), Assets.pause,
                new ClickListener() {
            @Override
            public void onClick() {
                new AudioManager().play("res/snd/Button.wav", 0);
                handler.pause = true;
                handler.getMouseManager().setUIManager(null);
                State.setState(handler.getGame().pauseState);
                handler.getGame().pauseState.setui();
                //System.out.println("clicked");
            }
        });
        uiManager.addObject(pause);

    }

    @Override
    public void tick() {
        world.tick();
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        uiManager.render(g);
        //g.drawImage(Assets.bomb, 64,64,(int)(54*.74),54,null);
    }

}
