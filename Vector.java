/**
 * The vector class extends Matrix and is a matrix with one row
 * 
 * @author Jonathan Peters
 * @version 1.0
 * @since 2023-02-07 
 */
public class Vector extends Matrix{
    /**
     * Constructor
     * Constructor without the values
     * 
     * @param c: number of columns
     */
    public Vector(int c) {
        super(1, c);
    }
    
    /**
     * Specialized Constructor
     * Constructor including the values
     * 
     * @param c: number of columns
     * @param linArr: list of doubles representing the values
     */
    public Vector(int c, double[] linArr) {
        super(1, c, linArr);
    }
    
    /**
     * getElement
     * A method getting the element from the vector as position 0, c
     * 
     * @param c: the column of the value
     * @return double: the value 
     */
    public double getElement(int c) {
        return super.getElement(0, c);    
    }
    
}
