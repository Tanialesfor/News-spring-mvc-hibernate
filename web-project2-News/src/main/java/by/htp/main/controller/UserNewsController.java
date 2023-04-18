package by.htp.main.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	private static final String JSP_PASSWORD_PARAM = "password";
	private static final String USER = "user";
	private static final String USER_STATUS = "userStatus";
	private static final String NEWS = "news";
	private static final String ACTIVE = "active";
	private static final String NOT_ACTIVE = "not active";
	private static final String ROLE = "role";
	private static final String GUEST = "guest";
	private static final String PRESENTATION = "presentation";
	private static final String VIEW_NEWS = "viewNews";
	private static final String NEWS_LIST = "newsList";
	private static final String EDIT_NEWS = "editNews";
	private static final String ADD_NEWS = "addNews";
	private static final String REGISTRATION = "registration";
	private static final String ERROR = "error";
	private static final String ERROR_MESSAGE = "errorMessage";
	
	@Autowired
	private NewsService newsService;

	@Autowired
	private UserService userService;

	@RequestMapping("/goToBasePage")
	public String goToBasePage(Model theModel) {
		List<News> news;

		try {
			news = newsService.list();
			theModel.addAttribute(NEWS, news);
		} catch (ServiceException e) {
			theModel.addAttribute(ERROR_MESSAGE, e.getMessage());
			return "redirect:goToErrorPage";
		}
		return "baseLayout";
	}

	@RequestMapping("/goToNewsList")
	public String goToNewsList(Model theModel) {
		List<News> newsList;

		try {
			newsList = newsService.list();
			theModel.addAttribute(NEWS, newsList);
			theModel.addAttribute(PRESENTATION, NEWS_LIST);			

		} catch (ServiceException e) {
			theModel.addAttribute(ERROR_MESSAGE, e.getMessage());
			return "redirect:goToErrorPage";
		}
		return "baseLayout";
	}

	@RequestMapping("/goToViewNews")
	public String goToViewNews(@RequestParam("id") int id, Model theModel) {

		News viewNews;
		try {
			viewNews = newsService.findById(id);

		} catch (ServiceException e) {
			theModel.addAttribute(ERROR_MESSAGE, e.getMessage());
			return "redirect:goToErrorPage";
		}
		theModel.addAttribute(NEWS, viewNews);
		theModel.addAttribute(PRESENTATION, VIEW_NEWS);
		return "baseLayout";
	}

		
	@RequestMapping("/goToRegistrationPage")
	public String goToRegistrationPage(Model theModel) {

		User user = new User();
		theModel.addAttribute(USER, user);
		theModel.addAttribute(PRESENTATION, REGISTRATION);
		return "baseLayout";
	}

	private static final String AUTHER_MESSAGE_REG = "autherMessageReg";
//	private static final String REGISTRATION_COMPLETED_SUCCESSFULLY = "local.doRegistration.auther.message.text";
	private static final String REGISTRATION_COMPLETED_SUCCESSFULLY = "registration completed successfully";
	private static final String AUTHER_INF_REG = "autherInfReg";
//	private static final String USER_ALREADY_EXIST = "local.doRegistration.auther.inf.text";
	private static final String USER_ALREADY_EXIST = "user already exist";
	
	@PostMapping("/doRegistration")
	public String doRegistration(@ModelAttribute("user") User user, HttpServletRequest request, Model theModel) {
		try {
			if(userService.registration(user)) {
				request.getSession().setAttribute(AUTHER_MESSAGE_REG, REGISTRATION_COMPLETED_SUCCESSFULLY);
				return "redirect:goToNewsList";
			} else {
				request.getSession().setAttribute(AUTHER_INF_REG, USER_ALREADY_EXIST);
				theModel.addAttribute(PRESENTATION, REGISTRATION);
				return "baseLayout";
			}
		} catch (ServiceException e) {
			theModel.addAttribute(ERROR_MESSAGE, e.getMessage());
			return "redirect:goToErrorPage";
		}
	}

	
//	private static final String WRONG_LOGIN_OR_PASSWORD = "local.signIn.auther.error.text";
	private static final String AUTHER_ERROR = "AuthenticationError";
	private static final String WRONG_LOGIN_OR_PASSWORD = "wrong login or password";
	
	@PostMapping("/doSignIn")
	public String doSignIn(@RequestParam("login") String login, @RequestParam("password") String password, HttpSession session, Model theModel) {
	User user;
		try {
            user=userService.signIn(login, password);
//			String role = userService.signIn(login, password);

			if (user!=null && !user.getRole().getRoleName().equals("guest")) {
				theModel.addAttribute(USER_STATUS, ACTIVE);
				theModel.addAttribute(ROLE, user.getRole().getRoleName());
				session.setAttribute(USER_STATUS, ACTIVE);
				session.setAttribute(ROLE, user.getRole().getRoleName());
				return "redirect:goToNewsList";
			} else {
				theModel.addAttribute(USER_STATUS, NOT_ACTIVE);
				session.setAttribute(AUTHER_ERROR, WRONG_LOGIN_OR_PASSWORD);
				return "baseLayout";
			}
		} catch (ServiceException e) {
			theModel.addAttribute(ERROR_MESSAGE, e.getMessage());
			return "redirect:goToErrorPage";
		}
	}

	@PostMapping("/doSignOut")
	public String doSignOut(Model theModel) {
		theModel.addAttribute(USER_STATUS, NOT_ACTIVE);
		theModel.addAttribute(ROLE, GUEST);
		return "redirect:goToBasePage";
	}

	

	@RequestMapping("/goToAddNews")
	public String goToAddNews(Model theModel) {

		News news = new News();
		theModel.addAttribute(NEWS, news);
		theModel.addAttribute(PRESENTATION, ADD_NEWS);
		return "baseLayout";
	}
	
	@PostMapping("/doAddNews")
	public String doAddNews(@ModelAttribute("news") News news, Model theModel) {
		try {
			newsService.add(news);

		} catch (ServiceException e) {
			theModel.addAttribute(ERROR_MESSAGE, e.getMessage());
			return "redirect:goToErrorPage";
		}
		return "redirect:goToNewsList";
	}

	@RequestMapping("/goToEditNews")
	public String goToEditNews(@RequestParam("id") int id, Model theModel) {

		News editNews;
		try {
			editNews = newsService.findById(id);

		} catch (ServiceException e) {
			theModel.addAttribute(ERROR_MESSAGE,e.getMessage());
			return "redirect:goToErrorPage";
		}
		theModel.addAttribute(NEWS, editNews);
		theModel.addAttribute(PRESENTATION, EDIT_NEWS);
		return "baseLayout";
	}
	
	@RequestMapping("/doEditNews")
	public String doEditNews(@ModelAttribute("news") News news, Model theModel) {
		try {
			newsService.update(news);

		} catch (ServiceException e) {
			theModel.addAttribute(ERROR_MESSAGE, e.getMessage());
			return "redirect:goToErrorPage";
		}
		return "redirect:goToNewsList";
	}

	@RequestMapping("/doDeleteNews")
	public String doDeleteNews(@RequestParam("id") List<String> id, Model theModel) {
		try {
			newsService.delete(id);

		} catch (ServiceException e) {
			theModel.addAttribute(ERROR_MESSAGE, e.getMessage());
			return "redirect:goToErrorPage";
		}
		return "redirect:goToNewsList";
	}

	@RequestMapping("/goToErrorPage")
	public String goToError(Model theModel) {

		theModel.addAttribute(PRESENTATION, ERROR);
		return "baseLayout";
	}
}
