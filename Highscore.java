package game;

public class Highscore {
private int hid;
private String hname;
private int score;
public Highscore (int hid, String hname, int score) {
	super();
	this.hid=hid;
	this.hname=hname;
	this.score=score;
}
public void getHighscore (int hid, String hname, int score) {
	this.hid=hid;
	this.hname=hname;
	this.score=score;
}
public int getHid() {
	return hid;
}
public void setHid(int hid) {
	this.hid = hid;
}
public String getHname() {
	return hname;
}
public void setHname(String hname) {
	this.hname = hname;
}
public int getScore() {
	return score;
}
public void setScore(int score) {
	this.score = score;
}
}
