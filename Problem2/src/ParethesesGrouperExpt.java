public class ParethesesGrouperExpt
{
  public void
    ParenthesesGrouperExpt ()
      throws Exception
  {

  }

  /**
   * function printTest, creates a new ParethesesGrouper object, which prints
   * out the groups of text delineated by the parenethes.
   * 
   * @param test
   * @post the groups of the text are printed to the screen, followed by a new
   *       line
   */
  public static void
    printTest (String test)
  {
    try
      {
        new ParethesesGrouper (test); // prints out the grouped sections
        System.out.println (); // for the sake of readability.
      } // try
    catch (Exception e)
      {
        e.printStackTrace ();
      } // catch
  } // printTest

  public static void
    main (String[] args)
      throws Exception
  {
    /*
     * Create an array of strings to test, some of them (indicated below) are
     * from SamR, published at:
     * http://www.cs.grinnell.edu/~rebelsky/Courses/CSC207
     * /2014S/assignments/current.html
     */
    String[] testStrings = {
                            // From SamR:
                            "{oh [boy] (I am having) (<so> much) fun matching 'symbols'}",
                            "(Hello (world)",
                            "(Hello (world))}  ]",

                            // My tests:
                            "(Hello [th[(e)re]] (World) '!')",
                            "This <is> sparta! (or, [perhaps,] some other {place<city, village, country>})",
                            "This (is sparta]", "This (is sparta]",
                            "This [(is sparta][{<" };

    for (String test : testStrings)
      {
        printTest (test);
      } // foreach testStrings as test

  } // main (String [])
} // ParethesesGrouperExpt
