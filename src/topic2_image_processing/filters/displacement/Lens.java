package topic2_image_processing.filters.displacement;

import mars.geometry.Vector;
import topic2_image_processing.filters.DisplacementFilter;


public class Lens extends DisplacementFilter {
	final double f;
	
	
	public Lens(double f) {
		this.f = f;
	}


	@Override
	public Vector source(Vector dst, Vector dim) {
		return null;
	}
	
}
