import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        Library lib = new Library();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1) List items");
            System.out.println("2) Generate report");
            System.out.println("3) Borrow item by ID");
            System.out.println("4) Return item by ID");
            System.out.println("5) Add sample book");
            System.out.println("0) Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    lib.getAllItems().forEach(System.out::println);
                    break;
                case "2":
                    lib.generateReport();
                    break;
                case "3":
                    System.out.print("Enter item ID to borrow: ");
                    int bid = Integer.parseInt(sc.nextLine().trim());
                    System.out.println(lib.borrowItem(bid) ? "Borrowed successfully." : "Unable to borrow.");
                    break;
                case "4":
                    System.out.print("Enter item ID to return: ");
                    int rid = Integer.parseInt(sc.nextLine().trim());
                    System.out.println(lib.returnItem(rid) ? "Returned successfully." : "Unable to return.");
                    break;
                case "5":
                    lib.addItem(new Book(999 + (int)(Math.random()*1000), "New Sample Book", 2024, "Student", "Sample"));
                    System.out.println("Sample book added.");
                    break;
                case "0":
                    System.out.println("Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
