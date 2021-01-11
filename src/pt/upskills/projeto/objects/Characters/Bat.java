package pt.upskills.projeto.objects.Characters;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Direction;
import pt.upskills.projeto.rogue.utils.Position;
import pt.upskills.projeto.rogue.utils.Vector2D;

import java.util.Observable;
import java.util.Observer;

public class Bat extends Enemy implements ImageTile, Observer {
    private Position position;
    private static final int BAT_DAMAGE = 1;

    public Bat(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "Bat";
    }

    @Override
    public Position getPosition() {
        return position;
    }


    @Override
    public void update(Observable o, Object arg) {

        Position newPosition = null;

        if (isInRange(position)) {
            for (ImageTile tile : Engine.tiles) {
                if (tile instanceof Hero) {
                    Position heroPosition = tile.getPosition();
                    if (heroPosition.getX() > getPosition().getX()) {
                        newPosition = position.plus(Direction.RIGHT.asVector());
                    } else if (heroPosition.getX() < getPosition().getX()) {
                        newPosition = position.plus(Direction.LEFT.asVector());
                    } else if (heroPosition.getY() > getPosition().getY()) {
                        newPosition = position.plus(Direction.DOWN.asVector());
                    } else if (heroPosition.getY() < getPosition().getY()) {
                        newPosition = position.plus(Direction.UP.asVector());
                    }
                }
            }
        } else { newPosition = position.plus(new Vector2D(randomNum(), 0)); }

        if (!damageOnHero(newPosition, BAT_DAMAGE) && itsAvailable(newPosition)){
            position = newPosition;
        }
    }
}
