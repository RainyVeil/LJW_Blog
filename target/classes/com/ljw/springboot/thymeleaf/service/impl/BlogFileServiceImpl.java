package com.ljw.springboot.thymeleaf.service.impl;

import com.ljw.springboot.thymeleaf.mapper.BlogFileMapper;
import com.ljw.springboot.thymeleaf.model.BlogFile;
import com.ljw.springboot.thymeleaf.service.BlogFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class BlogFileServiceImpl implements BlogFileService{
    @Autowired
    private BlogFileMapper blogFileMapper;
    @Override
    public ArrayList queryAllPicture() {
        return blogFileMapper.queryAllPicture(null);
    }

    @Override
    public ArrayList queryPictureByPrimaryKey(BlogFile bfile) {
         return blogFileMapper.queryAllPicture(bfile);
    }
}
