package shoot_em_up.template;

import javafx.scene.paint.Color;
import mars.drawingx.drawing.View;
import mars.geometry.Vector;


public class Enemy extends Body {
	static final Color COLOR = Color.hsb(150, 0.8, 0.96);
	
	
	
	public Enemy(double time) {
		super(
				time,
				new Vector(-350 + 700 * Math.random(), 425),
				new Vector(0, (-30 - 30 * Math.random()) * (1 + time / 20)),
				15
				);
	}
	
	
	@Override
	public void draw(View view, double time) {
		// TODO

		Vector p = positionAt(time);

		view.setFill(COLOR);
		view.fillCircleCentered(p, r);
	}
	
}
