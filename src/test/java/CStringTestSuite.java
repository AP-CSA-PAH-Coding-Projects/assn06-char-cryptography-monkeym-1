import static org.junit.jupiter.api.Assertions.*;

import apcsa.githubtrack.*;
import org.junit.jupiter.api.Test;

public class CStringTestSuite {

  // Converts a CString to a char[] using the provided toNumerical method
  // so tests can inspect the internal character sequence without requiring toString().
  private char[] toChars(CString s) {
    int[] nums = CStringUtil.toNumerical(s, 0);
    char[] result = new char[nums.length];
    for (int i = 0; i < nums.length; i++) {
      result[i] = (char) nums[i];
    }
    return result;
  }

  // Verifies that a CString contains the same character sequence as a normal String.
  private void assertCStringEquals(String expected, CString actual) {
    assertArrayEquals(expected.toCharArray(), toChars(actual));
  }

  // Confirms that the constructor correctly stores the characters from the input String.
  @Test
  public void constructorStoresCharacters() {
    CString s = new CString("hello");
    assertCStringEquals("hello", s);
  }

  // Verifies that reverse() mutates the CString by reversing its internal character order.
  @Test
  public void reverseMutatesCString() {
    CString s = new CString("math");
    s.reverse();
    assertCStringEquals("htam", s);
  }

  // Ensures reverse works correctly on odd-length strings.
  @Test
  public void reverseHandlesOddLength() {
    CString s = new CString("abcde");
    s.reverse();
    assertCStringEquals("edcba", s);
  }

  // Confirms that sortAscending produces lexicographically increasing order.
  @Test
  public void sortAscendingOrdersCharacters() {
    CString s = new CString("zebra");
    s.sortAscending();
    assertCStringEquals("aberz", s);
  }

  // Ensures ascending sort handles repeated characters correctly.
  @Test
  public void sortAscendingHandlesDuplicates() {
    CString s = new CString("banana");
    s.sortAscending();
    assertCStringEquals("aaabnn", s);
  }

  // Confirms that sortDescending produces lexicographically decreasing order.
  @Test
  public void sortDescendingOrdersCharacters() {
    CString s = new CString("zebra");
    s.sortDescending();
    assertCStringEquals("zreba", s);
  }

  // Ensures descending sort handles repeated characters correctly.
  @Test
  public void sortDescendingHandlesDuplicates() {
    CString s = new CString("banana");
    s.sortDescending();
    assertCStringEquals("nnbaaa", s);
  }

  // Verifies that a single character counts as a palindrome.
  @Test
  public void palindromeSingleChar() {
    assertTrue(CStringUtil.isPalindrome(new CString("a")));
  }

  // Confirms palindrome detection ignores spaces and letter casing.
  @Test
  public void palindromeIgnoresCaseAndSpaces() {
    assertTrue(CStringUtil.isPalindrome(new CString("Taco cat")));
  }

  // Confirms that non-palindromes are correctly rejected.
  @Test
  public void palindromeFalseCase() {
    assertFalse(CStringUtil.isPalindrome(new CString("rxrx")));
  }

  // Ensures toNumerical correctly converts characters to ASCII values.
  @Test
  public void toNumericalNoOffset() {
    CString s = new CString("abc");
    int[] expected = {97, 98, 99};
    assertArrayEquals(expected, CStringUtil.toNumerical(s, 0));
  }

  // Confirms that the offset parameter is applied to every ASCII value.
  @Test
  public void toNumericalWithOffset() {
    CString s = new CString("abc");
    int[] expected = {197, 198, 199};
    assertArrayEquals(expected, CStringUtil.toNumerical(s, 100));
  }

  // Confirms that negative values work with the offset parameter.
  @Test
  public void toNumericalWithNegativeOffset() {
    CString s = new CString("abc");
    int[] expected = {92, 93, 94};
    assertArrayEquals(expected, CStringUtil.toNumerical(s, -5));
  }

