package cs.devfire.auction_system_orm.database.dao;

import cs.devfire.auction_system_orm.database.connection.NamedParameter;
import cs.devfire.auction_system_orm.database.model.Customer;
import cs.devfire.auction_system_orm.database.model.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.LinkedList;

public class TableDAO extends AbstractDAO<Table> {

    @Override
	protected String getSQLInsert() {
		return "INSERT INTO [Table] (occupied, capacity) " +
				"VALUES (:occupied, :capacity);";
	}

	@Override
	protected String getSQLSelect() {
		return "SELECT * FROM [Table]";
	}

	@Override
	protected String getSQLSelectById() {
		return "SELECT * FROM [Table] " +
				"WHERE tableID=:id";
	}

	@Override
	protected String getSQLUpdate() {
		return "UPDATE [Table] " +
				"SET occupied=:occupied, capacity=:capacity " +
				"WHERE tableID=:id";
	}

	@Override
	protected String getSQLDeleteById() {
		return "DELETE FROM [Table] " +
				"WHERE tableID=:id";
	}

	/**
     * Parse statement with the user.
     * 
     * @param statement to be used
     * @param table to be passed to the statement
     * @throws SQLException if an error occurred
     */
	protected void prepareCommand(NamedParameter statement, Table table) throws SQLException {
		if (statement.hasParameter("id")) {
			statement.setInt("id", table.getTableID());
		}

		if (statement.hasParameter("tableID")) {
			statement.setInt("tableID", table.getTableID());
		}

		statement.setInt("occupied", table.getOccupied());
		statement.setInt("capacity", table.getCapacity());
	}

	/**
	 * Read the result set.
	 *
	 * @param rs to be read
	 * @return collection of users
	 * @throws SQLException if an error occurred
	 */
	protected Collection<Table> read(ResultSet rs) throws SQLException {
        Collection<Table> objects = new LinkedList<>();

        while (rs.next()) {
			Table object = Table.builder()
					.tableID(rs.getInt("tableID"))
					.occupied(rs.getInt("occupied"))
					.capacity(rs.getInt("capacity"))
					.build();

			objects.add(object);
		}

		return objects;
    }
	
	@Override
	protected void setKeys(Table user, ResultSet generatedKeys) throws SQLException {
		int i = 1;

		while (generatedKeys.next()) {
			user.setTableID(generatedKeys.getInt(i++));
		}
	}
	
}
