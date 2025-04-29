package utils;

import com.github.javafaker.Faker;
import dto.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class TestDataGenerator {

    private static final Faker faker = new Faker();
    private static final Random random = new Random();
    private static final Logger logger = LoggerFactory.getLogger(TestDataGenerator.class);

    public static Book generateRandomBook() {
        return Book.builder()
                .name(faker.book().title())
                .author(faker.book().author())
                .publication(faker.book().publisher())
                .category("Programming")
                .pages(faker.number().numberBetween(100, 1000))
                .price(faker.number().randomDouble(2, 10, 100))
                .build();
    }

    public static Book generateRandomBookWithRandomEmptyField() {
        Book book = generateRandomBook();
        String[] fields = {"name", "author", "publication", "category"};
        String randomField = fields[random.nextInt(fields.length)];

        switch (randomField) {
            case "name" -> book.setName("");
            case "author" -> book.setAuthor("");
            case "publication" -> book.setPublication("");
            case "category" -> book.setCategory("");
        }

        logger.info("Field '{}' set to empty", randomField);
        return book;
    }

    public static Book generateRandomBookWithNullField() {
        Book book = generateRandomBook();
        String[] fields = {"name", "author", "publication", "category"};
        String randomField = fields[random.nextInt(fields.length)];

        switch (randomField) {
            case "name" -> book.setName(null);
            case "author" -> book.setAuthor(null);
            case "publication" -> book.setPublication(null);
            case "category" -> book.setCategory(null);
        }

        logger.info("Field '{}' set to null", randomField);
        return book;
    }

    public static Book generateRandomBookWithInvalidField(String invalidField) {
        Book book = generateRandomBook();
        switch (invalidField.toLowerCase()) {
            case "pages" -> book.setPages(generateRandomNonPositiveInteger());
            case "price" -> book.setPrice(generateRandomNonPositiveDouble());
            default -> throw new IllegalArgumentException("Invalid field: " + invalidField);
        }
        return book;
    }

    public static Book updateRandomFieldInBook(Book book) {
        String[] fields = {"name", "author", "publication", "category", "pages", "price"};
        String randomField = fields[random.nextInt(fields.length)];

        switch (randomField) {
            case "name" -> book.setName(faker.book().title());
            case "author" -> book.setAuthor(faker.book().author());
            case "publication" -> book.setPublication(faker.book().publisher());
            case "category" -> book.setCategory("Programming");
            case "pages" -> book.setPages(faker.number().numberBetween(100, 1000));
            case "price" -> book.setPrice(faker.number().randomDouble(2, 10, 100));
        }

        logger.info("Field '{}' was updated'", randomField);
        return book;
    }

    public static int generateRandomNonPositiveInteger() {
        return random.nextBoolean() ? -random.nextInt(1000) : 0;
    }

    public static double generateRandomNonPositiveDouble() {
        return random.nextBoolean() ? -random.nextDouble() * 100 : 0.0;
    }
}
