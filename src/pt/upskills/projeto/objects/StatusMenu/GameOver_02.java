package pt.upskills.projeto.objects.StatusMenu;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class GameOver_02 implements ImageTile {
    private Position position;

    public GameOver_02(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "GameOver_02";
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
