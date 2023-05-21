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

	// 입력한 ID와 비밀번호를 전달받아 선생 또는 학생으로 로그인할 수 있는 메소드
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
				System.out.println("아이디 또는 비밀번호가 올바르지 않습니다.");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return student;
	}

	// 로그인한 학생 정보를 전달받아 ALOG 테이블에 행을 삽입하고 입실 처리하는 메소드
	@Override
	public int insertALog(StudentDTO student) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();

			String sql = "insert into alog(sno, logtype, sname, logintime) values(?, '입실', ?, sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student.getNo());
			pstmt.setString(2, student.getName());

			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]insertALog() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt);

		}
		return rows;
	}

	// 로그인한 학생 정보를 전달받아 ALOG 테이블에 행을 변경하고 퇴실 처리하는 메소드
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

	// 로그인한 학생 정보를 전달받아 ALOG 테이블에서 해당 학생의 행을 반환받는 메소드
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

	// 로그인한 학생 정보와 입실한 날짜를 전달받아 재입실을 방지하는 메소드
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

	// 로그인한 학생 정보와 입실한 날짜를 전달받아 재퇴실을 방지하는 메소드
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

	// 로그인한 학생 정보의 입실 퇴실 시간을 전달받아 ALOG 테이블에 행을 변경(출결 상태를 정상 처리)하는 메소드
	// 입실 시간: 9시 30분 이전 + 퇴실 시간: 6시 30분 이후
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
			close(con, pstmt);

		}
		return rows;
	}

	// 로그인한 학생 정보의 입실 퇴실 시간을 전달받아 ALOG 테이블에 행을 변경(출결 상태를 지각 처리)하는 메소드
	// 입실 시간: 9시 30분 이후 + 퇴실 시간: 6시 30분 이후
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
			close(con, pstmt);

		}
		return rows;
	}

	// 로그인한 학생 정보의 입실 퇴실 시간을 전달받아 ALOG 테이블에 행을 변경(출결 상태를 조퇴 처리)하는 메소드
	// 입실 시간: 9시 30분 이전 + 퇴실 시간: 6시 30분 이전
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
			close(con, pstmt);

		}
		return rows;
	}

	
	// 로그인한 학생 정보의 입실 퇴실 시간을 전달받아 ALOG 테이블에 행을 변경(출결 상태를 결석 처리)하는 메소드
	// 입실 시간: 9시 30분 이후 + 퇴실 시간: 6시 30분 이전
	@Override
	public int updateStatusAbsent(StudentDTO student) {// 퇴실 처리할 때 결석
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();
			String sql = "update alog set status = '결석' where sno = ? and trunc(logintime) = trunc(sysdate) and logintime > TO_DATE('09:30', 'HH24:MI') and logouttime < TO_DATE('18:30', 'HH24:MI')";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student.getNo());

			rows = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("[에러]updateStatusAbsent() 메소드의 SQL 오류 = " + e.getMessage());

		} finally {
			close(con, pstmt);

		}
		return rows;
	}

	// 로그인한 학생 정보의 입실 퇴실 시간을 전달받아 ALOG 테이블에 행을 변경(출결 상태를 정상으로 처리)하는 메소드
	// 입실후에 퇴실을 하지않음
	@Override
	public int updateStatusAbsent2(StudentDTO student) {// 입실 처리할 때 전날 결석
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();
			String sql = "update alog set status = '결석', logtype = '퇴실', logouttime = logintime where sno = ? and trunc(logintime) != trunc(sysdate) and logouttime is null";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student.getNo());

			rows = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("[에러]updateStatusAbsent() 메소드의 SQL 오류 = " + e.getMessage());

		} finally {
			close(con, pstmt);

		}
		return rows;
	}

	// 로그인한 학생이 정보를 전달받고 해당하는 학생이 마지막으로 출석한 날짜와 현재 입실한 날짜의 기간을 검색하여 존재하지 않다면 ALOG
	// 테이블에 행을 삽입하고 행들의 출결 상태를 결석으로 처리하는 메소드
	// 특정 날짜에 행이 없다면 결석 처리된 행을 삽입
	@Override
	public int insertStatusAbsent(StudentDTO student) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int rows = 0;

		try {
			con = getConnection();

			// 마지막 출석일을 확인하는 select 명령
			String sql1 = "select * from (select * from alog where sno = ? order by logintime desc) where rownum = 1";
			pstmt = con.prepareStatement(sql1);
			pstmt.setInt(1, student.getNo());

			rs = pstmt.executeQuery();

			LocalDate localLastDate = null;
			if (rs.next()) {
				localLastDate = rs.getDate("logintime").toLocalDate();
			} else {
				System.out.println("축하합니다! 처음으로 출석하셨습니다.");
				return rows;
			}

			LocalDate currentDate = LocalDate.now();

			while (localLastDate.isBefore(currentDate.minusDays(1))) {
				String absenceDate = localLastDate.plusDays(1).toString();
				String sql2 = "insert into alog(sno, logtype, sname, logintime, logouttime, status) VALUES (?, '퇴실', ?, ?, ?, '결석')";
				pstmt = con.prepareStatement(sql2);
				pstmt.setInt(1, student.getNo());
				pstmt.setString(2, student.getName());
				pstmt.setString(3, absenceDate);
				pstmt.setString(4, absenceDate);

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
