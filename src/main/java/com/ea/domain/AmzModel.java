package main.java.com.ea.domain;

public class AmzModel {

    private long orderId;
    private String buyerName;
    private String buyerAddress;
    private int quantity;
    private String sku;

    public AmzModel(Long orderId, String buyerName, String buyerAddress, int quantity, String sku) {
        this.orderId = orderId;
        this.buyerName = buyerName;
        this.buyerAddress = buyerAddress;
        this.quantity = quantity;
        this.sku = sku;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
