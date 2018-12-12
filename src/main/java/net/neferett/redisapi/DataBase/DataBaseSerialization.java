package net.neferett.redisapi.DataBase;

import lombok.Data;
import lombok.NonNull;
import lombok.SneakyThrows;
import net.neferett.redisapi.Annotations.Redis;
import net.neferett.redisapi.Datas.ManipulateDatas;
import net.neferett.redisapi.Utils.SerializationUtils;

@Data
public class DataBaseSerialization {

    private ManipulateDatas manipulateDatas;

    private final Object obj;

    private final DataBaseConnector connector;

    @NonNull
    private String id;

    private final Redis redis;

    private final Class<?> clazz;

    @SneakyThrows
    public DataBaseSerialization(Object obj, DataBaseConnector connector, String id, Class clazz) {
        this.obj = obj;
        this.clazz = clazz;
        this.redis = this.clazz.getAnnotation(Redis.class);
        this.id = id;
        this.connector = connector;
        this.createManipulator();
    }

    private void createManipulator() {
        this.manipulateDatas = new ManipulateDatas(
                this.connector.getRessource(),
                this.clazz.getSimpleName(),
                this.redis.folder(),
                this.id
        );
    }

    public void serialize() {
        this.manipulateDatas.set("data", SerializationUtils.serializeToString(this.obj));
    }

    public String deserialize() {
        this.manipulateDatas.setName(this.id);
        this.manipulateDatas.refreshCache();
        return this.manipulateDatas.get("data");
    }

}
