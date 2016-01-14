package main.java.com.rekkeb.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Main class showing the use of streams
 *
 * Created by rekkeb on 6/1/16.
 */
public class StreamsExample {

    public static void main(String[] args) {

        computeElementsInStream((a, b) -> a*b);

        computeElementsInCollection((a, b) -> a*b);

    }

    private static void computeElementsInStream(Operation op) {
        //Iterate with streams
        List<Integer> elementsStream = generateData();

        long startStream = System.nanoTime();
        System.out.print("Starting stream iteration...");

        elementsStream.stream()
                .map(e -> op.apply(e,e))
                .collect(Collectors.toList());

        long durationStream = (System.nanoTime() - startStream) / 1_000_000;
        System.out.printf(" Done in %d msecs\n", durationStream);
    }


    private static void computeElementsInCollection(Operation op) {
        //Iterate with collections
        List<Integer> elements = generateData();

        long start = System.nanoTime();
        System.out.print("Starting iterator iteration...");

        List<Integer> result = new ArrayList<>();

        for (Integer element : elements) {
            result.add(op.apply(element, element));
        }

        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.printf(" Done in %d msecs\n", duration);
    }

    private static List<Integer> generateData() {

        return IntStream.rangeClosed(0,10000000).boxed().collect(Collectors.toList());
    }

    @FunctionalInterface
    interface Operation {
        Integer apply (Integer a, Integer b);
    }
}
