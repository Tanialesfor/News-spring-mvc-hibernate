package by.htp.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.htp.main.bean.News;
import by.htp.main.bean.User;
import by.htp.main.service.NewsService;
import by.htp.main.service.ServiceException;

@Controller
@RequestMapping("/controller")
public class NewsController {
	
	@Autowired
	private NewsService newsService;
	
	
	@RequestMapping("/goToBasePage")
	public String goToBasePage( Model theModel) {
		List<News> news;
		
		try {
			news = newsService.latestList(5);
			theModel.addAttribute("news", news);
		
		} catch (ServiceException e) {
			theModel.addAttribute("message", "error message");
			return "error";
		}
		return "baseLayout";
	}
	
	@RequestMapping("/goToNewsList")
	public String goToNewsList(Model theModel) {
		List<News> newsList;
		
		try {
			newsList = newsService.list();

		} catch (ServiceException e) {
			theModel.addAttribute("message", "error message");
			return "error";
		}
		theModel.addAttribute("news", newsList);	
		theModel.addAttribute("presentation", "newsList");
		return "baseLayout";
	}
	
	@RequestMapping("/goToViewNews")
	public String goToViewNews(@ModelAttribute("news") News news, Model theModel) {
		
		News viewNews;
		try {
		viewNews = newsService.findById(news.getIdNews());
	
		} catch (ServiceException e) {
		theModel.addAttribute("message", "error message");
		return "error";
	}
		theModel.addAttribute("news", viewNews);
		theModel.addAttribute("presentation", "viewNews");
		return "baseLayout";
	}
	
	@RequestMapping("/goToEditNews")
	public String goToEditNews(@ModelAttribute("news") News news, Model theModel) {
		
		News editNews;
		try {
		editNews = newsService.findById(news.getIdNews());
	
		} catch (ServiceException e) {
		theModel.addAttribute("message", "error message");
		return "error";
	}
		theModel.addAttribute("news", editNews);
		theModel.addAttribute("presentation", "editNews");
		return "baseLayout";
	}
	
	@RequestMapping("/goToAddNews")
	public String goToAddNews(Model theModel) {
		
		News news= new News ();
		theModel.addAttribute("news", news);
		theModel.addAttribute("presentation", "addNews");
		return "baseLayout";
	}

	@RequestMapping("/goToError")
	public String goToError(Model theModel) {
		
		theModel.addAttribute("presentation", "error");
		return "error";
	}
	
	@RequestMapping("/doAddNews")
	public String doAddNews(@ModelAttribute("news") News news, Model theModel) {
		try {
		newsService.add(news);
	
		} catch (ServiceException e) {
		theModel.addAttribute("message", "error message");
		return "error";
	}
		return "redirect:/controller/goToNewsList";
	}
	
	@RequestMapping("/doEditNews")
	public String doEditNews(@ModelAttribute("news") News news, Model theModel) {
		try {
		newsService.update(news);
	
		} catch (ServiceException e) {
		theModel.addAttribute("message", "error message");
		return "error";
	}
		return "redirect:/controller/goToNewsList";
	}
	
	@RequestMapping("/doDeleteNews")
	public String doDeleteNews(@RequestParam("newsId") int [] id, Model theModel) {
		try {
		newsService.delete(id);
	
		} catch (ServiceException e) {
		theModel.addAttribute("message", "error message");
		return "error";
	}
		return "redirect:/controller/goToNewsList";
	}
	
	
}
