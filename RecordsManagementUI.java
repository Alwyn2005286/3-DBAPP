import javax.swing.*;
import java.awt.*;

public class RecordsManagementUI extends JFrame {

    public RecordsManagementUI() {
        setTitle("Records Management");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnEstablishment = new JButton("Establishment Management");
        JButton btnInspector = new JButton("Inspector Management");
        JButton btnRequirement = new JButton("Inspection Requirement Management");
        JButton btnViolation = new JButton("Violation");
        JButton btnInspection = new JButton("Inspection");
        JButton btnBack = new JButton("Back");

        // Open Inspection Management UI

        btnInspection.addActionListener(e -> {
            new InspectionUI().setVisible(true);
            dispose();
        });

        // Open Violation Management UI

        btnViolation.addActionListener(e -> {
            new ViolationUI().setVisible(true);
            dispose();
        });

        btnEstablishment.addActionListener(e -> {
            new EstablishmentUI().setVisible(true);
            dispose();
        });

        // ===== PLACEHOLDERS =====

        btnInspector.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Inspector Management (not implemented yet)")
        );

        btnRequirement.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Inspection Requirement Management (not implemented yet)")
        );

        // ===== BACK =====

        btnBack.addActionListener(e -> {
            new MainMenuUI().setVisible(true);
            dispose();
        });

        // ===== LAYOUT =====
        setLayout(new GridLayout(6, 1, 10, 10));

        add(btnEstablishment);
        add(btnInspector);
        add(btnRequirement);
        add(btnViolation);
        add(btnInspection);
        add(btnBack);
    }
}
