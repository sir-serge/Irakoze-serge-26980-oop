import java.time.LocalDate;

public class ComprehensivePolicy extends InsurancePolicy {
    public ComprehensivePolicy(String policyId, String vehicleType, String vehicleModel, int vehicleYear, String policyHolderName,
                              double coverageAmount, double premiumAmount, LocalDate policyStartDate, LocalDate policyEndDate) {
        super(policyId, vehicleType, vehicleModel, vehicleYear, policyHolderName, coverageAmount, premiumAmount, policyStartDate, policyEndDate);
    }

    @Override
    public void calculatePremium() {
        int age = LocalDate.now().getYear() - vehicleYear;
        premiumAmount = coverageAmount * 0.05 + (age * 10); // Example: 5% of coverage + $10 per year old
    }

    @Override
    public boolean processClaim(double claimAmount) {
        if (claimAmount > coverageAmount) {
            System.out.println("Claim exceeds coverage amount!");
            return false;
        }
        System.out.println("Comprehensive claim processed for $" + claimAmount);
        return true;
    }

    @Override
    public void generatePolicyReport() {
        System.out.println("Comprehensive Policy [" + policyId + "] for " + policyHolderName +
                " | Vehicle: " + vehicleType + " " + vehicleModel + " (" + vehicleYear + ")" +
                " | Coverage: $" + coverageAmount + " | Premium: $" + premiumAmount);
    }

    @Override
    public boolean validatePolicy() {
        return vehicleYear > 2000 && (vehicleType.equalsIgnoreCase("Car") || vehicleType.equalsIgnoreCase("SUV"));
    }
}