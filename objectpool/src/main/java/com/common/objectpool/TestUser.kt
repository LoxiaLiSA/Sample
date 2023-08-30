package com.common.objectpool

data class TestUser(
    val uid: Long,
    val name: String
) : ModelObject {
    override val objectUniqueId: Long
        get() = uid
}