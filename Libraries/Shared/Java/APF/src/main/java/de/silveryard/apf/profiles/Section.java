package de.silveryard.apf.profiles;

/**
 * Created by Sebif on 7/21/2017.
 */
public class Section<T extends AbstractType> {
    private final String name;
    private final String id;
    private final boolean required;
    private final T type;

    protected Section(String name, String id, boolean required, T type) {
        this.name = name;
        this.id = id;
        this.required = required;
        this.type = type;
    }

    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public boolean isRequired() {
        return required;
    }
    public T getType() {
        return type;
    }
}
