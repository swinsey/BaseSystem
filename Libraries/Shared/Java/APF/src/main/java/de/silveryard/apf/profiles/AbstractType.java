package de.silveryard.apf.profiles;

public abstract class AbstractType<T extends AbstractTypeProperties> {
    public abstract SectionType getSectionType();
    public abstract T getTypeProperties();
}
