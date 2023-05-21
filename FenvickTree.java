package semestrov2;

import java.util.Arrays;

public class FenvickTree {
    private int[] tree;
    private int[] sourceArray;
    public int COUNT_OF_OPERATIONS = 0;

    public FenvickTree(int[] sourceArray){
        getTree(sourceArray);
    }

    private void getTree(int[] sourceArray){
        this.sourceArray = sourceArray;
        this.tree = new int[sourceArray.length];
        for (int i = 0; i < sourceArray.length; i++){
            tree[i] = 0;
            }
        for (int i = 1; i <= sourceArray.length; i++) {
            inc(i, sourceArray[i-1]);
            }
        }


    public void deleteElement(int index){
        if(index < 1 || index >= tree.length){
            throw new IndexOutOfBoundsException("Этого индекса не существует. Передайте целое число от 1 до .. " + tree.length);
        }
        index = index-1;
        int[] array = new int[tree.length-1];
        int j = 0;
        for (int i = 0; i < tree.length; i++) {
            if(i != index){
                array[j] = sourceArray[i];
                j++;
            }
        }
        getTree(array);
        sourceArray = array;
    }

    public int getElement(int index){
        return getSum(index, index);
    }

    public int getSum(int l, int r){

        return getSum(r) - getSum(l-1);
    }

    public int getSum(int r){
        r = r-1;
        int i = r;
        int result = 0;
        while (i >= 0){
            COUNT_OF_OPERATIONS++;
            result += tree[i];
            i = (i & (i+1)) - 1;
        }
        return result;
    }

      public void inc(int index, int alpha){
        index = index - 1;
        sourceArray[index] += alpha;
        while (index < tree.length){
            tree[index] += alpha;
            index = index | (index + 1);
            COUNT_OF_OPERATIONS++;
        }
    }

    public int length(){
        return tree.length;
    }

   public void set(int index, int value){
        inc(index, value-getElement(index));
    }

    @Override
    public String toString() {
        return "FenvickTree{" +
                "tree=" + Arrays.toString(tree) +
                '}';
    }
}