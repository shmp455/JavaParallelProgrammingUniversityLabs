package ru.spbstu.telematics.sharagin.lab05;

/**
 * Created by maksim on 02.05.17.
 */
public class Progress {
    public boolean flagFisrt;
    public boolean flagSecond;
    public int berryCountFirst;
    public int berryCountSecond;

    public synchronized void GoFirstNeighbor(){
        while (flagSecond){
            try {
                wait();
                this.flagFisrt = true;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("Ягод собрано первым соседом (всего): " + berryCountFirst);
        this.flagFisrt = false;
        notify();
    }

    public synchronized void GoSecondNeighbor(){
        while (flagFisrt){
            try {
                wait();
                this.flagSecond = true;
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("Ягод собрано вторым соседом (всего): " + berryCountSecond);
        this.flagSecond = false;
        notify();
    }
}
