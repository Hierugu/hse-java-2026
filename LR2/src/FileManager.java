import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// Утилитный класс для работы с файлами
public class FileManager {
    
    // Сохранение объекта в JSON файл
    public static void saveToFile(Object obj, String filePath) throws FileOperationException {
        try {
            createDirectoryIfNotExists(filePath);
            String json = objectToJson(obj);
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(json);
            }
        } catch (IOException e) {
            throw new FileOperationException("Ошибка при сохранении объекта в файл: " + filePath, e);
        }
    }
    
    // Загрузка объекта из JSON файла
    public static <T> T loadFromFile(String filePath, Class<T> classType) throws FileOperationException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            
            String json = jsonBuilder.toString();
            return jsonToObject(json, classType);
        } catch (FileNotFoundException e) {
            throw new FileOperationException("Файл не найден: " + filePath, e);
        } catch (IOException e) {
            throw new FileOperationException("Ошибка при чтении из файла: " + filePath, e);
        }
    }
    
    // Сохранение коллекции объектов в JSON файл
    public static <T> void saveCollectionToFile(List<T> collection, String filePath) throws FileOperationException {
        try {
            createDirectoryIfNotExists(filePath);
            String json = collectionToJson(collection);
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(json);
            }
        } catch (IOException e) {
            throw new FileOperationException("Ошибка при сохранении коллекции в файл: " + filePath, e);
        }
    }
    
    // Загрузка коллекции объектов из JSON файла
    public static <T> List<T> loadCollectionFromFile(String filePath, Class<T> classType) throws FileOperationException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            
            String json = jsonBuilder.toString();
            return jsonToCollection(json, classType);
        } catch (FileNotFoundException e) {
            throw new FileOperationException("Файл не найден: " + filePath, e);
        } catch (IOException e) {
            throw new FileOperationException("Ошибка при чтении из файла: " + filePath, e);
        }
    }
    
    // Преобразование объекта в JSON строку (упрощенная реализация)
    private static String objectToJson(Object obj) {
        if (obj == null) return "null";
        
        StringBuilder json = new StringBuilder();
        json.append("{");
        
        Field[] fields = obj.getClass().getDeclaredFields();
        boolean first = true;
        
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                if (!first) {
                    json.append(",");
                }
                json.append("\"").append(field.getName()).append("\":");
                json.append(valueToJson(value));
                first = false;
            } catch (IllegalAccessException e) {
                // Пропускаем поля, к которым нет доступа
            }
        }
        
        // Добавляем поля из родительских классов
        Class<?> superClass = obj.getClass().getSuperclass();
        while (superClass != null && superClass != Object.class) {
            Field[] superFields = superClass.getDeclaredFields();
            for (Field field : superFields) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    if (!first) {
                        json.append(",");
                    }
                    json.append("\"").append(field.getName()).append("\":");
                    json.append(valueToJson(value));
                    first = false;
                } catch (IllegalAccessException e) {
                    // Пропускаем поля, к которым нет доступа
                }
            }
            superClass = superClass.getSuperclass();
        }
        
        json.append("}");
        return json.toString();
    }
    
    // Преобразование значения в JSON формат
    private static String valueToJson(Object value) {
        if (value == null) return "null";
        if (value instanceof String) return "\"" + value + "\"";
        if (value instanceof Number || value instanceof Boolean) return value.toString();
        if (value instanceof Character) return "\"" + value + "\"";
        
        // Для сложных объектов рекурсивно вызываем objectToJson
        return objectToJson(value);
    }
    
    // Преобразование JSON строки в объект (упрощенная реализация)
    private static <T> T jsonToObject(String json, Class<T> classType) throws FileOperationException {
        try {
            T obj = classType.getDeclaredConstructor().newInstance();
            
            // Упрощенный парсинг JSON (в реальном проекте лучше использовать библиотеку)
            json = json.trim();
            if (json.startsWith("{") && json.endsWith("}")) {
                json = json.substring(1, json.length() - 1);
                
                String[] pairs = json.split(",");
                for (String pair : pairs) {
                    String[] keyValue = pair.split(":", 2);
                    if (keyValue.length == 2) {
                        String key = keyValue[0].trim().replace("\"", "");
                        String value = keyValue[1].trim();
                        
                        try {
                            Field field = findField(obj.getClass(), key);
                            if (field != null) {
                                field.setAccessible(true);
                                field.set(obj, parseValue(value, field.getType()));
                            }
                        } catch (Exception e) {
                            // Пропускаем поля, которые не удалось установить
                        }
                    }
                }
            }
            
            return obj;
        } catch (Exception e) {
            throw new FileOperationException("Ошибка при преобразовании JSON в объект", e);
        }
    }
    
    // Поиск поля в классе и его родительских классах
    private static Field findField(Class<?> clazz, String fieldName) {
        while (clazz != null && clazz != Object.class) {
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        return null;
    }
    
    // Преобразование строкового значения в нужный тип
    private static Object parseValue(String value, Class<?> targetType) {
        value = value.trim();
        
        if (value.equals("null")) return null;
        if (value.startsWith("\"") && value.endsWith("\"")) {
            return value.substring(1, value.length() - 1);
        }
        
        if (targetType == int.class || targetType == Integer.class) {
            return Integer.parseInt(value);
        }
        if (targetType == double.class || targetType == Double.class) {
            return Double.parseDouble(value);
        }
        if (targetType == boolean.class || targetType == Boolean.class) {
            return Boolean.parseBoolean(value);
        }
        if (targetType == long.class || targetType == Long.class) {
            return Long.parseLong(value);
        }
        if (targetType == float.class || targetType == Float.class) {
            return Float.parseFloat(value);
        }
        if (targetType == char.class || targetType == Character.class) {
            if (value.length() >= 3 && value.startsWith("'") && value.endsWith("'")) {
                return value.charAt(1);
            }
        }
        
        return value;
    }
    
    // Преобразование коллекции в JSON
    private static <T> String collectionToJson(List<T> collection) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        
        for (int i = 0; i < collection.size(); i++) {
            if (i > 0) json.append(",");
            json.append(objectToJson(collection.get(i)));
        }
        
        json.append("]");
        return json.toString();
    }
    
    // Преобразование JSON в коллекцию
    private static <T> List<T> jsonToCollection(String json, Class<T> classType) throws FileOperationException {
        List<T> collection = new ArrayList<>();
        
        json = json.trim();
        if (json.startsWith("[") && json.endsWith("]")) {
            json = json.substring(1, json.length() - 1);
            
            // Упрощенный парсинг массива JSON
            int depth = 0;
            int start = 0;
            
            for (int i = 0; i < json.length(); i++) {
                char c = json.charAt(i);
                if (c == '{') depth++;
                else if (c == '}') depth--;
                else if (c == ',' && depth == 0) {
                    String itemJson = json.substring(start, i).trim();
                    if (!itemJson.isEmpty()) {
                        collection.add(jsonToObject(itemJson, classType));
                    }
                    start = i + 1;
                }
            }
            
            // Добавляем последний элемент
            if (start < json.length()) {
                String itemJson = json.substring(start).trim();
                if (!itemJson.isEmpty()) {
                    collection.add(jsonToObject(itemJson, classType));
                }
            }
        }
        
        return collection;
    }
    
    // Создание директории, если она не существует
    private static void createDirectoryIfNotExists(String filePath) throws IOException {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        
        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                throw new IOException("Не удалось создать директорию: " + parentDir.getAbsolutePath());
            }
        }
    }
}