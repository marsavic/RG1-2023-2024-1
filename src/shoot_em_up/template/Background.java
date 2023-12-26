package shoot_em_up.template;

import javafx.scene.paint.Color;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;


public class Background {

	public void draw(View view, double time) {
		// TODO

		DrawingUtils.clear(view, Color.hsb(0, 0, 0.1));
	}
		
}
