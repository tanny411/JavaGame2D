package ClearOut.states;

import java.awt.Graphics;

import ClearOut.Handler;
import static ClearOut.Handler.lv;
import ClearOut.gfx.Assets;
import ClearOut.input.AudioManager;
import ClearOut.ui.ClickListener;
import ClearOut.ui.UIImageButton;
import ClearOut.ui.UIManager;

public class LevelState extends State {

    public LevelState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);

        uiManager.addObject(
                new UIImageButton(430, 320, Assets.nextLevel.getWidth() / 2, Assets.nextLevel.getHeight() / 2,
                        Assets.nextLevel, new ClickListener() {
                    @Override
                    public void onClick() {
                        new AudioManager().play("res/snd/Button.wav", 0);
                        handler.getMouseManager().setUIManager(null);
                        handler.getGame().gameState = new GameState(handler, handler.getGame().levels[++lv]);
                        State.setState(handler.getGame().gameState);
                        handler.getGame().gameState.setui();
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
        g.drawImage(Assets.levelScreen, 0, 0, handler.getWidth(), handler.getHeight(), null);
        uiManager.render(g);
    }

}
