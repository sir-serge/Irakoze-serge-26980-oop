import java.time.LocalDateTime;

public class Payment {
    private int paymentId;
    private String paymentMethod;
    private double amountPaid;
    private LocalDateTime transactionDate;
    private boolean isProcessed;

    public Payment(int paymentId, String paymentMethod, double amountPaid) {
        if (!validatePaymentMethod(paymentMethod)) {
            throw new IllegalArgumentException("Invalid payment method");
        }
        if (amountPaid <= 0) {
            throw new IllegalArgumentException("Payment amount must be positive");
        }

        this.paymentId = paymentId;
        this.paymentMethod = paymentMethod;
        this.amountPaid = amountPaid;
        this.transactionDate = LocalDateTime.now();
        this.isProcessed = false;
    }

    private boolean validatePaymentMethod(String method) {
        return method.equalsIgnoreCase("CREDIT_CARD") ||
               method.equalsIgnoreCase("DEBIT_CARD") ||
               method.equalsIgnoreCase("PAYPAL") ||
               method.equalsIgnoreCase("BANK_TRANSFER");
    }

    public boolean processPayment(double expectedAmount) {
        if (amountPaid != expectedAmount) {
            System.out.println("Payment amount mismatch. Expected: $" + expectedAmount + ", Paid: $" + amountPaid);
            return false;
        }

        // Simulate payment processing
        try {
            Thread.sleep(1000); // Simulate processing time
            isProcessed = true;
            System.out.println("Payment processed successfully!");
            return true;
        } catch (InterruptedException e) {
            System.out.println("Payment processing interrupted");
            return false;
        }
    }

    public int getPaymentId() {
        return paymentId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", amountPaid=" + amountPaid +
                ", transactionDate=" + transactionDate +
                ", isProcessed=" + isProcessed +
                '}';
    }
} 