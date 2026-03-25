import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EstablishmentOwnerUI extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private EstablishmentOwnerDAO dao;
    private JTextField idField, firstNameField, lastNameField;

    public EstablishmentOwnerUI() {
        dao = new EstablishmentOwnerDAO();
        setLayout(new BorderLayout());

        // --- TOP PANEL: Input Form ---
        JPanel inputPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Owner Details"));

        inputPanel.add(new JLabel("Owner ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        inputPanel.add(firstNameField);

        inputPanel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        inputPanel.add(lastNameField);

        add(inputPanel, BorderLayout.NORTH);

        // --- CENTER PANEL: Table ---
        String[] columnNames = {"Owner ID", "First Name", "Last Name"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // --- BOTTOM PANEL: Controls ---
        JPanel controlPanel = new JPanel();
        JButton addButton = new JButton("Save Owner");
        JButton refreshButton = new JButton("Refresh Data");
        
        addButton.addActionListener(e -> saveOwner());
        refreshButton.addActionListener(e -> loadTableData());
        
        controlPanel.add(addButton);
        controlPanel.add(refreshButton);
        add(controlPanel, BorderLayout.SOUTH);

        loadTableData();
    }

    private void saveOwner() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();

            EstablishmentOwner newOwner = new EstablishmentOwner(id, firstName, lastName);
            dao.addOwner(newOwner);

            loadTableData();
            idField.setText("");
            firstNameField.setText("");
            lastNameField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric Owner ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadTableData() {
        tableModel.setRowCount(0); 
        List<EstablishmentOwner> owners = dao.getAllOwners();
        for (EstablishmentOwner owner : owners) {
            Object[] row = { owner.getOwnerId(), owner.getFirstName(), owner.getLastName() };
            tableModel.addRow(row);
        }
    }
}