public class Student {
    int id;
    String name;
    CourseNode courses; // Derslerin listesi

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.courses = null; // Başlangıçta ders yok
    }

    public void addCourse(String courseName, int grade) {
        CourseNode newCourse = new CourseNode(courseName, grade);
        if (courses == null) {
            courses = newCourse;
        } else {
            CourseNode temp = courses;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newCourse;
        }
    }
}
