package ru.iasokolov.demo

import com.fasterxml.uuid.Generators
import com.fasterxml.uuid.impl.TimeBasedEpochGenerator
import com.fasterxml.uuid.impl.TimeBasedReorderedGenerator
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import ru.iasokolov.demo.model.bigint.BigintTable
import ru.iasokolov.demo.model.TableData
import ru.iasokolov.demo.model.bigint.BigintRepository
import ru.iasokolov.demo.model.statistic.StatisticTable
import ru.iasokolov.demo.model.uuidv4.UuidV4Table
import ru.iasokolov.demo.model.statistic.StatisticTableRepository
import ru.iasokolov.demo.model.uuidv4.UuidV4Repository
import ru.iasokolov.demo.model.uuidv6.UuidV6Repository
import ru.iasokolov.demo.model.uuidv6.UuidV6Table
import ru.iasokolov.demo.model.uuidv7.UuidV7Table
import ru.iasokolov.demo.model.uuidv7.UuidV7Repository
import java.time.Duration
import java.time.LocalTime
import java.util.*

@Component
class CommandLineRunner(
    private val bigintRepository: BigintRepository,
    private val uuidV4Repository: UuidV4Repository,
    private val uuidV6Repository: UuidV6Repository,
    private val uuidV7Repository: UuidV7Repository,
    private val statisticRepository: StatisticTableRepository
) : CommandLineRunner {
    val uuidV6Generator: TimeBasedReorderedGenerator = Generators.timeBasedReorderedGenerator()
    val uuidV7Generator: TimeBasedEpochGenerator = Generators.timeBasedEpochGenerator()

    var id: Long = 0L

    companion object {
        const val PACKAGE_COUNT = 10_000
        const val PACKAGE_RECORD_SIZE = 100L
    }

    override fun run(vararg args: String?) {
        prepareTable()

        (1..PACKAGE_COUNT).forEach { pack ->
            val stat = StatisticTable(
                id = pack,
                bigintId = fullBigintTable(PACKAGE_RECORD_SIZE).toMillis(),
                uuidV4Id = fullUuidV4Table(PACKAGE_RECORD_SIZE).toMillis(),
                uuidV6Id = fullUuidV6Table(PACKAGE_RECORD_SIZE).toMillis(),
                uuidV7Id = fullUuidV7Table(PACKAGE_RECORD_SIZE).toMillis()
            )
            statisticRepository.save(stat)

            println("package $pack complete")
        }

        printStatistic()
    }

    fun prepareTable() {
        statisticRepository.deleteAll()
        bigintRepository.deleteAll()
        uuidV4Repository.deleteAll()
        uuidV6Repository.deleteAll()
        uuidV7Repository.deleteAll()

        println("table prepared")
    }

    fun printStatistic() {
        val avg = statisticRepository.getAvg()

        println("--- result ---")
        println("bigint: ${avg.bigint}")
        println("uuid v4: ${avg.uuidV4}")
        println("uuid v6: ${avg.uuidV6}")
        println("uuid v7: ${avg.uuidV7}")
    }

    fun fullBigintTable(recordCount: Long): Duration {
        val begin = LocalTime.now()

        (1..recordCount).forEach { _ ->
            val model = BigintTable(
                id = ++id,
                data = createTableData()
            )
            bigintRepository.save(model)
        }

        val end = LocalTime.now()
        return Duration.between(begin, end)
    }

    fun fullUuidV4Table(recordCount: Long): Duration {
        val begin = LocalTime.now()

        (1..recordCount).forEach { _ ->
            val model = UuidV4Table(
                id = UUID.randomUUID(),
                data = createTableData()
            )
            uuidV4Repository.save(model)
        }

        val end = LocalTime.now()
        return Duration.between(begin, end)
    }

    fun fullUuidV6Table(recordCount: Long): Duration {
        val begin = LocalTime.now()

        (1..recordCount).forEach { _ ->
            val model = UuidV6Table(
                id = uuidV6Generator.generate(),
                data = createTableData()
            )
            uuidV6Repository.save(model)
        }

        val end = LocalTime.now()
        return Duration.between(begin, end)
    }

    fun fullUuidV7Table(recordCount: Long): Duration {
        val begin = LocalTime.now()

        (1..recordCount).forEach { _ ->
            val model = UuidV7Table(
                id = uuidV7Generator.generate(),
                data = createTableData()
            )
            uuidV7Repository.save(model)
        }

        val end = LocalTime.now()
        return Duration.between(begin, end)
    }

    fun createTableData() = TableData(
        firstname = "firstname",
        lastname = "lastname",
        citizenship = "citizenship"
    )
}