package com.example.headerApp.Controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HeaderController {
    @GetMapping("/headers")
    public ResponseEntity<Map<String, Object>> getAllHeaders(@RequestHeader Map<String, String> requestHeaders) {
        // Prepare response headers
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Custom-Header", "SpringBootHeaderDemo");
        responseHeaders.add("Powered-By", "Spring Boot");

        // Prepare response body
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("requestHeaders", requestHeaders);
        responseBody.put("responseHeaders", responseHeaders.toSingleValueMap());

        return ResponseEntity.ok().headers(responseHeaders).body(responseBody);
    }


}