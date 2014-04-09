import java.io.*;

/**
 * ReversePolishCalculator
 * 
 * Calculate up to 15 arguments at a time, using Reverse Polish Notation (RPN.)
 * End the calculator by entering "e" as an argument.
 * 
 * @author graemeboy
 * 
 */
public class ReversePolishCalculator
{
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  final int maxStackSize = 15;
  final PrintWriter pen = new PrintWriter (System.out, true);
  final String inputPrompt = "Please enter your arguments: ";

  final char[] operators = { '+', '-', '/', '*', '%', '^' };

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * 
   * @throws Exception
   */
  public ReversePolishCalculator () throws Exception
  {
    // Start the controller, which will pick the view.
    new Controller ();
  }

  // +---------+-----------------------------------------------------
  // | Classes |
  // +---------+

  /*
   * Controller
   */
  public class Controller
  {
    BufferedReader br;
    Model model;
    ConsoleView consoleView; // A view for the little Eclipse Console!

    public Controller () throws Exception
    {
      try
        {
          model = new Model ();
          consoleView = new ConsoleView ();

          // Greet the user
          consoleView.echo ("\t\t\t\t* Welcome to the RPC! *");
          consoleView.echo ("=======================================================================================");
          consoleView.echo ("\tThe RPC handles basic arithmetic problems, returning integers.");
          consoleView.echo ("It can " + "accept integers, the operators "
                            + implodeCharArray (operators, ", ")
                            + " and 'p' to print, 'e' to end.");
          consoleView.echo ("=======================================================================================\n");

          br = new BufferedReader (new InputStreamReader (System.in));
          // Request some data from the user.
          consoleView.echo (inputPrompt);

          String line;
          int temp;

          while ((line = br.readLine ()).compareTo ("e") != 0)
            {
              String[] pieces = line.split (" "); // split into arguments
              if (pieces.length < maxStackSize * 2)
                {
                  for (int i = 0; i < pieces.length; i++)
                    {

                      if (pieces[i].length () == 1
                          && inCharArray (operators, pieces[i].charAt (0)))
                        {
                          if (model.size () < 2)
                            {
                              consoleView.echo ("Too few numerical arguments were given.");
                            } // if
                          else
                            {
                              switch (pieces[i])
                                {
                                  case "+":
                                    model.put (model.get () + model.get ());
                                    break;
                                  case "*":
                                    model.put (model.get () * model.get ());
                                    break;
                                  case "/":
                                    temp = model.get ();
                                    model.put (model.get () / temp);
                                    break;
                                  case "-":
                                    temp = model.get ();
                                    model.put (model.get () - temp);
                                    break;
                                  case "%":
                                    temp = model.get ();
                                    model.put (model.get () % temp);
                                    break;
                                  case "^":
                                    temp = model.get ();
                                    model.put ((int) Math.pow (model.get (),
                                                               temp));
                                } // switch
                            } // else
                        } // if
                      else
                        {
                          switch (pieces[i])
                            {
                              case "p":
                                consoleView.echo (String.valueOf (model.get ()));
                                break;
                              // The case of "e" is handled in the while loop
                              default:
                                // integer input
                                try
                                  {
                                    model.put (Integer.parseInt (pieces[i]));
                                  }
                                catch (NumberFormatException nfe)
                                  {
                                    consoleView.echo ("The input "
                                                      + pieces[i]
                                                      + " was not accepted. Was this meant to be an integer?");
                                  }
                            } // switch
                        } // else
                    } // for
                  consoleView.echo ("Calculation performed. " + inputPrompt);
                } // if
              else
                {
                  consoleView.echo ("Sorry, that calcalation was longer than I have space to remember.");
                } // else
            } // while
        } // try
      catch (IOException e)
        {
          e.printStackTrace ();
        } // catch

    } // Controller ()

    /**
     * function implodeCharArray, returns the items in a char array, glued
     * together by the glue parameter.
     * 
     * @param fragments
     * @param glue
     * @return unified, a String containing all the items in the fragments
     *         array, joined by the string "glue."
     */
    public String
      implodeCharArray (char[] fragments, String glue)
    {
      String unified = "";

      for (char peice : fragments)
        {
          unified += peice + glue;
        } // for
      return unified;
    } // implodeArray()

    public boolean
      inCharArray (char[] haystack, char needle)
    {
      for (char candidate : haystack)
        {
          if (candidate == needle)
            {
              return true;
            } // if
        } // for
      return false; // not present
    } // inArray
  } // Controller

  public class Model
  {
    ArrayBasedStack<Integer> memory; // Every brain has some memory.

    /*
     * Constructors
     */
    public Model () throws Exception
    {
      this.memory = new ArrayBasedStack<Integer> (maxStackSize);
    } // Model ()

    /*
     * Methods
     */
    /**
     * function put, adds a given integer to the memory stack
     * 
     * @param val
     * @throws Exception
     * @post get() = val
     */
    public void
      put (int val)
        throws Exception
    {
      this.memory.put (val);
    } // put (int val)

    /**
     * function get, returns the next interger in the memory stack
     * 
     * @return the next integer in the memory stack
     * @throws Exception
     * @pre there must be a value remaining in the stack
     * @post the last value is removed from the stack
     */
    public int
      get ()
        throws Exception
    {
      if (!this.memory.isEmpty ())
        {
          return this.memory.get ();
        }
      else
        {
          return 0; // STUB
        } // else
    } // get ()

    /**
     * function size, returns the number of items in the memory stack
     * 
     * @return the number of items in memory
     */
    public int
      size ()
    {
      return this.memory.size;
    } // size ()

    /**
     * function hasSpace
     * 
     * @return true or false if memory has the maximum number of arguments
     */
    public boolean
      hasSpace ()
    {
      return (!this.memory.isFull ());
    } // hasSpace()

    /**
     * function isEmpty
     * 
     * @return true or false, depending on if memory is empty
     */
    public boolean
      isEmpty ()
    {
      return this.memory.isEmpty ();
    } // isEmpty()

  } // Model

  /**
   * Class View
   * 
   * Create
   */
  class ConsoleView
  {
    // Constructors
    public ConsoleView ()
    {
      // What could a view do when it is initialized?
    } // View()

    // Methods
    /**
     * function echo, prints out some output provided by the controller
     * 
     * @param output
     * @post output is printed to the screen
     */
    public void
      echo (String output)
    {
      pen.println (output);
    } // print ()
  } // View
} // ReversePolishCalculator