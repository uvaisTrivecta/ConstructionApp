package com.trivecta.attendencetracker.model.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the biometric_info database table.
 * 
 */
@Entity
@Table(name="BIOMETRIC_INFO")
@NamedQueries ({
	@NamedQuery(name="BiometricInfo.findAll", query="SELECT b FROM BiometricInfo b"),
	@NamedQuery(name="BiometricInfo.findByLabourId", query="SELECT b FROM BiometricInfo b where b.labourMstr.id = :labourId")
})
public class BiometricInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	private byte[] profileImage;

	private String profileName;

	//bi-directional one-to-one association to LabourMstr
	@OneToOne
	@JoinColumn(name="labourId", referencedColumnName="id")
	private LabourMstr labourMstr;

	public BiometricInfo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getProfileImage() {
		return this.profileImage;
	}

	public void setProfileImage(byte[] profileImage) {
		this.profileImage = profileImage;
	}

	public String getProfileName() {
		return this.profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public LabourMstr getLabourMstr() {
		return this.labourMstr;
	}

	public void setLabourMstr(LabourMstr labourMstr) {
		this.labourMstr = labourMstr;
	}
}