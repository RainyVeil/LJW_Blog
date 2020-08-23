package com.ljw.springboot.thymeleaf.service;

import com.ljw.springboot.thymeleaf.model.BlogFile;

import java.util.ArrayList;
import java.util.HashMap;

public interface BlogFileService {
    ArrayList queryAllPicture();
    ArrayList queryPictureByPrimaryKey(BlogFile bfile);
}
