package com.example.headerApp.Controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HeaderController {

    // Existing REST endpoint
    @GetMapping("/api/headers")
    @ResponseBody
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

    // Updated endpoint for HTML view
    @GetMapping("/view-headers")
    public String viewHeaders(HttpServletRequest request, HttpServletResponse response, Model model) {
        // Get Request Headers
        Map<String, String> requestHeadersMap = new HashMap<>();
        Enumeration<String> requestHeaderNames = request.getHeaderNames();
        while (requestHeaderNames.hasMoreElements()) {
            String headerName = requestHeaderNames.nextElement();
            String headerValue = request.getHeader(headerName);
            requestHeadersMap.put(headerName, headerValue);
        }

        // Add custom response headers
        response.addHeader("Custom-Header", "SpringBootHeaderDemo");
        response.addHeader("Powered-By", "Spring Boot");

        // Get Response Headers
        Map<String, String> responseHeadersMap = new HashMap<>();
        for (String headerName : response.getHeaderNames()) {
            String headerValue = response.getHeader(headerName);
            responseHeadersMap.put(headerName, headerValue);
        }

        model.addAttribute("requestHeaders", requestHeadersMap);
        model.addAttribute("responseHeaders", responseHeadersMap);
        return "headers";
    }
}