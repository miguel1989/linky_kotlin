package linky.infra

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
//import org.springframework.transaction.PlatformTransactionManager

@Component
//val txManager: PlatformTransactionManager
class PipedNow(val reactions:Reactions) : Now {
    override fun <C : Command<R>, R : Command.R> execute(command: C): R {
        val piped: Now = Loggable(Reacting())
        return piped.execute(command)
    }

    class Loggable(private val origin: Now) : Now {
        private val logger = LoggerFactory.getLogger(Loggable::class.java)!!
        override fun <C : Command<R>, R : Command.R> execute(command: C): R {
            logger.info("--- Start execute command {} ---", command.toLogString())
            val response = origin.execute(command)
            logger.info("--- End execute command {} ---", command.toLogString())
            return response
        }
    }

    inner class Reacting : Now {
        override fun <C : Command<R>, R : Command.R> execute(command: C): R {
            val reaction: Reaction<C, R> = reactions.byCommand(command)
            return reaction.react(command)
        }
    }
}