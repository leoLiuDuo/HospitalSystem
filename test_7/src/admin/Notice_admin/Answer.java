package admin.Notice_admin;

public class Answer {
private String question1;
private String question2;
private String question3;
private String answer1;
private String answer2;
public String getQuestion1() {
	return question1;
}
public Answer() {
	super();
	// TODO Auto-generated constructor stub
}
public void setQuestion1(String question1) {
	this.question1 = question1;
}
public String getQuestion2() {
	return question2;
}
public void setQuestion2(String question2) {
	this.question2 = question2;
}
public String getQuestion3() {
	return question3;
}
public void setQuestion3(String question3) {
	this.question3 = question3;
}
public String getAnswer1() {
	return answer1;
}
public void setAnswer1(String answer1) {
	this.answer1 = answer1;
}
public String getAnswer2() {
	return answer2;
}
public void setAnswer2(String answer2) {
	this.answer2 = answer2;
}
public String getAnswer3() {
	return answer3;
}
public void setAnswer3(String answer3) {
	this.answer3 = answer3;
}
private String answer3;
public Answer(String question1, String question2, String question3,
		String answer1, String answer2, String answer3) {
	super();
	this.question1 = question1;
	this.question2 = question2;
	this.question3 = question3;
	this.answer1 = answer1;
	this.answer2 = answer2;
	this.answer3 = answer3;
}

}
