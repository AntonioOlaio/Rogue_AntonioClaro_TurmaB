package pt.upskills.projeto.objects.Characters;


import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.ImageMatrixGUI;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;
import java.util.Observer;

public abstract class Enemy extends Characters implements Observer, ImageTile {

    public int getHealth() {
        return health;
    }

    public void goodDamage(int amount) {
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        this.health -= amount;
        System.out.println("Vida do Enemy: "+health);
        if (health <= 0) {
            Engine.tiles.remove(this);
            gui.deleteObserver(this);
            gui.removeImage(this);
        }
    }

    int randomNum(){
        int num;
        if(Math.random() <= 0.33)
            num = -1;
        else if (Math.random() > 0.33 && Math.random() <= 0.66)
            num = 0;
        else
            num = 1;
        return num;
    }

    public boolean isInRange(Position position) {
        for (ImageTile tile : Engine.tiles) {
            if (tile instanceof Hero) {
                Position heroPosition = tile.getPosition();
                while (Math.sqrt(((position.getX() - heroPosition.getX())*(position.getX() - heroPosition.getY()))
                        + ((position.getY() - heroPosition.getY()) * (position.getY() - heroPosition.getY())))<=3){
                    return true;
                }
            }
        }return false;
    }
}


