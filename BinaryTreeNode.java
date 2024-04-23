public class BinaryTreeNode {
	Student data; // holds the student information
	BinaryTreeNode left; // acts as the pointer to the left child of this node
	BinaryTreeNode right; // acts as the pointer to the right child of this node
	BinaryTreeNode parent; // pointer to the parent of this node
	
	public BinaryTreeNode(Student data) { // constructor for the new node
		this.data = data; // having this as the only line ensures the left and right values will be null by default
	}
	
	public BinaryTreeNode getSuccessor() {
		// Successor resides in right subtree, if present
		if (right != null) {
			BinaryTreeNode successor = right;
			while (successor.left != null) {
				successor = successor.left;
			}
			return successor;
		}

		// Otherwise the successor is up the tree
		// Traverse up the tree until a parent is encountered from the left
		BinaryTreeNode node = this;
		while (node.parent != null && node == node.parent.right) {
			node = node.parent;
		}
		return node.parent;
	}
}
