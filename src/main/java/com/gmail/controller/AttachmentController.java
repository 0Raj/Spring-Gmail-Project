package com.gmail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.gmail.module.Attachment;
import com.gmail.module.Content;
import com.gmail.service.AttachmentService;

@RestController
@RequestMapping("/mail")
public class AttachmentController {


    @Autowired
    private AttachmentService attachementService;


    @PostMapping("/attachment")
    public ResponseEntity<Content> UploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        Content content = attachementService.saveAttachment(file);


        return new ResponseEntity<>(content, HttpStatus.OK);
    }

    @GetMapping("/attachment/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Integer fileId) throws Exception {
        Attachment attachment = attachementService.getAttachment(fileId);
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getFileName()
                                + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }


}