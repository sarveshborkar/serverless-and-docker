package org.app.md5checksum.controller;

import org.app.md5checksum.model.ChecksumRequest;
import org.app.md5checksum.model.ChecksumResponse;
import org.app.md5checksum.utility.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/checksum")
public class Controller {

    @Autowired
    Utility utility;

    private static final Logger requestLogger = LoggerFactory.getLogger("RequestLogger");
    private static final Logger responseLogger = LoggerFactory.getLogger("ResponseLogger");

    @PostMapping("/md5")
    public ResponseEntity<ChecksumResponse> calculateMD5Checksum(@RequestBody ChecksumRequest request) {
        String requestId = UUID.randomUUID().toString();
        long startTime = System.nanoTime();

        String text = request != null ? request.getText() : "";
        requestLogger.info("REQUEST - Request ID: {}, Received text: {}", requestId, text);

        String md5Checksum = utility.calculateMD5Sum(text);
        ChecksumResponse response = new ChecksumResponse(md5Checksum);

        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        responseLogger.info("RESPONSE - Request ID: {}, MD5 Checksum: {}, Processing time: {} ns", requestId, md5Checksum, duration);

        return ResponseEntity.ok(response);
    }

}
