package ru.spbstu.telematics.sharagin.lab05;

/**
 * Created by maksim on 02.05.17.
 */
public class MainLab05 {
    public static void main(String[] args){
        Progress progress = new Progress();
        FirstNeighbor firstNeighbor = new FirstNeighbor(progress, 500);
        SecondNeighbor secondNeighbor = new SecondNeighbor(progress, 500);
        new Thread(firstNeighbor).start();
        new Thread(secondNeighbor).start();
    }
}
