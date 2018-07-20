package ClearOut.states;

import java.awt.Graphics;

import ClearOut.Handler;
import ClearOut.input.AudioManager;
import ClearOut.ui.UIManager;
import sun.audio.AudioPlayer;

public abstract class State {

    public AudioManager BG;
    public UIManager uiManager;
    public boolean menu;
    private static State currentState = null;

    public static void setState(State state) {
        currentState = state;
    }

    public static State getState() {
        return currentState;
    }

    //CLASS
    protected Handler handler;

    public State(Handler handler) {
        this.handler = handler;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public void play(String path,int x)
    {
        if(BG!=null) BG.stop();
        BG=new AudioManager();
        BG.play(path,x);
    }
    public void setui() {
        handler.getMouseManager().setUIManager(uiManager);
        return;
    }

}
