
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class FinanceTrackerGUI {
    private static FinanceTracker tracker = new FinanceTracker();
    private static final String filename = "transactions.txt";

    public static void main(String[] args) {
        try {
            tracker.loadFromFile(filename);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not load existing transactions.");
        }

        JFrame frame = new JFrame("Finance Tracker");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));

        JTextField dateField = new JTextField();
        JTextField categoryField = new JTextField();
        JTextField amountField = new JTextField();
        JTextField descriptionField = new JTextField();

        inputPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        inputPanel.add(dateField);
        inputPanel.add(new JLabel("Category:"));
        inputPanel.add(categoryField);
        inputPanel.add(new JLabel("Amount:"));
        inputPanel.add(amountField);
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(descriptionField);

        JButton addButton = new JButton("Add Transaction");
        inputPanel.add(addButton);

        JButton saveButton = new JButton("Save & Exit");
        inputPanel.add(saveButton);

        // Output area
        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Display existing transactions
        for (Transaction t : tracker.getTransactions()) {
            outputArea.append(t.toString() + "\n");
        }

        // Add functionality to Add button
        addButton.addActionListener(e -> {
            try {
                String date = dateField.getText();
                String category = categoryField.getText();
                double amount = Double.parseDouble(amountField.getText());
                String description = descriptionField.getText();

                Transaction t = new Transaction(date, category, amount, description);
                tracker.addTransaction(t);
                outputArea.append(t.toString() + "\n");

                // Clear inputs
                dateField.setText("");
                categoryField.setText("");
                amountField.setText("");
                descriptionField.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please check all fields.");
            }
        });

        // Save and exit functionality
        saveButton.addActionListener(e -> {
            try {
                tracker.saveToFile(filename);
                System.exit(0);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Failed to save transactions.");
            }
        });

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
