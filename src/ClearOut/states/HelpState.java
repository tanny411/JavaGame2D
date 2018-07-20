package ClearOut.states;

import java.awt.Graphics;

import ClearOut.Handler;
import ClearOut.gfx.Assets;
import ClearOut.input.AudioManager;
import ClearOut.ui.ClickListener;
import ClearOut.ui.UIImageButton;
import ClearOut.ui.UIManager;

public class HelpState extends State {

    public HelpState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);

        uiManager.addObject(
                new UIImageButton(handler.getWidth() - Assets.cross.getWidth() - 10, 10, Assets.cross.getWidth(), Assets.cross.getHeight(),
                        Assets.cross, new ClickListener() {
                    @Override
                    public void onClick() {
                        new AudioManager().play("res/snd/Button.wav", 0);
                        handler.getMouseManager().setUIManager(null);
                        if (menu == false) {
                            State.setState(handler.getGame().pauseState);
                            handler.getGame().pauseState.setui();
                        } else {
                            State.setState(handler.getGame().menuState);
                            handler.getGame().menuState.setui();
                        }
                    }
                })
        );
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.helpScreen, 0, 0, handler.getWidth(),handler.getHeight(), null);
        uiManager.render(g);
    }

}
