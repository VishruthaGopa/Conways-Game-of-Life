import processing.core.PApplet;

public class Sketch extends PApplet {

	int[][] grid;
	int cols;
	int rows;
	int resolution = 10;//scale

 	public void settings() {
		size(600,400);

  	}

  	public void setup() {
		cols = width/resolution;
		rows = height/resolution;

		grid = new int[cols][rows];
		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < rows; j++) {
				grid[i][j]= floor(random(2));
			}
  		}

  	}

	public void draw() {
		background(0);

		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < rows; j++) {
				int x = i * resolution;
				int y = j * resolution;
				if(grid[i][j]== 1) {

					fill(255);
					stroke(0);
	    			 rect(x, y, resolution - 1, resolution - 1);
				}
			}
		} 

		int[][] next = new int[cols][rows];

		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < rows; j++) {
				int state= grid[i][j];

				//count live neighbours
				int sum = 0;
				int neighbours= countNeighbours(grid, i, j);

				if(state== 0 && neighbours== 3) {
					next[i][j]= 1;
				}else if(state== 1 && (neighbours < 2 || neighbours > 3)) {
					next[i][j]= 0;
				}else{
					next[i][j]= state;
				}

			}
		}
		grid= next;
	}

	//method to count neightbours
	public int countNeighbours(int [][]grid, int x, int y) {
		int sum = 0;

		for(int i = -1; i < 2; i++) {

			for(int j = -1; j < 2; j++) {
				int col= (x+i+cols) % cols; // so edges are also considered
				int row= (y+j+rows) % rows;
				sum += grid[col][row];

				//sum += grid[x+i][y+j];

			}

		}

		sum= sum - grid[x][y];
		return sum;
	}

}