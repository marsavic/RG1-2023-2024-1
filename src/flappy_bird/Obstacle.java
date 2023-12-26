package flappy_bird;

import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import mars.drawingx.drawing.View;
import mars.geometry.Vector;

class Obstacle extends Thing {
	final Vector c;
	final double rHole;
	
	final Vector vecRHole;
	final Vector dWall = new Vector(0, 10000);
	final double rWall = 80;
	
	
	Obstacle(Vector c, double rHole) {
		this.c = c;
		this.rHole = rHole;
		
		vecRHole = new Vector(0, rHole);
	}
	
	
	Vector[] os() {
		return new Vector[]{
				c.add(vecRHole),
				c.sub(vecRHole)
		};
	}
	
	@Override
	public void draw(View view, double t) {
		view.setStroke(Color.hsb(300, 0.5, 0.5));
		view.setLineCap(StrokeLineCap.ROUND);
		view.setLineWidth(2 * rWall);
		view.strokeLine(os()[0], os()[0].add(dWall));
		view.strokeLine(os()[1], os()[1].sub(dWall));
	}
	
	
	boolean crash(Bird bird)  {
		if (bird.p.y > os()[0].y & Math.abs(os()[0].x - bird.p.x) < rWall + bird.r) return true;
		if (bird.p.y < os()[1].y & Math.abs(os()[1].x - bird.p.x) < rWall + bird.r) return true;
		for (Vector o : os()) {
			if (o.sub(bird.p).norm() < rWall + bird.r) return true;
		}
		return false;
	}
}
