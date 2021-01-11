package pt.upskills.projeto.objects.StatusMenu;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class RedTile implements ImageTile {
    private Position position;

    public RedTile(Position position) {
        this.position = position;
    }


    @Override
    public String getName() {
        return "Red";
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
