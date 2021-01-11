package pt.upskills.projeto.objects;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.FireTile;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.Characters.Enemy;
import pt.upskills.projeto.rogue.utils.Position;

public class FireBall  implements FireTile {
    private Position position;

    public FireBall(Position position) {
        this.position = position;
    }

    @Override
    public boolean validateImpact() { //de validar se a bola toca em outro tile
        for (ImageTile tile : Engine.tiles) {
            if (tile instanceof Wall || tile instanceof Enemy) {
                if (tile.getPosition().equals(position)) {
                    return false;
                }
            }
        }return true;
    }

        @Override
        public void setPosition (Position position){
            this.position = position;
        }

        @Override
        public String getName () {
            return "Fire";
        }

        @Override
        public Position getPosition () {
            return this.position;
        }
}
