package ru.spbstu.telematics.sharagin.lab01;
import org.junit.Test;
import org.junit.Assert;
/**
 * Created by maksim on 25.02.17.
 */
public class MatrixCalculationsTestCase {
    @Test
    public void MultiplyMatrixRightDimensionTest()
    {
        MatrixCalculations obj = new MatrixCalculations();
        double[][] matrix1 = { {1,2,3},{4,5,6} };
        double[][] matrix2 = { {2,3},{1,2},{4,5} };
        Assert.assertNotNull(obj.MatrixMultiply(matrix1,matrix2));
    }

    @Test
    public void MultiplyMatrixWrongDimensionTest()
    {
        double[][] matrix1 = { {1,2,3},{4,5,6},{10,1,2} };
        double[][] matrix2 = { {4,2,3},{5,1,2} };
        TestMultiplyMatrixAssertNull(matrix1,matrix2);
    }
    public void TestMultiplyMatrixAssertNull(double[][] m, double[][] n)
    {
        MatrixCalculations obj = new MatrixCalculations();
        Assert.assertNull(obj.MatrixMultiply(m,n));
    }

    @Test
    public void AddMatrixRightDimensionTest()
    {
        MatrixCalculations obj = new MatrixCalculations();
        double[][] matrix1 = { {1,2,3},{4,5,6} };
        double[][] matrix2 = { {4,2,3},{5,1,2} };
        Assert.assertNotNull(obj.MatrixAdd(matrix1,matrix2));
    }

    @Test
    public  void  AddMatrixWrongDimensionTest()
    {
        double[][] matrix1 = { {1,2,3},{4,5,6},{10,1,2} };
        double[][] matrix2 = { {4,2,3},{5,1,2} };
        TestAddMatrixAssertNull(matrix1,matrix2);
    }
    public  void TestAddMatrixAssertNull(double[][] m, double[][] n)
    {
        MatrixCalculations obj = new MatrixCalculations();
        Assert.assertNull(obj.MatrixAdd(m,n));
    }
}
