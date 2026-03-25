import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RecordsManagementUI extends JFrame {

    public RecordsManagementUI() {
        setTitle("Records Management (View Only)");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.add("Inspections", createInspectionPanel());
        tabbedPane.add("Violations", createViolationPanel());

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> {
            new MainMenuUI().setVisible(true);
            dispose();
        });

        add(tabbedPane, BorderLayout.CENTER);
        add(btnBack, BorderLayout.SOUTH);
    }

    // ================= INSPECTIONS TABLE =================
    private JPanel createInspectionPanel() {
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);

        model.addColumn("Inspection ID");
        model.addColumn("Violation Code");
        model.addColumn("Assignment ID");
        model.addColumn("Date");
        model.addColumn("Remarks");
        model.addColumn("Score");
        model.addColumn("Grade");

        try {
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
        }

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    // ================= VIOLATIONS TABLE =================
    private JPanel createViolationPanel() {
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);

        model.addColumn("Violation Code");
        model.addColumn("Requirement Code");
        model.addColumn("Inspector Remarks");
        model.addColumn("Requirement Status");

        try {
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
        }

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }
}