package linky.infra.command

import java.lang.reflect.Type

interface Command<R : Return> {
    fun execute(now: Now): R {
        return now.execute(this)
    }

    fun toLogString(): String
    fun type(): Type {
        return this.javaClass
    }
}