import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ComplianceTrendReportUI extends JFrame {

    private JTextField yearField;
    private JButton generateButton;
    private JTable reportTable;
    private DefaultTableModel tableModel;

    public ComplianceTrendReportUI() {
        setTitle("Establishment Compliance Trend Report");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Year:"));
        yearField = new JTextField(10);
        topPanel.add(yearField);
        generateButton = new JButton("Generate Report");
        topPanel.add(generateButton);

        tableModel = new DefaultTableModel(new Object[]{"Establishment Name", "Passed Inspections", "Failed Inspections"}, 0);
        reportTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(reportTable);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        generateButton.addActionListener(e -> generateReport());
        
        JButton btnBack = new JButton("Back");
        topPanel.add(btnBack);
        btnBack.addActionListener(e -> {
            new ReportsUI().setVisible(true);
            dispose();
        });
    }

    private void generateReport() {
        try {
            int year = Integer.parseInt(yearField.getText());
            ReportDAO reportDAO = new ReportDAO();
            List<Object[]> reportData = reportDAO.getComplianceTrendData(year);

            tableModel.setRowCount(0); // Clear existing data

            for (Object[] row : reportData) {
                tableModel.addRow(row);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid year.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error generating report: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ComplianceTrendReportUI().setVisible(true));
    }
}
