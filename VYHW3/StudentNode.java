public class StudentNode {
    Student student;
    StudentNode left, right;

    public StudentNode(Student student) {
        this.student = student;
        this.left = this.right = null;
    }
}
