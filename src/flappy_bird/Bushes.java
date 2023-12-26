package flappy_bird;

import javafx.scene.paint.Color;
import mars.drawingx.drawing.View;
import mars.geometry.Vector;
import mars.random.RNG;

import java.util.ArrayList;
import java.util.List;

public class Bushes extends Thing {
	
	record Bush(
			Vector p,
			double r,
			Color c
	) {}
	
	private final double depth;
	private final List<Bush> bushes = new ArrayList<>();
	
	public Bushes(double depth, double yOffset) {
		this.depth = depth;
		RNG rng = new RNG();
		for (int i = -100; i <= 100; i++) {
			bushes.add(new Bush(
					new Vector(200 * i, -(rng.nextDouble(300, 350) + yOffset)),
					rng.nextDouble(50, 100),
					Color.hsb(120, rng.nextDouble(0.5, 0.8), rng.nextDouble(0.3, 0.5))
			));
		}
	}
	
	@Override
	public void draw(View view, double t) {
		for (Bush m : bushes) {
			view.setFill(m.c);
			view.fillCircleCentered(m.p, m.r);
		}
	}
	
	@Override
	public double depth() {
		return depth;
	}
	
}
