import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class InspectorUI extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private InspectorDAO dao;
    private JTextField idField, firstNameField, lastNameField;

    public InspectorUI() {
        dao = new InspectorDAO();
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Inspector Details"));

        inputPanel.add(new JLabel("Inspector ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        inputPanel.add(firstNameField);

        inputPanel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        inputPanel.add(lastNameField);

        add(inputPanel, BorderLayout.NORTH);

        String[] columnNames = {"Inspector ID", "First Name", "Last Name"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        JButton addButton = new JButton("Save Inspector");
        JButton refreshButton = new JButton("Refresh Data");
        
        addButton.addActionListener(e -> saveInspector());
        refreshButton.addActionListener(e -> loadTableData());
        
        controlPanel.add(addButton);
        controlPanel.add(refreshButton);
        add(controlPanel, BorderLayout.SOUTH);

        loadTableData();
    }

    private void saveInspector() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();

            Inspector newInspector = new Inspector(id, firstName, lastName);
            dao.addInspector(newInspector);

            loadTableData();
            idField.setText("");
            firstNameField.setText("");
            lastNameField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadTableData() {
        tableModel.setRowCount(0); 
        List<Inspector> inspectors = dao.getAllInspectors();
        for (Inspector inspector : inspectors) {
            Object[] row = { inspector.getInspectorId(), inspector.getFirstName(), inspector.getLastName() };
            tableModel.addRow(row);
        }
    }
}