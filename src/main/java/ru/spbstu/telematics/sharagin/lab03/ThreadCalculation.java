package ru.spbstu.telematics.sharagin.lab03;

/**
 * Created by maksim on 18.04.17.
 */
public class ThreadCalculation extends Thread {
    private int startRow, endRow;
    private int[][] a, b, result;
    private int n;

    public ThreadCalculation(int[][] a, int[][] b, int[][] result, int startRow, int endRow)
    {
        this.a = a;
        this.b = b;
        this.result = result;
        this.startRow = startRow;
        this.endRow = endRow;
        this.n = b.length;
    }

    private int MatrixElementCalculation(int row, int col)
    {
        int c = 0;
        for(int i = 0; i < n; i++)
        {
            c += a[row][i] * b[i][col];
        }
        return c;
    }

    @Override
    public void run()
    {
        for(int row = startRow; row <= endRow; row++)
        {
            for(int col = 0; col < result[row].length; col++)
            {
                result[row][col] = MatrixElementCalculation(row, col);
            }
        }
    }
}
