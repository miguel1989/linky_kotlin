package linky.infra.command

import java.lang.reflect.Type
import java.util.*

interface Command<T : Command.R> {
    interface R {
        class Void : R
        class Id(val uuid: UUID) : R
    }

    fun execute(now: Now):T {
        return now.execute(this)
    }

    fun toLogString(): String
    fun type(): Type {
        return this.javaClass
    }
}