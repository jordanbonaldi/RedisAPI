package net.neferett.redisapi;

import lombok.Data;
import lombok.Getter;
import net.neferett.redisapi.DataBase.DataBase;
import net.neferett.redisapi.DataBase.DataBaseManager;
import net.neferett.redisapi.DataBase.DataBaseSerialization;
import net.neferett.redisapi.Datas.SerializableObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class RedisAPI {

    private final DataBaseManager manager;

    private List<SerializableObject> obj = new ArrayList<>();

    public RedisAPI(String ip, String password, int port) {
        this.manager = new DataBaseManager(ip, password, port);
    }

    private SerializableObject getObject(Object o, String id) {
        SerializableObject __obj = this.obj.stream().filter(e ->
                o instanceof Class ? e.getClazz().getSimpleName()
                        .equalsIgnoreCase(((Class) o).getSimpleName()) : e.getObject().equals(o)
        ).findFirst().orElse(null);

        if (null == __obj) {
            __obj = new SerializableObject(o, id, this.manager);
            this.obj.add(__obj);
        }

        __obj.setId(id);

        return __obj;
    }

    public void serialize(Object o){
        this.serialize(o, null);
    }

    public void serialize(Object o, String id) {
        this.getObject(o, id).serialize();
    }

    public Object deSerialize(Object o) {
        return this.deSerialize(o, null);
    }

    public Map<String, Object> contains(Class o, String... args) {
        return this.manager.contains(o, args);
    }

    public Object deSerialize(Object o, String id) {
        return this.getObject(o, id).deSerialize();
    }

    public void close() {
        this.getManager().closeAll();
    }
}
