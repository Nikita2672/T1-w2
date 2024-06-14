# T1 Homework1 by Иванов Никита Денисович

Это домашняя работа №1 в [рамках открытых школ T1](https://t1.ru/internship/item/otkrytye-shkoly-ot-holdinga-t1/)

Тз к работе вы можете посмотреть по [ссылке](Task.md)

## Как начать

### 1. Настройка базы данных
Вам необходимо проставить значение переменных окружения DATABASE_URL, DATABASE_USERNAME DATABASE_PASSWORD это можно сделать командой:
```bash
export DATABASE_URL=<your database url>
export DATABASE_USERNAME=<your database username>
export DATABASE_PASSWORD=<your database password>
```
### 2. Настройка Kafka
Вам необходимо запустить [docker-compose.yml](docker-compose.yml)

### 3. Сборка и запуск приложения
Инструкцию по сборке и запуску приложения:
```bash
mvn clean install
java -jar producer/target/producer-0.0.1-SNAPSHOT.jar  
java -jar consumer/target/consumer-0.0.1-SNAPSHOT.jar 
```

### 4. Endpoint-ы и их описание
С дизайном endpoint-ов и их описанием вы можете ознакомиться, перейдя после запуска приложения по следующим ссылкам:

- **Producer**: [Swagger UI](http://localhost:8078/swagger-ui/index.html)
- **Consumer**: [Swagger UI](http://localhost:8079/swagger-ui/index.html)

---

## Общая архитектура приложения

Есть 3 maven модуля (`Producer`, `Consumer`, `Common`). `Producer` - модуль, который отвечает за генерацию метрик (сбор метрик 
происходит при помощи Spring Actuator). `Consumer` - модуль, который принимает метрики от продюсера и обрабатывает их 
(осуществляет логирование и сохранение в базу данных, а также предоставляет API для просмотра сохраненных метрик). 
`Producer` и `Consumer` - независимые друг от друга модули, все что у них есть общее вынесено в отдельный модуль - Common, 
от которого они оба зависят.

# Конфигурация Kafka

## Основной топик

- Есть следующий топик - `metrics-topic`.
- Топик имеет одну партицию и один реплицированный фрагмент.

## Топик для "мертвых" сообщений (DLT)

- Создается новый топик с именем `metrics-topic.DLT` для хранения сообщений, которые не удалось обработать.

## Обработчик ошибок

- Определяется обработчик ошибок, который использует `DeadLetterPublishingRecoverer` для отправки непринятых сообщений в DLT.
- Настраивается фиксированная задержка между попытками повторной обработки сообщений и ограничение на количество попыток.


---
**Примечание:** Этот проект разработан исключительно в учебных и демонстрационных целях.