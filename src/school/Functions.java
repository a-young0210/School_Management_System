package school;

import java.io.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.sql.*;

public class Functions {
   Connection conn = null;
   Statement st = null;
   ResultSet rs = null;
   int num; //������ �޴µ� ����
   P_Management mg;
   
   public Functions(String s, Connection con) throws SQLException {
	// TODO Auto-generated constructor stub
		con = DriverManager.getConnection(s, "root", "1234");
		st = con.createStatement();
		mg = new P_Management(s, con);
}

// �����̸����� ���� ã��
   public String searchpMajor(String Name) throws Exception{
      rs = st.executeQuery("select * from professor where Name = '" + Name + "'");
      while (rs.next()) {
         String major = rs.getString("Department"); 
         return major;
      }
      throw new Exception("��ϵ� ���� ����");
   }
   
   // �л��̸����� ���� ã��
      public String searchsMajor(Student S) throws Exception{
         rs = st.executeQuery("select * from student where stName = '" + S.getstName() + "'");
         while (rs.next()) {
            String major = rs.getString("fMajor"); 
            return major;
         }
         throw new Exception("��ϵ� �л� ����");
      }
   
   // ���� �̸����� ���� �̼����� ã��
   public String searchDivision(Lecture LE) throws Exception{
      rs = st.executeQuery("select * from lecture where leName = '" + LE.getleName() + "'");
      while (rs.next()) {
         String division = rs.getString("division"); 
         return division;
      }
      throw new Exception("��ϵ� ���� ����");
   }
      
   // �м���ȣ �Լ�
   //P_AddLecture
   public String makeLeNum(Lecture L, String name)throws Exception{ 
	   String semester = "2021-1";
      String leNum = "";
      int leNum1 = 0000;
      int leNumMT = mg.getLectureCount("����Ʈ���������а�", semester);
      int leNumKL = mg.getLectureCount("������а�", semester);
      int leNumEL = mg.getLectureCount("������а�", semester);
      int leNumEC = mg.getLectureCount("�����а�", semester);
      int leNumCH = mg.getLectureCount("ȭ�а�", semester);
      int leNumAA = mg.getLectureCount("�� ��", semester);
      
      //���������� �츮�� ���� �м���ȣ
      String rleNum;
      
         if (searchpMajor(name).equals("����Ʈ���������а�")) {
            leNum = "MT";
            rleNum = leNum + leNumMT;
         } else if (searchpMajor(name).equals("������а�")) {
            leNum = "KL";
            rleNum = leNum + leNumKL;
         } else if (searchpMajor(name).equals("������а�")) {
            leNum = "EL";
            rleNum = leNum + leNumEL;
         }  else if (searchpMajor(name).equals("�����а�")) {
            leNum = "EC";
            rleNum = leNum + leNumEC;
         }  else if (searchpMajor(name).equals("ȭ�а�")) {
            leNum = "CH";
            rleNum = leNum + leNumCH;
         } else {
            leNum = "AA";
            rleNum = leNum + leNumAA;
         }
         


         // �м���ȣ DB ���� 
         //st.executeUpdate("update lecture set leNum:='" + rleNum + "' where leName = '" + L.getleName() + "';");

     	return rleNum;
   }
   
   // �й� �����ϴ� �Լ�
   public void makeStNum(Student S)throws Exception{
      int mtNum = 0001, klNum = 1111, elNum = 2222, ecNum = 3333, chNum = 4444, aaNum = 5555;
      int stNum = 0;
      
      SimpleDateFormat sdf = new SimpleDateFormat("yy");
      Calendar cal = Calendar.getInstance();
      String yearstr = sdf.format(cal.getTime());
      int year = Integer.parseInt(yearstr);
      System.out.println(year);
      try {
         if (searchsMajor(S) == "����Ʈ���������а�") {
            stNum = mtNum;
            mtNum++;
         } else if (searchsMajor(S) == "������а�") {
            stNum = klNum;
            mtNum++;
         } else if (searchsMajor(S) == "������а�") {
            stNum = elNum;
            mtNum++;
         } else if (searchsMajor(S) == "�����а�") {
            stNum = ecNum;
            mtNum++;
         } else if (searchsMajor(S) == "ȭ�а�") {
            stNum = chNum;
            mtNum++;
         } else {
            stNum = aaNum;
            mtNum++;
         }
         
         int num = year + stNum;
         
         // �й� DB ���� 
         st.executeUpdate("update student set stNum:='" + num + "' where stName = '" + S.getstName() + "';");
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}