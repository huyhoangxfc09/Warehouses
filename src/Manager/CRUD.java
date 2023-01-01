package Manager;

import java.util.List;
import java.util.Scanner;

public interface CRUD <E>{
    E create(Scanner scanner);
    void add(E e);
    void  update(Scanner scanner);
    void delete(Scanner scanner);
    void displayAll(List<E> element);
    void  outputFile(String path);
    void inputFile(String path);


}
