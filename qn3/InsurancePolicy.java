import java.time.LocalDate;

public abstract class InsurancePolicy {
    protected String policyId;
    protected String vehicleType; // For simplicity, just a string
    protected String vehicleModel;
    protected int vehicleYear;
    protected String policyHolderName;
    protected double coverageAmount;
    protected double premiumAmount;
    protected LocalDate policyStartDate;
    protected LocalDate policyEndDate;

    public InsurancePolicy(String policyId, String vehicleType, String vehicleModel, int vehicleYear, String policyHolderName,
                          double coverageAmount, double premiumAmount, LocalDate policyStartDate, LocalDate policyEndDate) {
        this.policyId = policyId;
        this.vehicleType = vehicleType;
        this.vehicleModel = vehicleModel;
        this.vehicleYear = vehicleYear;
        this.policyHolderName = policyHolderName;
        this.coverageAmount = coverageAmount;
        this.premiumAmount = premiumAmount;
        this.policyStartDate = policyStartDate;
        this.policyEndDate = policyEndDate;
    }

    public abstract void calculatePremium();
    public abstract boolean processClaim(double claimAmount);
    public abstract void generatePolicyReport();
    public abstract boolean validatePolicy();

    public double getPremiumAmount() { return premiumAmount; }
    public double getCoverageAmount() { return coverageAmount; }
    public String getPolicyId() { return policyId; }
    public String getPolicyType() { return this.getClass().getSimpleName(); }
}