package edu.grinnell.csc207.boygraem.hw6.problem2;
import java.io.PrintWriter;
import java.util.Deque;
import java.util.LinkedList;

/**
 * ParethesesGrouper, groups text, as delineated by parentheses, and outputs the
 * form of these groups to the screen as a picture.
 * 
 * Example of "form:" (Hello, world!) => (-------------)
 * 
 * @author graemeboy
 * 
 */
public class ParethesesGrouper
{
  // "Write a procedure that takes a string as input and prints out a picture that shows the nesting."

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  // Make a map of opening and closing parenthesis.
  final char[] openParens = { '{', '(', '[', '<' }; // Set of open parentheses
  final char[] closeParens = { '}', ')', ']', '>' }; // Set of close parentheses

  final String unmatchedWarning = "  <-- UNMATCHED"; // For unmatched parens.

  final PrintWriter pen = new PrintWriter (System.out, true);

  int inputSize; // Size of the input given

  String input;
  String output;

  ArrayBasedStack<Group> working; // Working memory stack

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  public ParethesesGrouper (String input) throws Exception
  {
    this.input = input;
    this.inputSize = input.length ();
    this.output = input + "\n"; // Starts with the input, on its own line.
    this.working = new ArrayBasedStack<Group> (this.inputSize);

    groupByParenthesis (); // Perform the main operations.

  } // ParentheseGrouper (String)

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * function groupByParenthesis, takes the string input and breaks it into
   * groups based on the enclosing parentheses
   * 
   * @throws Exception
   * @pre
   */
  public void
    groupByParenthesis ()
      throws Exception
  {
    for (int i = 0; i < this.inputSize; i++)
      {
        char character = this.input.charAt (i);
        if (isOpenParen (character))
          {
            this.working.put (new Group (character, i)); // add to stack.
          } // if
        else if (isCloseParen (character))
          {
            // Take last group in from the working stack
            if (!this.working.isEmpty ())
              {
                Group current = this.working.get ();

                if (closeParens[charIndexOf (this.openParens, current.braceType)] == character)
                  {
                    current.closeBrace (i); // If valid, close that group
                    printGroup (current, i); // print it out
                  } // if
                else
                  {
                    // Make some kind of good error message from data.
                    printGroup (current, i); // print output up until this point
                    reportIllegalClose (character, i); // print warning
                  } // else
              } // if
            else
              {
                // The stack was empty, but received a closing parenthesis.
                printUnmatchedClose (character, i);
              } // else
          } // else if
      } // for

    // print remaining (unclosed) items
    if (!working.isEmpty ())
      {
        printUnclosedGroups ();
      } // if
  } // groupByParengthesis()

  /*
   * Functions for printing groups
   */
  /**
   * function printGroup, prints a picture of a group of text's nesting,
   * delinated by parentheses
   * 
   * @param group
   * @param currentIndex
   * @post a picture of a section of the input text's nesting has been printed
   *       to screen
   */
  public void
    printGroup (Group group, int currentIndex)
  {
    for (int x = 0; x < group.openIndex; x++)
      {
        this.output += " ";
      } // for
    this.output += group.braceType;

    if (group.closeIndex > 0)
      {
        for (int x = 0; x < (currentIndex - group.openIndex - 1); x++)
          {
            this.output += '-';
          } // for
        this.output += getClosingParen (group.braceType);
      } // if
    else
      {
        this.output += this.unmatchedWarning;
      } // else

    println (this.output);
  } // printGroup(Group, int)

  /*
   * Functions for error reporting
   */
  /**
   * function reportIllegalClose, reports an illegal closing parenthesis, which
   * happens when a closing parenthesis is encountered that does not match the
   * next brace type on the stack. E.g. "(Hello World]" - The closing
   * parenthesis ']' does not match opening parenthesis, '(' and is therefore
   * illegal.
   * 
   * @param character
   * @param index
   * @post an error message is printed to the screen.
   */
  public void
    reportIllegalClose (char character, int index)
  {
    println ("An illegal character, '" + character
             + "', was found at position " + index + " of the input.");
  } // reportIllegalClose(char, int )

