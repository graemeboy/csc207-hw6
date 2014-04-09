package edu.grinnell.csc207.boygraem.hw6.problem1;
import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayBasedQueueTest
{

  public String
    arrayOuput (String[] arrayIn)
  {
    String output = "[ ";
    for (int i = 0; i < arrayIn.length; i++)
      {
        output += arrayIn[i] + ", ";
      } // for

    return output + " ]";
  } // arrayOutput (String [])

  /**
   * Outputs a string queue in a readable form for testing.
   * 
   * @param queueIn
   * @return output, e.g. [val0, val1, val2]
   * @throws Exception
   */
  public String
    stringQueueOutput (ArrayBasedQueue<String> queueIn)
      throws Exception
  {
    // No iterator implemented, okay.
    // Concatenate all strings within QueueIn
    int queueSize = queueIn.size;
    String output = "[";
    for (int i = 0; i < queueSize; i++)
      {
        output += queueIn.get ();
        if (i != queueSize - 1)
          {
            output += ", ";
          } // if
      } // for
    return output += "]";
  } // stringQueueOutput(ArrayBasedQueue)

  public void
    responseComparison (String given, String correct)
  {
    if (given.compareTo (correct) == 0)
      {
        assertEquals (given, correct);
      } // if
    else
      {
        fail ("Expected: " + correct + " but received: " + given);
      } // else
  } // responseComparison (String, String)

  @Test
  public void
    test ()
      throws Exception
  {
    ArrayBasedQueue<String> abq = new ArrayBasedQueue<String> (5);
    abq.put ("alpha");
    abq.put ("gamma");
    abq.put ("beta");

    String response = stringQueueOutput (abq);
    String correctResponse = "[alpha, gamma, beta]";
    responseComparison (response, correctResponse);

  } // test()

  @Test
  public void
    test2 ()
      throws Exception
  {
    ArrayBasedQueue<String> abq = new ArrayBasedQueue<String> (5);
    abq.put ("alpha");
    abq.put ("gamma");
    abq.put ("beta");
    abq.get ();
    abq.get ();

    String response = stringQueueOutput (abq);
    String correctResponse = "[beta]";
    responseComparison (response, correctResponse);

  } // test()
  
  @Test
  public void
    test3 ()
      throws Exception
  {
    ArrayBasedQueue<String> abq = new ArrayBasedQueue<String> (5);
    abq.put ("alpha");
    abq.put ("gamma");
    abq.put ("beta");
    abq.put ( abq.get () );
    abq.put ( abq.get () );
    

    String response = stringQueueOutput (abq);
    String correctResponse = "[beta, alpha, gamma]";
    responseComparison (response, correctResponse);

  } // test()
  
  @Test
  public void
    test4 ()
      throws Exception
  {
    ArrayBasedQueue<String> abq = new ArrayBasedQueue<String> (5);
    abq.put ("alpha");
    abq.put ("gamma");
    abq.put ("beta");
    abq.put ( abq.get () );
    abq.put ( abq.get () );
    abq.put("delta");

    String response = stringQueueOutput (abq);
    String correctResponse = "[beta, alpha, gamma, delta]";
    responseComparison (response, correctResponse);

  } // test()
  
  @Test
  public void
    test5 ()
      throws Exception
  {
    ArrayBasedQueue<String> abq = new ArrayBasedQueue<String> (5);
    abq.put ("alpha");
    abq.put ("gamma");
    abq.put ("beta");
    abq.put ( abq.get () );
    abq.put ( abq.get () );
    abq.put("delta");    
    abq.put("echo");
    abq.get ();

    String response = stringQueueOutput (abq);
    String correctResponse = "[alpha, gamma, delta, echo]";
    responseComparison (response, correctResponse);

  } // test()
  
}
