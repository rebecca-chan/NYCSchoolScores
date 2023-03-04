#NYCSchools
Built with Android Studio Dolphin | 2021.3.1
I focused on using MVVM with Hilt as a start to this project so it can be scalable and maintainable later.
Other libraries I used - Retrofit for network calls, Coroutines for long running tasks
Things I would have done with more time:
Set up cleaner Loading and Error states, specifying what the errors are to user, add couroutine exception handler for better logging
Add UI/Instrumentation Tests
Use Room for persisting data
Use a fragment for the Scores details instead of an Activity for reusability/modularization

How To Install App

./gradlew installDebug



How To Run Unit Tests

./gradlew test
