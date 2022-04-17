package school;

import java.io.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.sql.*;

public class Management {
   Connection conn = null;
   Statement stmt = null;
   ResultSet rs = null;
   public Statement st;
   int num; //������ �޴µ� ����
   
   public Management(String url, Connection con) throws Exception {//UI���� ������ ����� �� �����ͼ� ��ü ����
	      st = con.createStatement();
   }
   
   // �����̸����� ���� ã��
   public String searchpMajor(Lecture LE) throws Exception{
      rs = stmt.executeQuery("select * from professor where prName = '" + LE.getprName() + "'");
      while (rs.next()) {
         String major = rs.getString("major"); 
         return major;
      }
      throw new Exception("��ϵ� ���� ����");
   }
   
   // �л��̸����� ���� ã��
      public String searchsMajor(Student S) throws Exception{
         rs = stmt.executeQuery("select * from student where stName = '" + S.getstName() + "'");
         while (rs.next()) {
            String major = rs.getString("fMajor"); 
            return major;
         }
         throw new Exception("��ϵ� �л� ����");
      }
   
   // ���� �̸����� ���� �̼����� ã��
   public String searchDivision(Lecture LE) throws Exception{
      rs = stmt.executeQuery("select * from lecture where leName = '" + LE.getleName() + "'");
      while (rs.next()) {
         String division = rs.getString("division"); 
         return division;
      }
      throw new Exception("��ϵ� ���� ����");
   }
      
   // �м���ȣ �Լ�
   public void makeLeNum(Lecture L)throws Exception{ 
      String leNum = "";
      int leNum1 = 0000;
      int leNumM = 0001;
      int leNumC = 0001;
      try {
         if (searchpMajor(L) == "����Ʈ���������а�") {
            leNum = "MT";
         } else if (searchpMajor(L) == "������а�") {
            leNum = "KL";
         } else if (searchpMajor(L) == "������а�") {
            leNum = "EL";
         }  else if (searchpMajor(L) == "�����а�") {
            leNum = "EC";
         }  else if (searchpMajor(L) == "ȭ�а�") {
            leNum = "CH";
         } else {
            leNum = "AA";
         }
         
         if (searchDivision(L) == "�����ʼ�" || searchDivision(L) == "���缱��") {
            leNum1 = leNumC;
            leNumC++;
         } else {
            leNum1 = leNumM;
            leNumM++;
         } 
         
         String rleNum = leNum + leNum1;
         
         // �м���ȣ DB ���� 
         stmt.executeUpdate("update lecture set leNum:='" + rleNum + "' where leName = '" + L.getleName() + "';");
      } catch (SQLException e) {
         e.printStackTrace();
      }
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
         stmt.executeUpdate("update student set stNum ='" + num + "' where stName = '" + S.getstName() + "';");
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}