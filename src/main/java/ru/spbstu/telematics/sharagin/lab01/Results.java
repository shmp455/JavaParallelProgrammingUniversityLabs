package ru.spbstu.telematics.sharagin.lab01;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

/**
 * Created by maksim on 25.02.17.
 */
public class Results {
    public static void main(String[] args)
    {
        MatrixCalculations obj = new MatrixCalculations();
        double[][] matrix1 = { {1,2,3},{4,5,7} };
        double[][] matrix2 = { {2,3},{1,2},{4,5} };
        double[][] matrix3 = { {4,5,6},{7,8,9} };
        String addOperation = "Add";
        String multiplyOperation = "Multiply";

        OutputResult(addOperation,matrix1,matrix3,obj);
        OutputResult(multiplyOperation,matrix1,matrix2,obj);
    }

    public static void OutputResult(String operation, double[][] m, double[][] n, MatrixCalculations obj)
    {
        if(operation == "Add")
        {
            RealMatrix addRes = obj.MatrixAdd(m,n);
            if(addRes != null){
                System.out.println("Add:");
                System.out.println("First matrix:");
                obj.MatrixOutput(MatrixUtils.createRealMatrix(m));
                System.out.println("Second matrix:");
                obj.MatrixOutput(MatrixUtils.createRealMatrix(n));
                System.out.println("Result:");
                obj.MatrixOutput(addRes);
                System.out.print("\n");
            }
        }
        else if(operation == "Multiply")
        {
            RealMatrix multiplyRes = obj.MatrixMultiply(m,n);
            if(multiplyRes != null){
                System.out.println("Multiply:");
                System.out.println("First matrix:");
                obj.MatrixOutput(MatrixUtils.createRealMatrix(m));
                System.out.println("Second matrix:");
                obj.MatrixOutput(MatrixUtils.createRealMatrix(n));
                System.out.println("Result:");
                obj.MatrixOutput(multiplyRes);
                System.out.print("\n");
            }
        }
        else
            return;
    }
}
