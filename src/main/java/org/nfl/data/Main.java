package org.nfl.data;

import java.util.stream.StreamSupport;

public class Main {
    public static void main(String[] args) {
        try {
            ModelOne modelOne = new ModelOne(2022);
            System.out.println(modelOne.performModel("receptions", "packers"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}