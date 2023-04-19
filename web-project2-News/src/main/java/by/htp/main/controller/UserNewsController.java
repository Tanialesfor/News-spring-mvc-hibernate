package by.htp.main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.htp.main.bean.News;
import by.htp.main.bean.StatusNews;
import by.htp.main.bean.User;
import by.htp.main.service.NewsService;
import by.htp.main.service.ServiceException;
import by.htp.main.service.UserService;

@Controller
@RequestMapping("/controller")
public class UserNewsController {

	private static final String USER = "user";
	private static final String USER_DATA = "userData";
	private static final String USER_STATUS = "userStatus";
	private static final String NEWS = "news";
	private static final String NEWS_ID = "id";
	private static final String ID_NEWS_DELETE_FROM_GSP = "idNews";
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
	private static final String REG_MESSAGE = "registrationMessage";
	private static final String REGISTRATION_COMPLETED_SUCCESSFULLY = "registration completed successfully";
	private static final String REG_MESSAGE_INF = "registrationMessageInf";
	private static final String USER_ALREADY_EXIST = "user already exist";
	private static final String AUTHENTICATUIN_MESSAGE = "authenticationMessage";
	private static final String AUTHERIZATION_WAS_SUCCESSFULL = "authorization was successful";
	private static final String AUTHER_ERROR = "authenticationError";
	private static final String WRONG_LOGIN_OR_PASSWORD = "wrong login or password";
	private static final String AUTHER_MESSAGE = "autherMessage";
	private static final String NEWS_ADDED_SUCCESSFULLY = "news added successfully";
	private static final String NEWS_EDITED_SUCCESSFULLY = "news edited successfully";
	private static final String NEWS_DELETED_SUCCESSFULLY = "news deleted successfully";
	private static final String ERROR = "error";
	private static final String ERROR_MESSAGE = "errorMessage";

	@Autowired
	private NewsService newsService;

	@Autowired
	private UserService userService;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@RequestMapping("/goToBasePage")
	public String goToBasePage(Model theModel) {
		List<News> news;

		try {
			news = newsService.list();
			theModel.addAttribute(NEWS, news);
		} catch (ServiceException e) {
			theModel.addAttribute(ERROR_MESSAGE, "error from method goToBasePage in the controller ");
			return "baseLayout";
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
			theModel.addAttribute(ERROR_MESSAGE, "error from method list latest news");
			return "baseLayout";
		}
		return "baseLayout";
	}

