package net.neferett.redisapi.DataBase;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DataBaseManager {

    private final String ip;
    private final String password;
    private final int port;

    List<DataBase> dataBaseConnectorList = new ArrayList<>();

    public DataBase addDataBase(DataBase dataBase) {
        dataBase.build();
        this.dataBaseConnectorList.add(dataBase);

        return dataBase;
    }

    public DataBase getDataBase(int dbId) {
        DataBase db = this.dataBaseConnectorList.stream().filter(e -> e.getDb() == dbId).findFirst().orElse(null);

        if (null == db) {
            db = new DataBase(ip, password, port, dbId);
            this.addDataBase(db);
        }

        return db;
    }

    public void closeAll() {
        this.dataBaseConnectorList.forEach(DataBase::close);
    }
}
