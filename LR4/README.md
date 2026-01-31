# Лабораторная работа 4: Пакет java.net

## Описание

Данная лабораторная работа демонстрирует использование пакета `java.net` для создания сетевых приложений на Java.

## Структура проекта

```
LR4/
├── src/
│   ├── URLParser.java           # Задание 1: Парсер URL
│   ├── WebsiteReader.java       # Задание 2: Читатель веб-сайтов (Runnable)
│   ├── WebContentFetcher.java   # Задание 2: Многопоточный загрузчик
│   └── App.java                 # Главный класс для демонстрации
├── docs/                        # Справочные материалы
├── README.md                    # Документация
└── Task.md                      # Задание
```

## Задание 1: URL Parser

### Описание
Приложение для парсинга и отображения компонентов URL:
- **Protocol** - протокол (http, https, ftp)
- **Host** - имя хоста или IP-адрес
- **Port** - номер порта (явный или по умолчанию)
- **Path** - путь к ресурсу
- **File** - полный путь с параметрами запроса

### Ключевые классы и методы
- `java.net.URL` - основной класс для работы с URL
- `url.getProtocol()` - получение протокола
- `url.getHost()` - получение хоста
- `url.getPort()` - получение порта
- `url.getPath()` - получение пути
- `url.getFile()` - получение файла с параметрами

### Обработка исключений
```java
try {
    URL url = new URL(urlString);
    // работа с URL
} catch (MalformedURLException e) {
    // обработка неверного формата URL
}
```

### Примеры использования

```bash
# Компиляция
cd LR4/src
javac URLParser.java

# Запуск
java URLParser
```

### Примеры URL для тестирования
- `http://www.example.com` - простой HTTP URL
- `https://www.example.com:8080/api/users` - HTTPS с явным портом
- `http://www.example.com/search?q=java&lang=en` - URL с параметрами
- `ftp://ftp.example.com/pub/docs/manual.pdf` - FTP протокол

## Задание 2: Multi-threaded Web Content Fetcher

### Описание
Многопоточное приложение для одновременной загрузки содержимого с нескольких веб-сайтов.

### Архитектура
```
Main Thread
    ├── Thread-1 (WebsiteReader) → Загрузка с сайта A
    └── Thread-2 (WebsiteReader) → Загрузка с сайта B
```

### Ключевые особенности
1. **Многопоточность**: Использование `Thread` и `Runnable`
2. **Синхронизация**: `synchronized` блоки для безопасного вывода
3. **Управление ресурсами**: Автоматическое закрытие потоков (try-with-resources)
4. **Обработка ошибок**: Перехват сетевых исключений

### Классы

#### WebsiteReader
Реализует `Runnable` для чтения содержимого веб-сайта в отдельном потоке.

**Основные методы:**
- `run()` - основная логика потока
- `getLinesRead()` - количество прочитанных строк
- `getCharsRead()` - количество прочитанных символов
- `getExecutionTime()` - время выполнения

#### WebContentFetcher
Главный класс, управляющий потоками.

**Функциональность:**
- Создание и запуск потоков
- Ожидание завершения (`thread.join()`)
- Сбор и отображение статистики

### Использование

```bash
# Компиляция
cd LR4/src
javac WebsiteReader.java WebContentFetcher.java

# Запуск
java WebContentFetcher
```

### Синхронизация вывода
```java
synchronized (System.out) {
    System.out.println("[" + threadName + "] Message");
}
```

## Запуск всех заданий

```bash
# Компиляция всех файлов
cd LR4/src
javac *.java

# Запуск демонстрации обоих заданий
java App
```

## Обработка исключений

### MalformedURLException
Возникает при неверном формате URL:
```java
try {
    URL url = new URL("invalid url");
} catch (MalformedURLException e) {
    System.err.println("Invalid URL: " + e.getMessage());
}
```

### UnknownHostException
Возникает когда хост не может быть разрешен:
```java
try {
    url.openStream();
} catch (UnknownHostException e) {
    System.err.println("Unknown host: " + e.getMessage());
}
```

### IOException
Общие ошибки ввода-вывода:
```java
try {
    // сетевые операции
} catch (IOException e) {
    System.err.println("I/O error: " + e.getMessage());
}
```

## Ключевые концепции Java Networking

### 1. URL Class
- Представляет Uniform Resource Locator
- Методы для извлечения компонентов URL
- `openStream()` для чтения содержимого

### 2. URLConnection
- Более функциональный класс для работы с соединениями
- `getInputStream()` и `getOutputStream()`
- Управление заголовками и метаданными

### 3. Многопоточность
- `Thread` и `Runnable` для параллельного выполнения
- `thread.join()` для ожидания завершения
- `synchronized` для потокобезопасности

### 4. Управление ресурсами
- Try-with-resources для автоматического закрытия
- Правильное управление потоками ввода-вывода

## Примеры вывода

### Task 1 - URLParser
```
============================================================
Parsing URL: https://www.example.com:8080/api/users
============================================================

📋 URL Components:
------------------------------------------------------------
Protocol            : https
Host                : www.example.com
Port                : 8080 (explicit)
Default Port        : 443
Path                : /api/users
File                : /api/users
Authority           : www.example.com:8080
------------------------------------------------------------
Complete URL        : https://www.example.com:8080/api/users
```

### Task 2 - WebContentFetcher
```
[Thread-1] Starting: http://www.example.com
[Thread-2] Starting: http://www.example.org
[Thread-1] Connected successfully
[Thread-2] Connected successfully
[Thread-1] Completed: 150 lines, 5432 characters
[Thread-1] Execution time: 1.2 seconds
[Thread-2] Completed: 200 lines, 7891 characters
[Thread-2] Execution time: 1.5 seconds

============================================================
SUMMARY:
============================================================
Thread-1 status: SUCCESS
Thread-2 status: SUCCESS
Total execution time: 1.5 seconds
Total lines read: 350
Total characters read: 13323
============================================================
```

## Требования

- Java 8 или выше
- Доступ к интернету для Task 2

## Автор

Абрамов Илья, МКС-254

## Дата

Январь 2026