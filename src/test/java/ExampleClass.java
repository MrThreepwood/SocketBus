import java.util.List;

public class ExampleClass {
    private final String tag = "ExampleClass";
    private int exampleInt;
    private List<String> exampleStrings;
    private boolean exampleBoolean;
    private transient String dontSerializeThis;

    public ExampleClass(int exampleInt, List<String> exampleStrings, boolean exampleBoolean, String dontSerialize) {
        this.exampleInt = exampleInt;
        this.exampleStrings = exampleStrings;
        this.exampleBoolean = exampleBoolean;
        this.dontSerializeThis = dontSerialize;
    }
}
