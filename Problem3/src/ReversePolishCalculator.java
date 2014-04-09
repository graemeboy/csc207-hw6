import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReversePolishCalculator
{
  final int maxStackSize = 10;

  public class Controller
  {
    BufferedReader br;
    Model model; // Will hold our data
    View consoleView; // A view for the little Eclipse Console!

    public Controller () throws Exception
    {
      model = new Model ();
      consoleView = new View ();

      // Greet the user
      consoleView.echo ("Welcome to the RPC!");

      br = new BufferedReader (new InputStreamReader (System.in));
      // Request some data from the user.
      consoleView.echo ("Please enter your calculation: ");

      String line = br.readLine ();
      String[] pieces = line.split (" "); // split into arguments

      int result;
      try
        {
          for (int i = 0; i < pieces.length; i++)
            {
              switch (pieces[i])
                {
                  case "p":
                    consoleView.echo (String.valueOf (model.get ()));
                    break;
                  case "+":
                    model.put(model.get () + model.get ());
                    break;
                  default:
                    // integer input
                    model.put (Integer.parseInt (pieces[i]));
                } // switch

            } // for
        } // try
      catch (IOException e)
        {
          e.printStackTrace ();
        } // catch
    } // Controller ()
  } // Controller

  public class Model
  {
    ArrayBasedStack<Integer> memory; // Every brain has some memory.

    public Model () throws Exception
    {
      this.memory = new ArrayBasedStack<Integer> (maxStackSize);
    } // Model ()

    public void
      put (int val)
        throws Exception
    {
      this.memory.put (val);
    } // put (int val)

    public int
      get ()
        throws Exception
    {
      return this.memory.get ();
    } // get ()

  } // Model

  class View
  {

    public View ()
    {

    } // View()

    public void
      echo (String output)
    {
      System.out.println (output);
    } // print ()
  } // View

  public ReversePolishCalculator () throws Exception
  {
    Controller control = new Controller ();

  }

  public static void
    main (String[] args)
      throws NumberFormatException,
        Exception
  {
    new ReversePolishCalculator ();
  } // main (String args[])
} // ReversePolishCalculator