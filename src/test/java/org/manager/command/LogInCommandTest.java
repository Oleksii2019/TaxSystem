package org.manager.command;

import org.TableOfURI;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.model.UserRole;
import org.model.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LogInCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    public CommandUtility utility;

    @Mock
    public Service service;

    @InjectMocks
    private LogInCommand command;

    @Before
    public void setup() {
        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void executePayerSuccessLogIn() {
        when(request.getParameter(LOGIN_PARAMETER)).thenReturn("payer");
        when(request.getParameter(PASSWORD_PARAMETER)).thenReturn("password");
        when(request.getParameter(USER_ROLE_PARAMETER)).thenReturn(UserRole.PAYER.toString());
        when(request.getSession().getAttribute(USER_NAME_PARAMETER)).thenReturn(GUEST_USER_NAME);
        doNothing().when(utility).setInputMistakeSign(request);
        when(utility.isUserLogged(request, "payer")).thenReturn(false);
        when(service.isPayerAuthorizedUser(anyString(), anyString())).thenReturn(true);

        String path = command.execute(request);

        verify(request).getParameter(LOGIN_PARAMETER);
        verify(utility, atLeastOnce()).isUserLogged(request, "payer");
        verify(service, atLeastOnce()).isPayerAuthorizedUser(anyString(), anyString());
        assertEquals(TableOfURI.PAYER_REPORTS.getPagePath(), path);
    }

    @Test
    public void executePayerNotSuccessLogIn() {
        when(request.getParameter(LOGIN_PARAMETER)).thenReturn("payer");
        when(request.getParameter(PASSWORD_PARAMETER)).thenReturn("password");
        when(request.getParameter(USER_ROLE_PARAMETER)).thenReturn(UserRole.PAYER.toString());
        when(request.getSession().getAttribute(USER_NAME_PARAMETER)).thenReturn(GUEST_USER_NAME);
        doNothing().when(utility).setInputMistakeSign(request);
        when(utility.getURIForRequestPage(request)).thenReturn(TableOfURI.NPERSON_LOGIN.getPagePath());
        when(utility.isUserLogged(request, "payer")).thenReturn(false);
        when(service.isPayerAuthorizedUser(anyString(), anyString())).thenReturn(false);

        String path = command.execute(request);

        verify(request).getParameter(LOGIN_PARAMETER);
        verify(utility, atLeastOnce()).isUserLogged(request, "payer");
        verify(service, atLeastOnce()).isPayerAuthorizedUser(anyString(), anyString());
        assertEquals(TableOfURI.NPERSON_LOGIN.getPagePath(), path);

    }

    @Test
    public void executeOfficerSuccessLogIn() {
        when(request.getParameter(LOGIN_PARAMETER)).thenReturn("Officer");
        when(request.getParameter(PASSWORD_PARAMETER)).thenReturn("password");
        when(request.getParameter(USER_ROLE_PARAMETER)).thenReturn(UserRole.OFFICER.toString());
        when(request.getSession().getAttribute(USER_NAME_PARAMETER)).thenReturn(GUEST_USER_NAME);
        doNothing().when(utility).setInputMistakeSign(request);
        when(service.isOfficerAuthorizedUser(anyString(), anyString())).thenReturn(true);

        String path = command.execute(request);

        verify(request).getParameter(LOGIN_PARAMETER);
        verify(utility, atLeastOnce()).isUserLogged(request, "Officer");
        verify(service, atLeastOnce()).isOfficerAuthorizedUser(anyString(), anyString());
        assertEquals(TableOfURI.OFFICER_REPORTS.getPagePath(), path);
    }

    @Test
    public void executeOfficerNotSuccessLogIn() {
        when(request.getParameter(LOGIN_PARAMETER)).thenReturn("Officer");
        when(request.getParameter(PASSWORD_PARAMETER)).thenReturn("password");
        when(request.getParameter(USER_ROLE_PARAMETER)).thenReturn(UserRole.OFFICER.toString());
        when(request.getSession().getAttribute(USER_NAME_PARAMETER)).thenReturn(GUEST_USER_NAME);
        doNothing().when(utility).setInputMistakeSign(request);
        when(utility.getURIForRequestPage(request)).thenReturn(TableOfURI.OFFICER_LOGIN.getPagePath());
        when(service.isOfficerAuthorizedUser(anyString(), anyString())).thenReturn(false);

        String path = command.execute(request);

        verify(request).getParameter(LOGIN_PARAMETER);
        verify(utility, atLeastOnce()).isUserLogged(request, "Officer");
        verify(service, atLeastOnce()).isOfficerAuthorizedUser(anyString(), anyString());
        assertEquals(TableOfURI.OFFICER_LOGIN.getPagePath(), path);
    }

}
