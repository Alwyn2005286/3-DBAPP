import javax.swing.*;
import java.awt.*;

public class MainMenuUI extends JFrame {

    public MainMenuUI() {
        setTitle("Establishment Inspection Database System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Kills the app when closed
        setLayout(new BorderLayout());

        // Create a central panel to hold our main buttons nicely
        JPanel centerPanel = new JPanel(new GridBagLayout());
        
        JButton btnDataManagement = new JButton("Open Data Management");
        btnDataManagement.setPreferredSize(new Dimension(250, 50));
        btnDataManagement.setFont(new Font("Arial", Font.BOLD, 14));

        // Launch the unified tabbed window
        btnDataManagement.addActionListener(e -> new DataManagementUI().setVisible(true));

        centerPanel.add(btnDataManagement);
        add(centerPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainMenuUI().setVisible(true);
        });
    }
}