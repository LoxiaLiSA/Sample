package com.common.objectpool

import kotlin.reflect.KClass

data class ObjectKey(
    val id: Long,
    val classSpec: KClass<*>
)