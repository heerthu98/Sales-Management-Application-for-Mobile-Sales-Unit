package sample;

import java.sql.Date;

public class ManageStocksTable extends ManageProductsTable {
    String productID;
   int total;
    public ManageStocksTable(String productID, int total) {
        this.productID=productID;
        this.total=total;
    }



    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
