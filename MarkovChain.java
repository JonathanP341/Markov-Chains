/**
 * The MarkovChain class is composed of a vector and Matrix and is used to predict probability 
 * 
 * @author Jonathan Peters
 * @version 1.0
 * @since 2023-02-07 
 */
public class MarkovChain {
    private Vector stateVector;
    private Matrix transitionMatrix;
    
    /**
     * MarkovChain Constructor
     * 
     * @param sVector: state vector
     * @param tMatrix: transition matrix
     */
    public MarkovChain(Vector sVector, Matrix tMatrix) {
        transitionMatrix = tMatrix;
        stateVector = sVector;
    }
    
    /**
     * inRange
     * A helper method checking if the given value is within the specified range
     * 
     * @param value: the value being tested
     * @return boolean: if the value is in range
     */
    private boolean inRange(double value) {
        if (value >= 0.99 && value <= 1.01) {
            return true; //If it satisfies the required values return true
        } else {
            return false; //Returning false because the values of the vector were not equal to 1
        }
    }
    
    /**
     * isValid
     * Checking whether both the stateVector and transitionMatrix are valid by 
     * looking at the values per row/column and if the size is compatible
     * 
     * @return boolean: If both the matrix and vector are valid
     */
    public boolean isValid() {
        //Checking if the matrix is a square, returning false if it isnt
        if (transitionMatrix.getNumCols() != transitionMatrix.getNumRows()) {
            return false;
        }
        
        //Checking if the size of the matrix and the size of the vector are equal
        if (transitionMatrix.getNumCols() != stateVector.getNumCols()) {
            return false;
        }
        
        //Checking that the values of the vector equal to 1.0
        double values = 0;
        for (int i = 0; i < stateVector.getNumCols(); i++) {
            values += stateVector.getElement(i);
        }
        if (inRange(values) == false) { //Returning false if the value is NOT equal to 1 and continuing otherwise
            return false;
        }
        
        //Checking if the values of each row of the matrix are equal to one
        for (int i = 0; i < transitionMatrix.getNumRows(); i++) {
            values = 0; //Resetting values so we can find the proper sum per row
            for (int j = 0; j < transitionMatrix.getNumCols(); j++) {
                values += transitionMatrix.getElement(i, j);
            }
            if (inRange(values) == false) { //Returning false if the value is NOT equal to 1 and continuing otherwise
                return false;
            }   
        }
        //If the matrix and the vector satifsy all of the conditions required, return true
        return true;
    }
    
    
    /**
     * computeProbabilityMatrix
     * A method create a matrix that represents the probability of an action after numSteps time has occurred
     * 
     * @param numSteps: Representing the amount of time something has occurred that would change the matrix
     * @return Matrix: if the value is in range
     */
    public Matrix computeProbabilityMatrix(int numSteps) {
        if (isValid() == false) { //If the matrix isnt valid return null
            return null;
        }
        
        Matrix m1 = transitionMatrix;
        //Multiplying the transition matrix by itself numSteps -1 times
        for (int i = 0; i < numSteps - 1; i++) {
            m1 = m1.multiply(transitionMatrix); //Multiplying by m1 so the original transition vector isnt changed everytime
        }
        
        //Multiplying the state vector by the matrix
        return stateVector.multiply(m1);
    }
    
}
