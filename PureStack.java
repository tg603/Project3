/**
 * Creates a stack data structure 
 *
 * @Zach 'TG' Thoroughgood
 */
 
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.lang.ClassCastException;
public class PureStack <E extends Object> {
	
	private ArrayList<E> mylist;
	
    /**
     * Creates a new instance of EmptyClass.
     */
    public PureStack() {
		mylist = new ArrayList<E>();
    }
    
    /**
     * Returns a String version of this.
     *
     * @return  A String description of this.
     */
    public String toString() {
		String a = "Top";
		String b = "Bottom";
        return b + " --->" + mylist + "<---" + a + "\n" + a + " --->" + mylist + "<---" + b ;
    }
	
	
	public void push(E item){
		mylist.add(item);
	}
    
	
/**
* Pop() method will return the top of your stacke and remove it from the stack
*
*@throws NoSuchElementException when the stack is empty
*@return The very first element in your stack
*/
	public E pop(){
		if(mylist.isEmpty()){
			//Testing code to make sure it throws the exception
			//Not sure why it doesn't out print my other line?
			System.out.println("Use a better line moron!");
			throw new NoSuchElementException("Use a better string than this.");
		}
		else{
			
			//return mylist.remove(mylist.size());
			
			return mylist.remove(mylist.size() - 1);
			
		}
	}
	
	
		public E peek(){
		if(mylist.isEmpty()){
			System.out.println("Peek-A-Boo");
			throw new NoSuchElementException("Use a better string than this.");
		}
		else{
			
			/*
			int a = mylist.size()- 1;
			System.out.println(a);
			*/
			
			return mylist.get(mylist.size() - 1);
			
		}
	}
	
	
	public boolean isEmpty(){
		return mylist.isEmpty();
	}
    
	/*
	public boolean equals(Object item){
		System.out.println(item);
		
		return "[" + item + "]" == this.mylist.toString();
		
		return mylist.toString().equals(item);
	}
	*/
		public boolean equals(PureStack<E> item){
			return mylist.equals(item.mylist);
	}
	
		public boolean equals (Object obj){
		PureStack<E> other = (PureStack<E>) obj;
		try{
			return this.mylist.equals(other.mylist);
		} catch (ClassCastException e){
			return false;
		}
	}
	
	
    /** 
     * Unit test for EmptyClass
     
     * @param args  Arguments used to test this class.
     */
    public static void main(String[] args) {
		PureStack<String> Strings = new PureStack<String>();
		Strings.push("hi");
		Strings.push("hola");
		Strings.push("chow");
		System.out.println(Strings);
		
		
		boolean isCorrect;
		boolean allCorrect = true;
		
		
		String failure = "This test failed";
		
		String success = "This test succeeded";
		
		
		//Tests an empty Stack and correctly throws an exception for it
		//Couldn't get it to take Alpacas and I'm not really sure why???
		//Put in <String> which functions inside of the test
		PureStack<String> alpacas = new PureStack<String>();
		try {
			alpacas.pop();
			//if the code reaches this point, the exception didn't get thrown!
			isCorrect = false;
		} catch (NoSuchElementException e) {
			//the exception was thrown; the method worked correctly!
			isCorrect = true;
		}
		System.out.println((isCorrect ? success : failure));
		allCorrect = allCorrect && isCorrect;
		
		
		//Tests one element inside of the stack which should come back as a failed test
		//Should come back failed as the exception wasn't thrown
		//Meaning the stack != empty
		alpacas.push("spit");
		//Shows that push() works correctly
		System.out.println("\n" + "Before Exception Test");
		System.out.println(alpacas);
		System.out.println("\n");
		try {
			alpacas.pop();
			//if the code reaches this point, the exception didn't get thrown!
			isCorrect = false;
		} catch (NoSuchElementException e) {
			//the exception was thrown; the method worked correctly!
			isCorrect = true;
		}
		System.out.println((isCorrect ? success : failure));
		allCorrect = allCorrect && isCorrect;
		
		System.out.println("\n" + "After Exception Test");
		
		System.out.println(alpacas);
		
		System.out.println("Shows the element got deleted via pop()" + "\n");
		
		alpacas.push("Spit");
		
		System.out.println("\n" + "Pushed element back into stack");
		
		System.out.println(alpacas);
		
		alpacas.push("Chew");
		
		System.out.println("\n" + "Pushed a second element into stack");
		
		System.out.println(alpacas);
		
		try {
			alpacas.pop();
			//if the code reaches this point, the exception didn't get thrown!
			isCorrect = false;
		} catch (NoSuchElementException e) {
			//the exception was thrown; the method worked correctly!
			isCorrect = true;
		}
		
		System.out.println((isCorrect ? success : failure));
		System.out.println(alpacas);
		//Tests the same stack 
		//Should return succeeded test as the element should of been removed via the pop() method
		System.out.println("\n");
		try {
			alpacas.pop();
			//if the code reaches this point, the exception didn't get thrown!
			isCorrect = false;
		} catch (NoSuchElementException e) {
			//the exception was thrown; the method worked correctly!
			isCorrect = true;
		}
		System.out.println((isCorrect ? success : failure));
		allCorrect = allCorrect && isCorrect;
		System.out.println(alpacas);
		
		
		//Tests peek() with an empty Stack
		try {
			alpacas.peek();
			//if the code reaches this point, the exception didn't get thrown!
			isCorrect = false;
		} catch (NoSuchElementException e) {
			//the exception was thrown; the method worked correctly!
			isCorrect = true;
		}
		System.out.println((isCorrect ? success : failure));
		allCorrect = allCorrect && isCorrect;
		
		alpacas.push("Smile");
		System.out.println(alpacas);
		
			try {
			alpacas.peek();
			//if the code reaches this point, the exception didn't get thrown!
			isCorrect = false;
		} catch (NoSuchElementException e) {
			//the exception was thrown; the method worked correctly!
			isCorrect = true;
		}
		System.out.println((isCorrect ? success : failure));
		allCorrect = allCorrect && isCorrect;
		
		System.out.println(alpacas);
		alpacas.push("Happy");
		System.out.println(alpacas);
    
		try {
			alpacas.peek();
			//if the code reaches this point, the exception didn't get thrown!
			isCorrect = false;
		} catch (NoSuchElementException e) {
			//the exception was thrown; the method worked correctly!
			isCorrect = true;
		}
		System.out.println((isCorrect ? success : failure));
		allCorrect = allCorrect && isCorrect;
		
		System.out.println(alpacas);
		
		alpacas.pop();
	
		System.out.println(alpacas);
		
		//Testing equals
		try {
			alpacas.equals("Smile");
			//if the code reaches this point, the exception didn't get thrown!
			isCorrect = false;
		} catch (ClassCastException e) {
			//the exception was thrown; the method worked correctly!
			isCorrect = true;
		}
		System.out.println((isCorrect ? success : failure));
		allCorrect = allCorrect && isCorrect;
	}

} //end of EmptyClass