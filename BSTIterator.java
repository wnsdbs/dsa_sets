import java.lang.Iterable;
import java.util.Iterator;

class BSTIterator implements Iterator<Student> {
       private BinaryTreeNode nextNode;
       
       public BSTIterator(BinaryTreeNode startNode) {
          nextNode = startNode;
       }
       
       public boolean hasNext() {
          return nextNode != null;
       }
       
       public Student next() {
           Student returnMe = null;
          if (nextNode != null) {
             returnMe = nextNode.data;
             nextNode = nextNode.getSuccessor();
          }
          return returnMe;
       }
    }

public class Set extends BinarySearchTree implements Iterable<Student> {
    public BSTIterator iterator() {
        // Special case for empty set
        if (root == null) {
            return new BSTIterator(null);
        }

        // Start the iterator at the minimum node
        BinaryTreeNode minNode = root;
        while (minNode.left != null) {
            minNode = minNode.left;
        }
        return new BSTIterator(minNode);
    }

    // add a Student to the set - returns true if it was added, false it it was already in the set
    public boolean add(Student student) {
        // if the specified node is not found
        if (search(student.studentId) == null) { 
            // implementing insert method from the superclass
            super.insert(student);
            return true;
        } else 
            return false;
    }
    
    // removes a Student from the set - returns true if successful, false if the value wasn't in the set
    public boolean remove(int id) {
        // implementing remove method from the superclass
        return super.remove(id);
    }
    
    // searches for a Student Id in the set - returns Student if it was found, null if not
    public BinaryTreeNode search(int id) {
        // implementing search method from the superclass
        BinaryTreeNode node = super.search(id);
        // if the specified node is found
        if (node != null) {
            // return the found node
            return node;
        } // else not found
        // return null
        return null;
    }
    
    // returns true if the Student Id is in the set, false otherwise
    public boolean contains(int id) {
        // if the specified node is found
        return search(id) != null;
    }
    
    // returns the intersection of the current set with a target set
    public Set intersection(Set set) {
        Set newSet = new Set();
        // looping through Student set
        for (Student student : this) {
            // if the set contains the specified node
            if (set.contains(student.studentId)) {
                newSet.add(student);
            }
        }
        return newSet;
    }
    
    // returns the union of the current set with a target set
    public Set union(Set set) {
        Set newSet = new Set();
        for (Student student : this) {
            // adding all students from this set
            newSet.add(student);
        }
        for (Student student : set) {
            // adding all students from the target set
            newSet.add(student);
        }
        return newSet;
    }
    
    /* This method must print in this exact fashion:
     * 
     * All printouts are to be rendered in "set notation".  This means the output begins with an
     * open curly brace.  This is followed by all members of the set.  Each member of the set is
     * followed by a comma, except for the last member.  Then the set is terminated with a closing
     * curly brace.  There is to be one space after each comma.  There may or may not be a space
     * after the opening curly brace, and before the closing curly brace, but it must be consistent;
     * if there is a space after the opening curly brace, there must be a space before the closing
     * curly brace, and vice versa.  The entire set is to be output on one line.  Each member of the
     * set will be represented as (studentId):(name).  The set must print in ascending order of
     * studentId.  An empty set is to be represented by a space inside curly braces.
     * 
     * Examples of valid and invalid printout:
     * 
     * VALID
     * 
     *     { 6:Jack, 16:Denise, 139:Eric }
     *     {6:Jack, 16:Denise, 139:Eric}
     *     { }
     * 
     * INVALID
     * 
     *     {6:Jack, 16:Denise, 139:Eric }    - Space before closing brace does not match no space after opening brace
     *     { 6:Jack, 16:Denise, 139:Eric}    - Space after opening brace does not match no space before closing brace
     *     { 6:Jack, 139:Eric, 16:Denise }    - Set members are not in order by ascending Student Id
     *     {6:Jack 16:Denise 139:Eric}        - Commas aren't separating the set members
     *     [6:Jack, 16:Denise, 139:Eric]    - These are square brackets instead of curly braces
     *     {}                                - There is no space between the curly braces
     */
    public void print() {
        System.out.print("{ ");
        boolean first = true;
        // iterating over the set 
        for (BSTIterator iter = iterator(); iter.hasNext();) {
            Student student = iter.next();
            if (!first) {
                System.out.print(", ");
            } else {
                first = false;
            }
            // printing the studentId and name
            System.out.print(student.studentId + ":" + student.name);
        }
        System.out.println(" }");
    } 
}
