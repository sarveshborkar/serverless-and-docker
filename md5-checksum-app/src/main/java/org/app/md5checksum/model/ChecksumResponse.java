package org.app.md5checksum.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChecksumResponse {
    private String checksum;
}