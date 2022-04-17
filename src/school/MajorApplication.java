package school;

import java.io.Serializable;

public class MajorApplication implements Serializable{
   private String stName;       // �̸� �ʵ�
   private String stNum;          // �й� �ʵ�
   private String classify;   // ��û���� �ʵ�
   private String apSemester;   // ��û�б� �ʵ�
   private String apMajor;      // ��û���� �ʵ�
   private String apDate;      // ��û���� �ʵ�
   private String apReason;   // ��û���� �ʵ�
   private String apState;      // ��û���� �ʵ�
   
   //�⺻ ������
   public MajorApplication(String stName, String stNum, String classify, String apSemester, String apMajor, String apDate, String apReason, String apState){ 
      this.stName = stName;
      this.stNum = stNum;
      this.classify = classify;
      this.apSemester = apSemester;
      this.apMajor = apMajor;
      this.apDate = apDate;
      this.apReason = apReason;
      this.apState = apState;
   }
   
   public void setstName(String stName){       // �̸� ������
      this.stName = stName;
   }      
   public void setstNum(String stNum) {   // �й� ������
      this.stNum = stNum;
   }
   public void setClassify(String classify) {   // ��û���� ������
      this.classify = classify;
   }
   public void setApSemester(String apSemester) {   // ��û�б� ������
      this.apSemester = apSemester;
   }
   public void setfApMajor(String apMajor) {   // ��û���� ������
      this.apMajor = apMajor;
   }
   public void setApDate(String apDate) {   // ��û���� ������
      this.apDate = apDate;
   }
   public void setApReason(String apReason) {   // ��û���� ������
      this.apReason = apReason;
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
   public String getClassify() {   // ��û���� ������
      return classify;
   }
   public String getApSemester() {   // ��û�б� ������
      return apSemester;
   }
   public String getApMajor() {   // ��û���� ������
      return apMajor;
   }
   public String getApDate() {   // ��û���� ������
      return apDate;
   }
   public String getApReason() {   // ��û���� ������
      return apReason;
   }
   public String getApState()  {   // ��û���� ������
      return apState;
   }
}