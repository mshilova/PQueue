import java.util.*;

/**
 * Filename: Heap12Tester.java
 * This class is testing methods of Heap12<E> class.
 * @date 05/23/2013
 */ 

public class Heap12Tester extends junit.framework.TestCase  {
  public static void main (String[] args)  {
    junit.textui.TestRunner.main(new String[]  {"Heap12Tester"});
  }

 /**
  * This method is testing the no-argument constructor of Heap12<E> class.
  * Testing that the default size was initialized to 0 and the Heap12 object
  * is empty initially.
  *
  * @see org.junit.Asser
  */ 
  public void testConstructor()  {
    // creating new object to test
    PQueue<Integer> test = new Heap12<Integer>();
    // verifying initial size and capacity
    assertTrue(test.size() == 0);
    assertFalse(!test.isEmpty());
  }
  
 /**
  * This method is testing the deep copy constructo of Heap12<E> class.
  * Namely, testing that the size of the new Heap12 object is the same
  * as the size of the Heap12 copy object.
  *
  * @see org.junit.Asser
  */ 
  public void testDeepConstructor()  {
    // creating new object to test
    PQueue<Integer> test3 = new Heap12<Integer>();
    // creating deep copy of test3 Heap12
    PQueue<Integer> testDeep = new Heap12<Integer>((Heap12)test3);
    // verifying deep copy
    assertEquals(test3.size(), testDeep.size());
  }
 
 /**
  * This method is testing the deep copy constructo of Heap12<E> class.
  * Namely, testing that the elements of the copy and the argument 
  * Heap12 objects are the same and the size is the same.
  *
  * @see org.junit.Assert
  */ 
  public void testDeepConstructor1()  {

    PQueue<Integer> testDeepAdd = new Heap12<Integer>();
    // applying mutations
    testDeepAdd.add(new Integer(1));
    testDeepAdd.add(new Integer(2));
    testDeepAdd.add(new Integer(3));
    testDeepAdd.add(new Integer(4));
    // making deep copy of the existing Heap12 object 
    PQueue<Integer> testDeepCopy = new Heap12<Integer>((Heap12)testDeepAdd);
    // making sure that the size of both Heaps is the same
    assertEquals(testDeepAdd.size(), testDeepCopy.size());
    assertEquals(testDeepCopy.peek(), new Integer(4));
    // verifying that the elements are in the right order
    assertEquals(testDeepCopy.remove(), new Integer(4));
    assertEquals(testDeepCopy.remove(), new Integer(3));
    assertEquals(testDeepCopy.remove(), new Integer(2));
    assertEquals(testDeepCopy.remove(), new Integer(1));
    assertTrue(testDeepCopy.isEmpty());
  }

 /**
  * This method is testing add() method of Heap12<E> class.
  * Namely, tesing that the size is increasing by one after
  * the method call, and the highest priority element is at 
  * the root of the Heap12 object.
  *
  * @see org.junit.Assert
  */ 
  public void testAdd()  {
    // creating new object to test
    PQueue<String> testAdd = new Heap12<String>();
    // apply mutators 
    testAdd.add("G");
    assertTrue(testAdd.size() > 0);
    testAdd.add("F");
    assertEquals(testAdd.size(), 2);
    testAdd.add("E");
    testAdd.add("D");
    testAdd.add("C");
    testAdd.add("A");
    // verify mutators     
    assertFalse(testAdd.isEmpty());
    assertEquals(testAdd.peek(), "G"); 
  }

 /**
  * This method is testing add() method of Heap12<E> class.
  * Testing that the largest integer added is at the root
  * and that the size is increased every time adding.
  *
  * @see org.junit.Assert
  * @see java.lang.Exception
  */ 
  public void testAdd1()  {
    PQueue<Integer> testA = new Heap12<Integer>();
    for(int k = 0; k < 100; k++)  {
      testA.add(new Integer(k));
    }
    assertEquals(testA.peek(), new Integer(99));
    assertEquals(testA.size(), 100);
  }

 /**
  * This method is testing add() method of Heap12<E> class.
  * Testing that the method throws NullPointerException
  * if the element is null.
  *
  * @see org.junit.Assert
  */ 
  public void testAddNull()  {
   // creating new object to test
    PQueue<String> testAddNull = new Heap12<String>();
    try  {
      testAddNull.add(null);
      fail("Expected NullPointerException");
    }    
    catch(NullPointerException e)  {
    } // test passed
  }
  
