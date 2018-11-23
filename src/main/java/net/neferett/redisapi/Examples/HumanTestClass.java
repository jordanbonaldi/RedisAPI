package net.neferett.redisapi.Examples;

import lombok.Data;
import net.neferett.redisapi.Annotations.RedisApi;

@RedisApi
@Data
public class HumanTestClass {
    private final String name;
    private final int age;
}
