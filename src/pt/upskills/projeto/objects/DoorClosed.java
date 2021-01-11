package pt.upskills.projeto.objects;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class DoorClosed implements ImageTile {

    private Position position;

    public DoorClosed(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "DoorClosed";
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
