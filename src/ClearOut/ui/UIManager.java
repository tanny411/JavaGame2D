package ClearOut.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ClearOut.Handler;

public class UIManager {

	private Handler handler;
	private ArrayList<UIObject> objects;

	public UIManager(Handler handler) {
		this.handler = handler;
		objects = new ArrayList<UIObject>();
	}

	public void tick() {
		for (int i = 0; i < objects.size(); i++) {
			UIObject o = objects.get(i);
			o.tick();
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			UIObject o = objects.get(i);
			o.render(g);
		}
	}

	public void onMouseMove(MouseEvent e) {
		for (int i = 0; i < objects.size(); i++) {
			UIObject o = objects.get(i);
			o.onMouseMove(e);
		}
	}

	public void onMouseRelease(MouseEvent e) {
		for (int i = 0; i < objects.size(); i++) {
			UIObject o = objects.get(i);
			o.onMouseRelease(e);
		}
	}

	public void addObject(UIObject o) {
		objects.add(o);
	}

	public void removeObject(UIObject o) {
		objects.remove(o);
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ArrayList<UIObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<UIObject> objects) {
		this.objects = objects;
	}

}
