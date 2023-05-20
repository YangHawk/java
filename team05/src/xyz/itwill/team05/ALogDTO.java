package xyz.itwill.team05;

/*

DESC ALOG;

이름         널?       유형           
---------- -------- ------------ 
LOGNO      NOT NULL NUMBER       
SNO                 NUMBER       
LOGTYPE             VARCHAR2(20) 
SNAME               VARCHAR2(20) 
LOGINTIME           TIMESTAMP(6) 
LOGOUTTIME          TIMESTAMP(6) 
STATUS              VARCHAR2(20) 

 */
public class ALogDTO {

	private int logNo;
	private int sNo;
	private String logType;
	private String sName;
	private String logInTime;
	private String logOutTime;
	private String status;

	public ALogDTO() {
		// TODO Auto-generated constructor stub
	}

	public ALogDTO(int logNo, int sNo, String logType, String logInTime, String logOutTime, String status,
			String sName) {
		super();
		this.logNo = logNo;
		this.sNo = sNo;
		this.logType = logType;
		this.logInTime = logInTime;
		this.logOutTime = logOutTime;
		this.status = status;
		this.sName = sName;
	}

	public int getLogNo() {
		return logNo;
	}

	public void setLogNo(int logNo) {
		this.logNo = logNo;
	}

	public int getsNo() {
		return sNo;
	}

	public void setsNo(int sNo) {
		this.sNo = sNo;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getLogInTime() {
		return logInTime;
	}

	public void setLogInTime(String logInTime) {
		this.logInTime = logInTime;
	}

	public String getLogOutTime() {
		return logOutTime;
	}

	public void setLogOutTime(String logOutTime) {
		this.logOutTime = logOutTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	@Override
	public String toString() {
		return logNo + "\t" + sNo + "\t" + logType + "\t" + sName + "\t" + logInTime + "\t" + logOutTime + "\t"
				+ status;
	}
}
