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
@Table(name="status_news")
public class StatusNews implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="status_name")
	private String statusName;
	
	public StatusNews() {
		super();
	}
	
	public StatusNews(int id, String statusName) {
		super();
		this.id=id;
		this.statusName=statusName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, statusName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatusNews other = (StatusNews) obj;
		return id == other.id && Objects.equals(statusName, other.statusName);
	}

	@Override
	public String toString() {
		return "StatusNews [id=" + id + ", statusName=" + statusName + "]";
	}
		
}
