import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AssociatedListTest {

	@Test
	public void inheritanceTest( ) {
		Object alist = new AssociatedList( );
		if ( ! ( alist instanceof SimpleMap ) ) {
			fail( "Your AssociatedList class does not extend the provided abstract class." );
		}
	}

	@Test
	public void putTest1( ) {
		AssociatedList alist = new AssociatedList( );
		String key = "Thing";
		int value = 1;
		try {
			alist.put(key, value);
		} catch ( IndexOutOfBoundsException e ) {
			String [ ] parts = e.getMessage().split(",");
			Scanner iscan = new Scanner( parts[0] );
			iscan.next( );
			int index = iscan.nextInt();
			Scanner sscan = new Scanner( parts[1] );
			sscan.next( );
			int size = sscan.nextInt();
			if ( index == size ) {
				Error err =  new AssertionError("Index Out Of Bounds. " + e.getMessage() + "\nCheck your loop end conditions.\nYou may have an Off-By-One issue in a loop.");
				err.setStackTrace( e.getStackTrace() );
				throw err;
			} else {
				throw e;
			}
		}
	}

	@Test
	public void putTest3( ) {
		AssociatedList alist = new AssociatedList( );
		alist.put("A", 1);
		alist.put("B", 2);
		alist.put("C", 3);
		String key = "C";
		int value = 3;
		boolean found = false;
		boolean correct = false;
		for( AssociatedList.Entry kvp : alist.entryList () ) {
			if ( key.equals(kvp.getKey()) ) {
				found = true;
				if ( value == kvp.getValue() ) {
					correct = true;
				}
			}
		}
		if ( !found ) {
			fail( String.format("put(%s, %d) executed, but key was not found in associated list: %s.", key, value, alist.entryList ()));
		}
		if ( !correct ) {
			fail( String.format("put(%s, %d) executed, but value found was incorrect: %s.", key, value, alist.entryList ()));
		}
	}

	@Test
	public void putTest2( ) {
		AssociatedList alist = new AssociatedList( );
		alist.put("A", 0);
		alist.put("B", 0);
		alist.put("C", 0);
		alist.put("C", 3);
		String key = "C";
		int value = 3;
		boolean found = false;
		boolean correct = false;
		for( AssociatedList.Entry kvp : alist.entryList () ) {
			if ( key.equals(kvp.getKey()) ) {
				found = true;
				if ( value == kvp.getValue() ) {
					correct = true;
				}
			}
		}
		if ( !correct ) {
			fail( String.format("put(%s, %d) executed to change value from 0 to 3, but value found was unchanged: %s.", key, value, alist.entryList ()));
		}
	}

	@Test
	public void getTest1( ) {
		AssociatedList alist = new AssociatedList();
		try {
			alist.put("A", 1);
		} catch ( Exception e ) {
			// Left Empty
		}
		if ( alist.entryList ().size() == 0 ) {
			fail( String.format( "Can't complete get test because put(%s, %d) failed, array list is empty.", "A", 1) );
		}
	}

	@Test
	public void getTest2( ) {
		AssociatedList alist = new AssociatedList();
		alist.put("A", 1);
		String key = "A";
		Integer value = 1;
		Integer result = alist.get(key);
		if (!value.equals(result)) {
			boolean found = false;
			boolean correct = false;
			for (AssociatedList.Entry kvp : alist.entryList ()) {
				if (key.equals(kvp.getKey())) {
					found = true;
					if (value.equals( kvp.getValue()) ) {
						correct = true;
					}
				}
			}
			if (!found) {
				fail(String.format("get(%s) returned %d when I was expecting %d. The key was not found in associated list: %s.", key, result, value, alist.entryList ()));
			}
			if (!correct) {
				fail(String.format("get(%s) returned %d when I was expecting %d. The value was wrong in the list: %s.", key, result, value, alist.entryList ()));
			}
			fail(String.format("get(%s) returned %d when I was expecting %d. However, the correct value is in the list: %s.", key, result, value, alist.entryList ()));
		}
	}

	@Test
	public void getTest3( ) {
		AssociatedList alist = new AssociatedList();
		for( char c = 'A'; c < 'K'; c++ ) {
			alist.put( "" + c, (int) c );
		}
		boolean allnull = true;
		for( char c = 'A'; c < 'Z'; c++ ) {
			if ( alist.get( "" + c ) != null ) {
				allnull = false;
			}
		}
		if ( allnull ) {
			fail( String.format( "The get method always seems to return null. Trace through the method to see if any other result is possible." ) );
		}
	}

	@Test
	public void isEmptyTest1( ) {
		AssociatedList alist = new AssociatedList();
		if ( !alist.isEmpty () ) {
			fail( String.format( "Immediately after AssociatedList instanciated by calling the no-args constructor, isEmpty returned false." ) );
		}
	}

	@Test
	public void isEmptyTest2( ) {
		AssociatedList alist = new AssociatedList();
		alist.put( "A", 1 );
		if ( alist.isEmpty () ) {
			fail( String.format( "Immediately after put( \"A\", 1 ), isEmpty returned true." ) );
		}
	}

	@Test
	public void isEmptyTest3( ) {
		AssociatedList alist = new AssociatedList();
		alist.put( "A", 1 );
		alist.remove ( "A" );
		if ( !alist.isEmpty () ) {
			fail( String.format( "After removing last association, isEmpty returned false." ) );
		}
	}

	@Test
	public void isEmptyTest4( ) {
		AssociatedList alist = new AssociatedList();
		alist.put( "A", 1 );
		alist.put( "B", 2 );
		alist.put( "C", 3 );
		alist.remove ( "B" );
		alist.remove ( "C" );
		alist.remove ( "A" );
		if ( !alist.isEmpty () ) {
			fail( String.format( "After removing last association, isEmpty returned false." ) );
		}
	}

	@Test
	public void sizeTest1( ) {
		AssociatedList alist = new AssociatedList();
		int exprected = 0;
		int size = alist.size ();
		if ( size != exprected ) {
			fail( String.format( "Immediately after AssociatedList instanciated by calling the no-args constructor, size returned %d expected %d.", size, exprected ) );
		}
	}

	@Test
	public void sizeTest2( ) {
		AssociatedList alist = new AssociatedList();
		alist.put ( "A", 1 );
		int exprected = 1;
		int size = alist.size ();
		if ( size != exprected ) {
			fail( String.format( "Immediately after put( \"A\", 1 ), size returned %d expected %d.", size, exprected ) );
		}
	}

	@Test
	public void sizeTest3( ) {
		AssociatedList alist = new AssociatedList();
		alist.put ( "A", 1 );
		alist.put ( "B", 2 );
		alist.put ( "C", 3 );
		int exprected = 3;
		int size = alist.size ();
		if ( size != exprected ) {
			fail( String.format( "Immediately after adding three entries to the list, size returned %d expected %d.", size, exprected ) );
		}
	}

	@Test
	public void sizeTest4( ) {
		AssociatedList alist = new AssociatedList();
		alist.put ( "A", 1 );
		alist.put ( "B", 2 );
		alist.put ( "C", 3 );
		alist.put ( "C", 2 );
		alist.put ( "C", 1 );
		int exprected = 3;
		int size = alist.size ();
		if ( size != exprected ) {
			fail( String.format( "Added three entries to the list then replaced the value in an entry, size returned %d expected %d.", size, exprected ) );
		}
	}

	@Test
	public void sizeTest5( ) {
		AssociatedList alist = new AssociatedList();
		alist.put ( "A", 1 );
		alist.put ( "B", 2 );
		alist.put ( "C", 3 );
		alist.remove ( "B" );
		int exprected = 2;
		int size = alist.size ();
		if ( size != exprected ) {
			fail( String.format( "Added three entries to the list then removed an entry, size returned %d expected %d.", size, exprected ) );
		}
	}

	@Test
	public void removeTest1( ) {
		AssociatedList alist = new AssociatedList();
		Integer exprected = null;
		Integer result = alist.remove ( "A" );
		if ( exprected != result ) {
			fail( String.format( "Executed remove ( \"A\" ) on empty list, remove returned %d expected %d.", result, exprected ) );
		}
	}

	@Test
	public void removeTest2( ) {
		AssociatedList alist = new AssociatedList();
		alist.put ( "A", 1 );
		alist.put ( "B", 2 );
		alist.put ( "C", 3 );
		Integer exprected = 1;
		Integer result = alist.remove ( "A" );
		if ( !exprected.equals( result ) ) {
			fail( String.format( "Executed put ( \"A\", 1 ) then remove ( \"A\" ), remove returned %d expected %d.", result, exprected ) );
		}
	}

	@Test
	public void removeTest3( ) {
		AssociatedList alist = new AssociatedList();
		alist.put ( "A", 1 );
		alist.put ( "B", 2 );
		alist.put ( "C", 3 );
		alist.remove ( "B" );
		Integer exprected = null;
		Integer result = alist.remove ( "B" );
		if ( exprected!= result ) {
			fail( String.format( "Executed put ( \"B\", 2 ) then remove ( \"B\" ) then remove ( \"B\" ), remove returned %d expected %d.", result, exprected ) );
		}
	}

	@Test
	public void entryListTest1( ) {
		AssociatedList alist = new AssociatedList();
		alist.put ( "A", 1 );
		alist.put ( "B", 2 );
		alist.put ( "C", 3 );
		List< SimpleMap.Entry > list = alist.entryList ();
		if ( list.size( ) != 3 ) {
			fail( String.format( "Added three entries to list then entryList( ) returned %s.", list ) );
		}
		boolean a = false, b = false, c = false;
		for ( SimpleMap.Entry entry : list ) {
			a = a || entry.getKey ().equals ( "A" ) && entry.getValue () == 1;
			b = b || entry.getKey ().equals ( "B" ) && entry.getValue () == 2;
			c = c || entry.getKey ().equals ( "C" ) && entry.getValue () == 3;
		}
		if ( (!a) || (!b) || (!c) ) {
			fail( String.format( "Added three entries to list then entryList( ) returned %s.", list ) );
		}
	}



}