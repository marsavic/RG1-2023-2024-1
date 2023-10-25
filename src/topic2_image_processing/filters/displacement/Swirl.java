package topic2_image_processing.filters.displacement;

import mars.geometry.Vector;
import topic2_image_processing.filters.DisplacementFilter;


public class Swirl extends DisplacementFilter {
	final double f, a;
	
	
	public Swirl(double f, double a) {
		this.f = f;
		this.a = a;
	}


	@Override
	public Vector source(Vector dst, Vector dim) {
		return null;
	}
	
}
