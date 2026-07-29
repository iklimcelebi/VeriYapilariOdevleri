import java.util.List;

public class StudentTree {
    private StudentNode root;

    // Öğrencileri eklemek için metod
    public void addStudent(Student student) {
        root = addStudentToNode(root, student);
    }

    private StudentNode addStudentToNode(StudentNode node, Student student) {
        if (node == null) {
            return new StudentNode(student);
        }

        if (student.id < node.student.id) {
            node.left = addStudentToNode(node.left, student);
        } else if (student.id > node.student.id) {
            node.right = addStudentToNode(node.right, student);
        }

        return node;
    }

    // eklediğimiz öğrencileri listeye ekleme
    public void addStudentsToList(StudentNode currentNode, List<Student> studentsList) {
        if (currentNode != null) {
            studentsList.add(currentNode.student);  // Öğrenciyi listeye ekliyoruz
            addStudentsToList(currentNode.left, studentsList);  // Sol alt ağacı gez
            addStudentsToList(currentNode.right, studentsList); // Sağ alt ağacı gez
        }
    }

    public StudentNode getRoot() {
        return root;
    }

    // öğrencileri ID'ye göre
    public void printStudentsById() {
        printStudentsById(root);
    }

    private void printStudentsById(StudentNode node) {
        if (node != null) {
            printStudentsById(node.left);
            System.out.println(node.student);
            printStudentsById(node.right);
        }
    }

    // oğrenci sayısını hesaplama
    public int countStudents() {
        return countStudents(root);
    }

    private int countStudents(StudentNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + countStudents(node.left) + countStudents(node.right);
    }

    // ogrenci silme
    public void deleteStudentById(int id) {
        root = deleteStudentById(root, id);
    }

    private StudentNode deleteStudentById(StudentNode node, int id) {
        if (node == null) {
            return null;
        }

        if (id < node.student.id) {
            node.left = deleteStudentById(node.left, id);
        } else if (id > node.student.id) {
            node.right = deleteStudentById(node.right, id);
        } else {
            // Öğrenci bulundu, silme işlemi
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            node.student = findMin(node.right).student;
            node.right = deleteStudentById(node.right, node.student.id);
        }

        return node;
    }

    private StudentNode findMin(StudentNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // oğrencileri genel ortalamaya göre sıralama
    public void displayByAverage() {
        // Implement displayByAverage logic
    }
}
