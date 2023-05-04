package cs.devfire.auction_system_orm.database.dao;

import cs.devfire.auction_system_orm.database.connection.ConnectionProvider;
import cs.devfire.auction_system_orm.database.connection.NamedParameter;
import cs.devfire.auction_system_orm.database.connection.NamedParameterCall;
import cs.devfire.auction_system_orm.database.model.Reservation;
import cs.devfire.auction_system_orm.utils.Colors;

import javax.sql.ConnectionPoolDataSource;
import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

public class ReservationDAO extends AbstractDAO<Reservation> {

    @Override
	protected String getSQLInsert() {
		return "INSERT INTO [Reservation] (customerID, tableID, start, [end], amount, created, canceled, description)" +
				"VALUES (:customerID, :tableID, :start, :end, :amount, :created, :canceled, :description);";
	}

	@Override
	protected String getSQLSelect() {
		return "SELECT * FROM [Reservation]";
	}

	@Override
	protected String getSQLSelectById() {
		return "SELECT * FROM [Reservation] " +
				"WHERE reservationID=:id";
	}

	@Override
	protected String getSQLUpdate() {
		return "UPDATE [Reservation] " +
				"SET customerID=:customerID, tableID=:tableID, start=:start, [end]=:end, amount=:amount, created=:created, canceled=:canceled, description=:description " +
				"WHERE reservationID=:id";
	}

	@Override
	protected String getSQLDeleteById() {
		return "UPDATE [Reservation] " +
				"SET deletedAt=GETDATE() " +
				"WHERE reservationID=:id";
	}

	/**
     * Parse statement with the user.
     * 
     * @param statement to be used
     * @param reservation to be passed to the statement
     * @throws SQLException if an error occurred
     */
	public void prepareCommand(NamedParameter statement, Reservation reservation) throws SQLException {
		if (statement.hasParameter("id") || statement.hasParameter("reservationID")) {
			statement.setInt("reservationID", reservation.getCustomerID());
		}

		statement.setInt("customerID", reservation.getCustomerID());
		statement.setInt("tableID", reservation.getTableID());
		statement.setDateTime("start", reservation.getStart());
		statement.setDateTime("end", reservation.getEnd());
		statement.setInt("amount", reservation.getAmount());
		statement.setString("description", reservation.getDescription());

		if (statement.hasParameter("created")) {
			statement.setDateTime("created", reservation.getCreated());
		}

		if (statement.hasParameter("deletedAt")) {
			statement.setDateTime("deletedAt", reservation.getDeletedAt());
		}

		if (statement.hasParameter("canceled")) {
			statement.setInt("canceled", reservation.getCanceled());
		}
	}

	/**
	 * Read the result set.
	 *
	 * @param rs to be read
	 * @return collection of users
	 * @throws SQLException if an error occurred
	 */
	protected Collection<Reservation> read(ResultSet rs) throws SQLException {
        Collection<Reservation> objects = new LinkedList<>();

        while (rs.next()) {
			Reservation object = Reservation.builder()
					.customerID(rs.getInt("customerID"))
					.tableID(rs.getInt("tableID"))
					.start(rs.getTimestamp("start").toLocalDateTime())
					.end(rs.getTimestamp("end").toLocalDateTime())
					.amount(rs.getInt("amount"))
					.created(rs.getTimestamp("created").toLocalDateTime())
					.canceled(rs.getInt("canceled"))
					.description(rs.getString("description"))
					.build();

			Timestamp ts = rs.getTimestamp("deletedAt");
			if (ts != null) {
				object.setDeletedAt(ts.toLocalDateTime());
			}

			objects.add(object);
		}

		return objects;
    }

	public String createReservation(Reservation reservation) throws SQLException {
		String sql = "{call NewReservation(:customerID, :tableID, :amount, :start, :end, :description, :status)}";

		try (Connection conn = ConnectionProvider.getConnection()) {
			try (CallableStatement cs = conn.prepareCall(sql)) {
				try (NamedParameterCall npc = new NamedParameterCall(conn, sql)) {
					prepareCommand(npc, reservation);
					npc.registerOutParameter("status", Types.VARCHAR);
					npc.execute();

					return (String) npc.getOutParameter("status");
				}
			}
		}
	}
	
	@Override
	protected void setKeys(Reservation reservation, ResultSet generatedKeys) throws SQLException {
		int i = 1;

		while (generatedKeys.next()) {
			reservation.setReservationID(generatedKeys.getInt(i++));
		}
	}
	
}