  // Tests maxMirror using the example from the README specification.
  @Test
  public void maxMirrorIntArrayExample1() {
    int[] arr = {1, 2, 3, 8, 9, 3, 2, 1};
    assertEquals(3, CStringUtil.maxMirror(arr));
  }

  // Tests a mirror section that includes the entire front portion of the array.
  @Test
  public void maxMirrorIntArrayExample2() {
    int[] arr = {1, 2, 1, 4};
    assertEquals(3, CStringUtil.maxMirror(arr));
  }

  // Tests mirror detection when mirrored elements are separated by other values.
  @Test
  public void maxMirrorIntArrayExample3() {
    int[] arr = {7, 1, 2, 9, 7, 2, 1};
    assertEquals(2, CStringUtil.maxMirror(arr));
  }

  // Tests mirror detection when mirrored elements are separated by other values.
  @Test
  public void maxMirrorIntArrayExample4() {
    int[] arr = {5, 9, 9, 4, 5, 4, 9, 9, 2};
    assertEquals(7, CStringUtil.maxMirror(arr));
  }

  // Tests mirror detection with empty array.
  @Test
  public void maxMirrorIntArrayExample5() {
    int[] arr = {};
    assertEquals(0, CStringUtil.maxMirror(arr));
  }

  // Tests mirror detection with 'rainbow' array.
  @Test
  public void maxMirrorIntArrayExample6() {
    int[] arr = {1, 2, 3, 4, 5, 4, 3, 2, 1};
    assertEquals(9, CStringUtil.maxMirror(arr));
  }

  // Tests mirror detection with larger array.
  @Test
  public void maxMirrorIntArrayExample7() {
    int[] arr = {21, 22, 9, 8, 7, 6, 23, 24, 6, 7, 8, 9, 25, 7, 8, 9};
    assertEquals(4, CStringUtil.maxMirror(arr));
  }

  // Tests mirror detection with array of length 1.
  @Test
  public void maxMirrorIntArrayExample8() {
    int[] arr = {1};
    assertEquals(1, CStringUtil.maxMirror(arr));
  }

  // Verifies the overloaded maxMirror method that accepts a CString.
  @Test
  public void maxMirrorCStringOverload() {
    CString s = new CString("abccba");
    assertEquals(3, CStringUtil.maxMirror(s));
  }

  // Verifies the overloaded maxMirror for other CString inputs.
  @Test
  public void maxMirrorCStringOverload2() {
    CString s = new CString("");
    assertEquals(0, CStringUtil.maxMirror(s));
  }

  // Verifies the overloaded maxMirror for other CString inputs.
  @Test
  public void maxMirrorCStringOverload3() {
    CString s = new CString("tacocat");
    assertEquals(7, CStringUtil.maxMirror(s));
  }

  // Confirms memeifyArray rearranges elements so each 6 is immediately followed by 7.
  @Test
  public void memeifySimpleCase() {
    int[] input = {1, 6, 1, 7};
    int[] expected = {1, 6, 7, 1};
    assertArrayEquals(expected, CStringUtil.memeifyArray(input));
  }

  // Tests memeifyArray with multiple 6/7 pairs and additional movable elements.
  @Test
  public void memeifyMultiplePairs() {
    int[] input = {1, 6, 1, 7, 7, 6, 1};
    int[] expected = {1, 6, 7, 1, 1, 6, 7};
    assertArrayEquals(expected, CStringUtil.memeifyArray(input));
  }

  // Tests memeifyArray with various inputs
  @Test
  public void memeifyExample3() {
    int[] input = {7, 7, 6, 1, 6, 1};
    int[] expected = {1, 1, 6, 7, 6, 7};
    assertArrayEquals(expected, CStringUtil.memeifyArray(input));
  }

  @Test
  public void memeifyExample4() {
    int[] input = {6, 2, 6, 7, 7};
    int[] expected = {6, 7, 6, 7, 2};
    assertArrayEquals(expected, CStringUtil.memeifyArray(input));
  }

  @Test
  public void memeifyExample5() {
    int[] input = {1, 1, 1};
    int[] expected = {1, 1, 1};
    assertArrayEquals(expected, CStringUtil.memeifyArray(input));
  }

