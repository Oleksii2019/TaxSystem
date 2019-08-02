package org.manager.filters;

import org.TableOfURI;
import org.apache.log4j.Logger;
import org.model.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.Constants.*;

/**
 *  For users' session authorization
 */
public class Authorization implements Filter {
    private static final Logger LOGGER =
            Logger.getLogger(Authorization.class);

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
        LOGGER.info(CURRENT_USER
                + request.getSession().getAttribute(USER_NAME_PARAMETER));
        if (!checkPageAccess(request.getRequestURI(),
                request.getSession().getAttribute(USER_ROLE_PARAMETER)
                .toString())) {
            LOGGER.info(ATTEMPT_ACCESS);
            request.setAttribute(LOGOUT_COMMAND, EMPTY_STRING);
        }

        filterChain.doFilter(servletRequest,servletResponse);

    }

    private Boolean checkPageAccess(String carrentURI, String userRole) {
        boolean res = true;
        for(TableOfURI e : TableOfURI.values()) {
            if (e.getPagePath().equals(carrentURI)) {
                if (UserRole.GUEST.toString().equals(userRole)) {
                    res = e.getAccessForGuest();
                }
                if (UserRole.PAYER.toString().equals(userRole)) {
                    res = e.getAccessForPayer();
                }
                if (UserRole.OFFICER.toString().equals(userRole)) {
                    res = e.getAccessForOfficer();
                }
            }
        }
        return res;
    }

    @Override
    public void destroy() {
    }

}
