import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ViolationUI extends JFrame {

    private JTextField txtId, txtReqCode, txtInspectionId;
    private JTable table;
    private DefaultTableModel model;

    private ViolationDAO dao = new ViolationDAO();

    public ViolationUI() {
        setTitle("Violation Management");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== FORM =====
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));

        txtId = new JTextField();
        txtReqCode = new JTextField();
        txtInspectionId = new JTextField();

        formPanel.add(new JLabel("Violation ID:"));
        formPanel.add(txtId);

        formPanel.add(new JLabel("Requirement Code:"));
        formPanel.add(txtReqCode);

        formPanel.add(new JLabel("Inspection ID:"));
        formPanel.add(txtInspectionId);

        add(formPanel, BorderLayout.NORTH);

        // ===== TABLE =====
        model = new DefaultTableModel();
        table = new JTable(model);

        model.addColumn("Violation ID");
        model.addColumn("Requirement Code");
        model.addColumn("Inspection ID");

        add(new JScrollPane(table), BorderLayout.CENTER);

        // ===== BUTTONS =====
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

        // ===== ACTIONS =====
        btnAdd.addActionListener(e -> addViolation());
        btnUpdate.addActionListener(e -> updateViolation());
        btnDelete.addActionListener(e -> deleteViolation());
        btnRefresh.addActionListener(e -> loadTable());
        btnBack.addActionListener(e -> {
            this.dispose();
            new RecordsManagementUI().setVisible(true);
        });

        // Table click
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();

                txtId.setText(model.getValueAt(row, 0).toString());
                txtReqCode.setText(model.getValueAt(row, 1).toString());
                txtInspectionId.setText(model.getValueAt(row, 2).toString());
            }
        });

        loadTable();
    }

    private void loadTable() {
        try {
            model.setRowCount(0);

            List<Violation> list = dao.getAllViolations();

            for (Violation v : list) {
                model.addRow(new Object[]{
                        v.getViolationId(),
                        v.getRequirementCode(),
                        v.getInspectionId()
                });
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addViolation() {
        try {
            Violation v = new Violation(
                    Integer.parseInt(txtReqCode.getText()),
                    Integer.parseInt(txtInspectionId.getText())
            );

            dao.addViolation(v);
            loadTable();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void updateViolation() {
        try {
            Violation v = new Violation(
                    Integer.parseInt(txtId.getText()),
                    Integer.parseInt(txtReqCode.getText()),
                    Integer.parseInt(txtInspectionId.getText())
            );

            dao.updateViolation(v);
            loadTable();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void deleteViolation() {
        try {
            int id = Integer.parseInt(txtId.getText());
            dao.deleteViolation(id);
            loadTable();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ViolationUI().setVisible(true);
        });
    }
}