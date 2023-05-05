package cz.devfire.auction_system_orm.database.dao;

import cz.devfire.auction_system_orm.database.connection.NamedParameterStatement;
import cz.devfire.auction_system_orm.database.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.LinkedList;

public class UserDao extends AbstractDao<User> {

    @Override
	protected String getSQLInsert() {
		return "INSERT INTO \"User\" " +
				"(login, name, surname, address, telephone, maximum_unfinished_auctions, last_visit, type) " +
				"VALUES " +
				"(:login, :name, :surname, :address, :telephone,:maximum_unfinished_auctions, :last_visit, :type)";
	}

	@Override
	protected String getSQLSelect() {
		return "SELECT * FROM \"User\"";
	}

	@Override
	protected String getSQLSelectById() {
		return "SELECT * FROM \"User\" WHERE id=:id";
	}

	@Override
	protected String getSQLUpdate() {
		return "UPDATE \"User\" " +
				"SET login=:login, name=:name, surname=:surname, address=:address, telephone=:telephone, maximum_unfinished_auctions=:maximum_unfinished_auctions, last_visit=:last_visit, type=:type " +
				"WHERE id=:id";
	}

	@Override
	protected String getSQLDeleteById() {
		return "DELETE FROM \"User\" WHERE id=:id";
	}

	/**
     * Parse statement with the user.
     * 
     * @param statement to be used
     * @param user to be passed to the statement
     * @throws SQLException if an error occurred
     */
	protected void prepareCommand(NamedParameterStatement statement, User user) throws SQLException {
		if (statement.hasParameter("id")) {
			statement.setInt("id", user.getId());
		}

		statement.setString("login", user.getLogin());
		statement.setString("name", user.getName());
		statement.setString("surname", user.getSurname());
		statement.setString("address", user.getAddress());
		statement.setString("telephone", user.getTelephone());
		statement.setInt("maximum_unfinished_auctions", user.getMaximumUnfinishedAuctions());
		statement.setDateTime("last_visit", user.getLastVisit());
		statement.setString("type", user.getType());
	}

	/**
	 * Read the result set.
	 *
	 * @param rs to be read
	 * @return collection of users
	 * @throws SQLException if an error occurred
	 */
	protected Collection<User> read(ResultSet rs) throws SQLException {
        Collection<User> users = new LinkedList<>();

        while (rs.next()) {
            User user = User.builder()
            		    .id(rs.getInt("id"))
            		    .login(rs.getString("login"))
            		    .name(rs.getString("name"))
            		    .surname(rs.getString("surname"))
            		    .address(rs.getString("address"))
            		    .telephone(rs.getString("telephone"))
            		    .maximumUnfinishedAuctions(rs.getInt("maximum_unfinished_auctions"))
            		    .type(rs.getString("type"))
            		    .build();

			// Timestamp can be null
			Timestamp ts = rs.getTimestamp("last_visit");
			if (ts != null) {
				user.setLastVisit(ts.toLocalDateTime());
			}

            users.add(user);
        }

        return users;
    }
	
	@Override
	protected void setKeys(User user, ResultSet generatedKeys) throws SQLException {
		// Modify this if the keys are different

		if (generatedKeys != null && generatedKeys.next()) {
			user.setId(generatedKeys.getInt(1));
		}
	}
	
}
