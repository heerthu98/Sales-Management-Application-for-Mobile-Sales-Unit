package sample;

public class ProductDettailsTable {

    float proPrice;
    String productID, proName, proModel, proColor;


    public ProductDettailsTable(String productID, String proName, String proModel, String proColor, float proPrice) {
        this.productID = productID;
        this.proPrice = proPrice;
        this.proName = proName;
        this.proModel = proModel;
        this.proColor = proColor;
    }

    public float getProPrice() {
        return proPrice;
    }

    public void setProPrice(float proPrice) {
        this.proPrice = proPrice;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProModel() {
        return proModel;
    }

    public void setProModel(String proModel) {
        this.proModel = proModel;
    }

    public String getProColor() {
        return proColor;
    }

    public void setProColor(String proColor) {
        this.proColor = proColor;
    }


}
