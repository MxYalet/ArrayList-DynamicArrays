/*
 * I attest that the code in this file is entirely my own except for the starter
 * code provided with the assignment and the following exceptions:
 * <
 * Enter all external resources and collaborations here. Note external code may
 * reduce your score but appropriate citation is required to avoid academic
 * integrity violations. Please see the Course Syllabus as well as the
 * university code of academic integrity:
 *  https://catalog.upenn.edu/pennbook/code-of-academic-integrity/
 * >
 * Signed,
 * Author: YOUR NAME HERE
 * Penn email: <YOUR-EMAIL-HERE@seas.upenn.edu>
 * Date: <YYYY-MM-DD>
 */
import java.util.Arrays;
import java.util.Objects;

public class MyArrayList<E> {

    /*
     * Do not change this initial capacity; it is used by our test cases
     */
    private static final int INITIAL_CAPACITY = 4;

    /*
     * These are protected so that test cases can access them. Please do not change
     * the visibility of these fields!
     */
    protected Object[] data;
    protected int size = 0;


    public MyArrayList() {
        data = new Object[INITIAL_CAPACITY];
    }

    /*
     * The requirements for this constructor are in
     * Create Constructor Section in the assignment pdf
     */
    public MyArrayList(E[] arr) {
        if (arr == null || arr.length == 0) {
            data = new Object[INITIAL_CAPACITY];
        } else {
            size = arr.length;
            int newArrayLength = Math.max(arr.length, INITIAL_CAPACITY);
            data = new Object[newArrayLength];
            System.arraycopy(arr, 0, data, 0, arr.length);
        }
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else
            return (E) data[index];
    }

    private void increaseCapacity() {
        String[] newData = new String[Math.max(2 * data.length, INITIAL_CAPACITY)];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    /*
     * This method adds the element to the list. Except for modifying it to use Java
     * Generics, DO NOT OTHERWISE CHANGE THIS METHOD as it is used in testing your
     * code.
     */
    public void add(E value) {
        if (size == data.length) {
            increaseCapacity();
        }
        data[size++] = value;
    }

    public void add(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == data.length) {
            increaseCapacity();
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = element;
        size++;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E target = (E) data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size] = null;

        // Check if need be to shrink the array
        if (size <= 0.25 * data.length && data.length > INITIAL_CAPACITY) {
            shrinkCapacity();
        }
        return target;
    }

    /*
     * The requirements for this method are in
     * remove method Section in the assignment pdf
     */
    public boolean remove(E obj) {
        for (int i = 0; i < size; i++) {
            if (obj.equals(data[i])) {

                // Adjust elements to fill the empty space
                for (int j = i; j < size - 1; j++) {
                    data[j] = data[j + 1];
                }
                data[size - 1] = null;
                size--;

                // Check if array needs to be shrunk
                if (size <= 0.25 * data.length && data.length > INITIAL_CAPACITY) {
                    shrinkCapacity();
                }
                return true;
            }
        }
        return false;
    }

    private void shrinkCapacity() {
        // Ensure the capacity doesn't go below the initial capacity
        int newCapacity = Math.max(INITIAL_CAPACITY, data.length / 2);
        data = Arrays.copyOf(data, newCapacity);
    }




    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + ": " + data[i]);
        }
    }

    public boolean contains(E obj) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(data[i], obj))
                return true;
        }
        return false;
    }

    /*
     * The requirements for this method are in
     * set(index, value) method Section in the assignment pdf
     */
    public E set(int index, E obj) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E oldValue = (E) data[index];
        data[index] = obj;
        return oldValue;
    }

    /*
     * The requirements for this method are in
     * indexOf method Section in the assignment pdf
     */
    public int indexOf(E obj) {
        for (int i = 0; i < size; i++) {
            if (obj.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }
}
