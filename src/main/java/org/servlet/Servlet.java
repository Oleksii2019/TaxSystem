package org.servlet;

import javax.servlet.http.*;
import java.io.IOException;

public class Servlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.getWriter().print("Hello from servlet");
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.getWriter().print("Hello from servlet");
    }
}
