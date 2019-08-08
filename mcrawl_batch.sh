#!/bin/bash

#set -x
# java -Xmx2048m -Djava.library.path=C:\Microsoft  -jar target/simple-metadata-crawler-1.0-SNAPSHOT.jar
CMD="java -jar target/simple-metadata-crawler-0.1.0.jar"
DATADIR=${DATADIR:-"./data"}
DATE=$(date +%Y-%m-%d)



# url, user, password, file_name_without_extension, optional catalogs regex filter
function crawl
{
	#DATE=$(date +%Y-%m-%d)
	OUTDIR=$DATADIR/$DATE
	mkdir -p $OUTDIR
	OUT_NAME=$OUTDIR"/"$4"_"$DATE
	
    rm -rf $OUT_NAME".log"
	LOG_COMMAND="tee -a $OUT_NAME.log"
	
	echo "Processing $4: $1" | $LOG_COMMAND
	echo $(date +%Y-%m-%d--%H-%M-%S) 2>&1 | $LOG_COMMAND
	START=$(date +%s.%N)
	
	catalogs=${5:-""}
	echo "Catalogs: $catalogs" | $LOG_COMMAND
	export JDBC_PASSWORD="$3" && $CMD --url $1 --catalogs "$catalogs" --user $2  --format avro -o $OUT_NAME".avro" 2>&1 | $LOG_COMMAND
	export JDBC_PASSWORD=""
	
	END=$(date +%s.%N)
	DIFF=$(echo "$END - $START" | bc)
	echo "Processing $4 ended in $DIFF seconds"  | $LOG_COMMAND
}

# Mysql
# crawl "jdbc:mysql://server:33306" "user" "pass" "server"

# SQl server
# crawl "jdbc:sqlserver://server:1433" "user" "pass" "server" "Catalog1|Catalog2|Catalog3"

# Oracle
# crawl "jdbc:oracle:thin:@server:1521:I" "user" "pass" "server__I"

# Informix
# crawl "jdbc:informix-sqli://server:19088/sysadmin:informixserver=informix" "user" "pass" "server__informix"

