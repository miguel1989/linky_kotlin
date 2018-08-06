package linky.infra.reaction

import linky.infra.command.Command
import linky.infra.command.Return
import org.springframework.beans.factory.ListableBeanFactory
import org.springframework.stereotype.Component
import java.lang.reflect.Type
import java.util.concurrent.ConcurrentHashMap

@Component
class Reactions(private val beanFactory: ListableBeanFactory) {
    private val mapping = ConcurrentHashMap<Type, Reaction<*, *>>()

    init {
        reactions().forEach { reaction ->
            mapping[reaction.commandType().type] = reaction
        }
    }

    private fun reactions(): MutableCollection<Reaction<*, *>> {
//        return ListableBeanFactoryExtensions.getBeansOfType<Reaction>()
        return beanFactory.getBeansOfType(Reaction::class.java).values
    }

    fun <C : Command<R>, R : Return> byCommand(command: C): Reaction<C, R> {
        return mapping[command.type()] as Reaction<C, R>? ?: throw NoReactionFound(command.type())
    }

    class NoReactionFound(type: Type) : RuntimeException("No reaction found for type $type")
}