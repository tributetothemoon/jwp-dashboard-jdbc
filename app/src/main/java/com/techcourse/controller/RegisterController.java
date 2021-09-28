package com.techcourse.controller;

import com.techcourse.dao.UserDao;
import com.techcourse.domain.User;
import com.techcourse.repository.H2DataBaseConfig;
import com.techcourse.repository.InMemoryUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nextstep.mvc.view.JspView;
import nextstep.mvc.view.ModelAndView;
import nextstep.web.annotation.Controller;
import nextstep.web.annotation.RequestMapping;
import nextstep.web.support.RequestMethod;

@Controller
public class RegisterController {

    private static final UserDao userDao = H2DataBaseConfig.getUserDao();

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {
        final User user = new User(
                request.getParameter("account"),
                request.getParameter("password"),
                request.getParameter("email"));
        userDao.insert(user);

        return new ModelAndView(new JspView("redirect:/index.jsp"));
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView(new JspView("/register.jsp"));
    }
}
