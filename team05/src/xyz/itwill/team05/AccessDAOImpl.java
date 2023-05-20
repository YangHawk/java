package xyz.itwill.team05;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccessDAOImpl extends JdbcDAO implements AccessDAO, StudentDAO {
	private static AccessDAOImpl _dao;

	public AccessDAOImpl() {
	}

	static {
		_dao = new AccessDAOImpl();
	}

	public static AccessDAOImpl getDAO() {
		return _dao;
	}

	@Override
	public StudentDTO login(String email, String phone) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentDTO student = null;

		try {
			con = getConnection();

			String sql = "select * from student where email = ? and substr(phone,10,4) = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, phone);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				student = new StudentDTO();

				student.setNo(rs.getInt("no"));
				student.setName(rs.getString("name"));
				student.setEmail(rs.getString("email"));
				student.setPhone(rs.getString("phone"));
				student.setAddress(rs.getString("address"));
			} else {
				System.out.println("아이디/비밀번호가 올바르지 않습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return student;
	}

	// 학생 입실
	@Override
	public int insertALog(StudentDTO student) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();

			String sql = "insert into alog(logno, sno, logtype, logintime) values(logno_seq.nextval ,?, '입실', sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student.getNo());

			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]insertALog() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt);

		}
		return rows;
	}

	// 학생 퇴실
	@Override
	public int updateALog(StudentDTO student) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;

		try {
			con = getConnection();

			String sql = "update alog set logtype = '퇴실', logouttime = sysdate WHERE sno = ? and trunc(logintime, 'DD') = trunc(sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student.getNo());

			rows = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("[에러]updateALog() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt);

		}
		return rows;
	}

	@Override
	public List<ALogDTO> showALog(StudentDTO student) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ALogDTO> aLog = new ArrayList<>();
		try {
			con = getConnection();

			String sql = "select * from alog where sno =? order by logintime";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student.getNo());

			rs = pstmt.executeQuery();

			while (rs.next()) {

				ALogDTO myALog = new ALogDTO();
				myALog.setLogNo(rs.getInt("logNo"));
				myALog.setsNo(rs.getInt("sNo"));
				myALog.setLogType(rs.getString("logType"));
				myALog.setLogInTime(rs.getString("logInTime"));
				myALog.setLogOutTime(rs.getString("logOutTime"));
				myALog.setStatus(rs.getString("status"));
				myALog.setsName(rs.getString("sName"));

				aLog.add(myALog);
			}

		} catch (SQLException e) {
			System.out.println("[에러]showALog() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return aLog;
	}

	@Override
	public boolean checkIn(StudentDTO student, LocalDate currentDate) {

		boolean checkIn = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();

			String sql = "select count(*) from alog where sno = ? and trunc(logInTime) = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student.getNo());
			pstmt.setDate(2, Date.valueOf(currentDate));

			rs = pstmt.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				checkIn = count > 0;
			}

		} catch (SQLException e) {
			System.out.println("[에러]checkIn() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}

		return checkIn;

	}

	@Override
	public boolean checkOut(StudentDTO student, LocalDate currentDate) {
		boolean checkOut = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();

			String sql = "select count(*) from alog where sno = ? and logType = '퇴실' and trunc(logInTime) = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student.getNo());
			pstmt.setDate(2, Date.valueOf(currentDate));

			rs = pstmt.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				checkOut = count > 0;
			}

		} catch (SQLException e) {
			System.out.println("[에러]checkOut() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}

		return checkOut;
	}

	@Override
	public int updateStatusNormal(StudentDTO student) {

		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();
			String sql = "update alog set status = '정상' where sno = ? and trunc(logintime) = trunc(sysdate) and logintime <= TO_DATE('09:30', 'HH24:MI') and logouttime >= TO_DATE('18:30', 'HH24:MI')";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student.getNo());

			rows = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("[에러]updateStatusNormal() 메소드의 SQL 오류 = " + e.getMessage());

		} finally {

		}
		return rows;
	}

	@Override
	public int updateStatusLate(StudentDTO student) {

		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();
			String sql = "update alog set status = '지각' where sno = ? and trunc(logintime) = trunc(sysdate) and logintime > TO_DATE('09:30', 'HH24:MI') and logouttime >= TO_DATE('18:30', 'HH24:MI')";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student.getNo());

			rows = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("[에러]updateStatusLate() 메소드의 SQL 오류 = " + e.getMessage());

		} finally {

		}
		return rows;
	}

	@Override
	public int updateStatusEarlyLeave(StudentDTO student) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();
			String sql = "update alog set status = '조퇴' where sno = ? and trunc(logintime) = trunc(sysdate) and logintime <= TO_DATE('09:30', 'HH24:MI') and logouttime < TO_DATE('18:30', 'HH24:MI')";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student.getNo());

			rows = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("[에러]updateStatusEarlyLeave() 메소드의 SQL 오류 = " + e.getMessage());

		} finally {

		}
		return rows;
	}

	@Override
	public int updateStatusAbsent(StudentDTO student) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();
			String sql = "update alog set status = '결석' where sno = ? and trunc(logintime) = trunc(sysdate) and logintime > TO_DATE('09:30', 'HH24:MI') and (logouttime < TO_DATE('18:30', 'HH24:MI') OR logouttime IS NULL)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student.getNo());

			rows = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("[에러]updateStatusAbsent() 메소드의 SQL 오류 = " + e.getMessage());

		} finally {

		}
		return rows;
	}

	@Override
	public int insertStatusAbsent(StudentDTO student) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    int rows = 0;
	    
	    try {
	        con = getConnection();

	        // 마지막 출석일을 확인하는 select 명령
	        String sql1 = "SELECT * FROM (SELECT * FROM alog WHERE sno = ? ORDER BY logintime DESC) WHERE ROWNUM = 1";
	        pstmt = con.prepareStatement(sql1);
	        pstmt.setInt(1, student.getNo());

	        rs = pstmt.executeQuery();

	        LocalDate localLastDate = null;
	        if (rs.next()) {
	            localLastDate = rs.getDate("logintime").toLocalDate();
	        } else {
	          System.out.println("오늘이 첫 출석입니다.");
	        }

	        LocalDate currentDate = LocalDate.now();

	        while (localLastDate != null && localLastDate.isBefore(currentDate)) {
	            String abscentDate = localLastDate.plusDays(1).toString();
	            String sql2 = "INSERT INTO alog(logno, sno, logtype, logouttime, status) VALUES (logno_seq.nextval, ?, '퇴실', ?, '결석')";
	            pstmt = con.prepareStatement(sql2);
	            pstmt.setInt(1, student.getNo());
	            pstmt.setString(2, abscentDate);
	            
	            // 명령 실행
	            rows += pstmt.executeUpdate();
	            
	            // localLastDate를 1일 추가하여 다음 날짜로 설정
	            localLastDate = localLastDate.plusDays(1);
	        }
	    } catch (SQLException e) {
	        System.out.println("[에러] insertStatusAbsent() 메소드의 SQL 오류 = " + e.getMessage());
	    } finally {
	        close(con, pstmt, rs);
	    }

	    return rows;
	}

}
