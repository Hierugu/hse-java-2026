# Лабораторная работа №2: Работа с файлами и обработка исключений

## Задание

Добавить в разработанные в предыдущих работах классах возможность записи данных в файл и считывания данных из файла.

## Реализация

### Архитектура решения

Для реализации файловых операций была разработана следующая архитектура:

1. **FileManager** - утилитный класс для работы с файлами
2. **FileOperationException** - пользовательское исключение для файловых операций
3. **Модифицированный App** - демонстрация работы с файлами

### Использованные классы для ввода-вывода

Для работы с файлами были использованы следующие классы из пакета java.io:

- **FileReader** - для чтения символов из файла
- **FileWriter** - для записи символов в файл
- **BufferedReader** - для буферизованного чтения из файла
- **BufferedWriter** - для буферизованной записи в файл
- **IOException** - базовый класс исключений ввода-вывода
- **FileNotFoundException** - исключение при отсутствии файла

### Использованные классы для обработки исключений

Для обработки исключений были использованы:

1. **Стандартные исключения Java:**
   - `IOException` - ошибки ввода/вывода
   - `FileNotFoundException` - файл не найден

2. **Пользовательское исключение:**
   - `FileOperationException` - создано для инкапсуляции ошибок файловых операций

### Описание разработанных классов

#### FileOperationException.java

Пользовательское исключение для обработки ошибок файловых операций:

```java
public class FileOperationException extends Exception {
    public FileOperationException(String message) {
        super(message);
    }
    
    public FileOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
```

#### FileManager.java

Утилитный класс, предоставляющий методы для работы с файлами:

**Основные методы:**
- `saveToFile(Object obj, String filePath)` - сохранение объекта в JSON файл
- `loadFromFile(String filePath, Class<T> classType)` - загрузка объекта из JSON файла
- `saveCollectionToFile(List<T> collection, String filePath)` - сохранение коллекции в JSON файл
- `loadCollectionFromFile(String filePath, Class<T> classType)` - загрузка коллекции из JSON файла

**Вспомогательные методы:**
- `objectToJson(Object obj)` - преобразование объекта в JSON строку
- `jsonToObject(String json, Class<T> classType)` - преобразование JSON в объект
- `collectionToJson(List<T> collection)` - преобразование коллекции в JSON
- `jsonToCollection(String json, Class<T> classType)` - преобразование JSON в коллекцию
- `createDirectoryIfNotExists(String filePath)` - создание директории при необходимости

#### Модификация App.java

В класс App был добавлен метод `demonstrateFileOperations()`, который демонстрирует:

1. Создание тестовых объектов
2. Сохранение отдельных объектов в файлы
3. Загрузку отдельных объектов из файлов
4. Работу с коллекциями объектов
5. Обработку исключений при работе с файлами

### Формат данных

Для хранения данных был выбран JSON формат, который обеспечивает:
- Читаемость для человека
- Структурированное хранение данных
- Поддержку вложенных объектов
- Легкость парсинга и генерации

### Пример использования

```java
// Сохранение объекта в файл
Engine engine = new Engine("Test Engine", 30000, 15.5);
FileManager.saveToFile(engine, "data/engine.json");

// Загрузка объекта из файла
Engine loadedEngine = FileManager.loadFromFile("data/engine.json", Engine.class);

// Работа с коллекциями
List<Aeroplane> fleet = new ArrayList<>();
fleet.add(passengerPlane);
fleet.add(cargoPlane);

FileManager.saveCollectionToFile(fleet, "data/fleet.json");
List<Aeroplane> loadedFleet = FileManager.loadCollectionFromFile("data/fleet.json", Aeroplane.class);
```

### Обработка исключений

Все файловые операции обернуты в блоки try-catch с использованием пользовательского исключения `FileOperationException`:

```java
try {
    FileManager.saveToFile(obj, filePath);
} catch (FileOperationException e) {
    System.err.println("Ошибка при сохранении файла: " + e.getMessage());
    e.printStackTrace();
}
```

## Результаты работы программы

Программа успешно демонстрирует:

1. ✅ Сохранение объектов всех типов (Vehicle, Aeroplane, PassengerPlane, CargoPlane, Engine) в JSON файлы
2. ✅ Загрузку объектов из JSON файлов с восстановлением всех полей
3. ✅ Работу с коллекциями объектов
4. ✅ Обработку исключений при работе с файлами
5. ✅ Создание директорий при необходимости

## Выводы

В ходе выполнения лабораторной работы были решены следующие задачи:

1. **Реализована возможность записи данных в файл** - все поля объектов сохраняются в структурированном JSON формате
2. **Реализована возможность считывания данных из файла** - объекты полностью восстанавливаются из файлов с сохранением всех полей
3. **Освоена работа с классами ввода-вывода** - использованы FileReader, FileWriter, BufferedReader, BufferedWriter
4. **Реализована обработка исключений** - создано пользовательское исключение и обработаны стандартные исключения ввода-вывода
5. **Продемонстрирована работа с различными типами данных** - примитивные типы, строки, объекты, коллекции

Разработанное решение обеспечивает надежную работу с файлами, сохраняет целостность данных и предоставляет удобный интерфейс для файловых операций.