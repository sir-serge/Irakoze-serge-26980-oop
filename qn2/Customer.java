public class Customer {
    private int customerId;
    private String customerName;
    private String email;
    private String address;
    private String phone;

    public Customer(int customerId, String customerName, String email, String address, String phone) {
        if (!validateEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (!validatePhone(phone)) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
        if (!validateAddress(address)) {
            throw new IllegalArgumentException("Address cannot be empty");
        }
        
        this.customerId = customerId;
        this.customerName = customerName;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    private boolean validateEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    private boolean validatePhone(String phone) {
        return phone.matches("^\\+?[0-9]{10,15}$");
    }

    private boolean validateAddress(String address) {
        return address != null && !address.trim().isEmpty();
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
} 