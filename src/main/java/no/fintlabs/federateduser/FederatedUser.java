package no.fintlabs.federateduser;

import lombok.Data;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Data
@Entry(objectClasses = {"person", "inetOrgPerson", "top"}, base = "ou=federated-users,o=fint")
public final class FederatedUser {

    @Id
    private Name dn;

    @Attribute(name = "mail")
    private String email;
}