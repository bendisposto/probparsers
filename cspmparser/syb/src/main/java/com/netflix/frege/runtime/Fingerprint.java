package com.netflix.frege.runtime;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Fingerprint {

    private final long high, low;

    public Fingerprint(long high, long low) {
        this.high = high;
        this.low = low;
    }

    private Fingerprint(byte[] bytes) {
        ByteBuffer buf = ByteBuffer.wrap(bytes);
        high = buf.getLong();
        low = buf.getLong();
    }

    public long high() {
        return high;
    }

    public long low() {
        return low;
    }

    private byte[] getBytes() {
        return ByteBuffer.allocate(16).putLong(high).putLong(low).array();
    }

    public static Fingerprint fingerprintData(long[] in) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e.toString());
        }
        ByteBuffer buf = ByteBuffer.allocate(8 * in.length);
        buf.asLongBuffer().put(in);
        return new Fingerprint(md.digest(buf.array()));
    }

    public static Fingerprint fingerprintString(String in) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e.toString());
        }
        return new Fingerprint(md.digest(in.getBytes()));
    }

    public String show() {
        return Long.toHexString(high) + Long.toHexString(low);
    }

    public int hashCode() {
        return (int)((high >> 32) ^ high ^ (low >> 32) ^ low);
    }
}