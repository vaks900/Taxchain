package com.ey.taxchain.model

import net.corda.core.serialization.CordaSerializable

@CordaSerializable
data class Pact(
        val customerName: String,
        val custId: String,
        val age: String,
        val category: String,
        val investmentType:String,
        val referenceNumber: String,
        val totalAmount: Double)