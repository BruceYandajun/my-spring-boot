package com.github.bruce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

@RestController
@RequestMapping("/file")
public class FileUploadController {

    @RequestMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("upload");
    }

    @RequestMapping("/upload")
    public String springUpload(HttpServletRequest request) throws IllegalStateException, IOException {
        long startTime = System.currentTimeMillis();

        //将当前上下文初始化给 CommonsMultipartResolver (多部分解析器)
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

        //检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    String path = "/" + file.getOriginalFilename();
                    file.transferTo(new File(path));
                }
            }
        }

        long endTime=System.currentTimeMillis();
        System.out.println("方法三的运行时间：" + (endTime - startTime) + "ms");
        return "/success";

    }

}
