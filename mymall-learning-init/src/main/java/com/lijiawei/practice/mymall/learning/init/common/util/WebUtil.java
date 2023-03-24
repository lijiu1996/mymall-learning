package com.lijiawei.practice.mymall.learning.init.common.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtil {

    // 返回Json给浏览器
    public static void renderJsonString(HttpServletResponse response, String string) {
        try
        {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().println(string);
            response.getWriter().flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void renderJsonString(HttpServletResponse response, String string, int state) {
        try
        {
            response.setStatus(state);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
