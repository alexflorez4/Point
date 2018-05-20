package main.java.com.ea.domain;

import org.apache.commons.lang3.StringUtils;

public class AmzModel {

    public int sellerId;
    public int marketId;
    public int supplierId;
    public String marketOrderId;
    public String marketListingId;
    public String sku;
    public int quantity;
    public String buyerName;
    public Address address;
    public String observations;

    public AmzModel() {
    }

    public AmzModel(int sellerId, int marketId, int supplierId, String marketOrderId, String marketListingId, String sku, int quantity, String buyerName, Address address, String observations) {
        this.sellerId = sellerId;
        this.marketId = marketId;
        this.supplierId = supplierId;
        this.marketOrderId = marketOrderId;
        this.marketListingId = marketListingId;
        this.sku = sku;
        this.quantity = quantity;
        this.buyerName = buyerName;
        this.address = address;
        this.observations = observations;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getMarketId() {
        return marketId;
    }

    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getMarketOrderId() {
        return marketOrderId;
    }

    public void setMarketOrderId(String marketOrderId) {
        this.marketOrderId = marketOrderId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getMarketListingId() {
        return marketListingId;
    }

    public void setMarketListingId(String marketListingId) {
        this.marketListingId = marketListingId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    @Override
    public String toString() {
        String orderStr = "&sellerId=" + sellerId +
                "&marketId=" + marketId +
                "&supplierId=" + supplierId +
                "&marketOrderId=" + marketOrderId +
                "&sku=" + sku +
                "&marketListingId=" + marketListingId +
                "&quantity=" + quantity +
                "&buyerName=" + buyerName +
                "&street=" + address +
                "&observations=" + observations;

        return StringUtils.replaceAll(orderStr, " ", "%20");
    }
}
