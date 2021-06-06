package no.fintlabs.federateduser;

import lombok.Data;
import no.fintlabs.ldap.BasicLdapEntry;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;
import org.springframework.ldap.support.LdapNameBuilder;

import javax.naming.Name;

@Data
@Entry(objectClasses = {"person", "inetOrgPerson", "top"})
public class FederatedUser implements BasicLdapEntry {

    @Id
    private Name dn;

    @Attribute(name = "mail")
    private String email;

    public String getDn() {
        if (dn != null) {
            return dn.toString();
        } else {
            return null;
        }
    }

    @Override
    public void setDn(String dn) {
        this.dn = LdapNameBuilder.newInstance(dn).build();
    }

    @Override
    public void setDn(Name dn) {
        this.dn = dn;
    }
}