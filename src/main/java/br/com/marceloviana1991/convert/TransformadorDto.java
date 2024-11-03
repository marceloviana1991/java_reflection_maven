package br.com.marceloviana1991.convert;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class TransformadorDto {

    public <I,O> O transformar(I input, Class<?> target) throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        Class<?> source = input.getClass();
        O targetClass = (O) target.getDeclaredConstructor().newInstance();

        Field[] sourceFields = source.getDeclaredFields();
        Field[] targetFields = target.getDeclaredFields();

        Arrays.stream(sourceFields).forEach(sourceField ->
                Arrays.stream(targetFields).forEach(targetField -> {
                    if(validate(sourceField, targetField)) {
                        try {
                            targetField.set(targetClass, sourceField.get(input));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }));
        return targetClass;
    }

    private boolean validate(Field sourceField, Field targetField) {
        if (sourceField.getName().equals(targetField.getName())
                && sourceField.getType().equals(targetField.getType())) {
            sourceField.setAccessible(true);
            targetField.setAccessible(true);
            return true;
        }
        return false;
    }
}
