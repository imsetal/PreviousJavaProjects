package cn.yzw.device.web.servlet;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BaseServlet extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    ValueFilter filter = new ValueFilter() {
        @Override
        public Object process(Object object, String name, Object value) {
            if(value instanceof BigDecimal || value instanceof Double || value instanceof Float){
                DecimalFormat df   =new   java.text.DecimalFormat("#.00");
                return df.format(value);
            }
            return value;
        }
    };

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

        logger.info("init");
        super.init(servletConfig);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置请求响应编码，防止中文乱码

        //2.获取浏览器的请求路径
        String requestPath = request.getRequestURI().replaceAll(request.getContextPath(), "");

        //3.拆解出方法名称
        String methodName = requestPath.substring(requestPath.lastIndexOf("/") + 1);
        logger.info("path [ " + requestPath + " ] ");


        //4.通过反射获取到方法实例
        Class<? extends BaseServlet> cls = this.getClass();

        Method method = null;
        try {
                method = cls.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        Object result = null;
        if (method != null){
            try {
                //5.调用方法
                result = method.invoke(this,request,response);
                logger.info(" method [ " + method + "] was invoke ");
                //如果返回值不为String类型，直接将返回值转换为Json类型数据响应给浏览器
                if (result.getClass() != String.class){
                    response.setContentType("application/json;charset=utf-8");
                    response.getWriter().write(JSON.toJSONString(result,filter));
                //如果返回值为String类型，则进行页面跳转操作
                }else {
                    //1.将返回值强转为String
                    String _result = (String) result;
                    //2.判断返回值中是否有 “ ： ”，如果没有，进行转发操作
                     if (!_result.contains(":")){
                         request.getRequestDispatcher(request.getContextPath() + _result).forward(request,response);
                         logger.info("request forward to " + _result);
                         //3.如果有，则进行如下操作
                     }else {
                         int index = _result.indexOf(":");//获取“ ： ”的位置下标
                         String bz = _result.substring(0,index);//获取 “ ： ”前的字符串，操作前缀
                         String path = _result.substring(index + 1);//获取 “ ： ”后的字符串，跳转路径
                         //4. 如果前缀为 ‘ r ’，也就是" redirect ",进行重定向操作
                         if ("r".equalsIgnoreCase(bz)){
                             logger.info("request redirect to " + path);
                             response.sendRedirect(request.getContextPath() + path);
                             //5. 如果前缀为 ‘ f ’，也就是" forward ",进行转发操作
                         }else if ("f".equalsIgnoreCase(bz)){
                             logger.info("request forward to " + path);
                             request.getRequestDispatcher(request.getContextPath() + path).forward(request,response);
                         }
                     }
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    }

}
