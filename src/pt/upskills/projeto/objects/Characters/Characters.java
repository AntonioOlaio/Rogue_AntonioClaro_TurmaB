package pt.upskills.projeto.objects.Characters;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.FireBall;
import pt.upskills.projeto.objects.Wall;
import pt.upskills.projeto.rogue.utils.Position;

public abstract class Characters {
    int health = 8;
    private Position position;

    public int getHealth(){return health;}

    public Position getPosition() {
        return position;
    }

    public boolean itsAvailable(Position newPosition){
        for (ImageTile tile: Engine.tiles){
            if (tile instanceof Hero || tile instanceof Wall || tile instanceof Enemy){
                if (newPosition.equals(tile.getPosition())){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean damageOnHero(Position newPosition, int amount) { // dano dos Enemy no Hero
        for (ImageTile tile : Engine.tiles) {
            if (tile instanceof Hero) {
                if (newPosition.equals(tile.getPosition())) {
                    ((Hero) tile).damage();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean damageOnEnemy(Position newPosition, int amount) { // dano do Hero nos Enemy
        for (ImageTile tile : Engine.tiles) {
            if (tile instanceof Enemy) {
                if (newPosition.equals(tile.getPosition())) {
                    ((Enemy) tile).goodDamage(2);
                    return true;
                }
            }
        }return false;
    }
}
