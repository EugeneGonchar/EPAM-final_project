package controller.command.command;

import controller.command.ActionCommand;
import controller.command.util.Constant;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import domain.entity.User;
import resource.ConfigurationManager;
import service.exception.ServiceException;
import service.factory.ServiceFactory;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class UploadUserImageCommand implements ActionCommand {

    private static final String USER_IMG_DIRECTORY = "img\\uploads\\user";

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) throws ServletException, IOException {
        ActionPageContainer actionPageContainer = null;
        String page = null;

        UserService userService = ServiceFactory.getInstance().getUserService();

        User user = (User) sessionRequestContent.getSessionAttribute(Constant.USER);
        Part filePart = sessionRequestContent.getPart(Constant.USER_IMG);

        String appPath = sessionRequestContent.getRealPath("");
        String savePath = appPath + File.separator + USER_IMG_DIRECTORY;

        String filename = writeFile(filePart, savePath);

        try {
            userService.updateUserImg(user, filename);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        user.setProfileImage(filename);

        sessionRequestContent.add2SessionAttributes(Constant.USER, user);
        page = ConfigurationManager.getProperty("path.page.profile");
        actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);
        return actionPageContainer;
    }

    private String writeFile(Part filePart, String directory) throws IOException {
        File fileDir = new File(directory);

        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        String format = getFileFormat(filePart);
        File file = File.createTempFile("photo", format, fileDir);
        try (InputStream inputStream = filePart.getInputStream()) {
            Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        return file.getName();
    }

    private String getFileFormat(Part filePart) {
        String filename = getFileName(filePart);
        System.out.println("filename = " + filename);
        if (filename == null) {
            //exception
        }
        return filename.substring(filename.lastIndexOf("."));
    }

    private String getFileName(Part filePart) {
        for (String content : filePart.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") +1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
