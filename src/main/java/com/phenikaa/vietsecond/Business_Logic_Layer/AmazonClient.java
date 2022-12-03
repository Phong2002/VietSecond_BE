package com.phenikaa.vietsecond.Business_Logic_Layer;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AmazonClient {
    private AmazonS3 s3client;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Value("${amazonProperties.accessKey}")
    private String accessKey;
    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = new AmazonS3Client(credentials);
    }
    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

    private void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public String uploadFile(MultipartFile multipartFile) {
        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
            uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileUrl;
    }

    public void deleteFile(String urlFile){
        int firstIndex = urlFile.lastIndexOf("/")+1;
        int lastIndex = urlFile.length();
        String fileName = urlFile.substring(firstIndex,lastIndex);
        s3client.deleteObject(bucketName,fileName);
    }
    public void deleteMultiFile(String []urlFiles){
        String[] newListUrl = new String[urlFiles.length];
        for(int i=0;i<urlFiles.length;i++){
            int firstIndex = urlFiles[i].lastIndexOf("/")+1;
            int lastIndex = urlFiles[i].length();
            String fileName = urlFiles[i].substring(firstIndex,lastIndex);
            newListUrl[i]=fileName;
        }

        DeleteObjectsRequest deleteObjectRequest = new DeleteObjectsRequest(bucketName).withKeys(urlFiles);
        s3client.deleteObjects(deleteObjectRequest);
    }

    public List<String> uploadMultifile(MultipartFile[] multipartFiles){
        List<String> listUrl = new ArrayList<>();
        try {
            for(MultipartFile file:multipartFiles){
                listUrl.add(uploadFile(file));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listUrl;
    }
}
