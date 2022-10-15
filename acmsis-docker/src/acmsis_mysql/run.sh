#!/bin/bash

mysql -uroot -p$MYSQL_ROOT_PASSWORD << EOF
system echo '================Start create database acmsis====================';
source $WORK_PATH/$FILE_0;
system echo '=====================Databases is Created!=======================';
quit;
EOF


