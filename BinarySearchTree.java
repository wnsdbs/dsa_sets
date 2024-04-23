public class BinarySearchTree implements IBinaryTree<Student> {
    protected BinaryTreeNode root = null;
    
    // insert a new value into the tree
    public void insert(Student value) {
        BinaryTreeNode node = new BinaryTreeNode(value);

        if (root == null) {
            root = node;
        } else {
            attachToNode(root, node);
        }
    }
    
    // a method that hangs the new node off of the proper side of the current node
    // and recurses if the desired spot is not null
    private void attachToNode(BinaryTreeNode parent, BinaryTreeNode node) {
        if (node.data.studentId >= parent.data.studentId) {
            if (parent.right == null) {
                parent.right = node;
                node.parent = parent;
            } else {
                attachToNode(parent.right, node);
            }
        } else {
            if (parent.left == null) {
                parent.left = node;
                node.parent = parent;
            } else {
                attachToNode(parent.left, node);
            }
        }
    }
    
    // remove node holding the specified key from the tree, returns true if found, false otherwise
    public boolean remove(int key) {
        // search for the key
        BinaryTreeNode btn = search(key);
        // if not found
        if (btn == null) {
            // return false    
            return false;
        // else
        } else {
            if (btn.left == null && btn.right == null) {  // remove a leaf
                if (btn == root) {
                    root = null;
                } else {
                    if (btn.parent.left == btn) {
                        btn.parent.left = null;
                    } else {
                        btn.parent.right = null;
                    }
                }
            } else if ((btn.left == null & btn.right != null) ||
                       (btn.left != null & btn.right == null)) {  // remove a node with one child
                if (btn == root) {
                    if (btn.left == null) {
                        root = root.right;
                    } else {
                        root = root.left;
                    }
                    root.parent = null;
                } else {
                    if (btn.parent.left == btn) {
                        if (btn.left == null) {
                            btn.parent.left = btn.right;
                            btn.right.parent = btn.parent;
                        } else {
                            btn.parent.left = btn.left;
                            btn.left.parent = btn.parent;
                        }
                    } else {
                        if (btn.left == null) {
                            btn.parent.right = btn.right;
                            btn.right.parent = btn.parent;
                        } else {
                            btn.parent.right = btn.left;
                            btn.left.parent = btn.parent;
                        }
                    }
                }
            } else {  // remove a node with both children
                // find left-most node on right child
                BinaryTreeNode currentNode = btn.right;
                while (currentNode.left != null) {
                    currentNode = currentNode.left;
                }

                btn.data = currentNode.data;  // copy the data from the left-most node into the node being deleted

                if (currentNode == btn.right) { // the right node WAS the left-most node
                    currentNode.parent.right = currentNode.right;
                    if (currentNode.right != null) {
                        currentNode.right.parent = currentNode.parent;
                    }
                } else if (currentNode.right != null) { // left-most node has a right component
                    currentNode.parent.left = currentNode.right; // so move it into the left-most
                    currentNode.right.parent = currentNode.parent; // node's position
                } else { // left-most node has no children, so get rid of it
                    currentNode.parent.left = null;
                }
            }
            
            // return true;
            return true;
        }
    }
    
    // find the key in the tree, returning its tree node, or null if it is not found
    public BinaryTreeNode search(int key) {
        BinaryTreeNode currentNode = root;
        while (currentNode != null) {
            if (currentNode.data.studentId == key) {
                return currentNode;
            } else {
                if (key < currentNode.data.studentId) {
                    if (currentNode.left == null) {
                        return null;
                    } else {
                        currentNode = currentNode.left;
                    }
                } else {
                    if (currentNode.right == null) {
                        return null;
                    } else {
                        currentNode = currentNode.right;
                    }
                }
            }
        }
        
        return null;
    }
    
    // print the tree
    public void print() {
        if (root == null) System.out.println("There are no nodes in the tree.");
        
        // build a queue of Students to enable breadth-first printing
        StudentQueue queue = new StudentQueue();
        BinaryTreeNode currentNode = root;
        while (currentNode != null) {
            // if there is a left node, add it to the queue
            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }
            // if there is a right node, add it to the queue
            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
            
            // print the node
            printNode(currentNode);
            
            // end the loop when we're out of students
            if (queue.length() == 0) {
                currentNode = null;
            } else {
                currentNode = queue.next();
            }
        }
    }
    
    // this gives us one common method to format the student output however we wish
    private void printNode(BinaryTreeNode node) {
        Student student = node.data;
        student.print();
    }
}