  @Test
  public void memeifyExample6() {
    int[] input = {};
    int[] expected = {};
    assertArrayEquals(expected, CStringUtil.memeifyArray(input));
  }

  // Verifies nestedSequence returns true when all characters of inner appear in outer.
  @Test
  public void nestedSequenceTrueCase() {
    CString outer = new CString("abeg");
    CString inner = new CString("be");
    assertTrue(CStringUtil.nestedSequence(outer, inner));
  }

  // Confirms nestedSequence returns false when inner contains a character not in outer.
  @Test
  public void nestedSequenceFalseCase() {
    CString outer = new CString("abeg");
    CString inner = new CString("bef");
    assertFalse(CStringUtil.nestedSequence(outer, inner));
  }

  // Testing nestedSequence for other inputs.
  @Test
  public void nestedSequenceCase3() {
    CString outer = new CString("abc");
    CString inner = new CString("");
    assertTrue(CStringUtil.nestedSequence(outer, inner));
  }

  public void nestedSequenceCase4() {
    CString outer = new CString("cdgggnp");
    CString inner = new CString("cno");
    assertFalse(CStringUtil.nestedSequence(outer, inner));
  }

  public void nestedSequenceCase5() {
    CString outer = new CString("cdgggnp");
    CString inner = new CString("cdgp");
    assertTrue(CStringUtil.nestedSequence(outer, inner));
  }

  // Verifies decrypt correctly performs the transformation described in the README.
  @Test
  public void decryptExample1() {
    CString result = CStringUtil.decrypt(new CString("APCSA rocks"));
    assertCStringEquals("skcor ASCPA", result);
  }

  // Additional decrypt example verifying clump analysis and reversal behavior.
  @Test
  public void decryptExample2() {
    CString result = CStringUtil.decrypt(new CString("study hard"));
    assertCStringEquals("drah yduts", result);
  }

  // Third decrypt example using a single word with no spaces.
  @Test
  public void decryptExample3() {
    CString result = CStringUtil.decrypt(new CString("polymorphism"));
    assertCStringEquals("msihpromylop", result);
  }

  // Confirms rotate shifts elements left by one position with wraparound.
  @Test
  public void rotateExample1() {
    CString[] arr = {
      new CString("alpha"), new CString("beta"), new CString("gamma"), new CString("delta")
    };

    Main.rotate(arr, 1);

    assertCStringEquals("beta", arr[0]);
    assertCStringEquals("gamma", arr[1]);
    assertCStringEquals("delta", arr[2]);
    assertCStringEquals("alpha", arr[3]);
  }

  // Confirms rotate correctly handles shifting by multiple positions.
  @Test
  public void rotateExample2() {
    CString[] arr = {
      new CString("red"),
      new CString("green"),
      new CString("blue"),
      new CString("yellow"),
      new CString("purple")
    };

    Main.rotate(arr, 2);

    assertCStringEquals("blue", arr[0]);
    assertCStringEquals("yellow", arr[1]);
    assertCStringEquals("purple", arr[2]);
    assertCStringEquals("red", arr[3]);
    assertCStringEquals("green", arr[4]);
  }

  // Confirms rotate correctly wraps elements when shifting by more than half the array.
  @Test
  public void rotateExample3() {
    CString[] arr = {
      new CString("north"), new CString("south"), new CString("east"), new CString("west")
    };

    Main.rotate(arr, 3);

    assertCStringEquals("west", arr[0]);
    assertCStringEquals("north", arr[1]);
    assertCStringEquals("south", arr[2]);
    assertCStringEquals("east", arr[3]);
  }

  // Ensures rotate handles values larger than the array length using wraparound behavior.
  @Test
  public void rotateWraparound() {
    CString[] arr = {new CString("a"), new CString("b"), new CString("c"), new CString("d")};

    Main.rotate(arr, 7);

    assertCStringEquals("d", arr[0]);
    assertCStringEquals("a", arr[1]);
    assertCStringEquals("b", arr[2]);
    assertCStringEquals("c", arr[3]);
  }
}
