import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        double result = 0;

        while (true) {
            System.out.println("\n--- Calculator ---");
            System.out.println("1. Add");
            System.out.println("2. Subtract");
            System.out.println("3. Multiply");
            System.out.println("4. Divide");
            System.out.println("5. Square Root");
            System.out.println("6. Power");
            System.out.println("7. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();

            if (choice == 7) {
                System.out.println("Exiting...");
                break;
            }

            double a, b;

            System.out.print("Enter first number: ");
            a = sc.nextDouble();

            switch (choice) {

                case 1:
                    System.out.print("Enter second number: ");
                    b = sc.nextDouble();
                    result = a + b;
                    break;

                case 2:
                    System.out.print("Enter second number: ");
                    b = sc.nextDouble();
                    result = a - b;
                    break;

                case 3:
                    System.out.print("Enter second number: ");
                    b = sc.nextDouble();
                    result = a * b;
                    break;

                case 4:
                    System.out.print("Enter second number: ");
                    b = sc.nextDouble();
                    if (b == 0) {
                        System.out.println("Cannot divide by zero!");
                        continue;
                    }
                    result = a / b;
                    break;

                case 5:
                    if (a < 0) {
                        System.out.println("Invalid input!");
                        continue;
                    }
                    result = Math.sqrt(a);
                    break;

                case 6:
                    System.out.print("Enter exponent: ");
                    b = sc.nextDouble();
                    result = Math.pow(a, b);
                    break;

                default:
                    System.out.println("Invalid choice!");
                    continue;
            }


            System.out.println("Result: " + result);
        }

        sc.close();
    }
}
