package net.neferett.redisapi.Examples;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import net.neferett.redisapi.Annotations.Redis;

@Redis(db = 1, folder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HumanTestClass {

    @NonNull
    private String name;

    @NonNull
    private int age;

    @NonNull
    private int toto;

    @NonNull
    private String albert;
}
