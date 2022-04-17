package school;

import java.io.Serializable;

public class Lecture implements Serializable{
   private String leName;       // ����� �ʵ�
   private String leNum;       // �м���ȣ �ʵ�
   private String semester;   // �б� �ʵ�
   private int limGrade;      // �����г� �ʵ�
   private String prName;   // ��米�� �ʵ�
   private String type;      // �������� �ʵ�
   private String time;      // ���ǽð� �ʵ�
   private int credit;         // ���� �ʵ�
   private int attMem;         // �����ο� �ʵ�
   private int limMem;         // �����ο� �ʵ�
   private String preLecture;   // �������� �ʵ�
   private String division;   // �̼����� �ʵ�
   private String state;      // ��ٱ��� �ʵ�
   
   //�⺻ ������
   public Lecture(String leName, String leNum, String semester, int limGrade, String prName, String type, String time, int credit,
         int attMem, int limMem, String preLecture, String division, String state){ 
      this.leName = leName;
      this.leNum = leNum;
      this.semester = semester;
      this.limGrade = limGrade;
      this.prName = prName;
      this.type = type;
      this.time = time;
      this.credit = credit;
      this.attMem = attMem;
      this.limMem = limMem;
      this.preLecture = preLecture;
      this.division = division;
      this.state = state;
   }
   
   public void setleName(String leName){       // ����� ������
      this.leName = leName;
   }      
   public void setleNum(String leNum) {   // �м���ȣ ������
      this.leNum = leNum;
   }
   public void setSemester(String semester) {   // �б� ������
      this.semester = semester;
   }
   public void setLimGrade(int limGrade) {   // �����г� ������
      this.limGrade = limGrade;
   }
   public void setprName(String prName) {   // ��米�� ������
      this.prName = prName;
   }
   public void setType(String type){    // �������� ������
      this.type = type;
   }
   public void setTime(String time) {   // ���ǽð� ������
      this.time = time;
   }
   public void setCredit(int credit) {   // ���� ������
      this.credit = credit;
   }
   public void setattMem(int attMem) {   // �����ο� ������
      this.attMem = attMem;
   }
   public void setLimMem(int limMem){    // �����ο� ������
      this.limMem = limMem;
   }
   public void setPreLecture(String preLecture) {   // �������� ������
      this.preLecture = preLecture;
   }
   public void setDivision(String division){    // �̼����� ������
      this.division = division;
   }
   public void setState(String state) {   // ��ٱ��� ������
      this.state = state;
   }
   

   public String getleName(){          // ����� ������
      return leName;
   }
   public String getleNum() {   // �м���ȣ ������
      return leNum;
   }
   public String getSemester() {   // �б� ������
      return semester;
   }
   public int getLimGrade()  {   // �����г� ������
      return limGrade;
   }
   public String getprName() {   // ��米�� ������
      return prName;
   }
   public String getType(){    // �������� ������
      return type;
   }
   public String getTime() {   // ���ǽð� ������
      return time;
   }
   public int getCredit() {   // ���� ������
      return credit;
   }
   public int getAttMem() {   // �����ο� ������
      return attMem;
   }
   public int getLimMem(){    // �����ο� ������
      return limMem;
   }
   public String getPreLecture() {   // �������� ������
      return preLecture;
   }
   public String getDivision(){    // �̼����� ������
      return division;
   }
   public String getState() {   // ��ٱ��� ������
      return state;
   }
}