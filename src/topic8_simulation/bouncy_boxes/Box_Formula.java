package topic8_simulation.bouncy_boxes;

import javafx.scene.paint.Color;
import mars.geometry.Vector;


public class Box_Formula extends Box {
	
	double t;
	Vector p, v;
	Color color;
	Vector areaD;
	

	@Override
	public Vector getPosition(double time) {
		return areaD;			
	}
	
	
	@Override
	public Vector getVelocity(double time) {
		return v;			
	}
	
	
	@Override
	public Color getColor() {
		return color;
	}

}