	@RequestMapping("/goToViewNews")
	public String goToViewNews(@RequestParam("id") int id, Model theModel) {

		News viewNews;
		try {
			viewNews = newsService.findById(id);

		} catch (ServiceException e) {
			theModel.addAttribute(ERROR_MESSAGE, "error from method findById");
			return "baseLayout";
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

	@PostMapping("/doRegistration")
	public String doRegistration(@Valid @ModelAttribute("user") User user, HttpSession session,
			BindingResult bindingResult, Model theModel) {

		if (bindingResult.hasErrors()) {
			theModel.addAttribute(PRESENTATION, REGISTRATION);
			return "baseLayout";
		}
		try {
			if (userService.registration(user)) {
				session.setAttribute(REG_MESSAGE, REGISTRATION_COMPLETED_SUCCESSFULLY);
				return "redirect:goToBasePage";
			} else {
				session.setAttribute(REG_MESSAGE_INF, USER_ALREADY_EXIST);
				theModel.addAttribute(PRESENTATION, REGISTRATION);
				return "redirect:goToBasePage";
			}
		} catch (ServiceException e) {
			theModel.addAttribute(ERROR_MESSAGE, "error from method registration user");
			return "baseLayout";
		}
	}

	@PostMapping("/doSignIn")
	public String doSignIn(@RequestParam("login") String login, @RequestParam("password") String password,
			HttpSession session, Model theModel) {
		User user;
		try {
			user = userService.signIn(login, password);

			if (user != null && !user.getRole().getRoleName().equals("guest")) {
				theModel.addAttribute(USER_STATUS, ACTIVE);
				theModel.addAttribute(ROLE, user.getRole().getRoleName());
				session.setAttribute(USER_STATUS, ACTIVE);
				session.setAttribute(ROLE, user.getRole().getRoleName());
				session.setAttribute(USER_DATA, user);
				session.setAttribute(AUTHENTICATUIN_MESSAGE, AUTHERIZATION_WAS_SUCCESSFULL);
				return "redirect:goToNewsList";
			} else {
				theModel.addAttribute(USER_STATUS, NOT_ACTIVE);
				session.setAttribute(AUTHER_ERROR, WRONG_LOGIN_OR_PASSWORD);
				return "baseLayout";
			}
		} catch (ServiceException e) {
			theModel.addAttribute(ERROR_MESSAGE, "error from method doSignIn");
			return "baseLayout";
		}
	}

	@PostMapping("/doSignOut")
	public String doSignOut(HttpSession session, Model theModel) {
		session.setAttribute(USER_STATUS, NOT_ACTIVE);
		session.setAttribute(ROLE, GUEST);
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
	public String doAddNews(@Valid @ModelAttribute("news") News news, BindingResult bindingResult, HttpSession session,
			Model theModel) {

		if (bindingResult.hasErrors()) {
			session.setAttribute(PRESENTATION, ADD_NEWS);
			return "baseLayout";
		}
		news.setStatusNews(new StatusNews(1, "new"));
		User user = (User) session.getAttribute(USER_DATA);
		news.setUser(user);
		try {
			newsService.add(news);
			session.setAttribute(AUTHER_MESSAGE, NEWS_ADDED_SUCCESSFULLY);

		} catch (ServiceException e) {
			theModel.addAttribute(ERROR_MESSAGE, "error from method add news");
			return "baseLayout";
		}
		return "redirect:goToNewsList";
	}

	@RequestMapping("/goToEditNews")
	public String goToEditNews(@RequestParam("id") int id, Model theModel) {

		News editNews;
		try {
			editNews = newsService.findById(id);

		} catch (ServiceException e) {
			theModel.addAttribute(ERROR_MESSAGE, "error from method goToEditNews in the controller");
			return "baseLayout";
		}
		theModel.addAttribute(NEWS, editNews);
		theModel.addAttribute(PRESENTATION, EDIT_NEWS);
		return "baseLayout";
	}

	@RequestMapping("/doEditNews")
	public String doEditNews(@Valid @ModelAttribute("news") News news, BindingResult bindingResult, HttpSession session,
			Model theModel) {
		if (bindingResult.hasErrors()) {
			theModel.addAttribute(PRESENTATION, EDIT_NEWS);
			return "baseLayout";
		}

		news.setStatusNews(new StatusNews(1, "new"));
		User user = (User) session.getAttribute(USER_DATA);
		news.setUser(user);
		try {
			newsService.update(news);
			theModel.addAttribute(NEWS_ID, news.getIdNews());
			session.setAttribute(AUTHER_MESSAGE, NEWS_EDITED_SUCCESSFULLY);

		} catch (ServiceException e) {
			theModel.addAttribute(ERROR_MESSAGE, "error from method update news");
			return "baseLayout";
		}
		return "redirect:goToNewsList";
	}

	@RequestMapping("/doDeleteNews")
	public String doDeleteNews(HttpServletRequest request, Model theModel) {

		String[] idNews = request.getParameterValues(ID_NEWS_DELETE_FROM_GSP);
		if (idNews != null) {
			List<String> idNewsDelete = new ArrayList<String>();

			for (String newsId : idNews) {
				idNewsDelete.add(newsId);
			}

			try {
				newsService.delete(idNewsDelete);
				request.getSession().setAttribute(AUTHER_MESSAGE, NEWS_DELETED_SUCCESSFULLY);
				return "redirect:goToNewsList";
			} catch (ServiceException e) {
				theModel.addAttribute(ERROR_MESSAGE, "error from method delete news");
				return "baseLayout";
			}
		} else {
			theModel.addAttribute(ERROR_MESSAGE, "no news to delete selected");
			return "baseLayout";
		}
	}

	@RequestMapping("/goToErrorPage")
	public String goToError(Model theModel) {

		theModel.addAttribute(PRESENTATION, ERROR);
		return "baseLayout";
	}
}
