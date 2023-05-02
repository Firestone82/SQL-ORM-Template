module db.impl.main {
    requires lombok;
    requires java.sql;
    requires org.apache.logging.log4j;
    requires com.microsoft.sqlserver.jdbc;

    exports cs.devfire.auction_system_orm;
}