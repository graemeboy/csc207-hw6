package edu.grinnell.csc207.boygraem.hw6.problem3;
public class RPCExpt
{

  public static void
    main (String args[])
  {
    try
      {
        new ReversePolishCalculator ();
      } // try
    catch (Exception e)
      {
        e.printStackTrace ();
      } // catch
  } // main (String [])
} // RPC Exprt
