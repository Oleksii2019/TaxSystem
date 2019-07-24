package org.manager.filters;

import org.model.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.Constants.*;

/**
 *  For servlet internalization according to user's choice on page
 *  (English or Ukrainian)
 */
public class Internalization implements Filter {
    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        if (session.isNew()) {
            session.setAttribute(LANGUAGE_PARAMETER, Localization.UK.toString());
            session.setAttribute(USER_NAME_PARAMETER, GUEST_USER_NAME);
            session.setAttribute(USER_ROLE_PARAMETER, UserRole.GUEST.toString());
        }
        String reqLanguage = request.getParameter("lang");
        if (Localization.UK.toString().equalsIgnoreCase(reqLanguage)
                || Localization.EN.toString().equalsIgnoreCase(reqLanguage)) {
            session.setAttribute(LANGUAGE_PARAMETER, reqLanguage);
        }

//        if (req.getParameter("cookieLocale") != null) {
//            Cookie cookie = new Cookie("lang", req.getParameter("cookieLocale"));
//            res.addCookie(cookie);
//        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
    }

}
