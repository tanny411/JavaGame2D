package ClearOut.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 32, height = 32;

    public static Font font28;

    public static BufferedImage menuScreen, pauseScreen, helpScreen, overScreen, levelScreen;

    public static BufferedImage nextLevel;

    public static BufferedImage dirt, grass, stone, tree, rock, wood;

    public static BufferedImage[] player_down, player_up, player_left, player_right;
    public static BufferedImage[] zombie_down, zombie_up, zombie_left, zombie_right;

    public static BufferedImage inventoryScreen;

    public static BufferedImage btnPlay, btnHelp, btnOptions, btnQuit, btnScore;

    public static BufferedImage pause, play, soundOn, soundOff, options, help, cross;

    public static BufferedImage replay, menu;

    public static BufferedImage[] wall, wallSp;
    
    public static BufferedImage walltm,walltl,walltr,walll,wallr,wallm;

    public static BufferedImage[] bombMid, bombHor, bombEndr, bombEndl, bombVer, bombEndu, bombEndd;

    public static BufferedImage bomb, gun, run;
    public static BufferedImage[] knife;
    public static BufferedImage[] fruits;

    public static BufferedImage exit;

    public static BufferedImage bulletUP, bulletDOWN, bulletLEFT, bulletRIGHT;

    public static BufferedImage bombPow, bombExtra;

    public static BufferedImage rock2, tree2,grass2,smallRock;
    
    public static BufferedImage gametitle;
    
    public static void init() {
        font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));

        inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");

        gametitle = ImageLoader.loadImage("/nameTitle.png");
        
        btnPlay = ImageLoader.loadImage("/Game menu buttons/play button.png");
        btnHelp = ImageLoader.loadImage("/Game menu buttons/Help button.png");
        btnQuit = ImageLoader.loadImage("/Game menu buttons/Quit button 2.png");
        btnScore = ImageLoader.loadImage("/Game menu buttons/Score button.png");
        btnOptions = ImageLoader.loadImage("/Game menu buttons/Option button.png");

        pause = ImageLoader.loadImage("/game state buttons/b_Pause3.png");
        play = ImageLoader.loadImage("/game state buttons/b_Play2.png");
        options = ImageLoader.loadImage("/game state buttons/b_Parameters.png");
        soundOn = ImageLoader.loadImage("/game state buttons/b_Sound1.png");
        soundOff = ImageLoader.loadImage("/game state buttons/b_Sound1_Inactive.png");
        help = ImageLoader.loadImage("/game state buttons/b_More2.png");
        cross = ImageLoader.loadImage("/game state buttons/b_No.png");

        menuScreen = ImageLoader.loadImage("/menuScreen.jpg");
        pauseScreen = ImageLoader.loadImage("/pauseScreen.png");
        helpScreen = ImageLoader.loadImage("/helpmenu.png");

        wood = sheet.crop(width, height, width, height);

        player_down = new BufferedImage[3];
        player_up = new BufferedImage[3];
        player_left = new BufferedImage[3];
        player_right = new BufferedImage[3];

        SpriteSheet spriteNew = new SpriteSheet(ImageLoader.loadImage("/sheet3new.png"));
        SpriteSheet ss = new SpriteSheet(ImageLoader.loadImage("/New/ss.png"));

        player_down[0] = spriteNew.crop(width * 0, 2 * height, width, 2 * height);
        player_down[1] = spriteNew.crop(width * 1, 2 * height, width, 2 * height);
        player_down[2] = spriteNew.crop(width * 2, 2 * height, width, 2 * height);
        player_up[0] = spriteNew.crop(width * 0, 0, width, 2 * height);
        player_up[1] = spriteNew.crop(width * 1, 0, width, 2 * height);
        player_up[2] = spriteNew.crop(width * 2, 0, width, 2 * height);

        player_left[0] = spriteNew.crop(width * 3, 0, width, 2 * height);
        player_left[1] = spriteNew.crop(width * 4, 0, width, 2 * height);
        player_left[2] = spriteNew.crop(width * 5, 0, width, 2 * height);
        player_right[0] = spriteNew.crop(width * 3, 2 * height, width, 2 * height);
        player_right[1] = spriteNew.crop(width * 4, 2 * height, width, 2 * height);
        player_right[2] = spriteNew.crop(width * 5, 2 * height, width, 2 * height);

        zombie_down = new BufferedImage[3];
        zombie_up = new BufferedImage[3];
        zombie_left = new BufferedImage[3];
        zombie_right = new BufferedImage[3];

        SpriteSheet Ghost = new SpriteSheet(ImageLoader.loadImage("/New/ghost.png"));
        
        zombie_down[0] = Ghost.crop(0, 0, 20, 20);
        zombie_down[1] = Ghost.crop(20,0, 20, 20);
        zombie_down[2] = Ghost.crop(2*20,0, 20, 20);
        zombie_up[0] = Ghost.crop(3*20,0, 20, 20);
        zombie_up[1] = Ghost.crop(4*20,0, 20, 20);
        zombie_up[2] = Ghost.crop(5*20,0, 20, 20);
        zombie_right[0] = Ghost.crop(9*20,0, 20, 20);
        zombie_right[1] = Ghost.crop(10*20,0, 20, 20);
        zombie_right[2] = Ghost.crop(11*20,0, 20, 20);
        zombie_left[0] = Ghost.crop(6*20,0, 20, 20);
        zombie_left[1] = Ghost.crop(7*20,0, 20, 20);
        zombie_left[2] = Ghost.crop(8*20,0, 20, 20);

        dirt = sheet.crop(width, 0, width, height);
       // dirt = ss.crop(64 * 2, 64*5, 64, 63);
        grass = sheet.crop(width * 2, 0, width, height);
        grass2 = ImageLoader.loadImage("/New/363_tile_Grass.jpg");
        stone = sheet.crop(width * 3, 0, width, height);
         
        tree = sheet.crop(0, 0, width, height * 2);
        tree2=ImageLoader.loadImage("/New/leafTree.png");
        rock = sheet.crop(0, height * 2, width, height);
        rock2 = ss.crop(64 * 2, 64*4, 64, 63);
        SpriteSheet bomberSprite = new SpriteSheet(ImageLoader.loadImage("/textures/BomberManEffect.png"));
        SpriteSheet bomberSprite2 = new SpriteSheet(ImageLoader.loadImage("/textures/blasteffects.png"));

        bombMid = new BufferedImage[7];
        bombMid[0] = bomberSprite.crop(0, 0, 48, 48);
        bombMid[1] = bomberSprite.crop(48, 0, 48, 48);
        bombMid[2] = bomberSprite.crop(2 * 48, 0, 48, 48);
        bombMid[3] = bomberSprite.crop(3 * 48, 0, 48, 48);
        bombMid[4] = bomberSprite.crop(4 * 48, 0, 48, 48);
        bombMid[5] = bomberSprite.crop(5 * 48, 0, 48, 48);
        bombMid[6] = bomberSprite.crop(6 * 48, 0, 48, 48);

        bombHor = new BufferedImage[7];
        bombHor[0] = bomberSprite.crop(0, 48, 48, 43);
        bombHor[1] = bomberSprite.crop(48, 48, 48, 43);
        bombHor[2] = bomberSprite.crop(2 * 48, 48, 48, 43);
        bombHor[3] = bomberSprite.crop(3 * 48, 48, 48, 43);
        bombHor[4] = bomberSprite.crop(4 * 48, 48, 48, 43);
        bombHor[5] = bomberSprite.crop(5 * 48, 48, 48, 43);
        bombHor[6] = bomberSprite.crop(6 * 48, 48, 48, 43);

        bombEndr = new BufferedImage[7];
        bombEndr[0] = bomberSprite.crop(0, 91, 48, 43);
        bombEndr[1] = bomberSprite.crop(48, 91, 48, 43);
        bombEndr[2] = bomberSprite.crop(2 * 48, 91, 48, 43);
        bombEndr[3] = bomberSprite.crop(3 * 48, 91, 48, 43);
        bombEndr[4] = bomberSprite.crop(4 * 48, 91, 48, 43);
        bombEndr[5] = bomberSprite.crop(5 * 48, 91, 48, 43);
        bombEndr[6] = bomberSprite.crop(6 * 48, 91, 48, 43);

        bombEndl = new BufferedImage[7];
        bombEndl[6] = bomberSprite2.crop(0, 87, 51, 45);
        bombEndl[5] = bomberSprite2.crop(51, 87, 47, 45);
        bombEndl[4] = bomberSprite2.crop(98, 87, 48, 45);
        bombEndl[3] = bomberSprite2.crop(146, 87, 48, 45);
        bombEndl[2] = bomberSprite2.crop(194, 87, 48, 45);
        bombEndl[1] = bomberSprite2.crop(242, 87, 48, 45);
        bombEndl[0] = bomberSprite2.crop(290, 87, 46, 45);

        bombEndu = new BufferedImage[7];
        bombEndu[0] = bomberSprite2.crop(0, 132, 48, 40);
        bombEndu[1] = bomberSprite2.crop(1 * 48, 132, 48, 40);
        bombEndu[2] = bomberSprite2.crop(2 * 48, 132, 48, 40);
        bombEndu[3] = bomberSprite2.crop(3 * 48, 132, 48, 40);
        bombEndu[4] = bomberSprite2.crop(4 * 48, 132, 48, 40);
        bombEndu[5] = bomberSprite2.crop(5 * 48, 132, 48, 40);
        bombEndu[6] = bomberSprite2.crop(6 * 48, 132, 48, 40);

        bombEndd = new BufferedImage[7];
        bombEndd[0] = bomberSprite2.crop(0, 240, 48, 49);
        bombEndd[1] = bomberSprite2.crop(1 * 48, 240, 48, 49);
        bombEndd[2] = bomberSprite2.crop(2 * 48, 240, 48, 49);
        bombEndd[3] = bomberSprite2.crop(3 * 48, 240, 48, 49);
        bombEndd[4] = bomberSprite2.crop(4 * 48, 240, 48, 49);
        bombEndd[5] = bomberSprite2.crop(5 * 48, 240, 48, 49);
        bombEndd[6] = bomberSprite2.crop(6 * 48, 240, 48, 49);

        bombVer = new BufferedImage[7];
        bombVer[0] = bomberSprite2.crop(0, 184, 48, 49);
        bombVer[1] = bomberSprite2.crop(1 * 48, 184, 48, 49);
        bombVer[2] = bomberSprite2.crop(2 * 48, 184, 48, 49);
        bombVer[3] = bomberSprite2.crop(3 * 48, 184, 48, 49);
        bombVer[4] = bomberSprite2.crop(4 * 48, 184, 48, 49);
        bombVer[5] = bomberSprite2.crop(5 * 48, 184, 48, 49);
        bombVer[6] = bomberSprite2.crop(6 * 48, 184, 48, 49);

        bomb = ImageLoader.loadImage("/textures/bomb2.png");
        gun = ImageLoader.loadImage("/gun.png");

        wall = new BufferedImage[7];
        
        SpriteSheet boxSprite,boxSprite2;
        boxSprite2= new SpriteSheet(ImageLoader.loadImage("/box.jpg"));
        boxSprite = new SpriteSheet(ImageLoader.loadImage("/New/brickgone.png"));
        //wall[0]=wall[1]=boxSprite2.crop(0,0,64+64,64+64);//(need to make size 2)
        
        wall[0] = boxSprite.crop(0,0,12,12);
        wall[1] = boxSprite.crop(12, 0, 12, 12);
        wall[2] = boxSprite.crop(2 * 12, 0, 12, 12);
        wall[3] = boxSprite.crop(3 * 12, 0, 12, 12);
        wall[4] = boxSprite.crop(4 * 12, 0, 12, 12);
        wall[5] = boxSprite.crop(5 * 12, 0, 12, 12);
        wall[6] = boxSprite.crop(6 * 12, 0, 12, 12);

        wallSp = new BufferedImage[2];

        //wallSp[0]=wallSp[1]=spriteNew.crop(7*32,6*32,32,32);
        wallSp[0] = wallSp[1] = ImageLoader.loadImage("/New/NewBox.png");

        
        walltm=ImageLoader.loadImage("/top_m.png");
        walltl=ImageLoader.loadImage("/top_l_dark.png");
        walltr=ImageLoader.loadImage("/top_r_dark.png");
        walll=ImageLoader.loadImage("/side_l_dark.png");
        wallr=ImageLoader.loadImage("/side_r_dark.png");
        wallm=ImageLoader.loadImage("/middle.png");
        
        
        fruits = new BufferedImage[7];

        fruits[0] = spriteNew.crop(32, 7 * 32, 32, 32);
        fruits[1] = spriteNew.crop(2 * 32, 7 * 32, 32, 32);
        fruits[2] = spriteNew.crop(3 * 32, 7 * 32, 32, 32);
        fruits[3] = spriteNew.crop(4 * 32, 7 * 32, 32, 32);
        fruits[4] = spriteNew.crop(5 * 32, 7 * 32, 32, 32);
        fruits[5] = spriteNew.crop(6 * 32, 7 * 32, 32, 32);
        fruits[6] = spriteNew.crop(7 * 32, 7 * 32, 32, 32);

        knife = new BufferedImage[3];
        knife[0] = spriteNew.crop(32 * 3, 6 * 32, 32, 32);
        knife[1] = spriteNew.crop(32 * 5, 6 * 32, 32, 32);
        knife[2] = spriteNew.crop(32 * 4, 6 * 32, 32, 32);

        overScreen = ImageLoader.loadImage("/overScreen.png");

        replay = ImageLoader.loadImage("/overscreen buttons/Button.png");
        menu = ImageLoader.loadImage("/overscreen buttons/menubtn.png");

        SpriteSheet ExitSprite = new SpriteSheet(ImageLoader.loadImage("/exit.png"));
        exit = ExitSprite.crop(7, 7, 51, 51);

        run = spriteNew.crop(0, 7 * 32 + 2, 32, 30);

        bulletUP = ImageLoader.loadImage("/bulletVer.png");
        bulletDOWN = ImageLoader.loadImage("/bulletVer.png");
        bulletLEFT = ImageLoader.loadImage("/bulletHor.png");
        bulletRIGHT = ImageLoader.loadImage("/bulletHor.png");

        bombPow = ImageLoader.loadImage("/firePower.png");
        bombExtra = ImageLoader.loadImage("/extraBomb.png");

        levelScreen = ImageLoader.loadImage("/levelState.png");

        nextLevel = ImageLoader.loadImage("/next_button.png");
        
        smallRock = ss.crop(3*64, 4*64, 64, 64);
        
        grass=grass2;
    }

}
