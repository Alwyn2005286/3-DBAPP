import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class InspectionUI extends JFrame {

    private JTextField txtInspectionId, txtViolationCode, txtAssignmentId, txtDate, txtRemarks, txtScore;
    private JComboBox<String> comboGrade;
    private JTable table;
    private DefaultTableModel model;

    public InspectionUI() {
        setTitle("Inspection Management");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== TOP PANEL (FORM) =====
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 5, 5));

        txtInspectionId = new JTextField();
        txtViolationCode = new JTextField();
        txtAssignmentId = new JTextField();
        txtDate = new JTextField("YYYY-MM-DD");
        txtRemarks = new JTextField();
        txtScore = new JTextField();
        comboGrade = new JComboBox<>(new String[]{"PASS", "FAIL"});

        formPanel.add(new JLabel("Inspection ID:"));
        formPanel.add(txtInspectionId);

        formPanel.add(new JLabel("Violation Code:"));
        formPanel.add(txtViolationCode);

        formPanel.add(new JLabel("Assignment ID:"));
        formPanel.add(txtAssignmentId);

        formPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        formPanel.add(txtDate);

        formPanel.add(new JLabel("Remarks:"));
        formPanel.add(txtRemarks);

        formPanel.add(new JLabel("Score:"));
        formPanel.add(txtScore);

        formPanel.add(new JLabel("Grade:"));
        formPanel.add(comboGrade);

        add(formPanel, BorderLayout.NORTH);

        // ===== TABLE =====
        model = new DefaultTableModel();
        table = new JTable(model);

        model.addColumn("Inspection ID");
        model.addColumn("Violation Code");
        model.addColumn("Assignment ID");
        model.addColumn("Date");
        model.addColumn("Remarks");
        model.addColumn("Score");
        model.addColumn("Grade");

        add(new JScrollPane(table), BorderLayout.CENTER);

        // ===== BUTTON PANEL =====
        JPanel buttonPanel = new JPanel();

        JButton btnAdd = new JButton("Add");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnRefresh = new JButton("Refresh");
        JButton btnBack = new JButton("Back");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnBack);

        add(buttonPanel, BorderLayout.SOUTH);

        // ===== BUTTON ACTIONS =====
        btnAdd.addActionListener(e -> addInspection());
        btnUpdate.addActionListener(e -> updateInspection());
        btnDelete.addActionListener(e -> deleteInspection());
        btnRefresh.addActionListener(e -> loadTable());
        btnBack.addActionListener(e -> {
            this.dispose();
            new TransactionsUI().setVisible(true);
        });

        // Load data initially
        loadTable();

        // Table row click → populate fields
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();

                txtInspectionId.setText(model.getValueAt(row, 0).toString());
                txtViolationCode.setText(model.getValueAt(row, 1).toString());
                txtAssignmentId.setText(model.getValueAt(row, 2).toString());
                txtDate.setText(model.getValueAt(row, 3).toString());
                txtRemarks.setText(model.getValueAt(row, 4).toString());
                txtScore.setText(model.getValueAt(row, 5).toString());
                comboGrade.setSelectedItem(model.getValueAt(row, 6).toString());
            }
        });
    }

    // ===== LOAD TABLE =====
    private void loadTable() {
        try {
            model.setRowCount(0);
            List<Inspection> list = InspectionDAO.getAllInspections();

            for (Inspection i : list) {
                model.addRow(new Object[]{
                        i.getInspectionId(),
                        i.getViolationCode(),
                        i.getAssignmentId(),
                        i.getInspectionDate(),
                        i.getRemarks(),
                        i.getScore(),
                        i.getGrade()
                });
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading inspections: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===== ADD =====
    private void addInspection() {
        try {
            Inspection i = new Inspection(
                    Integer.parseInt(txtInspectionId.getText()),
                    Integer.parseInt(txtViolationCode.getText()),
                    Integer.parseInt(txtAssignmentId.getText()),
                    LocalDate.parse(txtDate.getText()),
                    txtRemarks.getText(),
                    new BigDecimal(txtScore.getText()),
                    (String) comboGrade.getSelectedItem()
            );

            if (InspectionDAO.addInspection(i)) {
                JOptionPane.showMessageDialog(this, "Inspection added successfully!");
                loadTable();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add inspection.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding inspection: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===== UPDATE =====
    private void updateInspection() {
        try {
            Inspection i = new Inspection(
                    Integer.parseInt(txtInspectionId.getText()),
                    Integer.parseInt(txtViolationCode.getText()),
                    Integer.parseInt(txtAssignmentId.getText()),
                    LocalDate.parse(txtDate.getText()),
                    txtRemarks.getText(),
                    new BigDecimal(txtScore.getText()),
                    (String) comboGrade.getSelectedItem()
            );

            if (InspectionDAO.updateInspection(i)) {
                JOptionPane.showMessageDialog(this, "Inspection updated successfully!");
                loadTable();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update inspection.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating inspection: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===== DELETE =====
    private void deleteInspection() {
        try {
            int id = Integer.parseInt(txtInspectionId.getText());
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this inspection?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                if (InspectionDAO.deleteInspection(id)) {
                    JOptionPane.showMessageDialog(this, "Inspection deleted successfully!");
                    loadTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete inspection.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting inspection: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===== MAIN METHOD =====
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InspectionUI().setVisible(true);
        });
    }
}