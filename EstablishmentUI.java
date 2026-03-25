import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class EstablishmentUI extends JFrame {

    private JTextField txtEstablishmentId, txtOwnerId, txtEstablishmentName, txtCityName;
    private JTable table;
    private DefaultTableModel model;

    public EstablishmentUI() {
        setTitle("Establishment Management");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== TOP PANEL (FORM) =====
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));

        txtEstablishmentId = new JTextField();
        txtOwnerId = new JTextField();
        txtEstablishmentName = new JTextField();
        txtCityName = new JTextField();

        formPanel.add(new JLabel("Establishment ID:"));
        formPanel.add(txtEstablishmentId);

        formPanel.add(new JLabel("Owner ID:"));
        formPanel.add(txtOwnerId);

        formPanel.add(new JLabel("Establishment Name:"));
        formPanel.add(txtEstablishmentName);

        formPanel.add(new JLabel("City Name:"));
        formPanel.add(txtCityName);

        add(formPanel, BorderLayout.NORTH);

        // ===== TABLE =====
        model = new DefaultTableModel();
        table = new JTable(model);

        model.addColumn("Establishment ID");
        model.addColumn("Owner ID");
        model.addColumn("Establishment Name");
        model.addColumn("City Name");

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
        btnAdd.addActionListener(e -> addEstablishment());
        btnUpdate.addActionListener(e -> updateEstablishment());
        btnDelete.addActionListener(e -> deleteEstablishment());
        btnRefresh.addActionListener(e -> loadTable());
        btnBack.addActionListener(e -> this.dispose());

        // Load data initially
        loadTable();

        // Table row click → populate fields
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();

                txtEstablishmentId.setText(model.getValueAt(row, 0).toString());
                txtOwnerId.setText(model.getValueAt(row, 1).toString());
                txtEstablishmentName.setText(model.getValueAt(row, 2).toString());
                txtCityName.setText(model.getValueAt(row, 3).toString());
            }
        });
    }

    // ===== LOAD TABLE =====
    private void loadTable() {
        try {
            model.setRowCount(0);
            List<Establishment> list = EstablishmentDAO.getAllEstablishments();

            for (Establishment est : list) {
                model.addRow(new Object[]{
                        est.getEstablishmentId(),
                        est.getOwnerId(),
                        est.getEstablishmentName(),
                        est.getCityName()
                });
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading establishments: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===== ADD =====
    private void addEstablishment() {
        try {
            Establishment est = new Establishment(
                    Integer.parseInt(txtEstablishmentId.getText()),
                    Integer.parseInt(txtOwnerId.getText()),
                    txtEstablishmentName.getText(),
                    txtCityName.getText()
            );

            if (EstablishmentDAO.addEstablishment(est)) {
                JOptionPane.showMessageDialog(this, "Establishment added successfully!");
                loadTable();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add establishment.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding establishment: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===== UPDATE =====
    private void updateEstablishment() {
        try {
            Establishment est = new Establishment(
                    Integer.parseInt(txtEstablishmentId.getText()),
                    Integer.parseInt(txtOwnerId.getText()),
                    txtEstablishmentName.getText(),
                    txtCityName.getText()
            );

            if (EstablishmentDAO.updateEstablishment(est)) {
                JOptionPane.showMessageDialog(this, "Establishment updated successfully!");
                loadTable();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update establishment.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating establishment: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===== DELETE =====
    private void deleteEstablishment() {
        try {
            int id = Integer.parseInt(txtEstablishmentId.getText());
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this establishment?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                if (EstablishmentDAO.deleteEstablishment(id)) {
                    JOptionPane.showMessageDialog(this, "Establishment deleted successfully!");
                    loadTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete establishment.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting establishment: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===== MAIN METHOD =====
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EstablishmentUI().setVisible(true);
        });
    }
}
