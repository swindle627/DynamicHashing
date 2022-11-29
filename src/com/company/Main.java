package com.company;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
// Name: JaDante Hendrick
// Class: CS 4306/4
// Term: Fall 2022
// Instructor: Dr. Haddad
// Assignment: 4
// IDE: Intellij

// Algorithm Design Block
/*  Best and average: O(1)
    Worst: O(n)
    Test 1-
        26 comparisons for 26 words. Each word starts with a different letter so 1 value is inserted for each key which results in 1 comparison per value inserted.
    Test 2-
        26 comparisons for 26 words. 1 comparison for inserting initial key value and 1 comparison for incrementing the occurrence counter 25 times for the key value.
    Test 3-
        326 comparisons for 26 words. 1 comparison for the first key value inserted. Since each word is different but starts with the same letter they will be different values under the same key.
        O(n) values will be iterated through for each new value added with n increasing by 1 each time a value is added.
        From this it can be drawn that the summation formula, (n(n-1)/2) + 1 (where n = the number of values a key has), can be used to calculate the comparison count.
        The additional 1 in the formula represents the comparison for the first value that is added before the summation begins.
    Test 4-
        38 comparisons for 26 words. This input results with keys having a mixture of 0, 1 or multiple values.
        The comparison count here is calculated by adding 1 for each key with one value and (n(n-1)/2) + 1 (where n = the number of values a key has) for each key with multiple values.
    Test 5-
        1657 comparisons for 352 words.
        Like test 4, the comparison count here is calculated by adding 1 for each key with one value and (n(n-1)/2) + 1 (where n = the number of values a key has) for each key with multiple values.
        Its basically just a larger version of test 4
    Empirical Results vs. Theoretical Efficiency-
        Adding an initial value to a key or incrementing the first value attached to a key has a comparison count of O(1). This can be seen with tests 1 and 2.
        Adding a value to a key that already has values has a comparison count of O(n). This can be seen with test 3.
        Tests 4 and 5 show the average case. In both tests adding values usually has a comparison count of O(1) with some values having a comparison count of O(n).
 */

// Code Section
public class Main {

    public static void main(String[] args) {
        int option = 0;
        Scanner scan1 = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        String text = "";
        WordList[] tokenHashTable = new WordList[26];
        ArrayList<String> tokens = new ArrayList<String>();
        int comparisonCount = 0;

        // Controls the main menu
        while (option != 5) {
            System.out.println("Main Menu");
            System.out.println("1. Read input text");
            System.out.println("2. Hash input text to hash table");
            System.out.println("3. Display words and occurrences");
            System.out.println("4. Display efficiency outputs");
            System.out.println("5. Exit program");
            System.out.println("\nEnter option number:");

            option = scan1.nextInt();

            if (option == 1) {
                // takes user input
                System.out.println("\nEnter text: ");
                text = scan2.nextLine();
                text = text.toLowerCase(Locale.ROOT); // converts text to lowercase
                text = text.trim(); // gets rid of white space at the start and end of the text

                System.out.println("\nText read");

            } else if (option == 2) {
                // enters the tokens into the hash table
                tokens = ExtractTokens(text);

                comparisonCount = 0;
                tokenHashTable = InitializeTable();

                for(String token : tokens)
                {
                    int key = HashFunction(token.charAt(0));
                    int tokenComparisons = tokenHashTable[key].Add(token);
                    System.out.println("Comparisons for " + token + ": " + tokenComparisons);
                    comparisonCount += tokenComparisons;
                }

                System.out.println("\nHashing Complete");

            } else if (option == 3) {
                // prints WordList
                System.out.println(String.format("%-15s%-5s", "Word", "Count"));
                System.out.println("------------------------------");
                for(WordList index : tokenHashTable)
                {
                    if(index.NodeCount() != 0)
                    {
                        index.PrintNodes();
                    }
                }

                System.out.println("\nWords printed");

            } else if (option == 4) {
                // prints comparison count
                System.out.println("Input Value: " + text);
                System.out.println("Input Size: " + tokens.size() + " words");
                System.out.println("Comparison Count: " + comparisonCount);
            }

            System.out.println("\n");
        }
    }
    // Extracts tokens from text
    public static ArrayList<String> ExtractTokens(String text) {
        ArrayList<String> tokens = new ArrayList<String>();

        int startIndex = 0;

        // extracts the tokens from the text by checking for spaces and punctuation
        for (int i = 0; i < text.length(); i++) {

            if (text.charAt(i) == ' ') {
                String token = text.substring(startIndex, i);
                tokens.add(token);
                startIndex = i + 1;
            } else if (text.charAt(i) == ',' || text.charAt(i) == '.' || text.charAt(i) == '?') {
                String token = text.substring(startIndex, i);
                tokens.add(token);

                i++;

                // handles double spaces
                while (i < text.length() && text.charAt(i) == ' ') {
                    i++;
                }

                startIndex = i;
            }
        }

        // if the text ended in with punctuation then startIndex will equal text.Length
        if(startIndex < text.length())
        {
            tokens.add(text.substring(startIndex));
        }

        return tokens;
    }
    // Calculates the key for values
    // key = ascii value mod 97
    // a mod 97 = 97 mod 97 = 0; words that start with a will be stored at index 0 in tokenHashTable
    public static int HashFunction(char firstChar)
    {
        int asciiVal = firstChar;
        int key = asciiVal % 97;
        return key;
    }
    // Initializes the linked lists in the table
    public static WordList[] InitializeTable()
    {
        WordList[] tokenHashTable = new WordList[26];

        for (int i = 0; i < tokenHashTable.length; i++)
        {
            tokenHashTable[i] = new WordList();
        }

        return tokenHashTable;
    }
}
