package org.app.md5checksum.controller;

import org.app.md5checksum.model.ChecksumRequest;
import org.app.md5checksum.model.ChecksumResponse;
import org.app.md5checksum.utility.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ControllerTest {

    @Mock
    private Utility utility;

    @InjectMocks
    private Controller controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateMD5Checksum() {
        // Arrange
        String text = "test";
        String md5Checksum = "098f6bcd4621d373cade4e832627b4f6";
        ChecksumRequest request = new ChecksumRequest();
        request.setText(text);

        when(utility.calculateMD5Sum(text)).thenReturn(md5Checksum);

        ResponseEntity<ChecksumResponse> responseEntity = controller.calculateMD5Checksum(request);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(md5Checksum, responseEntity.getBody().getChecksum());
    }


    @Test
    public void testCalculateMD5Checksum_EmptyText() {
        String text = "";
        String md5Checksum = "d41d8cd98f00b204e9800998ecf8427e";
        ChecksumRequest request = new ChecksumRequest();
        request.setText(text);

        when(utility.calculateMD5Sum(text)).thenReturn(md5Checksum);
        ResponseEntity<ChecksumResponse> responseEntity = controller.calculateMD5Checksum(request);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(md5Checksum, responseEntity.getBody().getChecksum());
    }

    @Test
    public void testCalculateMD5Checksum_SpecialCharacters() {
        String text = "!@#$%^&*()";
        String md5Checksum = "d751713988987e9331980363e24189ce";
        ChecksumRequest request = new ChecksumRequest();
        request.setText(text);

        when(utility.calculateMD5Sum(text)).thenReturn(md5Checksum);

        ResponseEntity<ChecksumResponse> responseEntity = controller.calculateMD5Checksum(request);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(md5Checksum, responseEntity.getBody().getChecksum());
    }

    @Test
    public void testCalculateMD5Checksum_LongText() {
        String text = "a".repeat(1000);
        String md5Checksum = "cabe45dcc9ae5b66ba86600cca6b8ba8";
        ChecksumRequest request = new ChecksumRequest();
        request.setText(text);

        when(utility.calculateMD5Sum(text)).thenReturn(md5Checksum);

        ResponseEntity<ChecksumResponse> responseEntity = controller.calculateMD5Checksum(request);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(md5Checksum, responseEntity.getBody().getChecksum());
    }
}