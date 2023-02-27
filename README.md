# Prince theater

The movie price comparison android sample project between Filmworld and Cinemaworld theater
This app helps people to see which film is available and compare price between two cinemas
![Sample-video](./documentation/demo.gif)
------------
## This sample code is using:
- [Kotlin language](https://kotlinlang.org/)
- [Hilt Dagger Dependency Injection](https://dagger.dev/hilt/)
- [Retrofit](https://square.github.io/retrofit/)
- [RxKotlin](https://reactivex.io/)
- Android basic layout and components

## Source code structure
![project-structure](./documentation/project-structure.jpg)
- `app`: android application instance
- `base`: our base classes, such as `BaseActivity`
- `data`: contains only data class (Pojo, DTO, model...)
- `di`: Denpendency Injection
- `repository`: defines get/update/delete actions of object. In this project, that's `FilmRepository`
- `service`: Our business logic should come here
- `ui`: contains files related to the layout, view, activity, dialog...
- `util`: some helper files and functions