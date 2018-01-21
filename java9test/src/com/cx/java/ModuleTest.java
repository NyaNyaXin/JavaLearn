package com.cx.java;

import com.cx.bean.Person;

import java.util.logging.Logger;

public class ModuleTest {

     private static Logger logger = Logger.getLogger("ModuleTest.class");
     public static void main(String args[]){
         Person p = new Person("Tom",12);
         System.out.println(p);
         //User u = new User(1);

         logger.info("aaaaaa");
     }
}
