package shoot_em_up.full;

import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineJoin;
import mars.drawingx.drawing.View;
import mars.geometry.Transformation;
import mars.geometry.Vector;
import mars.utils.Graphics;
import mars.utils.Numeric;


public class Ship extends Body {
	static final Color COLOR = Color.hsb(220, 0.80, 0.96);
	static final int N_WAVES = 6;
	static final double T_PERIOD = 2;

	
	
	public Ship() {
		super(0, new Vector(0, -300), Vector.ZERO, 22);
	}
	
	
	@Override
	public void draw(View view, double time) {
		view.stateStore();
		view.addTransformation(Transformation.identity().scale(r).translate(positionAt(time)));
		Vector pt   = new Vector( 0.0,  1.0);

		Vector pl1  = new Vector(-0.3,  0.0);
		Vector pl2  = new Vector(-0.7,  0.0);

		Vector pl   = new Vector(-0.9, -1.0);
		
		Vector pbl1 = new Vector(-0.6, -0.7);
		Vector pbl2 = new Vector(-0.2, -0.7);
		
		Vector pb   = new Vector( 0.0, -0.9);
		
		Vector pbr1 = new Vector( 0.2, -0.7);
		Vector pbr2 = new Vector( 0.6, -0.7);
		
		Vector pr   = new Vector( 0.9, -1.0);
		
		Vector pr1  = new Vector( 0.7,  0.0);
		Vector pr2  = new Vector( 0.3,  0.0);

		
//		view.setFill(Graphics.scaleOpacity(COLOR, 0.1875));
//		view.fillCircleCentered(ZERO, 2);
		
		view.beginPath();
		view.moveTo(pt);
		view.bezierCurveTo(pl1 , pl2 , pl);
		view.bezierCurveTo(pbl1, pbl2, pb);
		view.bezierCurveTo(pbr1, pbr2, pr);
		view.bezierCurveTo(pr1 , pr2,  pt);
		view.closePath();

		view.setFill(Graphics.scaleOpacity(COLOR, 0.7));
		view.fill();
		
		view.setStroke(COLOR);
		view.setLineWidth(0.2);
		view.setLineJoin(StrokeLineJoin.ROUND);
		view.stroke();

		view.setFill(Graphics.scaleOpacity(COLOR, 0.25 + 0.125 * Numeric.sinT(time)));
		view.fillOvalCentered(new Vector(0, -1.5), new Vector(0.8, 0.3));

		view.setFill(Graphics.scaleOpacity(COLOR, 0.25 - 0.125 * Numeric.sinT(time)));
		view.fillOvalCentered(new Vector(0, -1.2), new Vector(0.9, 0.2));

		
		view.stateRestore();
	}
	
}
