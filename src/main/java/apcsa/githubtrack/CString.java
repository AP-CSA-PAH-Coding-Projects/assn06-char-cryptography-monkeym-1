package apcsa.githubtrack;

public class CString {
    private char[] data;
     // Constructs a CString from a Java String.
     // Does not store the String object, only the char array.
     
    public CString(String s) {
        if (s == null) {
            this.data = new char[0];
        } else {
            this.data = s.toCharArray();
        }
    }
    public void reverse() {
        int left = 0;
        int right = data.length - 1;
        while (left < right) {
            char temp = data[left];
            data[left] = data[right];
            data[right] = temp;
            left++;
            right--;
        }
    }
    // Sorts characters in ascending order using Selection Sort.
    public void sortAscending() {
        for (int i = 0; i < data.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] < data[minIdx]) {
                    minIdx = j;
                }
            }
            char temp = data[minIdx];
            data[minIdx] = data[i];
            data[i] = temp;
        }
    }
    // Sorts characters in descending order using Insertion Sort.
    public void sortDescending() {
        for (int i = 1; i < data.length; i++) {
            char key = data[i];
            int j = i - 1;
            // Move elements greater than key to one position ahead
            while (j >= 0 && data[j] < key) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = key;
        }
    }
    public char[] getData() {
        return data;
    }
    public int length() {
        return data.length;
    }
    // Returns the CString.
    public String toString() {
        return new String(data);
    }
}
