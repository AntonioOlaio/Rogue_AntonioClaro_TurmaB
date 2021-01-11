package pt.upskills.projeto.game;

import pt.upskills.projeto.gui.ImageMatrixGUI;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.*;
import pt.upskills.projeto.objects.Characters.Hero;
import pt.upskills.projeto.objects.Characters.Skeleton;
import pt.upskills.projeto.objects.StatusMenu.*;
import pt.upskills.projeto.rogue.utils.Position;
import pt.upskills.projeto.objects.Characters.Bat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Engine {
    private static Hero hero;
    public static List<ImageTile> tiles = new ArrayList<>();
    public static List<ImageTile> statusTiles = new ArrayList<>();

    public static List<ImageTile> getTilesAt(Position position){
        List<ImageTile> tilesAt = new ArrayList<>();
        for (ImageTile tile: tiles) {
            if (tile.getPosition().equals(position)) {
                tilesAt.add(tile);
            }
        }
        return tilesAt;
    }

    public void init() {
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();

        createFloor();
        readMapFile(0);
        createStatusMenu();

        gui.newImages(tiles);

        gui.go();

        while (true){
            gui.update();
        }
    }

    public void createFloor() {
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                tiles.add(new Floor(new Position(j, i)));
            }
        }
    }

    public static void createStatusMenu() {
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        gui.clearStatus();
        for (int i = 0; i < 10; i++) {
            statusTiles.add(new BlackTile(new Position(i, 0)));
        }
        fireBallMenu();
        heroHealthMenu();
        gui.newStatusImages(statusTiles);
    }

    private static void fireBallMenu() {
        for (int i = 0; i < hero.getFireBalls(); i++){
            statusTiles.add(new FireBall(new Position(i, 0)));
        }
    }

    private static void heroHealthMenu() {
        if(hero.getHealth() == 8) {
            for (int i = 3; i < 7; i++){
                statusTiles.add(new GreenTile(new Position(i, 0)));
            }
        } else if (hero.getHealth() == 7) {
            for (int i = 3; i < 6; i++){
                statusTiles.add(new GreenTile(new Position(i, 0)));
            }
            for (int i = 6; i < 7; i++){
                statusTiles.add(new RedGreenTile(new Position(i, 0)));
            }
        } else if (hero.getHealth() == 6) {
            for (int i = 3; i < 6; i++){
                statusTiles.add(new GreenTile(new Position(i, 0)));
            }
            for (int i = 6; i < 7; i++){
                statusTiles.add(new RedTile(new Position(i, 0)));
            }
        } else if (hero.getHealth() == 5) {
            for (int i = 3; i < 5; i++){
                statusTiles.add(new GreenTile(new Position(i, 0)));
            }
            for (int i = 5; i < 6; i++){
                statusTiles.add(new RedGreenTile(new Position(i, 0)));
            }
            for (int i = 6; i < 7; i++){
                statusTiles.add(new RedTile(new Position(i, 0)));
            }
        } else if (hero.getHealth() == 4) {
            for (int i = 3; i < 5; i++){
                statusTiles.add(new GreenTile(new Position(i, 0)));
            }
            for (int i = 5; i < 7; i++){
                statusTiles.add(new RedTile(new Position(i, 0)));
            }
        } else if (hero.getHealth() == 3) {
            for (int i = 3; i < 4; i++) {
                statusTiles.add(new GreenTile(new Position(i, 0)));
            }
            for (int i = 4; i < 5; i++) {
                statusTiles.add(new RedGreenTile(new Position(i, 0)));
            }
            for (int i = 5; i < 7; i++) {
                statusTiles.add(new RedTile(new Position(i, 0)));
            }
        } else if (hero.getHealth() == 2) {
            for (int i = 3; i < 4; i++) {
                statusTiles.add(new GreenTile(new Position(i, 0)));
            }
            for (int i = 4; i < 7; i++) {
                statusTiles.add(new RedTile(new Position(i, 0)));
            }
        } else if (hero.getHealth() == 1) {
            for (int i = 3; i < 4; i++) {
                statusTiles.add(new RedGreenTile(new Position(i, 0)));
            }
            for (int i = 4; i < 7; i++) {
                statusTiles.add(new RedTile(new Position(i, 0)));
            }
        } else {
            statusTiles.add(new GameOver_01(new Position(3, 0)));
            statusTiles.add(new GameOver_02(new Position(4, 0)));
            statusTiles.add(new GameOver_03(new Position(5, 0)));
            statusTiles.add(new GameOver_04(new Position(6, 0)));
            }
    }

    public void readMapFile(int level) {
        try {
            File file = new File("rooms/room" + level + ".txt");
            Scanner fileScanner = new Scanner(file);
            int i = 0;
            while(fileScanner.hasNextLine()) {
                String nextLine = fileScanner.nextLine();
                String[] tokens = nextLine.split("");
                // if(tokens[i]=="#"){ }
                System.out.println(Arrays.toString(tokens));
                for (int j = 0; j < tokens.length; j++) {
                    switch (tokens[j]) {
                        case "h":
                            hero = new Hero(new Position(j, i));
                            tiles.add(hero);
                            ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
                            gui.addObserver(hero);
                            break;
                        case "S":
                            Skeleton skeleton = new Skeleton(new Position(j, i));
                            tiles.add(skeleton);
                            ImageMatrixGUI gui2 = ImageMatrixGUI.getInstance();
                            gui2.addObserver(skeleton);
                            break;
                        case "W":
                            tiles.add(new Wall(new Position(j, i)));
                            break;
                        case "0":
                            tiles.add(new DoorOpen(new Position(j, i)));
                            break;
                        case "b":
                            Bat bat = new Bat(new Position(j, i));
                            tiles.add(bat);
                            ImageMatrixGUI gui3 = ImageMatrixGUI.getInstance();
                            gui3.addObserver(bat);
                            break;
                    }
                }i++;
            }
            fileScanner.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Engine engine = new Engine();
        engine.init();
    }
}