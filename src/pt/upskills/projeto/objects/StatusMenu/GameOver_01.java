package pt.upskills.projeto.objects.StatusMenu;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class GameOver_01 implements ImageTile {
    private Position position;

    public GameOver_01(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "GameOver_01";
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
