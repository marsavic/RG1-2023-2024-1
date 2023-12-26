package topic9_particle_systems.fountain;

import javafx.scene.paint.Color;
import mars.drawingx.drawing.View;
import mars.geometry.Vector;
import topic9_particle_systems.particles.Particle;


public class Drop extends Particle {
	double t0;
	Vector p0, v0, a;
	double r;
	double yFloor;
	Color color;
	
	
	
	public Drop(double t0, Vector p0, Vector v0, Vector a, double r, double yFloor, Color color) {
		this.t0 = t0;
		this.p0 = p0;
		this.v0 = v0;
		this.a = a;
		this.r = r;
		this.yFloor = yFloor;
		this.color = color;
	}

	
	private Vector getPosition(double time) {
		double tD = time - t0;
		return p0.add(v0.mul(tD)).add(a.mul(tD*tD/2));
	}

	
	@Override
	public void draw(View view, double time) {
		Vector p = getPosition(time);
		view.setFill(color);
		view.fillCircleCentered(p, r);
	}

	
	@Override
	public boolean isAlive(double time) {
		return getPosition(time).y >= yFloor;
	}
	
}
