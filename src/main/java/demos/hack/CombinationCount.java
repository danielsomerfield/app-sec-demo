package demos.hack;

import java.math.BigInteger;
import java.util.stream.Stream;

public class CombinationCount {


    private static final double MAC_SPEED = 1000000;
    private static final double CLUSTER_SPEED = 180000000000d;

    public static void main(final String [] args) {
        printHackSpeeds(10, 5);
        printHackSpeeds(62, 5);
        printHackSpeeds(62, 10);
        printHackSpeeds(96, 10);
    }

    private static void printHackSpeeds(final int range, final int count) {
        System.out.printf("%s Digits %s%n", count, combination(range, count));
        System.out.printf("Hack by a mac in %s%n", combination(range, count) / MAC_SPEED);
        System.out.printf("Hack by a mac in %s years %n",
                (combination(range, count) / MAC_SPEED) / (60 * 60 * 24 * 365));
        System.out.printf("Hack by a cluster in %s%n", combination(range, count) / CLUSTER_SPEED);
        System.out.printf("Hack by a cluster in %s years %n",
                (combination(range, count) / CLUSTER_SPEED) / (60 * 60 * 24 * 365));
    }

    public static double combination(final double range, final double count) {
        return Math.pow(range, count);
    }

    public static BigInteger perms(final long range, final long count) {
        return fact(range).divide(fact(range - count));
    }

    private static BigInteger fact(final long range) {
        return Stream.iterate(BigInteger.ONE, x -> x.add(BigInteger.ONE)).limit(range)
                .reduce(BigInteger.ONE, BigInteger::multiply);
    }


}
