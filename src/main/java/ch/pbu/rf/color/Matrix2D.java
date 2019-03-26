package ch.pbu.rf.color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a two-dimensional matrix.
 * 
 * @author Yanick Senn
 *
 * @param <T> Type.
 */
public class Matrix2D<T> {
	private final Map<Integer, List<T>> valuesByRowIndex = new HashMap<>();
	
	/**
	 * Constructor with rows, columns and default value.
	 * 
	 * @param rows Rows.
	 * @param columns Columns.
	 * @param defaultValue Default-value.
	 * 
	 * @throws IllegalArgumentException If rows is negative.
	 * @throws IllegalArgumentException If columns is negative.
	 */
	public Matrix2D(int rows, int columns, T defaultValue) {
		if (rows < 0) {
			throw new IllegalArgumentException("rows should not be negative");
		}
		
		if (columns < 0) {
			throw new IllegalArgumentException("columns should not be negative");
		}
		
		for (int i = 0; i < rows; i++) {
			List<T> cells = valuesByRowIndex.get(i);
			if (cells == null) {
				cells = new ArrayList<>();
				valuesByRowIndex.put(i, cells);
			}
			
			for (int j = 0; j < columns; j++) {
				cells.add(defaultValue);
			}
		}
	}
	
	
	/**
	 * Returns the amount of rows.
	 * 
	 * @return The amount of rows.
	 */
	public int getRows() {
		int result = valuesByRowIndex.size();
		return result;
	}
	
	/**
	 * Returns the amount of columns.
	 * 
	 * @return The amount of columns.
	 */
	public int getColumns() {
		int result = 0;
		
		if (getRows() > 0) {
			result = valuesByRowIndex.get(0).size();
		}
		
		return result;
	}
	
	/**
	 * Returns the value at given position.
	 * 
	 * @param row Row.
	 * @param column Column.
	 * 
	 * @return The value at given position.
	 * 
	 * @throws IllegalArgumentException If rows is negative.
	 * @throws IllegalArgumentException If columns is negative.
	 * @throws IndexOutOfBoundsException If row does not exist.
	 * @throws IndexOutOfBoundsException If column does not exist.
	 */
	public T get(int row, int column) {
		if (row < 0) {
			throw new IllegalArgumentException("rows should not be negative");
		}
		
		if (column < 0) {
			throw new IllegalArgumentException("columns should not be negative");
		}
		
		int amountOfRows = getRows();
		if (row > amountOfRows - 1) {
			throw new IndexOutOfBoundsException(String.format("row index %d does not exist (%d rows)", row, amountOfRows));
		}
		
		int amountOfColumns = getColumns();
		if (column > amountOfColumns - 1) {
			throw new IndexOutOfBoundsException(String.format("column index %d does not exist (%d columns)", column, amountOfColumns));
		}
		
		return valuesByRowIndex.get(row).get(column);
	}
	
	/**
	 * Returns <code>true</code> if this matrix is empty.
	 * 
	 * @return <code>true</code> if this matrix is empty. Returns <code>false</code> otherwise.
	 */
	public boolean isEmpty() {
		return valuesByRowIndex.isEmpty();
	}
	
	/**
	 * Returns the size of this matrix.
	 * 
	 * @return The size of this matrix.
	 */
	public int size() {
		return getRows() * getColumns();
	}
	
	/**
	 * Returns this matrix as a two-dimensional array.
	 * 
	 * @return This matrix as a two-dimensional array.
	 */
	public T[][] toArray() {
		@SuppressWarnings("unchecked")
		T[][] result = (T[][]) new Object[getRows()][getColumns()];
		
		for (int i = 0; i < getRows(); i++) {
			for (int j = 0; j < getColumns(); j++) {
				result[i][j] = get(i, i);
			}
		}
		
		return result;
	}
}
