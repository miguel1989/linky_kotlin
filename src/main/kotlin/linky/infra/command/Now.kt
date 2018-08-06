package linky.infra.command

interface Now {
    fun <C : Command<R>, R : Return> execute(command: C): R
}