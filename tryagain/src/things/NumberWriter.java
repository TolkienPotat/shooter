package things;

import dev.draw.Renderer;
import dev.draw.Texture;

public class NumberWriter {

	Texture[] numbers;
	int textureWidth;

	public NumberWriter() {

		numbers = new Texture[10];

		for (int i = 0; i < 10; i++) {
			numbers[i] = Texture.loadTexture("Textures/Characters/" + i + ".png");

		}
		textureWidth = numbers[0].getWidth();
	}

	public void draw(int x, int y, Renderer r, int num) {

		int d0, d1, d2, d3;
		

			d0 = Math.floorDiv(num, 1000);
			d1 = Math.floorDiv(num - d0*1000, 100);
			d2 = Math.floorDiv(num - (d0*1000 + d1*100), 10);
			d3 = num - (d0*1000 + d1*100 + d2*10);

			r.begin();
			numbers[d0].bind();
			r.drawTexture(numbers[d0], x, y, 10000, 10000);
			x += textureWidth;
			r.end();
			r.begin();
			numbers[d1].bind();
			r.drawTexture(numbers[d1], x, y, 10000, 10000);
			x += textureWidth;
			r.end();
			r.begin();
			numbers[d2].bind();
			r.drawTexture(numbers[d2], x, y, 10000, 10000);
			x += textureWidth;
			r.end();
			r.begin();
			numbers[d3].bind();
			r.drawTexture(numbers[d3], x, y, 10000, 10000);
			r.end();
			
			
		



	}

}
