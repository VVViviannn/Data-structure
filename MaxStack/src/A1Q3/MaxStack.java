package A1Q3;
import java.util.*;

/**
 * 
 * Name: Yuxian Wang
 * ID: 215170418
 * 
 * 
 * Specializes the stack data structure for comparable elements, and provides
 * a method for determining the maximum element on the stack in O(1) time.
 * @author yuxian wang
 */
public class MaxStack<E extends Comparable<E>> extends Stack<E> {

    private Stack<E> maxStack;
    private Stack<E> getMaxStack;

    public MaxStack() {
        maxStack = new Stack<>();
        getMaxStack = new Stack<>();
    }

    /* must run in O(1) time */
    public E push(E element) {
    	super.push(element);
    	if(maxStack.isEmpty()){
    		
    		maxStack.push(element);
    		getMaxStack.push(element);
    		
    	}else{
    		if(element.compareTo(getMaxStack.peek()) <= 0){
    			maxStack.push(element);
    			getMaxStack.push(getMaxStack.peek());
    		}else{
    			maxStack.push(element);
    			getMaxStack.push(element);    
    		}
    	}
    	return element;	
    }

    /* @exception  EmptyStackException  if this stack is empty. */
    /* must run in O(1) time */
   public synchronized E pop() {
	   super.pop();
	   if(maxStack.isEmpty()){
		   throw new EmptyStackException();
	   }else{
		   maxStack.pop();
		   getMaxStack.pop();
		   return maxStack.peek(); 
	   }
    }

    /* Returns the maximum value currenctly on the stack. */
    /* @exception  EmptyStackException  if this stack is empty. */
    /* must run in O(1) time */
    public synchronized E max() {
    	if(maxStack.isEmpty()){
 		   throw new EmptyStackException();
 	   }else{
 		  return getMaxStack.peek(); 
 	   }
    }
}
