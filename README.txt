This is a sample Android-Native application showing some github repositories

The application contains only one screen(RepositoriesActivity) for displaying the list of repositories

App architecture: clean-architecture + MVVM + Kotlin

Below is described in short details what dependencies and libraries are used in the app

- Dependency Injection - used Dagger 2.x
- For making http calls at background -> used Kotlin coroutines
- Network layer - used Retrofit 2.x
- Unit tests -> Mockito
- Serialization/Deserialization objects from/to Json -> Gson
- Image downloading and caching -> Picasso
