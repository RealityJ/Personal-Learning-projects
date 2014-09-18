package Homework2;

public class HelpDesk {
	private String            name         = null;
	private int               time         = 0;
	private int               work         = 0;
	private int               course       = 0;

	DSLinkedStack<Student>    StudentStack = new DSLinkedStack<Student>();
	DSLinkedStack<LogStudent> LogStack     = new DSLinkedStack<LogStudent>();

	public String toString() {
		String returnString = "Time " + time + ",";
		if (StudentStack.isEmpty()) {
			returnString = returnString + "IDLE";
		}
		else {
			Student student = null;
			try {
				student = StudentStack.top();
			}
			catch (StackUnderflowException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			returnString = returnString + "Helping " + student.getName() + " from COSC" + student.getCourse();
		}
		return returnString;
	}

	public void step() {
		if (!StudentStack.isEmpty()) {
			try {
				Student student = StudentStack.top();
				work = student.getWork() - 1;
				student.setWork(work);
				if (work == 0) {
					String logName = student.getName();
					int logCourse = student.getCourse();
					int logTime = time;
					LogStudent logStudent = new LogStudent(logName, logCourse, logTime, false);
					LogStack.push(logStudent);
					StudentStack.pop();
				}
			}
			catch (StackUnderflowException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		time++;
	}

	public String getLog() {
		String logString = "";
		LogStudent logStudent = null;
		while (!LogStack.isEmpty()) {
			logString = "";
			try {
				logStudent = LogStack.top();
			}
			catch (StackUnderflowException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logString = "Time: " + logStudent.getLogTime() + ", ";
			if (logStudent.getArriving()) {
				logString = logString + "Started";
			}
			else {
				logString = logString + "Finished";
			}
			logString = logString + " helping " + logStudent.getLogName() + " from COSC" + logStudent.getLogCourse();
			System.out.println(logString);
			try {
				LogStack.pop();
			}
			catch (StackUnderflowException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "";
	}

	public void addStudent(String name, int course, int work) {
		Student student = new Student(name, course, work);
		if (!StudentStack.isEmpty()) {
			try {
				Student oldStudent = StudentStack.top();
				if (student.getCourse() < oldStudent.getCourse()) {
					StudentStack.push(student);
					LogStudent logStudent = new LogStudent(name, course, time, true);
					LogStack.push(logStudent);
				}
			}
			catch (StackUnderflowException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			StudentStack.push(student);
			LogStudent logStudent = new LogStudent(name, course, time, true);
			LogStack.push(logStudent);
		}

	}

	public int getTime() {
		return time;
	}
}

class LogStudent {
	private int     Ltime;
	private int     Lcourse;
	private String  Lname;
	private boolean isArriving;

	LogStudent(String name, int course, int time, boolean isArriving) {
		this.Lname = name;
		this.Lcourse = course;
		this.Ltime = time;
		this.isArriving = isArriving;
	}

	public int getLogTime() {
		return Ltime;
	}

	public int getLogCourse() {
		return Lcourse;
	}

	public String getLogName() {
		return Lname;
	}

	public boolean getArriving() {
		return isArriving;
	}

	public void setArriving(boolean status) {
		isArriving = status;
	}
}

class Student {
	private int    Swork;
	private int    Scourse;
	private String Sname;

	Student(String name, int course, int work) {
		this.Sname = name;
		this.Scourse = course;
		this.Swork = work;
	}

	public String getName() {
		return Sname;
	}

	public int getCourse() {
		return Scourse;
	}

	public int getWork() {
		return Swork;
	}

	public void setWork(int newWork) {
		Swork = newWork;
	}
}

// ------------------------------------------------------------------//
class DSLinkedStack<T> implements DSUnboundedStackInterface<T> {
	private DSLLNode<T> top;

	public DSLinkedStack() {
		top = null;
	}

	public void push(T element) {
		top = new DSLLNode<T>(element, top);
	}

	public void pop() throws StackUnderflowException {
		if (!isEmpty())
			top = top.getNext();
		else
			throw new StackUnderflowException("Top attempted on empty stack.");
	}

	public T top() throws StackUnderflowException {
		if (!isEmpty())
			return top.getData();
		else
			throw new StackUnderflowException("Top attempted on empty stack.");
	}

	public boolean isEmpty() {
		return (top == null);
	}
}

interface DSUnboundedStackInterface<T> extends DSStackInterface<T> {
	void push(T element);
}

interface DSStackInterface<T> {
	void pop() throws StackUnderflowException;

	T top() throws StackUnderflowException;

	boolean isEmpty();
}

class DSLLNode<T> {
	private T           data;
	private DSLLNode<T> next;

	public DSLLNode(T data) {
		this(data, null);
	}

	public DSLLNode(T data, DSLLNode<T> next) {
		this.data = data;
		this.next = next;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setNext(DSLLNode<T> next) {
		this.next = next;
	}

	public T getData() {
		return this.data;
	}

	public DSLLNode<T> getNext() {
		return this.next;
	}
}

interface DSBoundedStackInterface<T> extends DSStackInterface<T> {
	void push(T element) throws StackOverflowException;

	boolean isFull();
}

class StackOverflowException extends Exception {
	public StackOverflowException(String s) {
		super(s);
	}

}

class StackUnderflowException extends Exception {

	public StackUnderflowException(String s) {
		super(s);
	}
}