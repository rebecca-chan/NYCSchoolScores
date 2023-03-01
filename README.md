#NYCSchools<br>
Built with Android Studio Dolphin | 2021.3.1<br>
I focused on using MVVM with Hilt as a start to this project so it can be scalable and maintainable later.<br>
Other libraries I used - Retrofit for network calls, Coroutines for long running tasks<br>
Things I would have done with more time:<br>
Set up cleaner Loading and Error states, specifying what the errors are to user, add couroutine exception handler for better logging<br>
Add UI/Instrumentation Tests<br>
Use Room for persisting data<br>
Use a fragment for the Scores details instead of an Activity for reusability/modularization<br><br>

How To Install App<br>

./gradlew installDebug<br><br>



How To Run Unit Tests<br>

./gradlew test
