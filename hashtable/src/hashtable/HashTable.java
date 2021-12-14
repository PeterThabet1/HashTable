package hashtable;

import hashtable.textio.TextIO;

/**
 * This class implements the concept of the HashTable. However, many additional
 * methods were ignored, since it wasn't required from the assignment.
 * the HashTable initial size was determined with 12 nodes following the convention.
 * Increasing the size of the HashTable isn't possible in this class. It wasn't required.
 * The program includes a simple interactive program, where the user can use to
 * test the program.
 * 
 * 
 * @author Peter Thabet
 *
 */
public class HashTable { // HashTable begin

	private ListNode[] table; // The HashTable
	private int count; // The size


	public static void main(String[] args) { // main() begin

		HashTable map = new HashTable(); //instantiating an object
		String key, value; // input key and value.

		while(true) {

			System.out.println("Please, test my program");
			System.out.println("1- get(key)");
			System.out.println("2- put(key, value)");
			System.out.println("3- remove(key)");
			System.out.println("4- containsKey(key)");
			System.out.println("5- size()");
			System.out.println("6- abort");
			System.out.print("enter a number: ");
			int input = TextIO.getlnInt();

			switch(input) {

			case 1 -> { // get()
				System.out.println("Key: ");
				key = TextIO.getlnString();
				System.out.println("Value: " + map.get(key));
				break;
			}

			case 2 -> { // put()
				System.out.println("Key: ");
				key = TextIO.getlnString();
				System.out.println("Value: ");
				value = TextIO.getlnString();
				map.put(key, value);
				System.out.println("Done!");
				break;
			}

			case 3 -> { // remove()
				System.out.println("Key: ");
				key = TextIO.getlnString();
				map.remove(key);
				System.out.println("Done!");
				break;
			}

			case 4 -> { // containsKey()
				System.out.println("Key: ");
				key = TextIO.getlnString();
				System.out.println(map.containsKey(key));
				break;
			}

			case 5 -> { // size()
				System.out.println("Size: " + map.size());
				break;
			}

			case 6 -> { // aborting the prog.
				return;
			}

			default -> { // number not expected
				System.out.println("The number you entered isn't in the menu.");
				break;
			}

			}
		}
	} // main() end


	private class ListNode{ // representing the array elements
		String key; 
		String value;
		ListNode next;
	}


	public HashTable(){
		table = new ListNode[12]; // initial size 12
	}


	/**
	 * The method takes a key and return the associated value.
	 * 
	 * @param key the key of the association.
	 * @return the value of the association.
	 */
	public String get(String key) { // get() begin

		int arrLoc = hash(key); // the index of the array

		ListNode list = table[arrLoc]; // the runner in the list

		while(list != null) {
			if(list.key.equals(key)) { // key found
				return list.value;
			}
			list = list.next;
		}
		return null; // no key was found
	} // get() end


	/**
	 * The method computes the hash code based on the key of the association, then
	 * replaces it with its associated value in the correct array location in the 
	 * correct LinkedList location.
	 * 
	 * @param key the key of the association
	 * @param value the value of the association
	 */
	public void put(String key, String value) { // put() begin

		int arrLoc = hash(key); // getting the right array location.

		ListNode list = table[arrLoc]; // the runner in the LinkedList

		while(list != null) {
			if(list.key.equals(key)) { // the same key was found.
				break;
			}
			list = list.next;
		}

		if(list != null) { // switching the old value with the new passed in parameter
			list.value = value;
		}
		else { // a new key, a new node must be created.

			ListNode newNode = new ListNode();
			newNode.key = key;
			newNode.value = value;

			newNode.next = table[arrLoc]; // getting the same array loc.
			table[arrLoc] = newNode; // placing the node in the location.
			count++; // increasing the size.
		}	
	} // put() end


	/**
	 * This method takes the key of the association and remove the whole node with
	 * the associated key.
	 * 
	 * @param key of the association to be removed
	 */
	public void remove(String key) { // remove() begin

		int arrLoc = hash(key); 

		if(table[arrLoc] == null) { // the location was empty
			return;
		}

		if(table[arrLoc].key.equals(key)) { // the key was found.

			table[arrLoc] = table[arrLoc].next;
			count--;
			return;
		}

		ListNode prev = table[arrLoc]; 

		ListNode current = prev.next;

		while(current != null && !current.key.equals(key)) {
			current = current.next;
			prev = current;
		}

		if(current != null) { // key found
			prev.next = current.next;
			count--;
		}

	} // remove() end


	/**
	 * The method iterates the HashTable and returns true if the key was found,
	 * otherwise false.
	 * 
	 * @param key of the association
	 * @return true if key was found
	 */
	public boolean containsKey(String key) { // containsKey() begin

		int arrLoc = hash(key);

		ListNode list = table[arrLoc];

		while(list != null) {
			if(list.key.equals(key)) { // key found
				return true;
			}
			list = list.next;
		}
		return false; // key wasn't found
	} //containsKey() end


	/**
	 * The method returns the size of the HashTable.
	 * 
	 * @return the size of the HashTable
	 */
	public int size() { // size() begin
		return count;
	} // size() end


	/**
	 * The method takes the key of the association and returns the hashcode of
	 * the key based on the key and the tables lenght.
	 * 
	 * @param key to hash
	 * @return int hashcode of the key
	 */
	private int hash(String key) { // hash() begin
		return (Math.abs(key.hashCode() % table.length));
	} // hash() end

} // HashTable end
