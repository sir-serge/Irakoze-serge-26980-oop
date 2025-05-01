import java.util.*;
import java.time.LocalDate;

public class MotorVehicleInsuranceSystem {
    private static List<InsurancePolicy> policies = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Motor Vehicle Insurance System ---");
            System.out.println("1. Add Policy");
            System.out.println("2. Process Claim");
            System.out.println("3. Generate Report");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1: addPolicy(); break;
                case 2: processClaim(); break;
                case 3: generateReport(); break;
                case 4: System.exit(0);
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static void addPolicy() {
        System.out.println("Select Policy Type: 1-Comprehensive 2-ThirdParty 3-Collision 4-Liability 5-RoadsideAssistance");
        int type = sc.nextInt(); sc.nextLine();
        System.out.print("Policy ID: "); String pid = sc.nextLine();
        System.out.print("Policy Holder Name: "); String holder = sc.nextLine();
        System.out.print("Vehicle Type: "); String vtype = sc.nextLine();
        System.out.print("Vehicle Model: "); String vmodel = sc.nextLine();
        System.out.print("Vehicle Year: "); int vyear = sc.nextInt();
        System.out.print("Coverage Amount: "); double coverage = sc.nextDouble();
        System.out.print("Premium Amount: "); double premium = sc.nextDouble();
        sc.nextLine();
        System.out.print("Policy Start Date (YYYY-MM-DD): "); LocalDate start = LocalDate.parse(sc.nextLine());
        System.out.print("Policy End Date (YYYY-MM-DD): "); LocalDate end = LocalDate.parse(sc.nextLine());

        try {
            InsurancePolicy policy = null;
            switch (type) {
                case 1:
                    policy = new ComprehensivePolicy(pid, vtype, vmodel, vyear, holder, coverage, premium, start, end);
                    break;
                case 2:
                    System.out.print("Engine Capacity (L): "); double engine = sc.nextDouble();
                    System.out.print("Additional Coverage (true/false): "); boolean addCov = sc.nextBoolean(); sc.nextLine();
                    policy = new ThirdPartyPolicy(pid, vtype, vmodel, vyear, holder, coverage, premium, start, end, engine, addCov);
                    break;
                case 3:
                    System.out.print("Safe Driver Discount (true/false): "); boolean safe = sc.nextBoolean();
                    System.out.print("Safety Check Passed (true/false): "); boolean check = sc.nextBoolean(); sc.nextLine();
                    policy = new CollisionPolicy(pid, vtype, vmodel, vyear, holder, coverage, premium, start, end, safe, check);
                    break;
                case 4:
                    System.out.print("Medical Checkup Done (true/false): "); boolean med = sc.nextBoolean();
                    System.out.print("Extended Disability Coverage (true/false): "); boolean ext = sc.nextBoolean(); sc.nextLine();
                    policy = new LiabilityPolicy(pid, vtype, vmodel, vyear, holder, coverage, premium, start, end, med, ext);
                    break;
                case 5:
                    System.out.print("Is Commercial (true/false): "); boolean comm = sc.nextBoolean();
                    System.out.print("Registration Verified (true/false): "); boolean reg = sc.nextBoolean();
                    System.out.print("Inspection Verified (true/false): "); boolean insp = sc.nextBoolean(); sc.nextLine();
                    policy = new RoadsideAssistancePolicy(pid, vtype, vmodel, vyear, holder, coverage, premium, start, end, comm, reg, insp);
                    break;
                default: System.out.println("Invalid policy type."); return;
            }
            policy.calculatePremium();
            if (!policy.validatePolicy()) {
                System.out.println("Policy validation failed!");
                return;
            }
            policies.add(policy);
            System.out.println("Policy added successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void processClaim() {
        System.out.print("Enter Policy ID: ");
        String pid = sc.nextLine();
        for (InsurancePolicy policy : policies) {
            if (policy.getPolicyId().equalsIgnoreCase(pid)) {
                System.out.print("Enter claim amount: ");
                double amount = sc.nextDouble(); sc.nextLine();
                if (!policy.processClaim(amount)) {
                    System.out.println("Claim processing failed.");
                }
                return;
            }
        }
        System.out.println("Policy not found.");
    }

    private static void generateReport() {
        double totalPremiums = 0;
        int totalClaims = 0;
        System.out.println("\n--- Policy Report ---");
        Map<String, Integer> typeCount = new HashMap<>();
        for (InsurancePolicy policy : policies) {
            policy.generatePolicyReport();
            totalPremiums += policy.getPremiumAmount();
            typeCount.put(policy.getPolicyType(), typeCount.getOrDefault(policy.getPolicyType(), 0) + 1);
        }
        System.out.println("Total Premiums Collected: $" + totalPremiums);
        System.out.println("Coverage Breakdown by Policy Type: " + typeCount);
        System.out.println("--- End of Report ---");
    }
}