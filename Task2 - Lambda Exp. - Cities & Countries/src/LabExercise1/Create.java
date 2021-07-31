package LabExercise1;

@FunctionalInterface
public interface Create<T>
{
    T create(String rowData);
}
