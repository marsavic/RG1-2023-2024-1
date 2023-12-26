package flappy_bird;

import javafx.scene.paint.Color;
import mars.drawingx.drawing.View;
import mars.geometry.Vector;
import mars.random.RNG;

import java.util.ArrayList;
import java.util.List;

public class Trees extends Thing {
	
	record Tree(
			Vector p,
			Color c
	) {}
	
	private final double depth;
	private final List<Tree> trees = new ArrayList<>();
	
	public Trees(double depth, double yOffset) {
		this.depth = depth;
		RNG rng = new RNG();
		for (int i = -500; i <= 500; i++) {
			trees.add(new Tree(
					new Vector(600 * i, yOffset + rng.nextDouble(-800, 1600)),
					Color.hsb(160, rng.nextDouble(0.3, 0.5), rng.nextDouble(0.2, 0.3))
			));
		}
	}
	
	@Override
	public void draw(View view, double t) {
		for (Tree m : trees) {
			view.setFill(m.c);
			view.fillPolygon(
					m.p.add(new Vector(-1000, -4000)),
					m.p,
					m.p.add(new Vector( 1000, -4000))
			);
		}
	}
	
	@Override
	public double depth() {
		return depth;
	}
	
}
