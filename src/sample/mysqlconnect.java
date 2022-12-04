package sample;

        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javax.swing.*;
        import java.sql.*;

public class mysqlconnect {

    Connection conn = null;

    public static Connection ConnectDb(){

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/rad", "root", "");

            // JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        }

        catch (Exception e){

            JOptionPane.showMessageDialog(null, e);
            return null;
        }


    }

    public static ObservableList<ManageProductsTable> getdataManageProductsTable(){

        Connection conn = ConnectDb();
        ObservableList<ManageProductsTable> list = FXCollections.observableArrayList();

        try {
            PreparedStatement ps = conn.prepareStatement("select * from productdetails");
            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    list.add(new ManageProductsTable(rs.getString("productID"), rs.getString("proName"),rs.getString("proModel"),rs.getString("proColor"), Float.parseFloat(rs.getString("proPrice"))));

                }
            }
        } catch (Exception e){

        }
        return list;
    }

    public static ObservableList<ManageStocksTable> getdataManageStocksTable(){

        Connection conn = ConnectDb();
        ObservableList<ManageStocksTable> listS = FXCollections.observableArrayList();

        try {
            PreparedStatement ps = conn.prepareStatement("select productID , sum(count) as total from totalstock");
            //PreparedStatement pjs = conn.prepareStatement("select sum(count) from totalstock");
            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    listS.add(new ManageStocksTable(rs.getString("productID"),Integer.parseInt(rs.getString("total"))));

                }
            }
        } catch (Exception e){

        }
        return listS;
    }

    public static ObservableList<ManageSalesRepTable> getdataManageSalesRepTable(){

        Connection conn = ConnectDb();
        ObservableList<ManageSalesRepTable> list2 = FXCollections.observableArrayList();

        try {
            PreparedStatement ps = conn.prepareStatement("select * from salesrep");
            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    list2.add(new ManageSalesRepTable(rs.getInt("srID"), rs.getString("srName"),rs.getInt("srMobile"),rs.getString("srAddress"),rs.getString("salesArea")));

                }
            }
        } catch (Exception e){

        }
        return list2;
    }


    public static ObservableList<dailyReportTable> getDatadailyReportTable(){

        Connection conn = ConnectDb();
        ObservableList<dailyReportTable> list = FXCollections.observableArrayList();

        try {
            PreparedStatement ps = conn.prepareStatement("select * from personalstock, productdetails where personalstock.productID = productdetails.productID");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){

                list.add(new dailyReportTable (rs.getString("productID"), rs.getString("proModel"), Integer.parseInt(rs.getString("count")), Float.parseFloat(rs.getString("proPrice")), Float.parseFloat(rs.getString("subTotal")), rs.getDate("date")));


            }
        } catch (Exception e){

        }

        return list;
    }


    public static ObservableList<personalStockTable> getDatapersonalStockTable(){

        Connection conn = ConnectDb();
        ObservableList<personalStockTable> list = FXCollections.observableArrayList();

        try {
            PreparedStatement ps = conn.prepareStatement("select * from personalstock, productdetails where personalstock.productID = productdetails.productID  ");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){

                list.add(new personalStockTable (rs.getString("productID"), rs.getString("proModel"), Integer.parseInt(rs.getString("srID")), Integer.parseInt(rs.getString("count")),  rs.getDate("date")));


            }
        } catch (Exception e){

        }

        return list;
    }

    public static ObservableList<ProductDettailsTable> getDataProductDettailsTable(){

        Connection conn = ConnectDb();
        ObservableList<ProductDettailsTable> list = FXCollections.observableArrayList();

        try {
            PreparedStatement ps = conn.prepareStatement("select * from productdetails");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){

                list.add(new ProductDettailsTable (rs.getString("productID"), rs.getString("proName"), rs.getString("proModel"), rs.getString("proColor"), Float.parseFloat(rs.getString("proPrice"))));


            }
        } catch (Exception e){

        }

        return list;
    }


}


