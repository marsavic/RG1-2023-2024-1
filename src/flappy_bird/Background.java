package flappy_bird;

import javafx.scene.paint.Color;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;

class Background extends Thing {
	@Override
	public void draw(View view, double t) {
		DrawingUtils.clear(view, Color.hsb(240, 0.3, 0.3));
	}
}
