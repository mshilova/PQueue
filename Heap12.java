/**
 * Filename: Heap12.java
 * This class uses heap-structured array to implements the PQueue interface.
 * The elements are comparable to each other in terms of their "priority".
 * This is a maxheap, meaning, the element with the "highest priority" is at
 * the root of the heap. Duplicate elements are allowed, however, null
 * elements are not.
 *
 * @date 05/23/2013
 */
public class Heap12<E extends Comparable<? super E>> implements PQueue<E>  {

  //////////////////fields////////////////////
  private int arrLength;  // capacity of the backing store 
  private int size;       // size of the Heap12
  private E [] arr;  // backing store for Heap12
  

  ////////////////constructors////////////////
 /**
  * Default constructor, setting default backing store capacity to 5
  * and the size is set to 0. Backing store array is created with the
  * length of the capacity.
  */ 
  public Heap12()  {
    this.arrLength = 5; // default capacity
    this.size = 0;
    arr = (E[]) new Comparable[arrLength];
  }
  
 /**
  * Copy constructor. Creates a deep copy of the argument heap.
  * @param a Heap12 object for copy
  */ 
  public Heap12(Heap12<E> existing)  {
   // create copy of the array but not the elements(deep copy)
    arr = (E[]) new Comparable[existing.arrLength];
    this.size = existing.size;
    this.arrLength = existing.arrLength;
    for(int i = 0; i < this.arrLength; i++)  {
      arr[i] = existing.arr[i];
    }
  }

 /**
  * Private constructor(arg array). Create a new Heap12, with the 
  * backing array being a very shallow copy of the array passed in.
  * Will be used for heapsort.
  */ 
  private Heap12(E[] array)  {
   this.arr = array;
   this.size = 0;
   this.arrLength = array.length;
  }
  
  //////////////////////////methods///////////////////////////////

 /**
  * Adds the specified element to this Heap12, the size is increased by 1.
  * If the size of the Heap12 is equal to the length of its backing store
  * array, the length of the array is doubled.
  * @throws NullPointerException if the element is null.
  * @param element to be added 
  */ 
  public void add(E e)  {
    if(e == null)  {
      throw new NullPointerException();
    }
    // if full, double the length of the back store array and copy
    // all of the elements over
    if(this.size == this.arrLength)  {
       E [] newArr = (E[]) new Comparable[this.arrLength*2];
       for(int i = 0; i < this.size; i++)  {
         newArr[i] = arr[i];
       }
       // update the backing store array
       arr = newArr;
       this.arrLength = arrLength*2; // update the length
    }
    arr[this.size] = e;
    // sort the heap, parent must have higher priority than the child
    this.bubbleUp(size);
    this.size++;
  }

 /**
  * compares the specified object with this Heap12 for equality
  * @return true if two Heap12 objects are equal, else false.
  * @param object for comparison
  */ 
  public boolean equals(java.lang.Object o)  {
    boolean same = false;
    if( o instanceof Heap12)  {
      // do casting to Heap12
      Heap12<E> p = (Heap12<E>)o;
      
      // check if size is the same
      if(this.size() == p.size())  {
        // two empty Heap12 objects are equal
        if(this.size == 0)  {
          same = true;
        }
        for(int index = 0; index < this.size; index++)  {
          if(p.arr[index].equals(this.arr[index]))  {
            same = true;
          }          
          else  { 
            same = false;
            break;
          }
        }
      }
    }
    return same;
  }

 /**
  * compute and return a hashcode for this Heap12. HashCode of the Heap12
  * object depends on individual elements' hashcodes of the Heap12.
  * @return hashcode for this Heap12.
  */ 
  public int hashCode()  {
    int hashCode = 1;
    for(int i = 0; i < this.size; i++)  {
      hashCode = 31*hashCode + (this.arr[i].hashCode());
    }
    return hashCode;
  }
  
 /**
  * Determine if this Heap12 contains any elements.
  * @return true if no elements, else false.
  */ 
  public boolean isEmpty()  {
    if(size == 0) 
      return true;
    else 
      return false;
  }

