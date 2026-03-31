[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/mkbadQSU)
# 🔐 Assn06 — `char` Cryptography

Throughout the year, you’ve learned about **classes, arrays, algorithms, data manipulation,** and **object-oriented design**. This project combines many of those ideas into one cumulative challenge, where you'll be creating a 'mock' C-Style string class, building tools to manipulate those strings, analyzing sequences of characters and numbers, and ultimately decrypting a secret message stored in a file!

## 🧠 Explanation & Reflection (Required)

Before diving into your implementation, you must include a **brief explanation of your thinking**. This may be written in either:

* A separate file, located in `src\main\resources\STUDENT_README.md`  
**OR**
* Clear, insightful comments within your code

Your comments or README file must:

* Explain **what your program does at a high level**
* Explain **why your logic works** (especially for loops, `ArrayList` processing, and the Monsters' attack logic)
* Mention any **challenges, assumptions,** or **design decisions** you made

> Remember: On the AP Exam, correct output alone is not enough — you earn points for demonstrating **reasoning and program design**.


---
# Part 0 — Why Learn About C Strings?

While Java manages strings automatically through the `String` class, the programming language **C** stores strings differently. In C, a string is **not an object** but rather an **array of characters** terminated by a special null character, '\0'. For example:

```
['h','e','l','l','o','\0']
```

Understanding this representation is valuable because:

* It reveals how strings are stored **in your computer's memory**
* It strengthens your understanding of **arrays** and **indexing**
* It deepens your understanding of how Java strings work under the hood

In this project, you will create a mock **C-style string class** (minus the terminating null character).


## Your Mission
A mysterious message has been discovered in a file named:

```
secretMessage.txt
```

Each word in the file, which is located in the `resources` directory, has been encrypted using an algorithm that manipulates characters and numeric representations.

Your job is to implement a set of classes that can represent and manipulate C-style strings, convert characters into numerical data, detect patterns such as palindromes and mirrored sequences, apply algorithmic transformations, decrypt each word, and reassemble a final message.

## Part 1 — The `CString` Class

The `CString` class represents a C-style string. However, unlike in C, your CString will not involve a null terminator. For example,
```
CString word = new CString("hello");
```
should construct a `CString` representing the argument's sequence of characters. The object should not store the Java `String` itself, but instead maintain its own internal representation that allows direct indexed access to each character.

### The `CString` class should have the following behaviors:

*  `reverse()`: Reverses the characters in this `CString`,
modifying the object's state and returning nothing.

    Example:
    ```
    Before: hello
    After:  olleh
    ```
---

* `sortAscending()`: Sorts the characters in ascending lexicographic order using the **Selection Sort algorithm**.

    Example:
    ```
    Input:  zebra
    Output: aberz
    ```
    To receive full credit, you **must implement Selection Sort**.

---

* `sortDescending()`: Sorts the characters in descending lexicographic order using the **Insertion Sort algorithm**.

    Example:
    ```
    Input:  zebra
    Output: zrbea
    ```
    To receive full credit, you **must implement Insertion Sort**.

<br>

## Part 2 — `CStringUtil` Class

`CStringUtil` is a **utility class**. A utility class is a stateless collection of static methods and constants, designed for reuse across a program, such as for math, string manipulation, or file handling.

---
### The `CStringUtil` class should have the following behaviors:

* `isPalindrome()`: Returns `true` if the `CString` is a **palindrome** and `false` otherwise. The spacing and letter casing of the string should be ignored.

    Examples:

    ```
    "a" → true
    "Taco cat" → true
    "madam" → true
    "ab" → false
    "rxrx" → false
    ```
---

* `toNumerical(CString str, int offset)`: Returns an integer array representing the ASCII values of each character in the `CString`. Additionally, each value should be shifted by `offset`.

    Example:
    ```
    CString str = "hello";

    toNumerical(str, 0)
    → [104,101,108,108,111]

    toNumerical(str, -100)
    → [4,1,8,8,11]
    ```

---

* `maxMirror(CString str)`: Let a mirror section be defined as a group of contiguous elements such that somewhere else in the array, the same group appears in *reverse* order.

    Example:

    ```
    [1,2,3,8,9,3,2,1]
    ```

    The corresponding mirror section would be:

    ```
    1 2 3
    ```

    `maxMirror` should return the **length** of the largest mirror section.

    Examples:

    ```
    maxMirror([1,2,3,8,9,3,2,1]) → 3
    maxMirror([1,2,1,4]) → 3
    maxMirror([7,1,2,9,7,2,1]) → 2
    ```

---

Additionally, you must overload the method such that either a `CString` or an `int[]` would be an accepted parameter. If the argument is a `CString`, you must convert it to its numerical counterpart.

* `memeifyArray(int[] nums)`: Returns a new array containing the same numbers in `nums`, but rearranged so that **every 6 is immediately followed by a 7**. Do not move the 6's, but every other number may move. The returned array must contain the same number of 6's and 7's, and every 6 should have a number after it that is not a 6. 7's may appear anywhere in the original array.

    Examples:
    ```
    [7,4,9,6,9,7]
    → [4,9,6,7,7,9]

    [1,6,1,7]
    → [1,6,7,1]

    [1,6,1,7,7,6,1]
    → [1,6,7,1,1,6,7]
    ```
---
* `nestedSequence(CString outer, CString inner)`: Given two CStrings, `inner` and `outer`, convert them to integer arrays and sort them in increasing order. Do not have redundant code here; you must invoke already-implemented methods where appropriate. Return `true` if all of the characters in `inner` appear in `outer`, and `false` otherwise. Your solution should make only a single "linear" pass of both arrays, leveraging the fact that both arrays have been sorted.

    Examples:
    ```
    nestedSequence([1,2,4,6], [2,4]) → true

    nestedSequence([1,2,4,6], [2,3,4]) → false

    nestedSequence([1,2,4,4,6], [2,4]) → true
    ```
---

* `decrypt(CString str)`: Returns a decrypted version of `str`. To determine the transformation, first analyze the **numerical representation** of the string and count the number of **clumps**, where a clump is defined as a sequence of two or more adjacent elements with the same value (for example, `[1,2,2,3,4,4]` contains two clumps, whereas `[1,1,1,1,1]` contains one clump). The number of clumps computed should influence how the characters in the original `CString` are transformed by shifting their ASCII values **backward** accordingly. After applying this transformation, the resulting sequence of characters should then be **reversed**, and the final result returned as a new `CString`. 

    Examples:
    ```
    decrypt(new CString("APCSA rocks")) → "skcor ASCPA"

    decrypt(new CString("study hard")) → "drah yduts"

    decrypt(new CString("polymorphism")) → "msihpromylop"
    ```

<br>

## Part 3 — `Main` Class

## `Main` Class Responsibilities

The `Main` class is responsible for orchestrating the final decoding of the hidden message. Your program should read the contents of the file `secretMessage.txt`, treating each word (distinguished by whitespaces) in the file as an individual `CString` and storing them in an appropriate array. Each word must then be decrypted using the appropriate method you implemented earlier. However, even after this transformation, the message will still be out of order. To resolve this, implement a method `rotate(CString[] arr, int d)` that shifts all elements in the array `d` positions to the left (wrapping elements that fall off the front to the end).

The correct value of `d` you must use is not provided directly; instead, it must be computed by examining the characters across the entire collection of CStrings, identifying the character with the **largest ASCII value**, and subtracting `60` from that value. Once the array has been rotated using this computed value of `d`, print the resulting sequence of words to the console to reveal the decoded message.

#### Examples of the `rotate` method:

The following illustrates how the `rotate(CString[] arr, int d)` method should behave, which involves shifting all elements **to the left by `d` positions**, wrapping items that fall off the front back to the end of the array.


***Example 1*** <br>
*Input:*
```
[CString("alpha"), CString("beta"), CString("gamma"), CString("delta")]
```
After calling `rotate` with `d` = 1...

*Output:*
```
[CString("beta"), CString("gamma"), CString("delta"), CString("alpha")]
```
<br>

***Example 2*** <br>
*Input:*
```
[CString("red"), CString("green"), CString("blue"), CString("yellow"), CString("purple")]
```
After calling `rotate` with `d` = 2...

*Output:*
```
[CString("blue"), CString("yellow"), CString("purple"), CString("red"), CString("green")]
```
<br>

***Example 3*** <br>
*Input:*
```
[CString("north"), CString("south"), CString("east"), CString("west")]
```
After calling `rotate` with `d` = 3...

*Output:*
```
[CString("west"), CString("north"), CString("south"), CString("east")]
```

---

### Implementation Requirements

Remember, your solution must:

✔ Use **proper object-oriented design**  
✔ Use **Selection Sort** and **Insertion Sort** where indicated  
✔ Avoid unnecessary loops when linear solutions exist  
✔ Write **clean, readable code**  
✔ Use meaningful variable names  
✔ Include comments, explaining key logic  

### Grading Note

Your `CString`, `CStringUtil`, and `Main` implementations will be evaluated using the JUnit test suite. To receive the score you deserve, you should review and run the tests locally to ensure **all of them pass** before submitting.

However, the correct decoding of the final message will not be checked by these JUnit tests. I will verify *after* you submit that your program successfully reconstructs the hidden message.

### Best of luck, cryptographer! 🔐

***The truth is waiting in the code...***
