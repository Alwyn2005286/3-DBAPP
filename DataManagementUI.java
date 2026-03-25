import javax.swing.*;

public class DataManagementUI extends JFrame {

    public DataManagementUI() {
        setTitle("Data Management");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Closes this window, but keeps the main app running

        // Create the Tabbed Pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Add each of your converted JPanels as a new tab
        tabbedPane.addTab("Cities", new CityUI());
        tabbedPane.addTab("Owners", new EstablishmentOwnerUI());
        tabbedPane.addTab("Establishments", new EstablishmentUI());
        tabbedPane.addTab("Inspectors", new InspectorUI());
        tabbedPane.addTab("Assignments", new AssignedInspectorUI());
        tabbedPane.addTab("Requirements", new InspectionRequirementUI());
        tabbedPane.addTab("Violations", new ViolationUI());
        tabbedPane.addTab("Inspections", new InspectionUI());

        // Add the tabbed pane to the frame
        add(tabbedPane);
    }
}