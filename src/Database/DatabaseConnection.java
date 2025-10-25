package Database;

import Interfaces.DbConnection;

import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection implements DbConnection {
    private final String connectionString;

    public DatabaseConnection(String connectionString) {
        this.connectionString = connectionString;
    }

    @Override
    public List<String> query(String sql) {
        System.out.println("Ejecutando query: " + sql);
        // Simula la ejecución de una consulta y devuelve datos de ejemplo
        List<String> results = new ArrayList<>();
        results.add("Dato 1");
        results.add("Dato 2");
        return results;
    }

    @Override
    public void execute(String sql) {
        System.out.println("Ejecutando sentencia: " + sql);
        // Simula la ejecución de una sentencia
    }
}
