package xyz.itwill.team05;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

public class JdbcDAO {

	private static PoolDataSource pds;

	static {
		pds = PoolDataSourceFactory.getPoolDataSource();
		try {
			pds.setConnectionFactoryClassName("oracle.jdbc.driver.OracleDriver");
			pds.setURL("jdbc:oracle:thin:@localhost:1521:xe");
			pds.setUser("scott");
			pds.setPassword("tiger");
			pds.setInitialPoolSize(10);
			pds.setMaxPoolSize(15);
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}

	public Connection getConnection() {
		Connection con = null;
		try {
			con = pds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public void close(Connection con) {
		try {
			if (con != null)
				con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close(Connection con, PreparedStatement pstmt) {
		try {
			if (pstmt != null)
				pstmt.close();

			if (con != null)
				con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();

			if (con != null)
				con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
