import java.time.LocalDate;

public class RoadsideAssistancePolicy extends InsurancePolicy {
    private boolean isCommercial;
    private boolean registrationVerified;
    private boolean inspectionVerified;

    public RoadsideAssistancePolicy(String policyId, String vehicleType, String vehicleModel, int vehicleYear, String policyHolderName,
                                   double coverageAmount, double premiumAmount, LocalDate policyStartDate, LocalDate policyEndDate,
                                   boolean isCommercial, boolean registrationVerified, boolean inspectionVerified) {
        super(policyId, vehicleType, vehicleModel, vehicleYear, policyHolderName, coverageAmount, premiumAmount, policyStartDate, policyEndDate);
        this.isCommercial = isCommercial;
        this.registrationVerified = registrationVerified;
        this.inspectionVerified = inspectionVerified;
    }

    @Override
    public void calculatePremium() {
        premiumAmount = 80;
        if (isCommercial) premiumAmount += 40;
    }

    @Override
    public boolean processClaim(double claimAmount) {
        if (claimAmount > coverageAmount) {
            System.out.println("Claim exceeds coverage amount!");
            return false;
        }
        System.out.println("Roadside Assistance claim processed for $" + claimAmount);
        return true;
    }

    @Override
    public void generatePolicyReport() {
        System.out.println("Roadside Assistance Policy [" + policyId + "] for " + policyHolderName +
                " | Vehicle: " + vehicleType + " " + vehicleModel + " (" + vehicleYear + ")" +
                " | Coverage: $" + coverageAmount + " | Premium: $" + premiumAmount +
                (isCommercial ? " | Commercial" : " | Private") +
                (registrationVerified ? " | Registration Verified" : " | Registration Not Verified") +
                (inspectionVerified ? " | Inspection Verified" : " | Inspection Not Verified"));
    }

    @Override
    public boolean validatePolicy() {
        return registrationVerified && inspectionVerified;
    }
}