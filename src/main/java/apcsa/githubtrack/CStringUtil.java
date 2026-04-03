package apcsa.githubtrack;

import java.util.ArrayList;
import java.util.List;

public class CStringUtil {

    // Private constructor to prevent instantiation
    private CStringUtil() {
    }
    public static boolean isPalindrome(CString str) {
        String clean = str.toString().toLowerCase().replace(" ", "");
        int left = 0;
        int right = clean.length() - 1;
        while (left < right) {
            if (clean.charAt(left) != clean.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static int[] toNumerical(CString str, int offset) {
        char[] chars = str.getData();
        int[] nums = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            nums[i] = (int) chars[i] + offset;
        }
        return nums;
    }

    public static int maxMirror(CString str) {
        int[] nums = toNumerical(str, 0);
        return maxMirror(nums);
    }

    public static int maxMirror(int[] nums) {
        if (nums.length == 0) return 0;
        int maxLen = 0;

        // Check lengths from largest possible down to 1
        for (int len = nums.length; len >= 1; len--) {
            // Check every subarray of this length
            for (int i = 0; i <= nums.length - len; i++) {
                int[] sub = new int[len];
                for (int k = 0; k < len; k++) {
                    sub[k] = nums[i + k];
                }

                // Check if reverse of sub exists in nums
                if (isReversePresent(nums, sub)) {
                    return len;
                }
            }
        }
        return maxLen;
    }
    // Helper to check if reverse of sub exists in arr
    private static boolean isReversePresent(int[] arr, int[] sub) {
        int[] reversedSub = new int[sub.length];
        for (int i =0; i < sub.length; i++) {
            reversedSub[i] = sub[sub.length - 1 - i];
        }

        // Search for reversedSub in arr
        for (int i = 0; i <= arr.length - reversedSub.length; i++) {
            boolean match = true;
            for (int j = 0; j < reversedSub.length; j++) {
                if (arr[i + j] != reversedSub[j]) {
                    match = false;
                    break;
                }
            }
            if (match) 
                return true;
        }
        return false;
    }

    public static int[] memeifyArray(int[] nums) {
        List<Integer> others = new ArrayList<>();
        int sixCount = 0;
        int sevenCount = 0;

        // Count 6s and 7s and collect others
        for (int n : nums) {
            if (n == 6) 
                sixCount++;
            else if (n == 7) 
                sevenCount++;
            else 
                others.add(n);
        }
        int[] res = new int[nums.length];
        int surplus7s = sevenCount - sixCount;
        for (int i = 0; i < surplus7s; i++) {
            others.add(7);
        }
        int rIdx = 0;
        // Place 6-7 pairs
        for(int i=0; i<sixCount; i++) {
            res[rIdx++] = 6;
            res[rIdx++] = 7;
        }
        // Place others
        for(int val : others) {
            res[rIdx++] = val;
        }
        
        return res;
    }


    public static boolean nestedSequence(CString outer, CString inner) {
        // Create copies to sort without modifying originals
        CString outerSorted = new CString(outer.toString());
        CString innerSorted = new CString(inner.toString());
        outerSorted.sortAscending();
        innerSorted.sortAscending();
        int[] outerNum = toNumerical(outerSorted, 0);
        int[] innerNum = toNumerical(innerSorted, 0);
        int i = 0; // outer index
        int j = 0; // inner index
        while (i < outerNum.length && j < innerNum.length) {
            if (outerNum[i] == innerNum[j]) {
                j++;
            }
            i++;
        }
        return j == innerNum.length;
    }

    public static CString decrypt(CString str) {
        int[] nums = toNumerical(str, 0);
        int clumps = 0;

        // Count clumps
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                clumps++;
                // Skip duplicates
                while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                    i++;
                }
            }
        }

        // Shift characters back
        char[] shifted = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            shifted[i] = (char) (str.getData()[i] - clumps);
        }

        // Create new CString and reverse it
        CString result = new CString(new String(shifted));
        result.reverse();

        return result;
    }
}
