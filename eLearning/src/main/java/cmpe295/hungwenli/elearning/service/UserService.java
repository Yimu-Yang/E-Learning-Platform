package cmpe295.hungwenli.elearning.service;

import cmpe295.hungwenli.elearning.model.User;
import cmpe295.hungwenli.elearning.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean register(HttpServletRequest request) {
        List<User> users = userRepository.findByUserName(request.getParameter("user_name"));
        if (users.size() != 0) {
            return false;
        }
        String userName = request.getParameter("user_name");
        String password = request.getParameter("password");
        User user = new User(userName, password);
        userRepository.save(user);
        request.getSession().setAttribute("user", userName);
        return true;
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("user") != null) {
            return;
        }
        String userName = request.getParameter("user_name");
        String password = request.getParameter("password");
        List<User> users = userRepository.findByUserName(userName);
        if (users.size() == 0 || !users.get(0).getPassword().equals(password)) {
            response.sendRedirect("/");
            return;
        }
        request.getSession().setAttribute("user", userName);
        response.sendRedirect("/content");
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("/");
    }

}
