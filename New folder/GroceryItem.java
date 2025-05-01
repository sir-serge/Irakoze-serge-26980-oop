import java.time.LocalDate;

public class GroceryItem extends StockItem {
    private LocalDate expirationDate;

    public GroceryItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String supplier, LocalDate expirationDate) {
        super(itemId, itemName, quantityInStock, pricePerUnit, "Groceries", supplier);
        this.expirationDate = expirationDate;
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
        String flag = "";
        if (expirationDate.isBefore(LocalDate.now().plusDays(5))) flag = " [NEAR EXPIRY]";
        System.out.println("Grocery: " + itemName + " | Qty: " + quantityInStock + " | Value: " + calculateStockValue() + " | Expiry: " + expirationDate + flag);
    }

    @Override
    public boolean validateStock() {
        return !expirationDate.isBefore(LocalDate.now());
    }
}