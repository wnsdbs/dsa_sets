public class MainClass {
    private static final int DATA_SIZE = 10;
    
    public static void main(String[] args) {
        Set set = new Set();
        
        Student[] data = new Student[DATA_SIZE];
        data[0] = new Student("Alice", "Freshman", 284);
        data[1] = new Student("Bob", "Senior", 606);
        data[2] = new Student("Carol", "Junior", 821);
        data[3] = new Student("Denise", "Junior", 16);
        data[4] = new Student("Eric", "Freshman", 139);
        data[5] = new Student("Frank", "Sophomore", 992);
        data[6] = new Student("Gail", "Junior", 363);
        data[7] = new Student("Henry", "Senior", 749);
        data[8] = new Student("Ingrid", "Junior", 802);
        data[9] = new Student("Jack", "Sophomore", 6);
        
        // Add students to the set
        for (Student student : data) {
            boolean added = set.add(student);
            if (added) {
                System.out.println("Added: " + student.name);
            } else {
                System.out.println("Failed to add: " + student.name);
            }
        }
        
        // Print the set
        System.out.print("Set: ");
        set.print();
        
        // Remove a student
        int idToRemove = 284;
        boolean removed = set.remove(idToRemove);
        if (removed) {
            System.out.println("Removed student with ID " + idToRemove);
        } else {
            System.out.println("Failed to remove student with ID " + idToRemove);
        }
        
        // Print the set after removal
        System.out.print("Set after removal: ");
        set.print();
        
        // Search for a student
        int id = 821;
        BinaryTreeNode foundStudent = set.search(id);
        if (foundStudent != null) {
            System.out.println("Found student with ID " + id + ": " + foundStudent.data.name);
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
        
        // Test intersection with another set
        Set otherSet = new Set();
        otherSet.add(new Student("David", "Freshman", 284));
        otherSet.add(new Student("Eve", "Senior", 749));
        otherSet.add(new Student("Fiona", "Sophomore", 363));
        Set intersection = set.intersection(otherSet);
        System.out.print("Intersection: ");
        intersection.print();
        
        // Test union with another set
        Set union = set.union(otherSet);
        System.out.print("Union: ");
        union.print();
    }
}
