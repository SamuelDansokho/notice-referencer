package vauvert.noticeme.referencer.domain.port.repository

import vauvert.noticeme.referencer.domain.entity.Company
import vauvert.noticeme.referencer.domain.entity.Contact
import vauvert.noticeme.referencer.domain.utils.CompanyId
import vauvert.noticeme.referencer.domain.utils.ContactId

interface ContactRepository {

    fun getContact(contactId: ContactId) : Contact?
}