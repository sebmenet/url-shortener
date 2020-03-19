package com.menet.urlshortener.util;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

@SuppressWarnings("UnstableApiUsage")
public final class HashingUtil {
    public static String getMurmurWithUTFCharsetHash(String original) {
        return Hashing.murmur3_32().hashString(original, StandardCharsets.UTF_8).toString();
    }
}
