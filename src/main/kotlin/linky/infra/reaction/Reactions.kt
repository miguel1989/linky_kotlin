package linky.infra.reaction

import linky.infra.command.Command
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.ListableBeanFactory
import org.springframework.stereotype.Component
import java.lang.reflect.Type
import java.util.concurrent.ConcurrentHashMap

@Component
class Reactions(private val beanFactory: ListableBeanFactory) {
    private val logger = LoggerFactory.getLogger(Reactions::class.java)
//    val mapping: MutableMap = mutableMapOf<Type, Reaction<C: Command<R>, R: Command.R> >()
    private val mapping = ConcurrentHashMap<Type, Reaction<*, *>>()

    init {
        reactions().forEach { reaction ->
            mapping[reaction.commandType().type] = reaction
        }
        logger.info(mapping.toString())
    }

    private fun reactions(): MutableCollection<Reaction<*, *>> {
//        return ListableBeanFactoryExtensions.getBeansOfType<Reaction>()
        return beanFactory.getBeansOfType(Reaction::class.java).values
    }

    fun <C : Command<R>, R : Command.R> byCommand(command: C): Reaction<C, R> {
        return mapping[command.type()] as Reaction<C, R>? ?: throw NoReactionFound(command.type())
    }

    class NoReactionFound(type: Type) : RuntimeException("No reaction found for type $type")
}