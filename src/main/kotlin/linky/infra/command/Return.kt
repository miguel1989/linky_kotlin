package linky.infra.command

import java.util.*

interface Return {
    class Void : Return
    class Id(val uuid: UUID) : Return
}