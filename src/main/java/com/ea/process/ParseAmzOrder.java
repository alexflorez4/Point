package main.java.com.ea.process;

import main.java.com.ea.domain.Address;
import main.java.com.ea.domain.AmzModel;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static main.java.com.ea.process.ParseAmzOrder.EnumSuppliers.AZEnum;
import static main.java.com.ea.process.ParseAmzOrder.EnumSuppliers.FXEnum;
import static main.java.com.ea.process.ParseAmzOrder.EnumSuppliers.TDEnum;

public class ParseAmzOrder {

    private static final String TELE_DYN = "([A-Za-z]{2,3})([-])(\\d+\\w+)(//s)";
    private static final String FRAGX = "[0-9]{6}";

    public enum EnumSuppliers {

        AZEnum(359, "TOYS"), FXEnum(542, " Perfumes"), TDEnum(543, "Teledynamics");

        int supplierId;
        String supplierName;

        EnumSuppliers(int id, String name){
            this.supplierId = id;
            this.supplierName = name;
        }

        public int getSupplierId() {
            return supplierId;
        }

        public String getSupplierName() {
            return supplierName;
        }
    }

    public List<AmzModel> parse(File file){

        List<AmzModel> orders = new ArrayList<>();

        Document doc;

        try {
            doc = Jsoup.parse(file, "UTF-8");
            Elements content = doc.getElementsByAttributeValueStarting("id", "myo-packing-slip-");

            for(Element nextElm : content){

                //Order id:
                Elements orderId = nextElm.getElementsByAttributeValueMatching("class", "a-section myo-orderId");

                if(StringUtils.isBlank(orderId.text()))
                    continue;

                String amzOrderId = StringUtils.strip(orderId.text(), "Order ID:");
                //orderIdStr = StringUtils.replace(orderIdStr, "-", "");

                Elements addressElm = nextElm.getElementsByAttributeValueMatching("class", "a-section myo-address");

                //Buyer name:
                String orderAddressHtml = addressElm.html();
                String addRem = StringUtils.substringAfter(orderAddressHtml, "<span id=\"myo-order-details-buyer-address\" class=\"myo-wrap\">");

                String buyerName = StringUtils.substringBefore(addRem, "<br>").trim();
                addRem = StringUtils.substringAfter(addRem, "<br>");

                String address = StringUtils.substringBefore(addRem, "<br>").trim();
                addRem = StringUtils.substringAfter(addRem, "<br>");

                String city = StringUtils.substringBefore(addRem, "<span").trim().replace(",","");
                addRem = StringUtils.substringAfter(addRem, "<span class=\"a-letter-space\">");

                String addressOther = StringUtils.substringBefore(addRem, "</span>").trim();
                addRem = StringUtils.substringAfter(addRem, "</span>");

                String state = StringUtils.substringBefore(addRem, "<span").trim();
                addRem = StringUtils.substringAfter(addRem, "<span class=\"a-letter-space\">");

                String stateOther = StringUtils.substringBefore(addRem, "</span").trim();
                addRem = StringUtils.substringAfter(addRem, "</span>").trim();

                String zipCode;
                String country;

                if(addRem.contains("<br>")){
                    zipCode = StringUtils.substringBefore(addRem, "<br>").trim();
                    addRem = StringUtils.substringAfter(addRem, "<br>");
                    country = StringUtils.substringBefore(addRem, "</span>").trim();
                }else{
                    zipCode = StringUtils.substringBefore(addRem, "</span>").trim();
                    country = "United States";
                }

                //Quantity:
                Elements qty = nextElm.getElementsByAttributeValueMatching("class", "a-text-center table-border");
                String quantity = qty.first().text();

                //Sku:
                Elements orderDetails = nextElm.getElementsByAttributeValueMatching("class", "a-row");
                List<String> details = orderDetails.eachText();

                String sku = details.stream().filter(s-> s.contains("SKU:")).reduce("", String::concat).trim();
                sku = StringUtils.removeAll(StringUtils.strip(sku, "SKU:").trim(), "(\\s+)(\\d+)|(\\s+)([Xx])(\\d+)");

                String asin = details.stream().filter(s-> s.contains("ASIN:")).reduce("", String::concat).trim();

                //Used for supplier name - String listingId = details.stream().filter(s-> s.contains("Listing ID:")).reduce("", String::concat).trim();


                String total = details.stream().filter(s-> s.contains("Grand total:")).reduce("", String::concat).trim();



                EnumSuppliers suppEnum = (EnumSuppliers) supplierChecker(sku);
                int supplierId = suppEnum.getSupplierId();
                String supplierName = suppEnum.getSupplierName();
                String listingId = suppEnum.getSupplierName();

                //String sku=null;
                Address buyerAddress = new Address(address, addressOther, city, state, stateOther, zipCode, country);
                /*for(String temp : details){
                    if(StringUtils.contains(temp, "SKU:"))
                        sku = StringUtils.strip(temp, "SKU:");
                    else
                        continue;
                }*/
                orders.add(new AmzModel(0, 100, supplierId, amzOrderId, listingId, sku, Integer.parseInt(quantity), buyerName, buyerAddress, ""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public static Enum<EnumSuppliers> supplierChecker(String sku2Check){

        if(Pattern.compile(FRAGX).matcher(sku2Check).matches()){
            return FXEnum;
        }else if(Pattern.compile(TELE_DYN).matcher(sku2Check).matches()){
            return TDEnum;
        }else{
            return AZEnum;
        }
    }
}
