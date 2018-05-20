package main.java.com.ea.process;

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

public class ParseAmzOrder {

    public List<AmzModel> parse(File file){

        List<AmzModel> orders = new ArrayList<>();
        String orderIdStr = null;
        String buyerName = null;
        String buyerAddress = null;
        String quantity = null;
        String sku = null;

        Document doc;
        try {
            doc = Jsoup.parse(file, "UTF-8");
            Elements content = doc.getElementsByAttributeValueStarting("id", "myo-packing-slip-");

            for(Element nextElm : content){

                //Order id:
                Elements orderId = nextElm.getElementsByAttributeValueMatching("class", "a-section myo-orderId");

                if(StringUtils.isBlank(orderId.text()))
                    continue;

                orderIdStr = StringUtils.strip(orderId.text(), "Order ID:");
                orderIdStr = StringUtils.replace(orderIdStr, "-", "");

                Elements address = nextElm.getElementsByAttributeValueMatching("class", "a-section myo-address");

                //Buyer name:
                String orderAddressHtml = address.html();
                buyerName = StringUtils.substringBetween(orderAddressHtml, "<span id=\"myo-order-details-buyer-address\" class=\"myo-wrap\">", "<br>");

                //Buyer address:
                String orderAddressText = address.text();
                buyerAddress = StringUtils.stripStart(orderAddressText, buyerName);

                //Quantity:
                Elements qty = nextElm.getElementsByAttributeValueMatching("class", "a-text-center table-border");
                quantity = qty.first().text();

                //Sku:
                Elements orderDetails = nextElm.getElementsByAttributeValueMatching("class", "a-row");
                List<String> details = orderDetails.eachText();

                //List<String> filteredList = new ArrayList<>();
                //details.stream().filter(s-> s.contains("SKU:")).forEach(filteredList::add);
                //sku = StringUtils.strip(filteredList.get(0), "SKU:");


                for(String temp : details){
                    if(StringUtils.contains(temp, "SKU:"))
                        sku = StringUtils.strip(temp, "SKU:");
                    else
                        continue;
                }

                orders.add(new AmzModel(Long.valueOf(orderIdStr), buyerName, buyerAddress, Integer.valueOf(quantity), sku));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return orders;
    }
}
