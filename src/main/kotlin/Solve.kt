import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.rendering.TextStyles
import kotlin.reflect.KClass

// Inspired by code from Olaf Gottschalk

var globalTestData: String? = null
    get() = field?.also {
        println("\n !!! USING TEST DATA !!! \n")
        field = null
    }

inline fun <reified T: Day> solve(test: SolveDsl<T>.() -> Unit = {}) {
    if (SolveDsl(T::class).apply(test).isEverythingOK())
        create(T::class).solve()
}

class SolveDsl<T : Day>(private val dayClass: KClass<T>) {
    val tests = mutableListOf<TestData>()

    var ok = true

    operator fun String.invoke(part1: Any? = null, part2: Any? = null) {
        ok || return
        ok = TestData(this, part1, part2).passesTestsUsing(dayClass)
    }

    infix fun String.part1(expectedPart1: Any?): TestData =
        TestData(this, expectedPart1, null).also { tests += it }

    infix fun String.part2(expectedPart2: Any?): TestData =
        TestData(this, null, expectedPart2).also { tests += it }

    infix fun TestData.part2(expectedPart2: Any?) {
        tests.remove(this)
        copy(expectedPart2 = expectedPart2).also { tests += it }
    }

    fun isEverythingOK() = ok && tests.all { it.passesTestsUsing(dayClass) }
}

fun <T : Day> create(dayClass: KClass<T>): T =
    dayClass.constructors.first { it.parameters.isEmpty() }.call()

data class TestData(val input: String, val expectedPart1: Any?, val expectedPart2: Any?) {
    fun <T : Day> passesTestsUsing(dayClass: KClass<T>): Boolean {
        (expectedPart1 != null || expectedPart2 != null) || return true
        globalTestData = input
        val day = create(dayClass)
        return listOfNotNull(
            Triple(1, { day.part1() }, "$expectedPart1").takeIf { expectedPart1 != null },
            Triple(2, { day.part2() }, "$expectedPart2").takeIf { expectedPart2 != null }
        ).all { (part, partFun, expectation) ->
            println(gray("Checking part $part against $expectation"))
            val actual = partFun()
            val match = actual == Day.NotYetImplemented || "$actual" == expectation
            if (!match) {
                println("Expected: ${brightRed(expectation)}")
                println("  Actual: ${brightRed("$actual")}")
                println(yellow("Check demo ${TextStyles.bold("input")} and demo ${TextStyles.bold("expectation")}!"))
            }
            match
        }
    }
}