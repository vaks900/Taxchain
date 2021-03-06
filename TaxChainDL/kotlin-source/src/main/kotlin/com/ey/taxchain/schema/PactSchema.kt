package com.ey.taxchain.schema

import net.corda.core.schemas.MappedSchema
import net.corda.core.schemas.PersistentState
import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

object PactSchema
object PactSchemaV1 : MappedSchema(
        schemaFamily = PactSchema.javaClass,
        version = 1, mappedTypes = listOf(PersistentPact::class.java)) {
    @Entity
    @Table(name = "pact_states")
    class PersistentPact(
            @Column(name = "customer_name")
            var customerName: String,
            @Column(name = "customer_id")
            var custId: String,
            @Column(name = "age")
            var age: String,
            @Column(name = "category")
            var category: String,
            @Column(name = "investment_type")
            var investmentType: String,
            @Column(name = "seller_name")
            var sellerName: String,
            @Column(name = "buyer_name")
            var buyerName: String,
            @Column(name = "amount")
            var totalAmount: Double,
            @Column(name = "reference_no")
            var referenceNumber: String,

            @Column(name = "date")
            var date: Instant
    ) : PersistentState()
}