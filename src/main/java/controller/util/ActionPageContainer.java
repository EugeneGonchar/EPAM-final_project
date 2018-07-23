package controller.util;

public class ActionPageContainer {
    private String page;
    private URLAction urlAction;

    public ActionPageContainer(String page, URLAction urlAction){
        this.page = page;
        this.urlAction = urlAction;
    }

    public String getPage() {
        return page;
    }

    public URLAction getUrlAction() {
        return urlAction;
    }
}
