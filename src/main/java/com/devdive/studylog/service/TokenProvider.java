package com.devdive.studylog.service;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;


public class TokenProvider {

    public String generate(String value, LocalDateTime now) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        md.update(localDateTimeToBytes(now));
        return bytesToHex(md.digest(value.getBytes()));
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b: bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    private byte[] localDateTimeToBytes(LocalDateTime localDateTime) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES * 2);
        buffer.putLong(localDateTime.toLocalDate().toEpochDay());
        buffer.putLong(localDateTime.toLocalTime().toNanoOfDay());
        return buffer.array();
    }
}
