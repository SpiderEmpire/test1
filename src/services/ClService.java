package services;

import java.io.File;
import java.security.PublicKey;

public class ClService {

	File f;
	int fileid;
	String n;
	byte[] cipher;
	String name, region;
	PublicKey serverpub, servicepub;
	int userid;

	public String getN() {
		return n;
	}

	public void setN(String string) {
		this.n = string;
	}

	public int getFileid() {
		return fileid;
	}

	public void setFileid(int fileid) {
		this.fileid = fileid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public byte[] getCipher() {
		return cipher;
	}

	public void setCipher(byte[] cipher) {
		this.cipher = cipher;
	}

	public File getF() {
		return f;
	}

	public void setF(File f) {
		this.f = f;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public PublicKey getServerpub() {
		return serverpub;
	}

	public void setServerpub(PublicKey serverpub) {
		this.serverpub = serverpub;
	}

	public PublicKey getServicepub() {
		return servicepub;
	}

	public void setServicepub(PublicKey servicepub) {
		this.servicepub = servicepub;
	}
}
