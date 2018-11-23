package net.neferett.redisapi;

import net.neferett.redisapi.Examples.HumanTestClass;

public class Main {

    public static void main(String [] args) {

        new RedisAPI().test();
        HumanTestClass h = new HumanTestClass("toto", 18);
    }

}
