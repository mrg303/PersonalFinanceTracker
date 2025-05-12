import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        FinanceTracker tracker = new FinanceTracker();
        Scanner input = new Scanner(System.in);
        String filename = "transactions.txt";
        tracker.loadFromFile(filename);

        while (true) {
            System.out.println("1. Add Transaction\n2. View All\n3. Save & Exit");
            String choice = input.nextLine();

            if (choice.equals("1")) {
                System.out.print("Date: ");
                String date = input.nextLine();
                System.out.print("Category: ");
                String category = input.nextLine();
                System.out.print("Amount: ");
                double amount = Double.parseDouble(input.nextLine());
                System.out.print("Description: ");
                String desc = input.nextLine();
                tracker.addTransaction(new Transaction(date, category, amount, desc));
            } else if (choice.equals("2")) {
                tracker.listTransactions();
            } else if (choice.equals("3")) {
                tracker.saveToFile(filename);
                System.out.println("Saved and exiting...");
                break;
            }
        }
        input.close();
    }

}