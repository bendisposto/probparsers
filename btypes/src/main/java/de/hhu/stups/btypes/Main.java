package de.hhu.stups.btypes;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by fabian on 05.06.18.
 */
public class Main {

    public static void main(String[] args) {
        //This is just a main function for testing
        Set set1 = new HashSet<BObject>(Arrays.asList(
                new BInteger(new BigInteger("1")),new BInteger(new BigInteger("2")),new BInteger(new BigInteger("3"))));
        Set set2 = new HashSet<BObject>(Arrays.asList(
                new BInteger(new BigInteger("1"))));
        BSet a = new BSet(set1);
        BSet b = new BSet(set2);
        System.out.println(a.intersect(b));
    }

}
