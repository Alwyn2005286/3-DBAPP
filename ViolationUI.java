import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViolationUI extends JFrame {

    private JTextField txtViolationCode, txtReqCode, txtInspectorRemarks;
    private JComboBox<String> comboRequirementStatus;
    private JTable table;
    private DefaultTableModel model;

    public ViolationUI() {
        setTitle("Violation Management");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== TOP PANEL (FORM) =====
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));

        txtViolationCode = new JTextField();
        txtReqCode = new JTextField();
        txtInspectorRemarks = new JTextField();
        comboRequirementStatus = new JComboBox<>(new String[]{"PASS", "FAIL"});

        formPanel.add(new JLabel("Violation Code:"));
        formPanel.add(txtViolationCode);

        formPanel.add(new JLabel("Requirement Code:"));
        formPanel.add(txtReqCode);

        formPanel.add(new JLabel("Inspector Remarks:"));
        formPanel.add(txtInspectorRemarks);

        formPanel.add(new JLabel("Requirement Status:"));
        formPanel.add(comboRequirementStatus);

        add(formPanel, BorderLayout.NORTH);

        // ===== TABLE =====
        model = new DefaultTableModel();
        table = new JTable(model);

        model.addColumn("Violation Code");
        model.addColumn("Requirement Code");
        model.addColumn("Inspector Remarks");
        model.addColumn("Requirement Status");

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
        btnAdd.addActionListener(e -> addViolation());
        btnUpdate.addActionListener(e -> updateViolation());
        btnDelete.addActionListener(e -> deleteViolation());
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

                txtViolationCode.setText(model.getValueAt(row, 0).toString());
                txtReqCode.setText(model.getValueAt(row, 1).toString());
                txtInspectorRemarks.setText(model.getValueAt(row, 2).toString());
                comboRequirementStatus.setSelectedItem(model.getValueAt(row, 3).toString());
            }
        });
    }

    // ===== LOAD TABLE =====
    private void loadTable() {
        try {
            model.setRowCount(0);

            List<Violation> list = ViolationDAO.getAllViolations();

            for (Violation v : list) {
                model.addRow(new Object[]{
                        v.getViolationCode(),
                        v.getRequirementCode(),
                        v.getInspectorRemarks(),
                        v.getRequirementStatus()
                });
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading violations: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===== ADD =====
    private void addViolation() {
        try {
            Violation v = new Violation(
                    Integer.parseInt(txtViolationCode.getText()),
                    Integer.parseInt(txtReqCode.getText()),
                    txtInspectorRemarks.getText(),
                    (String) comboRequirementStatus.getSelectedItem()
            );

            if (ViolationDAO.addViolation(v)) {
                JOptionPane.showMessageDialog(this, "Violation added successfully!");
                loadTable();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add violation.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding violation: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===== UPDATE =====
    private void updateViolation() {
        try {
            Violation v = new Violation(
                    Integer.parseInt(txtViolationCode.getText()),
                    Integer.parseInt(txtReqCode.getText()),
                    txtInspectorRemarks.getText(),
                    (String) comboRequirementStatus.getSelectedItem()
            );

            if (ViolationDAO.updateViolation(v)) {
                JOptionPane.showMessageDialog(this, "Violation updated successfully!");
                loadTable();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update violation.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating violation: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===== DELETE =====
    private void deleteViolation() {
        try {
            int id = Integer.parseInt(txtViolationCode.getText());
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this violation?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                if (ViolationDAO.deleteViolation(id)) {
                    JOptionPane.showMessageDialog(this, "Violation deleted successfully!");
                    loadTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete violation.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting violation: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===== MAIN METHOD =====
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ViolationUI().setVisible(true);
        });
    }
}