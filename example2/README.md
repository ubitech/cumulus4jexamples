# example1
This example provides an overview of Cumulus4J
Important Note
--------------
Before you execute the examples please execute the install.sh script that exists in the folder requiredmavenbinaries


Structural differences
----------------------
persistence.xml
  <property name="datanucleus.storeManagerType" value="rdbms"/> is replaced with
  <property name="datanucleus.storeManagerType" value="cumulus4j"/>