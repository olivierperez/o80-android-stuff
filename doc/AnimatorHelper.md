# AnimatorHelper

```kotlin
// Kotlin
val view : View = /*...*/
view.fadeIn()
view.fadeIn(1000)
view.fadeOut()
view.fadeOut(1000)
view.fadeOut(completelyHide = false)
```

```java
// Java
View view = /*...*/;
AnimatorHelper.fadeIn(view);
AnimatorHelper.fadeIn(view, 1000);
AnimatorHelper.fadeOut(view);
AnimatorHelper.fadeOut(view, 1000);
AnimatorHelper.fadeOut(view, 1000, false);
```