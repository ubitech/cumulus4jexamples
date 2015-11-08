#!/bin/bash
echo -n "Install libraries not existing in Central Maven Repository" 

mvn install:install-file -Dfile=org.cumulus4j.store-1.2.1-SNAPSHOT.jar -DgroupId=org.cumulus4j -DartifactId=org.cumulus4j.store -Dversion=1.2.1 -Dpackaging=jar

mvn install:install-file -Dfile=org.cumulus4j.annotation-1.2.1-SNAPSHOT.jar -DgroupId=org.cumulus4j -DartifactId=org.cumulus4j.annotation -Dversion=1.2.1 -Dpackaging=jar

mvn install:install-file -Dfile=org.cumulus4j.crypto-1.2.1-SNAPSHOT.jar -DgroupId=org.cumulus4j -DartifactId=org.cumulus4j.crypto -Dversion=1.2.1 -Dpackaging=jar

mvn install:install-file -Dfile=org.cumulus4j.testutil-1.2.1-SNAPSHOT.jar -DgroupId=org.cumulus4j -DartifactId=org.cumulus4j.testutil -Dversion=1.2.1 -Dpackaging=jar

mvn install:install-file -Dfile=org.cumulus4j.store.test-1.2.1-SNAPSHOT.jar -DgroupId=org.cumulus4j -DartifactId=org.cumulus4j.store.test -Dversion=1.2.1 -Dpackaging=jar

clear ; echo "Installation Done!"

if [ -d $HOME/bin ]; then
PATH=$PATH:$HOME/bin
fi
 
