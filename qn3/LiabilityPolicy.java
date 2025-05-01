import java.time.LocalDate;

public class LiabilityPolicy extends InsurancePolicy {
    private boolean medicalCheckup;
    private boolean extendedDisabilityCoverage;

    public LiabilityPolicy(String policyId, String vehicleType, String vehicleModel, int vehicleYear, String policyHolderName,
                          double coverageAmount, double premiumAmount, LocalDate policyStartDate, LocalDate policyEndDate,
                          boolean medicalCheckup, boolean extendedDisabilityCoverage) {
        super(policyId, vehicleType, vehicleModel, vehicleYear, policyHolderName, coverageAmount, premiumAmount, policyStartDate, policyEndDate);
        this.medicalCheckup = medicalCheckup;
        this.extendedDisabilityCoverage = extendedDisabilityCoverage;
    }

    @Override
    public void calculatePremium() {
        premiumAmount = 120;
        if (extendedDisabilityCoverage) premiumAmount += 50;
    }

    @Override
    public boolean processClaim(double claimAmount) {
        if (claimAmount > coverageAmount) {
            System.out.println("Claim exceeds coverage amount!");
            return false;
        }
        System.out.println("Liability claim processed for $" + claimAmount);
        return true;
    }

    @Override
    public void generatePolicyReport() {
        System.out.println("Liability Policy [" + policyId + "] for " + policyHolderName +
                " | Vehicle: " + vehicleType + " " + vehicleModel + " (" + vehicleYear + ")" +
                " | Coverage: $" + coverageAmount + " | Premium: $" + premiumAmount +
                (medicalCheckup ? " | Medical Checkup Done" : " | Medical Checkup Required") +
                (extendedDisabilityCoverage ? " | Extended Disability Coverage" : ""));
    }

    @Override
    public boolean validatePolicy() {
        return medicalCheckup;
    }
}