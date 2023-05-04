package cs.devfire.auction_system_orm;

import cs.devfire.auction_system_orm.database.connection.ConnectionProvider;
import cs.devfire.auction_system_orm.database.connection.NamedParameterCall;
import cs.devfire.auction_system_orm.database.dao.CustomerDAO;
import cs.devfire.auction_system_orm.database.dao.ReservationDAO;
import cs.devfire.auction_system_orm.database.dao.TableDAO;
import cs.devfire.auction_system_orm.database.model.Customer;
import cs.devfire.auction_system_orm.database.model.Reservation;
import cs.devfire.auction_system_orm.database.model.Table;
import cs.devfire.auction_system_orm.database.model.TableStatus;
import cs.devfire.auction_system_orm.utils.Colors;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

@Log4j2(topic = "App")
public class Program {

	public static void main(String[] args) {
		loadProperties();

		try (Connection con = ConnectionProvider.getConnection()) {
			log.info(Colors.ANSI_PURPLE +"Connected: {}"+ Colors.ANSI_RESET,con != null);
		} catch (SQLException e) {
			log.error("Connection error", e);
			return;
		}

		try {
			log.info("");
			log.info(Colors.ANSI_RED +"CRUD of table" + Colors.ANSI_RESET);
			log.info("-------------------------------------------------------");

			TableDAO dao = new TableDAO();
			int status = -1;

			Table table1 = Table.builder().occupied(0).capacity(15).build();
			Table table2 = Table.builder().occupied(0).capacity(4).build();

			status = dao.insert(table1);
			log.info(Colors.ANSI_GREEN +"Creating new table: {}"+ Colors.ANSI_RESET, table1);
			log.info(Colors.ANSI_YELLOW +" - Status: {}, Total tables: {}"+ Colors.ANSI_RESET, status == 1, dao.select().size());

			status = dao.insert(table2);
			log.info(Colors.ANSI_GREEN +"Creating new table: {}"+ Colors.ANSI_RESET, table2);
			log.info(Colors.ANSI_YELLOW +" - Status: {}, Total tables: {}"+ Colors.ANSI_RESET, status == 1, dao.select().size());

			Table table3 = dao.select(table2.getTableID());
			log.info(Colors.ANSI_GREEN +"Reading table with id {}"+ Colors.ANSI_RESET, table2.getTableID());
			log.info(Colors.ANSI_YELLOW +" - Table: {}"+ Colors.ANSI_RESET, table3);

			table1.setOccupied(1);
			table1.setCapacity(10);
			status = dao.update(table1);
			log.info(Colors.ANSI_GREEN +"Updating table: {}"+ Colors.ANSI_RESET, table1);
			log.info(Colors.ANSI_YELLOW +" - Status: {}, Total tables: {}"+ Colors.ANSI_RESET, status == 1, dao.select().size());

			status = dao.delete(table1.getTableID());
			log.info(Colors.ANSI_GREEN +"Deleting table with id {}"+ Colors.ANSI_RESET, table1.getTableID());
			log.info(Colors.ANSI_YELLOW +" - Status: {}, Total tables: {}"+ Colors.ANSI_RESET, status == 1, dao.select().size());

			log.info("-------------------------------------------------------");
		} catch (SQLException e) {
			log.error("Connection error", e);
		}

		try {
			log.info("");
			log.info(Colors.ANSI_RED +"FUNCTION - Getting all tables with its status"+ Colors.ANSI_RESET);
			log.info("--------------------------------------------------------");

			TableDAO dao = new TableDAO();
			for (TableStatus ts : dao.getTableStatus()) {
				log.info(Colors.ANSI_YELLOW +"| Table: {} | Status: {} | CustomerID: {} |" + Colors.ANSI_RESET,
						String.format("%-2s", ts.getTableID()),
						String.format("%-16s", ts.getStatus()),
						ts.getCustomerID());
			}

			log.info("--------------------------------------------------------");
		} catch (SQLException e) {
			log.error("Connection error", e);
		}

		try {
			log.info("");
			log.info(Colors.ANSI_RED +"CRUD of customer" + Colors.ANSI_RESET);
			log.info("-------------------------------------------------------");

			CustomerDAO dao = new CustomerDAO();
			int status = -1;

			Customer customer1 = Customer.builder().firstName("Peter").lastName("Parker").email("peterspider@gmail.com").phone("123456789").build();
			Customer customer2 = Customer.builder().firstName("Bruce").lastName("Wayne").email("bumbrucnik@yahoo.com").phone("987654321").build();

			status = dao.insert(customer1);
			log.info(Colors.ANSI_GREEN +"Creating new customer: {}"+ Colors.ANSI_RESET, customer1);
			log.info(Colors.ANSI_YELLOW +" - Status: {}, Total customers: {}"+ Colors.ANSI_RESET, status == 1, dao.select().size());

			status = dao.insert(customer2);
			log.info(Colors.ANSI_GREEN +"Creating new customer: {}"+ Colors.ANSI_RESET, customer2);
			log.info(Colors.ANSI_YELLOW +" - Status: {}, Total customers: {}"+ Colors.ANSI_RESET, status == 1, dao.select().size());

			Customer customer3 = dao.select(customer2.getCustomerID());
			log.info(Colors.ANSI_GREEN +"Reading customer with id {}"+ Colors.ANSI_RESET, customer1.getCustomerID());
			log.info(Colors.ANSI_YELLOW +" - Customer: {}"+ Colors.ANSI_RESET, customer3);

			customer3.setEmail("brucik@gmail.com");
			status = dao.update(customer3);
			log.info(Colors.ANSI_GREEN +"Updating customer: {}"+ Colors.ANSI_RESET, customer3);
			log.info(Colors.ANSI_YELLOW +" - Status: {}, Total customers: {}"+ Colors.ANSI_RESET, status == 1, dao.select().size());

			Customer customer4 = dao.select().stream().findFirst().orElse(customer1);
			status = dao.delete(customer4.getCustomerID());
			log.info(Colors.ANSI_GREEN +"Deleting customer with id {}"+ Colors.ANSI_RESET, customer4.getCustomerID());
			log.info(Colors.ANSI_YELLOW +" - Status: {}, Total customers: {}"+ Colors.ANSI_RESET, status == 1, dao.select().size());
		} catch (Exception e) {
			log.error("Connection error", e);
		}

		try {
			Collection<Reservation> reservations = Arrays.asList(
					Reservation.builder().customerID(1).tableID(1).start(LocalDateTime.now()).end(LocalDateTime.now().plusHours(1)).description("Dinner for two").build(),
					Reservation.builder().customerID(4).tableID(1).start(LocalDateTime.now()).end(LocalDateTime.now().plusHours(1)).amount(5).description("Vacation dinner").build(),
					Reservation.builder().customerID(2).tableID(2).start(LocalDateTime.now().plusDays(1)).end(LocalDateTime.now().plusDays(1).plusHours(2)).description("Lunch meeting").build(),
					Reservation.builder().customerID(1).tableID(3).start(LocalDateTime.now().plusDays(2)).end(LocalDateTime.now().plusDays(2).plusHours(3)).description("Family dinner").build(),
					Reservation.builder().customerID(8).tableID(1).start(LocalDateTime.now().plusDays(3)).end(LocalDateTime.now().plusDays(3).plusHours(1)).description("Quick lunch").build(),
					Reservation.builder().customerID(6).tableID(2).start(LocalDateTime.now().plusDays(4)).end(LocalDateTime.now().plusDays(4).plusHours(2)).description("Business dinner").build()
			);

			log.info("");
			log.info(Colors.ANSI_RED +"PROCEDURE - Creating new reservations" + Colors.ANSI_RESET);
			log.info("--------------------------------------------------------");

			ReservationDAO dao = new ReservationDAO();
			for (Reservation reservation : reservations) {
				String status = dao.createReservation(reservation);
				log.info(Colors.ANSI_GREEN +"Creating reservation: {}"+ Colors.ANSI_RESET, reservation);
				log.info(Colors.ANSI_YELLOW +" - Output: {}"+ Colors.ANSI_RESET, status);
			}

			log.info("--------------------------------------------------------");
		} catch (SQLException e) {
			log.error("Connection error", e);
		}
	}

	private static void loadProperties() {
		try (InputStream is = Program.class.getResourceAsStream("/application.properties")) {
			System.getProperties().load(is);
		} catch (IOException e) {
			log.error("loadProperties", e);
		}
	}
}
