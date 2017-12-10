# demo-rest-validacion

 features:list | grep hiber

 JBossFuse:karaf@root> features:list | grep hiber
[uninstalled] [4.2.22.Final-redhat-1] hibernate                                     karaf-enterprise-2.4.0.redhat-630187   Hibernate 4.2.x JPA persistence engine support
[uninstalled] [4.2.22.Final-redhat-1] hibernate-envers                              karaf-enterprise-2.4.0.redhat-630187   Hibernate Envers 4.2.x
[uninstalled] [5.2.4.Final          ] hibernate-validator                           karaf-enterprise-2.4.0.redhat-630187   Hibernate Validator support
[uninstalled] [2.1.0.redhat-630187  ] switchyard-camel-jpa-hibernate                switchyard-2.1.0.redhat-630187     

features:install hibernate-validator


[uninstalled] [4.2.22.Final-redhat-1] hibernate                                     karaf-enterprise-2.4.0.redhat-630187   Hibernate 4.2.x JPA persistence engine support
[uninstalled] [4.2.22.Final-redhat-1] hibernate-envers                              karaf-enterprise-2.4.0.redhat-630187   Hibernate Envers 4.2.x
[installed  ] [5.2.4.Final          ] hibernate-validator                           karaf-enterprise-2.4.0.redhat-630187   Hibernate Validator support
[uninstalled] [2.1.0.redhat-630187  ] switchyard-camel-jpa-hibernate                switchyard-2.1.0.redhat-630187      

