package by.htp.main.bean;

import java.io.Serializable;
import java.util.Date;
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
@Table(name="news")
public class News implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int idNews;
	
	@Column(name="title")
	private String title;
	
	@Column(name="brief")
	private String brief;
	
	@Column(name="content")
	private String content;
	
	@Column(name="date_creation")
	private Date newsDate;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "users_id")
	private User user;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "status_news_id")
	private StatusNews statusNews;
	
	public News () {
	}

	public int getIdNews() {
		return idNews;
	}

	public void setIdNews(int idNews) {
		this.idNews = idNews;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getNewsDate() {
		return newsDate;
	}

	public void setNewsDate(Date newsDate) {
		this.newsDate = newsDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public StatusNews getStatusNews() {
		return statusNews;
	}

	public void setStatusNews(StatusNews statusNews) {
		this.statusNews = statusNews;
	}

	@Override
	public int hashCode() {
		return Objects.hash(brief, content, idNews, newsDate, statusNews, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		News other = (News) obj;
		return Objects.equals(brief, other.brief) && Objects.equals(content, other.content) && idNews == other.idNews
				&& Objects.equals(newsDate, other.newsDate) && Objects.equals(statusNews, other.statusNews)
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "News [idNews=" + idNews + ", title=" + title + ", brief=" + brief + ", content=" + content
				+ ", newsDate=" + newsDate + ", statusNews=" + statusNews + "]";
	}
	
	
}
