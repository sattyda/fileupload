package TestUpload.demo.controller;

import TestUpload.demo.entity.FileUpload;
import TestUpload.demo.service.FileServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

@Controller
public class FileController {

    @Autowired
    FileServ fileServ;

    @GetMapping("/")
    public String index(){

        return "index";
    }


    @GetMapping("/view")
    public String view(  Model model ){
        List<FileUpload> files =  fileServ.getfile();
        model.addAttribute("files" , files);
        return "view";
    }


    @GetMapping("/add")
    public String add(HttpSession session, Model model){
        model.addAttribute("id" , session.getAttribute("loginId"));
        return "add";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/add";
    }

    @GetMapping("/login")
    public String login(HttpSession session){
        session.setAttribute("loginId" , "sattyda");
        return "redirect:/add";
    }

    @PostMapping("/submit")
    public String submit(@RequestParam("myfile") MultipartFile file) throws IOException {

        String mylocation = System.getProperty("user.dir") + "/src/main/resources/static/";

        String filename = file.getOriginalFilename();

        File mySavedFile = new File( mylocation + filename);

        InputStream inputStream = file.getInputStream();

        OutputStream outputStream = new FileOutputStream(mySavedFile);

        int read = 0;
        byte[] bytes = new byte[1024];

        while ((read = inputStream.read(bytes)) != -1){
            outputStream.write(bytes , 0 , read);
        }

        String mylink = "http://localhost:9090/" + filename;

        FileUpload fileUpload = new FileUpload();
        fileUpload.setFilename(mylink);


        fileServ.save(fileUpload);

        return "redirect:/";
    }



}
