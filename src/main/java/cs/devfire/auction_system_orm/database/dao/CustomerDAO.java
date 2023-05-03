package cs.devfire.auction_system_orm.database.dao;

import cs.devfire.auction_system_orm.database.connection.NamedParameter;
import cs.devfire.auction_system_orm.database.model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.LinkedList;

public class CustomerDAO extends AbstractDAO<Customer> {

    @Override
	protected String getSQLInsert() {
		return "INSERT INTO [Customer] (firstName, lastName, phone, email, deletedAt) " +
				"VALUES (:firstName, :lastName, :phone, :email, :deletedAt);";
	}

	@Override
	protected String getSQLSelect() {
		return "SELECT * FROM [Customer]";
	}

	@Override
	protected String getSQLSelectById() {
		return "SELECT * FROM [Customer] " +
				"WHERE customerID=:id";
	}

	@Override
	protected String getSQLUpdate() {
		return "UPDATE [Customer] " +
				"SET firstName=:firstName, lastName=:lastName, phone=:phone, email=:email, deletedAt=:deletedAt " +
				"WHERE customerID=:id";
	}

	@Override
	protected String getSQLDeleteById() {
		return "UPDATE [Customer] " +
				"SET deletedAt=GETDATE() " +
				"WHERE customerID=:id";
	}

	/**
     * Parse statement with the user.
     * 
     * @param statement to be used
     * @param user to be passed to the statement
     * @throws SQLException if an error occurred
     */
	protected void prepareCommand(NamedParameter statement, Customer user) throws SQLException {
		if (statement.hasParameter("id")) {
			statement.setInt("id", user.getCustomerID());
		}

		if (statement.hasParameter("customerID")) {
			statement.setInt("customerID", user.getCustomerID());
		}

		statement.setString("firstName", user.getFirstName());
		statement.setString("lastName", user.getLastName());
		statement.setString("phone", user.getPhone());
		statement.setString("email", user.getEmail());
		statement.setDateTime("deletedAt", user.getDeletedAt());
	}

	/**
	 * Read the result set.
	 *
	 * @param rs to be read
	 * @return collection of users
	 * @throws SQLException if an error occurred
	 */
	protected Collection<Customer> read(ResultSet rs) throws SQLException {
        Collection<Customer> objects = new LinkedList<>();

        while (rs.next()) {
			Customer object = Customer.builder()
					.customerID(rs.getInt("customerID"))
					.firstName(rs.getString("firstName"))
					.lastName(rs.getString("lastName"))
					.phone(rs.getString("phone"))
					.email(rs.getString("email"))
					.build();

			Timestamp ts = rs.getTimestamp("deletedAt");
			if (ts != null) {
				object.setDeletedAt(ts.toLocalDateTime());
			}

			objects.add(object);
		}

		return objects;
    }
	
	@Override
	protected void setKeys(Customer user, ResultSet generatedKeys) throws SQLException {
		int i = 1;

		while (generatedKeys.next()) {
			user.setCustomerID(generatedKeys.getInt(i++));
		}
	}
	
}
