package org.nfl.data;

import java.util.stream.StreamSupport;

public class Main {
    public static void main(String[] args) {
        try {
            new GUI();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}