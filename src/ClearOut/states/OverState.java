package ClearOut.states;

import java.awt.Graphics;

import ClearOut.Handler;
import static ClearOut.Handler.lv;
import ClearOut.gfx.Assets;
import ClearOut.input.AudioManager;
import ClearOut.ui.ClickListener;
import ClearOut.ui.UIImageButton;
import ClearOut.ui.UIManager;

public class OverState extends State {

    public UIImageButton replay, menu;

    public OverState(Handler handler) {
        super(handler);
        
        uiManager = new UIManager(handler);

        replay = new UIImageButton(204, 257, Assets.replay.getWidth(), Assets.replay.getHeight(),
                Assets.replay, new ClickListener() {
            @Override
            public void onClick() {
                new AudioManager().play("res/snd/Button.wav", 0);
                //System.out.println("YAS");
                handler.getMouseManager().setUIManager(null);
                handler.getGame().gameState = new GameState(handler, handler.getGame().levels[lv]);
                State.setState(handler.getGame().gameState);
                handler.getGame().gameState.setui();
            }
        });
        uiManager.addObject(replay);

        menu = new UIImageButton(367, 257, Assets.menu.getWidth(), Assets.menu.getHeight(),
                Assets.menu, new ClickListener() {
            @Override
            public void onClick() {
                if(handler.getGame().gameState.BG!=null) handler.getGame().gameState.BG.stop(); 
                new AudioManager().play("res/snd/Button.wav", 0);
                handler.getMouseManager().setUIManager(null);
                State.setState(handler.getGame().menuState);
                handler.getGame().menuState.setui();
                handler.getGame().menuState.play("res/sounds/Allmenu.wav",-1);
            }
        });
        uiManager.addObject(menu);
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        handler.getGame().gameState.render(g);
        g.drawImage(Assets.overScreen, 0, 0, handler.getWidth(), handler.getHeight(), null);
        uiManager.render(g);
    }
}
