package school;

import java.io.Serializable;


public class Professor implements Serializable{
	   private String prName;       // �̸� �ʵ�
	   //private int year;         // �Ի�?�⵵ �ʵ�
	   private String prNum;          // ���� �ʵ�
	   private String pw;          // ��й�ȣ �ʵ�
	   private int birth;         // ������� �ʵ�
	   private String college;      // �ܰ����� �ʵ�
	   private String major;      // �ҼӰ� �ʵ�
	   private String phoneNum;   // ��ȭ��ȣ �ʵ�
	   private String email;      // �̸��� �ʵ�
	   
	   //�⺻ ������
	   public Professor(String prName, String prNum, String pw, int birth, String college, String major, String phoneNum, String email){ 
	      this.prName = prName;
	      //this.year = year;
	      this.prNum = prNum;
	      this.pw = pw;
	      this.birth = birth;
	      this.college = college;
	      this.major = major;
	      this.phoneNum = phoneNum;
	      this.email = email;
	   }
	   public Professor() {
		   
	   }
	   
	   public void setprName(String prName){       // �̸� ������
	      this.prName = prName;
	   }      
	   /*public void setYear(int year) {   // �Ի�⵵ ������
	      this.year = year;
	   }*/
	   public void setprNum(String prNum) {   // ���� ������
	      this.prNum = prNum;
	   }
	   public void setPw(String pw) {   // ��й�ȣ ������
	      this.pw = pw;
	   }
	   public void setBirth(int birth) {   // ������� ������
	      this.birth = birth;
	   }
	   public void setCollege(String college) {   // �ܰ����� ������
	      this.college = college;
	   }
	   public void setMajor(String major) {   // �ҼӰ� ������
	      this.major = major;
	   }
	   public void setPhoneNum(String phoneNum){    // ��ȭ��ȣ ������
	      this.phoneNum = phoneNum;
	   }
	   public void setEmail(String email) {   // �̸��� ������
	      this.email = email;
	   }

	   public String getprName(){          // �̸� ������
	      return prName;
	   }
	   /*public int getYear(){       // �Ի�⵵ ������
	      return year;
	   }*/
	   public String getprNum(){       // ���� ������
	      return prNum;
	   }
	   public String getPw() {   // ��й�ȣ ������
	      return pw;
	   }
	   public int getBirth() {   // ������� ������
	      return birth;
	   }
	   public String getCollege() {   // �ܰ����� ������
	      return college;
	   }
	   public String getMajor() {   // �ҼӰ� ������
	      return major;
	   }
	   public String getPhoneNum() {    // ��ȭ��ȣ ������
	      return phoneNum;
	   }
	   
	   public String getEmail()  {   // �̸��� ������
	      return email;
	   }
	}