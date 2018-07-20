package ClearOut.states;

import java.awt.Graphics;

import ClearOut.Handler;
import ClearOut.gfx.Assets;
import ClearOut.input.AudioManager;
import ClearOut.ui.ClickListener;
import ClearOut.ui.UIImageButton;
import ClearOut.ui.UIManager;

public class MenuState extends State {

    public UIImageButton play, help, score, options;
    
    public MenuState(Handler handler) {
        
        super(handler);
        
        play("res/sounds/Allmenu.wav",-1);
        
        uiManager = new UIManager(handler);

        int x = handler.getWidth() / 2;
        int y = handler.getHeight() / 2 - 100;
        int w = Assets.btnPlay.getWidth() - 30;
        int h = Assets.btnPlay.getHeight() - 20;
        play = new UIImageButton(x - Assets.btnPlay.getWidth() / 2, y, w, h,
                Assets.btnPlay, new ClickListener() {
            @Override
            public void onClick() {
                BG.stop();
                new AudioManager().play("res/snd/Button.wav", 0);
                handler.getMouseManager().setUIManager(null);
                handler.lv=0;
                handler.getGame().gameState = new GameState(handler, handler.getGame().levels[handler.lv]);
                State.setState(handler.getGame().gameState);
                handler.getGame().gameState.setui();
                handler.getGame().gameState.play("res/sounds/during_game.wav",-1);
            }
        });
        uiManager.addObject(play);
        y += Assets.btnPlay.getHeight();
        help = new UIImageButton(x - Assets.btnHelp.getWidth() / 2, y, w, h,
                Assets.btnHelp, new ClickListener() {
            @Override
            public void onClick() {
                new AudioManager().play("res/snd/Button.wav", 0);
                State.setState(handler.getGame().helpState);
                handler.getGame().helpState.menu = true;
                handler.getGame().helpState.setui();
            }
        });
        uiManager.addObject(help);
        y += Assets.btnHelp.getHeight();
        options = new UIImageButton(x - Assets.btnOptions.getWidth() / 2, y, w, h,
                 Assets.btnOptions, new ClickListener() {
            @Override
            public void onClick() {
                   new AudioManager().play("res/snd/Button.wav", 0);
            }
        });
        uiManager.addObject(options);
        y += Assets.btnOptions.getHeight();
        score = new UIImageButton(x - Assets.btnScore.getWidth() / 2, y, w, h,
                 Assets.btnScore, new ClickListener() {
            @Override
            public void onClick() {
                new AudioManager().play("res/snd/Button.wav", 0);
                //System.out.println("Score Clicked");
            }
        });
        uiManager.addObject(score);
        y += Assets.btnScore.getHeight();
    }

    @Override
    public void tick() {
        uiManager.tick();

        // Temporarily just go directly to the GameState, skip the menu state!
        // handler.getMouseManager().setUIManager(null);
        // State.setState(handler.getGame().gameState);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.menuScreen, 0, 0, handler.getWidth(), handler.getHeight(), null);
        g.drawImage(Assets.gametitle,(handler.getWidth()-Assets.gametitle.getWidth())/2,30,Assets.gametitle.getWidth(),Assets.gametitle.getHeight(),null);
        uiManager.render(g);
    }

}
