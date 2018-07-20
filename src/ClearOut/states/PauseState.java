package ClearOut.states;

import java.awt.Graphics;

import ClearOut.Handler;
import ClearOut.gfx.Assets;
import ClearOut.input.AudioManager;
import ClearOut.ui.ClickListener;
import ClearOut.ui.UIImageButton;
import ClearOut.ui.UIManager;

public class PauseState extends State {

    public UIImageButton play, help, score, options, soundOn, soundOff;

    public PauseState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);

        makeButtons();
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        handler.getGame().gameState.render(g);
        g.drawImage(Assets.pauseScreen, 0, 0, handler.getWidth(), handler.getHeight(), null);
        uiManager.render(g);
    }

    private void makeButtons() {
        int x = 148- 10;
        int y = 189 + (int) (93 / 2) - Assets.help.getHeight() / 2;
        double blk = 83.75;
        help = new UIImageButton(x + (int) (2 * blk + blk / 2), y, Assets.help.getWidth(), Assets.help.getHeight(),
                Assets.help, new ClickListener() {
            @Override
            public void onClick() {
                new AudioManager().play("res/snd/Button.wav", 0);
                State.setState(handler.getGame().helpState);
                handler.getGame().helpState.menu = false;
                handler.getGame().helpState.setui();
            }
        });
        uiManager.addObject(help);
        play = new UIImageButton(x + (int) (blk / 2), y, Assets.play.getWidth(), Assets.play.getHeight(),
                Assets.play, new ClickListener() {
            @Override
            public void onClick() {
                new AudioManager().play("res/snd/Button.wav", 0);
                handler.pause = false;
                handler.getMouseManager().setUIManager(null);
                State.setState(handler.getGame().gameState);
                handler.getGame().gameState.setui();
            }
        });
        uiManager.addObject(play);
        soundOn = new UIImageButton(x + (int) (blk + blk / 2), y, Assets.soundOn.getWidth(), Assets.soundOn.getHeight(),
                Assets.soundOn, new ClickListener() {
            @Override
            public void onClick() {
                new AudioManager().play("res/snd/Button.wav", 0);
                uiManager.removeObject(soundOn);
                uiManager.addObject(soundOff);
            }
        });
        uiManager.addObject(soundOn);
        options = new UIImageButton(x + (int) (3 * blk + blk / 2), y, Assets.options.getWidth(), Assets.options.getHeight(),
                Assets.options, new ClickListener() {
            @Override
            public void onClick() {
               new AudioManager().play("res/snd/Button.wav", 0);

            }
        });
        uiManager.addObject(options);

        soundOff = new UIImageButton(x + (int) (blk + blk / 2), y, Assets.soundOff.getWidth(), Assets.soundOff.getHeight(),
                Assets.soundOff, new ClickListener() {
            @Override
            public void onClick() {
                new AudioManager().play("res/snd/Button.wav", 0);
                uiManager.removeObject(soundOff);
                uiManager.addObject(soundOn);
            }
        });
    }
}
