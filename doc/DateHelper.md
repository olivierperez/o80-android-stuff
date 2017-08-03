# DateHelper

```kotlin
// Kotlin
val timestamp : Long = Date().toTimestamp()
val date : Date = "597977100".strToDate()
val formatted : String = Date().getDateFormatted("yyyy")
val isToday : Boolean = Date().isDateToday()
val areSameDay : Boolean = Date().sameDayAs(Date())
val monthIsBefore : Boolean = aCalendar.monthIsBefore(otherCalendar)
```

```java
// Java
long timestamp = DateHelper.toTimestamp(new Date())
```