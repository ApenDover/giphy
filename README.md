# Giphy Service
Создать сервис, который обращается к сервису курсов валют, и отдает gif в ответ:<br>
Если курс по отношению к рублю за сегодня стал выше вчерашнего, то отдаем рандомную отсюда https://giphy.com/search/rich <br>
Если ниже - отсюда https://giphy.com/search/broke <br>
<br>Ссылки<br>
REST API курсов валют: https://docs.openexchangerates.org/ <br>
REST API гифок: https://developers.giphy.com/docs/api#quick-start-guide
<br><br>
Must Have
<br>
Сервис на Spring Boot 2 + Java / Kotlin. Запросы приходят на HTTP endpoint, туда передается код валюты.
Для взаимодействия с внешними сервисами используется Feign. Все параметры (валюта по отношению к которой смотрится курс, адреса внешних сервисов и т.д.) вынесены в настройки.
<br>
На сервис написаны тесты (для мока внешних сервисов можно использовать @mockbean или WireMock). Для сборки должен использоваться Gradle. Результатом выполнения должен быть репо на GitHub с инструкцией по запуску.
<br><br>Nice to Have<br>
Сборка и запуск Docker контейнера с этим сервисом.

# Настройки

Для конфигурации отредактируйте файл application.properties
connectTimeout и readTimeout настраиваются в application.yml

# Инструкция по запуску

Склонировать репозиторий, выполнив команду:
git clone https://github.com/ApenDover/giphy.git
Перейдя в корневую папку проекта собрать проект:
gradlew build
Собрать докер-образ с произвольным именем, в нашем случае giphy:
docker image build -t giphy .
Запустить контейнер с нашим образом:
docker run --publish 8080:8080 giphy

# Endpoints

/api/getGiphy
<br>
Parameters
<br>
currencyCode: int
<br><br>
Пример
http://localhost:8080/api/getGiphy?currencyCode=643
<br>
<br>
Response:

```json
{
    "errorCode": 200,
    "message": "Success",
    "cash": false,
    "giphyUrl": "https://media2.giphy.com/media/viyWjgY1fGEWWUfVGK/giphy-downsized-medium.gif?cid=6a3f003c2aa1c903fbde3923ec428c46a9447ebc37e70762&ep=v1_gifs_random&rid=giphy-downsized-medium.gif&ct=g",
    "giphyMood": "rich",
    "baseCurrency": "USD",
    "requestCurrency": "RUB",
    "rateAmountToday": 90.657494,
    "rateAmountYesterday": 90.674999
}
```

front доступен по адресу:
http://localhost:8080/

# Заметка

Внешнее API на бесплатной версии отдает только base:USD, в случае передачи другой валюты будет выброщен соответствующий ответ от сервера.<br>
В случае, если внешний сервис не успеет ответить в рамках настроек application.yml ответ случайной гифки будет отдан из CASH. Размер CASH ограничен.<br>
Внешний сервис курс обновляет раз в сутки, поэтому нет смысла его дергать с каждым обращением. Ответ значения курса также кешируется.
