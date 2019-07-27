package org.manager.filters;

import org.TableOfURI;
import org.model.service.Service;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.Constants.*;

/**
 *  For update data frpm data base on some page
 */
public class UpdateFilter implements Filter {
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

        if (TableOfURI.PAYER_REPORTS.getPagePath()
                .equals(request.getRequestURI())) {
            Service.getInstance().setPayerReports(
                    session.getAttribute(USER_NAME_PARAMETER).toString());
            session.setAttribute(REPORT_LIST,
                    Service.getInstance().getPayerReports());
        }
        if (TableOfURI.OFFICER_REPORTS.getPagePath()
                .equals(request.getRequestURI())) {
            Service.getInstance().setOfficerReports(
                    session.getAttribute(USER_NAME_PARAMETER).toString());
            session.setAttribute(REPORT_LIST,
                    Service.getInstance().getOfficerReports());
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
    }

}
