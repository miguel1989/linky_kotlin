package linky.infra

interface Now {
    fun <C : Command<R>, R : Command.R> execute(command: C): R
}