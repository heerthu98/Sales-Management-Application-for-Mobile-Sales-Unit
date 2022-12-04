package sample;

public class ManageSalesRepTable {
    int srID;
    String srName;
    int srMobile;
    String srAddress;
    String salesArea;
    public ManageSalesRepTable(int srID, String srName, int srMobile, String srAddress, String salesArea) {
        this.srID=srID;
        this.srName=srName;
        this.srMobile=srMobile;
        this.srAddress=srAddress;
        this.salesArea=salesArea;

    }

    public int getSrID() {
        return srID;
    }

    public void setSrID(int srID) {
        this.srID = srID;
    }

    public String getSrName() {
        return srName;
    }

    public void setSrName(String srName) {
        this.srName = srName;
    }

    public int getSrMobile() {
        return srMobile;
    }

    public void setSrMobile(int srMobile) {
        this.srMobile = srMobile;
    }

    public String getSrAddress() {
        return srAddress;
    }

    public void setSrAddress(String srAddress) {
        this.srAddress = srAddress;
    }

    public String getSalesArea() {
        return salesArea;
    }

    public void setSalesArea(String salesArea) {
        this.salesArea = salesArea;
    }
}
