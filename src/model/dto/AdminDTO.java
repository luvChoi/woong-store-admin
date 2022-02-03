package model.dto;

import java.sql.Date;

public class AdminDTO {
	//필드
	private int no; 		//관리자 일련번호
	private String id; 		//관리자 ID
	private String passwd;		//관리자 비밀번호
	private String passwdChek;	//관리자 비밀번호 확인
	private String name;		//관리자 이름
	private String dep_name;	//관리자 부서명
	private String level_staff;	//관리자 직급
	private String phone;		//관리자 연락처
	private String authority; //관리자 등급 "일반" 또는 "운영자"
	private Date regi_date;		//등록일
	private Date upd_date;		//수정일
	
	private int passChg_period;

	//Getter&Setter
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getPasswdChek() {
		return passwdChek;
	}

	public void setPasswdChek(String passwdChek) {
		this.passwdChek = passwdChek;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDep_name() {
		return dep_name;
	}

	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}

	public String getLevel_staff() {
		return level_staff;
	}

	public void setLevel_staff(String level_staff) {
		this.level_staff = level_staff;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Date getRegi_date() {
		return regi_date;
	}

	public void setRegi_date(Date regi_date) {
		this.regi_date = regi_date;
	}

	public Date getUpd_date() {
		return upd_date;
	}

	public void setUpd_date(Date upd_date) {
		this.upd_date = upd_date;
	}

	public int getPassChg_period() {
		return passChg_period;
	}

	public void setPassChg_period(int passChg_period) {
		this.passChg_period = passChg_period;
	}
}
