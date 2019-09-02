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
            csmt = con.prepareCall("{CALL listProduct()}");
            csmt.execute();
            rs=csmt.getResultSet();
            List pList=new ArrayList();
            while(rs.next()){
              pList.add(rs.getString(1));
            }
            combo.setModel(new DefaultComboBoxModel(pList.toArray()));
            combo.insertItemAt("Select One", 0);
            combo.setSelectedIndex(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
                rs.close();
                csmt.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }
}
