
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class EstablishmentUI extends JFrame {

    private JTextField txtId, txtName, txtOwner, txtAddress, txtContact, txtStatus;
    private JTable table;
    private DefaultTableModel model;

    public EstablishmentUI() {
        setTitle("Establishment Management");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== FORM PANEL =====
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));

        txtId = new JTextField();
        txtName = new JTextField();
        txtOwner = new JTextField();
        txtAddress = new JTextField();
        txtContact = new JTextField();
        txtStatus = new JTextField(); // OPEN, CLOSED, SUSPENDED

        formPanel.add(new JLabel("ID:"));
        formPanel.add(txtId);
        formPanel.add(new JLabel("Name:"));
        formPanel.add(txtName);
        formPanel.add(new JLabel("Owner:"));
        formPanel.add(txtOwner);
        formPanel.add(new JLabel("Address:"));
        formPanel.add(txtAddress);
        formPanel.add(new JLabel("Contact Info:"));
        formPanel.add(txtContact);
        formPanel.add(new JLabel("Status:"));
        formPanel.add(txtStatus);

        add(formPanel, BorderLayout.NORTH);

        // ===== TABLE =====
        model = new DefaultTableModel();
        table = new JTable(model);

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Owner");
        model.addColumn("Address");
        model.addColumn("Contact");
        model.addColumn("Status");

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
        btnBack.addActionListener(e -> { this.dispose(); new RecordsManagementUI().setVisible(true); });

        // Load data initially
        loadTable();

        // Table row click → populate fields
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();

                txtId.setText(model.getValueAt(row, 0).toString());
                txtName.setText(model.getValueAt(row, 1).toString());
                txtOwner.setText(model.getValueAt(row, 2).toString());
                txtAddress.setText(model.getValueAt(row, 3).toString());
                txtContact.setText(model.getValueAt(row, 4).toString());
                txtStatus.setText(model.getValueAt(row, 5).toString());
            }
        });
    }

    // ===== CRUD METHODS =====
    private void loadTable() {
        try {
            model.setRowCount(0);
            List<Establishment> list = EstablishmentDAO.getAllEstablishments();

            for (Establishment e : list) {
                model.addRow(new Object[]{
                        e.getEstablishmentId(),
                        e.getEstablishmentName(),
                        e.getOwnerName(),
                        e.getAddress(),
                        e.getContactInfo(),
                        e.getOperatingStatus()
                });
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addEstablishment() {
        try {
            Establishment e = new Establishment(
                    0,
                    txtName.getText(),
                    txtOwner.getText(),
                    txtAddress.getText(),
                    txtContact.getText(),
                    txtStatus.getText()
            );

            EstablishmentDAO.addEstablishment(e);
            loadTable();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void updateEstablishment() {
        try {
            Establishment e = new Establishment(
                    Integer.parseInt(txtId.getText()),
                    txtName.getText(),
                    txtOwner.getText(),
                    txtAddress.getText(),
                    txtContact.getText(),
                    txtStatus.getText()
            );

            EstablishmentDAO.updateEstablishment(e);
            loadTable();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void deleteEstablishment() {
        try {
            int id = Integer.parseInt(txtId.getText());
            EstablishmentDAO.deleteEstablishment(id);
            loadTable();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ===== MAIN =====
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EstablishmentUI().setVisible(true));
    }
}