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

@Repository
public class NewsDAOImpl implements NewsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<News> getLatestsList(int count) throws NewsDAOException {

		try {

			Session currentSession = sessionFactory.getCurrentSession();

			Query<News> theQuery = currentSession.createQuery(
					"from News where statusNews.id=:statusPubParam or statusNews.id=:statusNewParam order by date(date_creation) desc", News.class);

			theQuery.setParameter("statusPubParam", 4); 
			theQuery.setParameter("statusNewParam", 1); 
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
			Query<News> theQuery = currentSession.createQuery(
					"from News where statusNews.id=:statusPubParam or statusNews.id=:statusNewParam order by date(date_creation) desc", News.class);
			theQuery.setParameter("statusPubParam", 4); 
			theQuery.setParameter("statusNewParam", 1);

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
	public void deleteNewses(List<String> idNewses) throws NewsDAOException {

		Session currentSession = sessionFactory.getCurrentSession();

		for (String idNews : idNewses) {
			Integer id;
			try {
				id = Integer.parseInt(idNews);
				Query theQuery = currentSession
						.createQuery("update News set statusNews.id=:statusParam where idNews=:idParam");

				theQuery.setParameter("statusParam", 2); // deleted
				theQuery.setParameter("idParam", id);
				theQuery.executeUpdate();

			} catch (NumberFormatException e) {
				throw new NewsDAOException("Error with parsing ", e);
			}

		}
	}

}
