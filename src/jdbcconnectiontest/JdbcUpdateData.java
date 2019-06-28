package jdbcconnectiontest;

import java.awt.DisplayMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.MySQLConnection;

public class JdbcUpdateData {

	public static void main(String[] args) throws SQLException {
		Connection myCon=null;
		Statement stmt=null;
		ResultSet rs=null;
		String dburl="jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user="preeti";
		String pass="minshi";
		String sql1="select * from employees";
		String sqlupdate="update employees set email='preeti.verma@yahoo.com'where first_name='Preeti' and last_name='Verma'";
		try {
			
			//Get connection to database
			myCon=DriverManager.getConnection(dburl, user, pass);
			
			//Create statement
			stmt=myCon.createStatement();
			
			// Call helper method to display the employee's information
						System.out.println("BEFORE THE UPDATE...");
						displayEmployee(myCon, "John", "Doe");
			
						//Update employee
			int rowaffected=stmt.executeUpdate(sqlupdate);
			System.out.println(rowaffected);
			
			// Call helper method to display the employee's information
			System.out.println("AFTER THE UPDATE...");
			displayEmployee(myCon, "Preeti", "Verma");
			
		}catch(Exception e) {
			e.getMessage();
		}
		finally {
			close(myCon, stmt, rs);
		}
	}

	private static void displayEmployee(Connection con, String firstname, String lastname)  throws SQLException{
		
		PreparedStatement myStmt=null;
		ResultSet myRs=null;
		try {
			
	myStmt=	con.prepareStatement("select last_name,first_name,email from employees where last_name=? and first_name=?");
	myStmt.setString(1, "Verma");
	myStmt.setString(2, "Preeti");
	//myStmt.setString(1, lastname);
	//myStmt.setString(2, firstname);
	
	//execute sql query

	myRs=myStmt.executeQuery();
	
	//process resultset
	while(myRs.next())
	{
		String lastnm=myRs.getString("last_name");
		String fstnm=myRs.getString("first_name");
		String email=myRs.getString("email");
		System.out.println("Data is"+lastnm +" "+fstnm+" "+ email);
	}
		}
		catch(Exception e) {
			e.getMessage();
		}
		finally {
			close(myStmt, myRs);
		}
	}

	private static void close(PreparedStatement myStmt, ResultSet myRs) throws SQLException {
		
		close(null, myStmt, myRs);
	}

	private static void close(Connection con, Statement myStmt, ResultSet myRs) throws SQLException {
		
		if(con!=null) {
			con.close();
		}
		if(myStmt!=null) {
			myStmt.close();
		}
		if(myRs!=null) {
			myRs.close();
		}
	}
}
