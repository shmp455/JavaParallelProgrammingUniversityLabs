package ru.spbstu.telematics.sharagin.lab02;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by maksim on 26.04.17.
 */
public class MaksArrayListTestCase {
    @Test
    public void TestAddAfterRemove(){
        final MaksArrayList<String> stringMaksArrayList = new MaksArrayList<String>();
        stringMaksArrayList.add("f");
        stringMaksArrayList.add("s");
        stringMaksArrayList.remove("s");
        stringMaksArrayList.remove("f");
        stringMaksArrayList.add("t");
        Assert.assertEquals("t", stringMaksArrayList.get(0));
    }

    @Test
    public void TestAdd(){
        final MaksArrayList<Integer> integerMaksArrayList = new MaksArrayList<Integer>();
        for(int i = 0; i < 5; i++){
            Assert.assertTrue(integerMaksArrayList.add(i));
        }
    }

    @Test
    public void TestGet(){
        final MaksArrayList<Integer> integerMaksArrayList = new MaksArrayList<Integer>();
        for(Integer i = 0; i < 5; i++){
            integerMaksArrayList.add(i);
            Assert.assertEquals(i, integerMaksArrayList.get(i));
        }
    }

    @Test
    public void TestSize(){
        final MaksArrayList<Integer> integerMaksArrayList = new MaksArrayList<Integer>();
        for(Integer i = 0; i < 5; i++){
            integerMaksArrayList.add(i);
            Assert.assertEquals(i + 1, integerMaksArrayList.size());
        }
    }

    @Test
    public void TestRemove(){
        final MaksArrayList<String> stringMaksArrayList = new MaksArrayList<String>();
        stringMaksArrayList.add("f");
        Assert.assertTrue(stringMaksArrayList.remove("f"));
        Assert.assertFalse(stringMaksArrayList.remove("s"));
    }

    @Test
    public void TestContains(){
        final MaksArrayList<String> stringMaksArrayList = new MaksArrayList<String>();
        stringMaksArrayList.add("f");
        Assert.assertTrue(stringMaksArrayList.contains("f"));
        Assert.assertFalse(stringMaksArrayList.contains("s"));
    }
}
