package pt.upskills.projeto.objects.StatusMenu;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class GameOver_03 implements ImageTile {
    private Position position;

    public GameOver_03(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "GameOver_03";
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
