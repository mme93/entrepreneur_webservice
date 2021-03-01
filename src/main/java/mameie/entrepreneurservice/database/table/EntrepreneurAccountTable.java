package mameie.entrepreneurservice.database.table;

import mameie.entrepreneurservice.database.objects.EntrepreneurAccount;
import mameie.entrepreneurservice.database.settings.DatabaseSettings;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EntrepreneurAccountTable {

    public  final String tableName="account";
    public  final String coL_int_id="id";
    public  final String col_str_username="username";
    public  final String col_str_password="password";
    public  final String col_str_email="email";


    public List<EntrepreneurAccount> getTable(Connection con){
        List<EntrepreneurAccount> entrepreneurAccountList= new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            System.err.println(stmt.getConnection());
            ResultSet result= stmt.executeQuery("USE "+ DatabaseSettings.dbMaster);
            result= stmt.executeQuery("SELECT * FROM "+tableName);
            if(result!=null){
                result.first();
                while(!result.isAfterLast()){
                    EntrepreneurAccount entrepreneurAccount = new EntrepreneurAccount();
                    entrepreneurAccount.setPassword(result.getString(col_str_password));
                    entrepreneurAccount.setId(result.getInt(coL_int_id));
                    entrepreneurAccount.setEmail(result.getString(col_str_email));
                    entrepreneurAccount.setUsername(result.getString(col_str_username));
                    entrepreneurAccountList.add(entrepreneurAccount);
                    result.next();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return entrepreneurAccountList;
    }
}
