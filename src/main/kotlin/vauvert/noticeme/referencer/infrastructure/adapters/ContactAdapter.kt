package vauvert.noticeme.referencer.infrastructure.adapters

import org.springframework.stereotype.Repository
import vauvert.noticeme.referencer.domain.entity.Contact
import vauvert.noticeme.referencer.domain.port.repository.ContactRepository
import vauvert.noticeme.referencer.domain.utils.ContactId
import vauvert.noticeme.referencer.infrastructure.database.ContactDatabaseAccess

@Repository
class ContactAdapter(private val contactDatabaseAccess: ContactDatabaseAccess) : ContactRepository {
    override fun getContact(contactId: ContactId): Contact? = contactDatabaseAccess.getContact(contactId)
}