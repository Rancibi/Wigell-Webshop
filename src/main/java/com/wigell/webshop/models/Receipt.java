package com.wigell.webshop.models;

import com.wigell.webshop.models.clothes.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Receipt {
    private Order order;
    private String date;

    public Receipt(Order order) {
        this.order = order;
        this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
    }

    @Override
    public String toString() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("🧾 Kvitto - Webshop\n");
        receipt.append("Datum: ").append(date).append("\n");
        receipt.append("Kund: ").append(order.getCustomer().getName()).append("\n");
        receipt.append("Adress: ").append(order.getCustomer().getAddress()).append("\n");
        receipt.append("Beställning: \n");

        double totalPrice = 0;
        for (Clothes item : order.getClothesList()) {
            receipt.append(" - ").append(item).append("\n");
            totalPrice += item.getPrice();
        }

        receipt.append("Total kostnad: ").append(totalPrice).append(" kr\n");
        receipt.append("Tack för ditt köp!\n");

        return receipt.toString();
    }
}
