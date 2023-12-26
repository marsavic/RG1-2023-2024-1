package shoot_em_up.full;

import mars.geometry.Vector;
import mars.random.sampling.Sampling;
import topic9_particle_systems.particles.ParticleSystemInterval;


public class BackgroundParticleSystem extends ParticleSystemInterval<BackgroundParticle> {

	private static double intervalAt(double time) {
		return(0.05 / (1 + time / 20));
	}
	
	
	public BackgroundParticleSystem() {
		super(-10, intervalAt(0.0));
	}
	
	
	public void updateInterval(double time) {
		setInterval(intervalAt(time));
	}


	@Override
	protected BackgroundParticle generateNext() {
		double time = timeNext();
		return new BackgroundParticle(
				time,
				new Vector(-500 + 1000 * Math.random(), 500),
				new Vector(0, 6 * (-30 - 30 * Math.random()) * (1 + time / 20)),
				Sampling.exponential(100),
				0.05 * Math.random()
		);
	}

}
