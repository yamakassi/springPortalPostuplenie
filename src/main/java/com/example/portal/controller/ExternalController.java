package com.example.portal.controller;

import com.example.portal.domain.CertificateJSON;
import com.example.portal.domain.Image;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;

@RestController
public class ExternalController {

    /*
        @GetMapping("/cert_student")
    public @ResponseBody CertificateJSON getCert(@RequestParam(name = "snils")String snils,@RequestParam(name = "type")String type){
        RestTemplate template=new RestTemplate();
        String url="http://192.168.43.177:8080/query?snils="+snils+"&type="+type;
        CertificateJSON cert=template.getForObject(url,CertificateJSON.class);
        return cert;
    }*/
   /* @GetMapping("/spravka")
    public String  getCert(String snils){
        String type = "";
        RestTemplate template=new RestTemplate();
      //  String url="http://192.168.43.177:8080/query?snils="+snils+"&type="+type;
        CertificateJSON cert= new CertificateJSON();
        cert.setConclusion(true);
        cert.setDoctor_name("пирогов");

         return ResponseEntity.ok()
                .body(cert);
    }*/
}


