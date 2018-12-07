package net.neferett.redisapi;

import net.neferett.redisapi.Examples.HumanTestClass;

public class Main {

    public static void main(String [] args) {
        RedisAPI redisapi = new RedisAPI("127.0.0.1", "toto1", 6379);

        HumanTestClass tt = (HumanTestClass) redisapi.deSerialize(HumanTestClass.class, "3");

        System.out.println(tt.getAge());

        tt = new HumanTestClass("", 12, 34, "");

        redisapi.serialize(tt, "3");

        redisapi.close();
    }

}
