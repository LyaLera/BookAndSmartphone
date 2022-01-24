package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);

    Book first = new Book(1, "Samsung", 100, "author1");
    Book second = new Book(2, "second", 250, "author2");
    Book third = new Book(3, "third", 600, "author3");
    Book fourth = new Book(4, "fourth", 100, "author3");

    Smartphone number1 = new Smartphone(10, "Nokia", 10000, "manufacturer N");
    Smartphone number2 = new Smartphone(20, "Nokia", 50000, "manufacturer N2");
    Smartphone number3 = new Smartphone(30, "IPhone", 35000, "manufacturer S");
    Smartphone number4 = new Smartphone(40, "Samsung", 45000, "manufacturer S2");

    @BeforeEach
    public void setUp() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(number1);
        manager.add(number2);
        manager.add(number3);
        manager.add(number4);
    }

    @Test
    public void shouldSearchIfExistsBookByName() {

        Product[] actual = manager.searchBy(first.getName());
        Product[] expected = new Product[]{first, number4};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchIfExistsSmartphoneByName() {

        Product[] actual = manager.searchBy(number2.getName());
        Product[] expected = new Product[]{number1, number2};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchIfNotExistsBookByName() {
        int idToRemove = 2;

        repository.removeById(idToRemove);

        Product[] actual = manager.searchBy(second.getName());
        Product[] expected = new Product[]{};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchIfNotExistsSmartphoneByName() {
        int idToRemove = 30;

        repository.removeById(idToRemove);

        Product[] actual = manager.searchBy(number3.getName());
        Product[] expected = new Product[]{};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchIfExistsBookByAuthor() {

        Product[] actual = manager.searchBy(third.getAuthor());
        Product[] expected = new Product[]{third, fourth};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchIfExistsSmartphoneByManufacturer() {

        Product[] actual = manager.searchBy(number2.getManufacturer());
        Product[] expected = new Product[]{number2};
        assertArrayEquals(expected, actual);
    }
}