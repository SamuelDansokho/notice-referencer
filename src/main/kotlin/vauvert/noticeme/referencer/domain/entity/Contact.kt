package vauvert.noticeme.referencer.domain.entity

import vauvert.noticeme.referencer.domain.utils.Address
import vauvert.noticeme.referencer.domain.utils.Email
import vauvert.noticeme.referencer.domain.utils.PhoneNumber

data class Contact(
    val email: Email,
    val address : Address,
    val phoneNumbers: List<PhoneNumber?>,
)
