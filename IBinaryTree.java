public interface IBinaryTree<T> {
    public void insert(T value); // insert a new value into the tree
    public boolean remove(int key); // remove node holding the specified key from the tree, returns true if found, false otherwise
    public BinaryTreeNode search(int key); // find the key in the tree, returning its tree node, or null if it is not found
    public void print(); // print the tree vertically, one Student per line, in breadth-first mode
}
    
