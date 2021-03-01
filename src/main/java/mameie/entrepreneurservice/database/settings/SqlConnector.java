package mameie.entrepreneurservice.database.settings;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlConnector {

    Connection con;
    ResultSet result;

    public Connection getCon(){
        return  this.con;
    }
    public void reconnect(){

    }
    public boolean setConnection(){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(DatabaseSettings.dbMaster);
        dataSource.setPassword(DatabaseSettings.dbMasterPW);
        dataSource.setServerName(DatabaseSettings.dbPath);

        try {
            con = dataSource.getConnection();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    };
    public boolean isConnected() throws SQLException {
        if(this.con.isClosed()){
            return false;
        }else{
            return true;
        }
    }
    public boolean closeConnection(){
        try {
            this.con.close();
            if(this.con.isClosed()){
                return true;
            }else{
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
