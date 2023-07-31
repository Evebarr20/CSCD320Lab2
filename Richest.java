import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.File;
public class Richest {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File(args[0])); // create scanner to open file that user enters as a command parameter
        PrintStream richestOutput = new PrintStream("richest.output"); //Create a txt file that has output
        int[] arrays = new int[10001]; // initialized the array
        for(int i = 1; i < 10000; i++) { // for loop that goes through the file and inserts the first 10,000
            if(input.hasNextLine()) { //checks to see if the file has a next line
                int count = Integer.parseInt(input.nextLine());
                arrays[i] = count;
            }
        }

        Heap heap = new Heap();
        heap.Min_Heap_sort(arrays); // call the heap sort method

        if (input.hasNextLine()) { // do while loop that sorts
            do {
                int number = Integer.parseInt(input.nextLine());
                if (arrays[10000] < number) {
                    arrays[10000] = number;
                    heap.Min_Heap_sort(arrays); //call min sorts
                }
            } while (input.hasNextLine());
        }
        input.close(); //close scanner
        System.setOut(richestOutput); //print out output of code into file
        Heap.printHeap(arrays); //call the printHeap method in the Heap class


}


    public static class Heap { //create public static Heap class
        public void swap(int[]A, int x , int y) { //swap method: swap x and y
            int temp = A[x];
            A[x] = A[y];
            A[y] = temp;
        }
        public void Build_Min_Heap(int[] A) {
            int size = A.length;
            for (int i = size / 2 - 1; i >= 0; i--)
                Min_heapify(A, size, i); //call the Min_heapify method
        }
        public void Min_Heap_sort(int A[]) {
            Build_Min_Heap(A); //call the Build_Min_Heap method
            for (int i = A.length - 1; i > 0; i--) {
                swap(A, 0, i); //call the swap method
                Min_heapify(A, i, 0); //call the Min_heapify method
            }
        }


        void Min_heapify(int A[], int size, int i) {
            int parent = (int) Math.floor(i); //parent node
            int leftChild = (int) Math.floor(2 * i); //left child tree node
            int rightChild = (int) Math.floor(2 * i) + 1; //right child tree node

            if (leftChild < size && A[leftChild] < A[parent]) //if leftchild tree node is less than parent node
                parent = leftChild; //set parent to left child


            if (rightChild < size && A[rightChild] < A[parent]) //if rightChild tree node is less than parent node
                parent = rightChild; //set parent to rightchild

            if (parent != i) { //if parent doesn't equal to i
                swap(A, i, parent); //then call the swap method
                Min_heapify(A, size, parent); //recursive call Min_heapify
            }
        }

        static void printHeap(int A[]) { //print heap method prints out array
            int size = A.length; //size is set to array length

            for (int j : A) System.out.println(j + " "); //goes through the array and prints out each line
            System.out.println();
        }
    }
}
