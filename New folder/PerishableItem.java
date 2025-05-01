import java.time.LocalDate;

public class PerishableItem extends StockItem {
    private int shelfLife; // in days
    private LocalDate addedDate;

    public PerishableItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String supplier, int shelfLife, LocalDate addedDate) {
        super(itemId, itemName, quantityInStock, pricePerUnit, "Perishable", supplier);
        if (shelfLife < 1 || shelfLife > 14) throw new IllegalArgumentException("Shelf life must be 1-14 days.");
        this.shelfLife = shelfLife;
        this.addedDate = addedDate;
    }

    @Override
    public void updateStock(int quantity) {
        if (quantityInStock + quantity < 0) throw new IllegalArgumentException("Stock cannot be negative.");
        quantityInStock += quantity;
    }

    @Override
    public double calculateStockValue() {
        return quantityInStock * pricePerUnit;
    }

    @Override
    public void generateStockReport() {
        LocalDate expiry = addedDate.plusDays(shelfLife);
        String alert = expiry.isBefore(LocalDate.now()) ? " [EXPIRED!]" : "";
        System.out.println("Perishable: " + itemName + " | Qty: " + quantityInStock + " | Value: " + calculateStockValue() + " | Expiry: " + expiry + alert);
    }

    @Override
    public boolean validateStock() {
        return addedDate.plusDays(shelfLife).isAfter(LocalDate.now());
    }
}