package spzc.mtd.project_decoy_mtd;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {

	private String name;
	private String pwdHash;
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwdHash() {
		return pwdHash;
	}

//	dla bezpieczenstwa przechowujemy skrot hasla
	public void setPwdHash(String pwdHash) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(pwdHash.getBytes());
		this.pwdHash = new String(messageDigest.digest());
	}

}
