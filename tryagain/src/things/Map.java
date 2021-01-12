package things;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import dev.draw.Renderer;
import dev.draw.Texture;

public class Map {

	public Tile[][] tiles;
	
	public int length, width;
	
	Renderer renderer;
	
	public Map(String filePath, int maxX, int maxY) {
		tiles = new Tile[maxX][maxY];
		loadMap(filePath, maxX, maxY);
		length = maxX;
		width = maxY;
		renderer = new Renderer();
	}

	
	public void loadMap(String filePath, int maxX, int maxY) {
		
    	
		
		
			File file = new File(filePath);

			try {

				Scanner sc = new Scanner(file);

				
	            for (int l = 0; l < maxY; l++) {
	            	for (int m = 0; m < maxX; m++) {
	            		tiles[m][l] = new Tile();
	            		tiles[m][l].id = sc.nextInt();
	            		
	            	}

	        }
	        sc.close();
	    } 
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
			
		for (int l = 0; l < maxX; l++) {
			
			for (int m = 0; m < maxY; m++) {
				
				if(tiles[l][m].id == 2) {
					tiles[l][m].texture = Texture.loadTexture("Textures/tile.png");
					
				} else if(tiles[l][m].id == 3) {
					tiles[l][m].texture = Texture.loadTexture("Textures/wall.png");
					
				} else if(tiles[l][m].id == 1) {
					tiles[l][m].texture = Texture.loadTexture("Textures/ground.png");
					
				}
				
			}
		}
		
	}
	
	public void renderMap(Player player) {
		
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				renderer.begin();
				tiles[i][j].x = (i*40) - (player.xInGame);
				tiles[i][j].y = (j*40) - (player.yInGame);
				
				tiles[i][j].xInGame = i*40;
				tiles[i][j].yInGame = j*40;
				tiles[i][j].r.setBounds(tiles[i][j].xInGame, tiles[i][j].yInGame, 40, 40);
				
				tiles[i][j].texture.bind();
				renderer.drawTexture(tiles[i][j].texture,tiles[i][j].x,tiles[i][j].y, tiles[i][j].xInGame, tiles[i][j].yInGame);
				
				
				renderer.end();
				
			}
		}
		
	}
	
	
}
