package com.coopinc.socketbus;

import com.google.common.eventbus.EventBus;
import com.google.gson.*;

import java.util.HashMap;
import java.util.Map;

public class ConverterBus {
    private final Gson gson;
    private final Map<String, Class> classMap = new HashMap<>();
    private final EventBus eventBus;

    public ConverterBus(Gson gson, EventBus eventBus, Class... classes) {
        this.gson = gson;
        this.eventBus = eventBus;
        for (Class claz: classes){
            classMap.put(claz.getName(), claz);
        }
    }

    public ConverterBus(Gson gson, EventBus eventBus, ITag... iTags) {
        this.gson = gson;
        this.eventBus = eventBus;
        for (ITag iTag : iTags) {
            classMap.put(iTag.getTag(), iTag.getClass());
        }
    }

    public Exception eventFromString(String string) {
        JsonElement element = new JsonParser().parse(string);
        if (element.isJsonArray()) {
            return new IllegalArgumentException("Lists of objects are unsupported, instead wrap it in a class.");
        } else {
            try {
                String key = element.getAsJsonObject().entrySet().stream().filter(e -> e.getKey().equals("tag"))
                        .findFirst().get().getValue().getAsString();
                eventBus.post(gson.fromJson(element, classMap.get(key)));
                return null;
            } catch (Exception e) {
                return e;
            }
        }
    }
}
