package test24;

import java.sql.*;
import dao.DatabaseHelper;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class ProductController {

    public static boolean savePname(String pname) {
        Connection con = null;
        CallableStatement csmt = null;
        boolean t = true;
        try {
            con = DatabaseHelper.getConnection();
            csmt = con.prepareCall("{CALL saveproduct(?)}");
            csmt.setString(1, pname);
            t = csmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                csmt.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        return t;
    }

    public static void loadCombo(JComboBox combo) {
        Connection con = null;
        CallableStatement csmt = null;
        ResultSet rs = null;
        try {
            con = DatabaseHelper.getConnection();
            csmt = con.prepareCall("{ CALL listProduct()}");
            csmt.execute();
            rs = csmt.getResultSet();
            List pList = new ArrayList();
            while (rs.next()) {
                pList.add(rs.getString(1));
            }
            combo.setModel(new DefaultComboBoxModel(pList.toArray()));
            combo.insertItemAt("Select One", 0);
            combo.setSelectedIndex(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                rs.close();
                csmt.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    public static boolean savePurchase(String pname, String price, String date, String qty) {
        Connection con = null;
        CallableStatement csmt = null;
        boolean t = true;
        try {
            con = DatabaseHelper.getConnection();
            csmt = con.prepareCall("{CALL save_purchase(get_product_id(?),?,?,?)}");
            csmt.setString(1, pname);
            csmt.setString(2, price);
            csmt.setString(3, date);
            csmt.setString(4, qty);

            t = csmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                csmt.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        return t;
    }
    
    public static boolean saveSale(String pname, String price, String date, String qty) {
        Connection con = null;
        CallableStatement csmt = null;
        boolean t = true;
        try {
            con = DatabaseHelper.getConnection();
            csmt = con.prepareCall("{CALL save_sale(get_product_id(?),?,?,?)}");
            csmt.setString(1, pname);
            csmt.setString(2, price);
            csmt.setString(3, date);
            csmt.setString(4, qty);

            t = csmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                csmt.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        return t;
    }
}
