package topic9_particle_systems.fireworks;

import mars.geometry.Vector;
import mars.random.sampling.Sampling;
import topic9_particle_systems.particles.ParticleSystemInstant;


public class Firework extends ParticleSystemInstant<Spark> {
	double t, tD;
	Vector p, v, a;
	double speed;
	double hue;
	double r;
	
	
	public Firework(int n, double t, double tD, Vector p, Vector v, Vector a, double speed, double hue, double r) {
		super(t, n);
		this.t = t;
		this.tD = tD;
		this.p = p;
		this.v = v;
		this.a = a;
		this.speed = speed;
		this.hue = hue;
		this.r = r;
	}
	

	@Override
	protected Spark generateNext() {
		return new Spark(
				t + Sampling.exponential(0.1),
				tD * (0.7 + Sampling.exponential(0.3)),
				p,
				v.add(Vector.randomOnCircle(speed).mul(Sampling.gaussian(1, 0.3))),
				a,
				Sampling.gaussian(hue, 5),
				r
				);
	}
	
}
