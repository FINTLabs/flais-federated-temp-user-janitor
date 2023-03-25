# FINT LDAP Janitor
[![Build and deploy](https://github.com/FINTLabs/flais-federated-temp-user-janitor/actions/workflows/cd.yaml/badge.svg)](https://github.com/FINTLabs/flais-federated-temp-user-janitor/actions/workflows/cd.yaml)

Service to clean up all temporary federated users that NetIQ Access Manager creates in the user store.

## Propeties

| Property                               | Default                   | Description                                                         |
|----------------------------------------|---------------------------|---------------------------------------------------------------------|
| spring.ldap.username                   |                           | DN of user with priviliges to read and delete users in the base OU. |
| spring.ldap.password                   |                           | Password for the user.                                              |
| spring.ldap.urls                       |                           | Url of the LDAP server. E.g. ldap://localhost:389                   |
| spring.ldap.base                       | ou=federated-users,o=fint | Base where the temp federated users is stored.                      |
| flais.nam.federated-users-cleanup.cron | 0 0 0 * * *               | Cron expression for clean up interval. Default is every night.      |

