/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author mohamedk
 */
@WebServlet(name = "UpdateStudent", urlPatterns = {"/UpdateStudent", "/AddStudents"})
@MultipartConfig
public class UpdateStudent extends HttpServlet {

    @EJB
    validatorLocal validator;
    @EJB
    studentService sS;

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private static String[] fromString(String string) {
        String[] strings = string.replace("[", "").replace("]", "").split(", ");
        String result[] = new String[strings.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = strings[i];
        }
        return result;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = request.getServletPath();

        if (url.equals("/UpdateStudent")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));

                studentEntity sE = (studentEntity) sS.getStudent(id);
                request.setAttribute("Error", false);
                request.setAttribute("Edit", true);
                request.setAttribute("fname-error", false);
                request.setAttribute("lname-error", false);
                request.setAttribute("email-error", false);
                request.setAttribute("interest-error", false);
                request.setAttribute("gender-error", false);
                request.setAttribute("file-error", false);
                request.setAttribute("age-error", false);
                request.setAttribute("dob-error", false);

                request.setAttribute("fname-value", sE.getFname());
                request.setAttribute("lname-value", sE.getLname());
                request.setAttribute("email-value", sE.getEmail());

                request.setAttribute("gender-value", ((sE.getGender().equals("Male")) ? "0" : "1"));
                request.setAttribute("age-value", sE.getAge());
                SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
                String newDate = formatter.format(sE.getDob()).replace("-", "/");
                request.setAttribute("dob-value", newDate);
                //  String[] interest = new String[3];
                String[] interest = UpdateStudent.fromString(sE.getInterest());
                request.setAttribute("interest-value", interest);
            } catch (Exception e) {
                request.setAttribute("Error", true);
            }
        } else {
            request.setAttribute("Error", false);
            request.setAttribute("Edit", false);
            request.setAttribute("fname-error", false);
            request.setAttribute("lname-error", false);
            request.setAttribute("email-error", false);
            request.setAttribute("interest-error", false);
            request.setAttribute("gender-error", false);
            request.setAttribute("file-error", false);
            request.setAttribute("age-error", false);
            request.setAttribute("dob-error", false);

            request.setAttribute("fname-value", "");
            request.setAttribute("lname-value", "");
            request.setAttribute("email-value", "");
            request.setAttribute("gender-value", "");
            request.setAttribute("age-value", "");
            request.setAttribute("dob-value", "");
            String[] interest = new String[3];
            request.setAttribute("interest-value", interest);

        }
        RequestDispatcher req = request.getRequestDispatcher("WEB-INF/views/addStudent.jsp");
        req.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Create path components to save the file
        studentEntity sE = new studentEntity();
        request.setAttribute("errors", false);
        request.setAttribute("fname-error", false);
        request.setAttribute("lname-error", false);
        request.setAttribute("email-error", false);
        request.setAttribute("interest-error", false);
        request.setAttribute("gender-error", false);
        request.setAttribute("file-error", false);
        request.setAttribute("age-error", false);
        request.setAttribute("dob-error", false);
        final String path = request.getServletContext().getRealPath("");//request.getParameter("destination");
        final Part filePart = request.getPart("file");
        final String fileName = getFileName(filePart);
        String mimeType = request.getServletContext().getMimeType(fileName);

        OutputStream outImage = null;
        InputStream filecontent = null;

        try {
            if (mimeType.startsWith("image/")) {
                // It's an image.
                System.err.println("IMAGEEEEEE");
                sE.setFile(fileName);
                outImage = new FileOutputStream(new File(path + File.separator
                        + fileName));
                filecontent = filePart.getInputStream();
                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = filecontent.read(bytes)) != -1) {
                    outImage.write(bytes, 0, read);
                }
            } else {
                request.setAttribute("errors", true);
                request.setAttribute("file-error", true);
            }
        } catch (Exception e) {
            request.setAttribute("errors", true);
            request.setAttribute("file-error", true);
        } finally {
            if (outImage != null) {
                outImage.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
        }
        String fname = request.getParameter("fname");
        System.out.println(fname);
        if (!validator.isNull(fname) || !validator.length(fname, 1)) {
            request.setAttribute("errors", true);
            request.setAttribute("fname-error", true);
            request.setAttribute("fname-value", "");
        } else {
            request.setAttribute("fname-value", fname);
            sE.setFname(fname);
        }
        String lname = request.getParameter("lname");
        if (!validator.isNull(lname) || !validator.length(lname, 1)) {
            request.setAttribute("errors", true);
            request.setAttribute("lname-error", true);
            request.setAttribute("lname-value", "");
        } else {
            request.setAttribute("lname-value", lname);
            sE.setLname(lname);
        }
        String email = request.getParameter("email");
        if (!validator.isNull(fname) || !validator.length(fname, 1) || !validator.isEmail(email)) {
            request.setAttribute("errors", true);
            request.setAttribute("email-error", true);
            request.setAttribute("email-value", "");
        } else {
            request.setAttribute("email-value", email);
            sE.setEmail(email);
        }
        String[] interest = request.getParameterValues("interest");

        if (interest == null) {
            request.setAttribute("interest-error", true);
            interest = new String[3];
            request.setAttribute("interest-value", interest);
        } else {
            request.setAttribute("interest-value", interest);
            String interestString = Arrays.toString(interest);

            sE.setInterest(interestString);
        }
        String gender = request.getParameter("gender");
        if (!validator.isNull(gender) || !validator.isInt(gender)) {
            request.setAttribute("errors", true);
            request.setAttribute("gender-error", true);
            request.setAttribute("gender-value", "");
        } else {
            request.setAttribute("gender-value", gender);
            if (gender.equals("0")) {
                sE.setGender(Gender.Male);
            } else {
                sE.setGender(Gender.Female);
            }
        }

        String age = (String) request.getParameter("age");
        if (!validator.isNull(age) || !validator.isInt(age) || !validator.intRange(age, 10, 90)) {
            request.setAttribute("errors", true);
            request.setAttribute("age-error", true);
            request.setAttribute("age-value", "");
        } else {
            request.setAttribute("age-value", age);
            sE.setAge(Integer.parseInt(age));
        }
        String dob = request.getParameter("dob");
        if (!validator.isNull(dob) || !validator.length(dob, 1) || !validator.isDate(dob)) {
            request.setAttribute("errors", true);
            request.setAttribute("dob-error", true);
            request.setAttribute("dob-value", "");
        } else {
            request.setAttribute("dob-value", dob);
            String dopArr[] = dob.split("\\/");
            int month = Integer.parseInt(dopArr[0]);
            int day = Integer.parseInt(dopArr[1]);
            int year = Integer.parseInt(dopArr[2]);
            if ((month > 12 && month < 1) || (day > 31 && day < 1)) {
                request.setAttribute("errors", true);
                request.setAttribute("dob-error", true);
                request.setAttribute("dob-value", "");
            } else {
                Calendar cal = Calendar.getInstance();
                cal.set(year, month, day);
                Date d = cal.getTime();
                sE.setDob(d);
            }

        }
        //String fname = request.getParameter("fname");

        if ((Boolean) request.getAttribute("errors")) {
            RequestDispatcher req = request.getRequestDispatcher("WEB-INF/views/addStudent.jsp");
            
            req.forward(request, response);
        } else {
            String url = request.getServletPath();
            if (url.equals("/UpdateStudent")) {
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    sE.setId(id);
                    sS.updateStudent(sE);
                } catch (Exception e) {
                    System.err.println("Error !!!!");
                }
            } else {
                sS.addStduent(sE);
            }
            List<studentEntity> list = (List<studentEntity>) sS.listStudent();
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet addPass</title>");
                out.println("</head>");
                out.println("<body>");
                for (Iterator<studentEntity> i = list.iterator(); i.hasNext();) {
                    studentEntity item = i.next();
                    out.println("<h1>Fname" + item.getFname() + "</h1><br/>");
                }
                out.println("</body>");
                out.println("</html>");

            }
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
