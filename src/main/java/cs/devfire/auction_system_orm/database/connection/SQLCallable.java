package cs.devfire.auction_system_orm.database.connection;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface SQLCallable<V> {
    V call(Connection connection) throws SQLException;
}
