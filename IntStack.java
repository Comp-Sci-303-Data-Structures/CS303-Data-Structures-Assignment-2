import java.util.ArrayList;
import java.util.EmptyStackException;

// CS-303 Assignment 2
// Yenghoua Lee
// March 17, 2026
public class IntStack {
    private final ArrayList<Integer> list;

    // ------------ //
    //  Constructor //
    // ------------ //

    /** Creates an empty stack. */
    public IntStack() {
        list = new ArrayList<>();
    }

    // ---------------------- //
    //  Core stack operations //
    // ---------------------- //

    /**
     * Pushes value onto the top of the stack.
     * @param value the integer to push
     */
    public void push(int value) {
        list.add(value);   // end of ArrayList = top of stack
    }

    /**
     * Removes and returns the top element of the stack.
     * @return the top integer
     * @throws EmptyStackException if the stack is empty
     */
    public int pop() {
        if (isEmpty()) {
            throw new EmptyStackException(); // consistent error handling
        }
        // remove the last element (the top)
        return list.remove(list.size() - 1);
    }

    /**
     * Returns the top element without removing it.
     * @return the top integer
     * @throws EmptyStackException if the stack is empty
     */
    public int peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.get(list.size() - 1);
    }

    /**
     * Returns true if the stack contains no elements.
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Returns the number of elements currently in the stack.
     */
    public int size() {
        return list.size();
    }

    // ----------------- //
    //  Custom method    //
    // ----------------  //

    /**
     * Computes and returns the average of all elements in the stack.
     * @return the arithmetic mean as a double
     * @throws EmptyStackException if the stack is empty
     */
    public double average() {
        if (isEmpty()) {
            throw new EmptyStackException(); // can't average an empty set
        }
        long sum = 0;
        for (int value : list) {
            sum += value;
        }
        return (double) sum / list.size();
    }

    // ------------- //
    //  Utility      //
    // ------------- //

    /**
     * Returns a string showing the stack from bottom to top
     * Stack (bottom → top): [1, 3, 7, 4]
     */
    @Override
    public String toString() {
        return "Stack (bottom → top): " + list.toString();
    }


    public static void main(String[] args) {
        System.out.println("=== IntStack Demo ===\n");

        // 1. Instantiate the stack
        IntStack stack = new IntStack();

        // 2. isEmpty() before any pushes
        System.out.println("isEmpty() before any pushes: " + stack.isEmpty());

        // 3. Push several values
        int[] valuesToPush = {4, 8, 15, 16, 23, 42};
        for (int v : valuesToPush) {
            stack.push(v);
            System.out.println("push(" + v + ")  → " + stack);
        }

        System.out.println("\nStack size after pushes: " + stack.size());

        // 4. pop() — remove and display top value
        int popped = stack.pop();
        System.out.println("\npop()  → removed value: " + popped);
        System.out.println("Stack after pop: " + stack);

        // 5. peek() — display current top without removing
        int top = stack.peek();
        System.out.println("\npeek() → current top: " + top);
        System.out.println("Stack unchanged:  " + stack);

        // 6. average() — display average of remaining elements
        double avg = stack.average();
        System.out.printf("%naverage() → %.2f%n", avg);

        // --- Edge-case: EmptyStackException on pop/peek ---
        System.out.println("\n--- Edge-case demo: pop() on empty stack ---");
        IntStack emptyStack = new IntStack();
        try {
            emptyStack.pop();
        } catch (EmptyStackException e) {
            System.out.println("Caught EmptyStackException from pop() ✓");
        }

        try {
            emptyStack.peek();
        } catch (EmptyStackException e) {
            System.out.println("Caught EmptyStackException from peek() ✓");
        }

        System.out.println("\nFinal stack size: " + stack.size());
        System.out.println("isEmpty(): " + stack.isEmpty());
    }
}
