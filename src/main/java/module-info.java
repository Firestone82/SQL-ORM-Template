module db.impl.main {
    requires lombok;
    requires java.sql;
    requires org.apache.logging.log4j;
    requires com.microsoft.sqlserver.jdbc;

    exports cz.devfire.auction_system_orm;
}