package com.anjoyo.anjoyosafety.bean;

public class BlacklistcallBean {
public String Number;
public String Time;
public int Type;
public String name;
private Boolean flag = true;
public Boolean getFlag() {
	return flag;
}
public void setFlag(Boolean flag) {
	this.flag = flag;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getNumber() {
	return Number;
}
public void setNumber(String number) {
	Number = number;
}
public String getTime() {
	return Time;
}
public void setTime(String time) {
	Time = time;
}
public int getType() {
	return Type;
}
public void setType(int type) {
	Type = type;
}


}
