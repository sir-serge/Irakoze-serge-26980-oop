public class BooksItem extends ShoppingItem {
    private String isbn;
    private String edition;
    private String printQuality;

    public BooksItem(int itemId, String itemName, String itemDescription, double price, int stockAvailable, String isbn, String edition, String printQuality) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.isbn = isbn;
        this.edition = edition;
        this.printQuality = printQuality;
    }

    @Override
    public void updateStock(int quantity) {
        if (stockAvailable + quantity < 0) throw new IllegalArgumentException("Stock cannot be negative.");
        stockAvailable += quantity;
    }

    @Override
    public void addToCart(Customer customer) {
        if (stockAvailable <= 0) throw new IllegalArgumentException("Out of stock.");
        System.out.println(itemName + " (Book) added to " + customer.getCustomerName() + "'s cart.");
    }

    @Override
    public void generateInvoice(Customer customer) {
        System.out.println("Invoice for " + customer.getCustomerName() + ": " + itemName + " - $" + price);
    }

    @Override
    public boolean validateItem() {
        return stockAvailable > 0 && isbn != null && !isbn.isEmpty() && edition != null && !edition.isEmpty() && printQuality != null && !printQuality.isEmpty();
    }
}