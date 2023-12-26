package flappy_bird;

import mars.drawingx.drawing.View;

abstract class Thing {
	public abstract void draw(View view, double t);
	public double depth() { return 1.0; };
}