  /**
   * function printUnclosedGroups, prints any unclosed parentheses left in the
   * stack, along with an appropriate warning to the user that the parenthesis
   * was unmatched.
   * 
   * @throws Exception
   * @post a appropriate warning has been printed to the screen to indicate
   *       unmatched parentheses.
   */
  public void
    printUnclosedGroups ()
      throws Exception
  {
    int i = 1;
    while (!this.working.isEmpty ())
      {
        printGroup (this.working.get (), this.inputSize + i);
        i++; // increment the position of this paren
      } // while
  } // printUnclosedGroups()

  /**
   * function printUnmatchedClose prints out an appropriate message to indicate
   * the failure to match a parenthesis
   * 
   * @param closeParen
   * @param index
   * @post a warning has been printed to the screen
   */
  public void
    printUnmatchedClose (char closeParen, int index)
  {
    for (int i = 0; i < index; i++)
      {
        this.output += ' ';
      } // for
    this.output += closeParen + this.unmatchedWarning;
    println (output);
  } // printUnmatchedClose (char, int)

  /*
   * Functions to query parentheses
   */
  /**
   * function getClosingParen, returns the corresponding closing paren, given an
   * opening one.
   * 
   * @param openParen
   * @return a closing parenthesis for an opening one. E.g. ']' when given '['.
   */
  public char
    getClosingParen (char openParen)
  {
    return closeParens[charIndexOf (openParens, openParen)];
  }

  /**
   * function isOpenParen, returns true if a given character is in the set of
   * known open parentheses
   * 
   * @param character
   * @return true if character is an open parenthesis
   */
  public boolean
    isOpenParen (char character)
  {
    return (charIndexOf (this.openParens, character) >= 0);
  } // isOpenParen(char)

  /**
   * function isCloseParen, returns true if a given character is in the set of
   * known close parentheses
   * 
   * @param character
   * @return true if character is a close parenthesis
   */
  public boolean
    isCloseParen (char character)
  {
    return (charIndexOf (this.closeParens, character) >= 0);
  } // isCloseParen(char)

  /*
   * Auxillary functions
   */
  /**
   * int charIndexOf, returns the index of a character in an array, -1 if not
   * found
   * 
   * @param haystack
   * @param needle
   * @return the index of the character in an array of characters
   */
  public int
    charIndexOf (char[] haystack, char needle)
  {
    for (int i = 0; i < haystack.length; i++)
      {
        if (haystack[i] == needle)
          {
            return i; // in array, at this position.
          } // if
      } // for
    return -1; // not in array
  } // charIndexOf(char[], char)

  /**
   * function clearOutput, resets the output to nothing (which might change
   * depending on the type of picture)
   */
  public void
    clearOutput ()
  {
    this.output = "";
  } // clearOutput

  /**
   * functoin println, prints a line and clears the output string for the next
   * group.
   * 
   * @param input
   * @post input is printed to the screen, and output is reset to its "clear"
   *       state.
   */
  public void
    println (String input)
  {
    // "[print] out a picture that shows the nesting"
    this.pen.println (input);
    clearOutput ();
  }

  // +---------+------------------------------------------------------
  // | Classes |
  // +---------+

  /*
   * "Another option is to create a simple class that groups a character and an integer"
   * - SamR
   */
  public class Group
  {
    char braceType; // character
    int openIndex; // integer
    int closeIndex;

    public Group (char braceIn, int openIn)
    {
      this.braceType = braceIn;
      this.openIndex = openIn;
    } // Group (char, int)

    /**
     * function closeBrace, sets the closeIndex to a given integer once the
     * closing brace has been found
     * 
     * @param closeIn
     * @post closeIndex = closeIn
     */
    public void
      closeBrace (int closeIn)
    {
      this.closeIndex = closeIn;
    } // closeBrace (int)
  } // class Group
} // class ParethesesGrouper
