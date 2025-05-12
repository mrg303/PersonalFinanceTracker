import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class FinanceTracker {

    private ArrayList<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }
    public void saveToFile(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (Transaction t : transactions) {
            writer.write(t.toString());
            writer.newLine();
        }
        writer.close();
    }

    public void loadFromFile(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) return;
        transactions = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split(",");
            transactions.add(new Transaction(parts[0], parts[1], Double.parseDouble(parts[2]), parts[3]));
        }
        scanner.close();
    }

    public void listTransactions() {
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

}
