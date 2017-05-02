package ru.spbstu.telematics.sharagin.lab05;

/**
 * Created by maksim on 02.05.17.
 */
public class SecondNeighbor implements Runnable {
    Progress progress;
    private int berryCount;

    SecondNeighbor(Progress progress, int berryCount){
        this.progress = progress;
        this.berryCount = berryCount;
    }

    @Override
    public void run(){
        if(berryCount <= 0)
        {
            System.out.println("Some problems with berryCount");
            return;
        }
        for(int i = 1; i <= berryCount; i++){
            progress.berryCountSecond = i;
            progress.GoSecondNeighbor();
        }
    }
}
