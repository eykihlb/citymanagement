package main.java.com.mydao.Filter;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
@WebFilter(urlPatterns = "/",filterName = "loginFilter")
public class LoginFilter implements Filter{
    public LoginFilter() {
        super();
    }

    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("/userLogin")));
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        boolean allowedPath = ALLOWED_PATHS.contains(path);
        HttpSession session=request.getSession();
        filterChain.doFilter(request, response);
        /*if (allowedPath){
            filterChain.doFilter(request, response);
        }else{
            if(session.getAttribute("sysUser")==null){ //说明此用户没有成功登录过
                PrintWriter out=response.getWriter();
                JSONObject json = new JSONObject();
                json.put("isSuccess", "0");
                json.put("desp", "Login Timeout");
                out.write(json.toString());
                out.flush();
                out.close();
            }else{
                filterChain.doFilter(request, response);
            }
        }*/
    }

    @Override
    public void destroy() {

    }
}
