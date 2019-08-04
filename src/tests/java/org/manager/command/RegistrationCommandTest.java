package org.manager.command;

import org.TableOfURI;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.model.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    public CommandUtility utility;

    @Mock
    public Service service;

    @InjectMocks
    private RegistrationCommand command;

    @Before
    public void setup() {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(LOGIN_PARAMETER)).thenReturn("payer");
        when(request.getParameter(PASSWORD_PARAMETER)).thenReturn("password");
        when(request.getParameter(REG_NAME_PARAMETER)).thenReturn("nickName");
        doNothing().when(utility).setInputMistakeSign(request);
        doNothing().when(utility).removeInputMistakeSign(request);
    }

    @Test
    public void executeSuccessRegistration() {
        when(request.getSession().getAttribute(USER_NAME_PARAMETER)).thenReturn(GUEST_USER_NAME);
        when(utility.getURIForRequestPage(request)).thenReturn(TableOfURI.NPERSON_LOGIN.getPagePath());
        when(service.isNotPayerAuthorizedUser(anyString())).thenReturn(true);

        String path = command.execute(request);

        verify(service, times(1)).addNewPayer(anyString(), anyString(), anyString());
        assertEquals(TableOfURI.HOME.getPagePath(), path);
    }

    @Test
    public void executeNotSuccessRegistration() {
        when(request.getSession().getAttribute(USER_NAME_PARAMETER)).thenReturn(GUEST_USER_NAME);
        when(utility.getURIForRequestPage(request)).thenReturn(TableOfURI.NPERSON_LOGIN.getPagePath());
        when(service.isNotPayerAuthorizedUser(anyString())).thenReturn(false);

        String path = command.execute(request);

        verify(service, times(0)).addNewPayer(anyString(), anyString(), anyString());
    }

}