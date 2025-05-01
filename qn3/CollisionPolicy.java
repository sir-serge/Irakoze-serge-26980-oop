import java.time.LocalDate;

public class CollisionPolicy extends InsurancePolicy {
    private boolean safeDriverDiscount;
    private boolean safetyCheckPassed;

    public CollisionPolicy(String policyId, String vehicleType, String vehicleModel, int vehicleYear, String policyHolderName,
                          double coverageAmount, double premiumAmount, LocalDate policyStartDate, LocalDate policyEndDate,
                          boolean safeDriverDiscount, boolean safetyCheckPassed) {
        super(policyId, vehicleType, vehicleModel, vehicleYear, policyHolderName, coverageAmount, premiumAmount, policyStartDate, policyEndDate);
        this.safeDriverDiscount = safeDriverDiscount;
        this.safetyCheckPassed = safetyCheckPassed;
    }

    @Override
    public void calculatePremium() {
        premiumAmount = coverageAmount * 0.04;
        if (safeDriverDiscount) premiumAmount *= 0.9; // 10% discount
    }

    @Override
    public boolean processClaim(double claimAmount) {
        if (claimAmount > coverageAmount) {
            System.out.println("Claim exceeds coverage amount!");
            return false;
        }
        System.out.println("Collision claim processed for $" + claimAmount);
        return true;
    }

    @Override
    public void generatePolicyReport() {
        System.out.println("Collision Policy [" + policyId + "] for " + policyHolderName +
                " | Vehicle: " + vehicleType + " " + vehicleModel + " (" + vehicleYear + ")" +
                " | Coverage: $" + coverageAmount + " | Premium: $" + premiumAmount +
                (safeDriverDiscount ? " | Safe Driver Discount" : "") +
                (safetyCheckPassed ? " | Safety Check Passed" : " | Safety Check Required"));
    }

    @Override
    public boolean validatePolicy() {
        return safetyCheckPassed;
    }
}