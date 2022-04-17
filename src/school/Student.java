package school;

import java.io.Serializable;

public class Student implements Serializable{
   private String stName;       // �̸� �ʵ�
   private String stNum;          // �й� �ʵ�
   private int grade;          // �г� �ʵ�
   private String pw;          // ��й�ȣ �ʵ�
   private String birth;         // ������� �ʵ�
   private String college;      // �ܰ����� �ʵ�
   private String fMajor;      // �� 1���� �ʵ�
   private String sMajor;      // ������ �ʵ�
   private String dMajor;      // �������� �ʵ�
   private String phoneNum;   // ��ȭ��ȣ �ʵ�
   private String email;      // �̸��� �ʵ�
   private String changed;      // ������������ �ʵ�
   private String conditions;   // ���� ���� �ʵ�
   private int totalMajor;       // ���������� �ʵ�
   private int totalFinish;   // ���̼����� �ʵ�
   
   //�⺻ ������
   public Student(String stName, String stNum, int grade, String pw, String birth, String college, String fMajor, String sMajor, String dMajor, String phoneNum, 
         String email, String changed, String conditions, int totalMajor, int totalFinish){ 
      this.stName = stName;
      //this.year = year;
      this.stNum = stNum;
      this.grade = grade;
      this.pw = pw;
      this.birth = birth;
      this.college = college;
      this.fMajor = fMajor;
      this.sMajor = sMajor;
      this.dMajor = dMajor;
      this.phoneNum = phoneNum;
      this.email = email;
      this.changed = changed;
      this.conditions = conditions;
      this.totalMajor = totalMajor;
      this.totalFinish = totalFinish;
   }
   
   public Student() {
	// TODO Auto-generated constructor stub
}

public void setstName(String stName){       // �̸� ������
      this.stName = stName;
   }      
   /*public void setYear(int year) {   // ���г⵵ ������
      this.year = year;
   }*/
   public void setstNum(String stNum) {   // �й� ������
      this.stNum = stNum;
   }
   public void setGrade(int grade) {   // �г� ������
      this.grade = grade;
   }
   public void setPw(String pw) {   // ��й�ȣ ������
      this.pw = pw;
   }
   public void setBirth(String string) {   // ������� ������
      this.birth = string;
   }
   public void setCollege(String college) {   // �ܰ����� ������
      this.college = college;
   }
   public void setfMajor(String fMajor) {   // ��1���� ������
      this.fMajor = fMajor;
   }
   public void setsMajor(String sMajor) {   // ������ ������
      this.sMajor = sMajor;
   }
   public void setdMajor(String dMajor) {   // �������� ������
      this.dMajor = dMajor;
   }
   public void setPhoneNum(String phoneNum){    // ��ȭ��ȣ ������
      this.phoneNum = phoneNum;
   }
   
   public void setEmail(String email) {   // �̸��� ������
      this.email = email;
   }
   
   public void setfChanged(String changed) {   // ������������ ������
      this.changed = changed;
   }
   
   public void setConditions(String conditions) {   // �������� ������
      this.conditions = conditions;
   }
   
   public void setTotalMajor(int totalMajor) {   // ���������� ������
      this.totalMajor = totalMajor;
   }
   
   public void setTotalFinish(int totalFinish) {   // ���̼����� ������
      this.totalFinish = totalFinish;
   }
   
   

   public String getstName(){          // �̸� ������
      return stName;
   }
   public String getstNum(){       // �й� ������
      return stNum;
   }
   public int getGrade() {   // �г� ������
      return grade;
   }
   public String getPw() {   // ��й�ȣ ������
      return pw;
   }
   public String getBirth() {   // ������� ������
      return birth;
   }
   public String getCollege() {   // �ܰ����� ������
      return college;
   }
   public String getfMajor() {   // ��1���� ������
      return fMajor;
   }
   public String getsMajor()  {   // ������ ������
      return sMajor;
   }
   public String getdMajor()  {   // �������� ������
      return dMajor;
   }
   public String getPhoneNum() {    // ��ȭ��ȣ ������
      return phoneNum;
   }
   
   public String getEmail()  {   // �̸��� ������
      return email;
   }
   
   public String getChanged()  {   // ������������ ������
      return changed;
   }
   
   public String getConditions()  {   // �������� ������
      return conditions;
   }
   
   public int getTotalMajor()  {   // ���������� ������
      return totalMajor;
   }
   
   public int getTotalFinish() {   // ���̼����� ������
      return totalFinish;
   }
}
