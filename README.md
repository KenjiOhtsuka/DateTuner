# Date Tuner

[![](https://jitpack.io/v/KenjiOhtsuka/DateTuner.svg)](https://jitpack.io/#KenjiOhtsuka/DateTuner)

Kotlin Date Conversion Library

[API Document](https://kenjiohtsuka.github.io/DateTuner/api/date_tuner/index.html)

This library can generate and convert data objects,
such as `java.util.Date`,  `java.time.YearMonth`, `java.time.LocalDate`, etc. 

## Usage

Here are some example.

* When you want to create current `Date` object.

    ```kotlin
    DateTuner.createCurrentDate()
    ```

* When you want to create current `YearMonth` object.

    ```kotlin
    DateTuner.createCurrentYearMonth()
    ``` 
    
* When you want to create previous `YearMonth` object.

    ```kotlin
    // Last month
    DateTuner.createPreviousYearMonth()
    // The month before last month
    // You can put offset as an argument
    DateTuber.createPreviousYearMonth(2)
    ```

* When you want to get month number from a `Date` or `YearMonth` object,
you can get it through the same method.

    ```kotlin
    DateTuner.getMonth(date)
    DateTuner.getMonth(yearMonth)
    ```
