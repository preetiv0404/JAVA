package jdbcconnectiontest;

import java.sql.*;

public class JbcConnectionTest {
	
	public static void main(String args[]) throws SQLException
	{
		Connection myCon =null;
		Statement stmt=null;
		ResultSet rs=null;
		String dburl="jdbc:mysql://localhost:3306/demo?useSSL=false";
		//String dburl="jdbc:mysql://localhost:3306/demo?useUnicode=yes&characterEncoding=UTF-8";
		String user="preeti";
		String pass="minshi";
		String sql="select * from employees";
		try {
		//get connection to database
			
			myCon =DriverManager.getConnection(dburl, user, pass);
			
			//create statement
			stmt=myCon.createStatement();
			//execute sql query
		rs=	stmt.executeQuery(sql);
		while(rs.next())
		{
			System.out.println(rs.getString("first_name")+ "  "+rs.getString("last_name"));
		}
	}
catch(Exception e) {
	System.out.println(e.getMessage());
}
		finally{
			
			if(rs!=null) {
				rs.close();
			}
			if(stmt!=null) {
				stmt.close();
			}
			if(myCon!=null) {
				myCon.close();
			}	
		}
}
}