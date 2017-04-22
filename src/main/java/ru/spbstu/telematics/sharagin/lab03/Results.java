package ru.spbstu.telematics.sharagin.lab03;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by maksim on 18.04.17.
 */
public class Results {
    public static void main(String[] args) throws InterruptedException {
        Random rand = new Random();
        int threadsCount = 3;
        System.out.print("Input matrix B rows count:");
        Scanner in = new Scanner(System.in);
        int rowsB = in.nextInt();

        System.out.print("\nInput matrix B columns count:");
        int columnsB = in.nextInt();

        System.out.print("\nInput matrix A rows count:");
        int rowsA = in.nextInt();

        System.out.print("\nInput matrix A columns count:");
        int columnsA = in.nextInt();

        int[][] a = new int[rowsA][columnsA];
        int[][] b = new int[rowsB][columnsB];

        for(int i = 0; i < rowsA; i++)
        {
            for(int j = 0; j < columnsA; j++)
                a[i][j] = rand.nextInt(10);
        }
        for(int i = 0; i < rowsB; i++)
        {
            for(int j = 0; j < columnsB; j++)
                b[i][j] = rand.nextInt(10);
        }

        /*System.out.println("Matrix A:");
        PrintMatrix(a);
        System.out.println("\nMatrix B:");
        PrintMatrix(b);*/

        for(int i = 1; i <= threadsCount; i++) {
            int[][] c = Multiply(a, b, i);
            //System.out.println("\nMatrix C (" + i + " threads):");
            //PrintMatrix(c);
        }
    }

    public static int[][] Multiply(int[][] a, int[][] b, int threadsCount) throws InterruptedException {
        if(a == null ||
                a.length == 0 ||
                a[0] == null ||
                a[0].length == 0)
            throw new IllegalArgumentException("Matrix A problems");

        if(b == null ||
                b.length == 0 ||
                b[0] == null ||
                b[0].length == 0)
            throw new IllegalArgumentException("Matrix B problems");

        if(a[0].length != b.length)
            throw new IllegalArgumentException("Matrix are not aligned");

        // определяем размеры результирующей матрицы
        int m = a.length;
        int q = b[0].length;
        int[][] result = new int[m][q];

        // если количество потоков больше чем количество строк, то уменьшим количество потоков
        if(threadsCount > m)
            threadsCount = m;

        // посчитаем сколько строк результирующей матрицы будет считать каждый поток
        int count = m/threadsCount;
        int additional = m % threadsCount; // если не делится на threadsCount, то добавим к первому потоку

        // создаем и запускаем потоки
        Thread[] threads = new Thread[threadsCount];
        int start = 0;
        for(int i = 0; i < threadsCount; i++)
        {
            int cnt = ( i == 0 ? count + additional : count );
            threads[i] = new ThreadCalculation(a, b, result, start, start + cnt - 1);
            start += cnt;
            threads[i].start();
        }

        // ждем завершения
        try{
            long timeStart = System.currentTimeMillis();
            for(Thread thread : threads)
                thread.join();
            long timeEnd = System.currentTimeMillis();
            System.out.println("threads count " + threadsCount + "; time:" + Math.abs(timeStart - timeEnd));
        } catch(InterruptedException e) {
            System.out.println("Interrupted");
        }

        return  result;
    }

    public static void PrintMatrix(int[][] m)
    {
        for(int i = 0; i < m.length; i++)
        {
            for(int j = 0; j < m[i].length; j++)
                System.out.print(m[i][j] + " ");
            System.out.println();
        }
    }
}
