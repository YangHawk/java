package xyz.itwill.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xyz.itwill.dto.StudentDTO;

//STUDENT 테이블에 행(학생 정보) 삽입, 변경, 삭제, 검색 기능을 제공하는 클래스
public class StudentDAO extends JdbcDAO {
	private static StudentDAO _dao;

	public StudentDAO() {
		// TODO Auto-generated constructor stub
	}

	static {
		_dao = new StudentDAO();
	}

	public static StudentDAO getDAO() {
		return _dao;
	}

	// 학생 정보를 전달받아 STUDENT 테이블에 삽입하고 삽입행의 갯수를 반환하는 메소드
	public int insertStudent(StudentDTO student) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;

		try {
			con = getConnection();

			String sql = "insert into student values(?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student.getNo());
			pstmt.setString(2, student.getName());
			pstmt.setString(3, student.getEmail());
			pstmt.setString(4, student.getPhone());
			pstmt.setString(5, student.getAddress());

			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]insertStudent() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt);
		}

		return rows;
	}

	// 학생 정보를 전달받아 STUDENT 테이블에 저장된 학생 정보를 변경하고 변경행의 갯수를 반환하는 메소드(학번 기준)
	public int updateStudent(StudentDTO student) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;

		try {
			con = getConnection();

			String sql = "update student set name=?, email=?, phone=?, address=? where no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student.getName());
			pstmt.setString(2, student.getEmail());
			pstmt.setString(3, student.getPhone());
			pstmt.setString(4, student.getAddress());
			pstmt.setInt(5, student.getNo());

			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]updateStudent() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt);
		}

		return rows;
	}

	// 학번을 전달받아 STUDENT 테이블에 저장된 학생 정보를 삭제하고 삭제행의 갯수를 반환하는 메소드
	public int deleteStudent(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;

		try {
			con = getConnection();

			String sql = "delete from student where no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);

			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]deleteStudent() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt);
		}

		return rows;
	}

	// 학번을 전달받아 STUDENT 테이블에 저장된 학생 정보를 검색하여 DTO 객체로 반환하는 메소드
	public StudentDTO selectStudent(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentDTO student = null;

		try {
			con = getConnection();

			String sql = "select * from student where no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				student = new StudentDTO();
				student.setNo(rs.getInt("no"));
				student.setName(rs.getString("name"));
				student.setEmail(rs.getString("email"));
				student.setPhone(rs.getString("phone"));
				student.setAddress(rs.getString("address"));
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectStudent() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}

		return student;
	}

	// STUDENT 테이블에 저장된 모든 학생 정보를 검색하여 List 객체로 반환하는 메소드
	public List<StudentDTO> selectStudentList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<StudentDTO> studentList = new ArrayList<>();

		try {
			con = getConnection();

			String sql = "select * from student order by no";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				StudentDTO student = new StudentDTO();
				student.setNo(rs.getInt("no"));
				student.setName(rs.getString("name"));
				student.setEmail(rs.getString("email"));
				student.setPhone(rs.getString("phone"));
				student.setAddress(rs.getString("address"));

				studentList.add(student);
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectStudentList() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}

		return studentList;
	}
}
