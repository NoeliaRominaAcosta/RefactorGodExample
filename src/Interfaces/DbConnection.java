package Interfaces;

import java.util.List;

public interface DbConnection {
    List<String> query(String sql);
    void execute(String sql);
}
