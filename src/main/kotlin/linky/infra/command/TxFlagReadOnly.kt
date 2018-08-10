package linky.infra.command

import org.springframework.transaction.support.TransactionTemplate

class TxFlagReadOnly : TxFlag {
    override fun apply(transactionTemplate: TransactionTemplate) {
        transactionTemplate.isReadOnly = true
    }
}