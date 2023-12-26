package flappy_bird;

import javafx.scene.paint.Color;
import mars.drawingx.drawing.View;
import mars.geometry.Vector;

class Bird extends Thing {
	public final double r = 30;
	
	double dir = 1;
	Vector p = Vector.ZERO;
	Vector v = new Vector(100, 0);
	Vector a = new Vector(0, 1200);
	
	@Override
	public void draw(View view, double t) {
		view.setFill(Color.hsb(60, 0.8, 0.8));
		view.fillCircleCentered(p, r);
	}
	
	public void update(double dt) {
		p = p.add(v.mul(dt)).add(a.mul(dir * dt * dt / 2));
		v = v.add(a.mul(dir * dt));
	}
	
	public void setDir(double dir) {
		this.dir = dir;
		v = v.add(new Vector(5, 0));
	}
}
