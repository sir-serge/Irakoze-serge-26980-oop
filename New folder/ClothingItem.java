import java.util.List;

public class ClothingItem extends StockItem {
    private List<String> sizes;
    private List<String> colors;
    private double discount;

    public ClothingItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String supplier, List<String> sizes, List<String> colors, double discount) {
        super(itemId, itemName, quantityInStock, pricePerUnit, "Clothing", supplier);
        if (discount < 0 || discount > 0.5) throw new IllegalArgumentException("Discount cannot exceed 50%.");
        this.sizes = sizes;
        this.colors = colors;
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

    @Override
    public void generateStockReport() {
        System.out.println("Clothing: " + itemName + " | Qty: " + quantityInStock + " | Value: " + calculateStockValue() + " | Sizes: " + sizes + " | Colors: " + colors + " | Discount: " + (discount * 100) + "%");
    }

    @Override
    public boolean validateStock() {
        return quantityInStock > 0 && !sizes.isEmpty() && !colors.isEmpty();
    }
}