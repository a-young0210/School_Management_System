package school;

//�л� �б⺰ ���� - �л� ��ٱ���, ������û ����
public class StudentScore {
 
 //����
 //�����
 private String leName;
 //�м���ȣ
 private String leNum;
 //�̼�����
 private String division;
 //����
 private int credit;
 //���ǽð�
 private String time;
 //�����̸�
 private String prName;
 //����
 private int scoreCredit;
 //���
 private String grade;
 //�����б�
 private String semester;
 
 public StudentScore(String leName, String leNum, String division, int credit,
       String time, String prName, int scoreCredit, String grade, String semester)
 {
    this.leName = leName;
    this.leNum = leNum;
    this.division = division;
    this.credit = credit;
    this.time = time;
    this.prName = prName;
    this.scoreCredit = scoreCredit;
    this.grade = grade;
    this.semester = semester;
 }   
}