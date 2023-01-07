package com.web.config;

import java.security.MessageDigest;

/**
 * @author igor@shakirov-dev.ru on 04.01.2023
 * @version 1.0
 */


public class Quiz {
 
 public static void main(String[] args) throws Exception {
  MessageDigest md = MessageDigest.getInstance("MD5");
  byte[] digest = md.digest("abracadabra".getBytes("UTF-8"));
  for (byte b : digest) {
   System.out.printf("%02x", b);
  }
 }
}