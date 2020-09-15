package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {

    private FileService fileService;
    private UserService userService;

    @Autowired
    public FileController(FileService fileService,UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("fileUpload") MultipartFile fileupload,
                                   Authentication auth, Model model) throws IOException {

        Integer userid = userService.getUserById(auth.getName());
        if (fileupload.isEmpty()) {
            model.addAttribute("errorBannerResult","Please upload a file!");
            return "result";
        }else if(fileService.isDupilicateFileName(userid, fileupload.getOriginalFilename())) {
            model.addAttribute("errorBannerResult", "Filename already exists!");
            return "result";
        }else {
            int addrow = fileService.addFile(fileupload,userid);
            if(addrow != 1) {
                model.addAttribute("errorResult", true);
                return "result";
            }
        }

        model.addAttribute("successResult", true);
        return "result";

    }

    @GetMapping("/view/{fileId:.+}")
    public ResponseEntity<Resource> viewFile(@PathVariable int fileId, Authentication authentication){
        File file = fileService.getFile(fileId);
        try{
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getFileName())
                    .contentLength(file.getFileData().length)
                    .body(new ByteArrayResource(file.getFileData()));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/delete/{fileId:.+}")
    public String deleteFile(@PathVariable Integer fileId, Model model) {
        int delrow = fileService.deleteFile(fileId);
        if(delrow == 1) {
            model.addAttribute("successResult", true);
            return "result";
        }
        else{
            model.addAttribute("errorResult", true);
            return "result";
        }
    }
}
