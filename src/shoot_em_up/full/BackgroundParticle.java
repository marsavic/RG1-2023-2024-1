package shoot_em_up.full;

import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import mars.drawingx.drawing.View;
import mars.geometry.Vector;
import topic9_particle_systems.particles.Particle;


public class BackgroundParticle extends Particle {
	double t;
	Vector p0, v;
	Vector l;
	double opacity;
	
	
	
	public BackgroundParticle(double t, Vector p, Vector v, double l, double brightness) {
		this.t = t;
		this.p0 = p;
		this.v = v;
		this.l = new Vector(0, l);
		this.opacity = brightness;
	}

	
	private Vector positionAt(double time) {
		double td = time - t;
		return p0.add(v.mul(td));
	}

	
	@Override
	public void draw(View view, double time) {
		Vector p = positionAt(time);
		
		view.setStroke(Color.hsb(0, 0, 1, opacity));
		view.setLineWidth(12);
		view.setLineCap(StrokeLineCap.ROUND);
		view.strokeLine(p, p.add(l));
	}
	

	@Override
	public boolean isAlive(double time) {
		return positionAt(time).add(l).y > - 500;
	}
	
}
