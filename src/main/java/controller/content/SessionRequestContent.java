package controller.content;

import com.mysql.jdbc.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class SessionRequestContent {

    private HashMap<String, Object> requestAttributes;
    private Map<String, String[]> requestParameters;
    private HashMap<String, Object> sessionAttributes;

    private HttpServletRequest request;

    public SessionRequestContent(HttpServletRequest request){
        requestAttributes = new HashMap<>();
        requestParameters = new HashMap<>();
        sessionAttributes = new HashMap<>();

        this.request = request;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    private void extractRequestParameters(){
        requestParameters = request.getParameterMap();
        for(HashMap.Entry<String, String[]> pair : requestParameters.entrySet()){
            System.out.println("Request key: " + pair.getKey());
        }
    }

    private void extractSessionAttributes(){
        Enumeration<String> keys = request.getSession().getAttributeNames();
        while(keys.hasMoreElements()){
            String key = keys.nextElement();
            Object value = request.getSession().getAttribute(key);
            sessionAttributes.put(key, value);
        }
    }

    public void extractValues(){
        extractRequestParameters();
        extractSessionAttributes();
    }

    public void insertRequestAttributes(){
        for(HashMap.Entry<String, Object> pair : requestAttributes.entrySet()){
            request.setAttribute(pair.getKey(), pair.getValue());
        }
    }

    private void insertSessionAttributes(){
        for(HashMap.Entry<String, Object> pair : sessionAttributes.entrySet()){
            request.getSession().setAttribute(pair.getKey(), pair.getValue());
        }
    }

    public void insertReqSesAttributes(){
        insertRequestAttributes();
        insertSessionAttributes();
    }

    public void add2RequestAttributes(String key, Object value){
        requestAttributes.put(key, value);
    }

    public void add2SessionAttributes(String key, Object value){
        sessionAttributes.put(key, value);
    }

    public void removeSessionAttribute(String key){
        if (sessionAttributes.containsKey(key)){
            sessionAttributes.remove(key);
            request.getSession().removeAttribute(key);
        } else request.getSession().removeAttribute(key);
    }

    public String getRequestParameter(String key){
        StringBuilder stringBuilder = new StringBuilder();

        if(!requestParameters.isEmpty()){
            for(String word : requestParameters.get(key)){
                stringBuilder.append(word);
            }
        } else {
            System.out.println("Empty request parameter: " + key);
        }
        return stringBuilder.toString();
    }

    public String getRequestHeader(String s){
        return request.getHeader(s);
    }

    public Object getSessionAttribute(String key){
        return sessionAttributes.get(key);
    }

    public Part getPart(String s) throws ServletException, IOException {
        return request.getPart(s);
    }

    public String getRealPath(String s){
        return request.getServletContext().getRealPath(s);
    }
}
