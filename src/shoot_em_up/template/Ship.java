package shoot_em_up.template;

import javafx.scene.paint.Color;
import mars.drawingx.drawing.View;
import mars.geometry.Vector;


public class Ship extends Body {
	static final Color COLOR = Color.hsb(220, 0.80, 0.96);

	
	
	public Ship() {
		super(0, new Vector(0, -300), Vector.ZERO, 20);
	}
	
	
	@Override
	public void draw(View view, double time) {
		// TODO

		Vector p = positionAt(time);

		view.setFill(COLOR);
		view.fillCircleCentered(p, r);
	}
	
}
