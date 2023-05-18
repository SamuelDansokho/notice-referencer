package vauvert.noticeme.referencer.domain.utils

import java.util.*

class Utils {

    companion object {
        fun withRandomUuid(): String {
            return UUID.randomUUID().toString()
        }
    }

}