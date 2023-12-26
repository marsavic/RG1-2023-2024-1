package shoot_em_up.full;

import javafx.scene.paint.Color;
import mars.drawingx.drawing.View;
import mars.geometry.Vector;
import mars.utils.Graphics;
import mars.utils.Numeric;


public class Enemy extends Body {
	static final double T_ROTATION = 1.0;
	static final double T_PULSE = 2.0;
	static final int N_SPHERES = 3;
	static final double R_SPHERE = 6.0;
	
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
		Vector position = positionAt(time);
		
		double alfa = time / T_ROTATION;

		view.setFill(Graphics.scaleOpacity(COLOR, 0.2));
		view.fillCircleCentered(position, r + 1.4 * R_SPHERE * (0.5 + 0.5 * Numeric.sinT(time /T_PULSE)));

		view.setFill(Graphics.scaleOpacity(COLOR, 0.4));
		view.fillCircleCentered(position, r);

		view.setFill(Graphics.scaleOpacity(COLOR, 0.7));
		
		for (int i = 0; i < N_SPHERES; i++) {
			double phi = alfa + 1.0 * i / N_SPHERES;
			
			Vector p = position.add(Vector.polar(r, phi));
			
			view.fillCircleCentered(p, R_SPHERE);
		}
		
	}
	
}
