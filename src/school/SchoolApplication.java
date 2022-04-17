package school;

import java.io.Serializable;

public class SchoolApplication implements Serializable{
   private String stName;       // �̸� �ʵ�
   private String stNum;          // �й� �ʵ�
   private String apSemester;   // ��û�б� �ʵ�
   private String classify;   // �������ױ��� �ʵ�
   private String startDate;   // �������� �ʵ�
   private String endDate;      // �������� �ʵ�
   private String apReason;   // �������� �ʵ�
   private int abSemester;      // �����б�� �ʵ�
   private String apCount;      // ��û��
   private String apState;      // ��û��Ȳ �ʵ�
   
   //�⺻ ������
   public SchoolApplication(String stName, String stNum, String apSemester, String classify, String startDate, String endDate, String apReason, int abSemester,
         String apCount, String apState){ 
      this.stName = stName;
      this.stNum = stNum;
      this.apSemester = apSemester;
      this.classify = classify;
      this.startDate = startDate;
      this.endDate = endDate;
      this.apReason = apReason;
      this.abSemester = abSemester;
      this.apCount = apCount;
      this.apState = apState;
   }
   
   public void setstName(String stName){       // �̸� ������
      this.stName = stName;
   }      
   public void setstNum(String stNum) {   // �й� ������
      this.stNum = stNum;
   }
   public void setApSemester(String apSemester) {   // ��û�б� ������
      this.apSemester = apSemester;
   }
   public void setClassify(String classify) {   // �������ױ��� ������
      this.classify = classify;
   }
   
   public void setfStartDate(String startDate) {   // �������� ������
      this.startDate = startDate;
   }
   public void setEndDate(String endDate) {   // �������� ������
      this.endDate = endDate;
   }
   public void setApReason(String apReason) {   // �������� ������
      this.apReason = apReason;
   }
   public void setAbSemester(int abSemester) {   // �����б�� ������
      this.abSemester = abSemester;
   }
   public void setApCount(String apCount) {   // ��û�� ������
      this.apCount = apCount;
   }
   public void setApState(String apState){    // ��û���� ������
      this.apState = apState;
   }
   
   

   public String getstName(){          // �̸� ������
      return stName;
   }
   public String getstNum(){       // �й� ������
      return stNum;
   }
   public String getApSemester() {   // ��û�б� ������
      return apSemester;
   }
   public String getClassify() {   // �������ױ��� ������
      return classify;
   }
   public String getStartDate() {   // �������� ������
      return startDate;
   }
   public String getEndDate() {   // �������� ������
      return endDate;
   }
   public String getApReason() {   // �������� ������
      return apReason;
   }
   public int getAbSemester() {   // �����б�� ������
      return abSemester;
   }
   public String getApCount() {   // ��û�� ������
      return apCount;
   }
   public String getApState()  {   // ��û���� ������
      return apState;
   }
}