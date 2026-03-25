import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class InspectionRequirementUI extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private InspectionRequirementDAO dao;
    private JTextField codeField, titleField, fineField, descField, weightField;

    public InspectionRequirementUI() {
        dao = new InspectionRequirementDAO();
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 4, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Requirement Details"));

        inputPanel.add(new JLabel("Req Code:"));
        codeField = new JTextField();
        inputPanel.add(codeField);

        inputPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("Standard Fine:"));
        fineField = new JTextField();
        inputPanel.add(fineField);

        inputPanel.add(new JLabel("Description:"));
        descField = new JTextField();
        inputPanel.add(descField);

        inputPanel.add(new JLabel("Weight:"));
        weightField = new JTextField();
        inputPanel.add(weightField);
        
        inputPanel.add(new JLabel(""));
        inputPanel.add(new JLabel(""));

        add(inputPanel, BorderLayout.NORTH);

        String[] columnNames = {"Req Code", "Title", "Standard Fine", "Description", "Weight"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        JButton addButton = new JButton("Save Requirement");
        JButton refreshButton = new JButton("Refresh Data");
        
        addButton.addActionListener(e -> saveRequirement());
        refreshButton.addActionListener(e -> loadTableData());
        
        controlPanel.add(addButton);
        controlPanel.add(refreshButton);
        add(controlPanel, BorderLayout.SOUTH);

        loadTableData();
    }

    private void saveRequirement() {
        try {
            int code = Integer.parseInt(codeField.getText().trim());
            String title = titleField.getText().trim();
            double fine = Double.parseDouble(fineField.getText().trim());
            String desc = descField.getText().trim();
            int weight = Integer.parseInt(weightField.getText().trim());

            InspectionRequirement newReq = new InspectionRequirement(code, title, fine, desc, weight);
            dao.addRequirement(newReq);

            loadTableData();
            codeField.setText("");
            titleField.setText("");
            fineField.setText("");
            descField.setText("");
            weightField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please ensure Code, Fine, and Weight are valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadTableData() {
        tableModel.setRowCount(0); 
        List<InspectionRequirement> requirements = dao.getAllRequirements();
        for (InspectionRequirement req : requirements) {
            Object[] row = { req.getRequirementCode(), req.getTitle(), req.getStandardFine(), req.getDescription(), req.getWeight() };
            tableModel.addRow(row);
        }
    }
}