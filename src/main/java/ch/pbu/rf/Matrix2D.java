package ch.pbu.rf;

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
	 * Constructor with rows and columns.
	 * 
	 * @param rows Rows.
	 * @param columns Columns.
	 * 
	 * @throws IllegalArgumentException If rows is negative.
	 * @throws IllegalArgumentException If columns is negative.
	 */
	public Matrix2D(int rows, int columns) {
		this(rows, columns, null);
	}
	
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
	 * @param rowIndex Row.
	 * @param columnIndex Column.
	 * 
	 * @return The value at given position.
	 * 
	 * @throws IllegalArgumentException If rowIndex is negative.
	 * @throws IllegalArgumentException If columnIndex is negative.
	 * @throws IndexOutOfBoundsException If rowIndex does not exist.
	 * @throws IndexOutOfBoundsException If columnIndex does not exist.
	 */
	public T get(int rowIndex, int columnIndex) {
		checkRowsAndColumns(rowIndex, columnIndex);
		return valuesByRowIndex.get(rowIndex).get(columnIndex);
	}
	
	/**
	 * 
	 * @param value
	 * @throws IllegalArgumentException If rowIndex is negative.
	 * @throws IllegalArgumentException If columnIndex is negative.
	 * @throws IndexOutOfBoundsException If rowIndex does not exist.
	 * @throws IndexOutOfBoundsException If columnIndex does not exist.
	 */
	public void put(int rowIndex, int columnIndex, T value) {
		checkRowsAndColumns(rowIndex, columnIndex);
		List<T> row = valuesByRowIndex.get(rowIndex);
		row.remove(columnIndex);
		row.add(columnIndex, value);
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
	
	
	private void checkRowsAndColumns(int rowIndex, int columnIndex) {
		if (rowIndex < 0) {
			throw new IllegalArgumentException("rowIndex should not be negative");
		}
		
		if (columnIndex < 0) {
			throw new IllegalArgumentException("columnIndex should not be negative");
		}
		
		int amountOfRows = getRows();
		if (rowIndex > amountOfRows - 1) {
			throw new IndexOutOfBoundsException(String.format("rowIndex %d does not exist (%d rows)", rowIndex, amountOfRows));
		}
		
		int amountOfColumns = getColumns();
		if (columnIndex > amountOfColumns - 1) {
			throw new IndexOutOfBoundsException(String.format("columnIndex %d does not exist (%d columns)", columnIndex, amountOfColumns));
		}
	}
}
