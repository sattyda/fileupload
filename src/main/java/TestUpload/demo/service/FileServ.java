package TestUpload.demo.service;

import TestUpload.demo.entity.FileUpload;
import TestUpload.demo.repo.FileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServ {

    @Autowired
    FileRepo fileRepo;

    public void save(FileUpload fileUpload) {

        fileRepo.save(fileUpload);
    }

    public List<FileUpload> getfile() {
        return fileRepo.findAll();
    }
}
