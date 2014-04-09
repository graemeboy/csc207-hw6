package edu.grinnell.csc207.boygraem.hw6.problem1;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Queues implemented with arrays.
 * 
 * @author Samuel A. Rebelsky
 * @author Graeme Boy
 */
public class ArrayBasedQueue<T>
    implements
      Queue<T>
{
  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The values stored in the queue.
   */
  T[] values;

  /**
   * The index of the front of the queue.
   */
  int front;

  /**
   * The index of the back of the queue.
   */
  int back;

  /**
   * The number of elements in the queue.
   */
  int size;

  // +--------------+----------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new queue that holds up to capacity elements.
   */
  @SuppressWarnings({ "unchecked" })
  // Handle array casting
  public ArrayBasedQueue (int capacity) throws Exception
  {
    if (capacity <= 0)
      {
        throw new Exception ("Queues must have a positive capacity.");
      } // if (capacity <= 0)
    // Yay Java! It's not possible to say new T[capacity], so
    // we use this hack.
    this.values = (T[]) new Object[capacity];
    this.front = 0;
    this.size = 0;
  } // ArayBasedQueue(int capacity)

  // +---------------+---------------------------------------------------
  // | Queue Methods |
  // +---------------+

  @Override
  public boolean
    isEmpty ()
  {
    return this.size <= 0;
  } // isEmpty()

  @Override
  public boolean
    isFull ()
  {
    return this.size >= this.values.length; // full capacity
  } // isFull()

  @Override
  public void
    put (T val)
      throws Exception
  {
    if (this.isFull ())
      {
        throw new Exception ("no more room!");
      } // this.isFull()
    
    // Check if this element is not the back element
    if (this.back != this.values.length)
      { 
        this.values[this.back] = val;
        this.back++;        
      } // if
    else
      {
        // Find out the next best place
        int firstEmpty = getFirstEmpty();
        this.values[firstEmpty] = val;
        // The value has been put at lowest position in array possible.
        this.back = firstEmpty + 1; // this is the new last element
      } // else
    
    ++this.size;
  } // put(T)

  /**
   * Function getLowestNull. Starts from the beginning of the array and finds
   * the first element not assigned to a value.
   * 
   * @return first position in values where value is null; -1 if array is empty.
   */
  private int
    getFirstEmpty ()
  {
    for (int i = 0; i < this.values.length; i++)
      {
        if (this.values[i] == null)
          {
            // there is space here
            return i;
          } // if
      } // for
    return -1; // the array is full.
  } // wrapNext()

  @Override
  public T
    get ()
      throws Exception
  {
    if (this.isEmpty ())
      {
        throw new Exception ("empty");
      } // if empty
    // Grab and clear the element at the front of the queue
    T result = this.values[this.front];
    this.values[this.front] = null;
    // Check if the front is at the end of the array.
    if (this.front != this.values.length - 1)
      {
        this.front++;
      } // if
    else
      {
        this.front = 0; // Wrap it around
      } // else
    // We're removing an element, so decrement the size
    --this.size;
    // And we're done
    return result;
  } // get(T)

  @Override
  public T
    peek ()
      throws Exception
  {
    if (this.isEmpty ())
      {
        throw new Exception ("empty");
      } // if empty
    return this.values[this.front];
  } // peek()

  @Override
  public T
    dequeue ()
      throws Exception
  {
    return this.get ();
  } // dequeue

  @Override
  public void
    enqueue (T val)
      throws Exception
  {
    this.put (val);
  } // enqueue

  @Override
  public Iterator<T>
    iterator ()
  {
    return new ArrayBasedQueueIterator<T> (this);
  } // iterator()

  // +----------------+--------------------------------------------------
  // | Helper Methods |
  // +----------------+

} // class ArrayBasedQueue<T>

class ArrayBasedQueueIterator<T>
    implements
      Iterator<T>
{
  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+

  // +--------------+----------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new iterator.
   */
  public ArrayBasedQueueIterator (ArrayBasedQueue<T> q)
  {
    // STUB
  } // ArrayBasedQueueIterator

  // +---------+---------------------------------------------------------
  // | Methods |
  // +---------+

  @Override
  public T
    next ()
      throws NoSuchElementException
  {
    if (!this.hasNext ())
      {
        throw new NoSuchElementException ("no elements remain");
      } // if no elements
    // STUB
    throw new NoSuchElementException ("unimplemented");
  } // next()

  @Override
  public boolean
    hasNext ()
  {
    // STUB
    return false;
  } // hasNext()

  @Override
  public void
    remove ()
      throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException ();
  } // remove()
} // ArrayBasedQueueIterator<T>