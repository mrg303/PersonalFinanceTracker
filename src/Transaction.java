public class Transaction {
    // Represents a single transaction
    private String date;
    private String category;
    private double amount;
    private String description;

    public Transaction(String date, String category, double amount, String description) {
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    public String toString() {
        return date + "," + category + "," + amount + "," + description;
    }
}
