package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    private FileMapper fileMapper;

    @Autowired
    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public int addFile(MultipartFile fileUpload,int userId) throws IOException {
        File file = new File();
        try {
            file.setContentType(fileUpload.getContentType());
            file.setFileData(fileUpload.getBytes());
            file.setFileName(fileUpload.getOriginalFilename());
            file.setFileSize(Long.toString(fileUpload.getSize()));
            file.setUserId(userId);
        } catch (IOException e) {
            throw e;
        }

        return fileMapper.addFile(file);
    }

    public List<File> getFilesByUserId(int userId){
        return fileMapper.findByUserId(userId);
    }

    public int updateFile(MultipartFile fileUpload,int userId) throws IOException {
        File file = new File();
        try {
            file.setContentType(fileUpload.getContentType());
            file.setFileData(fileUpload.getBytes());
            file.setFileName(fileUpload.getOriginalFilename());
            file.setFileSize(Long.toString(fileUpload.getSize()));
            file.setUserId(userId);
        } catch (IOException e) {
            throw e;
        }

        return fileMapper.updateFile(file);
    }

    public File getFile(int fileId){
        return fileMapper.findFile(fileId);
    }


    public int deleteFile(int fileId) {
        return fileMapper.deleteFile(fileId);
    }

    public boolean isDupilicateFileName(Integer UserId, String FileName) {
        return (fileMapper.duplicateFile(UserId, FileName) != null) ;
    }
}
