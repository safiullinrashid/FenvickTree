package semestrov2;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[100_000_000];
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data.txt"));
            int i = 0;
            while (reader.ready()){
                array[i] = Integer.parseInt(reader.readLine());
                i++;
            }
            long start = System.currentTimeMillis();
            FenvickTree fenvickTree = new FenvickTree(array);
            long time = System.currentTimeMillis() - start;
            System.out.println("Creating long time: " + time);
            System.out.println("Creating long operations: " + fenvickTree.COUNT_OF_OPERATIONS);
            fenvickTree.COUNT_OF_OPERATIONS = 0;


            long sumTime = 0;
            long sumOperations = 0;
            for (int j = 0; j < 100; j++) {
                int l = (int)(Math.random()*(fenvickTree.length() - 1)+1);
                int r = (int)(Math.random()*(fenvickTree.length() - 1)+1);
                start = System.currentTimeMillis();
                fenvickTree.getSum(l, r);
                time = System.currentTimeMillis() - start;
                sumTime += time;
                sumOperations += fenvickTree.COUNT_OF_OPERATIONS;
                fenvickTree.COUNT_OF_OPERATIONS = 0;
            }
            System.out.println("Sum long time: " + sumTime/100);
            System.out.println("Sum long operations: " + sumOperations/100);


            long setTime = 0;
            long setOperations = 0;
            for (int j = 0; j < 100; j++) {
                int index = (int)(Math.random()*(fenvickTree.length() - 1)+1);
                start = System.currentTimeMillis();
                fenvickTree.set(index, (int)(Math.random()*10000));
                time = System.currentTimeMillis() - start;
                setTime += time;
                setOperations += fenvickTree.COUNT_OF_OPERATIONS;
                fenvickTree.COUNT_OF_OPERATIONS = 0;
            }
            System.out.println("Change long time: " + setTime/100);
            System.out.println("Change long operations: " + setOperations/100);

            long deleteTime = 0;
            long deleteOperations = 0;
            for (int j = 0; j < 100; j++) {
                int index = (int)(1 + Math.random()*(fenvickTree.length()));
                start = System.currentTimeMillis();
                fenvickTree.deleteElement(index);
                time = System.currentTimeMillis() - start;
                deleteTime += time;
                deleteOperations += fenvickTree.COUNT_OF_OPERATIONS;
                fenvickTree.COUNT_OF_OPERATIONS = 0;
            }
            System.out.println("Delete long time: " + deleteTime/100);
            System.out.println("Delete long operations: " + deleteOperations/100);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}