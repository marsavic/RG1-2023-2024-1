package topic2_image_processing.filters.color;

import javafx.scene.paint.Color;
import topic2_image_processing.filters.ColorFilter;

public class Grayscale extends ColorFilter {

	@Override
	public Color processColor(Color input) {
		double r = input.getRed();
		double g = input.getGreen();
		double b = input.getBlue();
		double opacity = input.getOpacity();
		
		double k = 0.71 * g + 0.21 * r + 0.07 * b;
		
		return new Color(k, k, k, opacity);
	}
	
}
