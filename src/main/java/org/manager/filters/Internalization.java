package org.manager.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.Constants.LANGUAGE_PARAMETER;
import static org.Constants.Localization;

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

        String reqLanguage = request.getParameter("lang");
        if (Localization.UK.toString().equalsIgnoreCase(reqLanguage)
                || Localization.EN.toString().equalsIgnoreCase(reqLanguage)) {
            session.setAttribute(LANGUAGE_PARAMETER, reqLanguage);
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
    }

}
