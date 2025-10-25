package Repository;

import Interfaces.DbConnection;

import java.util.List;

public class DatabaseRepositoryImpl implements DatabaseRepository{

    private final DbConnection dbConnection;

    public DatabaseRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


    @Override
    public List<String> getUserData(String userId) {
        return dbConnection.query("SELECT * FROM users WHERE id = '" + userId + "'");
    }

    @Override
    public void setUserData(String userId, List<String> data) {
        dbConnection.execute("UPDATE users SET data = '...' WHERE id = '" + userId + "'");
    }
}
