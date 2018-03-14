package A2Q1;
import java.util.*;
/**
 * Represents a sorted integer array.  Provides a method, kpairSum, that
 * determines whether the array contains two elements that sum to a given
 * integer k.  Runs in O(n) time, where n is the length of the array.
 * @author Yuxian Wang
 */
public class SortedIntegerArray {

    protected int[] sortedIntegerArray;

    public SortedIntegerArray(int[] integerArray) {
        sortedIntegerArray = integerArray.clone();
        Arrays.sort(sortedIntegerArray);
    }

/**
 * Determines whether the array contains two elements that sum to a given
 * integer k. Runs in O(n) time, where n is the length of the array.
 * @author Yuxian Wang
 */
    public boolean kPairSum(Integer k) {
    	
    	return kPairSumInterval(k, 0, sortedIntegerArray.length-1);
    	
    }
    
    private boolean kPairSumInterval(Integer k, int i, int j){
    	if(i == j || sortedIntegerArray.length <= 1){
    		return false;
    	}
    	if(((long)sortedIntegerArray[i]+(long)sortedIntegerArray[j]) < (long)k){
    		return kPairSumInterval(k, i+1, j);
    	}else if(((long)sortedIntegerArray[i]+(long)sortedIntegerArray[j]) > (long)k){
    		return kPairSumInterval(k, i, j-1);
    	}else{
    		return true;
    	}
    }
}
