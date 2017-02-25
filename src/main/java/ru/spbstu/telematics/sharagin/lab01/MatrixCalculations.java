/**
 * Created by maksim on 25.02.17.
 */
package ru.spbstu.telematics.sharagin.lab01;
import org.apache.commons.math3.linear.*;
import org.apache.commons.math3.exception.DimensionMismatchException;

public class MatrixCalculations {
    public RealMatrix MatrixAdd(double[][] matrix1, double[][] matrix2)
    {
        RealMatrix res = null;
        RealMatrix m = MatrixUtils.createRealMatrix(matrix1);
        RealMatrix n = MatrixUtils.createRealMatrix(matrix2);
        try {
            res = m.add(n);
        }
        catch (MatrixDimensionMismatchException e)
        {
            System.out.println("Add exception: secondMatrix is not the same size as firstMatrix; "+e);
        }
        return res;
    }

    public RealMatrix MatrixMultiply(double[][] matrix1, double[][] matrix2)
    {
        RealMatrix res = null;
        RealMatrix m = MatrixUtils.createRealMatrix(matrix1);
        RealMatrix n = MatrixUtils.createRealMatrix(matrix2);
        try
        {
            res = m.multiply(n);
        }
        catch (DimensionMismatchException e)
        {
            System.out.println("Multiply exception: columnDimension(firstMatrix) != rowDimension(secondMatrix); "+e);
        }
        return res;
    }

    public void MatrixOutput(RealMatrix res)
    {
        RealMatrixFormat formatter = new RealMatrixFormat("", "", "", "", "\n" , "\t");
        System.out.println(formatter.format(res));
    }
}
