package A3Q1;

import A3Q1.LinkedBinaryTree.Node;

/**
 * Extends the TreeMap class to allow convenient access to entries within a
 * specified range of key values (findAllInRange).
 * 
 * @author yuxian wang
 */
public class BSTRange<K, V> extends TreeMap<K, V> {

	/*
	 * Returns the lowest (deepest) position in the subtree rooted at pos that is a
	 * common ancestor to positions with keys k1 and k2, or to the positions they
	 * would occupy were they present.
	 */
	protected Position<Entry<K, V>> findLowestCommonAncestor(K k1, K k2, Position<Entry<K, V>> pos) {
		if (pos.getElement() != null) {
			if (this.compare(k1, pos.getElement().getKey()) < 0 && this.compare(k2, pos.getElement().getKey()) < 0) {
				return findLowestCommonAncestor(k1, k2, tree.left(pos));
			} else if (this.compare(k1, pos.getElement().getKey()) > 0 && this.compare(k2, pos.getElement().getKey()) > 0) {
				return findLowestCommonAncestor(k1, k2, tree.right(pos));
			} else {
				return pos;
			}
		}
		return pos;
	}

	/*
	 * Finds all entries in the subtree rooted at pos with keys of k or greater and
	 * copies them to L, in non-decreasing order.
	 */
	protected void findAllAbove(K k, Position<Entry<K, V>> pos, PositionalList<Entry<K, V>> L) {
		if (pos.getElement() != null) {
			if(this.compare(k,pos.getElement()) == 0) {
				L.addFirst(pos.getElement());
				findAllAbove(k,left(pos), L);
			}else if(this.compare(k,pos.getElement()) < 0){
				findAllAbove(k,right(pos), L);
	            L.addFirst(pos.getElement());
	            findAllAbove(k,left(pos), L);
			}else {
				findAllAbove(k, right(pos), L);
			}
		}
	}

	/*
	 * Finds all entries in the subtree rooted at pos with keys of k or less and
	 * copies them to L, in non-decreasing order.
	 */
	protected void findAllBelow(K k, Position<Entry<K, V>> pos, PositionalList<Entry<K, V>> L) {
		if (pos.getElement() != null) {
			if(this.compare(k,pos.getElement()) == 0) {
				L.addFirst(pos.getElement());
				findAllBelow(k,left(pos), L);
			}else if(this.compare(k,pos.getElement()) > 0){
				findAllBelow(k,right(pos), L);
	            L.addFirst(pos.getElement());
	            findAllBelow(k,left(pos), L);
			}else {
				findAllBelow(k, left(pos), L);
			}
		}
	}

	/*
	 * Returns all entries with keys no less than k1 and no greater than k2, in
	 * non-decreasing order.
	 */
	public PositionalList<Entry<K, V>> findAllInRange(K k1, K k2) {
		LinkedPositionalList<Entry<K, V>> range = new LinkedPositionalList<Entry<K, V>>();
		if(this.compare(k1, k2) <= 0) {
			Position<Entry<K, V>> ComA = this.findLowestCommonAncestor(k1, k2, tree.root());
			if (ComA.getElement() == null) {
				return range;
			}else {
				this.findAllBelow(k2, tree.right(ComA), range);
				range.addFirst(ComA.getElement());
				this.findAllAbove(k1, tree.left(ComA), range);
				return range;
			}
		}else {
			return range;
		}
		
		
	}
}
