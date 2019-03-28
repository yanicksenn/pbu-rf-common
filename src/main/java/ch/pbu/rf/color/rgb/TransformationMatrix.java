package ch.pbu.rf.color.rgb;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents the transformation matrix;
 * 
 * @author Yanick Senn
 */
public class TransformationMatrix {
	private final BigDecimal[][] values;
	
	/**
	 * Constructor with default value.
	 * 
	 * @param defaultValue Default-value.
	 */
	public TransformationMatrix(BigDecimal defaultValue) {
		values = new BigDecimal[][] {
			{ defaultValue, defaultValue, defaultValue },
			{ defaultValue, defaultValue, defaultValue },
			{ defaultValue, defaultValue, defaultValue }
		};
	}
	
	
	/**
	 * Returns the value at given location.
	 * 
	 * @param location Location.
	 * 
	 * @return The value at given location.
	 * 
	 * @throws NullPointerException If location is not specified.
	 */
	public BigDecimal get(Location location) {
		Objects.requireNonNull(location, "location is not specified");
		
		return values[location.getX()][location.getY()];	
	}
	
	/**
	 * Returns the row at the given y location.
	 * 
	 * @param y Y location.
	 * 
	 * @return The row at the given y location.
	 * 
	 * @throws NullPointerException If location is not specified.
	 */
	public BigDecimal[] getRow(Location.Y y) {
		Objects.requireNonNull(y, "y is not specified");

		int pos = y.get();
		BigDecimal[] result = new BigDecimal[] {
			values[pos][0],
			values[pos][1],
			values[pos][2],
		};
		
		return result;
	}
	
	/**
	 * Returns the column at the given x location.
	 * 
	 * @param x X location.
	 * 
	 * @return The column at the given x location.
	 * 
	 * @throws NullPointerException If location is not specified.
	 */
	public BigDecimal[] getColumn(Location.X x) {
		Objects.requireNonNull(x, "x is not specified");
		
		int pos = x.get();
		BigDecimal[] result = new BigDecimal[] {
			values[0][pos],
			values[1][pos],
			values[2][pos],
		};
		
		return result;
	}
	
	/**
	 * Sets the value at the given location.
	 * 
	 * @param location Location.
	 * @param value Value.
	 * 
	 * @throws NullPointerException If location is not specified.
	 */
	public void set(Location location, BigDecimal value) {
		Objects.requireNonNull(location, "location is not specified");

		values[location.getX()][location.getY()] = value;
	}
	

	@Override
	public int hashCode() {
		BigDecimal x0_y0 = get(Location.X0_Y0);
		BigDecimal x1_y0 = get(Location.X1_Y0);
		BigDecimal x2_y0 = get(Location.X2_Y0);
		
		BigDecimal x0_y1 = get(Location.X0_Y1);
		BigDecimal x1_y1 = get(Location.X1_Y1);
		BigDecimal x2_y1 = get(Location.X2_Y1);
		
		BigDecimal x0_y2 = get(Location.X0_Y2);
		BigDecimal x1_y2 = get(Location.X1_Y2);
		BigDecimal x2_y2 = get(Location.X2_Y2);
		
		return Objects.hash(
			x0_y0, 
			x1_y0, 
			x2_y0, 
			
			x0_y1, 
			x1_y1, 
			x2_y1, 
			
			x0_y2, 
			x1_y2, 
			x2_y2
		);
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null) {
			return false;
		}
		
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		TransformationMatrix other = (TransformationMatrix) obj;
		
		return 
			this.get(Location.X0_Y0).compareTo(other.get(Location.X0_Y0)) == 0 && 
			this.get(Location.X1_Y0).compareTo(other.get(Location.X1_Y0)) == 0 && 
			this.get(Location.X2_Y0).compareTo(other.get(Location.X2_Y0)) == 0 && 
			this.get(Location.X0_Y1).compareTo(other.get(Location.X0_Y1)) == 0 && 
			this.get(Location.X1_Y1).compareTo(other.get(Location.X1_Y1)) == 0 && 
			this.get(Location.X2_Y1).compareTo(other.get(Location.X2_Y1)) == 0 && 
			this.get(Location.X0_Y2).compareTo(other.get(Location.X0_Y2)) == 0 && 
			this.get(Location.X1_Y2).compareTo(other.get(Location.X1_Y2)) == 0 && 
			this.get(Location.X2_Y2).compareTo(other.get(Location.X2_Y2)) == 0;
	}
	
	
	/**
	 * Represents the x, y locations.
	 * 
	 * @author Yanick Senn
	 */
	public static enum Location {
		X0_Y0(X.X0, Y.Y0), 
		X1_Y0(X.X1, Y.Y0), 
		X2_Y0(X.X2, Y.Y0),
		
		X0_Y1(X.X0, Y.Y1), 
		X1_Y1(X.X1, Y.Y1), 
		X2_Y1(X.X2, Y.Y1),
		
		X0_Y2(X.X0, Y.Y2), 
		X1_Y2(X.X1, Y.Y2), 
		X2_Y2(X.X2, Y.Y2),
		;
		
		private final X x;
		private final Y y;
		
		private Location(X x, Y y) {
			this.x = x;
			this.y = y;
		}
		
		/**
		 * Returns the x value.
		 * 
		 * @return X value.
		 */
		public int getX() {
			return x.get();
		}
		
		/**
		 * Returns the y value.
		 * 
		 * @return Y value.
		 */
		public int getY() {
			return y.get();
		}
		

		
		
		/**
		 * Represents the x locations.
		 * 
		 * @author Yanick Senn
		 */
		public static enum X {
			X0(0),
			X1(1),
			X2(2),
			;
			
			private final int x;
			
			private X(int x) {
				this.x = x;
			}
			
			/**
			 * Returns the x value.
			 * 
			 * @return X value.
			 */
			public int get() {
				return x;
			}
		}
		
		/**
		 * Represents the y locations.
		 * 
		 * @author Yanick Senn
		 */
		public static enum Y {
			Y0(0),
			Y1(1),
			Y2(2),
			;
			
			private final int y;
			
			private Y(int y) {
				this.y = y;
			}
			
			/**
			 * Returns the y value.
			 * 
			 * @return Y value.
			 */
			public int get() {
				return y;
			}
		}
	}
}
