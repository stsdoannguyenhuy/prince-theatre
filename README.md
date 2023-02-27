# Prince theater

The movie price comparison android sample project between Filmworld and Cinemaworld theater.
This app helps people to see which film is available and compare price between two cinemas
![Sample-video](./documentation/demo.gif)
------------
[![API](https://img.shields.io/badge/API-24%2B-orange.svg?style=flat)](https://android-arsenal.com/api?level=24) [![TARGET API](https://img.shields.io/badge/TARGET_API-33-green.svg?style=flat)](https://android-arsenal.com/api?level=33) [![Size](https://img.shields.io/badge/apk_size-~5Mb-blue.svg?style=flat)](#)
------------
## This sample code is using:
- [Kotlin language](https://kotlinlang.org/)
- [Hilt Dagger Dependency Injection](https://dagger.dev/hilt/)
- [Retrofit](https://square.github.io/retrofit/)
- [RxKotlin](https://reactivex.io/)
- Android basic layout and components
- Backend API from [challenge api](https://challenge.lexicondigital.com.au/api/v2/filmworld/movies) with `x-api-key` provided

## Source code structure
![project-structure](./documentation/project-structure.jpg)
- `app`: android application instance
- `base`: our base classes, such as `BaseActivity`
- `data`: contains only data class (Pojo, DTO, model...)
- `di`: Dependency Injection
- `repository`: defines get/update/delete actions of object. In this project, that's `FilmRepository`
- `service`: Our business logic should come here
- `ui`: contains files related to the layout, view, activity, dialog...
- `util`: some helper files and functions
----------
## Testing
### Unit test
- [Test Report & Coverage](./documentation/test-report.html)
- I don't write unit test for `com.interview.princethreatre.ui` and generated-code by `dagger`