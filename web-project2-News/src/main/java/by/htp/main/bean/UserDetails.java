package by.htp.main.bean;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_details")
public class UserDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
//	@NotNull(message = "please, enter field")
//	@Pattern(regexp = "^[a-zA-Z-]{1,10}$", message = "please, enter symbols from 1 to 10")
	@Column(name="name")
	private String userName;
	
//	@NotNull(message = "please, enter field")
//	@Pattern(regexp = "^[a-zA-Z-]{1,15}$", message = "please, enter symbols from 1 to 15")
	@Column(name="surname")
	private String userSurname;

//	@NotNull(message = "please, enter field")
//	@Pattern(regexp = "((19|20)\\d\\d)\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])", message = "please, enter for template YYYY-MM-DD")
	@Column(name="birthday")
	private String birthday;
	
//	@NotNull(message = "please, enter field")
//	@Pattern(regexp = "^[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9]+)*(\\.[a-zA-Z]{2,})$", message = "please, enter for template YYYY-MM-DD")
	@Column(name="email")
	private String email;
	
	@OneToOne(mappedBy = "userDetails", cascade = CascadeType.ALL)
	private User user;
	
	public UserDetails() {
       super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSurname() {
		return userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthday, email, userName, userSurname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDetails other = (UserDetails) obj;
		return Objects.equals(birthday, other.birthday) && Objects.equals(email, other.email)
				&& Objects.equals(userName, other.userName) && Objects.equals(userSurname, other.userSurname);
	}

	@Override
	public String toString() {
		return "UserDetails [userName=" + userName + ", userSurname=" + userSurname + ", birthday=" + birthday
				+ ", email=" + email + "]";
	}
	
	
}
