package by.htp.main.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.Valid;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotNull(message = "please, enter field")
	@Pattern(regexp = "^[a-zA-Z0-9-]{1,10}$", message = "please, enter correctly")
	@Column(name = "login")
	private String login;

	@NotNull(message = "please, enter field")
	@Column(name = "password")
	private String password;

	@Column(name = "date_registration")
	private String dateRegistration;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "roles_id")
	private Role role;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "status_id")
	private StatusUser statusUser;

	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_details_id")
	private UserDetails userDetails;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH })
	private List<News> news;

	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDateRegistration() {
		return dateRegistration;
	}

	public void setDateRegistration(String dateRegistration) {
		this.dateRegistration = dateRegistration;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public StatusUser getStatusUser() {
		return statusUser;
	}

	public void setStatusUser(StatusUser statusUser) {
		this.statusUser = statusUser;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public List<News> getNews() {
		return news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateRegistration, id, login, news, password, role, statusUser, userDetails);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(dateRegistration, other.dateRegistration) && id == other.id
				&& Objects.equals(login, other.login) && Objects.equals(news, other.news)
				&& Objects.equals(password, other.password) && Objects.equals(role, other.role)
				&& Objects.equals(statusUser, other.statusUser) && Objects.equals(userDetails, other.userDetails);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", dateRegistration="
				+ dateRegistration + ", role=" + role + ", statusUser=" + statusUser + ", userDetails=" + userDetails
				+ ", news=" + news + "]";
	}

}
