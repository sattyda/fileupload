package TestUpload.demo.repo;

import TestUpload.demo.entity.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepo extends JpaRepository<FileUpload , Long> {

}
