public class CourseNode {
    String courseName;
    int grade;
    CourseNode next;

    public CourseNode(String courseName, int grade) {
        this.courseName = courseName;
        this.grade = grade;
        this.next = null;
    }
}
