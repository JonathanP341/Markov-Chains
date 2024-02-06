/**
 * The matrix class creates a 2D array of doubles which can be transposed and multiplied 
 * 
 * @author Jonathan Peters
 * @version 1.0
 * @since 2023-02-07 
 */
public class Matrix {
    private int numRows;
    private int numCols;
    private double[][] data;
    
    /**
     * Constructor
     * Constructor without the values
     * 
     * @param r: number of rows
     * @param c: number of columns
     */
    public Matrix(int r, int c) {
        numRows = r;
        numCols = c;
        data = new double[numRows][numCols];
    }
    
    /**
     * Specialized Constructor
     * Constructor including the values
     * 
     * @param r: number of rows
     * @param c: number of columns
     * @param linArr: the data to be stored in the 2D array
     */
    public Matrix(int r, int c, double[] linArr) {
        numRows = r;
        numCols = c;
        
        //Setting data to having numRows and numCols
        data = new double[numRows][numCols];
        
        //Converting the linear array into the 2D array for data such that [1, 2, 3, 4] -> [1, 2], [3, 4]
        for (int i = 0; i < numRows; i++) { 
            for (int j = 0; j < numCols; j++) {
                data[i][j] = linArr[numCols*i + j];
            }
        }
    }
    
    
    /**
     * transpose
     * A method that transposes the matrix by swapping the column and row of the values in the matrix
     * 
     * @return void
     */
    public void transpose() {
        //Initializing a new matrix which will be transposed
        Matrix m1 = new Matrix(numCols, numRows);
        //First step is to swap the rows and coloumsn of the new 
        for (int i = 0; i < m1.getNumRows(); i++) {
            for (int j = 0; j < m1.getNumCols(); j++) {
                m1.setElement(i, j, getElement(j, i)); //Setting the element in the new matrix 
            }
        }
        
        //Setting the variables of the matrix to the values of the transposed matrix
        numRows = m1.getNumRows();
        numCols = m1.getNumCols();
        data =  m1.getData();  
    }
    
    
    /**
     * multiply
     * A method that multiplies every value in a matrix by a scalar
     * 
     * @param scalar: The value you multiply the entire matrix by
     * @return Matrix: The new matrix after being multiplied
     */
    public Matrix multiply(double scalar) {
        //Setting a new matrix m1 equal to this
        Matrix m1 = this;
        //Looping through the rows and columns of the array
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                m1.setElement(i, j, m1.getElement(i, j) * scalar); //Multiplying each value by the scalar
            }
        }
        return m1;
    }
    
    
    /**
     * multiply
     * A method that multiplies one matrix by another by using 3 for loops 
     * 
     * @param other: The other matrix you multiply the matrix by
     * @return Matrix: The new matrix after being multiplied
     */
    public Matrix multiply(Matrix other) {
        if (numCols != other.getNumRows()) { //If the matrices arnt compatible
            return null;
        }
        //Creating the matrix using the num rows from this and the coloums in other which should be the same
        Matrix m1 = new Matrix(numRows, other.getNumCols());
        double val;
        for (int i = 0; i < numRows; i++) { //Looping through the matrix 
            for (int j = 0; j < other.getNumCols(); j++) { //Looping through the columns of the other matrix
                val = 0; //Resetting val so we can get the accurate value per index of the new array
                for (int k = 0; k < other.getNumCols(); k++) { //Looping through the values in the column of that matrix
                    val += data[i][k] * other.getElement(k, j); //Multiplying one value by another
                } 
                m1.setElement(i, j, val); //Setting val in the new matrix before resetting val   
            }                 
        }
        return m1;
    }
    
    
    /**
     * toString
     * Representing a matrix by printing out the values in rows and columns
     * 
     * @return String: The string representation of the matrix
     */
    public String toString() {
        String s = "";

        if (data.length == 0) { //If the matrix is empty
            return "Empty matrix";
        } else {
            //Looping through the matrix in order to properly format the print out the matrix
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    s += String.format("%8.3f", getElement(i, j)); //Adding spaces to  
               }
                if (i + 1 != data.length) {
                    s += "\n";
                }     
            }    
        }
        return s;
    }
    
    
    /**
     * getNumRows
     * Returning the number of rows in the matrix
     * 
     * @return int: The number of rows
     */
    public int getNumRows() {
        return numRows;
    }
    
    /**
     * getNumCols
     * Returning the number of columns in the matrix
     * 
     * @return int: The number of columns
     */
    public int getNumCols() {
        return numCols;
    }
    
    /**
     * getData
     * Returning the number of rows in the matrix
     * 
     * @return double[][]: The 2D array of the matrix
     */
    public double[][] getData() {
        return data;
    }
    
    /**
     * getElement
     * Returning the value of the specified element
     * 
     * @param r: rows
     * @param c: columns
     * @return int: The value of the array at r rows and c columns
     */
    public double getElement(int r, int c) {
        return data[r][c];
    }
    
    /**
     * getElement
     * Setting the specified element to the specified value
     * 
     * @param r: rows
     * @param c: columns
     * @param value: the new stored value
     */
    public void setElement(int r, int c, double value) {
        data[r][c] = value;
    }   
    
}
