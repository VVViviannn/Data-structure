package A1Q1;

import java.util.*;

/**
 * 
 * Name: Yuxian Wang
 * ID: 215170418
 *
 *
 * Represents a sparse numeric vector. Elements are comprised of a (long)
 * location index an a (double) value. The vector is maintained in increasing
 * order of location index, which facilitates numeric operations like inner
 * products (projections). Note that location indices can be any integer from 1
 * to Long.MAX_VALUE. The representation is based upon a singly-linked list. The
 * following methods are supported: iterator, getSize, getFirst, add, remove,
 * and dot, which takes the dot product of the with a second vector passed as a
 * parameter.
 * 
 * @author yuxian wang
 */
public class SparseNumericVector implements Iterable {

	protected SparseNumericNode head = null;
	protected SparseNumericNode tail = null;
	protected long size;

	/**
	 * Iterator
	 */
	@Override
	public Iterator<SparseNumericElement> iterator() { // iterator
		return new SparseNumericIterator(this);
	}

	/**
	 * @return number of non-zero elements in vector
	 */
	public long getSize() {
		return size;
	}

	/**
	 * @return the first node in the list.
	 */
	public SparseNumericNode getFirst() {
		return head;
	}

	/**
	 * Add the element to the vector. It is inserted to maintain the vector in
	 * increasing order of index. If the element has zero value, or if an
	 * element with the same index already exists, an
	 * UnsupportedOperationException is thrown.
	 * 
	 * @param e
	 *            element to add
	 */
	public void add(SparseNumericElement e) throws UnsupportedOperationException {
		if (e.getValue() == 0) {
			throw new UnsupportedOperationException("zero value");
		}
		SparseNumericNode currentNode = new SparseNumericNode(e, null);
		if (head == null) {
			size = 0;
			head = currentNode;
			head.setNext(tail);
			size++;
		} else {
			SparseNumericNode temp = head;
			if (currentNode.getElement().getIndex() < head.getElement().getIndex()) {
				head = currentNode;
				head.setNext(temp);
				size++;
			} else if (currentNode.getElement().getIndex() > head.getElement().getIndex()) {
				if (size == 1) {
					head.setNext(currentNode);
					size++;
				} else {
					for (int i = 1; i < size; i++) {
						if (currentNode.getElement().getIndex() > temp.getNext().getElement().getIndex()) {

							temp = temp.getNext();
							while (temp.getNext() == null) {
								temp.setNext(currentNode);
								break;
							}
						} else if (currentNode.getElement().getIndex() < temp.getNext().getElement().getIndex()) {

							currentNode.setNext(temp.getNext());
							temp.setNext(currentNode);
							break;

						} else {
							System.out.println(size);
							System.out.println("error");
							throw new UnsupportedOperationException("already exists");
						}
					}
					size++;
				}
			} else {
				throw new UnsupportedOperationException("already exists");
			}
		}
	}

	/**
	 * If an element with the specified index exists, it is removed and the
	 * method returns true. If not, it returns false.
	 *
	 * @param index
	 *            of element to remove
	 * @return true if removed, false if does not exist
	 */
	public boolean remove(Long index) {
		SparseNumericNode temp = head;
		if(index == head.getElement().getIndex()){
			temp = head.getNext();
			temp.setNext(temp.getNext());
			head = temp;
			size --;
			return true;
		}else{
		for (int i = 1; i < size; i++){
			if(index == temp.getNext().getElement().getIndex()){
				temp.setNext(temp.getNext().getNext());
				size --;
				return true;
				
			}else{
				temp = temp.getNext();
				head.setNext(head.getNext());
			}
		  }
		return false;
		}
	}

	/**
	 * Returns the inner product of the vector with a second vector passed as a
	 * parameter. The vectors are assumed to reside in the same space. Runs in
	 * O(m+n) time, where m and n are the number of non-zero elements in each
	 * vector.
	 * 
	 * @param Y
	 *            Second vector with which to take inner product
	 * @return result of inner product
	 */

	public double dot(SparseNumericVector Y) {
		double sum = 0.0;
		SparseNumericNode tempX = this.head;
		SparseNumericNode tempY = Y.head;
		while(tempX != null && tempY != null){
			if(tempX.getElement().getIndex() == tempY.getElement().getIndex()){
			sum += tempX.getElement().getValue() * tempY.getElement().getValue();
			tempX = tempX.getNext();
			tempY = tempY.getNext();
			}else if(tempX.getElement().getIndex() < tempY.getElement().getIndex()){
				tempX = tempX.getNext();
				}else{
					tempY = tempY.getNext();
					}
			
		}
		return sum;
	}

	/**
	 * returns string representation of sparse vector
	 */

	@Override
	public String toString() {
		String sparseVectorString = "";
		Iterator<SparseNumericElement> it = iterator();
		SparseNumericElement x;
		while (it.hasNext()) {
			x = it.next();
			sparseVectorString += "(index " + x.getIndex() + ", value " + x.getValue() + ")\n";
		}
		return sparseVectorString;
	}
}
