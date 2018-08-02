package linky.infra.command

interface Now {
    fun <C : Command<R>, R : Command.R> execute(command: C): R
}