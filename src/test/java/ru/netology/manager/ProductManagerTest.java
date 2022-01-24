package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);

    Product zero = new Product(0, "zero", 300);

    Book first = new Book(1, "first", 100, "author");
    Book second = new Book(2, "second", 250, "author");
    Book third = new Book(3, "third", 600, "author");
    Book fourth = new Book(4, "fourth", 100, "author");

    Smartphone number1 = new Smartphone(10, "number1", 10000, "manufacturer");
    Smartphone number2 = new Smartphone(20, "number2", 50000, "manufacturer");
    Smartphone number3 = new Smartphone(30, "number3", 35000, "manufacturer");
    Smartphone number4 = new Smartphone(40, "number4", 45000, "manufacturer");

    @Test
    public void shouldSearchIfExistsBook() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(number1);
        manager.add(number2);
        manager.add(number3);
        manager.add(number4);

        manager.searchBy(first.getName());
        manager.searchBy(first.getAuthor());

        Product[] actual = manager.searchBy(first.getName());
        Product[] expected = new Product[]{first};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchIfExistsSmartphone() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(number1);
        manager.add(number2);
        manager.add(number3);
        manager.add(number4);

        manager.searchBy(number2.getName());
        manager.searchBy(number2.getManufacturer());

        Product[] actual = manager.searchBy(number2.getName());
        Product[] expected = new Product[]{number2};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchIfNotExistsBook() {
        int idToRemove = 2;

        manager.add(third);
        manager.add(second);

        repository.removeById(idToRemove);
        manager.searchBy(second.getName());

        Product[] actual = manager.searchBy(second.getName());
        Product[] expected = new Product[]{};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchIfNotExistsSmartphone() {
        int idToRemove = 30;

        manager.add(number3);
        manager.add(number4);

        repository.removeById(idToRemove);
        manager.searchBy(number3.getName());

        Product[] actual = manager.searchBy(number3.getName());
        Product[] expected = new Product[]{};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchByNameProduct() {

        manager.add(zero);
        manager.searchBy(zero.getName());

        Product[] actual = manager.searchBy(zero.getName());
        Product[] expected = new Product[]{zero};
        assertArrayEquals(expected, actual);
    }
}