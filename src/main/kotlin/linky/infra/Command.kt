package linky.infra

import java.lang.reflect.Type
import java.util.*

interface Command<T : Command.R> {
    interface R {
        class Void : Command.R
        class Id(val uuid: UUID) : Command.R
    }

    fun execute(now: Now):T {
        return now.execute(this)
    }

    fun toLogString(): String
    fun type(): Type {
        return this.javaClass
    }
}