# Android Unit and Integration Testing

Tempate and example test cases

## Dependencies Used

Add the following in the build.gradle app file

```bash

    testImplementation 'junit:junit:<VERSION>'
    androidTestImplementation 'androidx.test.ext:<VERSION>'
    androidTestImplementation 'androidx.test.espresso:espresso-core:<VERSION>'
    testImplementation 'com.google.truth:truth:<VERSION>'
    androidTestImplementation 'com.google.truth:truth:<VERSION>'

```

## Other Notes

- testImplemetation adds dependencies to the test file
- androidTestImplementation adds dependencies to the androidTest file

- **Unit Tests**- Test of single units of our app(eg. testing the function of a class)~70%
- **Integration Tests-** Tests how two components of our app work together(eg. Fragment and ViewModel)~20%
- **UI Test**-  Tests that check if many or all components of your app work well together and if the UI look how it should~10%

### Test Driven Development

Development style that highly values testing.

Test cases(Unit Tests) are written before development

1. Write the function signature
2. Write test case for that function
3. Write function logic so the test cases pass

- You should only have one assertion per test case

Things that make a good test case:

1. Scope
2. Speed
3. Fidelity
4. Not a flaky test(That sometimes fail and sometimes succeed)

### Some Notes

- All tests

## Testing Android Components

@Before- is used to annotated a function that will run before every test case hence reducing boilerplate code

@After- is used to run the code annotated by it after each test case

## Room Database testing

Make class in androidTest folder(Instrumentation test) and make the same folder structure

annotate with @RunWith(AndroidJunit::class)

@SmallTest -telling it is unit test

@MediumTest- Integration test

@LargeTest-Ui test

![Testing%207140c7f24c714e81ae5b9bb9cbf194db/test.png](https://github.com/rawlin/Android_Testing/blob/master/Images/test.png)

- Use `runBlockingTest{}` which is a special runBlocking for tests to run suspend functions on the main thread
- Add LiveDataUtilAndroidTest.kt file to make LiveData synchronous
- Add below to tell JUnit to run the test code in one thread (Main)line by line

```bash
@get:Rule
var instantTaskExecuterRule=InstantTaskExecutorRule()
```

### Creating Fake Repository(Test Double) for Testing

Create an interface so that both the real and fake repositories use the same functions

### Testing ViewModel

Make the test class in the the test directory as they can be run in the JVM

Main Dispatcher not available in Tests because Coroutine main dispatcher relies on Main Looper which is only available in a real app environment(for test Directory)

**To solve the above problem we need to create our own JUnit Rule as below:**

```kotlin
@ExperimentalCoroutinesApi
class MainCoroutineRule(
    private val dispatcher: CoroutineDispatcher=TestCoroutineDispatcher()
):TestWatcher(),TestCoroutineScope by TestCoroutineScope(dispatcher) {

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        cleanupTestCoroutines()
        Dispatchers.resetMain()
    }
}
```

## Testing with Hilt

Add these dependencies

```kotlin
androidTestImplementation 'com.google.dagger:hilt-android-testing:2.28-alpha'
kaptAndroidTest 'com.google.dagger:hilt-android-compiler:2.28-alpha'
```

PS: only for Instrumentation test 

Then create HiltTestRunner as below:

```kotlin
class HiltTestRunner :AndroidJUnitRunner(){

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}
```

 then replace  `testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"` with 

`testInstrumentationRunner "<DIRECTORY OF HiltTestRunner>"`

Annotate the Test class with `@HiltAndroidTest`

Add Rule to Test class

```
@get:Rule
val hiltRule=HiltAndroidRule(this)
```

```kotlin
@Before
fun setup(){
	hiltRule.inject()
	........
}
```

