package cs.devfire.auction_system_orm.database.connection;

import java.sql.SQLException;
import java.util.concurrent.Callable;

public interface SQLCallable<V> extends Callable<V> {
    @Override
    V call() throws SQLException;
}
