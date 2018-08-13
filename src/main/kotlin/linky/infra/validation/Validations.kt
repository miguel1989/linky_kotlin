package linky.infra.validation

import linky.infra.command.Command
import linky.infra.command.Return
import org.springframework.beans.factory.ListableBeanFactory
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import java.lang.reflect.Type

@Component
class Validations(private val beanFactory: ListableBeanFactory) {
    private val mapping = ConcurrentHashMap<Type, Validation<*>>()

    init {
        validations().forEach { validation ->
            mapping[validation.commandType().type] = validation
        }
    }

    private fun validations(): MutableCollection<Validation<*>> {
        return beanFactory.getBeansOfType(Validation::class.java).values
    }

    fun <C : Command<R>, R : Return> byCommand(command: C): Validation<C>? {
        if (mapping[command.type()] != null) {
            return mapping[command.type()] as Validation<C>
        }
        return null
    }
}
