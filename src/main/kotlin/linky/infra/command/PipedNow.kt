package linky.infra.command

import linky.infra.reaction.Reaction
import linky.infra.reaction.Reactions
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate

@Component
//val txManager: PlatformTransactionManager
class PipedNow(
        private val reactions:Reactions,
        private val platformTransactionManager: PlatformTransactionManager
) : Now {
    override fun <C : Command<R>, R : Return> execute(command: C): R {
        val piped: Now = Loggable(Transactional(Reacting()))
        return piped.execute(command)
    }

    inner class Loggable(private val origin: Now) : Now {
        private val logger = LoggerFactory.getLogger(Loggable::class.java)!!
        override fun <C : Command<R>, R : Return> execute(command: C): R {
            logger.info("--- Start execute command {} ---", command.toLogString())
            val response = origin.execute(command)
            logger.info("--- End execute command {} ---", command.toLogString())
            return response
        }
    }

    inner class Transactional(private val origin: Now) : Now {
        override fun <C : Command<R>, R : Return> execute(command: C): R {
            val txTemplate = TransactionTemplate(platformTransactionManager)
            command.txFlags().forEach{ flag -> flag.apply(txTemplate)}
            return txTemplate.execute { transactionStatus -> origin.execute(command) }
        }
    }

    inner class Reacting : Now {
        override fun <C : Command<R>, R : Return> execute(command: C): R {
            val reaction: Reaction<C, R> = reactions.byCommand(command)
            return reaction.react(command)
        }
    }
}