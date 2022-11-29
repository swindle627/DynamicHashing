package com.company;
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
public class WordList {
    // Linked list of words for each key
    private Node head;

    // adds a value to the linked list
    public int Add(String word)
    {
        // counts comparisons made when adding a new value or incrementing the counter of an existing value
        int comparisonCount = 0;

        // if a key doesn't have any values yet the value is entered
        // if a key has values the value is added if it isn't in the list already
        // if the value is in the list the counter for the value is incremented
        if(head == null)
        {
            head = new Node();
            head.word = word;
            head.wordCount++;
            comparisonCount++;
        }
        else
        {
            Node curr = head;

            while(curr.next != null && !curr.word.equals(word))
            {
                curr = curr.next;
                comparisonCount++;
            }

            if(curr.word.equals(word))
            {
                curr.wordCount++;
                comparisonCount++;
            }
            else
            {
                Node newNode = new Node();
                newNode.word = word;
                newNode.wordCount++;

                curr.next = newNode;
                comparisonCount++;
            }

        }


        return comparisonCount;
    }
    // prints the word and word count for each node
    public void PrintNodes()
    {
        Node curr = head;

        while (curr != null)
        {
            System.out.println(String.format("%-15s%-5s", curr.word, curr.wordCount));
            curr = curr.next;
        }
    }
    // returns the amount of nodes in the linked list
    public int NodeCount()
    {
        int nodeCount = 0;
        Node curr = head;

        while (curr != null)
        {
            nodeCount++;
            curr = curr.next;
        }

        return nodeCount;
    }
}
