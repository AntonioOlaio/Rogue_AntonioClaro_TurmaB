package pt.upskills.projeto.objects.Characters;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.game.FireBallThread;
import pt.upskills.projeto.gui.ImageMatrixGUI;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.FireBall;
import pt.upskills.projeto.rogue.utils.Direction;
import pt.upskills.projeto.rogue.utils.Position;

import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

public class Hero extends Characters implements ImageTile, Observer {

    private static final int HERO_DAMAGE = 1;

    private Position position;
    private Direction lastDirection = Direction.RIGHT;
    private int fireBalls = INITIAL_NUMBERS_OF_FIREBALLS;
    private static final int INITIAL_NUMBERS_OF_FIREBALLS = 3;

    public Hero(Position position) {
        this.position = position;
    }

    public int getFireBalls() {
        return fireBalls;
    }

    public void setFireBalls(int fireBalls) {
        this.fireBalls = fireBalls;
    }

    public void damage() {
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        this.health -= 1;
        System.out.println("Vida do Hero: "+health);
        //update ao health bar
        Engine.createStatusMenu();
        if (health <= 0) {
            Engine.tiles.remove(this);
            gui.deleteObserver(this);
            gui.removeImage(this);
        }
    }

    @Override
    public String getName() {
        return "Hero";
    }

    @Override
    public Position getPosition() {
        return position;
    }


    /**
     * This method is called whenever the observed object is changed. This function is called when an
     * interaction with the graphic component occurs {{@link ImageMatrixGUI}}
     * @param o
     * @param arg
     */

    @Override
    public void update(Observable o, Object arg) {

        Position newPosition = null;

        Integer keyCode = (Integer) arg;
        if (keyCode == KeyEvent.VK_DOWN) {
            if (getPosition().getY() <= 8) {
                newPosition = position.plus(Direction.DOWN.asVector());
            }
        }
        if (keyCode == KeyEvent.VK_UP) {
            if (getPosition().getY() > 0) {
                newPosition = position.plus(Direction.UP.asVector());
            }
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            if (getPosition().getX() > 0) {
                newPosition = position.plus(Direction.LEFT.asVector());
            }
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            if (getPosition().getX() <= 8) {
                newPosition = position.plus(Direction.RIGHT.asVector());
            }
        }
        if (keyCode == KeyEvent.VK_DOWN){
            lastDirection = Direction.DOWN;
        } else if (keyCode == KeyEvent.VK_UP) {
            lastDirection = Direction.UP;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            lastDirection = Direction.RIGHT;
        } else if (keyCode == KeyEvent.VK_LEFT) {
            lastDirection = Direction.LEFT;
        }

        if (keyCode == KeyEvent.VK_SPACE) {
            if (fireBalls > 0) {
                fireBalls -= 1;
                Engine.createStatusMenu();
                FireBall fireBall = new FireBall(position);
                FireBallThread fireBallThread = new FireBallThread(lastDirection, fireBall);
                fireBallThread.start();
                Engine.tiles.add(fireBall);
                ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
                gui.addImage(fireBall);
            }
        }

        if(newPosition != null) {
            if (!damageOnEnemy(newPosition, HERO_DAMAGE) && (itsAvailable(newPosition))) {
                position = newPosition;
            }
        }
    }
}
