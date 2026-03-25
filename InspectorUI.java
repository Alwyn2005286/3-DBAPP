import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class InspectorUI extends JFrame {

    private JTextField txtInspectorId, txtFirstName, txtLastName;
    private JTable table;
    private DefaultTableModel model;

    public InspectorUI() {
        setTitle("Inspector Management");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== TOP PANEL (FORM) =====
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));

        txtInspectorId = new JTextField();
        txtFirstName = new JTextField();
        txtLastName = new JTextField();

        formPanel.add(new JLabel("Inspector ID:"));
        formPanel.add(txtInspectorId);

        formPanel.add(new JLabel("First Name:"));
        formPanel.add(txtFirstName);

        formPanel.add(new JLabel("Last Name:"));
        formPanel.add(txtLastName);

        add(formPanel, BorderLayout.NORTH);

        // ===== TABLE =====
        model = new DefaultTableModel();
        table = new JTable(model);

        model.addColumn("Inspector ID");
        model.addColumn("First Name");
        model.addColumn("Last Name");

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
        btnAdd.addActionListener(e -> addInspector());
        btnUpdate.addActionListener(e -> updateInspector());
        btnDelete.addActionListener(e -> deleteInspector());
        btnRefresh.addActionListener(e -> loadTable());
        btnBack.addActionListener(e -> {
            this.dispose();
            // Assuming you'll have a main management UI to go back to
            // For now, it just closes. You can change this later.
            // new MainMenuUI().setVisible(true); 
        });

        // Load data initially
        loadTable();

        // Table row click → populate fields
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();

                txtInspectorId.setText(model.getValueAt(row, 0).toString());
                txtFirstName.setText(model.getValueAt(row, 1).toString());
                txtLastName.setText(model.getValueAt(row, 2).toString());
            }
        });
    }

    // ===== LOAD TABLE =====
    private void loadTable() {
        try {
            model.setRowCount(0);
            List<Inspector> list = InspectorDAO.getAllInspectors();

            for (Inspector i : list) {
                model.addRow(new Object[]{
                        i.getInspectorId(),
                        i.getFirstName(),
                        i.getLastName()
                });
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading inspectors: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===== ADD =====
    private void addInspector() {
        try {
            Inspector i = new Inspector(
                    Integer.parseInt(txtInspectorId.getText()),
                    txtFirstName.getText(),
                    txtLastName.getText()
            );

            if (InspectorDAO.addInspector(i)) {
                JOptionPane.showMessageDialog(this, "Inspector added successfully!");
                loadTable();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add inspector.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding inspector: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===== UPDATE =====
    private void updateInspector() {
        try {
            Inspector i = new Inspector(
                    Integer.parseInt(txtInspectorId.getText()),
                    txtFirstName.getText(),
                    txtLastName.getText()
            );

            if (InspectorDAO.updateInspector(i)) {
                JOptionPane.showMessageDialog(this, "Inspector updated successfully!");
                loadTable();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update inspector.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating inspector: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===== DELETE =====
    private void deleteInspector() {
        try {
            int id = Integer.parseInt(txtInspectorId.getText());
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this inspector?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                if (InspectorDAO.deleteInspector(id)) {
                    JOptionPane.showMessageDialog(this, "Inspector deleted successfully!");
                    loadTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete inspector.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting inspector: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===== MAIN METHOD =====
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InspectorUI().setVisible(true);
        });
    }
}
