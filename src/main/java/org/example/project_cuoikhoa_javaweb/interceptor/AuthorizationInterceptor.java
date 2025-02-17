package org.example.project_cuoikhoa_javaweb.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;


public class AuthorizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        String userRole = (String) session.getAttribute("userRole"); // Giả sử "userRole" là attribute session của bạn

        if (userRole == null) {
            response.sendRedirect("/dangnhap");
            return false;
        }

        String uri = request.getRequestURI();
        // Kiểm tra các đường dẫn yêu cầu vai trò admin
        if (uri.startsWith("/admin") && !userRole.equals("admin")) {
            response.sendRedirect("/err");
            return false;
        }

        // Kiểm tra các đường dẫn yêu cầu vai trò users
        if(uri.startsWith("/users") && !userRole.equals("user") && !userRole.equals("admin")){
            response.sendRedirect("/err");
            return false;
        }

        return true;

    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
