package topic9_particle_systems;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.geometry.Vector;
import mars.random.sampling.Sampling;
import mars.utils.Numeric;
import topic9_particle_systems.particles.Particle;
import topic9_particle_systems.particles.ParticleSystemInterval;

import static mars.geometry.Vector.vec;


class Raindrop extends Particle {
	double t, duration;
	Vector p, v;
	double wiggleOffset, wigglePeriod, wiggleAmplitude;
	double size;
	
	
	public Raindrop(double t, Vector p, Vector v) {
		this.t = t;
		this.p = p;
		this.v = v;
		
		// Sampling.uniform(a, b) daje slucajan broj iz [a, b), tj. isto sto i a + Math.random() * (b - a)
		duration        = Sampling.uniform(1, 4);
		wiggleOffset    = Sampling.uniform();
		wigglePeriod    = Sampling.uniform(2, 4);
		wiggleAmplitude = Sampling.uniform(0, 10);
		size            = Sampling.uniform(2, 6);
	}

	@Override
	public void draw(View view, double time) {
		double td = time - t;
		double k = td / duration;
		view.setFill(Color.hsb(210, 0.2, 1, 0.6 * (1-k)));
		view.fillCircleCentered(
				p
				.add(v.mul(td))
				.add(v.perp().normalizedTo(Numeric.sinT(td / wigglePeriod + wiggleOffset) * wiggleAmplitude))
			, size);
	}

	@Override
	public boolean isAlive(double time) {
		return time < t + duration;
	}	
}


class Rain extends ParticleSystemInterval<Raindrop> {
	public Rain(double timeStart, double density) {
		super(timeStart, 1 / density);
	}

	@Override
	protected Raindrop generateNext() {
		return new Raindrop(
				timeNext(),
				vec(Sampling.uniform(-140, 140), 180),
				vec(-20, -100)
		);
	}
}


public class Cloud implements Drawing {
	
	@GadgetAnimation(start = true)
	double time = 0.0;

	
	Rain rain = new Rain(0.0, 50);
	
	
	void drawCloudCircles(View view, double w) {
		view.fillCircleCentered(vec(0, 200), 100 - w);
		view.fillCircleCentered(vec(-90, 180), 70 - w);
		view.fillCircleCentered(vec(80, 180), 80 - w);
	}
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.125));
		
		rain.draw(view, time);

		view.setFill(Color.hsb(210, 0.2, 1));
		drawCloudCircles(view, 0);
		view.setFill(Color.hsb(210, 0.1, 1));
		drawCloudCircles(view, 20);		
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 800);
	}
	
}
