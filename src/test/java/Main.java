import com.coopinc.socketbus.ConverterBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static EventBus eventBus = new EventBus();
    public static Main main;

    public Main() {
        eventBus.register(this);
    }

    public static void main(String... args) {
        main = new Main();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ConverterBus converterBus = new ConverterBus(gson, eventBus, ExampleClass.class);
        List<String> exampleList = new ArrayList<>();
        exampleList.add("First string");
        exampleList.add("Second string");
        ExampleClass exampleClass = new ExampleClass(1, exampleList, false, "Error!");
        String exampleString = gson.toJson(exampleClass);
        System.out.print(exampleString);
        Exception e = converterBus.eventFromString(exampleString);
        if (e != null) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void onExampleClass(ExampleClass event) {
        System.out.println("Got the event!");
    }
}
