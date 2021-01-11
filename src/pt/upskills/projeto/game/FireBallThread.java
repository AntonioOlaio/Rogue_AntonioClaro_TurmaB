package pt.upskills.projeto.game;

import pt.upskills.projeto.gui.FireTile;
import pt.upskills.projeto.gui.ImageMatrixGUI;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.Characters.Enemy;
import pt.upskills.projeto.rogue.utils.Direction;
import pt.upskills.projeto.rogue.utils.Position;

import java.util.List;

public class FireBallThread extends Thread {

    private Direction direction;
    private FireTile fireTile;

    public FireBallThread(Direction direction, FireTile fireTile) {
        this.direction = direction;
        this.fireTile = fireTile;
    }

    @Override
    public void run() {
        boolean control = true;
        while (control) {
            Position nextPosition = fireTile.getPosition().plus(direction.asVector());
            fireTile.setPosition(nextPosition);
            try {
                if (fireTile.validateImpact()) {
                    // FireBall continue
                    sleep(300);
                }else{
                    // FireBall should explode and stop is job
                    List<ImageTile> tilesAtFireBall = Engine.getTilesAt(fireTile.getPosition());
                    for (ImageTile tile: tilesAtFireBall){
                        if (tile instanceof Enemy){
                            ((Enemy) tile).goodDamage(Integer.MAX_VALUE);
                        }
                    }
                    sleep(500);
                    control = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Remove FireBall of {ImageMatrixGUI}
        ImageMatrixGUI.getInstance().removeImage(fireTile);
    }
}