 /**
  * This method is testing remove() and peek() methods of Heap12<E> class.
  * In particular, when the Heap12 object is empty, both methods should
  * return null and the size does not change. Else, both methods should 
  * return the same elements and size decreases with remove().
  *
  * @see org.junit.Assert
  */ 
  public void testRemovePeek()  {
    PQueue<String> testRem = new Heap12<String>();
    // verifying returns from empty Heap12 object
    assertNull(testRem.remove());
    assertNull(testRem.peek());
    assertEquals(testRem.size(), 0);
    // apply mutators 
    testRem.add("G");
    testRem.add("F");
    testRem.add("E");
    testRem.add("D");
    // verify that peek and remove return the same element 
    // and the size decreased by one
    assertEquals(testRem.size(), 4);
    assertEquals(testRem.peek(), testRem.remove());
    assertEquals(testRem.size(), 3);
  }
 /**
  * Testing that elements of highest priority are removed first, using
  * remove() of Heap12<E> class.
  *
  * @see org.junit.Assert
  */ 
  public void testRemove()  {
    PQueue<Integer> testRemove = new Heap12<Integer>();
    testRemove.add(12);
    testRemove.add(2);
    // verifying largest element is removed first  
    assertEquals(testRemove.remove(), new Integer(12));    
    assertEquals(testRemove.remove(), new Integer(2));
    assertNull(testRemove.remove());
    assertTrue(testRemove.isEmpty());
    
  }
 /**
  * Testing that elements of highest priority are removed first with
  *  a lot of elements, using remove() of Heap12<E> class.
  *  Making sure that peek() and remove() return the same elements.
  *
  * @see org.junit.Assert
  */ 
  public void testRemove1()  {
    PQueue<Integer> testR = new Heap12<Integer>();
    // applying mutations
    testR.add(3);
    testR.add(4);
    testR.add(50);
    testR.add(99);
    testR.add(100);
    // verifying mutations 
    assertEquals(testR.remove(), new Integer(100));
    assertEquals(testR.peek(), new Integer(99));
    assertEquals(testR.remove(), new Integer(99));
    assertEquals(testR.peek(), new Integer(50));
    assertEquals(testR.remove(), new Integer(50));
    assertEquals(testR.peek(), new Integer(4));
    assertEquals(testR.remove(), new Integer(4));
    assertEquals(testR.peek(), new Integer(3));
    assertEquals(testR.remove(), new Integer(3));
    assertNull(testR.remove());
    assertNull(testR.peek());
  }
  
 /**
  * Testing remove() and add() methods of Heap12<E> class.
  * Adding random numbers and making sure that the elements
  * of higher priority are removed first. 
  *   
  * @see org.junit.Assert
  * @see java.util.Random
  */ 
  public void testRemoveAdd()  {
    PQueue<Integer> testRemAdd = new Heap12<Integer>();
    // adding random numbers 0 - 99
    for(int i = 0; i < 30; i++)  {
      java.util.Random ran = new Random();
      int k = ran.nextInt(100);
      testRemAdd.add(k);
    } 
    // making sure the elements removed in decreasing order
    int max = testRemAdd.remove();
    for(int i = 0; i < 14; i++)  {
      int big = testRemAdd.remove();
      int small = testRemAdd.remove();
      assertTrue(max >= big);
      assertTrue(big >= small);
    }
  } 

 /**
  * Testing isEmpty() of the Heap12<E> class.
  * Namely, the Heap12 object should be empty after creation,
  * after adding elements, isEmpty() should return false.
  *
  * @see org.junit.Assert
  */ 
  public void testIsEmpty()  {
    PQueue<Integer> testE = new Heap12<Integer>();  
    // verifying initial state of Heap12 object 
    assertTrue(testE.isEmpty());
    // applying mutations
    testE.add(12);
    // verifying mutations 
    assertFalse(testE.isEmpty());
    // applying mutations
    testE.remove();
    // verifying mutations
    assertTrue(testE.isEmpty());
  }

 /**
  * This method is testing size() and remove() methods of Heap12<E> class.
  * Namely, initially the size should be 0, increase by 1 when adding and
  * decrease by 1 when removing. 
  *
  * @see org.junit.Assert
  */
  public void testSizeRemove()  {
    PQueue<String> testS = new Heap12<String>();
    // verifying initial size 
    assertTrue(testS.size() == 0);
    // applying mutations
    testS.add("CSE");
    testS.add("UCSD");
    testS.add("Linguistics");
    // verifying mutations
    assertEquals(testS.size(), 3);
    // applying mutations
    assertEquals(testS.peek(), testS.remove());
    // verifying mutations
    assertEquals(testS.size(), 2);
  }

 /**
  * Testing equals() method of Heap12<E> class.
  * Making sure that two Heap12 objects are equals only if they have the same 
  * size and corresponding pairs of elements in the backing store arrays of 
  * the two heaps are equal.
  *
  * @see org.junit.assert
  */ 
  public void testEquals()  {
    PQueue<Integer> testEq1 = new Heap12<Integer>();
    PQueue<Integer> testEq2 = new Heap12<Integer>();
    // two empty heaps are equal
    assertTrue(testEq1.equals(testEq2));
    // apply mutations
    testEq1.add(5);
    testEq1.add(2);
    testEq1.add(3);
    testEq1.add(1);
    // verifying mutations 
    assertFalse(testEq1.equals(testEq2));
    // applying mutations
    testEq2.add(1);
    testEq2.add(5);
    testEq2.add(3);
    testEq2.add(2);
    // verifying mutations
    assertTrue(testEq1.equals(testEq2));
  }

