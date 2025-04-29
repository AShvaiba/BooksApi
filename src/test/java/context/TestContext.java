package context;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
    private Map<Keys, Object> contextData = new HashMap<>();

    public void set(Keys key, Object value) {
        contextData.put(key, value);
    }

    public <T> T get(Keys key, Class<T> clazz) {
        return clazz.cast(contextData.get(key));
    }

    public enum Keys {
        BOOKS_API,
        RESPONSE,
        CREATED_BOOK,
        BOOK_ID,
        NON_EXISTING_BOOK_ID
    }
}
