package shoot_em_up.template;

import topic9_particle_systems.particles.ParticleSystemInstant;


/**
 * Eksplozija koja nastaje prilikom udara projektila u protivnika.
 */
public class Explosion extends ParticleSystemInstant<ExplosionParticle> {
	
	public Explosion(double t, Projectile projectile, Enemy enemy) {
		super(t, 0);
		// TODO
	}
	
	
	@Override
	protected ExplosionParticle generateNext() {
		// TODO
		return null;
	}
	
}
