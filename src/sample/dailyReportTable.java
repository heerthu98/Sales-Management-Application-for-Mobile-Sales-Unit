package sample;

import java.util.Date;

public class dailyReportTable {

    String productID, proModel;

    int count;

    float proPrice, subTotal;

    Date date;



    public dailyReportTable(String productID, String proModel, int count, float proPrice, float subTotal, Date date) {
        this.productID = productID;
        this.proModel = proModel;
        this.count = count;
        this.proPrice = proPrice;
        this.subTotal = subTotal;

        this.date = date;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProModel() {
        return proModel;
    }

    public void setProModel(String proModel) {
        this.proModel = proModel;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getProPrice() {
        return proPrice;
    }

    public void setProPrice(float proPrice) {
        this.proPrice = proPrice;
    }

    public float getSubTotal() {

        subTotal = count * proPrice;
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
