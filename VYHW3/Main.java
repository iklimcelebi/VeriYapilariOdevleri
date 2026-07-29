import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        StudentTree tree = new StudentTree(); // Öğrencileri saklayacak ağaç
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // ogrencileri dosyadan oku
        loadStudentsFromFile("Student.txt", tree);

        while (running) {
            System.out.println("1. Öğrencileri sıralı listele");
            System.out.println("2. Öğrencileri genel ortalamaya göre sırala");
            System.out.println("3. Numarası verilen bir öğrenciyi sil");
            System.out.println("4. Yeni öğrenci ekle");
            System.out.println("5. Öğrenci sayısını görüntüle");
            System.out.println("6. Belirli bir ders için graf oluştur ve göster");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Satır sonu karakterini temizle

            switch (choice) {
                case 1:
                    tree.printStudentsById();
                    break;
                case 2:
                    tree.displayByAverage();
                    break;
                case 3:
                    System.out.print("Silmek istediğiniz öğrencinin numarasını girin: ");
                    int studentIdToDelete = scanner.nextInt();
                    tree.deleteStudentById(studentIdToDelete);// ıdye göre ogrenciyi silme işlemi
                    break;
                case 4:
                    System.out.print("Yeni öğrencinin numarasını girin: ");
                    int studentId = scanner.nextInt();
                    scanner.nextLine(); // Satır sonu karakterini temizle
                    System.out.print("Yeni öğrencinin adını girin: ");
                    String studentName = scanner.nextLine();
                    Student newStudent = new Student(studentId, studentName);
                    tree.addStudent(newStudent);
                    break;
                case 5:
                    System.out.println("Toplam öğrenci sayısı: " + tree.countStudents());
                    break;
                case 6:
                    createAndDisplayGraph(tree, scanner);
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Geçersiz seçim. Tekrar deneyin.");
            }
        }
        scanner.close();
    }

    private static void loadStudentsFromFile(String filename, StudentTree tree) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);  // dosyadan okunan satırı ekrana yazdırma
                String[] data = line.split(" ");
                int studentId = Integer.parseInt(data[0]);
                String studentName = data[1] + " " + data[2]; // Adı ve soyadı birleşiyor

                // Yeni öğrenci
                Student student = new Student(studentId, studentName);

                // dersleri notları ekliyoruz
                for (int i = 3; i < data.length; i += 2) {
                    String courseName = data[i];
                    int grade = Integer.parseInt(data[i + 1]);
                    student.addCourse(courseName, grade);
                }

                tree.addStudent(student);
            }
            System.out.println("Öğrenciler dosyadan başarıyla yüklendi.");
        } catch (IOException e) {
            System.out.println("Dosya okuma hatası: " + e.getMessage());
        }
    }

    private static void createAndDisplayGraph(StudentTree tree, Scanner scanner) {
        Graph<String> graph = new Graph<>();
        List<Student> students = new LinkedList<>();

        // Öğrencileri listeye eklemek için kök düğümü kullanıyoruz.
        tree.addStudentsToList(tree.getRoot(), students); // root ile çağrıyoruz.

        System.out.print("Graf oluşturulacak dersin adını girin: ");
        String courseName = scanner.nextLine();

        for (Student student : students) {
            graph.addVertex(student.name);
        }
        for (Student student1 : students) {
            for (Student student2 : students) {
                if (!student1.equals(student2) && hasCourse(student1, courseName) && hasCourse(student2, courseName)) {
                    try {
                        if (!graph.hasEdge(student1.name, student2.name)) {
                            graph.addEdge(student1.name, student2.name);
                            graph.addEdge(student2.name, student1.name); // Çift yönlü kenar
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        System.out.println("Graf yapısı:");
        graph.display();
    }

    private static boolean hasCourse(Student student, String courseName) {
        CourseNode current = student.courses;
        while (current != null) {
            if (current.courseName.equals(courseName)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
}
