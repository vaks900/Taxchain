package com.ey.taxchain.state

import com.ey.taxchain.contract.PactContract
import com.ey.taxchain.model.Pact
import com.ey.taxchain.schema.PactSchemaV1
import net.corda.core.contracts.LinearState
import net.corda.core.contracts.UniqueIdentifier
import net.corda.core.crypto.keys
import net.corda.core.identity.AbstractParty
import net.corda.core.identity.Party
import net.corda.core.schemas.MappedSchema
import net.corda.core.schemas.PersistentState
import net.corda.core.schemas.QueryableState
import java.security.PublicKey
import java.time.Instant

data class PactState(val order: Pact,
                     val seller: Party,
                     val buyer: Party,
                     val date: Instant = Instant.now(),
                     override val linearId: UniqueIdentifier = UniqueIdentifier()
) : LinearState, QueryableState {
    /*override*/ val contract get() = PactContract()

     /** The public keys of the involved parties. */
      override val participants: List<AbstractParty> get () = listOf(buyer,seller)
     /** Tells the vault to track a state if we are one of the parties involved. */
     /*override*/ fun isRelevant(ourKeys: Set<PublicKey>) = ourKeys.intersect(participants.flatMap { it.owningKey.keys }).isNotEmpty()

     fun withNewOwner(newOwner: Party) = copy(buyer = newOwner)
    override fun generateMappedObject(schema: MappedSchema): PersistentState {
        return when (schema) {
            is PactSchemaV1 -> PactSchemaV1.PersistentPact(
                    customerName = this.order.customerName,
                    custId = this.order.custId,
                    age = this.order.age,
                    category = this.order.category,
                    investmentType = this.order.investmentType,
                    sellerName = this.seller.name.toString(),
                    buyerName = this.buyer.name.toString(),
                    referenceNumber = this.order.referenceNumber,
                    totalAmount = this.order.totalAmount,
                    date = Instant.now()
            )
            else -> throw IllegalArgumentException("Unrecognised schema $schema")
        }
    } override fun supportedSchemas(): Iterable<MappedSchema> = listOf(PactSchemaV1)
}