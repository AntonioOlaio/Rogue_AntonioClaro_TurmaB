package pt.upskills.projeto.objects.ObtainableObject;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class Sword extends ObtainableObject implements ImageTile {
    private Position position;

    public Sword(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "Sword";
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
