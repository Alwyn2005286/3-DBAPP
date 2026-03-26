public class InspectionRequirement {
    private int requirementCode;
    private String title;
    private double standardFine;
    private String description;
    private int weight;

    public InspectionRequirement(int requirementCode, String title, double standardFine, String description, int weight) {
        this.requirementCode = requirementCode;
        this.title = title;
        this.standardFine = standardFine;
        this.description = description;
        this.weight = weight;
    }

    public int getRequirementCode() { return requirementCode; }
    public String getTitle() { return title; }
    public double getStandardFine() { return standardFine; }
    public String getDescription() { return description; }
    public int getWeight() { return weight; }
}