package by.htp.main.dao;

import java.util.List;

import by.htp.main.bean.News;


public interface NewsDAO {
	
	List<News> getList() throws NewsDAOException;
//	List<News> getLatestsList(int count) throws NewsDAOException;
	News fetchById(int id) throws NewsDAOException;
	void addNews(News news) throws NewsDAOException;
	void updateNews(News news) throws NewsDAOException;
	void deleteNewses(int[] idNewses)throws NewsDAOException;
}
