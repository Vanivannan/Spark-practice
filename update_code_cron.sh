#!/bin/sh
set -x
export LANG=zh_CN.UTF-8
source_path=/opt/projects/Spark-practice

# 0,30 * * * * /bin/sh /opt/projects/Spark-practice/update_code_cron.sh 2>&1 >/opt/opensource/studyspace/$(date +\%Y\%m\%d).log
echo ''
echo "update time: `date`"
echo '**********************************************************************************'
cd ${source_path}
echo "update code date is: `date`" >>tip.txt
/usr/git/bin/git add . && /usr/git/bin/git commit -m "update file"
/usr/git/bin/git push origin master
/usr/git/bin/git stash && git pull origin master
echo '**********************************************************************************'
