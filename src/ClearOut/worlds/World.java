package ClearOut.worlds;

import java.awt.Graphics;

import ClearOut.Handler;
import ClearOut.entities.Entity;
import ClearOut.entities.EntityManager;
import ClearOut.entities.creatures.Enemy;
import ClearOut.entities.creatures.Player;
import ClearOut.entities.statics.Exit;
import ClearOut.entities.statics.Rock;
import ClearOut.entities.statics.Rock2;
import ClearOut.entities.statics.Tree;
import ClearOut.entities.statics.Tree2;
import ClearOut.entities.statics.Wall;
import ClearOut.entities.statics.WallSp;
import ClearOut.items.Item;
import ClearOut.items.ItemManager;
import ClearOut.tiles.Tile;
import ClearOut.utils.Utils;

public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	private int[][] entity;
	private int exitX,exitY;
	//Entities
	private EntityManager entityManager;
	// Item
	public Player player; 
	private ItemManager itemManager;
	
	private Exit exit;
	
	public int boxNum;
	
	public boolean hasGun,hasKnife;
	
	public Enemy[] enemy;
	
	public World(Handler handler, String path){
		this.handler = handler;
		player=new Player(handler);
		entityManager = new EntityManager(handler, player);
		itemManager = new ItemManager(handler);
		Item.makeFruits();
		
		boxNum=0;
		
		hasGun=false;
                hasKnife=false;
		
		loadWorld(path);
		
		EntityAdd();
		
		exit=new Exit(handler,exitX*64,exitY*64);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}
	
	private void EntityAdd() {
		for(int y = 0;y < height;y++){
			for(int x = 0;x < width;x++){
				Entity e=getEntity(x,y);
				if(e!=null)entityManager.addEntity(e);
			}
		}
		for(int i=0;i<enemy.length;i++)
		{
			entityManager.addEntity(enemy[i]);
		}
	}

	public void tick(){
		exit.tick();
		itemManager.tick();
		entityManager.tick();

		if(player.bomb!=null) {player.bomb.tick(); if(!player.bomb.isActive()) player.bomb=null;}
		// Inventory
                player.inventory.tick();
	}
	
	public void render(Graphics g){
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		
		for(int y = yStart;y < yEnd;y++){
			for(int x = xStart;x < xEnd;x++){
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		//exit
		exit.render(g);
		// Items
		itemManager.render(g);
		//Entities
		entityManager.render(g);
		//bomb
		if(player.bomb!=null)player.bomb.render(g);
		//System.out.println(boxNum);
                 // Inventory
                player.inventory.render(g);
	}
	public Entity getEntity(int x,int y)
	{
		if(entity[x][y]==2) return null;
		if(entity[x][y]==1) return new Rock(handler,x*64,y*64);
		if(entity[x][y]==4) return new Tree(handler,x*64,(y-1)*64);
		if(entity[x][y]==5) {boxNum++;return new WallSp(handler,x*64,y*64);}
                if(entity[x][y]==6) return new Rock2(handler,x*64,y*64);
		if(entity[x][y]==7) return new Tree2(handler,x*64,(y-1)*64);
                
                if(entity[x][y]==8) return new Wall(handler,x*64,y*64,0,0);
                if(entity[x][y]==3) return new Wall(handler,x*64,y*64,0,1);
                if(entity[x][y]==9) return new Wall(handler,x*64,y*64,0,2);
                
                
		return null;
	}
	
	public Tile getTile(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.grassTile;
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null)
			return Tile.dirtTile;
		return t;
	}
	
	private void loadWorld(String path){
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		int tokenIn=4;
		tiles = new int[width][height];
		for(int y = 0;y < height;y++){
			for(int x = 0;x < width;x++){
				tiles[x][y] = Utils.parseInt(tokens[tokenIn++]);
			}
		}
		
		exitX=Utils.parseInt(tokens[tokenIn++]);
		exitY=Utils.parseInt(tokens[tokenIn++]);
		
		entity=new int[width][height];
		for(int y = 0;y < height;y++){
			for(int x = 0;x < width;x++){
				entity[x][y] = Utils.parseInt(tokens[tokenIn++]);
			}
		}
		
		int siz=Utils.parseInt(tokens[tokenIn++]);
		enemy=new Enemy[siz];
		for(int i=0;i<siz;i++)
		{
			enemy[i]=new Enemy(handler,Utils.parseInt(tokens[tokenIn++]),Utils.parseInt(tokens[tokenIn++]));
		}
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}
	
}








