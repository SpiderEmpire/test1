package services;

import java.util.Date;

public class User {
 String email,pwd,message;
 String fnm,lnm,phone,MeterNo,region,username,status,consumer;
 int id, usage,UId;
 String filenm,filepath;
 Date date;
 

int pin;
 String address; 
 String serverid;
 String fromdate,todate;
 public String getConsumer() {
		return consumer;
	}

	public void setConsumer(String consumer) {
		this.consumer = consumer;
	}
public String getFromdate() {
	return fromdate;
}

public void setFromdate(String fromdate) {
	this.fromdate = fromdate;
}

public String getTodate() {
	return todate;
}

public void setTodate(String todate) {
	this.todate = todate;
}

public String getServerid() {
	return serverid;
}

public void setServerid(String serverid) {
	this.serverid = serverid;
}

public int getPin() {
	return pin;
}

public void setPin(int pin) {
	this.pin = pin;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}

public Date getDate() {
	return date;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}
public String getFilenm() {
	return filenm;
}

public void setFilenm(String filenm) {
	this.filenm = filenm;
}

public String getFilepath() {
	return filepath;
}

public void setFilepath(String filepath) {
	this.filepath = filepath;
}

public int getUId() {
	return UId;
}

public void setUId(int uId) {
	UId = uId;
}

public int getUsage() {
	return usage;
}

public void setUsage(int usage) {
	this.usage = usage;
}

public String getRegion() {
	return region;
}

public void setRegion(String region) {
	this.region = region;
}

public String getMeterNo() {
	return MeterNo;
}

public void setMeterNo(String meterNo) {
	MeterNo = meterNo;
}

public String getEmail() {
	return email;
}

public String getFnm() {
	return fnm;
}

public void setFnm(String fnm) {
	this.fnm = fnm;
}

public String getLnm() {
	return lnm;
}

public void setLnm(String lnm) {
	this.lnm = lnm;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPwd() {
	return pwd;
}

public void setPwd(String pwd) {
	this.pwd = pwd;
}


public void setDate(Date date) {
	this.date = date;
}

public Date getdate(Date date) {
	// TODO Auto-generated method stub
	return date;
	
}
}
