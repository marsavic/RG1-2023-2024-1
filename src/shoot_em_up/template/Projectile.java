package shoot_em_up.template;

import javafx.scene.paint.Color;
import mars.drawingx.drawing.View;
import mars.geometry.Vector;


public class Projectile extends Body {
	
	private boolean finished = false;
	
	
	public Projectile(Ship ship, double time) {
		super(
				time,
				ship.positionAt(time).add(new Vector(0, ship.r)),
				new Vector(0, 400),
				2
		);
	}

	
	public void finish() {
		if (!finished) {
			finished = true;
		}
	}
	
	
	public boolean isFinished() {
		return finished;
	}
	
	
	@Override
	public void draw(View view, double time) {
		// TODO

		Vector position = positionAt(time);

		view.setFill(Color.hsb(0, 0, 1));
		view.fillCircleCentered(position, r);
	}
	
}