 /**
  * This method is tesing equals() method of Heap12<E> class.
  * Namely, verifying that two heaps that holding objects of 
  * different types are only equal when empty.
  *
  * @see org.junit.assert
  */ 
  public void testEquals1()  {
    PQueue<Integer> testEq3 = new Heap12<Integer>();
    PQueue<String> testEq4 = new Heap12<String>();
    // should be equal when empty
    assertTrue(testEq3.equals(testEq4));
    // applying mutations 
    testEq3.add(5);
    testEq4.add("CSE12");
    // verifying mutations 
    assertFalse(testEq3.equals(testEq4));  
  
  }

 /**
  * This method is testing sort() method of Heap12<E> class.
  * Namely testing null argument should result in NullPointerException.
  *
  * @see org.junit.assert
  * @see java.lang.Ecxeption
  */ 
  public void testSortNull()  {
    try  {
      Integer [] arr = null;
      Heap12.sort(arr);
      fail("Expected NullPointerException");
    }
    catch (NullPointerException e)  {
    } // passed the test
  }

 /**
  * This method is testing sort() method of Heap12<E> class.\
  * Testing that the argument array of integers is sorted after
  * the method call.
  *
  * @see org.junit.Assert
  */ 
  public void testSort()  {
    Integer[] testSort = {1, 120, 25, 55, 0, 125};
    Heap12.sort(testSort);
    // verifying that the array is sorted
    assertEquals(testSort[0], new Integer(0));
    assertEquals(testSort[1], new Integer(1));
    assertEquals(testSort[2],  new Integer(25));
    assertEquals(testSort[3], new Integer(55));
    assertEquals(testSort[4], new Integer(120));
    assertEquals(testSort[5], new Integer(125));
  }
  
 /**
  * This method is testing sort() method of Heap12<E> class.
  * Testing that the argument array of Strings is sorted after
  * the method call.
  *
  * @see org.junit.Assert
  */ 
  public void testSort1()  {
    String[] testS = {"D", "X", "A"};
    Heap12.sort(testS);
    // verifying that the array is sorted
    assertEquals(testS[0], "A");
    assertEquals(testS[1], "D");
    assertEquals(testS[2], "X");
  }

 /**
  * This method is testing sort() method of Heap12<E> class.
  * Testing that the argument array of Strings is sorted after
  * the method call by comparing the sorted array to another
  * array that was pre-sorted.
  *
  * @see org.junit.Assert
  */ 
  public void testSort2() {
    String [] testSort2 = {"a", "k", "d", "u", "m", "b"};
    Heap12.sort(testSort2);
    assertEquals(testSort2[0], "a");
    assertEquals(testSort2[1], "b");
    assertEquals(testSort2[2], "d");
    assertEquals(testSort2[3], "k");
    assertEquals(testSort2[4], "m");
    assertEquals(testSort2[5], "u");
    // verifying that the array is sorted
    String [] testSort3 = {"a", "b", "d", "k", "m", "u"};
    assertTrue(Arrays.deepEquals(testSort2, testSort3)); 
  }

 /**
  * This mehtod is testing hashCode() of the Heap12<E> class.
  * Making sure that if two heap objects are equal their hash 
  * codes are the same by using two empty heap objects.
  *
  * @see org.junit.Assert
  */ 
  public void testHash()  {
    PQueue<Integer> testH1 = new Heap12<Integer>();
    PQueue<String> testH2 = new Heap12<String>();
    // verifying that equal heaps have same hashcode
    assertTrue(testH1.equals(testH2));
    assertTrue(testH1.hashCode() == testH2.hashCode());
  
  }

 /**
  * This mehtod is testing hashCode() of the Heap12<E> class.
  * Making sure that if two heap objects are equal their hash 
  * codes are the same by using two equal non-empty objects.
  *
  * @see org.junit.Assert
  */ 
  public void testHash1()  {
    PQueue<String> testH3 = new Heap12<String>();
    PQueue<String> testH4 = new Heap12<String>();
    // applying mutations 
    testH3.add("Hello");
    testH3.add("CSE");
    testH3.add("UCSD");
    testH4.add("UCSD");
    testH4.add("CSE");
    testH4.add("Hello");
    // verifying mutations
    assertTrue(testH3.equals(testH4));
    assertEquals(testH3.hashCode(), testH4.hashCode()); 
  }
  
 /**
  * Testing hashCode() of the Heap12<E> class after adding and
  * removing elements of two Heap12 objects.
  *
  * @see org.junit.Assert
  */ 
  public void testHashCode()  {
    PQueue<Integer> testHash1 = new Heap12<Integer>();
    PQueue<Integer> testHash2 = new Heap12<Integer>();
    testHash1.add(1);
    testHash1.add(50);
    testHash1.add(50);
    testHash1.add(50);
    testHash1.add(5);
    testHash1.remove();
    testHash2.add(50);
    testHash2.add(50);
    testHash2.add(50);
    testHash2.add(1);
    testHash2.add(5);
    testHash2.remove();
    // veifying mutations
    assertTrue(testHash1.equals(testHash2));
    assertEquals(testHash1.hashCode(), testHash2.hashCode());
  }
}
