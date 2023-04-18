package by.htp.main.service;

import java.util.List;

import by.htp.main.bean.News;

public interface NewsService {
 
  List<News> latestList(int count)  throws ServiceException;
  List<News> list()  throws ServiceException;
  News findById(int id) throws ServiceException;
  void add(News news) throws ServiceException;
  void update(News news) throws ServiceException;
  void delete(List<String> idNews) throws ServiceException;
  
}
