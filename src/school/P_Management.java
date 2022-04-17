package school;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class P_Management {

   private Connection conn = null;
   public Statement st;
   public ResultSet rs;
   String leName,leNum,semester, prName, room, type, time, preLecture, division, state;
   int credit, attMem, limMem, limGrade;
   
   //********************************************************����***************************************************
   public P_Management(String s, Connection con) throws SQLException{

		con = DriverManager.getConnection(s, "root", "1234");
		st = con.createStatement();
	  }
   //���� ���� - �ش� ������ ���̺� �����ϱ� �� ��ȸ
   //���̺� �����ϱ�
   //P_AddLecture
   public void InsertLecture(Lecture le) throws SQLException
   {
      
      //�ش� ���̺� ���� �����ϴ� sql�� �ۼ�
      String sql = "insert into lecture values ('" + le.getleName() +"', '"+  le.getleNum()+"', '"+ le.getSemester()+"', "+ le.getLimGrade() + ","
            + "'"+le.getprName()+"', '"+le.getType()+"', '"+le.getTime()+"', "+le.getCredit()+", "+le.getAttMem()+""
                  + ", "+le.getLimMem()+", '"+le.getPreLecture()+"', '"+le.getDivision()+"', '"+le.getState()+"');";
         
         
      //��ɾ� ����
      st.executeUpdate(sql);
   }
   
	//P_Home
	public Professor getProfessor(String id) throws SQLException {
		Professor p = new Professor();

		ResultSet rs = st.executeQuery("select*from professor where Number = '"+id+"';");
		while (rs.next()) {
			p.setprName(rs.getString(1));
			p.setprNum(rs.getString(2));
			p.setBirth(rs.getInt(4));
			p.setCollege(rs.getString("college"));
			p.setMajor(rs.getString("department"));
			p.setPhoneNum(rs.getString(7));
			p.setEmail(rs.getString(8));
		}

		return p;
	}
	
	
   
   //���� ���� �޾ƿ���
   //P_AddLecture
   public ResultSet getLecture(String name) throws SQLException
   {
	   //�̸��� ������ �ƴ϶��
	   if(!name.equals(""))
	   {
		   //�ش� ������ �̸��� ���� ��� �޾ƿ���
		   //�Է��� index���� ���� ������ ���̴� ��ɾ� ����
		      ResultSet rs = st.executeQuery("select*from lecture where Prof = '"+name+"';");

		      return rs;

	   }
	   //�����̶�� ��� ���� �޾ƿ���
	   else {
		   ResultSet rs = st.executeQuery("select*from lecture;");
		      return rs;
	   }
   }
   
   //���� ���� �޾ƿ���
   //P_ShowLecture
   public ResultSet searchLecture(String name, String a, String b, String semester) throws SQLException
   {
	   //name���� �޴� ������ ���� ������, �к�, �ð��뺰�� �˻��ϴ� ������ �޶���
	   //�̸��� ������ �ƴ϶��
	   if(name.equals("�к�"))
	   {
		   //�ش� ������ �̸��� ���� ��� �޾ƿ���
		   //�Է��� index���� ���� ������ ���̴� ��ɾ� ����
		   //ù��° ���� - �̼�����, �ι�° ���� - ����
		   rs = st.executeQuery("select*from lecture where Division = '"+a+"' and Semester = '"+semester+"' and Number like '"+b+"%';");

	   }
	   else if(name.equals("�ð�")){
		   //ù��° ���� - ����, �ι��� ���� - �ڼ��� �ð� ex)1-2 ����
		   rs = st.executeQuery("select*from lecture where Time = '"+a+b+"' and Semester = '"+semester+"';");
		   System.out.println("select*from lecture where Time = '"+a+b+"' and Semester = '"+semester+"';");
	   }
	   else if(name.equals("������")){
		   //ù��° ���� - �˻��ϴ� ������ ���������� �м���ȣ���� �˷���, �ι��� ���� - �˻��ϴ� ����
		   if(a.equals("�����"))
		   {
			   //��������� ���
			   rs = st.executeQuery("select*from lecture where Name = '"+b+"' and Semester = '"+semester+"';");
		   }
		   else {
			   //�м���ȣ�� ���
			   rs = st.executeQuery("select*from lecture where Number = '"+b+"' and Semester = '"+semester+"';");
		   }
		  
	   }
	   else if(!semester.contentEquals("")){
		   //�к�, �ð�, ����� ���� ��� -> P_ShowProLecture���� �ش� ������ ������ ���� ��ȸ�ϱ�
		   rs = st.executeQuery("select*from lecture where Prof = '"+b+"' and Semester = '"+semester+"';");
		   
	   }else {
		   //�к�, �ð�, ����� ���� ��� -> P_ShowProLecture���� �ش� ������ ������ ���� ��ȸ�ϱ�
		   rs = st.executeQuery("select*from lecture where Prof = '"+b+"';");
	   }

	  return rs;
   }
   

   //P_AddLecture
   public int getLectureCount(String s, String semester) throws SQLException
   {
	   String leNum = null;   
       if (s.equals("����Ʈ���������а�")) {
           leNum = "MT";
        } else if (s.equals("������а�")) {
           leNum = "KL";
        } else if (s.equals("������а�")) {
           leNum = "EL";
        }  else if (s.equals("�����а�")) {
           leNum = "EC";
        }  else if (s.equals("ȭ�а�")) {
           leNum = "CH";
        } else {
           leNum = "AA";
        }
		   rs = st.executeQuery("select count(*)from lecture where Semester = '"+semester+"' and Number like '"+leNum+"%';");
			//�� ���� �о num ������ ����
		   /*
			int num = 0;
		   while(rs.next())
		      {
			   	num++;
		      }
		    */

		      
		      rs.next();// Ŀ�� �̵�
		      int perCnt = rs.getInt(1); // ���� �б�


			rs.close();
			
			return perCnt;
   }
   
   //�м� ��ȣ �Է��ϸ� �ش� ������ ������
   //P_ShowLecture
   public Lecture getLe(String leNum) throws SQLException
   {
      //�Է��� �м���ȣ�� ���� ���� ���� �ҷ�����
      ResultSet rs = st.executeQuery("select*from lecture where Number = '"+leNum+"';");
         
      while(rs.next())
      {
         //���� �޼ҵ� ���� �޾ƿͼ� �Ҵ���.
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
      //���� ��ü ����
      Lecture le = new Lecture(leName, leNum, semester, limGrade, prName, type, time, credit, attMem, limMem, preLecture, division, state);
      return le;
   }
   
   //�ش� �л��� �ش� �б⿡ ������ ���� �ҷ�����
   //P_Graduation
   public ResultSet getStClasses(String stNum, String semester) throws SQLException
   {
      //�л� ���� ���̺��� �Է��� �б� ������ ���� ���� ���� �ҷ�����
      ResultSet rs = st.executeQuery("select*from student"+stNum+" WHERE semester = '"+semester+"' AND state = 1;");
      return rs;
   }
   
   //���� ��ȸ�ϴ� ���
   //��ü �������� �ش� �б⿡ �ش� �Ǵ� ���� �ҷ�����
   public ResultSet getClasses(String semester, String username) throws SQLException
   {
	   //������ �ش� �б⿡ ������ ������ ��ȸ�Ѵٸ�?
	   if(!username.equals(""))
	   {
		   //�Է��� index���� ���� ������ ���̴� ��ɾ� ����
		      ResultSet rs = st.executeQuery("select*from Lecture WHERE semester = '"+semester+"' AND Prof = '"+username+"';");

		      return rs;

	   }
	   else {
		      //��ü ���� ���̺��� �Է��� �б� ������ ���� ���� ���� �ҷ�����
		      ResultSet rs = st.executeQuery("select*from Lecture WHERE semester = '"+semester+"';");
		      return rs;
	   }

   }
   
	//P_GraduationCheck
	public ResultSet getStudent(String major) throws SQLException
	{	
		ResultSet rs = st.executeQuery(
				"select*from student WHERE fMajor = '" + major + "' ;");
		
		return rs;
	}
   
	//P_GraduationCheck
	//�л��� ���� ���� ���� ��ȯ�ϴ� �Լ�
	public int getTotalCredit(String StuNum, String which) throws SQLException
	{
		int credit = 0;
		int totalcredit = 0;
		if(which.contentEquals("������"))
		{
			ResultSet rs = st.executeQuery(
					"select*from lenum WHERE stNum = '" + StuNum + "';");
			while (rs.next()) {
				credit = rs.getInt(6);
				totalcredit += credit;
			}
		}else if(which.contentEquals("�����ʼ�"))
		{
			ResultSet rs = st.executeQuery(
					"select*from lenum WHERE stNum = '" + StuNum + "' AND division = '"+which+"';");
			while (rs.next()) {
				credit = rs.getInt(6);
				totalcredit += credit;
			}
		}else if(which.contentEquals("���缱��"))
		{
			ResultSet rs = st.executeQuery(
					"select*from lenum WHERE stNum = '" + StuNum + "' AND division = '"+which+"';");
			while (rs.next()) {
				credit = rs.getInt(6);
				totalcredit += credit;
			}
		}else if(which.contentEquals("�����ʼ�"))
		{
			ResultSet rs = st.executeQuery(
					"select*from lenum WHERE stNum = '" + StuNum + "' AND division = '"+which+"';");
			while (rs.next()) {
				credit = rs.getInt(6);
				totalcredit += credit;
			}
		}else if(which.contentEquals("��������"))
		{
			ResultSet rs = st.executeQuery(
					"select*from lenum WHERE stNum = '" + StuNum + "' AND division = '"+which+"';");
			while (rs.next()) {
				credit = rs.getInt(6);
				totalcredit += credit;
			}
		}
		return totalcredit;
	}
	
	//P_Graduation
	//�л��� ���� �������� �� ������ ����ϴ� �Լ�
	public float getTotalGrade(String StuNum, String which) throws SQLException
	{
		float grade = 0;
		if(which.contentEquals("����"))
		{
			ResultSet rs = st.executeQuery(
					"select*from student WHERE stNum = '" + StuNum + "';");
			while (rs.next()) {
				grade = rs.getFloat(13);
			}
		}else
		{
			ResultSet rs = st.executeQuery(
					"select*from student WHERE stNum = '" + StuNum + "';");
			while (rs.next()) {
				grade = rs.getFloat(14);
			}
		}
		return grade;
	}
	   
   //********************************************************�ƿ�***************************************************
   

   //**ȸ������
   //���� �ߺ� Ȯ��
	public boolean checkId(String id) {
			// �Ķ���ͷ� �޾ƿ� id�� �˻��Ѵ�.
		try {
			rs = st.executeQuery("select * from professor where Number ='" + id + "';");

			while (rs.next()) {
				if (rs.getString("Number").equals(id))// ��ġ�ϴ� �̸��� �ִٸ�
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
 			rs = st.executeQuery("select * from professor where Phone = '" + phoneNum + "';");
 			while (rs.next()) {
 				if (rs.getString("Phone").equals(phoneNum))// ��ġ�ϴ� �̸��� �ִٸ�
 					return true;// true ��ȯ
 			}
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
 		return false;
 	}
 	
 	//���� ��� �޼ҵ�
 	 	public void add(String prNum, String pw, String birth, String email, String phoneNum)throws Exception{ 
 	 		try {
 	 			// �޾ƿ� Account ��ü P�� db�� �߰��� �ش�.
 	 			rs = st.executeQuery("update professor set pw = '" + pw + "', birth = '" + birth + "', email = '" + email + "', phone = '" + phoneNum + "' where Number= '" + prNum +"';");

 	 		} catch (SQLException e) {
 	 			e.printStackTrace();
 	 		}
 	 	}
 	
 	 	
 	//**ID,PW ã��
 	//ID ã��
 	public String findId(String email, String phoneNum) throws Exception {
 			
 		rs = st.executeQuery("select *from professor where email ='" + email + "'and phone='" + phoneNum + "';");
 		while (rs.next()) {
 			if (rs.getString("email").equals(email) && rs.getString("phone").equals(phoneNum))// ��ġ�ϴ� ���̵�(�й�)�� �ִٸ�
 				return rs.getString(2);// id ��ȯ
 		}
 		return null;// ��ġ�ϴ� ���� ����
 	}
 	
 	//PW ã��
	public String findPw(String id, String email, String phoneNum) throws Exception {
		
		rs = st.executeQuery("select *from professor where number = '" + id + "' and email ='" + email + "'and phone='" + phoneNum + "';");
		while (rs.next()) {
			if (rs.getString("number").equals(id) && rs.getString("email").equals(email) && rs.getString("phone").equals(phoneNum))// ��ġ�ϴ� ���̵�(�й�)�� �ִٸ�
				return rs.getString(3);// pw ��ȯ
		}
		return null;// ��ġ�ϴ� ���� ����
	}
	
	
	
 	//**���� ����
    //���� ����
    public void editInfo(String prNum, String phoneNum, String email) throws Exception {
 	   try {
 		   rs = st.executeQuery("update professor set phoneNum = '" + phoneNum + "', email = '" + email + "' where Number= '" + prNum + "';");
 	   } catch (SQLException e) {
           e.printStackTrace();
        }
    }
    
    //���� ��ü ����
    public ResultSet getAllLe(String name) throws SQLException
    {
    	ResultSet rs = st.executeQuery("select * from Lecture where Prof = '" + name + "';");
 		return rs;
 	 
    }
   
	
	//*********��û ��ȸ
	//��,����, ���� �޾ƿ���
	public ResultSet getSchoolA() throws SQLException
	{
	   //�Է��� index���� ���� ������ ���̴� ��ɾ� ����
	   ResultSet rs = st.executeQuery("select * from schoolApplication;");
	            
	   return rs;
	}
	   
	//���н�û �޾ƿ���
	public ResultSet getLeave() throws SQLException
	{
	   //�Է��� index���� ���� ������ ���̴� ��ɾ� ����
	   ResultSet rs = st.executeQuery("select * from schoolApplication where classify = '" + "����" + "';");
	            
	   return rs;
	}
	
	
	//���н�û �޾ƿ���
	public ResultSet getGoBack() throws SQLException
	{
	   //�Է��� index���� ���� ������ ���̴� ��ɾ� ����
	   ResultSet rs = st.executeQuery("select * from schoolApplication where classify = '" + "����" + "';");
	            
	   return rs;
	}
	
	
	//�����û �޾ƿ���
	public ResultSet getDrop() throws SQLException
	{
	   //�Է��� index���� ���� ������ ���̴� ��ɾ� ����
	   ResultSet rs = st.executeQuery("select * from schoolApplication where classify = '" + "����" + "';");
	            
	   return rs;
	}
	
	//��, ����, ���� �޾ƿ���
	public ResultSet getMajorA() throws SQLException
	{
		//�Է��� index���� ���� ������ ���̴� ��ɾ� ����
		ResultSet rs = st.executeQuery("select * from majorApplication;");
		            
		return rs;
	}
	   
	//��, �������� ��û �޾ƿ���
	public ResultSet getMinDouble() throws SQLException
	{
		//�Է��� index���� ���� ������ ���̴� ��ɾ� ����
		ResultSet rs = st.executeQuery("select * from majorApplication where classify = '" + "������" + "'or classify = '" + "��������" + "';");
		            
		return rs;
	}
	
	//���� ��û �޾ƿ���
	public ResultSet getChange() throws SQLException
	{
		//�Է��� index���� ���� ������ ���̴� ��ɾ� ����
		ResultSet rs = st.executeQuery("select * from majorApplication where classify = '" + "����"  + "';");
			            
		return rs;
	}
	

   
   //**����
   // ���� ���� �޼ҵ�
   //P_Huhak
   public void acceptLeave(String id, String now) throws Exception {
  	 try {
  		 rs = st.executeQuery("update schoolapplication set apState = '" + "����" + "' where stNum= '" + id
 			   + "'and apCount = '" + now + "'and classify = '" + "����" + "';");
  	 } catch (SQLException e) {
          e.printStackTrace();
     }
   }
   
   // ���� ���� �޼ҵ�
   //P_Bokhak
   public void acceptGoBack(String id, String now) throws Exception {
	 try {
		 rs = st.executeQuery("update schoolapplication set apState = '" + "����" + "' where stNum= '" + id
	 			  + "'and apCount = '" + now + "'and classify = '" + "����" + "';");
	 } catch (SQLException e) {
	        e.printStackTrace();
	 }
   }
   
   // ���� ���� �޼ҵ�
   //P_Jatuae
   public void acceptDrop(String id, String now) throws Exception {
	 try {
		 rs = st.executeQuery("update schoolapplication set apState = '" + "����" + "' where stNum= '" + id
	 			   + "'and apCount = '" + now + "'and classify = '" + "����" + "';");
	  } catch (SQLException e) {
	        e.printStackTrace();
	  }
   }
    
   // �κ���������û ���� �޼ҵ�
   //P_BuBoksu
   public void acceptMinDouble(String id, String now) throws Exception {
	 try {
	  	rs = st.executeQuery("update majorapplication set apState = '" + "����" + "' where stNum= '" + id
	 		   + "'and apDate = '" + now + "'and (classify = '" + "������" + "'or classify = '" + "��������" + "');");
	 } catch (SQLException e) {
	       e.printStackTrace();
	 }
   }
   

   // ������û ���� �޼ҵ�
   //P_JunKua
   public void acceptChange(String id, String now) throws Exception {
	 try {
		 rs = st.executeQuery("update majorapplication set apState = '" + "����" + "' where stNum= '" + id
	 	   + "'and apDate = '" + now + "'and classify = '" + "����" + "';");
	 } catch (SQLException e) {
	     e.printStackTrace();
	 }
   }
   
   
   //**********��û ����/���
   // ���� ����/��� �޼ҵ�
   public void deleteLeave(String id, String now) throws Exception {
      st.executeUpdate("delete from schoolApplication where stNum = '" + id  + "'and apCount = '" + now + "'and classify = '" + "����" + "';");
   }
   
   // ���� ����/��� �޼ҵ�
   public void deleteGoBack(String id, String now) throws Exception {
      st.executeUpdate("delete from schoolApplication where stNum = '" + id  + "'and apCount = '" + now + "'and classify = '" + "����" + "';");
   }
      
      
   // ���� ����/��� �޼ҵ�
   public void deleteDrop(String id, String now) throws Exception {
      st.executeUpdate("delete from schoolApplication where stNum = '" + id  + "'and apCount = '" + now + "'and classify = '" + "����" + "';");
   }
  
   // �κ������� ��û ����/��� �޼ҵ�
   public void deleteMinDouble(String id, String now) throws Exception {
      st.executeUpdate("delete from majorApplication where stNum = '" + id  + "'and apDate = '" + now + "'and (classify = '" + "������" + "'or classify = '" + "��������" + "');");
   }
   
   // ������û ����/��� �޼ҵ�
   public void deleteChange(String id, String now) throws Exception {
      st.executeUpdate("delete from majorApplication where stNum = '" + id  + "'and apDate = '" + now + "'and classify = '" + "����" + "';");
   }
   
   
      
// ********************************************************����***************************************************

// �α����ϱ�(����.ver)
	/*
	 * ������ ��й�ȣ�� UI���� ���ÿ� �޾ƿ� ��(=���̵� ��й�ȣ ��ġ����Ȯ��) ����(id)�� ���簡 db���� Ȯ�εǸ� true��
	 * ��ȯ�Ѵ�.(=�α��� ����!)
	 */
	public boolean loginPr(String id, String pw) throws Exception {
		String checkIdMsg = "select *from professor where Number ='" + id + "'and pw='" + pw + "'"; // ���̵�� ��� �Է��ϱ�

		rs = st.executeQuery(checkIdMsg);
		while (rs.next()) {
			if (rs.getString("Number").equals(id))// ��ġ�ϴ� ���̵�(����)�� �ִٸ�
				return true;// true ��ȯ
		}

		return false;// ��ġ�ϴ� ���� ����
	}

	// �α����� ������ �̸� ������ �޾ƿ���
	public String checkNamePr(String id) throws Exception {
		String checkNameMsg = "select *from professor"; // table ��ü ��� SQL��

		rs = st.executeQuery(checkNameMsg);
		while (rs.next()) {
			if (rs.getString("Number").equals(id))// ��ġ�ϴ� �̸��� �ִٸ�
				return rs.getString("Name");// true ��ȯ
		}

		return null;// ��ġ�ϴ� �̸� ����
	}

	 //******************************** P_Grade() *******************************	
	// ��米��(prName)�� �����б�(semester)���� '�����' �޾ƿ���
	public List<String> getLeInSem(String prName, String semester) throws Exception {
		String getTable = "select *from lecture where semester ='" + semester + "';";
		rs = st.executeQuery(getTable);

		// ������ �ߺ� ����� �����ϱ� ���� '����'�� �־��ٰ� �ٽ� '����Ʈ'�� ��ȯ!
		List<String> lectures = new ArrayList<String>();
		HashSet<String> lectures2 = new HashSet<String>();
		while (rs.next()) {
			if (rs.getString("Prof").equals(prName)) {
				String getLeName = rs.getString("Name"); // �����
				lectures.add(getLeName);
			}
		}

		for (String item : lectures) {
			lectures2.add(item);
		}

		List<String> lectures3 = new ArrayList<String>(lectures2);
		return lectures3;
	}

	// ���� ������ �ҷ����� -> �б�� �޾ƿ��� 
	/* �б⸦ �Ű������� �޾ƿͼ� �ش��б��� ���������͸� ����Ѵ�. */
	public List<LectureScore> showGrade(String semester, String leNum) throws Exception {
		String searchWithMsg = "select *from leNum where leNum ='" +leNum + "' AND semester ='" + semester + "';";
		rs = st.executeQuery(searchWithMsg);
		List<LectureScore> score = new ArrayList<LectureScore>();

		while (rs.next()) {
			String getLeName = rs.getString("leName"); // �����
			String getStName = rs.getString("stName"); // �̸�
			String getStNum = rs.getString("stNum"); // �й�
			int getMidScore = rs.getInt("midScore"); // �߰����
			int getFinScore = rs.getInt("finScore"); // �⸻���
			int getAssiScore = rs.getInt("assiScore"); // ����
			int getAttendScore = rs.getInt("attendScore"); // �⼮
			int getTotalScore = rs.getInt("totalScore"); // ����
			String getGrade = rs.getString("grade"); // ���

			// Person ��ü p�� �������־� ���� ���� �����͵��� �� ������ �־���
			LectureScore s = new LectureScore(getLeName, getStName, getStNum, getMidScore, getAssiScore, getFinScore,
					getAttendScore, getTotalScore, getGrade);
			score.add(s);
		}
		return score;
	}

	// ���� ����
	public void modifyScore(String stNum, String leNum, LectureScore S) throws Exception {
		// table ���� ����
		if(S.getGrade().contentEquals("A+"))
		{
			S.setScoreCredit(4.5f);
		}else if(S.getGrade().contentEquals("A0")){
			S.setScoreCredit(4.0f);
		}else if(S.getGrade().contentEquals("B+")){
			S.setScoreCredit(3.5f);
		}else if(S.getGrade().contentEquals("B0")){
			S.setScoreCredit(3.0f);
		}else if(S.getGrade().contentEquals("C+")){
			S.setScoreCredit(2.5f);
		}else if(S.getGrade().contentEquals("C0")){
			S.setScoreCredit(2.0f);
		}
		
		st.executeUpdate("update leNum set midScore='" + S.getMidScore() + "', " + "assiScore='" + S.getAssiScore()
				+ "', " + "finScore='" + S.getFinScore() + "', " + "totalScore='" + S.getTotalScore() + "'," + "grade='"
				+ S.getGrade() + "'," + "attendScore='" + S.getAttendScore() + "', scoreCredit = '"+S.getScoreCredit()+"'" + " where stNum='" + stNum
				+ "' AND leNum = '" + leNum + "';");
	}

	// �����ο� ���ϱ�
	/* �����̸��� OO�� ���� ���� ����� ���� ���� */
	public int getCount(String leNum) throws SQLException {
		String getCountMsg = "select count(*) from leNum where leNum ='" + leNum + "';";

		rs = st.executeQuery(getCountMsg);

		rs.next();// Ŀ�� �̵�
		int perCnt = rs.getInt(1); // ���� �б�

		return perCnt;// ���� ��ȯ
	}

	// �л� ��� ���ϱ� �޼ҵ�(��)
	/* �����̸��� OO�� ���� ���� �� �� ���ϱ� */
	public int stLecture(String semester, String leNum) throws Exception {
		String getCountMsg = "select sum(totalScore) from leNum where leNum ='" +leNum  + "';";
		rs = st.executeQuery(getCountMsg);
		rs.last();
		int allTotal = rs.getInt(1);

		return allTotal;
	}

	   
	// ����� �ֱ�
	public void setLeName(String leName) {
		this.leName = leName;
	}

	// ����� ����
	public String getLeName() {
		return leName;
	}

	// ���� �ݱ�
	public void dbClose() throws Exception {
		rs.close();
		st.close();
		conn.close();
	}
}