 /**
  * Returns the elment in this Heap12 that would be returned by remove(),
  * namely returns the element of the 'highest' priority. The states of 
  * the Heap12 is not changed after the call of this method.
  * @return the element of the highest prioroty, null if empty Heap12.
  */
  public E peek()  {
    if(size != 0)  {
      return arr[0];
    }
    else  {
      return null;
    }
  }

 /**
  * Removes and returns the highest priority element in this Heap12. If more
  * then one elements has the same highest priority, one of them is removed
  * and returned. The size is decreased by one, other elements are not 
  * changed.
  * @return element removed, null if no elements.
  */ 
  public E remove()  {
    // element to return
    E oldTop = null;
      if(this.size > 0)  {
        oldTop = arr[0];
        // if only one element in array, set it to 0
        if(this.size == 1)  {
          arr[0] = null;
        }
        // more then one elements in array, swap first and last elements
        // decrease size and call trickleDown() to maintain  maxheap state
        else  {
          arr[0] = arr[this.size - 1];
        }
        this.size--;
        this.trickleDown(0);
      }
    return oldTop;
  }

 /**
  * Returns the number of elements in this Heap12
  * @return the size of this Heap12.
  */ 
  public int size()  {
    return this.size;
  }
 
 /**
  * Sort an array of Comparable<? super T> objects into nondecreasing
  * order according to the natural ordering of its elements. Uses heapsort
  * algorithm to sort its elements 'in place', without allocating new array.
  * @throws NullPointerException if the argument is null
  * @param the array to be sorted.
  */ 
  public static <T extends java.lang.Comparable<? super T>> void sort(T[] a)  {
    // call the private constructor initializing it to param array
    if(a == null)  {
      throw new NullPointerException();
    }
    Heap12<T> sort = new Heap12<T>(a);
    // adding array elements to Heap12
    for(int i = 0; i < a.length; i++)  {
      sort.add(a[i]);
    }
    // removing elements from this Heap12 and adding them to the passed 
    // array starting from the back.
    // since remove() returns the highest priority element, elements
    // are sorted in nondecreasing order.
    for(int k = sort.size() - 1; k >= 0; k--)  {
      a[k] = sort.remove();
    } 
  }

 /**
  * Method to maintain maxHeap structure after removing an element. Compares
  * the priority of the parent node with the children nodes. If the parent
  * is lower in priority than the highest priority of its children, then
  * the elements are swapped, thereby maintaining maxheap structure.
  * @param the index at with comparison is staring.
  */
  private void trickleDown(int index)  {
    // if there is only one element, it is maxheap.
    if (size > 1)  { 
      for(int top = index; top < this.size; top++)  {
        int left = 2*top + 1; // left child
        int right = 2*top + 2; // right child
        // index of highest priority element is top arbitrary
        int max = top; 
        // if left child is there and it has higher priority than max
        // left is the new max
        if (left < this.size && arr[left].compareTo(arr[max]) > 0) {
           max = left;
        }
        // if right child exists and it has higher priority than max
        // right is the new max
        if(right < this.size && arr[right].compareTo(arr[max]) > 0)  {
          max = right;
        }
        // if max is not the parents index, switch the lelements
        if(max != top)  {
          E tmp = arr[max];
          arr[max] = arr[top];
          arr[top] = tmp;
        }
      }
    }
  }

  /**
   * Method that maintains maxheap structure after adding an element to the
   * Heap12. Compares the priority of child with its parent and swaps them
   * if the priority of the child is higher than the parent.
   * @param index at wich the element was added and needs to be compared. 
   */ 
  private void bubbleUp(int index)  {
    for(int child  = index; child > 0; child--)  {
      // if child has higher priority than parent, swap them
      int parent = (child - 1)/2;
      if(arr[child].compareTo(arr[parent]) > 0)  {
        E tmp = arr[child];
        arr[child] = arr[parent];
        arr[parent] = tmp;
      }   
    }
  }
}
