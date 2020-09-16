package com.udacity.jwdnd.course1.cloudstorage.mappers;


import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE userId = #{userId}")
    List<File> findByUserId(int userId);

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    File findFile(int fileId);

    @Insert("INSERT INTO FILES (fileId, fileName, contentType, fileSize, userId, fileData) VALUES(#{fileId}, #{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int addFile(File file);

    @Update("UPDATE FILES SET fileName=#{fileName}, contentType=#{contentType}, fileSize=#{fileSize}, userId=#{userId}, fileData=#{fileData} WHERE fileId=#{fileId}")
    int updateFile(File file);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    int deleteFile(Integer fileId);

    @Select("SELECT * FROM FILES WHERE userId = #{userId} AND filename = #{filename}")
    File duplicateFile(Integer userId, String filename);

}
