package by.htp.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.htp.main.bean.News;
import by.htp.main.dao.NewsDAO;
import by.htp.main.dao.NewsDAOException;
import by.htp.main.service.NewsService;
import by.htp.main.service.ServiceException;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsDAO newsDAO;

	@Transactional
	@Override
	public void add(News news) throws ServiceException {

		try {
			newsDAO.addNews(news);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public void update(News news) throws ServiceException {

		try {
			newsDAO.updateNews(news);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public void delete(List<String> idNews) throws ServiceException {
		try {
			newsDAO.deleteNewses(idNews);

		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}

	}

	@Transactional
	@Override
	public List<News> list() throws ServiceException {
		try {
			return newsDAO.getList();
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public News findById(int id) throws ServiceException {
		try {
			return newsDAO.fetchById(id);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<News> latestList(int count) throws ServiceException {
		try {
			return newsDAO.getLatestsList(count);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

}
