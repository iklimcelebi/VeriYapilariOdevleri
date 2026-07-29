import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Postfix ifadeyi giriniz (örnek: 6_9_*_3_+): ");
        String input = scanner.nextLine(); // kullanıcıdan postfix ifade almak için kullandığımız scanner.

        // GenericStack nesnesinin türünü ve boyutunu belirtiyoruz.
        GenericStack<Integer> stack = new GenericStack<>(Integer.class, 100);

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (Character.isDigit(currentChar)) {
                // Eğer karakter bir sayıysa, onu integer olarak stacke ekle
                stack.push(Character.getNumericValue(currentChar));
            } else if (currentChar != '_') {
                // eger alt tire verilmediyse stackten iki sayı çek ve işlemi yap
                try {
                    int b = stack.pop();
                    int a = stack.pop();

                    switch (currentChar) {
                        case '+':
                            stack.push(a + b);
                            break;
                        case '-':
                            stack.push(a - b);
                            break;
                        case '*':
                            stack.push(a * b);
                            break;
                        case '/':
                            stack.push(a / b);
                            break;
                        default:
                            System.out.println("Bilinmeyen işlem: " + currentChar);
                            return;
                    }
                } catch (Exception e) {
                    System.out.println("Hata: " + e.getMessage());
                    return;
                }
            }
        }

        // Sonuç stackte tek eleman olarak kalacaktır.
        try {
            int result = stack.pop();
            System.out.println("Sonuç: " + result);
        } catch (Exception e) {
            System.out.println("Hata: " + e.getMessage());
        }

        scanner.close(); // Scanner'ı kapatıyoruz
    }
}
