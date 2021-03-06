package org.apache.cayenne.testdo.relationships_flattened.auto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.apache.cayenne.BaseDataObject;
import org.apache.cayenne.exp.Property;
import org.apache.cayenne.testdo.relationships_flattened.FlattenedTest2;
import org.apache.cayenne.testdo.relationships_flattened.FlattenedTest3;
import org.apache.cayenne.testdo.relationships_flattened.FlattenedTest4;
import org.apache.cayenne.testdo.relationships_flattened.FlattenedTest5;

/**
 * Class _FlattenedTest1 was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _FlattenedTest1 extends BaseDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String FT1_ID_PK_COLUMN = "FT1_ID";

    public static final Property<String> NAME = Property.create("name", String.class);
    public static final Property<List<FlattenedTest2>> FT2ARRAY = Property.create("ft2Array", List.class);
    public static final Property<List<FlattenedTest3>> FT3ARRAY = Property.create("ft3Array", List.class);
    public static final Property<List<FlattenedTest3>> FT3OVER_COMPLEX = Property.create("ft3OverComplex", List.class);
    public static final Property<List<FlattenedTest4>> FT4ARRAY_FOR1 = Property.create("ft4ArrayFor1", List.class);
    public static final Property<List<FlattenedTest5>> FT5ARRAY = Property.create("ft5Array", List.class);

    protected String name;

    protected Object ft2Array;
    protected Object ft3Array;
    protected Object ft3OverComplex;
    protected Object ft4ArrayFor1;
    protected Object ft5Array;

    public void setName(String name) {
        beforePropertyWrite("name", this.name, name);
        this.name = name;
    }

    public String getName() {
        beforePropertyRead("name");
        return this.name;
    }

    public void addToFt2Array(FlattenedTest2 obj) {
        addToManyTarget("ft2Array", obj, true);
    }

    public void removeFromFt2Array(FlattenedTest2 obj) {
        removeToManyTarget("ft2Array", obj, true);
    }

    @SuppressWarnings("unchecked")
    public List<FlattenedTest2> getFt2Array() {
        return (List<FlattenedTest2>)readProperty("ft2Array");
    }

    @SuppressWarnings("unchecked")
    public List<FlattenedTest3> getFt3Array() {
        return (List<FlattenedTest3>)readProperty("ft3Array");
    }

    public void addToFt3OverComplex(FlattenedTest3 obj) {
        addToManyTarget("ft3OverComplex", obj, true);
    }

    public void removeFromFt3OverComplex(FlattenedTest3 obj) {
        removeToManyTarget("ft3OverComplex", obj, true);
    }

    @SuppressWarnings("unchecked")
    public List<FlattenedTest3> getFt3OverComplex() {
        return (List<FlattenedTest3>)readProperty("ft3OverComplex");
    }

    @SuppressWarnings("unchecked")
    public List<FlattenedTest4> getFt4ArrayFor1() {
        return (List<FlattenedTest4>)readProperty("ft4ArrayFor1");
    }

    public void addToFt5Array(FlattenedTest5 obj) {
        addToManyTarget("ft5Array", obj, true);
    }

    public void removeFromFt5Array(FlattenedTest5 obj) {
        removeToManyTarget("ft5Array", obj, true);
    }

    @SuppressWarnings("unchecked")
    public List<FlattenedTest5> getFt5Array() {
        return (List<FlattenedTest5>)readProperty("ft5Array");
    }

    @Override
    public Object readPropertyDirectly(String propName) {
        if(propName == null) {
            throw new IllegalArgumentException();
        }

        switch(propName) {
            case "name":
                return this.name;
            case "ft2Array":
                return this.ft2Array;
            case "ft3Array":
                return this.ft3Array;
            case "ft3OverComplex":
                return this.ft3OverComplex;
            case "ft4ArrayFor1":
                return this.ft4ArrayFor1;
            case "ft5Array":
                return this.ft5Array;
            default:
                return super.readPropertyDirectly(propName);
        }
    }

    @Override
    public void writePropertyDirectly(String propName, Object val) {
        if(propName == null) {
            throw new IllegalArgumentException();
        }

        switch (propName) {
            case "name":
                this.name = (String)val;
                break;
            case "ft2Array":
                this.ft2Array = val;
                break;
            case "ft3Array":
                this.ft3Array = val;
                break;
            case "ft3OverComplex":
                this.ft3OverComplex = val;
                break;
            case "ft4ArrayFor1":
                this.ft4ArrayFor1 = val;
                break;
            case "ft5Array":
                this.ft5Array = val;
                break;
            default:
                super.writePropertyDirectly(propName, val);
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        writeSerialized(out);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        readSerialized(in);
    }

    @Override
    protected void writeState(ObjectOutputStream out) throws IOException {
        super.writeState(out);
        out.writeObject(this.name);
        out.writeObject(this.ft2Array);
        out.writeObject(this.ft3Array);
        out.writeObject(this.ft3OverComplex);
        out.writeObject(this.ft4ArrayFor1);
        out.writeObject(this.ft5Array);
    }

    @Override
    protected void readState(ObjectInputStream in) throws IOException, ClassNotFoundException {
        super.readState(in);
        this.name = (String)in.readObject();
        this.ft2Array = in.readObject();
        this.ft3Array = in.readObject();
        this.ft3OverComplex = in.readObject();
        this.ft4ArrayFor1 = in.readObject();
        this.ft5Array = in.readObject();
    }

}
