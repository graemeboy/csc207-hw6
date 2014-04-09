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
  final String inputPrompt = "Please enter your arguments (use 'p' to print, 'e' to end): ";

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
          consoleView.echo ("Welcome to the RPC!");

          br = new BufferedReader (new InputStreamReader (System.in));
          // Request some data from the user.
          consoleView.echo (inputPrompt);

          String line;

          char[] operators = { '+', '-', '/', '*', '%' };

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
                          if (model.isEmpty ())
                            {
                              consoleView.echo ("No numerical arguments were given (The stack is empty!)");
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
                                    model.put (model.get () / model.get ());
                                    break;
                                  case "-":
                                    model.put (model.get () - model.get ());
                                    break;
                                }
                            }
                        } // if
                      else
                        {
                          switch (pieces[i])
                            {
                              case "p":
                                consoleView.echo (String.valueOf (model.get ()));
                                break;

                              default:
                                // integer input
                                model.put (Integer.parseInt (pieces[i]));
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
        }
    } // get ()

    public boolean
      hasSpace ()
    {
      return (!this.memory.isFull ());
    } // hasSpace()

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