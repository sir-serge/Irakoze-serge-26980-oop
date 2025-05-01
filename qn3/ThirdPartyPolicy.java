import java.time.LocalDate;

public class ThirdPartyPolicy extends InsurancePolicy {
    private double engineCapacity; // in liters
    private boolean additionalCoverage;

    public ThirdPartyPolicy(String policyId, String vehicleType, String vehicleModel, int vehicleYear, String policyHolderName,
                           double coverageAmount, double premiumAmount, LocalDate policyStartDate, LocalDate policyEndDate,
                           double engineCapacity, boolean additionalCoverage) {
        super(policyId, vehicleType, vehicleModel, vehicleYear, policyHolderName, coverageAmount, premiumAmount, policyStartDate, policyEndDate);
        this.engineCapacity = engineCapacity;
        this.additionalCoverage = additionalCoverage;
    }

    @Override
    public void calculatePremium() {
        premiumAmount = 100 + (engineCapacity * 50);
        if (additionalCoverage) premiumAmount += 75;
    }

    @Override
    public boolean processClaim(double claimAmount) {
        if (claimAmount > coverageAmount) {
            System.out.println("Claim exceeds coverage amount!");
            return false;
        }
        System.out.println("Third Party claim processed for $" + claimAmount);
        return true;
    }

    @Override
    public void generatePolicyReport() {
        System.out.println("Third Party Policy [" + policyId + "] for " + policyHolderName +
                " | Vehicle: " + vehicleType + " " + vehicleModel + " (" + vehicleYear + ")" +
                " | Engine: " + engineCapacity + "L | Coverage: $" + coverageAmount +
                " | Premium: $" + premiumAmount + (additionalCoverage ? " | Additional Coverage" : ""));
    }

    @Override
    public boolean validatePolicy() {
        return engineCapacity > 0.5;
    }
}