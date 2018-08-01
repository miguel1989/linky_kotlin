package linky.infra

import com.google.common.reflect.TypeToken

interface Reaction2<C : Command<R>, R : Command.R> {
    fun react(command: C): R
    fun commandType(): TypeToken<C> {
        return object : TypeToken<C>(javaClass) {}
    }
}