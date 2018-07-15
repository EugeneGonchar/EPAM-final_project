package controller.content;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class SessionRequestContent {

    private HashMap<String, Object> requestAttributes;
    private Map<String, String[]> requestParameters;
    private HashMap<String, Object> sessionAttributes;

    private HttpServletRequest request;

    public SessionRequestContent(HttpServletRequest request){
        requestAttributes = new HashMap<String, Object>();
        requestParameters = new HashMap<String, String[]>();
        sessionAttributes = new HashMap<String, Object>();

        this.request = request;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void extractValues(){
        requestParameters = request.getParameterMap();
    }

    public void insertRequestAttributes(){
        for(HashMap.Entry<String, Object> pair : requestAttributes.entrySet()){
             request.setAttribute(pair.getKey(), pair.getValue());
        }
    }

    public void insertSessionAttributes(){
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
        for(String word : requestParameters.get(key)){
            stringBuilder.append(word);
        }
        String string = stringBuilder.toString();
        return string;
    }
}
