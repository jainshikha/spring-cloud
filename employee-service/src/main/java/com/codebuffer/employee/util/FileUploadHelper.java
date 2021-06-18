package com.codebuffer.employee.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
public class FileUploadHelper {
  public static final String uploadDir = "D:\\imageOutputs";

  public boolean uploadFile(MultipartFile multipartFile) {

    boolean uploadStatus = false;
    try {
      Files.copy(
          multipartFile.getInputStream(),
          Paths.get(uploadDir + File.separator + multipartFile.getOriginalFilename()),
          StandardCopyOption.REPLACE_EXISTING);
      uploadStatus = true;
    } catch (IOException e) {
      log.error("exception is thrown, while uploading file." + e);
    }
    return uploadStatus;
  }
}
