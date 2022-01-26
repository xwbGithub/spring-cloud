package org.xwb.springcloud.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Administrator
 * @description
 */
@Slf4j
public class CommonUtils {

    public static String getType() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            log.info(" input pizza 种类{}", input);
            return input.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
