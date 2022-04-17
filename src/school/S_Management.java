package school;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class S_Management {

	public static String id;
	public static String url = "jdbc:mariadb://localhost:3306/school";
	public static Connection con;
	public Statement st;
	public ResultSet rs;
	String leName, leNum, semester, prName, room, type, time, preLecture, division, state;
	int credit, attMem, limMem, limGrade;

	// ********************************************************����***************************************************

	public S_Management(String s, Connection con) throws SQLException, ClassNotFoundException {

		con = DriverManager.getConnection(s, "root", "1234");
		st = con.createStatement();
	}

	// ����
	// ���� ���� �޾ƿ���
	public ResultSet getLecture() throws SQLException {
		// �Է��� index���� ���� ������ ���̴� ��ɾ� ����
		ResultSet rs = st.executeQuery("select*from lecture;");

		return rs;
	}

	// ��� Ŭ���� �����ϸ鼭 �й��� �Է��ϸ� �ش� �л��� �̸��� ��ȯ�ϵ��� ��
	
	public String getstName(String id) throws Exception {
		String stName = null;
		ResultSet rs = st.executeQuery("select*from student where stNum = '" + id + "';");
		while (rs.next()) {
			stName = rs.getString(1);
		}

		return stName;
	}

	// ���� ��û �ϱ�
	// ���� - �����ؼ� ������ �г� �´���, [���� ���� ������� Ȯ���ϱ�]
	// S_ApplicationClass
	public void applicationClass(String stNum, Lecture le, String stName) throws SQLException {
		// �ش� �б⿡ �ش� ������ ��ٱ��Ͽ� �־���Ҵ��� Ȯ���ϱ�
		// ��ٱ��Ͽ� �־�������� true, ��ٱ��Ͽ� �־���� �ʾҴٸ� false
		if (isBucket(stNum, le)) {
			// �ش� ������ ã�Ƽ� state�� �����ϱ�
			String sql = "update student" + stNum + " set State = 1 where lecNum = '" + le.getleNum() + "';";
			// ��ɾ� ����
			st.executeUpdate(sql);
		} else {
			// ���������� �ش� �л��� ������ ���̺� �Է��ϱ�
			// ���� ��û�� ������ State = 1�� ����
			String sql = "insert into student" + stNum + " values ('" + le.getleName() + "','" + le.getleNum() + "',"
					+ "'" + le.getDivision() + "','" + le.getCredit() + "', '" + le.getTime() + "'," + "'"
					+ le.getprName() + "',NULL,NULL,'" + le.getSemester() + "', 1);";
			// ��ɾ� ����
			st.executeUpdate(sql);
		}

		// ������ table�� �Է�
		String sql1 = "insert into lenum values ('" + stNum + "', '" + stName + "', '" + le.getleName() + "', '"
				+ le.getleNum() + "', '" + le.getDivision() + "', " + "" + le.getCredit()
				+ ", NULL, NULL, NULL, NULL, NULL, NULL, '" + le.getprName() + "', '" + le.getTime() + "', '"
				+ le.getLimGrade() + "', '" + le.getSemester() + "');";
		// values ('"+stName+"', '"+stNum+"',
		// ��ɾ� ����
		st.executeUpdate(sql1);

		// ��ü ���� ������ �ִ� ��û �л� �� �Ѹ� ���̱�
		String sql2 = "update lecture set AttMem = " + (le.getAttMem() + 1) + " where Number = '" + le.getleNum()
				+ "';";

		// ��ɾ� ����
		st.executeUpdate(sql2);

	}

	// ���� ��û �ϴ� ������ �̹� ��ٱ��� ������ �ִ��� Ȯ���ϱ�
	public boolean isBucket(String stNum, Lecture le) throws SQLException {
		int result = 0;
		// �ش� �б⿡ �ش� �ð��� ���Ǹ� ��ٱ��Ͽ� �־����� Ȯ��
		ResultSet rs = st.executeQuery("select EXISTS (select * from student" + stNum + " WHERE semester = '"
				+ le.getSemester() + "' AND State = 0 AND lecNum = '" + le.getleNum() + "') as success;");

		while (rs.next()) {
			result = rs.getInt(1);
		}

		// �ش� ������ �����Ѵٸ�!
		if (result == 1) {
			return true;
		} else {
			// �ش� ������ �������� �ʴ´ٸ�!
			return false;
		}
	}

	// ������û ������ �ߺ� ����
	// S_ApplicationClass
	// S_StuClasses
	public int isClass(String stNum, Lecture le) throws SQLException {
		int result = 0;
		// �ش� �б⿡ ���� ��û�� ���� ���̱�
		// �ش� �б⿡ �ش� �ð��� ���Ǹ� ��� ������ �ִ��� Ȯ��
		ResultSet rs = st.executeQuery("select EXISTS (select * from student" + stNum + " WHERE semester = '"
				+ le.getSemester() + "' AND time = '" + le.getTime() + "' AND State = 1) as success;");

		while (rs.next()) {
			result = rs.getInt(1);
		}

		return result;
	}

	// �м� ��ȣ �Է��ϸ� �ش� ������ ������
	// S_ShowAllClasses
	// S_ShowAllGrade
	public Lecture getLe(String leNum) throws SQLException {
		// �Է��� �м���ȣ�� ���� ���� ���� �ҷ�����
		ResultSet rs = st.executeQuery("select*from lecture where Number = '" + leNum + "';");
		while (rs.next()) {
			// ���� �޼ҵ� ���� �޾ƿͼ� �Ҵ���.
			leName = rs.getString(1);
			leNum = rs.getString(2);
			semester = rs.getString(3);
			limGrade = rs.getInt(4);
			prName = rs.getString(5);
			type = rs.getString(6);
			time = rs.getString(7);
			credit = rs.getInt(8);
			attMem = rs.getInt(9);
			limMem = rs.getInt(10);
			preLecture = rs.getString(11);
			division = rs.getString(12);
		}
		// ���� ��ü ����
		Lecture le = new Lecture(leName, leNum, semester, limGrade, prName, type, time, credit, attMem, limMem,
				preLecture, division, state);

		return le;
	}

	// �й� �Է��ϸ� �ش� �б� �� �̼� ���� ������
	// S_ShowAllGrade
	// S_StuClasses
	// S_ApplicationClass
	public int getStudentTotalCredit(String stNum, String semester) throws SQLException {
		int TotalCredit = 0;
		// �л� ���� ���̺��� �Է��� �м���ȣ�� ���� ���� ���� �ҷ�����
		ResultSet rs = st
				.executeQuery("select*from student" + stNum + " WHERE semester = '" + semester + "' AND State = 1;");
		while (rs.next()) {
			credit = rs.getInt(4);
			TotalCredit += credit;
		}
		return TotalCredit;
	}

	// �ش� �б⿡ ������û�� ���񰹼� ������
	// S_ApplicationClass
	public int getStudentTotalCount(String stNum, String semester) throws SQLException {
		// �л� ���� ���̺��� �Է��� �м���ȣ�� ���� ���� ���� �ҷ�����
		ResultSet rs = st.executeQuery(
				"select count(*)from student" + stNum + " WHERE Semester = '" + semester + "' AND State = 1;");
		rs.next();
		// �� ���� �о num ������ ����
		int num = rs.getInt(1);
		return num;
	}

	// ��ٱ��Ͽ��� ���� ���� �޾ƿ���
	// S_ApplicationClass
	// S_Bucket
	public ResultSet bucketLecture(String classification, String major, String queary, String which, String semester)
			throws SQLException {
		if (queary.equals("")) {
			rs = st.executeQuery("select*from lecture where Division = '" + classification + "' and Number like '"
					+ major + "%' and Semester = '" + semester + "';");
		}
		// ù��° ���� - �˻��ϴ� ������ ���������� �м���ȣ���� �˷���, �ι��� ���� - �˻��ϴ� ����
		else if (which.equals("�����")) {
			// ��������� ���
			rs = st.executeQuery("select*from lecture where Name = '" + queary + "' and Division = '" + classification
					+ "' and Number like '" + major + "%' and Semester = '" + semester + "';");
		} else {
			// �м���ȣ�� ���
			rs = st.executeQuery("select*from lecture where Division = '" + classification + "' and Number = '" + queary
					+ "' and Semester = '" + semester + "';");
		}
		return rs;
	}

	// ��ٱ��Ͽ� ���� ���� ������
	// S_Bucket
	public int getStudentBucketCredit(String stNum, String semester) throws SQLException {
		int BucketCredit = 0;
		// �л� ���� ���̺��� �Է��� �м���ȣ�� ���� ���� ���� �ҷ�����
		ResultSet rs = st
				.executeQuery("select*from student" + stNum + " WHERE Semester = '" + semester + "' AND State = 0;");
		while (rs.next()) {
			credit = rs.getInt(4);
			BucketCredit += credit;
		}
		return BucketCredit;
	}

	// ��ٱ��Ͽ� ���� ���񰹼� ������
	// S_Bucket
	public int getStudentBucketCount(String stNum, String semester) throws SQLException {
		// �л� ���� ���̺��� �Է��� �м���ȣ�� ���� ���� ���� �ҷ�����
		ResultSet rs = st.executeQuery(
				"select count(*)from student" + stNum + " WHERE Semester = '" + semester + "' AND State = 0;");
		rs.next();
		// �� ���� �о num ������ ����
		int num = rs.getInt(1);
		return num;
	}

	// ���� ��ȸ�ϴ� ���
	// ��ü �������� �ش� �б⿡ �ش� �Ǵ� ���� �ҷ�����
	// S_ShowAllClasses
	public ResultSet getClasses(String stNum, String semester) throws SQLException {
		// ��ü ���� ���̺��� �Է��� �б� ������ ���� ���� ���� �ҷ�����
		ResultSet rs = st.executeQuery("select*from Lecture WHERE semester = '" + semester + "';");
		return rs;
	}

	// �ش� �л��� �ش� �б⿡ ������ ���� �ҷ�����
	// S_ShowAllClasses
	// S_ShowAllGrade
	// S_StuClasses
	public ResultSet getStClasses(String stNum, String semester) throws SQLException {
		// �л� ���� ���̺��� �Է��� �б� ������ ���� ���� ���� �ҷ�����
		ResultSet rs = st
				.executeQuery("select*from student" + stNum + " WHERE semester = '" + semester + "' AND state = 1;");
		return rs;
	}

	// ��ٱ��� ��� - ��ٱ��Ͽ� �ִ��� ���� �� �ִ� ���� : 25���� �� �ɷ� �ϱ�
	// �л� ���� table�� �Է��ϱ� -> State = 0�� ���·� �Է��ϱ�.
	// S_Bucket
	public void putBucket(String stNum, String semester, Lecture le) throws SQLException {
		// �л� ���� ���̺� �ʵ尪 �ֱ�
		// ���� ��û�� ������ State = 1�� ����
		String sql = "insert into student" + stNum + " values ('" + le.getleName() + "','" + le.getleNum() + "'," + "'"
				+ le.getType() + "','" + le.getCredit() + "', '" + le.getTime() + "'," + "'" + le.getprName()
				+ "',NULL,NULL,'" + le.getSemester() + "', 0);";
		// ��ɾ� ����
		st.executeUpdate(sql);
	}

	// ��ٱ��� ���� �ҷ�����
	// S_Bucket
	public ResultSet getBucket(String stNum, String semester) throws SQLException {
		// �л� ���� ���̺� �л��� �ش� �б⸦ ���� ��ٱ��� ���� �� ��� ����
		// 1. �ش� �л� ��ٱ��� �������� �м���ȣ�� ��� ����
		ResultSet rs = st
				.executeQuery("select*from student" + stNum + " where Semester = '" + semester + "' AND state = 0;");

		return rs;
	}

	// ������û ��� �ϱ�
	// S_ApplicationClass
	public void removeLe(String stNum, Lecture le) throws SQLException {
		// ����ϱ� ����
		// �л� ���� ���̺� �ش� �м� ��ȣ�� ���� ������ ã�� ����
		String sql = "delete from student" + stNum + " where leNum = '" + le.getleNum() + "' AND state = 1;";
		// ��ɾ� ����
		st.executeUpdate(sql);

		// ��ü ���� ������ �ִ� ��û �л� �� �Ѹ� ���̱�
		String sql2 = "update lecture set attMem = " + (le.getAttMem() - 1) + " where leNum = '" + le.getleNum() + "';";

		// ��ɾ� ����
		st.executeUpdate(sql2);
	}

	// ��ٱ��� ��� �ϱ�
	// S_Bucket
	public void removeBucket(String stNum, String leNum) throws SQLException {
		// ����ϱ� ����
		// �л� ���� ���̺��� �ش� �м� ��ȣ�� ���� ������ ã�� ����
		String sql = "delete from student" + stNum + " where leNum = '" + leNum + "' AND state = 0;";
		// ��ɾ� ����
		st.executeUpdate(sql);
	}

	// S_Graduation
	// �л��� ���� ���� ���� ��ȯ�ϴ� �Լ�
	public int getTotalCredit(String StuNum, String which) throws SQLException {
		int credit = 0;
		int totalcredit = 0;
		if (which.contentEquals("������")) {
			ResultSet rs = st.executeQuery("select*from lenum WHERE stNum = '" + StuNum + "';");
			while (rs.next()) {
				credit = rs.getInt(6);
				totalcredit += credit;
			}
		} else if (which.contentEquals("�����ʼ�")) {
			ResultSet rs = st
					.executeQuery("select*from lenum WHERE stNum = '" + StuNum + "' AND division = '" + which + "';");
			while (rs.next()) {
				credit = rs.getInt(6);
				totalcredit += credit;
			}
		} else if (which.contentEquals("���缱��")) {
			ResultSet rs = st
					.executeQuery("select*from lenum WHERE stNum = '" + StuNum + "' AND division = '" + which + "';");
			while (rs.next()) {
				credit = rs.getInt(6);
				totalcredit += credit;
			}
		} else if (which.contentEquals("�����ʼ�")) {
			ResultSet rs = st
					.executeQuery("select*from lenum WHERE stNum = '" + StuNum + "' AND division = '" + which + "';");
			while (rs.next()) {
				credit = rs.getInt(6);
				totalcredit += credit;
			}
		} else if (which.contentEquals("��������")) {
			ResultSet rs = st
					.executeQuery("select*from lenum WHERE stNum = '" + StuNum + "' AND division = '" + which + "';");
			while (rs.next()) {
				credit = rs.getInt(6);
				totalcredit += credit;
			}
		}
		return totalcredit;
	}

	// S_Graduation
	// �л��� ���� �������� �� ������ ����ϴ� �Լ�
	public float getTotalGrade(String StuNum, String which) throws SQLException {
		float grade = 0;
		if (which.contentEquals("����")) {
			ResultSet rs = st.executeQuery("select*from student WHERE stNum = '" + StuNum + "';");
			while (rs.next()) {
				grade = rs.getFloat(13);
			}
		} else {
			ResultSet rs = st.executeQuery("select*from student WHERE stNum = '" + StuNum + "';");
			while (rs.next()) {
				grade = rs.getFloat(15);
			}
		}
		return grade;
	}

	// S_GraduationCheck
	// S_showAllGrade
	public Student getStudent(String StuNum) throws SQLException {
		Student s = new Student();

		ResultSet rs = st.executeQuery("select*from student WHERE stNum = '" + StuNum + "' ;");
		while (rs.next()) {
			s.setstName(rs.getString(1));
			s.setstNum(rs.getString(2));
			s.setGrade(rs.getInt(3));
			s.setBirth(rs.getString(5));
			s.setfMajor(rs.getString(6));
			s.setCollege(rs.getString("college"));
			s.setfMajor(rs.getString("fMajor"));
			s.setPhoneNum(rs.getString(10));
			s.setEmail(rs.getString(11));
		}

		return s;
	}

	// S_showAllGrade
	public void setstScore(String stNum,  double allScoreAvg) throws SQLException {
		int cnt = getAllSubCnt(stNum);
		float totalMajor = getAllSoreMajor(stNum)/cnt;
		st.executeQuery("update student set totalMajor = "+totalMajor+", totalFinish = "+allScoreAvg+" WHERE stNum = '" + stNum + "' ;");
	}


	// S_showAllGrade
	// ���� �� ���� ���ϱ�
	public float getAllSoreMajor(String stNum) throws SQLException {
		rs = st.executeQuery("select sum(scoreCredit) from leNum where stNum ='" + stNum + "' AND division = '�����ʼ�';");
		rs.next();

		float allScoreSum1 = rs.getFloat(1);
		
		rs = st.executeQuery("select sum(scoreCredit) from leNum where stNum ='" + stNum + "' AND division = '��������';");
		rs.next();
		
		float allScoreSum2 = rs.getFloat(1);
		
		return allScoreSum1 + allScoreSum2;
	}
   
   //********************************************************�ƿ�***************************************************
   
   //**ȸ������
   //�й� �ߺ� Ȯ��
	public boolean checkId(String id) {
 			// �Ķ���ͷ� �޾ƿ� id�� �˻��Ѵ�.
 		try {
 			rs = st.executeQuery("select * from student where stNum ='" + id + "';");

 			while (rs.next()) {
 				if (rs.getString("stNum").equals(id))// ��ġ�ϴ� �̸��� �ִٸ�
 					return true;// true ��ȯ
 			}
 			
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
		return false;
 	}
 	
	
 	//��ȭ��ȣ �ߺ� Ȯ�� �޼ҵ�
 	public boolean checkPhoneNum(String phoneNum) {
 		try {
 			// �Ķ���ͷ� �޾ƿ� id�� �˻��Ѵ�.
 			rs = st.executeQuery("select * from student where phoneNum= '" + phoneNum + "';");
 			while (rs.next()) {
 				if (rs.getString("phoneNum").equals(phoneNum))// ��ġ�ϴ� �̸��� �ִٸ�
 					return true;// true ��ȯ
 			}
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
 		return false;
 	}
 	
 	//���� ��� �޼ҵ�
 	public void add(String stNum, String pw, String birth, String email, String phoneNum)throws Exception{ 
 		try {
 			// �޾ƿ� Account ��ü P�� db�� �߰��� �ش�.
 			rs = st.executeQuery("update student set pw = '" + pw + "', birth = '" + birth + "', email = '" + email + 
 					"', phoneNum = '" + phoneNum + "' where stNum= '" + stNum +"';");

 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
 	}
 	
 	
 	//**ID,PW ã��
 	//ID ã��
	public String findId(String email, String phoneNum) throws Exception {
		
		rs = st.executeQuery("select *from student where email ='" + email + "'and phoneNum='" + phoneNum + "';");
		while (rs.next()) {
			if (rs.getString("email").equals(email) && rs.getString("phoneNum").equals(phoneNum))// ��ġ�ϴ� ���̵�(�й�)�� �ִٸ�
				return rs.getString(2);// id ��ȯ
		}
		return null;// ��ġ�ϴ� ���� ����
	}
	
 	//PW ã��
	public String findPw(String id, String email, String phoneNum) throws Exception {
		
		rs = st.executeQuery("select *from student where stNum = '" + id + "' and email ='" + email + "'and phoneNum='" + phoneNum + "';");
		while (rs.next()) {
			if (rs.getString("stNum").equals(id) && rs.getString("email").equals(email) && rs.getString("phoneNum").equals(phoneNum))// ��ġ�ϴ� ���̵�(�й�)�� �ִٸ�
				return rs.getString(4);// pw ��ȯ
		}
		return null;// ��ġ�ϴ� ���� ����
	}
 	
 	
 	//**�л� ����
    //���� ����
    public void editInfo(String stNum, String phoneNum, String email) throws Exception {
 	   try {
 		   rs = st.executeQuery("update student set phoneNum = '" + phoneNum + "', email = '" + email + "' where stNum= '" + stNum + "';");
 	   } catch (SQLException e) {
           e.printStackTrace();
        }
 	   
    }
 	
 	
 	//*******��û
 	// ���� ��� �޼ҵ�
    public void addLeave(String stName, String stNum, String apSemester, String startDate, String endDate, 
 		   String apReason, int abSemester, String apCount)throws Exception{ 
       try {
          // �޾ƿ� �κ�����û ������ DB�� ����
          rs = st.executeQuery("insert into schoolApplication values('"
                + stName + "','" + stNum + "','" + apSemester + "', '" + "����" + "','" + startDate + "','" 
                + endDate + "','" + apReason + "','" + abSemester + "','" + apCount + "', '" + "�̽���" + "');");
       } catch (SQLException e) {
          e.printStackTrace();
       }
    }
    
    // ���� ��� �޼ҵ�
    //S_Bokhak
    public void addGoBack(String stName, String stNum, String apSemester, String startDate, String endDate, String apReason, int abSemester, String apCount)throws Exception{ 
       try {
          // �޾ƿ� �κ�����û ������ DB�� ����
          rs = st.executeQuery("insert into schoolApplication values('"
                  + stName + "','" + stNum + "','" + apSemester + "', '" + "����" + "','" + startDate + "','" 
                  + endDate + "','" + apReason + "','" + abSemester + "','" + apCount + "', '" + "�̽���" + "');");
       } catch (SQLException e) {
          e.printStackTrace();
       }
    }
    
    // ���� ��� �޼ҵ�
    //S_Jatuae
    public void addDrop(String stName, String stNum, String apSemester, String startDate, String endDate, int abSemester, String apReason, String apCount)throws Exception{ 
       try {
          // �޾ƿ� �κ�����û ������ DB�� ����
          rs = st.executeQuery("insert into schoolApplication values('"
                  + stName + "','" + stNum + "','" + apSemester + "', '" + "����" + "','" + startDate + "','" 
                  + endDate + "','" + apReason + "','" + abSemester + "','" + apCount + "', '" + "�̽���" + "');");
       } catch (SQLException e) {
          e.printStackTrace();
       }
    }
     
    
    // ��������û ��� �޼ҵ�
    //S_BuBokSu
    public void addMinor(String stName, String stNum, String apSemester, String apMajor, String apDate)throws Exception{ 
       try {
          // �޾ƿ� ��������û ������ DB�� ����
          rs = st.executeQuery("insert into majorApplication values('"
                + stName + "','" + stNum + "', '" + "������" + "','" + apSemester + "','"
                + apMajor + "','" + apDate + "','" + ""  + "', '" + "�̽���" + "');");
       } catch (SQLException e) {
          e.printStackTrace();
       }
    }
    
    // ����������û ��� �޼ҵ�
    //S_BuBokSu
    public void addDouble(String stName, String stNum, String apSemester, String apMajor, String apDate)throws Exception{ 
        try {
           // �޾ƿ� ��������û ������ DB�� ����
           rs = st.executeQuery("insert into majorApplication values('"
                 + stName + "','" + stNum + "', '" + "��������" + "','" + apSemester + "','"
                 + apMajor + "','" + apDate + "','" + ""  + "', '" + "�̽���" + "');");
        } catch (SQLException e) {
           e.printStackTrace();
        }
     }
       
    // ������û ��� �޼ҵ�
    //S_JunKua
    public void addChange(String stName, String stNum, String apSemester, String apMajor, String apReason, String apDate)throws Exception{ 
        try {
           // �޾ƿ� ��������û ������ DB�� ����
           rs = st.executeQuery("insert into majorApplication values('"
                 + stName + "','" + stNum + "', '" + "����" + "','" + apSemester + "','"
                 + apMajor + "','" + apDate + "','" + apReason  + "', '" + "�̽���" + "');");
        } catch (SQLException e) {
           e.printStackTrace();
        }
     } 
    
   
 	
   
 	//*********��û ��ȸ
 	String stName, stNum, apSemester, classify, startDate, endDate, apReason, apCount, apState, apDate, apMajor;
 	int abSemester;
 	
 	// �й����� SchoolApplication ������ ��ü ����
	public SchoolApplication getSa(String id, String now, String what) throws SQLException {
		// �Է��� �м���ȣ�� ���� ���� ���� �ҷ�����
		ResultSet rs = st.executeQuery("select * from schoolApplication where stNum = '" + id + "'and apCount = '" + now + "'and classify = '" + what + "';");
		while (rs.next()) {
			// ���� �޼ҵ� ���� �޾ƿͼ� �Ҵ���.
			stName = rs.getString(1);
			stNum = rs.getString(2);
			apSemester = rs.getString(3);
			classify = rs.getString(4);
			startDate = rs.getString(5);
			endDate = rs.getString(6);
			apReason = rs.getString(7);
			abSemester = rs.getInt(8);
			apCount = rs.getString(9);
			apState = rs.getString(10);
		}
		
		// SchoolApplication ��ü ����
		SchoolApplication sa = new SchoolApplication(stName, stNum, apSemester, classify, startDate, endDate, apReason, abSemester, apCount, apState);
		
		return sa;
	}
   
   //��,����, ���� �޾ƿ���
   public ResultSet getSchoolA(String id) throws SQLException
   {
      //�Է��� index���� ���� ������ ���̴� ��ɾ� ����
      ResultSet rs = st.executeQuery("select * from schoolApplication where stNum = '" + id + "';");
            
      return rs;
   }
   
   //���н�û �޾ƿ���
   public ResultSet getLeave(String id) throws SQLException
   {
      //�Է��� index���� ���� ������ ���̴� ��ɾ� ����
      ResultSet rs = st.executeQuery("select * from schoolApplication where stNum = '" + id + "'and classify = '" + "����" + "';");
            
      return rs;
   }
   
   //���н�û �޾ƿ���
   public ResultSet getGoBack(String id) throws SQLException
   {
      //�Է��� index���� ���� ������ ���̴� ��ɾ� ����
      ResultSet rs = st.executeQuery("select * from schoolApplication where stNum = '" + id + "'and classify = '" + "����" + "';");
            
      return rs;
   }
   
   //�����û �޾ƿ���
   public ResultSet getDrop(String id) throws SQLException
   {
      //�Է��� index���� ���� ������ ���̴� ��ɾ� ����
      ResultSet rs = st.executeQuery("select * from schoolApplication where stNum = '" + id + "'and classify = '" + "����" + "';");
            
      return rs;
   }
   
	// �й����� MajorApplication ������ ��ü ����
	public MajorApplication getMa(String id, String now, String what) throws SQLException {
		// �Է��� �м���ȣ�� ���� ���� ���� �ҷ�����
		ResultSet rs = st.executeQuery("select * from majorApplication where stNum = '" + id + "'and apDate = '" + now + "'and classify = '" + what + "';");
		while (rs.next()) {
			// ���� �޼ҵ� ���� �޾ƿͼ� �Ҵ���.
			stName = rs.getString(1);
			stNum = rs.getString(2);
			classify = rs.getString(3);
			apSemester = rs.getString(4);
			apMajor = rs.getString(5);
			apDate = rs.getString(6);
			apReason = rs.getString(7);
			apState = rs.getString(8);
		}
		
		// MajorApplication ��ü ����
		MajorApplication ma = new MajorApplication(stName, stNum, classify, apSemester, apMajor, apDate, apReason, apState);
		
		return ma;
	}
	
	//��, ����, ���� �޾ƿ���
	public ResultSet getMajorA(String id) throws SQLException
	{
		//�Է��� index���� ���� ������ ���̴� ��ɾ� ����
	    ResultSet rs = st.executeQuery("select * from majorApplication where stNum = '" + id + "';");
	            
	    return rs;
	}
   
	//��, �������� ��û �޾ƿ���
	public ResultSet getMinDouble(String id) throws SQLException
	{
		//�Է��� index���� ���� ������ ���̴� ��ɾ� ����
	    ResultSet rs = st.executeQuery("select * from majorApplication where stNum = '" + id + "'and classify = '" + "������" + "'or classify = '" + "��������" + "';");
	            
	    return rs;
	}
	
	//���� ��û �޾ƿ���
	public ResultSet getChange(String id) throws SQLException
	{
		//�Է��� index���� ���� ������ ���̴� ��ɾ� ����
		ResultSet rs = st.executeQuery("select * from majorApplication where stNum = '" + id + "'and classify = '" + "����" + "';");
		            
		return rs;
	}
   
   //********************************************************����***************************************************
// �α����ϱ�(�л�.ver) -> ������ ����
	public boolean loginSt(String id, String pw) throws Exception {
		String checkIdMsg = "select *from student where stNum ='" + id + "'and pw='" + pw + "'"; // ���̵�� ��� �Է��ϱ�

		rs = st.executeQuery(checkIdMsg);
		while (rs.next()) {
			if (rs.getString("stNum").equals(id))// ��ġ�ϴ� ���̵�(�й�)�� �ִٸ�
				return true;// true ��ȯ
		}

		return false;// ��ġ�ϴ� ���� ����
	}

	// ******************************** S_ShowRecentGrade()
	// *******************************
	/* �����б� �߿� ���б�(max����) ��(col)�� ã�Ƽ� ���б� ������ �ش� ��(row) �� ��� */
	public String getMaxYear(String stNum) throws SQLException {
		String searchMaxYear = "select max(semester) from leNum where stNum = '" + stNum + "';";
		rs = st.executeQuery(searchMaxYear);
		rs.last();
		String maxYear = rs.getString(1);
		return maxYear;
	}

	// ���б� ������ȸ
	public List<LectureScore> showRecentGrade(String stNum, String maxYear) throws Exception {
		String searchWithMsg = "select * from leNum where semester = '" + maxYear + "'AND stNum = '" + stNum + "';";

		rs = st.executeQuery(searchWithMsg);
		List<LectureScore> score = new ArrayList<LectureScore>();

		while (rs.next()) {
			String getLeName = rs.getString("leName"); // �����
			String getStName = rs.getString("stName");
			int getCredit = rs.getInt("credit"); // �̼�����
			String getStNum = rs.getString("stNum"); // �й�
			int getMidScore = rs.getInt("midScore"); // �߰����
			int getFinScore = rs.getInt("finScore"); // �⸻���
			int getAssiScore = rs.getInt("assiScore"); // ����
			int getAttendScore = rs.getInt("attendScore"); // �⼮
			int getTotalScore = rs.getInt("totalScore"); // ����
			String getGrade = rs.getString("grade"); // ���

			// Person ��ü p�� �������־� ���� ���� �����͵��� �� ������ �־���
			LectureScore s = new LectureScore(getLeName, getStName, getCredit, getStNum, getMidScore, getAssiScore,
					getFinScore, getAttendScore, getTotalScore, getGrade);
			score.add(s);
		}
		return score;
	}

	// ******************************** S_ShowAllGrade()
	// *******************************
	// �ش� �б� ������ȸ
	public List<LectureScore> showGrade(String stNum, String semester) throws Exception {
		String searchWithMsg = "select *from leNum where stNum ='" + stNum + "' AND semester ='" + semester + "';";
		rs = st.executeQuery(searchWithMsg);
		List<LectureScore> score = new ArrayList<LectureScore>();

		while (rs.next()) {
			String getLeNum = rs.getString("leNum"); // �м���ȣ
			String getLeName = rs.getString("leName"); // �����
			String getDivision = rs.getString("division"); // �̼�����
			int getCredit = rs.getInt("credit"); // �̼�����
			String getTime = rs.getString("time");// ���ǽð�
			String getprName = rs.getString("professorName");// ������
			int getTotalScore = rs.getInt("totalScore"); // ����
			float getScoreCredit = rs.getFloat("scoreCredit");
			String getGrade = rs.getString("grade"); // ���

			// Person ��ü p�� �������־� ���� ���� �����͵��� �� ������ �־���
			LectureScore s = new LectureScore(getLeNum, getLeName, getDivision, getCredit, getTime, getprName,
					getTotalScore, getScoreCredit, getGrade);
			score.add(s);
		}
		return score;
	}

	// ��ü �������� ���� ���ϱ�
	public int getAllSubCnt(String stNum) throws SQLException {
		String getSubCnt = "select count(*) from leNum where stNum='" + stNum + "';";
		rs = st.executeQuery(getSubCnt);

		rs.next();
		int allSubCnt = rs.getInt(1);

		return allSubCnt;
	}

	// �� �̼� ����
	public int getAllCreditCnt(String stNum) throws SQLException {
		String getCreditSum = "select sum(credit) from leNum where stNum ='" + stNum + "';";

		rs = st.executeQuery(getCreditSum);

		rs.next();// Ŀ�� �̵�
		int allCreditCnt = rs.getInt(1); // ���� �б�

		return allCreditCnt;// ���� ��ȯ
	}

	// �� ���� ����
	public int getAllMCredit(String stNum) throws SQLException {
		String getMCreditSum = "select sum(credit) from leNum where (division = '�����ʼ�' or division = '��������') and stNum ='"
				+ stNum + "';";

		rs = st.executeQuery(getMCreditSum);
		rs.next();

		int allMCredit = rs.getInt(1);

		return allMCredit;
	}

	// �б� �� ���� ���ϱ�
	public double getAllScoreAvg(String stNum) throws SQLException {
		String getAllSum = "select sum(scoreCredit) from leNum where stNum ='" + stNum + "';";
		rs = st.executeQuery(getAllSum);
		rs.next();

		double allScoreSum = rs.getDouble(1);
		return allScoreSum;
	}

	// ���� �ݱ�
	public void dbClose() throws Exception {
		rs.close();
		st.close();
		con.close();
	}

}