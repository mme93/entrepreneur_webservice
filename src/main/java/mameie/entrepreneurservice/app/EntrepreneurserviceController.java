package mameie.entrepreneurservice.app;


import mameie.entrepreneurservice.database.objects.EntrepreneurAccount;
import mameie.entrepreneurservice.database.settings.DatabaseSettings;
import mameie.entrepreneurservice.database.settings.SqlConnector;
import mameie.entrepreneurservice.database.table.EntrepreneurAccountTable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class EntrepreneurserviceController {

    SqlConnector sqlConnector;

    public EntrepreneurserviceController(){
        super();
        init();
    }
    //Startet die Verbindung zur Datenbank
    private void init(){
        this.sqlConnector=new SqlConnector();
        sqlConnector.setConnection();
        try {
            if(sqlConnector.isConnected()){
                DatabaseSettings.isConnected=true;
            }else{
                DatabaseSettings.isConnected=false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @GetMapping("/login/{username}/{password}")
    public void getLoginDataIsCorrect(@PathVariable("username") String username, @PathVariable("password") String password) throws Exception {
        if(this.sqlConnector!=null){
            for(EntrepreneurAccount entrepreneurAccount: EntrepreneurAccountTable.getTable(this.sqlConnector.getCon())){
                    System.err.println(entrepreneurAccount.getPassword());
            }
        }
    }

}
