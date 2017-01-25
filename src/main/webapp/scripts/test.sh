####################################

#!/bin/sh

# Industrialisation Logiciel
# Script version 0.2

usage(){
	echo "Usage: $0 HOST PASSWORD init | start | stop | help"
	echo "$0 help for more informations"
	echo "Installation and activation of remote access for docker for Ubuntu distributions"
	echo "Design for versions 12.04 - 14.04 - 15.10 - 16.04"
	exit 1
}

help(){
	echo -e "\n Usage : $0 HOST PASSWORD init | start | stop | help \n"
	echo -e "Example : $0 192.168.1.13 network init"
	echo -e "Example : $0 start "
	echo -e "Example : $0 stop "
	echo -e "Example : $0 help \n"
	echo -e "init :"
	echo -e "\t Check version - start docker - download docker compose - enable remote access to docker HOST on port 2375"
	echo -e "start : "
	echo -e "\t start docker service"
	echo -e "stop : "
	echo -e "\t stop docker service \n"
	echo -e "Docker API informations :"
	echo -e "\t Access to API at : host:2375 "
	echo -e "\t Example : 192.168.1.13:2375/info"
	echo -e "\t more info for API calls at https://docs.docker.com/engine/reference/api/docker_remote_api/ "
}

checkVersion(){
	version=`lsb_release -a | grep Release | cut -c10-`
	echo $version
}

init(){
	PASSWORD=$3
	echo "$PASSWORD" | sudo -S apt-get update -y 
	sudo apt-get install apt-transport-https ca-certificates -y 
	sudo apt-key adv \
               --keyserver hkp://ha.pool.sks-keyservers.net:80 \
               --recv-keys 58118E89F3A912897C070ADBF76221572C52609D
   
   VERSION=$(checkVersion)
   
   if [ "$VERSION" = "16.04" ];then
   		REPO="deb https://apt.dockerproject.org/repo ubuntu-xenial main"
   elif [ "$VERSION" = "15.10" ];then
   		REPO="deb https://apt.dockerproject.org/repo ubuntu-wily main"
   elif [ "$VERSION" = "14.04" ];then
   		REPO="deb https://apt.dockerproject.org/repo ubuntu-trusty main"
   elif [ "$VERSION" = "12.04" ];then
   		REPO="deb https://apt.dockerproject.org/repo ubuntu-precise main"
   fi
   
   echo "$REPO" | sudo tee /etc/apt/sources.list.d/docker.list
   
   sudo apt-get update -y 
   
   # Specifics actions for various version
   
   if [ "$VERSION" = "16.04" -o "$VERSION" = "15.10" -o "$VERSION" = "14.04" ];then
   		sudo apt-get install linux-image-extra-$(uname -r) linux-image-extra-virtual -y 
   else
   		sudo apt-get install linux-image-generic-lts-trusty -y
   		sudo reboot
   fi
   
   # Install Docker & Docker Compose
   
   sudo apt-get update -y
   sudo apt-get install docker-engine -y
   
   if [ `echo $?` -eq 0 ];then
   		echo "Installation ok"
   fi
}

start(){
	sudo service docker start
}

stop(){
	sudo service docker stop
}

## Download docker-compose binaries and add execute permissions on it. Then download bash auto completion for docker-compose and add it to Bash ##
downloadDockerCompose(){

	sudo curl -L https://github.com/docker/compose/releases/download/1.8.0/docker-compose-`uname -s`-`uname -m` > docker-compose
	sudo mv docker-compose /usr/local/bin/docker-compose
	sudo chmod +x /usr/local/bin/docker-compose
	sudo curl -L https://raw.githubusercontent.com/docker/compose/$(docker-compose version --short)/contrib/completion/bash/docker-compose > docker-compose
	sudo mv docker-compose /etc/bash_completion.d/docker-compose
}

## Expose Docker on the internet to enable remote access to docker daemon ##
setupDocker(){

	HOST=$1
	
	echo "[Service]" >> docker_opts.conf 
	echo "ExecStart=" >> docker_opts.conf 
	echo "ExecStart=/usr/bin/docker daemon -H tcp://"$HOST":2375 -H unix:///var/run/docker.sock" >> docker_opts.conf 
	sudo mv docker_opts.conf /etc/systemd/system/docker.service.d/docker_opts.conf
	sudo systemctl daemon-reload 
	sudo systemctl restart docker
	
}

######################################
##               MAIN               ##
######################################



if [ "$#" -ne 3 ] && [ "$1" = "init" ];then
	echo "Illegal number of arguments"
	usage
	exit 1
elif [ "$#" -ne 1 ] && [ "$3" != "init" ];then
	echo "Illegal number of arguments 4"
	usage
	exit 1
else
	if [ "$#" -eq 1 ];then
		CASE=$1
	elif [ "$#" -eq 3 ];then
		CASE="$3"
		IP_HOST="$1"
		matchIp=`echo "$IP_HOST" | grep -E -o "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)"`
		if [ -z "$matchIp" ];then
			echo "Host check : incorrect host format"
			exit 1
		fi 
	fi
	case "$CASE" in 
		start)
				start
			;;			
		stop)
				stop
			;;
		init)
				init
				echo "Init Ok : starting service"
				start
				echo "Starting service Ok : download Docker compose"
				downloadDockerCompose
				echo "Download Ok: setup Docker"
				setupDocker $1
				echo "Setup finish."
			;;
		help)
				help
			;;
	*)
				usage
				exit 1
	esac
fi