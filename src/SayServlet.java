import com.mysql.jdbc.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.MalformedInputException;
import java.util.Enumeration;

@WebServlet(name = "SayServlet")
public class SayServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF=8");
//
//        request.setCharacterEncoding("UTF-8");
//        Reader r = request.getReader();

//        Writer w = response.getWriter();


//        try {
            // Copy one character at a time
//            int c = r.read();
//            while (c != -1) {
//                w.write(c);
//                c = r.read();
//            }
//            w.close();
//        }
//        catch (MalformedInputException mie) {
//            response.resetBuffer();
//            w.write("FAILED");
//        }
//        kak = w;
//        Prob.prob();
        PrintWriter out = response.getWriter();
        BufferedReader rd = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        String line = null;
        String message = new String();
        while ((line = rd.readLine()) != null) {

            message += line;
        }
         Parser.xmlStrDok = message;

        Parser message2 = new Parser();
        message2.parse();
        String m = Parser.xmlStr;
        out.println(m);


    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF=8");
        PrintWriter out = response.getWriter();
        BufferedReader in = request.getReader();
        try {


            out.println(in.readLine());
        } finally {
            out.close();
        }
    }


}
