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
import by.htp.main.service.UserService;

@Controller
@RequestMapping("/controller")
public class UserNewsController {
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private UserService userService;
	
	
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
	public String goToViewNews(@RequestParam("newsId") int id, Model theModel) {
		
		News viewNews;
		try {
		viewNews = newsService.findById(id);
	
		} catch (ServiceException e) {
		theModel.addAttribute("message", "error message");
		return "error";
	}
		theModel.addAttribute("news", viewNews);
		theModel.addAttribute("presentation", "viewNews");
		return "baseLayout";
	}
	
	@RequestMapping("/goToEditNews")
	public String goToEditNews(@RequestParam("newsId") int id, Model theModel) {
		
		News editNews;
		try {
		editNews = newsService.findById(id);
	
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
	
	@RequestMapping("/goToRegistrationPage")
	public String goToRegistrationPage(Model theModel) {
		
		User user= new User ();
		theModel.addAttribute("user", user);
		theModel.addAttribute("presentation", "registration");
		return "baseLayout";
	}
	
	@RequestMapping("/doRegistration")
	public String doRegistration(@ModelAttribute("user") User user, Model theModel) {
		try {
		userService.registration(user);
	
		} catch (ServiceException e) {
		theModel.addAttribute("message", "error message");
		return "error";
	}
		return "newsList";
	}
	
	@RequestMapping("/doSignIn")
	public String doSignIn(@ModelAttribute("user") User user, Model theModel) {
		try {
			
//			String role = service.signIn(login, password);
//
//			if (!role.equals("guest")) {
//				request.getSession(true).setAttribute(USER, ACTIVE);
//				request.getSession().setAttribute(ROLE, role);
//				request.getSession().setAttribute(PERMISSION_ROLE, service.isPermission(login, password));
//				response.sendRedirect("controller?command=go_to_news_list");
//			} else {
//				request.getSession(true).setAttribute(USER, NOT_ACTIVE);
//				request.setAttribute(AUTHER_ERROR, WRONG_LOGIN_OR_PASSWORD);
//				request.getRequestDispatcher("/WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		
		String role= userService.signIn(user.getLogin(), user.getPassword());
		
		if (!role.equals("guest")) {
			theModel.addAttribute("user", "active");
			theModel.addAttribute("role", "role");
		return "newsList";
		} else {
			theModel.addAttribute("user", "not active");
			return "redirect:/controller/goToBasePage";
		}
		} catch (ServiceException e) {
		theModel.addAttribute("message", "error message");
		return "error";
	  }
	}
	
	@RequestMapping("/doSignOut")
	public String doSignOut(Model theModel) {
				theModel.addAttribute("user", "not active");
				return "redirect:/controller/goToBasePage";
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
