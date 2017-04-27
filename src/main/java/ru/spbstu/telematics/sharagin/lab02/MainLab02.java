package ru.spbstu.telematics.sharagin.lab02;

import java.util.ArrayList;

/**
 * Created by maksim on 26.04.17.
 */
public class MainLab02 {
    public static void main(String[] args){
        MaksArrayList<String> stringMaksArrayListList = new MaksArrayList<String>();
        ArrayList<String> stringArrayList = new ArrayList<String>();

        stringMaksArrayListList.add("first");
        stringMaksArrayListList.add("second");
        stringMaksArrayListList.add("third");

        stringArrayList.add("first");
        stringArrayList.add("second");
        stringArrayList.add("third");

        System.out.println("Size:");
        System.out.print("MaksArrayList: " + stringMaksArrayListList.size()
                        + "; ArrayList: " + stringArrayList.size() + "\n");

        System.out.println("\nContains:");
        System.out.print("MaksArrayList: " + stringMaksArrayListList.contains("second")
                        + "; ArrayList: " + stringArrayList.contains("second") + "\n");

        System.out.println("\nGet:");
        System.out.print("MaksArrayList: " + stringMaksArrayListList.get(2)
                        + "; ArrayList: " + stringArrayList.get(2) + "\n");

        System.out.println("\nAdd:");
        System.out.print("MaksArrayList: " + stringMaksArrayListList.add("fourth")
                        + "; ArrayList: " + stringArrayList.add("fourth") + "\n");

        System.out.println("\nRemove:");
        System.out.print("MaksArrayList: " + stringMaksArrayListList.remove("second")
                        + "; ArrayList: " + stringArrayList.remove("second") + "\n");
    }
}
