package com.mobiquity;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.Packer;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println(Packer.pack("src//main//resources//file.txt"));
        } catch (APIException e) {
            e.printStackTrace();
        }
    }

}
