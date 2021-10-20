#!/opt/procursus/bin/fish

mvn clean install
scp -i /Volumes/Data/Keys/id_rsa target/me.sillysock.SillyNick-1.0-SNAPSHOT.jar sillysock@165.22.54.78:~/SillyCraft/plugins
