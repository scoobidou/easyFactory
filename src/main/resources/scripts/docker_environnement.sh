#!/bin/bash
# INDUSTRIALISATION LOGICIEL
# Script Version 0.1

# Dependences

    apt update && apt upgrade
    apt-get install curl ca-certificates apt-transport-https

# Installation Docker & Docker Compose:

    echo "deb http://http.debian.net/debian wheezy-backports main" >> /etc/apt/sources.list
    apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys 58118E89F3A912897C070ADBF76221572C52609D
    echo "deb https://apt.dockerproject.org/repo debian-jessie main" >> /etc/apt/sources.list.d/docker.list
    apt update && apt upgrade -y
    apt-get install docker-engine --y
    service docker start

# Download docker-compose binaries and add execute permissions on it. Then download bash auto completion for docker-compose and add it to Bash.`

    curl -L https://github.com/docker/compose/releases/download/1.8.0/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
    chmod +x /usr/local/bin/docker-compose
    curl -L https://raw.githubusercontent.com/docker/compose/$(docker-compose version --short)/contrib/completion/bash/docker-compose > /etc/bash_completion.d/

# Expose Docker on the internet to enable remote access to docker daemon:

    mkdir /etc/systemd/system/docker.service.d 
    echo "[Service]" >> /etc/systemd/system/docker.service.d/docker_opts.conf 
    echo "ExecStart=" >> /etc/systemd/system/docker.service.d/docker_opts.conf 
    echo "ExecStart=/usr/bin/docker daemon -H tcp://192.168.4.248:2375 -H unix:///var/run/docker.sock" >> /etc/systemd/system/docker.service.d/docker_opts.conf 
    systemctl daemon-reload 
    systemctl restart docker

# Executer l'environnement avec Docker Compose:

    cd /CHEMIN_ACCES_DOCKER COMPOSER/
    docker-compose up -d

# Arreter l'environnement avec Docker Compose:
	# cd /CHEMIN_ACCES_DOCKER COMPOSER/
	# docker-compose stop
	
# Arreter et supprimer l'environnement avec Docker Compose:
    # cd /CHEMIN_ACCES_DOCKER COMPOSER/
    # docker-compose down