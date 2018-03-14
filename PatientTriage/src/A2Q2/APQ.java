package A2Q2;

import java.util.*;

/**
 * Adaptible priority queue using location-aware entries in a min-heap, based on
 * an extendable array. The order in which equal entries were added is
 * preserved.
 *
 * @author jameselder
 * @param <E>
 *            The entry type.
 */
public class APQ<E> {

	private final ArrayList<E> apq; // will store the min heap
	private final Comparator<E> comparator; // to compare the entries
	private final Locator<E> locator; // to locate the entries within the queue

	/**
	 * Constructor
	 * 
	 * @param comparator
	 *            used to compare the entries
	 * @param locator
	 *            used to locate the entries in the queue
	 * @throws NullPointerException
	 *             if comparator or locator parameters are null
	 */
	public APQ(Comparator<E> comparator, Locator<E> locator) throws NullPointerException {
		if (comparator == null || locator == null) {
			throw new NullPointerException();
		}
		apq = new ArrayList<>();
		apq.add(null); // dummy value at index = 0
		this.comparator = comparator;
		this.locator = locator;
	}

	/**
	 * Inserts the specified entry into this priority queue.
	 *
	 * @param e
	 *            the entry to insert
	 * @throws NullPointerException
	 *             if parameter e is null
	 */
	public void offer(E e) throws NullPointerException {
		if (e == null) {
			throw new NullPointerException();
		}
		this.apq.add(e);
		int i = this.size();
		while (i > 1 && this.comparator.compare(e, this.apq.get(i / 2)) < 0) {
			this.upheap(i);
			i = i / 2;
		}
		this.locator.set(e, i);
	}

	/**
	 * Removes the entry at the specified location.
	 *
	 * @param pos
	 *            the location of the entry to remove
	 * @throws BoundaryViolationException
	 *             if pos is out of range
	 */
	public void remove(int pos) throws BoundaryViolationException {
		if (pos < 1 || pos > this.size()) {
			throw new BoundaryViolationException();
		}
		if (pos == this.size()) {
			this.apq.remove(this.size());
		} else {
			this.downheap(pos);
		}
	}

	/**
	 * Removes the first entry in the priority queue.
	 */
	public E poll() {
		if (isEmpty()) {
			return null;
		}
		E polled = this.apq.get(1);
		if (this.size() == 1) {
			this.apq.remove(1);
		} else {
			this.downheap(1);
		}
		return polled;
	}

	/**
	 * Returns but does not remove the first entry in the priority queue.
	 */
	public E peek() {
		if (isEmpty()) {
			return null;
		}
		return this.apq.get(1);
	}

	public boolean isEmpty() {
		return (size() == 0);
	}

	public int size() {
		return apq.size() - 1; // dummy node at location 0
	}

	/**
	 * Shift the entry at pos upward in the heap to restore the minheap property
	 * 
	 * @param pos
	 *            the location of the entry to move
	 */
	private void upheap(int pos) {
		int up = pos / 2;
		this.swap(pos, up);
	}

	/**
	 * Shift the entry at pos downward in the heap to restore the minheap
	 * property
	 * 
	 * @param pos
	 *            the location of the entry to move
	 */
	private void downheap(int pos) {
		this.swap(pos, this.size());
		this.apq.remove(this.size());
		int i = pos * 2;
		while (i + 1 <= this.size()) {
			if (comparator.compare(this.apq.get(i), this.apq.get(i + 1)) <= 0) {
				this.swap(pos, i);
				pos = i;
				i = 2 * i;
			} else if (comparator.compare(this.apq.get(i), this.apq.get(i + 1)) > 0) {
				this.swap(pos, i + 1);
				pos = i + 1;
				i = 2 * (i + 1);
			}
		}
		if (i == this.size() && comparator.compare(this.apq.get(pos), this.apq.get(i)) > 0) {
			this.swap(pos, i);
		}
	}

	/**
	 * Swaps the entries at the specified locations.
	 *
	 * @param pos1
	 *            the location of the first entry
	 * @param pos2
	 *            the location of the second entry
	 */
	private void swap(int pos1, int pos2) {
		E temp = this.apq.get(pos1);
		this.locator.set(this.apq.get(pos1), pos2);
		this.locator.set(this.apq.get(pos2), pos1);
		this.apq.set(pos1, this.apq.get(pos2));
		this.apq.set(pos2, temp);

	}
}