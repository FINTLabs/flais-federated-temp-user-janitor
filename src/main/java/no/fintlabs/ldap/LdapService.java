package no.fintlabs.ldap;

import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

import javax.naming.directory.SearchControls;
import java.util.List;

@Service
public class LdapService {

    private final SearchControls searchControls;
    private final LdapTemplate ldapTemplate;

    public LdapService(LdapTemplate ldapTemplate) {
        searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        this.ldapTemplate = ldapTemplate;
    }


    public boolean entryExists(String dn) {
        try {
            ldapTemplate.lookup(LdapNameBuilder.newInstance(dn).build());
            return true;
        } catch (org.springframework.ldap.NamingException e) {
            return false;
        }
    }

    public <T> List<T> getAll(String base, Class<T> type) {
        if (entryExists(base)) {
            return ldapTemplate.findAll(LdapNameBuilder.newInstance(base).build(), searchControls, type);
        }
        return null;
    }


    public void deleteEntry(BasicLdapEntry basicLdapEntry) {
        ldapTemplate.delete(basicLdapEntry);
    }

}