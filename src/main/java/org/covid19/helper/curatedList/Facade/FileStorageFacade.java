package org.covid19.helper.curatedList.Facade;

import org.covid19.helper.curatedList.DTO.ResultDTO;
import org.covid19.helper.curatedList.Service.FileStorageService;
import org.covid19.helper.curatedList.Util.ApplicationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Component
public class FileStorageFacade {

    @Autowired
    private FileStorageService fileStorageService;



    public String uploadFile(MultipartFile file, String uuid) throws Exception {
        File newFile = Objects.requireNonNull(ApplicationUtil.convertMultiPartToFile(file)).getFile();
        return fileStorageService.uploadFile(newFile, uuid);

    }

//    public File downloadFile(String uuid) throws IOException, ValidationException {
////        File file;
////        if(FileType.MEMBER_DATA_BLANK.equals(type)){
////            file = new File(BASE_FILE_PATH+"Employee_Data_Format.xlsx");
////        }else{
////            file = fileStorageService.downloadFile(type, uuid);
////        }
////        return file;
//    }
}
