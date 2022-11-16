package com.phenikaa.vietsecond.Presentation_Layer;

import com.phenikaa.vietsecond.Business_Logic_Layer.AmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/storage/")
@CrossOrigin("*")
public class BucketController {

    private AmazonClient amazonClient;

    @Autowired
    BucketController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(@RequestPart(value = "file") MultipartFile file) {
        return new ResponseEntity<String>(  this.amazonClient.uploadFile(file), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFile(@RequestParam String urlFile){
        amazonClient.deleteFile(urlFile);
        return new ResponseEntity<String>("delete",HttpStatus.OK);
    }

    @DeleteMapping("/deletes")
    public ResponseEntity<?> deleteMultiFile(@RequestBody String[] listFile){
        amazonClient.deleteMultiFile(listFile);
        return new ResponseEntity<String>("delete",HttpStatus.OK);
    }
}