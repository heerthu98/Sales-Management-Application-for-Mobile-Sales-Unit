package sample;

import javax.swing.*;
import java.util.Date;

public class personalStockTable {

   String productID, proModel;
   Date date;
   int srID, count;

    public personalStockTable(String productID, String proModel, int srID, int count, Date date) {
        this.productID = productID;
        this.proModel = proModel;
        this.srID = srID;
        this.count = count;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSrID() {
        return srID;
    }

    public void setSrID(int srID) {
        this.srID = srID;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

