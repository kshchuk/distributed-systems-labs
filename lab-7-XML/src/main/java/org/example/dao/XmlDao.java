package org.example.dao;

import org.example.model.IId;
import org.example.xml.dom.reader.Reader;
import org.example.xml.dom.writer.Writer;

import javax.xml.validation.Schema;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 *
 * @param <C> - container, that contains list of target objects
 * @param <T> - target object generic
 * @param <Id> - id generic
 */
public abstract class XmlDao<C, T extends IId<Id>, Id> implements CrudDao<T, Id>{
    protected final String inputFileXML;
    protected final Reader<C> reader;
    protected final Writer<C> writer;
    private final Schema schema;
    public XmlDao(String inputFileXML, Reader<C> reader, Writer<C> writer, Schema schema) {
        this.inputFileXML = inputFileXML;
        this.reader = reader;
        this.writer = writer;
        this.schema = schema;
    }
    protected void writeFile(C parsed) throws Exception {
        writer.write(inputFileXML, parsed);
    }

    protected C readFile() throws Exception {
        return reader.read(inputFileXML, schema);
    }

    protected abstract List<T> toCollection(C container);

    protected  <V> V withReadWrite(Function<C, V> function) throws Exception {
        C container = readFile();
        V result =  function.apply(container);
        writeFile(container);
        return result;
    }

    private void withReadWrite(Consumer<C> consumer) throws Exception {
        C container = readFile();
        consumer.accept(container);
        writeFile(container);
    }

    public List<T> findAll() throws Exception {
        return toCollection(readFile());
    }

    @Override
    public T create(T object) throws Exception {
        return withReadWrite(parsed->{
            List<T> containersList = toCollection(parsed);
            containersList.add(object);
            return object;
        });
    }

    @Override
    public Optional<T> read(Id id) throws Exception {
        return findAll().stream().filter(d->id.equals(d.getId())).findFirst();
    }

    @Override
    public void update(T object) throws Exception {
        withReadWrite(parsed -> {
            List<T> objectList = toCollection(parsed);
            Id targetId = object.getId();
            if (targetId==null){
                throw new IllegalArgumentException("Not initialized element passed to update");
            }
            deleteById(objectList, targetId);
            objectList.add(object);
        });
    }

    private void deleteById(List<T> objectList, Id targetId) {
        if(!objectList.removeIf(d-> Objects.equals(d.getId(), targetId))){
            throw new NoSuchElementException("Called to remove not existing element");
        }
    }

    public void delete(T object) throws Exception {
        withReadWrite(container -> {
            if (!toCollection(container).remove(object)) {
                throw new NoSuchElementException("Called to not existing element");
            }
        });

    }

    @Override
    public void delete(Id id) throws Exception {
        withReadWrite(container -> {
            try {
                deleteById(toCollection(container), id);
            } catch (NoSuchElementException e) {
                throw new NoSuchElementException("Called to not existing element");
            }
        });
    }
}
