public class ElectronicsItem extends StockItem {
    private int warrantyPeriod; // in months
    private double discount; // 0.0 to 0.5

    public ElectronicsItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String supplier, int warrantyPeriod, double discount) {
        super(itemId, itemName, quantityInStock, pricePerUnit, "Electronics", supplier);
        if (warrantyPeriod < 0 || warrantyPeriod > 60) throw new IllegalArgumentException("Warranty period must be 0-60 months.");
        if (discount < 0 || discount > 0.5) throw new IllegalArgumentException("Discount cannot exceed 50%.");
        this.warrantyPeriod = warrantyPeriod;
        this.discount = discount;
    }

    @Override
    public void updateStock(int quantity) {
        if (quantityInStock + quantity < 0) throw new IllegalArgumentException("Stock cannot be negative.");
        quantityInStock += quantity;
    }

    @Override
    public double calculateStockValue() {
        return quantityInStock * pricePerUnit * (1 - discount);
    }

    public void applyDiscount(double discount) {
        if (discount < 0 || discount > 0.5) throw new IllegalArgumentException("Discount cannot exceed 50%.");
        this.discount = discount;
    }

    @Override
    public void generateStockReport() {
        System.out.println("Electronics: " + itemName + " | Qty: " + quantityInStock + " | Value: " + calculateStockValue() + " | Warranty: " + warrantyPeriod + " months | Discount: " + (discount * 100) + "%");
    }

    @Override
    public boolean validateStock() {
        return quantityInStock > 0;
    }
}