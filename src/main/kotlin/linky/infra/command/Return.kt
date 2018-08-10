package linky.infra.command

import java.util.*

interface Return {
    open class Void : Return
    open class Id(val uuid: UUID) : Return
}