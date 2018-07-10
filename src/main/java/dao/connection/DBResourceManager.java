package dao.connection;

import java.util.ResourceBundle;

    public class DBResourceManager {

        private static final DBResourceManager instance = new DBResourceManager();
        private static final String PROPERTIES_PATH = "db";
        private ResourceBundle bundle = ResourceBundle.getBundle(PROPERTIES_PATH);

        public static DBResourceManager getInstance() {
            return instance;
        }

        public String getParameter(String key){
            return bundle.getString(key);
        }

        public String getValue(String key){
            return bundle.getString(key);
        }
    }
