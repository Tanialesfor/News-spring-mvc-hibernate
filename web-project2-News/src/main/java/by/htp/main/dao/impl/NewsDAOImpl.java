package by.htp.main.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import by.htp.main.bean.News;
import by.htp.main.dao.NewsDAO;
import by.htp.main.dao.NewsDAOException;

public class NewsDAOImpl implements NewsDAO {

	@Autowired
	private SessionFactory sessionFactory;

//	private static final String SELECT_LATEST_NEWS = "SELECT * FROM news.news WHERE status_news_id = ? OR status_news_id = ? ORDER BY news.news.date_creation DESC LIMIT ?";
//	private static final String SELECT_ALL_NEWS = "SELECT * FROM news.news WHERE status_news_id = ? OR status_news_id = ? ORDER BY news.news.date_creation DESC";
//	private static final String INSERT_NEWS = "INSERT INTO news.news(title, date_creation, brief, content, users_id, status_news_id) VALUES(?, ?, ?, ?, ?, ?)";
//	private static final String SELECT_NEWS_FOR_ID = "SELECT * FROM news.news WHERE id = ?";
//	private static final String UPDATE_NEWS = "UPDATE news.news SET title = ?, date_creation = ?, brief = ?, content = ?, users_id = ?, status_news_id = ? WHERE id = ?";
//	private static final String UPDATE_STATUS_NEWS = "UPDATE news.news SET status_news_id = ? WHERE id = ?";

	@Override
	public List<News> getLatestsList(int count) throws NewsDAOException {

		try {

			Session currentSession = sessionFactory.getCurrentSession();

			Query<News> theQuery = currentSession.createQuery("from News where statusNews=:statusParam", News.class);

			theQuery.setParameter("statusParam", 5); // published
			theQuery.setMaxResults(count);

			List<News> newsList = theQuery.getResultList();

			return newsList;

		} catch (HibernateException e) {
			throw new NewsDAOException("Hibernate getting error from method getLatestsList(int count)", e);
		}

	}

	@Override
	public List<News> getList() throws NewsDAOException {

		try {
			Session currentSession = sessionFactory.getCurrentSession();

			Query<News> theQuery = currentSession.createQuery("from News where statusNews=:statusParam", News.class);

			theQuery.setParameter("statusParam", 5); // published
			List<News> newsList = theQuery.getResultList();

			return newsList;

		} catch (HibernateException e) {
			throw new NewsDAOException("Hibernate getting error from method getList", e);
		}
	}

	@Override
	public void addNews(News news) throws NewsDAOException {

		try {
			Session currentSession = sessionFactory.getCurrentSession();
			currentSession.save(news);
			Query theQuery = currentSession
					.createQuery("update News set statusNews=:statusParam where idNews=:idParam", News.class);

			theQuery.setParameter("statusParam", 5); // published
			theQuery.setParameter("idParam", news.getIdNews());
			theQuery.executeUpdate();

		} catch (HibernateException e) {
			throw new NewsDAOException("Hibernate getting error from method addNews", e);
		}
	}

	@Override
	public News fetchById(int id) throws NewsDAOException {

		try {
			Session currentSession = sessionFactory.getCurrentSession();

			News news = currentSession.get(News.class, id);

			return news;

		} catch (HibernateException e) {
			throw new NewsDAOException("Hibernate getting error from method fetchById", e);
		}
	}

	@Override
	public void updateNews(News news) throws NewsDAOException {
		try {
			Session currentSession = sessionFactory.getCurrentSession();

			currentSession.update(news);
			
		} catch (HibernateException e) {
			throw new NewsDAOException("Hibernate getting error from method updateNews", e);
		}

	}

	@Override
	public void deleteNewses(int[] idNewses) throws NewsDAOException {

		try {
			Session currentSession = sessionFactory.getCurrentSession();

			for (int id : idNewses) {
				try {

					Query theQuery = currentSession
							.createQuery("update News set statusNews=:statusParam where idNews=:idParam", News.class);

					theQuery.setParameter("statusParam", 2); // deleted
					theQuery.setParameter("idParam", id);
					theQuery.executeUpdate();

//					News news = currentSession.get(News.class, id);
//					currentSession.delete(news);	

				} catch (NumberFormatException e) {
					throw new NewsDAOException("Error with parsing ", e);
				}
			}
		} catch (HibernateException e) {
			throw new NewsDAOException("Hibernate getting error from method deleteNewses", e);
		}
	}

}
