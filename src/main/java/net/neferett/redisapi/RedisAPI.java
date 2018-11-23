package net.neferett.redisapi;

import lombok.Data;
import net.neferett.redisapi.Annotations.RedisApi;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;

@Data
public class RedisAPI {

    public void test() {
        Reflections rfl = new Reflections("net.neferett.redisapi.Examples");

        List<Class<?>> rr = new ArrayList<>(rfl.getTypesAnnotatedWith(RedisApi.class));

        System.out.println(rr.size());

        rr.forEach(e -> {
        });
    }


}
