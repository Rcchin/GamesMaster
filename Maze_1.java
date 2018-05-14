import java.util.Random;

public class Maze_1 {
	// Define class variables 
	public static final int N = 1;
	public static final int S = 2;
	public static final int E = 4;
	public static final int W = 8;

	public static final int DEFAULT_WIDTH = 10;
	public static final int DEFAULT_HEIGHT = 10;

	protected Random _random = null;
	protected Long _seed = null; 
	protected int _w = 0;
	protected int _h = 0;
	protected int[][] _grid = null;

	// Define class methods
	public static int DX(int direction) {
		switch ( direction ) {
		case Maze_1.E:
			return +1;
		case Maze_1.W:
			return -1;
		case Maze_1.N:
		case Maze_1.S:
			return 0;
		}
		// error condition, but should never reach here
		return -1;
	}

	public static int DY(int direction) {
		switch ( direction ) {
		case Maze_1.E:
		case Maze_1.W:
			return 0;
		case Maze_1.N:
			return -1;
		case Maze_1.S:
			return 1;
		}
		// error condition, but should never reach here
		return -1;
	}

	public static int OPPOSITE(int direction) {
		switch ( direction ) {
		case Maze_1.E:
			return Maze_1.W;
		case Maze_1.W:
			return Maze_1.E;
		case Maze_1.N:
			return Maze_1.S;
		case Maze_1.S:
			return Maze_1.N;
		}
		// error condition, but should never reach here
		return -1;
	}


	/**
	 * Initialize a new 2D maze with, optionally supply the width, height and seed.
	 * 
	 * Default seed will give "random" behavior.
	 * User-supplied seed value will give "deterministic" behavior. 
	 */
	public Maze_1() {
		this(DEFAULT_WIDTH,DEFAULT_HEIGHT);
	}
	public Maze_1(int w,int h) {
		initialize(w,h);
		_random = new Random();
	}
	public Maze_1(int w,int h,long seed) {
		initialize(w,h);
		_random = new Random(seed);
		_seed = new Long(seed);
	}
	private void initialize(int w,int h) {
		_w = w; _h = h; 
		_grid = new int[h][w];
		for ( int j=0; j < h; ++j ) {
			for ( int i=0; i < w; ++i ) {
				_grid[j][i] = 0;
			}
		}
	}

	/**
	 * Draw the grid, starting in the upper-left hand corner. 
	 */
	public void draw() {
		// draw the "top" line
		System.out.print(" ");
		for ( int i=0; i < (_w*2 - 1); ++i ) {
			System.out.print("_");
		}
		System.out.println("");

		// draw each row
		for ( int j=0; j < _h; ++j ) {
			System.out.print("|");
			for ( int i=0; i < _w; ++i ) {
				// render "bottom" using the "S" switch
				System.out.print((_grid[j][i] & Maze_1.S) != 0 ? " " : "_");

				// render "side" using "E" switch
				if ( (_grid[j][i] & Maze_1.E) != 0 ) {
					System.out.print(((_grid[j][i] | _grid[j][i+1]) & Maze_1.S) != 0 ? " " : "_" );
				} else {
					System.out.print("|");
				}
			}
			System.out.println("");
		}

		// output maze //data
		outputMetadata();
	}

	protected void outputMetadata() {
		String meta = " " + _w + " " + _h;
		if ( _seed != null ) {
			meta += " " + _seed;
		} else { 
			meta += " random";
		}
		System.out.println(meta);	
	}
}