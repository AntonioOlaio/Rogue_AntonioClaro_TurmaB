package pt.upskills.projeto.objects.StatusMenu;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class GameOver_04 implements ImageTile {
    private Position position;

    public GameOver_04(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "GameOver_04";
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
