package ru.spbstu.telematics.sharagin.lab05;

/**
 * Created by maksim on 02.05.17.
 */
public class FirstNeighbor implements Runnable {
    Progress progress;
    private int berryCount;

    FirstNeighbor(Progress progress, int berryCount){
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
        progress.flagFisrt = true;
        for(int i = 1; i <= berryCount; i++){
            progress.berryCountFirst = i;
            progress.GoFirstNeighbor();
        }
    }
}
