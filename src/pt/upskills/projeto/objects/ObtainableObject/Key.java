package pt.upskills.projeto.objects.ObtainableObject;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class Key extends ObtainableObject implements ImageTile {
    private Position position;

    public Key(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "Key";
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
