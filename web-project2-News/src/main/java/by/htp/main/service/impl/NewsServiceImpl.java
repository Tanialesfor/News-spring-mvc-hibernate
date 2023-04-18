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
		
//	@Override
	@Transactional
	public void add(News news) throws ServiceException {
//		String title = news.getTitle();
//		String brief = news.getBriefNews();
//		String content = news.getContent();
//		String date = news.getNewsDate();
		
		  try {
					newsDAO.addNews(news);
				} catch (NewsDAOException e) {
					throw new ServiceException(e);
				}	
	}

//	@Override
	@Transactional
	public void update(News news) throws ServiceException {
				
//		String title = news.getTitle();
//		String brief = news.getBriefNews();
//		String content = news.getContent();
//		String date = news.getNewsDate();
		
		try {
			newsDAO.updateNews(news);
			} catch (NewsDAOException e) {
					throw new ServiceException(e);
				}
		}

//	@Override
	@Transactional
	public void delete(List<String> idNews) throws ServiceException {
		try {
			newsDAO.deleteNewses(idNews);

		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}

	}

////	@Override
//	@Transactional
//	public List<News> latestList(int count) throws ServiceException {
//		try {
//			return newsDAO.getLatestsList(5);
//		} catch (NewsDAOException e) {
//			throw new ServiceException(e);
//		}
//	}

//	@Override
	@Transactional
	public List<News> list() throws ServiceException {
		try {
			return newsDAO.getList();
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

//	@Override
	@Transactional
	public News findById(int id) throws ServiceException {
		try {
			return newsDAO.fetchById(id);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	
}
