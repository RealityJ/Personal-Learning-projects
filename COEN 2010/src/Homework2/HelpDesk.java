package Homework2;



public class HelpDesk {
  private int    time       = 0;
  private int    course     = 0;
  private int    workload   = 0;
  private String name       = "";
  private int    index      = 0;

  Student        logStudent = new Student(name, course, workload, time);

  public void step() {
    time++;
  }

 
  public void addStudent(String name, int course, int workload) {
    Student student = new Student(name, course, workload, time);
  }

 
  public int getTime() {
    return time;
  }

  public String toString() {
    String sendBack = "Time: " + this.time + ",";
    if (this.name == "") {
      String sendBackAgain = sendBack + "IDLE";
      return sendBackAgain;
    }
    else {
      String sendBackAgain = sendBack + "Helping " + this.name + "from " + this.course;
      return sendBackAgain;
    }
    // Return the status of the simulation. This produces strings like, "Time 2, Helping Jack
    // from COSC1010", or "Time 9, IDLE".
  }

  public String getLog() {

  }

  public int getCourse() {
    return time;
  }
}

class Student {
  private String name;
  private int    course;
  private int    workload;
  private int    arriveTime;

  Student(String name, int course, int workload, int time) {
    // TODO Auto-generated constructor stub
    this.name = name;
    this.course = course;
    this.workload = workload;
    this.arriveTime = time;
  }

  public int getTime() {
    return arriveTime;
  }

  public int getCourse() {
    return course;
  }

  public int getWork() {
    return workload;
  }

  public String getName() {
    return name;
  }

  public void setTime(int time) {
    this.arriveTime = time;
  }

  public void setCourse(int course) {
    this.course = course;
  }

  public void setWork(int work) {
    this.workload = work;
  }

  public void setName(String name) {
    this.name = name;
  }
}
