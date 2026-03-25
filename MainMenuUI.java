import java.awt.*;
import javax.swing.*;

public class MainMenuUI extends JFrame {

    public MainMenuUI() {
        setTitle("Main Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnRecords = new JButton("Records Management");
        JButton btnTransactions = new JButton("Transactions");
        JButton btnInspectors = new JButton("Inspector Management");
        JButton btnEstablishments = new JButton("Establishment Management");
        JButton btnReports = new JButton("Reports");

        // Open Records Management
        btnRecords.addActionListener(e -> {
            new RecordsManagementUI().setVisible(true);
            dispose();
        });

        // Open Transactions UI
        btnTransactions.addActionListener(e -> {
            new TransactionsUI().setVisible(true);
            dispose();
        });

        // Open Inspector Management
        btnInspectors.addActionListener(e -> {
            new InspectorUI().setVisible(true);
        });

        // Open Establishment Management
        btnEstablishments.addActionListener(e -> {
            new EstablishmentUI().setVisible(true);
        });

        // Placeholder: Reports
        btnReports.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Opened Reports");
        });

        setLayout(new GridLayout(5, 1, 10, 10));

        add(btnRecords);
        add(btnTransactions);
        add(btnInspectors);
        add(btnEstablishments);
        add(btnReports);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainMenuUI().setVisible(true);
        });
    }
}