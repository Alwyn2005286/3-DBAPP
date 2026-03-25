import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CityUI extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private CityDAO dao;
    private JTextField nameField;

    public CityUI() {
        dao = new CityDAO();
        setLayout(new BorderLayout());

        // --- TOP PANEL: Input Form ---
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("City Details"));

        inputPanel.add(new JLabel("City Name:"));
        nameField = new JTextField(15);
        inputPanel.add(nameField);

        add(inputPanel, BorderLayout.NORTH);

        // --- CENTER PANEL: Table ---
        String[] columnNames = {"City Name"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // --- BOTTOM PANEL: Controls ---
        JPanel controlPanel = new JPanel();
        JButton addButton = new JButton("Save City");
        JButton refreshButton = new JButton("Refresh Data");
        
        addButton.addActionListener(e -> saveCity());
        refreshButton.addActionListener(e -> loadTableData());
        
        controlPanel.add(addButton);
        controlPanel.add(refreshButton);
        add(controlPanel, BorderLayout.SOUTH);

        loadTableData();
    }

    private void saveCity() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "City name cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        City newCity = new City(name);
        dao.addCity(newCity);
        loadTableData();
        nameField.setText("");
    }

    private void loadTableData() {
        tableModel.setRowCount(0); 
        List<City> cities = dao.getAllCities();
        for (City city : cities) {
            Object[] row = { city.getCityName() };
            tableModel.addRow(row);
        }
    }
}