package controller.command.command;

import controller.command.ActionCommand;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import resource.ConfigurationManager;
import service.CarService;
import service.ServiceFactory;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class UploadCarImageCommand implements ActionCommand {

    private static final String CAR_IMG_DIRECTORY = "img\\uploads\\car";

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) throws ServletException, IOException {
        ActionPageContainer actionPageContainer = null;
        String page = null;

        CarService carService = ServiceFactory.getInstance().getCarService();

        int carId = Integer.parseInt(sessionRequestContent.getRequestParameter("car_id"));
        Part filePart = sessionRequestContent.getPart("user_img");

        String appPath = sessionRequestContent.getRealPath("");
        String savePath = appPath + File.separator + CAR_IMG_DIRECTORY;

        String filename = writeFile(filePart, savePath);

        carService.updateCarImg(carId, filename);

        page = ConfigurationManager.getProperty("path.page.admin.get_cars");
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
