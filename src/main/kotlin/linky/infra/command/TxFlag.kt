package linky.infra.command

import org.springframework.transaction.support.TransactionTemplate

interface TxFlag {
    fun apply(transactionTemplate: TransactionTemplate)
}