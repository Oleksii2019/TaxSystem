package org.manager.command;

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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MakeReportCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    public CommandUtility utility;

    @Mock
    public Service service;

    @InjectMocks
    private MakeReportCommand command;

    @Before
    public void setup() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(USER_NAME_PARAMETER)).thenReturn("user");
        when(request.getParameter(SELECTED_REPORT_PARAMETER)).thenReturn(EMPTY_STRING);
        when(request.getParameter(REPORT_RECLAMATION_TEXT_PARAMETER)).thenReturn(EMPTY_STRING);
    }

    @Test
    public void executeCreatePayerReport() {
        when(session.getAttribute(USER_ROLE_PARAMETER)).thenReturn(UserRole.PAYER);
        when(request.getParameter(CREATE_PAYER_REPORT_PARAMETER)).thenReturn(EMPTY_STRING);
        when(request.getParameter(CREATE_COMPLAINT_PARAMETER)).thenReturn(null);
        when(request.getParameter(EDIT_REPORT_PARAMETER)).thenReturn(null);
        when(request.getParameter(ACCEPT_REPORT_PARAMETER)).thenReturn(null);
        when(request.getParameter(REPORT_RECLAMATION_PARAMETER)).thenReturn(null);

        String path = command.execute(request);

        verify(service, times(1)).addNewReport(anyString());
    }

    @Test
    public void executeCreateComplaintReport() {
        when(session.getAttribute(USER_ROLE_PARAMETER)).thenReturn(UserRole.PAYER);
        when(request.getParameter(CREATE_PAYER_REPORT_PARAMETER)).thenReturn(null);
        when(request.getParameter(CREATE_COMPLAINT_PARAMETER)).thenReturn(EMPTY_STRING);
        when(request.getParameter(EDIT_REPORT_PARAMETER)).thenReturn(null);
        when(request.getParameter(ACCEPT_REPORT_PARAMETER)).thenReturn(null);
        when(request.getParameter(REPORT_RECLAMATION_PARAMETER)).thenReturn(null);

        String path = command.execute(request);

        verify(service, times(1)).createComplaint(anyString());
    }

    @Test
    public void executeEditReport() {
        when(session.getAttribute(USER_ROLE_PARAMETER)).thenReturn(UserRole.PAYER);
        when(request.getParameter(CREATE_PAYER_REPORT_PARAMETER)).thenReturn(null);
        when(request.getParameter(CREATE_COMPLAINT_PARAMETER)).thenReturn(null);
        when(request.getParameter(EDIT_REPORT_PARAMETER)).thenReturn(EMPTY_STRING);
        when(request.getParameter(ACCEPT_REPORT_PARAMETER)).thenReturn(null);
        when(request.getParameter(REPORT_RECLAMATION_PARAMETER)).thenReturn(null);

        String path = command.execute(request);

        verify(service, times(1)).editReportByPayer(anyString());
    }

    @Test
    public void executeAcceptReport() {
        when(session.getAttribute(USER_ROLE_PARAMETER)).thenReturn(UserRole.OFFICER);
        when(request.getParameter(CREATE_PAYER_REPORT_PARAMETER)).thenReturn(null);
        when(request.getParameter(CREATE_COMPLAINT_PARAMETER)).thenReturn(null);
        when(request.getParameter(EDIT_REPORT_PARAMETER)).thenReturn(null);
        when(request.getParameter(ACCEPT_REPORT_PARAMETER)).thenReturn(EMPTY_STRING);

        String path = command.execute(request);

        verify(service, times(1)).acceptReport(anyString(), anyString());
    }

    @Test
    public void executeReclamatReport() {
        when(session.getAttribute(USER_ROLE_PARAMETER)).thenReturn(UserRole.OFFICER);
        when(request.getParameter(CREATE_PAYER_REPORT_PARAMETER)).thenReturn(null);
        when(request.getParameter(CREATE_COMPLAINT_PARAMETER)).thenReturn(null);
        when(request.getParameter(EDIT_REPORT_PARAMETER)).thenReturn(null);
        when(request.getParameter(ACCEPT_REPORT_PARAMETER)).thenReturn(null);
        when(request.getParameter(REPORT_RECLAMATION_PARAMETER)).thenReturn(EMPTY_STRING);

        String path = command.execute(request);

        verify(service, times(1)).createReportAlternation(anyString(), anyString(), anyString());
    }

}